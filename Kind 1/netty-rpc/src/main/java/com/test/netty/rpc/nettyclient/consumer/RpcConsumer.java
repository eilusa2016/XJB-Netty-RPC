package com.test.netty.rpc.nettyclient.consumer;

import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.test.netty.rpc.constant.ClientConstant;
import com.test.netty.rpc.nettyclient.handler.HelloClientHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 *  消费代理类
 *  注意：
 *  	框架使用者不用关心底层的网络实现。这里我们可以使用 JDK 的动态代理来实现这个目的。
		思路：客户端调用代理方法，返回一个实现了 HelloService 接口的代理对象，调用代理对象的方法，返回结果。
		我们需要在代理中做手脚，当调用代理方法的时候，我们需要初始化 Netty 客户端，还需要向服务端请求数据，并返回数据
 * @author karl.xu
 *
 */
public class RpcConsumer {
	private static ExecutorService executor = Executors
		      									.newFixedThreadPool(Runtime
		      														.getRuntime()
		      														.availableProcessors()
		      														);
	private static HelloClientHandler client;
	
	
	
	 /**
	   * 创建一个代理对象，使用 JDK 的动态代理
	   * 
	   */
	  public Object createProxy(final Class<?> serviceClass,
	      final String providerName) {
		  return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),  new Class<?>[]{serviceClass}, (proxy, method, args) -> {
	          if (client == null) {
	              initClient();
	            }
	            // 设置参数
	            client.setPara(providerName + args[0]);
	            return executor.submit(client).get();
	          });
	  }
	
	
	  /**
	   * 初始化客户端
	   */
	  private static void initClient() {
	    client = new HelloClientHandler();
	    EventLoopGroup group = new NioEventLoopGroup();
	    Bootstrap b = new Bootstrap();
	    b.group(group)
	        .channel(NioSocketChannel.class)
	        .option(ChannelOption.TCP_NODELAY, true)
	        .handler(new ChannelInitializer<SocketChannel>() {
	          @Override
	          public void initChannel(SocketChannel ch) throws Exception {
	            ChannelPipeline p = ch.pipeline();
	            p.addLast(new StringDecoder());
	            p.addLast(new StringEncoder());
	            p.addLast(client);
	          }
	        });
	    try {
	      b.connect(ClientConstant.ServerHOST, ClientConstant.ServerPORT).sync();
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	  }
}
