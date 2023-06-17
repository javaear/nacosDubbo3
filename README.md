# nacosDubbo3
Dubbo3.2 重新起航，整合nacos SpringBoot2.6.8 ， 文档后期待整理

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


3、分别建了2个库 seata-user   seata-goods
CREATE TABLE `user_tbl` (
	`count` INT(11) NULL DEFAULT NULL,
	`id` VARCHAR(50) NOT NULL COLLATE 'utf8_general_ci',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
CREATE TABLE `goods_tbl` (
	`id` VARCHAR(50) NOT NULL COLLATE 'utf8_general_ci',
	`count` INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`id`) USING BTREE
)
COMMENT='goods_tbl'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

每个库放入undo_log 表
CREATE TABLE `undo_log` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`branch_id` BIGINT(20) NOT NULL,
	`xid` VARCHAR(100) NOT NULL COLLATE 'utf8_general_ci',
	`context` VARCHAR(128) NOT NULL COLLATE 'utf8_general_ci',
	`rollback_info` LONGBLOB NOT NULL,
	`log_status` INT(11) NOT NULL,
	`log_created` DATETIME NOT NULL,
	`log_modified` DATETIME NOT NULL,
	PRIMARY KEY (`id`) USING BTREE,
	UNIQUE INDEX `ux_undo_log` (`xid`, `branch_id`) USING BTREE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

4、测试 启动 jxx-order  调用 结果Y 分布式事物成功(2个库分别更新 user_tbl  ，更新goods_tbl)
http://localhost:8006/order/w?w=1

2023-06-15 23:37:26.359  INFO 15936 --- [nio-8006-exec-2] i.seata.tm.api.DefaultGlobalTransaction  : Begin new global transaction [192.168.1.101:8091:412754101312835584]
2023-06-15 23:37:27.232  INFO 15936 --- [nio-8006-exec-2] org.jxx.dubbo.order.OrderServiceImpl     : ===purchase=========================
2023-06-15 23:37:29.684  INFO 15936 --- [nio-8006-exec-2] i.seata.tm.api.DefaultGlobalTransaction  : [192.168.1.101:8091:412754101312835584] commit status: Committed
2023-06-15 23:37:29.684  INFO 15936 --- [nio-8006-exec-2] org.jxx.dubbo.OrderViewController        : =============result:======

###########################################


整合spring security  jxx-security

###########################################