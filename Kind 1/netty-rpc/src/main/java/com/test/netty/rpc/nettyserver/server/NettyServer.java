package com.test.netty.rpc.nettyserver.server;

import com.test.netty.rpc.nettyserver.handler.HelloServerHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
/**
 * 
 * @author karl.xu
 *
 */
public class NettyServer {
	/**
	 * 启动一个简单的服务
	 * @param hostName
	 * @param port
	 */
	public static void startSimpleServer(String hostName, int port) {
	    try {
	      ServerBootstrap bootstrap = new ServerBootstrap();
	      NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
	      bootstrap.group(eventLoopGroup)
	          .channel(NioServerSocketChannel.class)
	          .childHandler(new ChannelInitializer<SocketChannel>() {
	            @Override
	            protected void initChannel(SocketChannel ch) throws Exception {
	              ChannelPipeline p = ch.pipeline();
	              p.addLast(new StringDecoder());
	              p.addLast(new StringEncoder());
	              p.addLast(new HelloServerHandler());
	            }
	          });
	      bootstrap.bind(hostName, port).sync();
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	  }
}
