/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50727
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2020-07-16 15:35:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE IF NOT EXISTS `permission` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `permission` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', 'user:query', '查询自己');
INSERT INTO `permission` VALUES ('2', 'user:add', '添加');
INSERT INTO `permission` VALUES ('3', 'user:delete', '删除');
INSERT INTO `permission` VALUES ('4', 'user:update', '修改');
INSERT INTO `permission` VALUES ('5', 'user:list', '列举所有');

-- ----------------------------
-- Table structure for permission_resource
-- ----------------------------
DROP TABLE IF EXISTS `permission_resource`;
CREATE TABLE IF NOT EXISTS `permission_resource` (
  `permission_id` int(11) NOT NULL,
  `resource_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission_resource
-- ----------------------------
INSERT INTO `permission_resource` VALUES ('1', '1');
INSERT INTO `permission_resource` VALUES ('1', '2');
INSERT INTO `permission_resource` VALUES ('2', '1');
INSERT INTO `permission_resource` VALUES ('2', '2');
INSERT INTO `permission_resource` VALUES ('2', '3');
INSERT INTO `permission_resource` VALUES ('2', '4');
INSERT INTO `permission_resource` VALUES ('3', '1');
INSERT INTO `permission_resource` VALUES ('3', '2');
INSERT INTO `permission_resource` VALUES ('3', '3');
INSERT INTO `permission_resource` VALUES ('3', '4');

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE IF NOT EXISTS `resource` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `resource` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('1', '/login', null);
INSERT INTO `resource` VALUES ('2', '/register', null);
INSERT INTO `resource` VALUES ('3', '/user', null);
INSERT INTO `resource` VALUES ('4', '/admin', null);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'TEST');
INSERT INTO `role` VALUES ('2', 'USER');
INSERT INTO `role` VALUES ('3', 'ADMIN');
INSERT INTO `role` VALUES ('4', 'ROOT');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE IF NOT EXISTS `role_permission` (
  `role_id` bigint(11) NOT NULL,
  `permission_id` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1');
INSERT INTO `role_permission` VALUES ('2', '1');
INSERT INTO `role_permission` VALUES ('2', '2');
INSERT INTO `role_permission` VALUES ('3', '1');
INSERT INTO `role_permission` VALUES ('3', '2');
INSERT INTO `role_permission` VALUES ('3', '3');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'test', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `user` VALUES ('2', 'user', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `user` VALUES ('3', 'admin', 'e10adc3949ba59abbe56e057f20f883e');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` bigint(11) NOT NULL,
  `role_id` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('2', '1');
INSERT INTO `user_role` VALUES ('2', '2');
