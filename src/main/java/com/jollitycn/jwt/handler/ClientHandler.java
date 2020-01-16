package com.jollitycn.jwt.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

public class ClientHandler extends SimpleChannelInboundHandler<Object> {

//    @Override
//    protected void messageReceived(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
//        //客户端读取服务端发送回来的消息，只是进行显示
//        System.out.println(msg);
//    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object s) throws Exception {
        System.out.println(" client channelRead0" + s);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        try {
            //  ByteBuf bb = (ByteBuf)msg;
//            byte[] respByte = new byte[bb.readableBytes()];
//            bb.readBytes(respByte);
//            String respStr = new String(respByte, "UTF-8");
            System.err.println("client--收到响应：" + msg);

            // 直接转成对象
//          handlerObject(ctx, msg);

        } finally {
            // 必须释放msg数据
            ReferenceCountUtil.release(msg);

        }

    }

    // 数据读取完毕的处理
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.err.println("客户端读取数据完毕");
    }

    // 出现异常的处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("client 读取数据出现异常" + cause);
        ctx.close();
    }
}
