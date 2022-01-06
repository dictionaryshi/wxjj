package com.wx.netty.protocol;

import com.fasterxml.jackson.core.type.TypeReference;
import com.scy.core.json.JsonUtil;

public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlogrithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {

        return JsonUtil.writeValueAsBytes(object);
    }

    @Override
    public <T> T deserialize(byte[] bytes, TypeReference<T> typeReference) {
        return JsonUtil.json2Object(bytes, typeReference);
    }
}
