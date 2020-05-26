package com.beinglee.nettystudy.utils;

import com.beinglee.nettystudy.protocol.Attributes;
import com.beinglee.nettystudy.server.Session;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhanglu
 * @date 2020/5/25 20:39
 */
public abstract class SessionUtils {

    private static final Map<String, Channel> userIdChannels = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel) {
        userIdChannels.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            String userId = getSession(channel).getUserId();
            userIdChannels.remove(userId);
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static Channel getChannel(String userId) {
        return userIdChannels.get(userId);
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attributes.SESSION);
    }


}
