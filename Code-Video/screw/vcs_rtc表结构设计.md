# 数据库设计文档

**数据库名：** vcs_rtc

**文档版本：** 1.0.0

**文档描述：** 数据库设计文档生成

| 表名                  | 说明       |
| :---: | :---: |
| [rtc_agent_queue](#rtc_agent_queue) | 视频技能组 |
| [rtc_agent_queue_and_agent](#rtc_agent_queue_and_agent) | 技能组与坐席的关系表 |
| [rtc_agent_state](#rtc_agent_state) | VEC坐席状态表 |
| [rtc_business_flow](#rtc_business_flow) | 业务流程表 |
| [rtc_card_ocr_record](#rtc_card_ocr_record) | ocr识别记录表 |
| [rtc_chat_group](#rtc_chat_group) | 文本聊天讨论组表 |
| [rtc_chat_message](#rtc_chat_message) | 文本聊天消息记录 |
| [rtc_elecsign_record](#rtc_elecsign_record) | 电子签名操作记录表 |
| [rtc_enquiry](#rtc_enquiry) | 视频满意度评价表 |
| [rtc_enquiry_tag](#rtc_enquiry_tag) | 评价标签 |
| [rtc_evaluate](#rtc_evaluate) | 服务小结信息表 |
| [rtc_evaluate_operate_log](#rtc_evaluate_operate_log) | 服务小结操作记录 |
| [rtc_identity_auth_record](#rtc_identity_auth_record) | 身份认证操作记录表 |
| [rtc_info_push_record](#rtc_info_push_record) | 信息推送记录表 |
| [rtc_plugin_config](#rtc_plugin_config) | 插件配置表 |
| [rtc_queue_schedule_grade](#rtc_queue_schedule_grade) | 技能组调度优先级表 |
| [rtc_routing_rule](#rtc_routing_rule) | 路由规则 |
| [rtc_session](#rtc_session) | 独立视频客服会话表 |
| [rtc_session_members](#rtc_session_members) | 通话成员列表 |
| [rtc_session_summary_record](#rtc_session_summary_record) | 话后小结记录表 |
| [rtc_tenant_options](#rtc_tenant_options) | 视频灰度 |
| [rtc_unanswered_task](#rtc_unanswered_task) | 未接通任务表 |
| [rtc_visitor_action_log](#rtc_visitor_action_log) | 访客行为记录 |
| [rtc_visitor_external](#rtc_visitor_external) | 外部访客信息表 |

## 视频技能组(rtc_agent_queue)
**表名：** <a id="rtc_agent_queue">rtc_agent_queue</a>

**说明：** 视频技能组

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | queueId |   int   | 10 |   0    |    N     |  Y   |       | queueId  |
|  2   | queueName |   varchar   | 45 |   0    |    Y     |  N   |       | 技能组名称  |
|  3   | queueDesc |   varchar   | 256 |   0    |    Y     |  N   |       | 技能组描述  |
|  4   | tenantId |   int   | 10 |   0    |    Y     |  N   |       | 租户id  |
|  5   | createDatetime |   datetime   | 19 |   0    |    N     |  N   |   CURRENT_TIMESTAMP    | 创建时间  |
|  6   | lastUpdateDateTime |   datetime   | 19 |   0    |    Y     |  N   |       | 更新时间  |
|  7   | queueGroupType |   enum   | 13 |   0    |    Y     |  N   |   UserDefined    | 技能组类型  |

## 技能组与坐席的关系表(rtc_agent_queue_and_agent)

**表名：** <a id="rtc_agent_queue_and_agent">rtc_agent_queue_and_agent</a>

**说明：** 技能组与坐席的关系表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | ID  |
|  2   | tenantId |   int   | 10 |   0    |    Y     |  N   |       | 租户id  |
|  3   | queueId |   int   | 10 |   0    |    Y     |  N   |       | queueId  |
|  4   | agentUserId |   char   | 36 |   0    |    Y     |  N   |       | 技能组描述  |
|  5   | priority |   int   | 10 |   0    |    Y     |  N   |   0    | 优先级  |
|  6   | createDatetime |   datetime   | 19 |   0    |    N     |  N   |   CURRENT_TIMESTAMP    | 创建时间  |
|  7   | lastUpdateDateTime |   datetime   | 19 |   0    |    Y     |  N   |       | 更新时间  |

## VEC坐席状态表(rtc_agent_state)
**表名：** <a id="rtc_agent_state">rtc_agent_state</a>

**说明：** VEC坐席状态表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   bigint   | 20 |   0    |    N     |  Y   |       | id  |
|  2   | tenantId |   int   | 10 |   0    |    N     |  N   |       | 租户id  |
|  3   | agentUserId |   varchar   | 36 |   0    |    N     |  N   |       | 坐席id  |
|  4   | state |   varchar   | 32 |   0    |    Y     |  N   |   OFFLINE    | VEC坐席状态  |
|  5   | stateType |   tinyint   | 4 |   0    |    Y     |  N   |   1    | 是否当前状态  |
|  6   | createDateTime |   datetime   | 19 |   0    |    N     |  N   |       | 创建时间  |
|  7   | lastUpdateDateTime |   datetime   | 19 |   0    |    N     |  N   |       | 更新时间  |

## 业务流程表(rtc_business_flow)
**表名：** <a id="rtc_business_flow">rtc_business_flow</a>

**说明：** 业务流程表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | ID  |
|  2   | tenantId |   int   | 10 |   0    |    N     |  N   |       | 租户Id  |
|  3   | rtcSessionId |   varchar   | 36 |   0    |    N     |  N   |       | 通话ID  |
|  4   | agentId |   char   | 36 |   0    |    N     |  N   |       | 坐席ID  |
|  5   | visitorId |   char   | 36 |   0    |    N     |  N   |       | 访客ID  |
|  6   | businessType |   varchar   | 20 |   0    |    N     |  N   |       | 业务类型，信息推送infopush、身份认证identityauth、卡证识别cardocr、电子签名elecsign、截屏抓拍elecsign  |
|  7   | isFinished |   tinyint   | 4 |   0    |    N     |  N   |   0    | 流程是否结束标识，0-未结束1-已结束  |
|  8   | origin |   varchar   | 50 |   0    |    Y     |  N   |       | 动作发起来源，客户的iframe(值为iframe),环信辅助区（值为空）  |
|  9   | createDatetime |   datetime   | 19 |   0    |    Y     |  N   |       | 创建时间  |
|  10   | url |   varchar   | 500 |   0    |    Y     |  N   |       |   |

## ocr识别记录表(rtc_card_ocr_record)
**表名：** <a id="rtc_card_ocr_record">rtc_card_ocr_record</a>

**说明：** ocr识别记录表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | ID  |
|  2   | tenantId |   int   | 10 |   0    |    N     |  N   |       | 租户Id  |
|  3   | flowId |   int   | 10 |   0    |    N     |  N   |       | 对应rtc_business_flow表的主键  |
|  4   | action |   varchar   | 50 |   0    |    N     |  N   |       | 操作  |
|  5   | mediaId |   varchar   | 100 |   0    |    Y     |  N   |       | 图片id  |
|  6   | mediaUrl |   varchar   | 500 |   0    |    Y     |  N   |       | 图片地址  |
|  7   | result |   text   | 65535 |   0    |    Y     |  N   |       | 处理结果  |
|  8   | url |   varchar   | 500 |   0    |    Y     |  N   |       | 附件地址  |
|  9   | createDatetime |   datetime   | 19 |   0    |    Y     |  N   |       | 创建时间  |

## 文本聊天讨论组表(rtc_chat_group)
**表名：** <a id="rtc_chat_group">rtc_chat_group</a>

**说明：** 文本聊天讨论组表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   bigint   | 20 |   0    |    N     |  Y   |       | 消息ID  |
|  2   | tenantId |   int   | 10 |   0    |    N     |  N   |       | 租户ID  |
|  3   | rtcSessionId |   varchar   | 36 |   0    |    N     |  N   |       | 会话ID  |
|  4   | createDateTime |   datetime   | 19 |   0    |    N     |  N   |       | 创建时间  |


## 文本聊天消息记录(rtc_chat_message)
**表名：** <a id="rtc_chat_message">rtc_chat_message</a>

**说明：** 文本聊天消息记录

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | msgId |   varchar   | 36 |   0    |    N     |  Y   |       | 消息ID  |
|  2   | tenantId |   int   | 10 |   0    |    N     |  N   |       | 租户ID  |
|  3   | rtcSessionId |   varchar   | 36 |   0    |    N     |  N   |       | 会话ID  |
|  4   | chatGroupId |   bigint   | 20 |   0    |    N     |  N   |       | 讨论组ID  |
|  5   | messageType |   varchar   | 50 |   0    |    N     |  N   |       | 消息类型  |
|  6   | fromUserId |   varchar   | 36 |   0    |    N     |  N   |       | 发送者ID  |
|  7   | fromUserType |   enum   | 9 |   0    |    N     |  N   |       | 发送者的用户类型  |
|  8   | fromUserNiceName |   varchar   | 280 |   0    |    Y     |  N   |       | 发送者名称  |
|  9   | body |   varchar   | 5000 |   0    |    N     |  N   |       | 消息体  |
|  10   | sequence |   bigint   | 20 |   0    |    N     |  N   |       | 消息顺位  |
|  11   | createDateTime |   datetime   | 19 |   0    |    N     |  N   |       | 创建时间  |


## 电子签名操作记录表(rtc_elecsign_record)
**表名：** <a id="rtc_elecsign_record">rtc_elecsign_record</a>

**说明：** 电子签名操作记录表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | ID  |
|  2   | tenantId |   int   | 10 |   0    |    N     |  N   |       | 租户Id  |
|  3   | flowId |   int   | 10 |   0    |    N     |  N   |       | 对应rtc_business_flow表的主键  |
|  4   | action |   varchar   | 50 |   0    |    N     |  N   |       | 操作  |
|  5   | url |   varchar   | 500 |   0    |    Y     |  N   |       |   |
|  6   | createDatetime |   datetime   | 19 |   0    |    Y     |  N   |       | 创建时间  |


## 视频满意度评价表(rtc_enquiry)
**表名：** <a id="rtc_enquiry">rtc_enquiry</a>

**说明：** 视频满意度评价表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   bigint   | 20 |   0    |    N     |  Y   |       | 主键  |
|  2   | tenantId |   int   | 10 |   0    |    Y     |  N   |       | 租户id  |
|  3   | rtcSessionId |   varchar   | 36 |   0    |    Y     |  N   |       | 通话id  |
|  4   | agentUserId |   char   | 36 |   0    |    Y     |  N   |       | 坐席id  |
|  5   | visitorUserId |   char   | 36 |   0    |    Y     |  N   |       | 访客id  |
|  6   | isAutoInvite |   tinyint   | 4 |   0    |    Y     |  N   |       | 1-系统邀评，0-手动邀评  |
|  7   | score |   varchar   | 15 |   0    |    Y     |  N   |       | 分值  |
|  8   | comment |   varchar   | 200 |   0    |    Y     |  N   |       | 备注  |
|  9   | tags |   varchar   | 500 |   0    |    Y     |  N   |       | 标签  |
|  10   | createDateTime |   datetime   | 19 |   0    |    Y     |  N   |       | 创建时间  |
|  11   | updateDateTime |   datetime   | 19 |   0    |    Y     |  N   |       | 更新时间  |

## 评价标签(rtc_enquiry_tag)
**表名：** <a id="rtc_enquiry_tag">rtc_enquiry_tag</a>

**说明：** 评价标签

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 主键  |
|  2   | tenantId |   int   | 10 |   0    |    Y     |  N   |       | 租户id  |
|  3   | score |   int   | 10 |   0    |    Y     |  N   |       | 分值  |
|  4   | tagName |   varchar   | 20 |   0    |    Y     |  N   |       | 标签名称  |
|  5   | createDateTime |   datetime   | 19 |   0    |    Y     |  N   |       | 创建时间  |
|  6   | updateDateTime |   datetime   | 19 |   0    |    Y     |  N   |       | 更新时间  |

## 服务小结信息表(rtc_evaluate)
**表名：** <a id="rtc_evaluate">rtc_evaluate</a>

**说明：** 服务小结信息表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   varchar   | 50 |   0    |    N     |  Y   |       | 服务小结Id  |
|  2   | tenantId |   int   | 10 |   0    |    Y     |  N   |       | 租户Id  |
|  3   | rtcSessionId |   varchar   | 50 |   0    |    Y     |  N   |       | rtc会话Id  |
|  4   | content |   text   | 65535 |   0    |    Y     |  N   |       | 服务小结评价  |
|  5   | summary |   text   | 65535 |   0    |    Y     |  N   |       | 服务小结标签id和名称  |
|  6   | createTime |   datetime   | 19 |   0    |    Y     |  N   |       | 创建时间  |
|  7   | updateTime |   datetime   | 19 |   0    |    Y     |  N   |       | 修改时间  |

## 服务小结操作记录(rtc_evaluate_operate_log)
**表名：** <a id="rtc_evaluate_operate_log">rtc_evaluate_operate_log</a>

**说明：** 服务小结操作记录

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   varchar   | 50 |   0    |    N     |  Y   |       | 服务小结操作记录id  |
|  2   | tenantId |   int   | 10 |   0    |    Y     |  N   |       | 租户Id  |
|  3   | rtcSessionId |   varchar   | 50 |   0    |    Y     |  N   |       | rtc会话Id  |
|  4   | content |   text   | 65535 |   0    |    Y     |  N   |       | 服务小结评价  |
|  5   | summary |   text   | 65535 |   0    |    Y     |  N   |       | 服务小结标签id和标签名称  |
|  6   | createTime |   datetime   | 19 |   0    |    Y     |  N   |       | 操作时间  |
|  7   | operatorId |   varchar   | 50 |   0    |    Y     |  N   |       | 操作者id（坐席Id）  |
|  8   | operatorName |   varchar   | 50 |   0    |    Y     |  N   |       | 操作者姓名（坐席真实名称）  |
|  9   | operatorAction |   varchar   | 50 |   0    |    Y     |  N   |       | 操作行为  |

## 身份认证操作记录表(rtc_identity_auth_record)
**表名：** <a id="rtc_identity_auth_record">rtc_identity_auth_record</a>

**说明：** 身份认证操作记录表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | ID  |
|  2   | tenantId |   int   | 10 |   0    |    N     |  N   |       | 租户Id  |
|  3   | flowId |   int   | 10 |   0    |    N     |  N   |       | 对应rtc_business_flow表的主键  |
|  4   | action |   varchar   | 50 |   0    |    N     |  N   |       | 操作  |
|  5   | result |   varchar   | 500 |   0    |    Y     |  N   |       | 处理结果  |
|  6   | url |   varchar   | 500 |   0    |    Y     |  N   |       |   |
|  7   | createDatetime |   datetime   | 19 |   0    |    Y     |  N   |       | 创建时间  |

## 信息推送记录表(rtc_info_push_record)
**表名：** <a id="rtc_info_push_record">rtc_info_push_record</a>

**说明：** 信息推送记录表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | ID  |
|  2   | tenantId |   int   | 10 |   0    |    N     |  N   |       | 租户Id  |
|  3   | flowId |   int   | 10 |   0    |    N     |  N   |       | 对应rtc_business_flow表的主键  |
|  4   | action |   varchar   | 50 |   0    |    N     |  N   |       | 操作  |
|  5   | ext |   text   | 65535 |   0    |    Y     |  N   |       | 推送信息  |
|  6   | url |   varchar   | 500 |   0    |    Y     |  N   |       | 附件地址  |
|  7   | createDatetime |   datetime   | 19 |   0    |    Y     |  N   |       | 创建时间  |

## 插件配置表(rtc_plugin_config)
**表名：** <a id="rtc_plugin_config">rtc_plugin_config</a>

**说明：** 插件配置表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | configId |   varchar   | 36 |   0    |    N     |  Y   |       | Id  |
|  2   | tenantId |   int   | 10 |   0    |    N     |  N   |       | 租户Id  |
|  3   | configName |   varchar   | 150 |   0    |    N     |  N   |       | 插件名称  |
|  4   | configJson |   longtext   | 2147483647 |   0    |    N     |  N   |       | 插件配置  |
|  5   | isDefault |   tinyint   | 4 |   0    |    N     |  N   |   0    | 默认配置  |
|  6   | type |   enum   | 3 |   0    |    N     |  N   |       | 插件类型  |
|  7   | associationId |   int   | 10 |   0    |    N     |  N   |       | 关联Id  |
|  8   | createDatetime |   datetime   | 19 |   0    |    N     |  N   |   CURRENT_TIMESTAMP    | 创建时间  |
|  9   | lastUpdateDateTime |   datetime   | 19 |   0    |    Y     |  N   |       | 更新时间  |

## 技能组调度优先级表(rtc_queue_schedule_grade)
**表名：** <a id="rtc_queue_schedule_grade">rtc_queue_schedule_grade</a>

**说明：** 技能组调度优先级表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       |   |
|  2   | tenantId |   int   | 10 |   0    |    N     |  N   |       | 租户ID  |
|  3   | queueId |   bigint   | 20 |   0    |    N     |  N   |       | 技能组Id  |
|  4   | grade |   int   | 10 |   0    |    N     |  N   |       | 级别，值越低优先级越高  |
|  5   | createdAt |   datetime   | 19 |   0    |    Y     |  N   |       | 创建时间  |
|  6   | updatedAt |   datetime   | 19 |   0    |    Y     |  N   |       | 修改时间  |

## 路由规则(rtc_routing_rule)
**表名：** <a id="rtc_routing_rule">rtc_routing_rule</a>

**说明：** 路由规则

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | ID  |
|  2   | ruleType |   varchar   | 40 |   0    |    Y     |  N   |       | 路由规则的种类  |
|  3   | routingMark |   varchar   | 40 |   0    |    Y     |  N   |       | 具体的分流标识例:webapp咨询关联的id  |
|  4   | routingMarkType |   varchar   | 40 |   0    |    Y     |  N   |       | 具体的分流标识的类型例:关联的类型微信小程序微信  |
|  5   | tenantId |   int   | 10 |   0    |    Y     |  N   |       | 租户id  |
|  6   | timeScheduleId |   int   | 10 |   0    |    Y     |  N   |       | 时间计划id  |
|  7   | dutyType |   varchar   | 45 |   0    |    Y     |  N   |       | 绑定是全天还是分上下班  |
|  8   | bindId |   int   | 10 |   0    |    Y     |  N   |       | 上班绑定的技能组  |
|  9   | offWorkBindId |   int   | 10 |   0    |    Y     |  N   |       | 下班绑定的技能组  |
|  10   | createDatetime |   datetime   | 19 |   0    |    N     |  N   |   CURRENT_TIMESTAMP    | 创建时间  |
|  11   | lastUpdateDateTime |   datetime   | 19 |   0    |    Y     |  N   |       | 更新时间  |

## 独立视频客服会话表(rtc_session)
**表名：** <a id="rtc_session">rtc_session</a>

**说明：** 独立视频客服会话表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | rtc_session_id |   varchar   | 36 |   0    |    N     |  Y   |       | 主键  |
|  2   | tenant_id |   int   | 10 |   0    |    N     |  N   |       | 租户id  |
|  3   | tech_channel_id |   int   | 10 |   0    |    Y     |  N   |       | 关联id  |
|  4   | visitor_user_id |   char   | 36 |   0    |    Y     |  N   |       | 访客id  |
|  5   | visitor_user_nickname |   varchar   | 200 |   0    |    Y     |  N   |       | 访客昵称  |
|  6   | visitor_user_name |   varchar   | 255 |   0    |    Y     |  N   |       | 访客名称（前端展示用的访客id）  |
|  7   | queue_id |   int   | 10 |   0    |    Y     |  N   |       | 调度队列ID  |
|  8   | state |   enum   | 10 |   0    |    Y     |  N   |   Wait    | 会话状态  |
|  9   | agent_user_id |   char   | 36 |   0    |    Y     |  N   |       | 坐席id  |
|  10   | start_datetime |   datetime   | 19 |   0    |    Y     |  N   |       | 接起时间  |
|  11   | stop_datetime |   datetime   | 19 |   0    |    Y     |  N   |       | 挂断时间  |
|  12   | create_datetime |   datetime   | 19 |   0    |    Y     |  N   |       | 创建时间  |
|  13   | agent_user_nice_name |   varchar   | 45 |   0    |    Y     |  N   |       | 坐席昵称  |
|  14   | tech_channel_type |   varchar   | 45 |   0    |    Y     |  N   |       | channel类型  |
|  15   | tech_channel_name |   varchar   | 280 |   0    |    Y     |  N   |       | 关联  |
|  16   | origin_type |   varchar   | 20 |   0    |    Y     |  N   |       | 来源系统  |
|  17   | call_type |   tinyint   | 4 |   0    |    Y     |  N   |   0    | 0-呼入1-呼出  |
|  18   | multi_call |   tinyint   | 4 |   0    |    Y     |  N   |   0    | 是否多方通话0-否1-是  |
|  19   | extra |   varchar   | 1000 |   0    |    Y     |  N   |       | 附加信息，例如预约任务的任务id等  |
|  20   | source_type |   varchar   | 50 |   0    |    Y     |  N   |       | 视频通话来源类型标识  |
|  21   | priority |   int   | 10 |   0    |    Y     |  N   |       | 调度优先级,值越小优先级越高  |
|  22   | vip |   varchar   | 50 |   0    |    Y     |  N   |       |   |

## 通话成员列表(rtc_session_members)
**表名：** <a id="rtc_session_members">rtc_session_members</a>

**说明：** 通话成员列表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 主键ID  |
|  2   | rtc_session_id |   varchar   | 36 |   0    |    N     |  N   |       | 通话ID  |
|  3   | tenant_id |   int   | 10 |   0    |    N     |  N   |       | 租户id  |
|  4   | member_id |   varchar   | 36 |   0    |    N     |  N   |       | 成员id  |
|  5   | member_type |   varchar   | 50 |   0    |    N     |  N   |       | 成员类型，agent,visitor,visitor_external  |
|  6   | member_name |   varchar   | 200 |   0    |    Y     |  N   |       | 成员标识名  |
|  7   | join_call |   tinyint   | 4 |   0    |    N     |  N   |       | 是否还在房间内  |
|  8   | join_datetime |   datetime   | 19 |   0    |    Y     |  N   |       | 加入时间  |
|  9   | leave_datetime |   datetime   | 19 |   0    |    Y     |  N   |       | 退出时间  |

## 话后小结记录表(rtc_session_summary_record)
**表名：** <a id="rtc_session_summary_record">rtc_session_summary_record</a>

**说明：** 话后小结记录表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | id  |
|  2   | tenantId |   int   | 10 |   0    |    Y     |  N   |       | 租户id  |
|  3   | servicesessionId |   varchar   | 36 |   0    |    Y     |  N   |       | 会话id  |
|  4   | comment |   varchar   | 1000 |   0    |    Y     |  N   |       | 评论  |
|  5   | summary |   text   | 65535 |   0    |    Y     |  N   |       | 概要  |
|  6   | actor |   varchar   | 1000 |   0    |    Y     |  N   |       | 操作人基本信息  |
|  7   | createDateTime |   datetime   | 19 |   0    |    N     |  N   |       | 创建时间  |

## 视频灰度表(rtc_tenant_options)
**表名：** <a id="rtc_tenant_options">rtc_tenant_options</a>

**说明：** 视频灰度表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | optionId |   bigint   | 20 |   0    |    N     |  Y   |       |   |
|  2   | tenantId |   int   | 10 |   0    |    Y     |  N   |       |   |
|  3   | optionName |   varchar   | 64 |   0    |    N     |  N   |       |   |
|  4   | optionValue |   longtext   | 2147483647 |   0    |    N     |  N   |       |   |
|  5   | createDateTime |   datetime   | 19 |   0    |    N     |  N   |       |   |
|  6   | lastUpdateDateTime |   datetime   | 19 |   0    |    N     |  N   |       |   |

## 未接通任务表(rtc_unanswered_task)
**表名：** <a id="rtc_unanswered_task">rtc_unanswered_task</a>

**说明：** 未接通任务表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | id  |
|  2   | tenantId |   int   | 10 |   0    |    Y     |  N   |       | 租户id  |
|  3   | rtcSessionId |   varchar   | 36 |   0    |    Y     |  N   |       | 视频通话id  |
|  4   | visitorUserId |   varchar   | 36 |   0    |    Y     |  N   |       | 访客id  |
|  5   | visitorNickname |   varchar   | 128 |   0    |    Y     |  N   |       | 访客昵称  |
|  6   | queueId |   int   | 10 |   0    |    Y     |  N   |       | 技能组id  |
|  7   | queueName |   varchar   | 48 |   0    |    Y     |  N   |       | 技能组名称  |
|  8   | agentUserId |   varchar   | 36 |   0    |    Y     |  N   |       | 坐席id  |
|  9   | agentNickname |   varchar   | 48 |   0    |    Y     |  N   |       | 坐席昵称  |
|  10   | originType |   varchar   | 20 |   0    |    Y     |  N   |       | 渠道  |
|  11   | techChannelName |   varchar   | 48 |   0    |    Y     |  N   |       | 插件  |
|  12   | createDateTime |   datetime   | 19 |   0    |    Y     |  N   |       | 视频发起时间  |
|  13   | stopDatetime |   datetime   | 19 |   0    |    Y     |  N   |       | 访客挂断时间  |
|  14   | relatedSessionId |   varchar   | 36 |   0    |    Y     |  N   |       | 询前引导会话id  |
|  15   | repetitions |   smallint   | 6 |   0    |    Y     |  N   |       | 重复次数  |
|  16   | recall |   tinyint   | 4 |   0    |    Y     |  N   |   0    | 是否回呼（0：否；1：回呼）  |
|  17   | callbackSessionId |   varchar   | 36 |   0    |    Y     |  N   |       | 坐席回呼视频通话id  |
|  18   | executeAgentUserId |   varchar   | 36 |   0    |    Y     |  N   |       | 任务执行坐席id  |
|  19   | executeAgentNickname |   varchar   | 48 |   0    |    Y     |  N   |       | 任务执行坐席昵称  |
|  20   | executeDatetime |   datetime   | 19 |   0    |    Y     |  N   |       | 任务执行时间  |
|  21   | taskStartTime |   datetime   | 19 |   0    |    Y     |  N   |       | 任务开始时间  |
|  22   | taskStopTime |   datetime   | 19 |   0    |    Y     |  N   |       | 任务结束时间  |
|  23   | status |   tinyint   | 4 |   0    |    Y     |  N   |   0    | 任务执行状态（0：未执行；1：已执行）  |
|  24   | result |   tinyint   | 4 |   0    |    Y     |  N   |   0    | 任务结果（0：未完成；1：已完成）  |
|  25   | comment |   text   | 65535 |   0    |    Y     |  N   |       | 备注  |
|  26   | channelId |   int   | 10 |   0    |    Y     |  N   |       |   |
|  27   | visitorUserName |   varchar   | 128 |   0    |    Y     |  N   |       |   |

## 访客行为记录表(rtc_visitor_action_log)
**表名：** <a id="rtc_visitor_action_log">rtc_visitor_action_log</a>

**说明：** 访客行为记录表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   bigint   | 20 |   0    |    N     |  Y   |       | 自动生成主键ID  |
|  2   | tenantId |   int   | 10 |   0    |    N     |  N   |       | 租户ID  |
|  3   | visitorId |   char   | 36 |   0    |    N     |  N   |       | 访客ID  |
|  4   | sessionServiceId |   char   | 36 |   0    |    N     |  N   |       | 会话ID  |
|  5   | operation |   varchar   | 10 |   0    |    N     |  N   |       | 访客动作  |
|  6   | createAt |   datetime   | 19 |   0    |    Y     |  N   |       | 创建时间  |

## 外部访客信息表(rtc_visitor_external)
**表名：** <a id="rtc_visitor_external">rtc_visitor_external</a>

**说明：** 外部访客信息表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | userId |   char   | 36 |   0    |    N     |  Y   |       | 主键ID  |
|  2   | tenantId |   int   | 10 |   0    |    N     |  N   |       | 租户ID  |
|  3   | nickname |   varchar   | 255 |   0    |    Y     |  N   |       | 昵称  |
|  4   | username |   varchar   | 255 |   0    |    N     |  N   |       | 访客ID  |
|  5   | email |   varchar   | 255 |   0    |    Y     |  N   |       | 电子邮箱  |
|  6   | phone |   varchar   | 255 |   0    |    Y     |  N   |       | 手机号  |
|  7   | createDateTime |   datetime   | 19 |   0    |    N     |  N   |       | 创建时间  |
|  8   | lastUpdateDateTime |   datetime   | 19 |   0    |    N     |  N   |       | 更新时间  |
