package com.netty.webscoket.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther:zds
 * @Date: 20-4-14 10:39
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SockjsConnectionEvent {
    private boolean isConnect;
}
