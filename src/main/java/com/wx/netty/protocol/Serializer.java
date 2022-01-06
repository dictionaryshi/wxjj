package com.wx.netty.protocol;

import com.fasterxml.jackson.core.type.TypeReference;

public interface Serializer {

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     *
     * @return
     */
    byte getSerializerAlogrithm();

    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(byte[] bytes, TypeReference<T> typeReference);
}
