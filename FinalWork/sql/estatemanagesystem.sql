/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80036
 Source Host           : localhost:3306
 Source Schema         : estatemanagesystem

 Target Server Type    : MySQL
 Target Server Version : 80036
 File Encoding         : 65001

 Date: 11/12/2024 17:19:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for month_datas
-- ----------------------------
DROP TABLE IF EXISTS `month_datas`;
CREATE TABLE `month_datas`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `year` int(0) NOT NULL,
  `month` int(0) NOT NULL,
  `oid` int(0) NOT NULL,
  `water_price` decimal(10, 2) NOT NULL,
  `water_consumption` decimal(10, 2) NOT NULL,
  `electricity_price` decimal(10, 2) NOT NULL,
  `electricity_consumption` decimal(10, 2) NOT NULL,
  `gas_price` decimal(10, 2) NOT NULL,
  `gas_consumption` decimal(10, 2) NOT NULL,
  `heater_price` decimal(10, 2) NOT NULL,
  `heater_consumption` decimal(10, 2) NOT NULL,
  `television_consumption` decimal(10, 2) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `year`(`year`, `month`, `oid`) USING BTREE,
  INDEX `oid_`(`oid`) USING BTREE,
  CONSTRAINT `oid_` FOREIGN KEY (`oid`) REFERENCES `owners` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of month_datas
-- ----------------------------
INSERT INTO `month_datas` VALUES (1, 2024, 12, 3, 0.00, 24.00, 0.00, 50.20, 0.00, 25.10, 0.00, 0.00, 23.00);
INSERT INTO `month_datas` VALUES (2, 2024, 11, 3, 0.00, 13.00, 0.00, 50.20, 0.00, 25.10, 0.00, 0.00, 23.00);
INSERT INTO `month_datas` VALUES (4, 2024, 10, 3, 0.00, 12.00, 0.00, 50.20, 0.00, 25.10, 0.00, 0.00, 23.00);
INSERT INTO `month_datas` VALUES (5, 2024, 2, 4, 5.80, 10.20, 0.67, 10.20, 3.30, 10.20, 24.00, 10.40, 23.00);

-- ----------------------------
-- Table structure for owner_payments
-- ----------------------------
DROP TABLE IF EXISTS `owner_payments`;
CREATE TABLE `owner_payments`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `pid` int(0) NOT NULL,
  `payment_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `installments` int(0) NOT NULL,
  `payed_installments` int(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pid`(`pid`) USING BTREE,
  CONSTRAINT `pid` FOREIGN KEY (`pid`) REFERENCES `payments` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of owner_payments
-- ----------------------------
INSERT INTO `owner_payments` VALUES (2, 2, '2', 3, 1);
INSERT INTO `owner_payments` VALUES (5, 4, '2', 3, 2);
INSERT INTO `owner_payments` VALUES (6, 6, '2', 6, 6);

-- ----------------------------
-- Table structure for owners
-- ----------------------------
DROP TABLE IF EXISTS `owners`;
CREATE TABLE `owners`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `id_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `building_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `floor_number` int(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `working_unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `telephone_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `area` decimal(10, 2) NOT NULL,
  `uid` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `uid` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of owners
-- ----------------------------
INSERT INTO `owners` VALUES (3, '1234567', '6', 4, 'owner2', 'new unit', '1325465', 50.00, NULL);
INSERT INTO `owners` VALUES (4, '123456', '5', 4, 'owner2', 'null', '1325465', 50.00, 3);
INSERT INTO `owners` VALUES (5, '123', '1', 1, '123', 'new work', '32413421', 150.00, 4);

-- ----------------------------
-- Table structure for payments
-- ----------------------------
DROP TABLE IF EXISTS `payments`;
CREATE TABLE `payments`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `oid` int(0) NOT NULL,
  `year` int(0) NOT NULL,
  `month` int(0) NOT NULL,
  `is_payed` tinyint(1) NOT NULL,
  `water_cost` decimal(10, 2) NOT NULL,
  `electricity_cost` decimal(10, 2) NOT NULL,
  `gas_cost` decimal(10, 2) NOT NULL,
  `heater_cost` decimal(10, 2) NOT NULL,
  `estate_cost` decimal(10, 2) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `oid`(`oid`) USING BTREE,
  CONSTRAINT `oid` FOREIGN KEY (`oid`) REFERENCES `owners` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payments
-- ----------------------------
INSERT INTO `payments` VALUES (2, 3, 2024, 12, 0, 12.50, 5.80, 5.30, 0.00, 256.30);
INSERT INTO `payments` VALUES (4, 3, 2024, 11, 1, 15.60, 52.60, 56.10, 0.00, 256.30);
INSERT INTO `payments` VALUES (5, 3, 2024, 10, 0, 0.00, 0.00, 0.00, 0.00, 500.00);
INSERT INTO `payments` VALUES (6, 4, 2024, 2, 1, 59.16, 6.83, 33.66, 249.60, 500.00);

-- ----------------------------
-- Table structure for role_keys
-- ----------------------------
DROP TABLE IF EXISTS `role_keys`;
CREATE TABLE `role_keys`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `role_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_keys
-- ----------------------------
INSERT INTO `role_keys` VALUES (1, 'ABC');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `uid` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'test', 'admin', '12345', '122', 1);
INSERT INTO `users` VALUES (2, 'test001', 'admin', 'F67EBE1CA013FCD54BCB19A54466D3AE', '624644F7-5869-40E1-B2F6-F8E19C0024CB', 0);
INSERT INTO `users` VALUES (3, 'test002', 'user', '7DB87A638AA97EB022A225788D4BAA15', '0C676533-5D1B-4D4D-BE95-EECF36FF20DC', 0);
INSERT INTO `users` VALUES (4, 'test003', 'user', 'BAEC0B3EE3F1F5EEA0A4A2FFD8BE221C', '8700FCD8-5AA1-4D0B-A0F2-1C3D823FA497', 0);

SET FOREIGN_KEY_CHECKS = 1;
