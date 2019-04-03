package com.test.netty.rpc.api.service;


public interface RpcService {
	String hello(String name);
	int add(int a,int b);
	int sub(int a,int b);
}
