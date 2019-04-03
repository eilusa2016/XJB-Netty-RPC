package com.test.netty.rpc.server.handler;

import com.test.netty.rpc.server.initializer.RPCChannelSocketInitializer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class RpcRegistry {
	public  static  final String LOCALHOST="localhost";
	
	private RpcRegistry() {}
	
	public static RpcRegistry getInstance() {
		return new RpcRegistry();
	}
	/**
	 * 默认本地
	 * @param port
	 */
	public  void  startServer(int  port) {
		startServer(LOCALHOST, port);
	}
	
	public  void  startServer(String  host,int  port) {
		EventLoopGroup bossGroup=new NioEventLoopGroup();
		EventLoopGroup workGrup=new NioEventLoopGroup();
		ServerBootstrap bootstrap=new ServerBootstrap();
		try {
			bootstrap.group(bossGroup, workGrup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new RPCChannelSocketInitializer())
					.option(ChannelOption.SO_BACKLOG, 128)
					.childOption(ChannelOption.SO_KEEPALIVE, true);
			//绑定
			ChannelFuture future = bootstrap.bind(host, port);
			System.out.println("server listener at port : "+port);
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			bossGroup.shutdownGracefully();
			workGrup.shutdownGracefully();
		}finally {
			
		}
	}
	
}
