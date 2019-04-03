package com.test.netty.rpc.nettyserver.starter;

import com.test.netty.rpc.constant.ClientConstant;
import com.test.netty.rpc.nettyserver.server.NettyServer;

/**
 * 啓動服務
 * @author karl.xu
 *
 */
public class ServerStarter {

	public static void main(String[] args) {
		NettyServer.startSimpleServer(ClientConstant.ServerHOST, ClientConstant.ServerPORT);
	}
}
