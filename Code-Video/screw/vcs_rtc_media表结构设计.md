# 数据库设计文档

**数据库名：** audio2

**文档版本：** 1.0.0

**文档描述：** 数据库设计文档生成

| 表名                  | 说明       |
| :---: | :---: |
| [agora_kefu_user](#agora_kefu_user) | 客服-声网rtcuser映射关系 |
| [agora_project_info](#agora_project_info) | 项目信息表 |
| [agora_rtc_media_call](#agora_rtc_media_call) | 声网视频通话记录表 |
| [agora_rtc_media_call_detail](#agora_rtc_media_call_detail) | 声网视频通话记录详情表 |
| [agora_rtc_media_call_log](#agora_rtc_media_call_log) | 声网视频通话日志表 |
| [agora_rtc_media_call_record](#agora_rtc_media_call_record) | 声网视频通话录制表 |
| [agora_rtc_media_share_detail](#agora_rtc_media_share_detail) | 共享屏幕详情记录表 |
| [agora_tenant_info](#agora_tenant_info) | 租户vec相关信息表 |
| [agora_whiteboard_room](#agora_whiteboard_room) | 白板坐席房间分配表 |

## 客服-声网rtcuser映射关系(agora_kefu_user)
**表名：** <a id="agora_kefu_user">agora_kefu_user</a>

**说明：** 客服-声网rtcuser映射关系

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 声网rtcuid  |
|  2   | tenant_id |   int   | 10 |   0    |    N     |  N   |       | 环信租户id  |
|  3   | user_id |   varchar   | 36 |   0    |    Y     |  N   |       | 环信用户id  |
|  4   | user_type |   enum   | 15 |   0    |    Y     |  N   |   Agent    | 用户类型Agent-坐席、Visitor-访客、RecordSingle-单流录制用户、合流录制用户,ShareScreen-共享屏幕,视频通话外部访客  |
|  5   | created |   datetime   | 19 |   0    |    N     |  N   |       | 创建时间  |
|  6   | updated |   datetime   | 19 |   0    |    Y     |  N   |       | 更新时间  |

## 项目信息表(agora_project_info)
**表名：** <a id="agora_project_info">agora_project_info</a>

**说明：** 项目信息表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | ID  |
|  2   | projectId |   varchar   | 20 |   0    |    Y     |  N   |       | 项目id  |
|  3   | name |   varchar   | 100 |   0    |    Y     |  N   |       | 项目名称  |
|  4   | vendorKey |   varchar   | 32 |   0    |    N     |  N   |       | AppID  |
|  5   | signKey |   varchar   | 32 |   0    |    Y     |  N   |       | App证书  |
|  6   | signSecret |   varchar   | 200 |   0    |    Y     |  N   |       | 回掉签名  |
|  7   | recordingServer |   varchar   | 20 |   0    |    Y     |  N   |       | 录制项目服务器IP地址  |
|  8   | whiteboardId |   varchar   | 64 |   0    |    Y     |  N   |       | 白板AppId  |
|  9   | whiteboardAk |   varchar   | 64 |   0    |    Y     |  N   |       | 白板Ak  |
|  10   | whiteboardSk |   varchar   | 64 |   0    |    Y     |  N   |       | 白板Sk  |
|  11   | status |   tinyint   | 4 |   0    |    N     |  N   |   0    | 状态  |
|  12   | created |   datetime   | 19 |   0    |    N     |  N   |   CURRENT_TIMESTAMP    | 创建时间  |
|  13   | updated |   datetime   | 19 |   0    |    Y     |  N   |       | 更新时间  |

## 声网视频通话记录表(agora_rtc_media_call)
**表名：** <a id="agora_rtc_media_call">agora_rtc_media_call</a>

**说明：** 声网视频通话记录表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   bigint   | 20 |   0    |    N     |  Y   |       | id  |
|  2   | tenant_id |   int   | 10 |   0    |    N     |  N   |       | 环信租户id  |
|  3   | channel_name |   varchar   | 64 |   0    |    N     |  N   |       | 视频通话声网频道名  |
|  4   | status |   enum   | 7 |   0    |    N     |  N   |       | Ring-响铃Cancel-访客取消视频通话Reject-访客拒绝视频通话Call-通话中Timeout-超时End-通话结束  |
|  5   | inviter_id |   varchar   | 36 |   0    |    Y     |  N   |       | 发起人id  |
|  6   | inviter_type |   enum   | 7 |   0    |    N     |  N   |       | 发起人type  |
|  7   | invitee_id |   varchar   | 36 |   0    |    Y     |  N   |       | 被呼人id  |
|  8   | invitee_type |   enum   | 7 |   0    |    N     |  N   |       | 被呼人type  |
|  9   | created |   datetime   | 19 |   0    |    N     |  N   |       | 创建时间  |
|  10   | updated |   datetime   | 19 |   0    |    Y     |  N   |       | 更新时间  |
|  11   | origin_system |   enum   | 7 |   0    |    Y     |  N   |   MESSAGE    | 来源系统：MESSAGE-在线，VIDEO-独立音视频  |

## 声网视频通话记录详情表(agora_rtc_media_call_detail)
**表名：** <a id="agora_rtc_media_call_detail">agora_rtc_media_call_detail</a>

**说明：** 声网视频通话记录详情表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   bigint   | 20 |   0    |    N     |  Y   |       | id  |
|  2   | tenant_id |   int   | 10 |   0    |    N     |  N   |       | 环信租户id  |
|  3   | call_id |   bigint   | 20 |   0    |    N     |  N   |       | 主表agora_rtc_media_callid  |
|  4   | inviter_id |   varchar   | 36 |   0    |    Y     |  N   |       | 邀请人id  |
|  5   | invitee_id |   varchar   | 36 |   0    |    Y     |  N   |       | 被邀请人id  |
|  6   | invite_type |   enum   | 24 |   0    |    N     |  N   |       | 邀请类型坐席呼叫访客、访客呼叫坐席、坐席呼叫坐席、坐席邀请外部人员、访客邀请外部人员  |
|  7   | join_call |   tinyint   | 4 |   0    |    Y     |  N   |   0    | 是否加入  |
|  8   | join_date |   datetime   | 19 |   0    |    Y     |  N   |       | 被邀请人加入时间  |
|  9   | leave_date |   datetime   | 19 |   0    |    Y     |  N   |       | 被邀请人离开时间  |
|  10   | created |   datetime   | 19 |   0    |    N     |  N   |       | 创建时间  |
|  11   | updated |   datetime   | 19 |   0    |    Y     |  N   |       | 更新时间  |
|  12   | ext |   varchar   | 500 |   0    |    Y     |  N   |       | 扩展字段  |

## 声网视频通话日志表(agora_rtc_media_call_log)
**表名：** <a id="agora_rtc_media_call_log">agora_rtc_media_call_log</a>

**说明：** 声网视频通话日志表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   bigint   | 20 |   0    |    N     |  Y   |       | 主键  |
|  2   | tenant_id |   int   | 10 |   0    |    N     |  N   |       | 环信租户id  |
|  3   | call_id |   bigint   | 20 |   0    |    N     |  N   |       | 主表agora_rtc_media_callid  |
|  4   | created |   datetime   | 19 |   0    |    N     |  N   |       | 创建时间  |
|  5   | occurrence |   datetime   | 19 |   0    |    N     |  N   |       | 事件发生时间  |
|  6   | state |   int   | 10 |   0    |    Y     |  N   |       | 操作对应状态码  |

## 声网视频通话录制表(agora_rtc_media_call_record)
**表名：** <a id="agora_rtc_media_call_record">agora_rtc_media_call_record</a>

**说明：** 声网视频通话录制表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   bigint   | 20 |   0    |    N     |  Y   |       | id  |
|  2   | tenant_id |   int   | 10 |   0    |    N     |  N   |       | 环信租户id  |
|  3   | call_id |   bigint   | 20 |   0    |    N     |  N   |       | 主表agora_rtc_media_callid  |
|  4   | user_id |   varchar   | 36 |   0    |    Y     |  N   |       | 被录制人id  |
|  5   | record_type |   enum   | 10 |   0    |    N     |  N   |       | 录制type  |
|  6   | sid |   varchar   | 100 |   0    |    Y     |  N   |       | 声网录制id，贯穿整个录制生命周期  |
|  7   | resource_id |   varchar   | 500 |   0    |    Y     |  N   |       | 声网录制资源id，贯穿整个录制生命周期  |
|  8   | record_start |   datetime   | 19 |   0    |    Y     |  N   |       | 录制开始时间（声网的真实录制开始时间）  |
|  9   | record_end |   datetime   | 19 |   0    |    Y     |  N   |       | 录制结束时间（环信调用声网录制结束成功的时间）  |
|  10   | playback_url |   varchar   | 500 |   0    |    Y     |  N   |       | 回放地址  |
|  11   | created |   datetime   | 19 |   0    |    N     |  N   |       | 创建时间  |
|  12   | updated |   datetime   | 19 |   0    |    Y     |  N   |       | 更新时间  |

## 共享屏幕详情记录表(agora_rtc_media_share_detail)
**表名：** <a id="agora_rtc_media_share_detail">agora_rtc_media_share_detail</a>

**说明：** 共享屏幕详情记录表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   bigint   | 20 |   0    |    N     |  Y   |       | 主键id  |
|  2   | tenant_id |   int   | 10 |   0    |    Y     |  N   |       | 租户id  |
|  3   | call_id |   bigint   | 20 |   0    |    Y     |  N   |       | 通话id  |
|  4   | sharer_id |   int   | 10 |   0    |    Y     |  N   |       | 共享屏幕声网uid  |
|  5   | user_id |   varchar   | 36 |   0    |    Y     |  N   |       | 客服系统访客id或坐席id  |
|  6   | user_type |   varchar   | 36 |   0    |    Y     |  N   |       | 共享者类型，坐席,访客  |
|  7   | begin_date |   datetime   | 19 |   0    |    Y     |  N   |       | 共享开始时间  |
|  8   | end_date |   datetime   | 19 |   0    |    Y     |  N   |       | 共享结束时间  |
|  9   | created |   datetime   | 19 |   0    |    Y     |  N   |       | 创建时间  |
|  10   | updated |   datetime   | 19 |   0    |    Y     |  N   |       | 更新时间  |

## 租户vec相关信息表(agora_tenant_info)
**表名：** <a id="agora_tenant_info">agora_tenant_info</a>

**说明：** 租户vec相关信息表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | ID  |
|  2   | tenantId |   int   | 10 |   0    |    N     |  N   |       | 环信租户id  |
|  3   | appId |   varchar   | 32 |   0    |    N     |  N   |       | AppID  |
|  4   | created |   datetime   | 19 |   0    |    N     |  N   |   CURRENT_TIMESTAMP    | 创建时间  |
|  5   | updated |   datetime   | 19 |   0    |    Y     |  N   |       | 更新时间  |

## 白板坐席房间分配表(agora_whiteboard_room)
**表名：** <a id="agora_whiteboard_room">agora_whiteboard_room</a>

**说明：** 白板坐席房间分配表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | ID  |
|  2   | tenantId |   int   | 10 |   0    |    N     |  N   |       | 租户ID  |
|  3   | callId |   bigint   | 20 |   0    |    Y     |  N   |       | 通话ID  |
|  4   | roomId |   varchar   | 32 |   0    |    N     |  N   |       | 房间ID  |
|  5   | isBan |   tinyint   | 4 |   0    |    N     |  N   |   0    | 封禁状态  |
|  6   | createTime |   datetime   | 19 |   0    |    N     |  N   |   CURRENT_TIMESTAMP    | 创建时间  |
|  7   | updateTime |   datetime   | 19 |   0    |    Y     |  N   |       | 更新时间  |
