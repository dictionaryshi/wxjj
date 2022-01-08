package com.wx.netty.protocol;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class Packet {

    /**
     * 协议版本
     */
    @JsonIgnore
    private Byte version = 1;

    @JsonIgnore
    public abstract int getCommand();
}
