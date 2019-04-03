package com.test.netty.rpc.service.impl;

import com.test.netty.rpc.api.service.RpcService;

public class RpcServerImpl implements  RpcService{

	@Override
	public String hello(String name) {
		return "Test RPC Method paramter is: "+name;
	}
 
	@Override
	public int add(int a, int b) {
		return a + b;
	}
 
	@Override
	public int sub(int a, int b) {
		return a - b;
	}
}
