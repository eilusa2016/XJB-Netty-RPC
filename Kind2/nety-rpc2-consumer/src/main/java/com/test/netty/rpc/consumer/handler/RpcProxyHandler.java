package com.test.netty.rpc.consumer.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 
 * @author karl.xu
 *
 */
public class RpcProxyHandler extends ChannelInboundHandlerAdapter {
	private Object response;

	public Object getResponse() {
		return this.response;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("client receive message: " + msg);
		response = msg;
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.flush();
		ctx.close();
	}
}
