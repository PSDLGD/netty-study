package com.beinglee.nettystudy.server.handler;

import com.beinglee.nettystudy.protocol.packet.MsgRequestPacket;
import com.beinglee.nettystudy.protocol.packet.MsgResponsePacket;
import com.beinglee.nettystudy.server.Session;
import com.beinglee.nettystudy.utils.SessionUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MsgRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MsgRequestPacket msgRequestPacket) {
        Session session = SessionUtils.getSession(ctx.channel());

        MsgResponsePacket responsePacket = new MsgResponsePacket();
        responsePacket.setMessage(msgRequestPacket.getMessage());
        responsePacket.setFromUserId(session.getUserId());
        responsePacket.setFromUserName(session.getUserName());

        Channel toUserChannel = SessionUtils.getChannel(msgRequestPacket.getToUserId());
        if (toUserChannel != null && SessionUtils.hasLogin(toUserChannel)) {
            toUserChannel.writeAndFlush(responsePacket);
        } else {
            System.err.println("[" + msgRequestPacket.getToUserId() + "] 不在线，发送失败!");
        }
    }
}
