# nacosDubbo3
Dubbo3.2 重新起航，整合nacos SpringBoot2.6.8

###########################################

1、下载nacos2.1.2 位于 soft目录  或从官方下载：
https://github.com/alibaba/nacos/releases/tag/2.1.2

https://github.com/alibaba/nacos/archive/refs/tags/2.1.2.zip

https://github.com/alibaba/nacos/releases/download/2.1.2/nacos-server-2.1.2.tar.gz

解压nacos,bin目录下使用startup.cmd -m standalone启动Nacos服务，
账号密码为nacos:nacos,
访问地址：http://localhost:8848/nacos/

2、用这个大多是想替换feign,用rpc 方式调用服务。

###########################################


Dubbo3.2 重新起航，整合seata  jxx-seata

###########################################
1、 下载    db 本例用DB，
执行 数据库脚本  
seata\script\server\db\mysql.sql 

2、seata1.4.0  db模式，nacos注册配置中心
调试了 2天 最终用了1.4.0 ,seata软件放到了soft 目录

run app 出现这个表示成功
2023-06-15 23:12:26.251  INFO 8100 --- [eoutChecker_1_1] i.s.c.rpc.netty.TmNettyRemotingClient    : register TM success. client version:1.4.0, server version:1.4.0,channel:[id: 0x86c4a042, L:/192.168.1.101:58824 - R:/192.168.1.101:8091]
2023-06-15 23:12:26.252  INFO 8100 --- [eoutChecker_1_1] i.s.core.rpc.netty.NettyPoolableFactory  : register success, cost 15 ms, version:1.4.0,role:TMROLE,channel:[id: 0x86c4a042, L:/192.168.1.101:58824 - R:/192.168.1.101:8091]


