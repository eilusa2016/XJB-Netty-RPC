package com.test.netty.rpc.provider;

import com.test.netty.rpc.rpcinterface.HelloService;
/**
 * 提供者的实现
 * @author karl.xu
 *
 */
public class HelloServiceImpl implements HelloService {

	@Override
	public String hello(String msg) {
		 return msg != null ? msg + " -----> RPC Msg" : "RPC Msg is Empty";
	}

}
