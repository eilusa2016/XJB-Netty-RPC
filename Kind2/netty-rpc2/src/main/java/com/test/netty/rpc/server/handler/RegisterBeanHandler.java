package com.test.netty.rpc.server.handler;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.test.netty.rpc.api.util.InvokeMsg;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 扫描以及注册所有的BEAN
 * @author karl.xu
 *
 */
public class RegisterBeanHandler extends ChannelInboundHandlerAdapter {
	
	public static ConcurrentHashMap<String, Object> classMap = new ConcurrentHashMap<String, Object>();
	
	private List<String> classCache = new ArrayList<String>();
	
	public RegisterBeanHandler() {
		/**
		 * 1，掃描
		 */
		scanPackageBeans("com.test.netty.rpc.service.impl");
		/**
		 * 2，注冊
		 */
		doRegister();
	}
	
	
	private void doRegister() {
		if(classCache.size() ==0 ){return;}
		for(String name:classCache){
			try {
				Class<?> clazz = Class.forName(name);
				Class<?> interfaces = clazz.getInterfaces()[0];//获得实现类的接口的名字
				classMap.put(interfaces.getName(), clazz.newInstance());
			} catch (Exception e) {
				
			}
		}
	}
	
	/**
	 * 掃描包下面的实现类
	 * @param packageName
	 */
	private  void scanPackageBeans(String packageName) {
		URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));
		File dirFile = new File(url.getFile());
		for(File file:dirFile.listFiles()){
			if(file.isDirectory()){
				//递归加载
				scanPackageBeans(packageName+"."+file.getName());
			}else{
				classCache.add(packageName+"."+file.getName().replace(".class", ""));
			}
		}
	}
	
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		Object result = new Object();
		InvokeMsg request = (InvokeMsg)msg;
		if(classMap.containsKey(request.getClassName())){
			Object clazz = classMap.get(request.getClassName());
			Method method = clazz.getClass().getMethod(request.getMethodName(),
            request.getParamTypes());
			result = method.invoke(clazz, request.getValues());
		}
		ctx.writeAndFlush(result);
		ctx.close();
	}
 
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.flush();
		ctx.close();
	}
}
