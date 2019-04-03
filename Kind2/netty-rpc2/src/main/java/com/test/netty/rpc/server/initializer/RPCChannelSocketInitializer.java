package com.test.netty.rpc.server.initializer;

import com.test.netty.rpc.server.handler.RegisterBeanHandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
/**
 * RPC channel C初始化
 * @author karl.xu
 *
 */
public class RPCChannelSocketInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline=ch.pipeline();
		pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0,4,0,4))
				.addLast(new LengthFieldPrepender(4))
				.addLast("encoder",new ObjectEncoder())
				.addLast("decoder",new ObjectDecoder(Integer.MAX_VALUE,ClassResolvers.cacheDisabled(null)))
				.addLast(new RegisterBeanHandler());
	}
}
