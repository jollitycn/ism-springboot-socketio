package com.jollitycn.socket.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * descripiton: 客户端逻辑处理
 *
 * @author: www.jollitycn
 * @date: 2018/3/23
 * @time: 16:50
 * @modifier:
 * @since:
 */
public class ClientHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object s) throws Exception {
        System.out.println(" ClientHandler channelRead0" + s);
    }

}
