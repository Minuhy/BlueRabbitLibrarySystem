/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : br_library_system

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 09/03/2023 02:16:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for administrator
-- ----------------------------
DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator`  (
  `AdminID` int(11) NOT NULL,
  `AdminName` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Account` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ifsuper` tinyint(1) NOT NULL,
  PRIMARY KEY (`Account`) USING BTREE,
  UNIQUE INDEX `Account`(`Account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of administrator
-- ----------------------------
INSERT INTO `administrator` VALUES (1, '小猫', '10000', 'b7a782741f667201b54880c925faec4b', 1);
INSERT INTO `administrator` VALUES (2, '小狗', '10001', 'e10adc3949ba59abbe56e057f20f883e', 0);
INSERT INTO `administrator` VALUES (3, '小兔', '10002', '9103c8c82514f39d8360c7430c4ee557', 0);
INSERT INTO `administrator` VALUES (4, '小鼠', '10003', 'f5dffc111454b227fbcdf36178dfe6ac', 0);
INSERT INTO `administrator` VALUES (5, '小可', '10004', 'd783823cc6284b929c2cd8df2167d212', 0);
INSERT INTO `administrator` VALUES (6, '笑笑', '10005', '6eb887126d24e8f1cd8ad5033482c781', 0);
INSERT INTO `administrator` VALUES (17, '琳琳', '10017', '6eb887126d24e8f1cd8ad5033482c781', 0);
INSERT INTO `administrator` VALUES (27, '小鱼', '10027', '6eb887126d24e8f1cd8ad5033482c781', 0);
INSERT INTO `administrator` VALUES (0, '管理员', 'admin', 'd41d8cd98f00b204e9800998ecf8427e', 1);

-- ----------------------------
-- Table structure for books_info
-- ----------------------------
DROP TABLE IF EXISTS `books_info`;
CREATE TABLE `books_info`  (
  `BookName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书名',
  `SumQuantity` int(11) NOT NULL COMMENT '总册数',
  `Quantity` int(11) NOT NULL COMMENT '剩余册数',
  `LendTime` int(11) NOT NULL COMMENT '借出次数',
  `BookID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书刊编号',
  `BookBarcode` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书刊条码',
  `BookClassify` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书刊分类',
  `BookThem` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主题词',
  `Author` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `Publisher` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出版社',
  `PublishTime` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出版版次',
  `PublishDate` date NULL DEFAULT NULL COMMENT '出版日期',
  `BookType` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '馆藏分类',
  `Stack` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所在书室',
  `BookShelf` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所在书架',
  `Price` float NULL DEFAULT NULL COMMENT '价格',
  `ContentText` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `Remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `BookPage` int(11) NULL DEFAULT NULL COMMENT '页数',
  `WordsNumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字数',
  PRIMARY KEY (`BookID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '图书信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of books_info
-- ----------------------------
INSERT INTO `books_info` VALUES ('毛泽东思想和中国特色社会主义理论体系概论', 5, 2, 5, '00001', '9787040494815', '思想、理论、历史', '毛泽东、中国特色社会主义', '高英', '高等教育出版社', '第6版', '2018-09-01', '图书', '第一书室', '第一书架', 25, '中国特色社会主义的历史', '大二教科书', 313, '260千字');
INSERT INTO `books_info` VALUES ('浮生物语', 17, 3, 27, '000010', '9367566799036', '小说', '架空、玄幻', '裟椤双树', '上海交通出版社', '第5版', '2018-08-16', '图书', '第五书室', '第四书架', 29.99, '轻快幽默的妖怪历险记', '感动、幻想、提升生活乐趣', 487, '393千字');
INSERT INTO `books_info` VALUES ('意林2018年合订本', 90, 54, 66, '00002', '9771007384158', '杂志、作文、青春文学', '作文，小小姐', '高英', '意林杂志社', '第1版', '2019-01-01', '期刊', '第三书室', '第二书架', 29.8, '有助于中学生作文提高，解决中学生烦恼的期刊', '期刊合订本', 744, '540千字');
INSERT INTO `books_info` VALUES ('新编刑法罪名适用指南', 8, 7, 12, '00003', '9771557384236', '法律', '刑法 罪名 法律', '熊选国', '人民法院出版', '第1版', '2017-05-01', '期刊', '第三书室', '第二书架', 9.8, '法律刑法的了解', '期刊合订本', 264, '54千字');
INSERT INTO `books_info` VALUES ('c++程序设计(特别版)', 4, 3, 12, '00004', '9559338384236', '自动化技术、计算机技术', ' c++ 程序设计 软件工程', 'Special', '人民交通出版社', '第2版', '2018-09-03', '图书', '第四书室', '第一书架', 34.6, 'C++语言的了解与掌握', '大一教科书', 504, '344千字');
INSERT INTO `books_info` VALUES ('英语专业四级备考', 15, 3, 26, '00005', '9534457984236', '美洲诸语言', '四级 英语', '盛萧', '地质出版社', '第5版', '2019-02-23', '图书', '第四书室', '第三书架', 26, '备考四级', '大学英语四级考试提炼', 198, '194千字');
INSERT INTO `books_info` VALUES ('外国法律史研究', 10, 7, 9, '00006', '7715534569036', '法律', '刑法 罪名 法律', '何勤华', '中国政法大学出版社', '第3版', '2018-09-01', '图书', '第三书室', '第二书架', 30.8, '法律刑法的了解', '法律刑法的了解', 334, '670千字');
INSERT INTO `books_info` VALUES ('编程黑马真言', 13, 9, 8, '00007', '4563552169036', '自动化技术、计算机技术', '软件工具、工具软件', '王轶男', '人民交通出版社', '第1版', '2018-04-24', '图书', '第四书室', '第二书架', 35, '计算机语言的发展历程', '编程语言的拓展', 264, '145千字');
INSERT INTO `books_info` VALUES ('默读', 13, 0, 25, '00008', '2453466799036', '小说', '刑侦 犯罪', 'priest', '人民交通出版社', '第1版', '2018-05-16', '图书', '第五书室', '第三书架', 36.8, '救赎，烧脑，破案', '提升推理能力', 564, '445千字');
INSERT INTO `books_info` VALUES ('毛泽东理论概述', 9, 2, 7, '00009', '4652377494815', '思想、理论、历史', '毛泽东、毛泽东理论、中国特色社会主义', '教育部社会科学研究所', '高等教育出版社', '第7版', '2019-04-11', '图书', '第一书室', '第一书架', 32, '中国特色社会主义的历史、毛泽东理论的发展历程', '毛泽东理论', 453, '540千字');
INSERT INTO `books_info` VALUES ('考研数学高等数学辅导讲义·基础篇', 12, 12, 0, '00011', '9787576404319', '考研', '讲义、高数、数学', '汤家凤', '中国政法大学出版社', '第1版', '2023-03-08', '图书', '第一书室', '第二书架', 68, '一本受大学生喜欢的考研高数辅导书。', '无', 126, '205千字');
INSERT INTO `books_info` VALUES ('毛泽东思想和中国特色社会主义理论体系概论', 5, 2, 5, '00071', '9787040494815', '思想、理论、历史', '毛泽东、中国特色社会主义', '高英', '高等教育出版社', '第6版', '2018-09-01', '图书', '第一书室', '第一书架', 25, '中国特色社会主义的历史', '大二教科书', 313, '260千字');
INSERT INTO `books_info` VALUES ('意林2018年合订本', 90, 54, 66, '00702', '9771007384158', '杂志、作文、青春文学', '作文，小小姐', '高英', '意林杂志社', '第1版', '2019-01-01', '期刊', '第三书室', '第二书架', 29.8, '有助于中学生作文提高，解决中学生烦恼的期刊', '期刊合订本', 744, '540千字');

-- ----------------------------
-- Table structure for reader
-- ----------------------------
DROP TABLE IF EXISTS `reader`;
CREATE TABLE `reader`  (
  `ReaderID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `ReaderName` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `Apart` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学院',
  `Sex` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '男',
  `Class` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级',
  `TelNo` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  PRIMARY KEY (`ReaderID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of reader
-- ----------------------------
INSERT INTO `reader` VALUES ('5056', '田田', '信息学院', '男', '6233', '00000011');
INSERT INTO `reader` VALUES ('5156', '多多', '体育学院', '男', '6345', '00000012');
INSERT INTO `reader` VALUES ('5256', '糖糖', '外国语学院', '女', '6453', '00000013');
INSERT INTO `reader` VALUES ('5356', '悠悠', '音乐舞蹈学院', '男', '6571', '00000014');
INSERT INTO `reader` VALUES ('5456', '东东', '美术与设计学院', '女', '6655', '00000015');
INSERT INTO `reader` VALUES ('5556', '旺旺', '信息学院', '女', '6259', '00000016');
INSERT INTO `reader` VALUES ('5656', '青青', '法学院', '男', '6782', '00000017');
INSERT INTO `reader` VALUES ('5756', '菲菲', '教育学院', '女', '6890', '00000018');
INSERT INTO `reader` VALUES ('5856', '吉吉', '文学院', '女', '6934', '00000019');
INSERT INTO `reader` VALUES ('5956', '晨晨', '商学院', '男', '6075', '00000020');
INSERT INTO `reader` VALUES ('5966', '贝贝', '数学与金融院', '男', '6137', '00000021');
INSERT INTO `reader` VALUES ('5976', '乐乐', '马克思主义学院', '女', '6953', '00000022');
INSERT INTO `reader` VALUES ('5986', '盼盼', '体育学院', '男', '6370', '00000023');

-- ----------------------------
-- Table structure for t_borrow
-- ----------------------------
DROP TABLE IF EXISTS `t_borrow`;
CREATE TABLE `t_borrow`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '借阅ID',
  `reader_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '读者ID',
  `book_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书ID',
  `borrow_admin_id` int(11) NOT NULL COMMENT '借出操作者',
  `return_admin_id` int(11) NULL DEFAULT NULL COMMENT '归还操作者',
  `book_number` int(10) UNSIGNED NOT NULL DEFAULT 1 COMMENT '借出数量',
  `duration` bigint(20) NOT NULL COMMENT '借出时长，借阅时间加这个就是应该要归还的时间',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '图书状态：0正常，1破碎，2破损严重，3丢失',
  `penalty` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '罚金',
  `create_timestamp` bigint(20) UNSIGNED NOT NULL COMMENT '借阅时间',
  `update_timestamp` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '修改时间',
  `return_timestamp` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '归还时间，0表示未归还',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_borrow
-- ----------------------------
INSERT INTO `t_borrow` VALUES (1, '5056', '00001', 1, 1, 1, 6666, 0, '0', 32131231, 0, 56);
INSERT INTO `t_borrow` VALUES (2, '5056', '00001', 1, 1, 11, 46465, 0, '0', 234523455, 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
