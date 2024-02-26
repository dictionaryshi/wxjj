package com.wx.socketio;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatObject {

    private String userName;

    private String message;
}
