package com.wx.netty.attribute;

import com.scy.core.StringUtil;
import com.scy.core.format.MessageUtil;
import com.wx.netty.session.Session;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class SessionUtil {

    private static final Map<String, Channel> USER_ID_CHANNEL_MAP = new ConcurrentHashMap<>();

    private static final Map<String, ChannelGroup> GROUP_ID_CHANNEL_GROUP_MAP = new ConcurrentHashMap<>();

    public static boolean bindSession(Session session, Channel channel) {
        if (Objects.isNull(session) || StringUtil.isEmpty(session.getUserId()) || Objects.isNull(channel)) {
            log.error(MessageUtil.format("login error", "session", session, "channel", channel));
            return Boolean.FALSE;
        }

        USER_ID_CHANNEL_MAP.put(session.getUserId(), channel);

        channel.attr(Attributes.SESSION).set(session);

        log.info(MessageUtil.format("login success", "session", session, "onlineNumber", USER_ID_CHANNEL_MAP.size()));
        return Boolean.TRUE;
    }

    public static boolean unBindSession(Channel channel) {
        if (Objects.isNull(channel)) {
            log.error(MessageUtil.format("logout error", "channel", channel));
            return Boolean.FALSE;
        }

        if (hasLogin(channel)) {
            Session session = getSession(channel);
            if (Objects.isNull(session)) {
                log.error(MessageUtil.format("logout error", "session", session));
                return Boolean.FALSE;
            }

            USER_ID_CHANNEL_MAP.remove(session.getUserId());

            channel.attr(Attributes.SESSION).set(null);

            log.info(MessageUtil.format("logout success", "session", session, "onlineNumber", USER_ID_CHANNEL_MAP.size()));
        } else {
            log.info("logout fail, channel 未登陆");
        }
        return Boolean.TRUE;
    }

    public static boolean hasLogin(Channel channel) {
        return getSession(channel) != null;
    }

    @Nullable
    public static Session getSession(Channel channel) {
        if (Objects.isNull(channel)) {
            return null;
        }

        return channel.attr(Attributes.SESSION).get();
    }

    @Nullable
    public static Channel getChannel(String userId) {
        if (StringUtil.isEmpty(userId)) {
            return null;
        }

        return USER_ID_CHANNEL_MAP.get(userId);
    }

    public static boolean bindChannelGroup(String groupId, ChannelGroup channelGroup) {
        if (StringUtil.isEmpty(groupId) || Objects.isNull(channelGroup)) {
            log.error(MessageUtil.format("bindChannelGroup error", "groupId", groupId, "channelGroup", channelGroup));
            return Boolean.FALSE;
        }

        GROUP_ID_CHANNEL_GROUP_MAP.put(groupId, channelGroup);

        return Boolean.TRUE;
    }

    @Nullable
    public static ChannelGroup getChannelGroup(String groupId) {
        if (StringUtil.isEmpty(groupId)) {
            return null;
        }

        return GROUP_ID_CHANNEL_GROUP_MAP.get(groupId);
    }
}
