package com.test.netty.rpc.consumer;

import com.test.netty.rpc.api.service.RpcService;
import com.test.netty.rpc.consumer.proxy.RpcProxy;
/**
 * 启动客户端
 * @author karl.xu
 *
 */
public class Consumer {
	public static void main(String[] args) {
		RpcService service=new RpcProxy().create(RpcService .class); 
		String hello = service.hello("rpc");
    	System.out.println(hello);
    	System.out.println(service.add(5, 3));
    	System.out.println(service.sub(5, 3));
	}
}
