/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 15/03/2021 17:32:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for juncheng_security_admin
-- ----------------------------
DROP TABLE IF EXISTS `juncheng_security_admin`;
CREATE TABLE `juncheng_security_admin` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `client_name` varchar(100) NOT NULL,
  `user_name` varchar(32) NOT NULL COMMENT '用户名',
  `real_name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `photo_uri` text COMMENT '头像',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `gender` smallint(1) DEFAULT '0' COMMENT '性别',
  `create_user` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `last_modify_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '最后修改时间',
  `last_modify_user` varchar(32) NOT NULL COMMENT '最后修改人',
  `create_ip_addr` varchar(32) DEFAULT NULL COMMENT '注册ip',
  `salt` varchar(11) NOT NULL COMMENT '密码加密字符串',
  `delete_status` smallint(1) NOT NULL DEFAULT '0' COMMENT '删除状态',
  `disable_status` smallint(1) NOT NULL DEFAULT '0' COMMENT '禁用状态',
  `founder` smallint(1) NOT NULL DEFAULT '0' COMMENT '是否是总管理员',
  `login_error_count` int(4) DEFAULT '0' COMMENT '登录错误次数',
  `login_lock_status` smallint(1) DEFAULT '0' COMMENT '锁定状态',
  `login_lock_start_time` bigint(13) DEFAULT '0' COMMENT '锁定开始时间',
  `login_lock_end_time` bigint(13) DEFAULT '0' COMMENT '锁定结束时间',
  `session_token` varchar(255) DEFAULT NULL COMMENT 'session标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员表 管理员表';

-- ----------------------------
-- Table structure for juncheng_security_admin_log
-- ----------------------------
DROP TABLE IF EXISTS `juncheng_security_admin_log`;
CREATE TABLE `juncheng_security_admin_log` (
  `id` varchar(32) NOT NULL,
  `client_name` varchar(100) NOT NULL,
  `type` smallint(2) NOT NULL COMMENT '类型',
  `action` varchar(100) NOT NULL COMMENT '动作',
  `admin_id` varchar(32) DEFAULT NULL COMMENT '管理员id',
  `content` text NOT NULL COMMENT '内容',
  `create_user` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `last_modify_user` varchar(32) NOT NULL COMMENT '最后修改人',
  `last_modify_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员登录日志';

-- ----------------------------
-- Table structure for juncheng_security_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `juncheng_security_admin_role`;
CREATE TABLE `juncheng_security_admin_role` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `admin_id` varchar(32) NOT NULL COMMENT '管理员id',
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `create_user` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `last_modify_user` varchar(32) NOT NULL COMMENT '最后更新人',
  `last_modify_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员角色表 ';

-- ----------------------------
-- Table structure for juncheng_security_role
-- ----------------------------
DROP TABLE IF EXISTS `juncheng_security_role`;
CREATE TABLE `juncheng_security_role` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `client_name` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL COMMENT '名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_user` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `last_modify_user` varchar(32) NOT NULL COMMENT '最后更新人',
  `last_modify_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表 ';

-- ----------------------------
-- Table structure for juncheng_security_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `juncheng_security_role_permission`;
CREATE TABLE `juncheng_security_role_permission` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `group_id` varchar(32) NOT NULL COMMENT '权限组id',
  `group_name` varchar(32) NOT NULL COMMENT '权限组',
  `permission_name` varchar(32) NOT NULL COMMENT '权限名称',
  `permission_code` varchar(100) NOT NULL COMMENT '权限码',
  `create_user` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `last_modify_user` varchar(32) NOT NULL COMMENT '最后更新人',
  `last_modify_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表 ';

-- ----------------------------
-- Table structure for juncheng_security_setting
-- ----------------------------
DROP TABLE IF EXISTS `juncheng_security_setting`;
CREATE TABLE `juncheng_security_setting` (
  `id` varchar(32) NOT NULL,
  `client_name` varchar(100) DEFAULT NULL,
  `login_log_status` smallint(1) DEFAULT '0' COMMENT '是否记录登录日志',
  `login_error_count` int(4) DEFAULT '0' COMMENT '登录错误锁定次数',
  `login_error_time` int(4) DEFAULT '0' COMMENT '登录错误锁定时间(单位分钟)',
  `login_error_status` smallint(1) DEFAULT '0' COMMENT '是否开启错误次数',
  `login_not_ip_status` smallint(1) DEFAULT '0' COMMENT '是否开启异地登录',
  `login_not_ip_notice` smallint(1) DEFAULT '0' COMMENT '异地登录是否提醒',
  `last_modify_time` bigint(13) DEFAULT '0' COMMENT '最后修改时间',
  `last_modify_user` varchar(32) DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='安全设置';

SET FOREIGN_KEY_CHECKS = 1;
