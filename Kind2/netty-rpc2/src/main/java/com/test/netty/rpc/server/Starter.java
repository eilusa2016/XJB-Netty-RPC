package com.test.netty.rpc.server;

import com.test.netty.rpc.api.constant.ClientConstant;
import com.test.netty.rpc.server.handler.RpcRegistry;

public class Starter {
	public static void main(String[] args) {
		RpcRegistry.getInstance().startServer(ClientConstant.ServerPORT);
	}
}
