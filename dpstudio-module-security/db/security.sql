/*
 Navicat Premium Data Transfer

 Source Server         : localhost_2020
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : juncheng_swj_lw

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 23/01/2021 22:37:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for juncheng_security_admin
-- ----------------------------
DROP TABLE IF EXISTS `juncheng_security_admin`;
CREATE TABLE `juncheng_security_admin` (
  `id` varchar(32) NOT NULL COMMENT 'id',
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员表 管理员表';

-- ----------------------------
-- Records of juncheng_security_admin
-- ----------------------------
BEGIN;
INSERT INTO `juncheng_security_admin` VALUES ('9eaae11bac4b4948ab60ab160765aca8', 'demo', NULL, NULL, 'e6b5862832ad8f7071bea5aa8f8356a8', NULL, 0, 'admin', 1607072633921, 1607072633921, 'admin', NULL, 'agKTgd', 0, 0, 0, 0, 0, 0, 0, NULL);
INSERT INTO `juncheng_security_admin` VALUES ('admin', 'admin', '管理员', 'http://localhost:8080/uploads/resources/image/1148e13f1a752f0359f4921c922d0e09', '81f83960e275bb4b6087dedce321f2ef', '13942046422', 1, 'admin', 0, 1608775310897, 'admin', NULL, 'HB34p6', 0, 0, 1, 0, 0, 0, 0, '76C0CDB60ECEF59DB6F65BFB6C7C11D8');
COMMIT;

-- ----------------------------
-- Table structure for juncheng_security_admin_log
-- ----------------------------
DROP TABLE IF EXISTS `juncheng_security_admin_log`;
CREATE TABLE `juncheng_security_admin_log` (
  `id` varchar(32) NOT NULL,
  `type` smallint(2) NOT NULL COMMENT '类型',
  `action` varchar(100) NOT NULL COMMENT '动作',
  `admin_id` varchar(32) DEFAULT NULL COMMENT '管理员id',
  `content` text NOT NULL COMMENT '内容',
  `create_user` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `last_modify_user` varchar(32) NOT NULL COMMENT '最后修改人',
  `last_modify_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员登录日志';

-- ----------------------------
-- Records of juncheng_security_admin_log
-- ----------------------------
BEGIN;
INSERT INTO `juncheng_security_admin_log` VALUES ('006eda69b4ca47849c798f8f5bf7a410', 0, '登录', 'admin', '管理员在2020-11-10 17:06:00登录了系统，IP是192.168.31.215', 'admin', 1604999160691, 'admin', 1604999160691);
INSERT INTO `juncheng_security_admin_log` VALUES ('009c3b5d1eda4b8d8b67fa317761db94', 0, '登录', 'admin', '管理员在2020-11-12 13:41:04登录了系统，IP是192.168.31.215', 'admin', 1605159664970, 'admin', 1605159664970);
INSERT INTO `juncheng_security_admin_log` VALUES ('0120875f94e843bdae21efdc1a411c3f', 0, '登录', 'admin', '管理员在2021-01-11 17:35:00登录了系统，IP是113.227.196.144', '1', 1610357700683, '1', 1610357700683);
INSERT INTO `juncheng_security_admin_log` VALUES ('02b511c656e54640b4c34322f21887b1', 0, '登录', 'admin', '管理员在2020-11-12 10:46:22登录了系统，IP是192.168.31.215', 'admin', 1605149182860, 'admin', 1605149182860);
INSERT INTO `juncheng_security_admin_log` VALUES ('02fdb11ba44f409387f0fd519ff23fad', 0, '登录', 'admin', '管理员在2020-11-23 16:00:11登录了系统，IP是192.168.31.215', 'admin', 1606118411413, 'admin', 1606118411413);
INSERT INTO `juncheng_security_admin_log` VALUES ('0419b5dc2fc94dd98dce77b6d3c14aaa', 0, '登录', 'admin', '管理员在2020-10-21 15:08:47登录了系统，IP是192.168.31.221', 'admin', 1603264127251, 'admin', 1603264127251);
INSERT INTO `juncheng_security_admin_log` VALUES ('042494eb7d7b4a3c883f439fb66dc34c', 0, '登录', 'admin', '管理员在2020-11-09 11:46:23登录了系统，IP是127.0.0.1', 'admin', 1604893583353, 'admin', 1604893583353);
INSERT INTO `juncheng_security_admin_log` VALUES ('052c52d2607c49b98408c30f96e302e7', 0, '登录', 'admin', '管理员在2020-12-24 10:01:41登录了系统，IP是127.0.0.1', '1', 1608775301442, '1', 1608775301442);
INSERT INTO `juncheng_security_admin_log` VALUES ('06eb4afbe4a14f7fac5ad23a287e38ae', 0, '登录', 'admin', '管理员在2020-11-23 10:35:49登录了系统，IP是192.168.31.215', 'admin', 1606098949666, 'admin', 1606098949666);
INSERT INTO `juncheng_security_admin_log` VALUES ('073cd07eb30c46c5ae756c209c74623b', 0, '登录', 'admin', '管理员在2020-10-19 17:30:20登录了系统，IP是127.0.0.1', 'admin', 1603099820809, 'admin', 1603099820809);
INSERT INTO `juncheng_security_admin_log` VALUES ('09feb264606a4789b379cb85e57700f1', 0, '登录', '2a65ee158c334c09ae8becf5ca3f7e5c', '客服在2020-10-21 14:03:51登录了系统，IP是127.0.0.1', '2a65ee158c334c09ae8becf5ca3f7e5c', 1603260231236, '2a65ee158c334c09ae8becf5ca3f7e5c', 1603260231236);
INSERT INTO `juncheng_security_admin_log` VALUES ('0c64aa368b3b44ef92405b6f5e4bca6a', 0, '登录', 'admin', '管理员在2020-11-28 08:50:07登录了系统，IP是192.168.31.215', 'admin', 1606524607405, 'admin', 1606524607406);
INSERT INTO `juncheng_security_admin_log` VALUES ('0c87e34389454390a9ee2954ae536431', 0, '登录', 'admin', '管理员在2020-12-26 10:01:17登录了系统，IP是127.0.0.1', '1', 1608948077002, '1', 1608948077002);
INSERT INTO `juncheng_security_admin_log` VALUES ('0e56010f1bb94e4c96846f15d170be99', 0, '登录', 'admin', '管理员在2020-12-24 09:58:56登录了系统，IP是127.0.0.1', '1', 1608775136033, '1', 1608775136033);
INSERT INTO `juncheng_security_admin_log` VALUES ('0e8652b1310b471ab790c965280401be', 0, '登录', 'admin', '管理员在2020-12-14 13:09:50登录了系统，IP是127.0.0.1', '1', 1607922590181, '1', 1607922590181);
INSERT INTO `juncheng_security_admin_log` VALUES ('0ea346cf6643473c970dbaae30e0e037', 0, '登录', 'admin', '管理员在2020-11-11 14:06:00登录了系统，IP是192.168.31.215', 'admin', 1605074760534, 'admin', 1605074760534);
INSERT INTO `juncheng_security_admin_log` VALUES ('0ed1c5e65724435080c7a02fe0dcd522', 0, '登录', 'admin', '管理员在2020-11-11 09:10:36登录了系统，IP是192.168.31.215', 'admin', 1605057036943, 'admin', 1605057036943);
INSERT INTO `juncheng_security_admin_log` VALUES ('0ef2a43893a34d719a4a631ccf80da94', 0, '登录', 'admin', '管理员在2020-12-04 17:54:45登录了系统，IP是192.168.31.220', 'admin', 1607075685145, 'admin', 1607075685145);
INSERT INTO `juncheng_security_admin_log` VALUES ('102ca07bcad14085a291b78ca4995e22', 0, '登录', 'admin', '管理员在2020-12-24 10:06:00登录了系统，IP是127.0.0.1', '1', 1608775560843, '1', 1608775560843);
INSERT INTO `juncheng_security_admin_log` VALUES ('13b1431f642940f092cb522fd0b9940d', 0, '登录', 'admin', '管理员在2020-12-24 10:03:14登录了系统，IP是127.0.0.1', '1', 1608775394167, '1', 1608775394167);
INSERT INTO `juncheng_security_admin_log` VALUES ('1485ef0620384c0fb543b4548212fe49', 0, '登录', 'admin', '管理员在2020-12-24 09:52:56登录了系统，IP是127.0.0.1', '1', 1608774776670, '1', 1608774776670);
INSERT INTO `juncheng_security_admin_log` VALUES ('15d2fdba64bf461a96f340e2db8db4e7', 0, '登录', 'admin', '管理员在2020-12-15 10:35:07登录了系统，IP是192.168.31.220', '1', 1607999707182, '1', 1607999707182);
INSERT INTO `juncheng_security_admin_log` VALUES ('166818f083d74b2d9b1692977a3a3ef6', 0, '登录', 'admin', '管理员在2020-11-09 11:34:57登录了系统，IP是192.168.31.220', 'admin', 1604892897301, 'admin', 1604892897301);
INSERT INTO `juncheng_security_admin_log` VALUES ('16d459c4413f477bb7f8c5c0143c812f', 0, '登录', 'admin', '管理员在2020-10-19 09:50:24，IP是127.0.0.1登录了系统', 'admin', 1603072224320, 'admin', 1603072224320);
INSERT INTO `juncheng_security_admin_log` VALUES ('183ddd40cb0c40b68801d05fe7b7ccca', 0, '登录', 'admin', '管理员在2020-11-11 13:29:43登录了系统，IP是192.168.31.215', 'admin', 1605072583291, 'admin', 1605072583291);
INSERT INTO `juncheng_security_admin_log` VALUES ('18fdf67451fd4ee588522da3cd4a9806', 0, '登录', 'admin', '管理员在2020-10-19 16:21:26登录了系统，IP是127.0.0.1', 'admin', 1603095686107, 'admin', 1603095686107);
INSERT INTO `juncheng_security_admin_log` VALUES ('19c552203b6b4b19a3c2be1d86290ca8', 0, '登录', 'admin', '管理员在2020-10-19 17:23:52登录了系统，IP是192.168.31.220', 'admin', 1603099432922, 'admin', 1603099432922);
INSERT INTO `juncheng_security_admin_log` VALUES ('1a1a69d77927426292f420c8a605c8c6', 0, '登录', 'admin', '管理员在2020-11-11 14:39:40登录了系统，IP是192.168.31.215', 'admin', 1605076780863, 'admin', 1605076780863);
INSERT INTO `juncheng_security_admin_log` VALUES ('1b0b01a5cd8842f5939c90e5d09c13e4', 0, '登录', 'admin', '管理员在2020-12-04 17:13:59登录了系统，IP是192.168.31.220', 'admin', 1607073239558, 'admin', 1607073239558);
INSERT INTO `juncheng_security_admin_log` VALUES ('1bac1f221ac1447faef6f6bb60b3e393', 0, '登录', 'admin', '管理员在2020-12-04 17:52:45登录了系统，IP是192.168.31.220', '1', 1607075565511, '1', 1607075565511);
INSERT INTO `juncheng_security_admin_log` VALUES ('1ceb1265b5ae460cad3057b69d1badd1', 0, '登录', 'admin', '管理员在2020-12-04 16:40:40登录了系统，IP是192.168.31.220', '1', 1607071240273, '1', 1607071240273);
INSERT INTO `juncheng_security_admin_log` VALUES ('1ead529773fb4caab3689a2c5e4db262', 0, '登录', 'admin', '管理员在2020-10-21 15:05:16登录了系统，IP是192.168.31.221', 'admin', 1603263916725, 'admin', 1603263916725);
INSERT INTO `juncheng_security_admin_log` VALUES ('1ebaa4bddedc4cef92502ee451ba764b', 0, '登录', 'admin', '管理员在2020-10-19 17:30:34登录了系统，IP是127.0.0.1', 'admin', 1603099834667, 'admin', 1603099834667);
INSERT INTO `juncheng_security_admin_log` VALUES ('1ee3d696dfc44740b793df39a77df07a', 0, '登录', 'admin', '管理员在2020-10-19 15:52:31，IP是127.0.0.1登录了系统', 'admin', 1603093951852, 'admin', 1603093951852);
INSERT INTO `juncheng_security_admin_log` VALUES ('1f69395992ba491b8e72c80dce3b6196', 0, '登录', 'admin', '管理员在2020-11-28 18:09:15登录了系统，IP是192.168.31.215', 'admin', 1606558155331, 'admin', 1606558155331);
INSERT INTO `juncheng_security_admin_log` VALUES ('1f8d9291043a4ff6ba274daa757f17a0', 0, '登录', 'admin', '管理员在2020-12-16 12:47:33登录了系统，IP是127.0.0.1', '1', 1608094053661, '1', 1608094053661);
INSERT INTO `juncheng_security_admin_log` VALUES ('1fe7dca37ab945bc8e3a1d4ac083ed1e', 0, '登录', 'admin', '管理员在2020-11-11 13:20:21登录了系统，IP是192.168.31.215', 'admin', 1605072021786, 'admin', 1605072021786);
INSERT INTO `juncheng_security_admin_log` VALUES ('2043cf76e9504e90a3bda8988ed799a6', 0, '登录', 'admin', '管理员在2020-11-21 10:08:38登录了系统，IP是192.168.31.215', 'admin', 1605924518784, 'admin', 1605924518784);
INSERT INTO `juncheng_security_admin_log` VALUES ('2212035141e74566a0b4957ac8fe2cb7', 0, '登录', 'admin', '管理员在2020-12-08 16:37:32登录了系统，IP是192.168.31.220', '1', 1607416652323, '1', 1607416652323);
INSERT INTO `juncheng_security_admin_log` VALUES ('22e5fdf90ed844d7ada4bcc8796bcc56', 0, '登录', 'admin', '管理员在2020-11-24 15:42:27登录了系统，IP是192.168.31.215', 'admin', 1606203747289, 'admin', 1606203747289);
INSERT INTO `juncheng_security_admin_log` VALUES ('22fecd710d194c78ab69145b449df1ae', 0, '登录', 'admin', '管理员在2020-11-09 11:47:45登录了系统，IP是127.0.0.1', 'admin', 1604893665330, 'admin', 1604893665330);
INSERT INTO `juncheng_security_admin_log` VALUES ('23aba0d116f64b9e96ab0f37daa44858', 0, '登录', 'admin', '管理员在2020-12-23 16:47:51登录了系统，IP是127.0.0.1', '1', 1608713271967, '1', 1608713271967);
INSERT INTO `juncheng_security_admin_log` VALUES ('24bd9094c9514c9ea2744aee152b955b', 0, '登录', 'admin', '管理员在2020-10-21 14:01:14登录了系统，IP是127.0.0.1', 'admin', 1603260074472, 'admin', 1603260074472);
INSERT INTO `juncheng_security_admin_log` VALUES ('24f33bd04ff44e39835a70b6401a4d0e', 0, '登录', 'admin', '管理员在2020-11-24 13:41:46登录了系统，IP是192.168.31.215', 'admin', 1606196506536, 'admin', 1606196506536);
INSERT INTO `juncheng_security_admin_log` VALUES ('257c62a8aeb24cd1a154c6f7e7cd42dc', 0, '登录', 'admin', '管理员在2021-01-11 17:36:25登录了系统，IP是113.227.196.144', '1', 1610357785185, '1', 1610357785185);
INSERT INTO `juncheng_security_admin_log` VALUES ('2712b7e66da7434f9c9b49970c6834b3', 0, '登录', '9eaae11bac4b4948ab60ab160765aca8', 'demo在2020-12-04 17:05:20登录了系统，IP是192.168.31.220', 'admin', 1607072720357, 'admin', 1607072720357);
INSERT INTO `juncheng_security_admin_log` VALUES ('274f4b41b988484aaa0edc3a076836d9', 0, '登录', '2a65ee158c334c09ae8becf5ca3f7e5c', '客服在2020-10-21 14:58:45登录了系统，IP是127.0.0.1', '2a65ee158c334c09ae8becf5ca3f7e5c', 1603263525116, '2a65ee158c334c09ae8becf5ca3f7e5c', 1603263525116);
INSERT INTO `juncheng_security_admin_log` VALUES ('2790684a228f43c2addb3807b654ff88', 0, '登录', 'admin', '管理员在2020-11-11 18:38:32登录了系统，IP是192.168.31.215', 'admin', 1605091112834, 'admin', 1605091112834);
INSERT INTO `juncheng_security_admin_log` VALUES ('2888886da86641bba1419ed4970a4e26', 0, '登录', 'admin', '管理员在2020-12-14 15:38:29登录了系统，IP是127.0.0.1', '1', 1607931509036, '1', 1607931509036);
INSERT INTO `juncheng_security_admin_log` VALUES ('29afe35db33343febce1a4c1d81d8f59', 0, '登录', 'admin', '管理员在2020-10-19 17:27:00登录了系统，IP是127.0.0.1', 'admin', 1603099620152, 'admin', 1603099620152);
INSERT INTO `juncheng_security_admin_log` VALUES ('2b1ac60a2a86434c97f8231641eb2cdf', 0, '登录', 'admin', '管理员在2020-11-24 14:23:50登录了系统，IP是192.168.31.215', 'admin', 1606199030333, 'admin', 1606199030333);
INSERT INTO `juncheng_security_admin_log` VALUES ('2b5010160f4f4c9d9a785928d46ac007', 0, '登录', 'admin', '管理员在2020-11-24 09:43:58登录了系统，IP是192.168.31.215', 'admin', 1606182238634, 'admin', 1606182238634);
INSERT INTO `juncheng_security_admin_log` VALUES ('2d0a91a9fbe743d18e64b573cd9bad20', 0, '登录', 'admin', '管理员在2020-11-23 22:10:11登录了系统，IP是192.168.1.100', 'admin', 1606140611323, 'admin', 1606140611323);
INSERT INTO `juncheng_security_admin_log` VALUES ('2e6d60036e1f4dbe9be73c5483f1ac40', 0, '登录', 'admin', '管理员在2020-10-19 16:47:05登录了系统，IP是127.0.0.1', 'admin', 1603097225144, 'admin', 1603097225144);
INSERT INTO `juncheng_security_admin_log` VALUES ('2ec43a703e2b477db931e04f54ae1864', 0, '登录', 'admin', '管理员在2021-01-05 13:09:19登录了系统，IP是127.0.0.1', '1', 1609823359753, '1', 1609823359753);
INSERT INTO `juncheng_security_admin_log` VALUES ('2fb09b0a0d3443c2b8591f790fc082ac', 0, '登录', 'admin', '管理员在2021-01-11 17:35:26登录了系统，IP是113.227.196.144', '1', 1610357726379, '1', 1610357726379);
INSERT INTO `juncheng_security_admin_log` VALUES ('2fe403a3c138426fb49500738b429a5a', 0, '登录', 'admin', '管理员在2020-12-14 13:26:53登录了系统，IP是127.0.0.1', '1', 1607923613403, '1', 1607923613403);
INSERT INTO `juncheng_security_admin_log` VALUES ('3080ad0d139a423394930f1638a25d9f', 0, '登录', 'admin', '管理员在2020-11-23 16:45:02登录了系统，IP是192.168.31.215', 'admin', 1606121102106, 'admin', 1606121102106);
INSERT INTO `juncheng_security_admin_log` VALUES ('309ad233de994201a10dcfc44fc6f3ac', 0, '登录', 'admin', '管理员在2021-01-05 09:54:16登录了系统，IP是127.0.0.1', '1', 1609811656889, '1', 1609811656889);
INSERT INTO `juncheng_security_admin_log` VALUES ('31bf8b259cf54b68af5da4245bcfe9c8', 0, '登录', 'admin', '管理员在2020-11-11 09:05:14登录了系统，IP是192.168.31.215', 'admin', 1605056714362, 'admin', 1605056714362);
INSERT INTO `juncheng_security_admin_log` VALUES ('31cd81be8e504d55b916fbd1d4bd17d3', 0, '登录', 'admin', '管理员在2020-11-26 18:13:12登录了系统，IP是192.168.31.118', 'admin', 1606385592660, 'admin', 1606385592660);
INSERT INTO `juncheng_security_admin_log` VALUES ('31f3c6901efa4c7182fac2b57dc609cf', 0, '登录', 'admin', '管理员在2020-11-24 16:23:08登录了系统，IP是192.168.31.215', 'admin', 1606206188254, 'admin', 1606206188254);
INSERT INTO `juncheng_security_admin_log` VALUES ('324ee60d890541b79892f3d00af070b6', 0, '登录', 'admin', '管理员在2020-10-19 17:45:16登录了系统，IP是127.0.0.1', 'admin', 1603100716393, 'admin', 1603100716393);
INSERT INTO `juncheng_security_admin_log` VALUES ('32cf090a67094a92af180931d8ce67b6', 0, '登录', 'admin', '管理员在2020-11-11 14:02:47登录了系统，IP是192.168.31.215', 'admin', 1605074567886, 'admin', 1605074567886);
INSERT INTO `juncheng_security_admin_log` VALUES ('332b89c7da0e49f29910d826e34b080d', 0, '登录', 'admin', '管理员在2020-12-14 15:44:45登录了系统，IP是127.0.0.1', '1', 1607931885717, '1', 1607931885717);
INSERT INTO `juncheng_security_admin_log` VALUES ('342808c602774f3280ac89821126a01c', 0, '登录', 'admin', '管理员在2020-10-19 17:26:48登录了系统，IP是127.0.0.1', 'admin', 1603099608363, 'admin', 1603099608363);
INSERT INTO `juncheng_security_admin_log` VALUES ('3569d79f573c4fd88f4aaa89c35a350f', 0, '登录', 'admin', '管理员在2020-11-17 09:32:18登录了系统，IP是192.168.31.215', 'admin', 1605576738300, 'admin', 1605576738300);
INSERT INTO `juncheng_security_admin_log` VALUES ('370066cd0a3d45d8970680413724f357', 0, '登录', 'admin', '管理员在2020-11-12 16:08:38登录了系统，IP是192.168.31.215', 'admin', 1605168518341, 'admin', 1605168518341);
INSERT INTO `juncheng_security_admin_log` VALUES ('37d845db83be4814aa5f5658f88efbee', 0, '登录', 'admin', '管理员在2020-11-23 09:44:03登录了系统，IP是192.168.31.215', 'admin', 1606095843343, 'admin', 1606095843343);
INSERT INTO `juncheng_security_admin_log` VALUES ('3880b6feacd64e36a1eab1f964a207a6', 0, '登录', 'admin', '管理员在2020-10-19 15:44:54，IP是127.0.0.1登录了系统', 'admin', 1603093494247, 'admin', 1603093494247);
INSERT INTO `juncheng_security_admin_log` VALUES ('38a1db724276486f9316e39016291775', 0, '登录', 'admin', '管理员在2020-11-28 15:01:41登录了系统，IP是192.168.31.215', 'admin', 1606546901187, 'admin', 1606546901187);
INSERT INTO `juncheng_security_admin_log` VALUES ('3921120aee504802bb63332b8ed6c6b2', 0, '登录', 'admin', '管理员在2020-10-20 11:58:19登录了系统，IP是127.0.0.1', 'admin', 1603166299343, 'admin', 1603166299343);
INSERT INTO `juncheng_security_admin_log` VALUES ('3922f8b62545468491fa1f9216c848c9', 0, '登录', 'admin', '管理员在2020-11-24 14:07:20登录了系统，IP是192.168.31.215', 'admin', 1606198040995, 'admin', 1606198040995);
INSERT INTO `juncheng_security_admin_log` VALUES ('3a54bb9266d143afabd3a0f974331b7d', 0, '登录', 'admin', '管理员在2020-11-17 08:25:36登录了系统，IP是192.168.31.215', 'admin', 1605572736366, 'admin', 1605572736366);
INSERT INTO `juncheng_security_admin_log` VALUES ('3a9ad6c9f18940e082956a7232eca63c', 0, '登录', 'admin', '管理员在2020-11-12 13:50:36登录了系统，IP是192.168.31.215', 'admin', 1605160236197, 'admin', 1605160236197);
INSERT INTO `juncheng_security_admin_log` VALUES ('3b2d3240dd324c6aa462529bd88dc381', 0, '登录', 'admin', '管理员在2020-10-20 16:45:07登录了系统，IP是127.0.0.1', 'admin', 1603183507783, 'admin', 1603183507783);
INSERT INTO `juncheng_security_admin_log` VALUES ('3ba7f10aad8e4596893dd0bfd67f781a', 0, '登录', 'admin', '管理员在2020-12-24 09:51:23登录了系统，IP是127.0.0.1', '1', 1608774683996, '1', 1608774683996);
INSERT INTO `juncheng_security_admin_log` VALUES ('3bce5a39cdc7466592052c88eaddd8fb', 0, '登录', 'admin', '管理员在2020-11-24 15:35:31登录了系统，IP是192.168.31.215', 'admin', 1606203331430, 'admin', 1606203331430);
INSERT INTO `juncheng_security_admin_log` VALUES ('3d1020b02f05485cbb92166f28ed0302', 0, '登录', 'admin', '管理员在2020-12-24 13:35:46登录了系统，IP是127.0.0.1', '1', 1608788146177, '1', 1608788146177);
INSERT INTO `juncheng_security_admin_log` VALUES ('3d1eb150e9ec4e5080f9c081d4aba28d', 0, '登录', 'admin', '管理员在2020-11-11 11:09:25登录了系统，IP是192.168.31.215', 'admin', 1605064165129, 'admin', 1605064165129);
INSERT INTO `juncheng_security_admin_log` VALUES ('3da9c7d0d04c42cf9b159e8b469a95da', 0, '登录', 'admin', '管理员在2020-11-12 08:35:13登录了系统，IP是192.168.31.215', 'admin', 1605141313090, 'admin', 1605141313090);
INSERT INTO `juncheng_security_admin_log` VALUES ('3ddee482351b461ab78608f48d107f15', 0, '登录', 'admin', '管理员在2020-12-08 17:05:52登录了系统，IP是192.168.31.220', '1', 1607418352659, '1', 1607418352659);
INSERT INTO `juncheng_security_admin_log` VALUES ('3e28a386c095412e8773e6752fc419b0', 0, '登录', 'admin', '管理员在2020-11-23 10:47:57登录了系统，IP是192.168.31.215', 'admin', 1606099677506, 'admin', 1606099677506);
INSERT INTO `juncheng_security_admin_log` VALUES ('3e949d2f3f5b44e8a126ba7d57e39908', 0, '登录', 'admin', '管理员在2020-11-23 10:33:32登录了系统，IP是192.168.31.215', 'admin', 1606098812161, 'admin', 1606098812161);
INSERT INTO `juncheng_security_admin_log` VALUES ('3fb8581ba3984359b0569117d1406d67', 0, '登录', 'admin', '管理员在2021-01-22 13:36:31登录了系统，IP是192.168.31.220', '1', 1611293791107, '1', 1611293791107);
INSERT INTO `juncheng_security_admin_log` VALUES ('40f538cc9fd540dba92e7b8aff219f85', 0, '登录', 'admin', '管理员在2020-11-24 15:27:05登录了系统，IP是192.168.31.215', 'admin', 1606202825575, 'admin', 1606202825575);
INSERT INTO `juncheng_security_admin_log` VALUES ('419b73f3bb6f4abc8a23e4e890d9e853', 0, '登录', 'admin', '管理员在2020-11-09 11:49:35登录了系统，IP是192.168.31.220', 'admin', 1604893775054, 'admin', 1604893775054);
INSERT INTO `juncheng_security_admin_log` VALUES ('4213d927fd90451e9943df39b9e82147', 0, '登录', 'admin', '管理员在2020-12-15 10:40:28登录了系统，IP是192.168.31.220', '1', 1608000028966, '1', 1608000028966);
INSERT INTO `juncheng_security_admin_log` VALUES ('42425c77ac8e4dd688db9586d9e403e9', 0, '登录', 'admin', '管理员在2020-11-24 11:03:39登录了系统，IP是192.168.31.215', 'admin', 1606187019008, 'admin', 1606187019008);
INSERT INTO `juncheng_security_admin_log` VALUES ('4461a6444e614ec3bec0d710f92260ae', 0, '登录', 'admin', '管理员在2020-11-24 12:53:48登录了系统，IP是192.168.31.215', 'admin', 1606193628455, 'admin', 1606193628455);
INSERT INTO `juncheng_security_admin_log` VALUES ('44a579fd57d940cf9d382d053f047869', 0, '登录', 'admin', '管理员在2020-11-23 21:22:04登录了系统，IP是192.168.1.100', 'admin', 1606137724248, 'admin', 1606137724249);
INSERT INTO `juncheng_security_admin_log` VALUES ('44e4df491eae43b58f1d66bcd615e4e1', 0, '登录', 'admin', '管理员在2020-12-08 15:30:00登录了系统，IP是192.168.31.220', '1', 1607412600736, '1', 1607412600736);
INSERT INTO `juncheng_security_admin_log` VALUES ('454935861e1c4669ae9b32b6ca004d1b', 0, '登录', 'admin', '管理员在2020-11-12 11:53:52登录了系统，IP是192.168.31.215', 'admin', 1605153232945, 'admin', 1605153232945);
INSERT INTO `juncheng_security_admin_log` VALUES ('454c27ca116149ff8e02626fa478c525', 0, '登录', 'admin', '管理员在2021-01-11 17:37:26登录了系统，IP是113.227.196.144', '1', 1610357846134, '1', 1610357846134);
INSERT INTO `juncheng_security_admin_log` VALUES ('45da86d70ba44eb7996bb73e1dca683d', 0, '登录', 'admin', '管理员在2020-11-27 21:13:48登录了系统，IP是192.168.31.215', 'admin', 1606482828972, 'admin', 1606482828972);
INSERT INTO `juncheng_security_admin_log` VALUES ('46039662f3de4e15813cbccd8b689aec', 0, '登录', 'admin', '管理员在2020-11-24 13:30:42登录了系统，IP是192.168.31.215', 'admin', 1606195842933, 'admin', 1606195842933);
INSERT INTO `juncheng_security_admin_log` VALUES ('46a05b28f44e48f4ae9a957678d0ad47', 0, '登录', 'admin', '管理员在2020-12-29 09:31:06登录了系统，IP是127.0.0.1', '1', 1609205466154, '1', 1609205466154);
INSERT INTO `juncheng_security_admin_log` VALUES ('4838ad2b394045a3a1981eba846f3ef4', 0, '登录', 'admin', '管理员在2020-12-04 17:45:41登录了系统，IP是192.168.31.220', '12a512ae1a87460c93d2312e216e537b', 1607075141267, '12a512ae1a87460c93d2312e216e537b', 1607075141267);
INSERT INTO `juncheng_security_admin_log` VALUES ('4875748f531a453488340183e3a5029b', 0, '登录', '2a65ee158c334c09ae8becf5ca3f7e5c', '客服在2020-10-19 16:40:52登录了系统，IP是127.0.0.1', '2a65ee158c334c09ae8becf5ca3f7e5c', 1603096852278, '2a65ee158c334c09ae8becf5ca3f7e5c', 1603096852278);
INSERT INTO `juncheng_security_admin_log` VALUES ('49103bd7ede84287bd055714c0f5c312', 0, '登录', 'admin', '管理员在2020-11-24 13:43:20登录了系统，IP是192.168.31.215', 'admin', 1606196600500, 'admin', 1606196600500);
INSERT INTO `juncheng_security_admin_log` VALUES ('49d4751a5b244d69b42384279c2cb1d4', 0, '登录', 'admin', '管理员在2020-11-10 18:24:42登录了系统，IP是192.168.31.215', 'admin', 1605003882052, 'admin', 1605003882052);
INSERT INTO `juncheng_security_admin_log` VALUES ('4ad74733f71c489789707793aa2d6511', 0, '登录', '9eaae11bac4b4948ab60ab160765aca8', 'demo在2020-12-14 11:20:41登录了系统，IP是127.0.0.1', '1', 1607916041497, '1', 1607916041497);
INSERT INTO `juncheng_security_admin_log` VALUES ('4b5abd667d054bfa89088a41ec6166ce', 0, '登录', 'admin', '管理员在2020-10-19 11:06:30，IP是127.0.0.1登录了系统', 'admin', 1603076790459, 'admin', 1603076790459);
INSERT INTO `juncheng_security_admin_log` VALUES ('4b62bef0b0204f9e89cc0e4c5258117d', 0, '登录', 'admin', '管理员在2020-11-09 11:31:02登录了系统，IP是127.0.0.1', 'admin', 1604892662014, 'admin', 1604892662014);
INSERT INTO `juncheng_security_admin_log` VALUES ('4ca6fb971d334b9197c2706a9254e9bd', 0, '登录', 'admin', '管理员在2020-11-09 14:39:22登录了系统，IP是127.0.0.1', 'admin', 1604903962221, 'admin', 1604903962221);
INSERT INTO `juncheng_security_admin_log` VALUES ('4cb8fd8b1d1d40ea878a70a4fa8e4667', 0, '登录', 'admin', '管理员在2020-12-30 13:58:49登录了系统，IP是127.0.0.1', '1', 1609307929812, '1', 1609307929812);
INSERT INTO `juncheng_security_admin_log` VALUES ('4cc2a751432c482e9d1f99ec40139005', 0, '登录', 'admin', '管理员在2020-11-11 10:21:16登录了系统，IP是192.168.31.215', 'admin', 1605061276615, 'admin', 1605061276615);
INSERT INTO `juncheng_security_admin_log` VALUES ('4cd32764c8f64947985ad0db6251a94a', 0, '登录', 'admin', '管理员在2020-10-21 14:56:44登录了系统，IP是127.0.0.1', 'admin', 1603263404476, 'admin', 1603263404476);
INSERT INTO `juncheng_security_admin_log` VALUES ('4dd9943d4664473ea3f18eeca3f8c181', 0, '登录', 'admin', '管理员在2021-01-11 17:35:08登录了系统，IP是113.227.196.144', '1', 1610357708784, '1', 1610357708784);
INSERT INTO `juncheng_security_admin_log` VALUES ('4f9c9fcdcc284df0af8e8b4ad1b04e24', 0, '登录', '2a65ee158c334c09ae8becf5ca3f7e5c', '客服在2020-10-19 16:24:01登录了系统，IP是127.0.0.1', '2a65ee158c334c09ae8becf5ca3f7e5c', 1603095841740, '2a65ee158c334c09ae8becf5ca3f7e5c', 1603095841740);
INSERT INTO `juncheng_security_admin_log` VALUES ('4f9eed80f98240d8885ab6e92c16d109', 0, '登录', 'admin', '管理员在2020-11-11 09:05:58登录了系统，IP是192.168.31.215', 'admin', 1605056758924, 'admin', 1605056758924);
INSERT INTO `juncheng_security_admin_log` VALUES ('4fc50923ed274eb0998fa6b66d09362a', 0, '登录', 'admin', '管理员在2020-11-10 15:48:55登录了系统，IP是192.168.31.215', 'admin', 1604994535211, 'admin', 1604994535211);
INSERT INTO `juncheng_security_admin_log` VALUES ('500311003b2d47d1a78b39110bdda9d0', 0, '登录', 'admin', '管理员在2020-11-25 17:48:02登录了系统，IP是192.168.31.215', 'admin', 1606297682242, 'admin', 1606297682242);
INSERT INTO `juncheng_security_admin_log` VALUES ('504f2ff7b1bb4d1887d80340cbd7e404', 0, '登录', 'admin', '管理员在2020-12-14 15:48:58登录了系统，IP是127.0.0.1', '1', 1607932138521, '1', 1607932138521);
INSERT INTO `juncheng_security_admin_log` VALUES ('50998c1deb8442f580db7888558fefe1', 0, '登录', 'admin', '管理员在2020-11-24 11:01:31登录了系统，IP是192.168.31.215', 'admin', 1606186891028, 'admin', 1606186891028);
INSERT INTO `juncheng_security_admin_log` VALUES ('51ef7c4bc4504a0c972f9c6a7fa969c3', 0, '登录', 'admin', '管理员在2020-12-14 12:39:31登录了系统，IP是127.0.0.1', '1', 1607920771950, '1', 1607920771950);
INSERT INTO `juncheng_security_admin_log` VALUES ('546d784f3515471fad8738d7400799a7', 0, '登录', 'admin', '管理员在2020-11-28 08:07:33登录了系统，IP是192.168.31.215', 'admin', 1606522053152, 'admin', 1606522053152);
INSERT INTO `juncheng_security_admin_log` VALUES ('55db29799fc7476493d3dcb23db6b5d7', 0, '登录', 'admin', '管理员在2020-12-30 13:57:42登录了系统，IP是127.0.0.1', '1', 1609307862619, '1', 1609307862619);
INSERT INTO `juncheng_security_admin_log` VALUES ('571505ae8e5849969ca1fd29b50ebd00', 0, '登录', 'admin', '管理员在2020-10-19 16:25:10登录了系统，IP是127.0.0.1', 'admin', 1603095910203, 'admin', 1603095910203);
INSERT INTO `juncheng_security_admin_log` VALUES ('5760840ba25b4f8090180c7cf70f70f5', 0, '登录', 'admin', '管理员在2020-11-27 12:50:51登录了系统，IP是192.168.31.118', 'admin', 1606452651469, 'admin', 1606452651469);
INSERT INTO `juncheng_security_admin_log` VALUES ('582ffaae4a0848eeab59c849a278a378', 0, '登录', 'admin', '管理员在2020-11-11 13:25:25登录了系统，IP是192.168.31.215', 'admin', 1605072325811, 'admin', 1605072325811);
INSERT INTO `juncheng_security_admin_log` VALUES ('58386bd3f2bd416c89be69203af6d731', 0, '登录', 'admin', '管理员在2020-12-04 16:15:18登录了系统，IP是192.168.31.220', 'admin', 1607069718344, 'admin', 1607069718344);
INSERT INTO `juncheng_security_admin_log` VALUES ('592c8439dcdc4f1a85d0bc292cf80902', 0, '登录', 'admin', '管理员在2020-11-28 13:39:32登录了系统，IP是192.168.31.215', 'admin', 1606541972173, 'admin', 1606541972173);
INSERT INTO `juncheng_security_admin_log` VALUES ('5abdfe5ed61449b295252c151254f009', 0, '登录', 'admin', '管理员在2020-10-21 15:12:33登录了系统，IP是127.0.0.1', 'admin', 1603264353129, 'admin', 1603264353129);
INSERT INTO `juncheng_security_admin_log` VALUES ('5c10177347b543d38d0ebecc33b70495', 0, '登录', 'admin', '管理员在2020-12-15 10:43:31登录了系统，IP是127.0.0.1', '1', 1608000211093, '1', 1608000211093);
INSERT INTO `juncheng_security_admin_log` VALUES ('5cc7e7e93fad461881396f469a294165', 0, '登录', 'admin', '管理员在2020-12-30 13:57:58登录了系统，IP是127.0.0.1', '1', 1609307878349, '1', 1609307878349);
INSERT INTO `juncheng_security_admin_log` VALUES ('5d1590966b024575a6707e731d7fe83f', 0, '登录', 'admin', '管理员在2020-10-19 16:38:23登录了系统，IP是127.0.0.1', 'admin', 1603096703985, 'admin', 1603096703985);
INSERT INTO `juncheng_security_admin_log` VALUES ('5e26c0e7bb50496eae68d784a815eb06', 0, '登录', 'admin', '管理员在2020-12-30 14:01:24登录了系统，IP是127.0.0.1', '1', 1609308084775, '1', 1609308084775);
INSERT INTO `juncheng_security_admin_log` VALUES ('60074be350b848aba399154c30c690f8', 0, '登录', 'admin', '管理员在2020-10-21 14:57:07登录了系统，IP是127.0.0.1', 'admin', 1603263427379, 'admin', 1603263427379);
INSERT INTO `juncheng_security_admin_log` VALUES ('604420064ede4cd08549021e5f10d8f3', 0, '登录', 'admin', '管理员在2020-11-12 08:36:09登录了系统，IP是192.168.31.215', 'admin', 1605141369694, 'admin', 1605141369694);
INSERT INTO `juncheng_security_admin_log` VALUES ('606eb7134c7e4ecd825727e264ec6854', 0, '登录', 'admin', '管理员在2020-11-17 09:30:00登录了系统，IP是192.168.31.215', 'admin', 1605576600543, 'admin', 1605576600543);
INSERT INTO `juncheng_security_admin_log` VALUES ('60a725970b8b4205976539e855a7652d', 0, '登录', 'admin', '管理员在2020-11-12 15:44:58登录了系统，IP是192.168.31.215', 'admin', 1605167098348, 'admin', 1605167098348);
INSERT INTO `juncheng_security_admin_log` VALUES ('63c84753c0ab4f3ba3e6619e5760e6ac', 0, '登录', 'admin', '管理员在2020-10-21 14:07:23登录了系统，IP是127.0.0.1', 'admin', 1603260443363, 'admin', 1603260443363);
INSERT INTO `juncheng_security_admin_log` VALUES ('63f98194a10a411bb851895a0a8ac5eb', 0, '登录', 'admin', '管理员在2020-11-28 08:57:44登录了系统，IP是192.168.31.215', 'admin', 1606525064992, 'admin', 1606525064992);
INSERT INTO `juncheng_security_admin_log` VALUES ('6523227d5ae74f489691e3b248163ea9', 0, '登录', 'admin', '管理员在2020-11-28 14:19:56登录了系统，IP是192.168.31.215', 'admin', 1606544396616, 'admin', 1606544396616);
INSERT INTO `juncheng_security_admin_log` VALUES ('65b85dc061604e049ee741ce92d83103', 0, '登录', 'admin', '管理员在2020-11-28 11:53:14登录了系统，IP是192.168.31.215', 'admin', 1606535594393, 'admin', 1606535594393);
INSERT INTO `juncheng_security_admin_log` VALUES ('664cd6bc8d274a4ab12f3b6390b97399', 0, '登录', 'admin', '管理员在2020-11-24 15:50:50登录了系统，IP是192.168.31.215', 'admin', 1606204250042, 'admin', 1606204250042);
INSERT INTO `juncheng_security_admin_log` VALUES ('66bc0c3768284621831d1d2a3b4fc12e', 0, '登录', 'admin', '管理员在2020-11-11 09:06:08登录了系统，IP是192.168.31.215', 'admin', 1605056768769, 'admin', 1605056768769);
INSERT INTO `juncheng_security_admin_log` VALUES ('6738692a7bce4b28874f432142a2203d', 0, '登录', 'admin', '管理员在2020-10-21 15:17:03登录了系统，IP是127.0.0.1', 'admin', 1603264623448, 'admin', 1603264623448);
INSERT INTO `juncheng_security_admin_log` VALUES ('69facba87001462e96e6539e15ef9cd4', 0, '登录', 'admin', '管理员在2020-12-24 10:01:05登录了系统，IP是127.0.0.1', '1', 1608775265763, '1', 1608775265763);
INSERT INTO `juncheng_security_admin_log` VALUES ('6b45435077e34c19852c069db58ffea7', 0, '登录', 'admin', '管理员在2020-11-23 09:52:08登录了系统，IP是192.168.31.215', 'admin', 1606096328935, 'admin', 1606096328935);
INSERT INTO `juncheng_security_admin_log` VALUES ('6ba9d173a89343cca67fdf0fd0b9375a', 0, '登录', 'admin', '管理员在2020-12-24 09:41:16登录了系统，IP是192.168.31.220', '1', 1608774076289, '1', 1608774076289);
INSERT INTO `juncheng_security_admin_log` VALUES ('6bbc74ddf7404ef98df35184e36c6f37', 0, '登录', 'admin', '管理员在2020-11-10 14:22:45登录了系统，IP是127.0.0.1', 'admin', 1604989365002, 'admin', 1604989365002);
INSERT INTO `juncheng_security_admin_log` VALUES ('70d8ea7d44124da8a437ea691b203e6f', 0, '登录', 'admin', '管理员在2020-11-24 09:45:23登录了系统，IP是192.168.31.215', 'admin', 1606182323699, 'admin', 1606182323699);
INSERT INTO `juncheng_security_admin_log` VALUES ('70eaa32628ee4992a4e7224451c54f16', 0, '登录', 'admin', '管理员在2020-11-28 16:12:46登录了系统，IP是192.168.31.215', 'admin', 1606551166185, 'admin', 1606551166185);
INSERT INTO `juncheng_security_admin_log` VALUES ('71024a5da0234b50bb2cbb145086cf58', 0, '登录', 'admin', '管理员在2020-12-24 09:34:16登录了系统，IP是127.0.0.1', '1', 1608773656144, '1', 1608773656144);
INSERT INTO `juncheng_security_admin_log` VALUES ('713b2c5ad35c4f5798dbbdcb8627d2e6', 0, '登录', 'admin', '管理员在2020-11-23 16:41:07登录了系统，IP是192.168.31.215', 'admin', 1606120867736, 'admin', 1606120867736);
INSERT INTO `juncheng_security_admin_log` VALUES ('72506883292749aba423077dfbaba882', 0, '登录', 'admin', '管理员在2020-12-04 16:40:58登录了系统，IP是192.168.31.220', '1', 1607071258658, '1', 1607071258658);
INSERT INTO `juncheng_security_admin_log` VALUES ('7258d4dabd264d10a2d357d4d2f569c0', 0, '登录', '2a65ee158c334c09ae8becf5ca3f7e5c', '客服在2020-10-21 14:06:29登录了系统，IP是127.0.0.1', '2a65ee158c334c09ae8becf5ca3f7e5c', 1603260389431, '2a65ee158c334c09ae8becf5ca3f7e5c', 1603260389431);
INSERT INTO `juncheng_security_admin_log` VALUES ('7288d61cdbd340f49a47e512c0d11d12', 0, '登录', 'admin', '管理员在2020-12-25 14:32:20登录了系统，IP是127.0.0.1', '1', 1608877940538, '1', 1608877940538);
INSERT INTO `juncheng_security_admin_log` VALUES ('748c7510a94148cc894f316e015a6840', 0, '登录', 'admin', '管理员在2020-10-19 16:13:04登录了系统，IP是127.0.0.1', 'admin', 1603095184327, 'admin', 1603095184327);
INSERT INTO `juncheng_security_admin_log` VALUES ('75ba8985a3d9471887ee2b86ac1c959f', 0, '登录', 'admin', '管理员在2020-11-24 16:48:53登录了系统，IP是192.168.31.215', 'admin', 1606207733337, 'admin', 1606207733337);
INSERT INTO `juncheng_security_admin_log` VALUES ('75ed134a086d4f6981c3aa7a4f739b0c', 0, '登录', 'admin', '管理员在2020-10-21 15:03:20登录了系统，IP是192.168.31.221', 'admin', 1603263800853, 'admin', 1603263800853);
INSERT INTO `juncheng_security_admin_log` VALUES ('771d3e5dead545ed86579174ce0347a7', 0, '登录', 'admin', '管理员在2020-11-24 08:49:58登录了系统，IP是192.168.31.215', 'admin', 1606178998029, 'admin', 1606178998030);
INSERT INTO `juncheng_security_admin_log` VALUES ('7921008b7cae4bcf901cb7bb931c95a0', 0, '登录', 'admin', '管理员在2020-12-08 16:52:05登录了系统，IP是192.168.31.220', '1', 1607417525308, '1', 1607417525308);
INSERT INTO `juncheng_security_admin_log` VALUES ('79663a5a5e8242eaae4cbffb1acd2c94', 0, '登录', 'admin', '管理员在2020-12-24 17:38:18登录了系统，IP是127.0.0.1', '1', 1608802698395, '1', 1608802698395);
INSERT INTO `juncheng_security_admin_log` VALUES ('7a75c94100f8424bbece2cc1d44aa6be', 0, '登录', 'admin', '管理员在2020-11-24 13:33:11登录了系统，IP是192.168.31.215', 'admin', 1606195991940, 'admin', 1606195991940);
INSERT INTO `juncheng_security_admin_log` VALUES ('7a7ad75963b346c5af142b43530e2094', 0, '登录', 'admin', '管理员在2020-10-21 14:58:03登录了系统，IP是127.0.0.1', 'admin', 1603263483490, 'admin', 1603263483490);
INSERT INTO `juncheng_security_admin_log` VALUES ('7a93f99e408e4d97b54f800e0d0bb465', 0, '登录', 'admin', '管理员在2020-10-19 17:35:33登录了系统，IP是127.0.0.1', 'admin', 1603100133712, 'admin', 1603100133712);
INSERT INTO `juncheng_security_admin_log` VALUES ('7b80fd84e14d40d2b3f0a2d54b20c654', 0, '登录', 'admin', '管理员在2020-12-04 17:58:19登录了系统，IP是192.168.31.220', 'admin', 1607075899188, 'admin', 1607075899188);
INSERT INTO `juncheng_security_admin_log` VALUES ('7be1a23c514e44c387924b0939dbafc2', 0, '登录', 'admin', '管理员在2020-11-11 18:54:48登录了系统，IP是192.168.31.215', 'admin', 1605092088153, 'admin', 1605092088153);
INSERT INTO `juncheng_security_admin_log` VALUES ('7c5d4ef254914265b69815101e71b81d', 0, '登录', 'admin', '管理员在2021-01-11 17:36:05登录了系统，IP是113.227.196.144', '1', 1610357765830, '1', 1610357765830);
INSERT INTO `juncheng_security_admin_log` VALUES ('7d89ca0d1d984375a1f9ea94ef50b31c', 0, '登录', 'admin', '管理员在2020-11-12 17:31:03登录了系统，IP是192.168.31.215', 'admin', 1605173463019, 'admin', 1605173463019);
INSERT INTO `juncheng_security_admin_log` VALUES ('7e152d70c4304ff7a6c4d9d7a2fbf4c0', 0, '登录', 'admin', '管理员在2020-11-28 10:52:09登录了系统，IP是192.168.31.215', 'admin', 1606531929764, 'admin', 1606531929764);
INSERT INTO `juncheng_security_admin_log` VALUES ('7f1d663ae4934c94904fac3e82c43f9b', 0, '登录', 'admin', '管理员在2020-10-19 16:39:35登录了系统，IP是127.0.0.1', 'admin', 1603096775209, 'admin', 1603096775209);
INSERT INTO `juncheng_security_admin_log` VALUES ('7fe8ef3ff5ab4f01915cff3ba6ed4e2c', 0, '登录', 'admin', '管理员在2020-10-20 18:15:52登录了系统，IP是127.0.0.1', 'admin', 1603188952132, 'admin', 1603188952132);
INSERT INTO `juncheng_security_admin_log` VALUES ('84a771e49fe54d3fb7c65108d5e6040b', 0, '登录', 'admin', '管理员在2020-11-11 14:13:28登录了系统，IP是192.168.31.215', 'admin', 1605075208714, 'admin', 1605075208714);
INSERT INTO `juncheng_security_admin_log` VALUES ('858288cf982f45fdbc4384da5b6595be', 0, '登录', 'admin', '管理员在2020-11-11 10:26:06登录了系统，IP是192.168.31.215', 'admin', 1605061566950, 'admin', 1605061566950);
INSERT INTO `juncheng_security_admin_log` VALUES ('85ea7ebc72314d68b58e2246249af4fe', 0, '登录', 'admin', '管理员在2020-12-08 16:49:13登录了系统，IP是192.168.31.220', '1', 1607417353942, '1', 1607417353942);
INSERT INTO `juncheng_security_admin_log` VALUES ('866dfbcaa9644384b6a59b33cf395134', 0, '登录', '2a65ee158c334c09ae8becf5ca3f7e5c', '客服在2020-10-19 16:47:52登录了系统，IP是127.0.0.1', '2a65ee158c334c09ae8becf5ca3f7e5c', 1603097272144, '2a65ee158c334c09ae8becf5ca3f7e5c', 1603097272144);
INSERT INTO `juncheng_security_admin_log` VALUES ('8699f31e93d540e0af40289bb54f3979', 0, '登录', 'admin', '管理员在2020-11-11 10:08:30登录了系统，IP是192.168.31.215', 'admin', 1605060510913, 'admin', 1605060510913);
INSERT INTO `juncheng_security_admin_log` VALUES ('8716db2ffc124858a78f5c8af138a5e0', 0, '登录', 'admin', '管理员在2020-10-19 17:21:45登录了系统，IP是192.168.31.220', 'admin', 1603099305124, 'admin', 1603099305124);
INSERT INTO `juncheng_security_admin_log` VALUES ('879472210aa14751b3586b90275577d1', 0, '登录', 'admin', '管理员在2020-11-11 10:10:05登录了系统，IP是192.168.31.215', 'admin', 1605060605867, 'admin', 1605060605867);
INSERT INTO `juncheng_security_admin_log` VALUES ('87e787cc7291421b9a2cd3dce13cbab1', 0, '登录', 'admin', '管理员在2020-11-11 14:40:51登录了系统，IP是192.168.31.215', 'admin', 1605076851806, 'admin', 1605076851806);
INSERT INTO `juncheng_security_admin_log` VALUES ('8a75f3fef2d64de5944624cb2061dcec', 0, '登录', 'admin', '管理员在2020-12-04 16:41:23登录了系统，IP是192.168.31.220', '1', 1607071283976, '1', 1607071283976);
INSERT INTO `juncheng_security_admin_log` VALUES ('8ae3715bb4174d88b439c67f501a2e8e', 0, '登录', 'admin', '管理员在2020-12-08 18:28:41登录了系统，IP是127.0.0.1', 'admin', 1607423321181, 'admin', 1607423321181);
INSERT INTO `juncheng_security_admin_log` VALUES ('8ae44f1a85034a169990b1a8c394aa8d', 0, '登录', 'admin', '管理员在2020-12-09 16:50:22登录了系统，IP是127.0.0.1', '1', 1607503822900, '1', 1607503822900);
INSERT INTO `juncheng_security_admin_log` VALUES ('8afb8047aefb42e39375c107ba3e27f0', 0, '登录', 'admin', '管理员在2020-11-24 13:26:41登录了系统，IP是192.168.31.215', 'admin', 1606195601446, 'admin', 1606195601446);
INSERT INTO `juncheng_security_admin_log` VALUES ('8b4720f516f34f64bed94054dee56de2', 0, '登录', 'admin', '管理员在2020-11-11 18:25:04登录了系统，IP是192.168.31.215', 'admin', 1605090304208, 'admin', 1605090304208);
INSERT INTO `juncheng_security_admin_log` VALUES ('8b679dfa1caa4502bd44d942cb22cc35', 0, '登录', 'admin', '管理员在2020-12-14 11:18:07登录了系统，IP是127.0.0.1', '1', 1607915887288, '1', 1607915887288);
INSERT INTO `juncheng_security_admin_log` VALUES ('8bb80864ec4243b2ba142f52b7104dcb', 0, '登录', 'admin', '管理员在2020-11-11 08:39:47登录了系统，IP是192.168.31.215', 'admin', 1605055187546, 'admin', 1605055187546);
INSERT INTO `juncheng_security_admin_log` VALUES ('8f8dea9b2a184f1ab809f22f525bf200', 0, '登录', 'admin', '管理员在2020-11-24 13:20:05登录了系统，IP是192.168.31.215', 'admin', 1606195205223, 'admin', 1606195205223);
INSERT INTO `juncheng_security_admin_log` VALUES ('8fb36aed89aa4e46a3b61928da83b254', 0, '登录', 'admin', '管理员在2020-11-11 12:33:09登录了系统，IP是192.168.31.215', 'admin', 1605069189451, 'admin', 1605069189451);
INSERT INTO `juncheng_security_admin_log` VALUES ('8fc02464932948ceb7ab063215a60492', 0, '登录', 'admin', '管理员在2020-11-12 10:21:58登录了系统，IP是192.168.31.215', 'admin', 1605147718708, 'admin', 1605147718709);
INSERT INTO `juncheng_security_admin_log` VALUES ('903015e10c9e433f8500fc968bad8b35', 0, '登录', 'admin', '管理员在2020-11-12 10:20:25登录了系统，IP是192.168.31.215', 'admin', 1605147625699, 'admin', 1605147625699);
INSERT INTO `juncheng_security_admin_log` VALUES ('90699674a83d4f5d80fb7f0a86b205f9', 0, '登录', 'admin', '管理员在2020-10-20 17:53:46登录了系统，IP是127.0.0.1', 'admin', 1603187626489, 'admin', 1603187626489);
INSERT INTO `juncheng_security_admin_log` VALUES ('9094926bdcfe49bbbeb9dbc5471a2f2e', 0, '登录', 'admin', '管理员在2020-11-09 11:42:53登录了系统，IP是192.168.31.220', 'admin', 1604893373725, 'admin', 1604893373725);
INSERT INTO `juncheng_security_admin_log` VALUES ('90c561eeab4240bd8f674f062eb72268', 0, '登录', 'admin', '管理员在2020-11-24 13:39:00登录了系统，IP是192.168.31.215', 'admin', 1606196340325, 'admin', 1606196340325);
INSERT INTO `juncheng_security_admin_log` VALUES ('90e5e9b41b5a4417ac98e9cf56c15eb7', 0, '登录', 'admin', '管理员在2020-10-19 17:19:12登录了系统，IP是127.0.0.1', 'admin', 1603099152389, 'admin', 1603099152389);
INSERT INTO `juncheng_security_admin_log` VALUES ('91a0e8b792374bd39ee93c1a67f285ec', 0, '登录', 'admin', '管理员在2020-10-19 16:36:55登录了系统，IP是127.0.0.1', 'admin', 1603096615781, 'admin', 1603096615781);
INSERT INTO `juncheng_security_admin_log` VALUES ('9294ba5a92404b04b0ef11ad4561ca04', 0, '登录', 'admin', '管理员在2020-11-23 11:35:21登录了系统，IP是192.168.31.215', 'admin', 1606102521005, 'admin', 1606102521005);
INSERT INTO `juncheng_security_admin_log` VALUES ('929b4d3b2121424b9bb6f9bce53a0fb8', 0, '登录', 'admin', '管理员在2020-11-23 16:37:16登录了系统，IP是192.168.31.215', 'admin', 1606120636008, 'admin', 1606120636008);
INSERT INTO `juncheng_security_admin_log` VALUES ('92ae67c0deb74cc5a0a4dad2a306c7b6', 0, '登录', 'admin', '管理员在2020-11-12 09:51:14登录了系统，IP是192.168.31.215', 'admin', 1605145874392, 'admin', 1605145874392);
INSERT INTO `juncheng_security_admin_log` VALUES ('92ec9906c6304e94a989c58badbf2038', 0, '登录', 'admin', '管理员在2020-12-04 16:34:33登录了系统，IP是192.168.31.220', 'admin', 1607070873900, 'admin', 1607070873900);
INSERT INTO `juncheng_security_admin_log` VALUES ('92fe380ff6114faaa1e7241c6eff1413', 0, '登录', '2a65ee158c334c09ae8becf5ca3f7e5c', '客服在2020-10-19 16:21:36登录了系统，IP是127.0.0.1', '2a65ee158c334c09ae8becf5ca3f7e5c', 1603095696748, '2a65ee158c334c09ae8becf5ca3f7e5c', 1603095696748);
INSERT INTO `juncheng_security_admin_log` VALUES ('930ef5b0759f4d1ca6476ad11a469778', 0, '登录', 'admin', '管理员在2021-01-11 17:36:14登录了系统，IP是113.227.196.144', '1', 1610357774776, '1', 1610357774776);
INSERT INTO `juncheng_security_admin_log` VALUES ('945664d735c343d4a242212182f6a5f1', 0, '登录', 'admin', '管理员在2020-10-21 15:01:20登录了系统，IP是127.0.0.1', 'admin', 1603263680044, 'admin', 1603263680044);
INSERT INTO `juncheng_security_admin_log` VALUES ('951ac43c6f324b1a8b3f17717f9b8093', 0, '登录', 'admin', '管理员在2020-10-20 17:53:15登录了系统，IP是127.0.0.1', 'admin', 1603187595873, 'admin', 1603187595873);
INSERT INTO `juncheng_security_admin_log` VALUES ('96663a98cf3d442695d26cfdd84bd430', 0, '登录', '2a65ee158c334c09ae8becf5ca3f7e5c', '客服在2020-10-19 16:28:00登录了系统，IP是127.0.0.1', '2a65ee158c334c09ae8becf5ca3f7e5c', 1603096080972, '2a65ee158c334c09ae8becf5ca3f7e5c', 1603096080972);
INSERT INTO `juncheng_security_admin_log` VALUES ('976b264c9adc45ad88ac4d320e9b5927', 0, '登录', 'admin', '管理员在2020-10-21 14:58:23登录了系统，IP是127.0.0.1', 'admin', 1603263503133, 'admin', 1603263503133);
INSERT INTO `juncheng_security_admin_log` VALUES ('992f02263365481797733a032e1cf99a', 0, '登录', 'admin', '管理员在2020-11-11 12:41:04登录了系统，IP是192.168.31.215', 'admin', 1605069664064, 'admin', 1605069664064);
INSERT INTO `juncheng_security_admin_log` VALUES ('994bdab0ae0e44d3a6392bd114397d9a', 0, '登录', 'admin', '管理员在2020-12-08 16:52:47登录了系统，IP是192.168.31.220', '1', 1607417567013, '1', 1607417567013);
INSERT INTO `juncheng_security_admin_log` VALUES ('9aa28ddc8fef4a72bd8c1204f9f5ef2e', 0, '登录', '2a65ee158c334c09ae8becf5ca3f7e5c', '客服在2020-10-19 16:20:31登录了系统，IP是127.0.0.1', '2a65ee158c334c09ae8becf5ca3f7e5c', 1603095631500, '2a65ee158c334c09ae8becf5ca3f7e5c', 1603095631500);
INSERT INTO `juncheng_security_admin_log` VALUES ('9ac4a40b8734421f9cabbab1d2b1ebad', 0, '登录', 'admin', '管理员在2020-12-30 14:02:56登录了系统，IP是127.0.0.1', '1', 1609308176134, '1', 1609308176134);
INSERT INTO `juncheng_security_admin_log` VALUES ('9b37388743184b77bfe6455a80d63e1d', 0, '登录', 'admin', '管理员在2020-11-11 09:05:24登录了系统，IP是192.168.31.215', 'admin', 1605056724501, 'admin', 1605056724501);
INSERT INTO `juncheng_security_admin_log` VALUES ('9b59507a7573474ab6b916dedc49e6a1', 0, '登录', 'admin', '管理员在2020-11-10 18:00:38登录了系统，IP是192.168.31.215', 'admin', 1605002438320, 'admin', 1605002438320);
INSERT INTO `juncheng_security_admin_log` VALUES ('9baa56aeddac4bf8bfe3d4f82a7966b1', 0, '登录', 'admin', '管理员在2020-10-19 10:58:01，IP是127.0.0.1登录了系统', 'admin', 1603076281422, 'admin', 1603076281422);
INSERT INTO `juncheng_security_admin_log` VALUES ('9effc75feea049f9b97f14519dec8591', 0, '登录', 'admin', '管理员在2020-10-21 15:01:00登录了系统，IP是127.0.0.1', 'admin', 1603263660141, 'admin', 1603263660141);
INSERT INTO `juncheng_security_admin_log` VALUES ('9feb15ded74b4cd79f4d62f4aeb0ef95', 0, '登录', 'admin', '管理员在2020-11-12 17:43:18登录了系统，IP是192.168.31.215', 'admin', 1605174198902, 'admin', 1605174198902);
INSERT INTO `juncheng_security_admin_log` VALUES ('a0911a4206b84b07b8410fe868b1f58b', 0, '登录', 'admin', '管理员在2020-11-10 13:43:17登录了系统，IP是127.0.0.1', 'admin', 1604986997676, 'admin', 1604986997676);
INSERT INTO `juncheng_security_admin_log` VALUES ('a0df0d300bc844b0826b2842319ebad9', 0, '登录', 'admin', '管理员在2020-10-20 18:13:51登录了系统，IP是127.0.0.1', 'admin', 1603188831003, 'admin', 1603188831003);
INSERT INTO `juncheng_security_admin_log` VALUES ('a144ba2e084d46738f9f1c42dc1cc838', 0, '登录', 'admin', '管理员在2020-11-24 15:17:42登录了系统，IP是192.168.31.215', 'admin', 1606202262998, 'admin', 1606202262998);
INSERT INTO `juncheng_security_admin_log` VALUES ('a32a4dcbfca548d0b8c61d5edcd9214b', 0, '登录', '12a512ae1a87460c93d2312e216e537b', 'demo1在2020-12-04 17:14:22登录了系统，IP是192.168.31.220', 'admin', 1607073262150, 'admin', 1607073262150);
INSERT INTO `juncheng_security_admin_log` VALUES ('a4f43b4e2aa848ccbf637d1ad0a73934', 0, '登录', 'admin', '管理员在2020-11-12 17:46:08登录了系统，IP是192.168.31.215', 'admin', 1605174368795, 'admin', 1605174368795);
INSERT INTO `juncheng_security_admin_log` VALUES ('a51cc64194e44846b4d923ed27f0f2dd', 0, '登录', 'admin', '管理员在2020-10-19 17:18:47登录了系统，IP是127.0.0.1', 'admin', 1603099127986, 'admin', 1603099127986);
INSERT INTO `juncheng_security_admin_log` VALUES ('a537997a4d934da7a6ffd913b9226998', 0, '登录', 'admin', '管理员在2020-10-20 16:28:04登录了系统，IP是127.0.0.1', 'admin', 1603182484837, 'admin', 1603182484837);
INSERT INTO `juncheng_security_admin_log` VALUES ('a66e211739334eb3a2d8a960e6ad3983', 0, '登录', 'admin', '管理员在2020-12-25 16:08:24登录了系统，IP是127.0.0.1', '1', 1608883704672, '1', 1608883704672);
INSERT INTO `juncheng_security_admin_log` VALUES ('a7a57d82692d4975a991bb9cdac7e81e', 0, '登录', 'admin', '管理员在2020-11-10 18:22:05登录了系统，IP是192.168.31.215', 'admin', 1605003725354, 'admin', 1605003725354);
INSERT INTO `juncheng_security_admin_log` VALUES ('a8ccad3e13ea46f7bff2e7c33339e608', 0, '登录', 'admin', '管理员在2020-12-04 16:47:05登录了系统，IP是192.168.31.220', 'admin', 1607071625849, 'admin', 1607071625849);
INSERT INTO `juncheng_security_admin_log` VALUES ('a94a3d1524424adfb1eefd393e99a886', 0, '登录', 'admin', '管理员在2020-10-19 17:22:41登录了系统，IP是192.168.31.220', 'admin', 1603099361338, 'admin', 1603099361339);
INSERT INTO `juncheng_security_admin_log` VALUES ('aa510fdfc32548a9b3e72735a2837f99', 0, '登录', 'admin', '管理员在2021-01-03 13:47:16登录了系统，IP是127.0.0.1', '1', 1609652836140, '1', 1609652836140);
INSERT INTO `juncheng_security_admin_log` VALUES ('ac0e246985e04bebbd175d2a647502ac', 0, '登录', 'admin', '管理员在2020-12-08 17:47:05登录了系统，IP是127.0.0.1', '1', 1607420825274, '1', 1607420825274);
INSERT INTO `juncheng_security_admin_log` VALUES ('acb61cb19bc74db1bd8630c63a0c28bf', 0, '登录', 'admin', '管理员在2020-11-28 17:01:32登录了系统，IP是192.168.31.215', 'admin', 1606554092164, 'admin', 1606554092164);
INSERT INTO `juncheng_security_admin_log` VALUES ('acdf9a92459248a2989d7525a0fd3291', 0, '登录', 'admin', '管理员在2020-11-11 16:23:15登录了系统，IP是192.168.31.215', 'admin', 1605082995165, 'admin', 1605082995165);
INSERT INTO `juncheng_security_admin_log` VALUES ('ad33c91f45e2469c952254718295f5ef', 0, '登录', 'admin', '管理员1在2020-10-19 11:08:39，IP是127.0.0.1登录了系统', 'admin', 1603076919556, 'admin', 1603076919556);
INSERT INTO `juncheng_security_admin_log` VALUES ('af34cec818ba4dd58248baa46d2f3e60', 0, '登录', 'admin', '管理员在2020-11-21 10:01:58登录了系统，IP是192.168.31.215', 'admin', 1605924118178, 'admin', 1605924118178);
INSERT INTO `juncheng_security_admin_log` VALUES ('af8ddfa9761d497a9264bf9af33039bb', 0, '登录', 'admin', '管理员在2020-12-04 17:04:58登录了系统，IP是192.168.31.220', '1', 1607072698036, '1', 1607072698036);
INSERT INTO `juncheng_security_admin_log` VALUES ('b02ba24998a247a3877467f85a8b0372', 0, '登录', '2a65ee158c334c09ae8becf5ca3f7e5c', '客服在2020-10-21 14:06:55登录了系统，IP是127.0.0.1', '2a65ee158c334c09ae8becf5ca3f7e5c', 1603260415285, '2a65ee158c334c09ae8becf5ca3f7e5c', 1603260415285);
INSERT INTO `juncheng_security_admin_log` VALUES ('b056c31c17e146f9b7e625841afc33ce', 0, '登录', 'admin', '管理员在2020-10-21 15:07:20登录了系统，IP是192.168.31.221', 'admin', 1603264040154, 'admin', 1603264040154);
INSERT INTO `juncheng_security_admin_log` VALUES ('b05abf8aeace41f2bd46540b4b445043', 0, '登录', 'admin', '管理员在2020-11-18 10:56:25登录了系统，IP是192.168.31.215', 'admin', 1605668185900, 'admin', 1605668185900);
INSERT INTO `juncheng_security_admin_log` VALUES ('b0707ce1d7024b5cbda5ef06e86623d7', 0, '登录', 'admin', '管理员在2020-11-12 13:22:36登录了系统，IP是192.168.31.215', 'admin', 1605158556633, 'admin', 1605158556633);
INSERT INTO `juncheng_security_admin_log` VALUES ('b07b4bd308134a409b4a12fb40a28906', 0, '登录', '2a65ee158c334c09ae8becf5ca3f7e5c', '客服在2020-10-21 14:58:33登录了系统，IP是127.0.0.1', '2a65ee158c334c09ae8becf5ca3f7e5c', 1603263513466, '2a65ee158c334c09ae8becf5ca3f7e5c', 1603263513466);
INSERT INTO `juncheng_security_admin_log` VALUES ('b098d6a6b0484dd4b901cf24c21d919b', 0, '登录', 'admin', '管理员在2020-10-19 17:21:29登录了系统，IP是192.168.31.220', 'admin', 1603099289771, 'admin', 1603099289771);
INSERT INTO `juncheng_security_admin_log` VALUES ('b0a6375f08164f648c8c7c3652ff6917', 0, '登录', 'admin', '管理员在2020-11-25 10:57:19登录了系统，IP是192.168.31.215', 'admin', 1606273039011, 'admin', 1606273039011);
INSERT INTO `juncheng_security_admin_log` VALUES ('b14990c20e7a4bbe855952080b9fb06f', 0, '登录', 'admin', '管理员在2020-12-14 15:47:43登录了系统，IP是127.0.0.1', '1', 1607932063793, '1', 1607932063793);
INSERT INTO `juncheng_security_admin_log` VALUES ('b21545f5964e4256835f9d29cab54fe5', 0, '登录', '2a65ee158c334c09ae8becf5ca3f7e5c', '客服在2020-10-21 14:05:18登录了系统，IP是127.0.0.1', '2a65ee158c334c09ae8becf5ca3f7e5c', 1603260318570, '2a65ee158c334c09ae8becf5ca3f7e5c', 1603260318570);
INSERT INTO `juncheng_security_admin_log` VALUES ('b35090759eb54250b060dfbeb45afd13', 0, '登录', 'admin', '管理员在2020-10-19 17:30:52登录了系统，IP是127.0.0.1', 'admin', 1603099852117, 'admin', 1603099852117);
INSERT INTO `juncheng_security_admin_log` VALUES ('b43fd3ec5b1b4c3ca2c45613d5f3659f', 0, '登录', 'admin', '管理员在2020-10-21 15:05:32登录了系统，IP是192.168.31.221', 'admin', 1603263932872, 'admin', 1603263932872);
INSERT INTO `juncheng_security_admin_log` VALUES ('b4e1cdb2a436407a9f5ff26ca243575d', 0, '登录', 'admin', '管理员在2020-12-04 16:41:41登录了系统，IP是192.168.31.220', 'admin', 1607071301323, 'admin', 1607071301323);
INSERT INTO `juncheng_security_admin_log` VALUES ('b5e9b323ef4b4d528b17eff5b418b423', 0, '登录', 'admin', '管理员在2020-10-20 16:29:15登录了系统，IP是127.0.0.1', 'admin', 1603182555514, 'admin', 1603182555514);
INSERT INTO `juncheng_security_admin_log` VALUES ('b74d1cfb694a4e328d4f53b9e252c733', 0, '登录', 'admin', '管理员在2020-10-21 09:46:10登录了系统，IP是127.0.0.1', 'admin', 1603244770433, 'admin', 1603244770433);
INSERT INTO `juncheng_security_admin_log` VALUES ('b89306d8462847888a97dacfba2d8415', 0, '登录', 'admin', '管理员在2020-10-20 18:17:50登录了系统，IP是127.0.0.1', 'admin', 1603189070371, 'admin', 1603189070371);
INSERT INTO `juncheng_security_admin_log` VALUES ('b9ef53d81f5c4ab9a3776c35b114c7da', 0, '登录', 'admin', '管理员在2020-12-24 09:56:20登录了系统，IP是127.0.0.1', '1', 1608774980853, '1', 1608774980853);
INSERT INTO `juncheng_security_admin_log` VALUES ('ba2a03a14c4d40b687aa07d1298bf3e8', 0, '登录', 'admin', '管理员在2020-11-11 14:10:04登录了系统，IP是192.168.31.215', 'admin', 1605075004643, 'admin', 1605075004643);
INSERT INTO `juncheng_security_admin_log` VALUES ('bbf65dcfd05145b99215ab52645c8363', 0, '登录', 'admin', '管理员在2021-01-05 09:48:06登录了系统，IP是127.0.0.1', '1', 1609811286753, '1', 1609811286753);
INSERT INTO `juncheng_security_admin_log` VALUES ('bc36332731d6413790c90dea37fb4778', 0, '登录', 'admin', '管理员在2020-11-11 10:23:05登录了系统，IP是192.168.31.215', 'admin', 1605061385790, 'admin', 1605061385790);
INSERT INTO `juncheng_security_admin_log` VALUES ('be5d99a378f546f3a210d8bff9dd3334', 0, '登录', 'admin', '管理员在2020-12-24 09:58:35登录了系统，IP是127.0.0.1', '1', 1608775115664, '1', 1608775115664);
INSERT INTO `juncheng_security_admin_log` VALUES ('bec16981a42b49d09661d0719ef84786', 0, '登录', 'admin', '管理员在2020-10-19 17:35:08登录了系统，IP是127.0.0.1', 'admin', 1603100108451, 'admin', 1603100108451);
INSERT INTO `juncheng_security_admin_log` VALUES ('bf835035fd7f48e48c1556a816ddadb0', 0, '登录', 'admin', '管理员在2020-11-24 12:42:46登录了系统，IP是192.168.31.215', 'admin', 1606192966926, 'admin', 1606192966926);
INSERT INTO `juncheng_security_admin_log` VALUES ('bfd5b9f796c748938fea12c6308dded1', 0, '登录', 'admin', '管理员在2020-12-08 18:50:59登录了系统，IP是127.0.0.1', 'admin', 1607424659280, 'admin', 1607424659280);
INSERT INTO `juncheng_security_admin_log` VALUES ('bffd9f1440b04acd914800bba01ce0e4', 0, '登录', 'admin', '管理员在2020-12-26 10:19:37登录了系统，IP是127.0.0.1', '1', 1608949177651, '1', 1608949177651);
INSERT INTO `juncheng_security_admin_log` VALUES ('c0966e0bd1f4481781229771d227eaa4', 0, '登录', '2a65ee158c334c09ae8becf5ca3f7e5c', '客服在2020-10-21 14:05:50登录了系统，IP是127.0.0.1', '2a65ee158c334c09ae8becf5ca3f7e5c', 1603260350064, '2a65ee158c334c09ae8becf5ca3f7e5c', 1603260350064);
INSERT INTO `juncheng_security_admin_log` VALUES ('c0cf88aa27fa4b96958690d885fe458f', 0, '登录', 'admin', '管理员在2020-12-08 17:01:32登录了系统，IP是192.168.31.220', '1', 1607418092787, '1', 1607418092787);
INSERT INTO `juncheng_security_admin_log` VALUES ('c10de335a11b4174a97b182aa5afeb04', 0, '登录', 'admin', '管理员在2021-01-11 14:12:43登录了系统，IP是113.227.196.144', '1', 1610345563345, '1', 1610345563345);
INSERT INTO `juncheng_security_admin_log` VALUES ('c12034c47b274de89c8dac0ac231a514', 0, '登录', 'admin', '管理员在2020-11-23 20:47:03登录了系统，IP是192.168.1.100', 'admin', 1606135623451, 'admin', 1606135623451);
INSERT INTO `juncheng_security_admin_log` VALUES ('c18741cb24314c2ea46fb9d77ba20166', 0, '登录', 'admin', '管理员在2021-01-05 09:53:23登录了系统，IP是127.0.0.1', '1', 1609811603691, '1', 1609811603691);
INSERT INTO `juncheng_security_admin_log` VALUES ('c20f040d17db450bb359cf5f255a9e9f', 0, '登录', 'admin', '管理员在2020-11-21 09:33:07登录了系统，IP是192.168.31.215', 'admin', 1605922387257, 'admin', 1605922387257);
INSERT INTO `juncheng_security_admin_log` VALUES ('c38b08b1eb584425b96584c6f54e040b', 0, '登录', '2a65ee158c334c09ae8becf5ca3f7e5c', '客服在2020-10-19 16:27:27登录了系统，IP是127.0.0.1', '2a65ee158c334c09ae8becf5ca3f7e5c', 1603096047652, '2a65ee158c334c09ae8becf5ca3f7e5c', 1603096047652);
INSERT INTO `juncheng_security_admin_log` VALUES ('c391a45dbae1494db5cc6cc5b86f947c', 0, '登录', 'admin', '管理员在2020-12-15 08:57:57登录了系统，IP是127.0.0.1', '1', 1607993877259, '1', 1607993877259);
INSERT INTO `juncheng_security_admin_log` VALUES ('c46c190843024ec280b3b7accba13ee4', 0, '登录', 'admin', '管理员在2020-10-20 18:14:20登录了系统，IP是127.0.0.1', 'admin', 1603188860072, 'admin', 1603188860072);
INSERT INTO `juncheng_security_admin_log` VALUES ('c4db15db4c8b45b9ab9429e889fa9652', 0, '登录', 'admin', '管理员在2020-11-23 22:05:29登录了系统，IP是192.168.1.100', 'admin', 1606140329703, 'admin', 1606140329703);
INSERT INTO `juncheng_security_admin_log` VALUES ('c4e3c16a622c4f85ae8ee824a6631a64', 0, '登录', 'admin', '管理员在2020-11-30 08:26:59登录了系统，IP是192.168.31.215', 'admin', 1606696019600, 'admin', 1606696019600);
INSERT INTO `juncheng_security_admin_log` VALUES ('c50c77aaf5cb440bb7d328c6e9e968fb', 0, '登录', 'admin', '管理员在2020-11-11 13:36:58登录了系统，IP是192.168.31.215', 'admin', 1605073018698, 'admin', 1605073018698);
INSERT INTO `juncheng_security_admin_log` VALUES ('c6348447b70a4cac986762a2b681ee2b', 0, '登录', 'admin', '管理员在2020-12-04 18:19:41登录了系统，IP是192.168.31.220', '1', 1607077181249, '1', 1607077181249);
INSERT INTO `juncheng_security_admin_log` VALUES ('c63f8b8d26944bea8b8df33a2ac5bfb9', 0, '登录', 'admin', '管理员在2020-12-24 13:31:58登录了系统，IP是127.0.0.1', '1', 1608787918384, '1', 1608787918384);
INSERT INTO `juncheng_security_admin_log` VALUES ('c6b4d83cd3204f40a7ab315050b6f239', 0, '登录', 'admin', '管理员在2020-12-24 09:57:04登录了系统，IP是127.0.0.1', '1', 1608775024666, '1', 1608775024666);
INSERT INTO `juncheng_security_admin_log` VALUES ('c7041a52971c46518441547ac85bcc97', 0, '登录', 'admin', '管理员在2020-11-11 14:23:06登录了系统，IP是192.168.31.215', 'admin', 1605075786670, 'admin', 1605075786670);
INSERT INTO `juncheng_security_admin_log` VALUES ('c81784728ae746ce9e8632670dd11d5f', 0, '登录', 'admin', '管理员在2020-11-23 17:56:04登录了系统，IP是192.168.31.215', 'admin', 1606125364831, 'admin', 1606125364831);
INSERT INTO `juncheng_security_admin_log` VALUES ('c8fd26fa59f5452a8dff18bc19f975b8', 0, '登录', 'admin', '管理员在2020-12-14 15:42:16登录了系统，IP是127.0.0.1', '1', 1607931736110, '1', 1607931736110);
INSERT INTO `juncheng_security_admin_log` VALUES ('c99f3ac5c2854c20b088290a69634d22', 0, '登录', 'admin', '管理员在2020-11-24 15:32:11登录了系统，IP是192.168.31.215', 'admin', 1606203131077, 'admin', 1606203131077);
INSERT INTO `juncheng_security_admin_log` VALUES ('c9ed27512c3340aaaf71de562c43cc70', 0, '登录', 'admin', '管理员在2020-10-19 18:05:48登录了系统，IP是127.0.0.1', 'admin', 1603101948299, 'admin', 1603101948299);
INSERT INTO `juncheng_security_admin_log` VALUES ('ca0d55d80e804bff97bd4b6545e58a2f', 0, '登录', '2a65ee158c334c09ae8becf5ca3f7e5c', '客服在2020-10-19 16:23:42登录了系统，IP是127.0.0.1', '2a65ee158c334c09ae8becf5ca3f7e5c', 1603095822586, '2a65ee158c334c09ae8becf5ca3f7e5c', 1603095822586);
INSERT INTO `juncheng_security_admin_log` VALUES ('cae311901173463a8c8cb3fd12318d7d', 0, '登录', 'admin', '管理员在2020-11-11 13:33:20登录了系统，IP是192.168.31.215', 'admin', 1605072800650, 'admin', 1605072800650);
INSERT INTO `juncheng_security_admin_log` VALUES ('cb3d5261f005490f9ae53168cad32004', 0, '登录', 'admin', '管理员在2020-11-24 11:30:06登录了系统，IP是192.168.31.215', 'admin', 1606188606421, 'admin', 1606188606421);
INSERT INTO `juncheng_security_admin_log` VALUES ('cbc26591c9654e97a09c95057723b9fd', 0, '登录', 'admin', '管理员在2020-12-14 11:19:39登录了系统，IP是127.0.0.1', '1', 1607915979221, '1', 1607915979221);
INSERT INTO `juncheng_security_admin_log` VALUES ('cc1b98e60e66427ca574169d57e8c4d9', 0, '登录', 'admin', '管理员在2020-10-19 17:44:56登录了系统，IP是127.0.0.1', 'admin', 1603100696435, 'admin', 1603100696435);
INSERT INTO `juncheng_security_admin_log` VALUES ('cc3d2c2af5b24c9b8483e66faad110e9', 0, '登录', 'admin', '管理员在2020-11-12 16:02:33登录了系统，IP是192.168.31.215', 'admin', 1605168153046, 'admin', 1605168153047);
INSERT INTO `juncheng_security_admin_log` VALUES ('ccd3066ac81248a19a699807a2d7c28d', 0, '登录', 'admin', '管理员在2020-10-19 17:19:02登录了系统，IP是127.0.0.1', 'admin', 1603099142215, 'admin', 1603099142215);
INSERT INTO `juncheng_security_admin_log` VALUES ('cd9754282dc64af39218904c5673fbe3', 0, '登录', 'admin', '管理员在2020-10-21 15:05:49登录了系统，IP是192.168.31.221', 'admin', 1603263949152, 'admin', 1603263949152);
INSERT INTO `juncheng_security_admin_log` VALUES ('cd9ffcff248e43cd9d8d56b53f4012da', 0, '登录', 'admin', '管理员在2020-11-12 10:16:48登录了系统，IP是192.168.31.215', 'admin', 1605147408053, 'admin', 1605147408053);
INSERT INTO `juncheng_security_admin_log` VALUES ('ce542c10ce914e2abc5aee67f919b83c', 0, '登录', '2a65ee158c334c09ae8becf5ca3f7e5c', '客服在2020-10-19 15:47:28，IP是127.0.0.1登录了系统', '2a65ee158c334c09ae8becf5ca3f7e5c', 1603093648543, '2a65ee158c334c09ae8becf5ca3f7e5c', 1603093648543);
INSERT INTO `juncheng_security_admin_log` VALUES ('cebac5f241874d05bea01a1d9b480aac', 0, '登录', 'admin', '管理员在2020-12-04 16:50:16登录了系统，IP是192.168.31.220', '1', 1607071816353, '1', 1607071816353);
INSERT INTO `juncheng_security_admin_log` VALUES ('cfae6ee3c1e4462c93ac387464c9a7a4', 0, '登录', 'admin', '管理员在2020-10-19 08:53:02，IP是127.0.0.1登录了系统', 'admin', 1603068782408, 'admin', 1603068782408);
INSERT INTO `juncheng_security_admin_log` VALUES ('cfeed5d0ca8a4faeb1daa4c96e8abba0', 0, '登录', 'admin', '管理员在2020-10-21 14:02:32登录了系统，IP是127.0.0.1', 'admin', 1603260152708, 'admin', 1603260152708);
INSERT INTO `juncheng_security_admin_log` VALUES ('d4067552f5fd4138ac89af8544507233', 0, '登录', 'admin', '管理员在2020-12-04 17:02:43登录了系统，IP是192.168.31.220', '1', 1607072563210, '1', 1607072563210);
INSERT INTO `juncheng_security_admin_log` VALUES ('d57f356afcb04687a362317782c0f5c8', 0, '登录', 'admin', '管理员在2020-11-26 13:28:31登录了系统，IP是192.168.31.118', 'admin', 1606368511212, 'admin', 1606368511212);
INSERT INTO `juncheng_security_admin_log` VALUES ('d62571266ec7434e9541a946bed2254f', 0, '登录', 'admin', '管理员在2020-11-12 09:14:10登录了系统，IP是192.168.31.215', 'admin', 1605143650611, 'admin', 1605143650611);
INSERT INTO `juncheng_security_admin_log` VALUES ('d96bb86a8c62489ba5cd50354e4cfc8d', 0, '登录', 'admin', '管理员在2020-11-24 16:34:56登录了系统，IP是192.168.31.215', 'admin', 1606206896680, 'admin', 1606206896680);
INSERT INTO `juncheng_security_admin_log` VALUES ('da6bbd475d8c4ef1becfc43b06ce3100', 0, '登录', 'admin', '管理员在2020-12-14 15:50:19登录了系统，IP是127.0.0.1', '1', 1607932219422, '1', 1607932219422);
INSERT INTO `juncheng_security_admin_log` VALUES ('db8e0c9c4d2f4247958de741b89b83b0', 0, '登录', 'admin', '管理员在2020-12-15 08:26:09登录了系统，IP是127.0.0.1', '1', 1607991969752, '1', 1607991969752);
INSERT INTO `juncheng_security_admin_log` VALUES ('dbda7a1ff92a4104ba78f26eb83d1f14', 0, '登录', 'admin', '管理员在2021-01-05 09:54:02登录了系统，IP是127.0.0.1', '1', 1609811642548, '1', 1609811642548);
INSERT INTO `juncheng_security_admin_log` VALUES ('dc6a67bb8ead444ebdb0a05f1c7619b9', 0, '登录', 'admin', '管理员在2020-11-11 10:08:50登录了系统，IP是192.168.31.215', 'admin', 1605060530199, 'admin', 1605060530199);
INSERT INTO `juncheng_security_admin_log` VALUES ('dca26ca112f34d159dacf7736ad0cfcf', 0, '登录', 'admin', '管理员在2020-10-19 17:26:38登录了系统，IP是127.0.0.1', 'admin', 1603099598893, 'admin', 1603099598893);
INSERT INTO `juncheng_security_admin_log` VALUES ('dd116e097e354ef6968a89335ddf125d', 0, '登录', 'admin', '管理员在2020-10-21 15:03:31登录了系统，IP是192.168.31.221', 'admin', 1603263811863, 'admin', 1603263811863);
INSERT INTO `juncheng_security_admin_log` VALUES ('ddf86213057844dc8e51c7ca9c5c5971', 0, '登录', 'admin', '管理员在2020-11-28 14:35:24登录了系统，IP是192.168.31.215', 'admin', 1606545324472, 'admin', 1606545324472);
INSERT INTO `juncheng_security_admin_log` VALUES ('df2d9ec42acd40e39aac2f0bc6d3c98d', 0, '登录', 'admin', '管理员在2020-11-23 17:54:25登录了系统，IP是192.168.31.215', 'admin', 1606125265775, 'admin', 1606125265775);
INSERT INTO `juncheng_security_admin_log` VALUES ('dfbee7ff67b241898aedf8f998dc27ba', 0, '登录', 'admin', '管理员在2020-11-11 12:49:02登录了系统，IP是192.168.31.215', 'admin', 1605070142713, 'admin', 1605070142713);
INSERT INTO `juncheng_security_admin_log` VALUES ('e10ddfc3d0e440328bdd5d8675f350a3', 0, '登录', 'admin', '管理员在2020-11-11 13:38:35登录了系统，IP是192.168.31.215', 'admin', 1605073115848, 'admin', 1605073115848);
INSERT INTO `juncheng_security_admin_log` VALUES ('e1f5c0fbd0184fde9b1c93efd68c40a2', 0, '登录', 'admin', '管理员在2020-12-04 17:50:09登录了系统，IP是192.168.31.220', 'admin', 1607075409526, 'admin', 1607075409526);
INSERT INTO `juncheng_security_admin_log` VALUES ('e23c0222c3474500a435501ed4c5e139', 0, '登录', 'admin', '管理员在2020-11-11 16:37:05登录了系统，IP是192.168.31.215', 'admin', 1605083825410, 'admin', 1605083825410);
INSERT INTO `juncheng_security_admin_log` VALUES ('e3fc248e11db4025bcb4991a6fcdd0b2', 0, '登录', 'admin', '管理员在2020-12-04 17:57:42登录了系统，IP是192.168.31.220', 'admin', 1607075862221, 'admin', 1607075862221);
INSERT INTO `juncheng_security_admin_log` VALUES ('e56563cb1b3548e29c5bdecdf60dbfaa', 0, '登录', 'admin', '管理员在2020-10-21 15:00:50登录了系统，IP是127.0.0.1', 'admin', 1603263650835, 'admin', 1603263650835);
INSERT INTO `juncheng_security_admin_log` VALUES ('e5ef29df50ab4effb1aa8c505fed438c', 0, '登录', 'admin', '管理员在2020-11-11 17:59:30登录了系统，IP是192.168.31.215', 'admin', 1605088770394, 'admin', 1605088770394);
INSERT INTO `juncheng_security_admin_log` VALUES ('e8c9927f9d7a4a5abdd573b03c8a3d4f', 0, '登录', 'admin', '管理员在2020-11-11 18:20:11登录了系统，IP是192.168.31.215', 'admin', 1605090011512, 'admin', 1605090011512);
INSERT INTO `juncheng_security_admin_log` VALUES ('e92e917805714ff8a4067ff31c4a7e8e', 0, '登录', 'admin', '管理员在2020-10-21 09:15:30登录了系统，IP是127.0.0.1', 'admin', 1603242930410, 'admin', 1603242930410);
INSERT INTO `juncheng_security_admin_log` VALUES ('ea8f0f36aab74b7db46e09b836c9bcaa', 0, '登录', 'admin', '管理员在2020-11-23 11:40:39登录了系统，IP是192.168.31.215', 'admin', 1606102839272, 'admin', 1606102839272);
INSERT INTO `juncheng_security_admin_log` VALUES ('eb36b142c5da45909e32d0970e147aa4', 0, '登录', 'admin', '管理员在2020-11-24 16:10:17登录了系统，IP是192.168.31.215', 'admin', 1606205417130, 'admin', 1606205417130);
INSERT INTO `juncheng_security_admin_log` VALUES ('ec5c341002b84c8b88ee80f19da55400', 0, '登录', 'admin', '管理员在2020-12-14 15:55:17登录了系统，IP是127.0.0.1', '1', 1607932517521, '1', 1607932517521);
INSERT INTO `juncheng_security_admin_log` VALUES ('ec5c7dad4c874103889d78bf61d90d13', 0, '登录', 'admin', '管理员在2020-10-20 17:51:16登录了系统，IP是127.0.0.1', 'admin', 1603187476359, 'admin', 1603187476359);
INSERT INTO `juncheng_security_admin_log` VALUES ('ed41767af91644aa8a2dca82ecfe1b6d', 0, '登录', 'admin', '管理员在2020-11-12 16:07:09登录了系统，IP是192.168.31.215', 'admin', 1605168429655, 'admin', 1605168429655);
INSERT INTO `juncheng_security_admin_log` VALUES ('eddf95e6196348ec9cd0ef80789067e7', 0, '登录', 'admin', '管理员在2020-11-26 14:25:19登录了系统，IP是192.168.31.118', 'admin', 1606371919697, 'admin', 1606371919697);
INSERT INTO `juncheng_security_admin_log` VALUES ('ee088c5b99c34e6b84598593c68bf805', 0, '登录', 'admin', '管理员在2020-10-19 17:29:41登录了系统，IP是127.0.0.1', 'admin', 1603099781598, 'admin', 1603099781598);
INSERT INTO `juncheng_security_admin_log` VALUES ('ee5d12f2bc654719b30e4e06255a6f36', 0, '登录', 'admin', '管理员在2020-10-21 15:09:01登录了系统，IP是192.168.31.221', 'admin', 1603264141709, 'admin', 1603264141709);
INSERT INTO `juncheng_security_admin_log` VALUES ('ef3d0dd773db40ea85a66e242f138ce3', 0, '登录', 'admin', '管理员在2021-01-05 09:54:32登录了系统，IP是127.0.0.1', '1', 1609811672708, '1', 1609811672708);
INSERT INTO `juncheng_security_admin_log` VALUES ('ef551be2cf1a4cd8baf4c859b204692c', 0, '登录', 'admin', '管理员在2020-11-10 13:28:41登录了系统，IP是127.0.0.1', 'admin', 1604986121676, 'admin', 1604986121676);
INSERT INTO `juncheng_security_admin_log` VALUES ('efe761c19d3e42c38cd410c4c9045f0e', 0, '登录', 'admin', '管理员在2020-12-04 16:46:38登录了系统，IP是192.168.31.220', '1', 1607071598972, '1', 1607071598972);
INSERT INTO `juncheng_security_admin_log` VALUES ('f043592ef7624a6aae22eed6a1757f99', 0, '登录', 'admin', '管理员在2020-11-24 08:45:52登录了系统，IP是192.168.31.215', 'admin', 1606178752420, 'admin', 1606178752420);
INSERT INTO `juncheng_security_admin_log` VALUES ('f055caa7bd734440be5a00b628503578', 0, '登录', 'admin', '管理员在2020-10-20 17:54:10登录了系统，IP是127.0.0.1', 'admin', 1603187650743, 'admin', 1603187650743);
INSERT INTO `juncheng_security_admin_log` VALUES ('f0620f5d94f4461983a45448fbbac4c8', 0, '登录', 'admin', '管理员在2020-12-25 17:27:59登录了系统，IP是127.0.0.1', '1', 1608888479723, '1', 1608888479723);
INSERT INTO `juncheng_security_admin_log` VALUES ('f1e2c00e1eba47418dbcbefe876c8b76', 0, '登录', 'admin', '管理员在2020-10-20 12:50:55登录了系统，IP是127.0.0.1', 'admin', 1603169455362, 'admin', 1603169455362);
INSERT INTO `juncheng_security_admin_log` VALUES ('f325df02456145dda63baa91c534f891', 0, '登录', 'admin', '管理员在2020-10-19 17:16:55登录了系统，IP是127.0.0.1', 'admin', 1603099015025, 'admin', 1603099015025);
INSERT INTO `juncheng_security_admin_log` VALUES ('f355900f0bc84655b1f710916b217ec4', 0, '登录', 'admin', '管理员在2021-01-11 17:48:10登录了系统，IP是113.227.196.144', '1', 1610358490940, '1', 1610358490940);
INSERT INTO `juncheng_security_admin_log` VALUES ('f3c1033c12594731a35ebe92e891993f', 0, '登录', 'admin', '管理员在2020-11-12 11:48:26登录了系统，IP是192.168.31.215', 'admin', 1605152906712, 'admin', 1605152906712);
INSERT INTO `juncheng_security_admin_log` VALUES ('f4466a02a10f4f9c920b6c24c3cc54f5', 0, '登录', 'admin', '管理员在2020-11-26 13:31:06登录了系统，IP是192.168.31.118', 'admin', 1606368666446, 'admin', 1606368666446);
INSERT INTO `juncheng_security_admin_log` VALUES ('f5fa0f9f7f24489192734df5f501ec84', 0, '登录', 'admin', '管理员在2020-12-15 10:37:07登录了系统，IP是127.0.0.1', '1', 1607999827834, '1', 1607999827834);
INSERT INTO `juncheng_security_admin_log` VALUES ('f64e0d4506504d84b47e37642f01d1c6', 0, '登录', 'admin', '管理员在2020-12-23 14:29:33登录了系统，IP是127.0.0.1', '1', 1608704973756, '1', 1608704973756);
INSERT INTO `juncheng_security_admin_log` VALUES ('f6829476004c47cf85e4c7c85dc18480', 0, '登录', 'admin', '管理员在2021-01-05 11:05:14登录了系统，IP是127.0.0.1', '1', 1609815914223, '1', 1609815914223);
INSERT INTO `juncheng_security_admin_log` VALUES ('f6f42046011347ae89bf683edd0d628c', 0, '登录', 'admin', '管理员在2020-11-09 11:38:16登录了系统，IP是127.0.0.1', 'admin', 1604893096221, 'admin', 1604893096221);
INSERT INTO `juncheng_security_admin_log` VALUES ('f72a6e12050f4493b87a647181f3aa6d', 0, '登录', 'admin', '管理员在2020-11-24 08:01:15登录了系统，IP是192.168.31.215', 'admin', 1606176075420, 'admin', 1606176075420);
INSERT INTO `juncheng_security_admin_log` VALUES ('f7389aecd4374eb6974bf4eec831afee', 0, '登录', 'admin', '管理员在2020-12-24 10:02:01登录了系统，IP是127.0.0.1', '1', 1608775321799, '1', 1608775321799);
INSERT INTO `juncheng_security_admin_log` VALUES ('f7541c4021834161ae18fe41dee7f514', 0, '登录', 'admin', '管理员在2020-11-23 08:38:59登录了系统，IP是192.168.31.215', 'admin', 1606091939828, 'admin', 1606091939828);
INSERT INTO `juncheng_security_admin_log` VALUES ('f79250d238ef4af08186a0f71dbbc425', 0, '登录', 'admin', '管理员在2020-11-24 14:47:56登录了系统，IP是192.168.31.215', 'admin', 1606200476707, 'admin', 1606200476707);
INSERT INTO `juncheng_security_admin_log` VALUES ('f7efa23e71884734a16abef0c7a6e56f', 0, '登录', 'admin', '管理员在2020-12-04 17:55:02登录了系统，IP是192.168.31.220', 'admin', 1607075702022, 'admin', 1607075702022);
INSERT INTO `juncheng_security_admin_log` VALUES ('fb05f1a94b574a94a5e3f4d8a22c7739', 0, '登录', 'admin', '管理员在2020-11-11 10:16:47登录了系统，IP是192.168.31.215', 'admin', 1605061007172, 'admin', 1605061007172);
INSERT INTO `juncheng_security_admin_log` VALUES ('fd831777fc31426da6b074f392255658', 0, '登录', 'admin', '管理员在2020-11-12 13:43:23登录了系统，IP是192.168.31.215', 'admin', 1605159803973, 'admin', 1605159803973);
INSERT INTO `juncheng_security_admin_log` VALUES ('fdc945134ce747999c4bff0b59a95054', 0, '登录', 'admin', '管理员在2020-10-20 17:31:19登录了系统，IP是127.0.0.1', 'admin', 1603186279822, 'admin', 1603186279822);
COMMIT;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员角色表 ';

-- ----------------------------
-- Records of juncheng_security_admin_role
-- ----------------------------
BEGIN;
INSERT INTO `juncheng_security_admin_role` VALUES ('58f35e1e99bd48df82c5a2a8ea3448b4', '454fdd1b9168446da33ac363b381cbab', '1069466f3a45448b81930375f3399e9b', 'admin', 1602810399316, 'admin', 1602810399316);
INSERT INTO `juncheng_security_admin_role` VALUES ('594d2c21b4bb46478be4c2aaf86edc10', '2a65ee158c334c09ae8becf5ca3f7e5c', 'a349163cbfa3431297ef180e027cfba0', 'admin', 1603093674042, 'admin', 1603093674042);
INSERT INTO `juncheng_security_admin_role` VALUES ('5ec9c29d2f784a2eb0199c2ee43630a0', '454fdd1b9168446da33ac363b381cbab', 'f74960337ec044898fcdd02d23366ef9', 'admin', 1602810429396, 'admin', 1602810429396);
INSERT INTO `juncheng_security_admin_role` VALUES ('9d0f244a90bd4254895322d7fefd2aaa', '9eaae11bac4b4948ab60ab160765aca8', '26bd74159f784aef94834ad2d2dd1eab', 'admin', 1607076985119, 'admin', 1607076985119);
INSERT INTO `juncheng_security_admin_role` VALUES ('bd99e01cd68e48579224f0c4d3e9ba4d', '454fdd1b9168446da33ac363b381cbab', '1069466f3a45448b81930375f3399e9b', 'admin', 1602810413921, 'admin', 1602810413921);
INSERT INTO `juncheng_security_admin_role` VALUES ('e4957c3ea69e464eb2ab9b126e1e2d82', '2a65ee158c334c09ae8becf5ca3f7e5c', '26bd74159f784aef94834ad2d2dd1eab', 'admin', 1603093683322, 'admin', 1603093683322);
COMMIT;

-- ----------------------------
-- Table structure for juncheng_security_role
-- ----------------------------
DROP TABLE IF EXISTS `juncheng_security_role`;
CREATE TABLE `juncheng_security_role` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_user` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `last_modify_user` varchar(32) NOT NULL COMMENT '最后更新人',
  `last_modify_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表 ';

-- ----------------------------
-- Records of juncheng_security_role
-- ----------------------------
BEGIN;
INSERT INTO `juncheng_security_role` VALUES ('26bd74159f784aef94834ad2d2dd1eab', '客服', '客服', 'admin', 1603093534458, 'admin', 1603093534458);
INSERT INTO `juncheng_security_role` VALUES ('a349163cbfa3431297ef180e027cfba0', '财务', NULL, 'admin', 1603093548717, 'admin', 1603093548717);
COMMIT;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表 ';

-- ----------------------------
-- Records of juncheng_security_role_permission
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for juncheng_security_setting
-- ----------------------------
DROP TABLE IF EXISTS `juncheng_security_setting`;
CREATE TABLE `juncheng_security_setting` (
  `id` varchar(32) NOT NULL,
  `login_log_status` smallint(1) DEFAULT '0' COMMENT '是否记录登录日志',
  `login_error_count` int(4) DEFAULT '0' COMMENT '登录错误锁定次数',
  `login_error_time` int(4) DEFAULT '0' COMMENT '登录错误锁定时间(单位分钟)',
  `login_error_status` smallint(1) DEFAULT '0' COMMENT '是否开启错误次数',
  `login_not_ip_status` smallint(1) DEFAULT '0' COMMENT '是否开启异地登录',
  `login_not_ip_notice` smallint(1) DEFAULT '0' COMMENT '异地登录是否提醒',
  `last_modify_time` bigint(13) DEFAULT '0' COMMENT '最后修改时间',
  `last_modify_user` varchar(32) DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='安全设置';

-- ----------------------------
-- Records of juncheng_security_setting
-- ----------------------------
BEGIN;
INSERT INTO `juncheng_security_setting` VALUES ('admin', 1, 2, 5, 0, 0, 0, 1607071621565, 'admin');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
