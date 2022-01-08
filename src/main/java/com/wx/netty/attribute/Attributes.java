package com.wx.netty.attribute;

import com.wx.netty.session.Session;
import io.netty.util.AttributeKey;

public interface Attributes {

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
