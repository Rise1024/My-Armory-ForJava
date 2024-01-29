package com.es.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: DS
 * @Date: 2024/1/26 17:20
 * @Description:
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String originType;
    private String fromType;
    private Integer tenantId;
    private Integer organId;
    private String fromId;
    private String sessionId;
    private String channelId;
    private Long createTime;
    private Long createDateTime;


}
