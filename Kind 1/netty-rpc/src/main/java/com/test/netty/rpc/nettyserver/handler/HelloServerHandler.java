package com.test.netty.rpc.nettyserver.handler;

import com.test.netty.rpc.constant.ClientConstant;
import com.test.netty.rpc.provider.HelloServiceImpl;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 处理请求的数据
 * 
 * @author karl.xu
 *
 */
public class HelloServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		 // 如何符合约定，则调用本地方法，返回数据
	    if (msg.toString().startsWith(ClientConstant.providerName)) {
	      String result = new HelloServiceImpl()
	          .hello(msg.toString().substring(msg.toString().lastIndexOf("#") + 1));
	      ctx.writeAndFlush(result);
	    }
	}
}
