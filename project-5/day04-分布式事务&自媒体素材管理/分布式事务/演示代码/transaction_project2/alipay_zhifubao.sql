SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- 支付宝中的账户表
-- ----------------------------
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account` (
  `id` int(11) NOT NULL,
  `amount` decimal(11,3) DEFAULT NULL,
  `frozen` decimal(11,3) DEFAULT NULL,
  `username` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `t_account` VALUES ('1', '900.000', '0.000', 'xiaoming');

-- ----------------------------
-- 支付宝库中的本地消息表
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message` (
  `msg_id` varchar(32) NOT NULL DEFAULT '',
  `msg_status` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`msg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `t_message` VALUES ('b82c8735f1a94ea0a16440b80624e175', '1', 'xiaoming', '2020-07-24 11:38:04', '2020-07-24 11:38:04', '100');
-- ----------------------------
-- 支付宝库中的回滚日志表(Seata使用)
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) NOT NULL,
  `context` varchar(128) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
