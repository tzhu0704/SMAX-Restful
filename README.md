# SMAX-Restful

SMAX Restful Webhook 提供相关SMAX工单等实体的Restful访问，目前支持

- 与AlertManager集成，提供对应的Webhook，实现基于报警事件的自动工单创建（暂不支持工单关闭）

- 支持基于工单ID的工单详细信息查询


## 启动说明

- 需要将编译后的SMAX-restful.jar与startwebhook.sh放入统一目录

- 运行startwebhook.sh

- 执行 com.mf.smax.Application.main 方法

### 开单效果
![输入图片说明](https://s7.51cto.com/images/202110/d5eba456201f5b842e0970944a3b47bd38e3fd.png?x-oss-process=image/watermark,size_14,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_10)

## 项目结构


```
├─java
│  └─com
│      └─mf
│          ├─integration
│          │      Constants.java --SMAX常量说明
│          │      HttpUtil.java
│          │      MyX509TrustManager.java
│          │      SMACall.java   --SMAX交互场景实现
│          │
│          └─smax
│              │  Application.java
│              │
│              ├─common
│              │  └─api
│              │          SwaggerConfig.java
│              │
│              ├─entity
│              │      AlertFromAlertManager.java
│              │      QueryTicket.java
│              │      Ticket.java
│              │
│              ├─test
│              │      servicetest.java
│              │
│              └─web
│                      ticketController.java
│
├─resources
│      application.properties  --SMAX实例基本配置
│      application.yml
│      logback.xml
│
└─webapp
    │  index.jsp
    │
    └─WEB-INF
            web.xml
```






## Restful 接口说明

- 支持Swagger2,查看接口说明方法，如下：
http://XX.XX.XX.XX:8000/SMAX-Restful/swagger-ui.html

具体试用说明参见51CTO Blog：
https://blog.51cto.com/gnzhutan/4269716

欢迎关注： http://gnzhutan.blog.51cto.com

