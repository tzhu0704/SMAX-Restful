# SMAX-Restful

SMAX Restful Webhook 提供相关SMAX工单等实体的Restful访问，目前支持

- 与AlertManager集成，提供对应的Webhook，实现基于报警事件的自动工单创建（暂不支持工单关闭）

- 支持基于工单ID的工单详细信息查询


## 启动说明

- 需要将编译后的SMAX-restful.jar与startwebhook.sh放入统一目录

- 运行startwebhook.sh

- 执行 com.mf.smax.Application.main 方法

## Restful 接口说明

- 支持Swagger2,查看接口说明方法，如下：
http://XX.XX.XX.XX:8000/SMAX-Restful/swagger-ui.html

具体试用说明参见51CTO Blog：
https://blog.51cto.com/gnzhutan/4269716

欢迎关注： http://gnzhutan.blog.51cto.com

