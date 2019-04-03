package com.test.netty.rpc.nettyclient.handler;

import java.util.concurrent.Callable;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Netty的客户端
 * client 既是 handler ，也是一个 Callback
 * @author karl.xu
 *
 */
public class HelloClientHandler extends ChannelInboundHandlerAdapter implements Callable<Object> {

	private ChannelHandlerContext context;
	private String result;
	private String para;
	
	 @Override
	  public void channelActive(ChannelHandlerContext ctx) {
	    context = ctx;
	  }

	  /**
	   * 收到服务端数据，唤醒等待线程
	   */
	  @Override
	  public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) {
	    result = msg.toString();
	    notify();
	  }
	
	/**
	 * 写出数据
	 *开始等待唤醒
	 */
	@Override
	public synchronized Object call() throws Exception {
		context.writeAndFlush(para);
	    wait();
	    return result;
	}
	public void setPara(String para) {
	    this.para = para;
	}
}
