# carp

鲤鱼

眼似真珠鳞似金，时时动浪出还沈。

河中得上龙门去，不叹江湖岁月深。

## 开发快速启动
1. application.yml配置好数据库链接(mysql)
2. 启动`AdminApplication` 或 `WebappApplication`, 同时设置
program arguments `--flyway.enabled=true --spring.profiles.active=dev --debug`.
（启动时flyway会自动初始化表、测试数据)
3. 启动后访问 [管理平台](http://localhost:8081/admin) 


## 模块介绍
>carp-parent
定义公共依赖，理论上所有的子模块都需要使用parent作为父模块


>carp-base
功能配置等功能

>carp-core
服务接口、实现方法

>carp-admin
管理端

>carp-front
PC端

>carp-mobile
mobile端

>carp commerce
封装基本的commerce操作

>carp-webapp
包含全部

## 其他
master 为开发分支，不固定更新
发布时采用release分支
更多模块详情，请查看对应模块README描述

