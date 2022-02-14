package com.wx.netty.protocol;

import com.scy.netty.protocol.AbstractPacket;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.wx.netty.protocol.Command.LOGOUT_REQUEST;

@Getter
@Setter
@ToString
public class LogoutRequestPacket extends AbstractPacket {

    @Override
    public int getCommand() {
        return LOGOUT_REQUEST;
    }
}
