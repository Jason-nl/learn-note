create database day06_119;
use day06_119;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'zhangsan', '1234');
INSERT INTO `user` VALUES ('2', 'lisi', '1234');
INSERT INTO `user` VALUES ('3', 'wangwu', '1234');
INSERT INTO `user` VALUES ('4', 'zhaoliu', '1234');