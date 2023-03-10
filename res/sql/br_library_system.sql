/*

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : br_library_system

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 10/03/2023 19:01:43
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
INSERT INTO `administrator` VALUES (1, 'Сè', '10000', 'b7a782741f667201b54880c925faec4b', 1);
INSERT INTO `administrator` VALUES (2, 'С��', '10001', 'e10adc3949ba59abbe56e057f20f883e', 0);
INSERT INTO `administrator` VALUES (3, 'С��', '10002', '9103c8c82514f39d8360c7430c4ee557', 0);
INSERT INTO `administrator` VALUES (4, 'С��', '10003', 'f5dffc111454b227fbcdf36178dfe6ac', 0);
INSERT INTO `administrator` VALUES (5, 'С��', '10004', 'd783823cc6284b929c2cd8df2167d212', 0);
INSERT INTO `administrator` VALUES (6, 'ЦЦ', '10005', '6eb887126d24e8f1cd8ad5033482c781', 0);
INSERT INTO `administrator` VALUES (17, '����', '10017', '6eb887126d24e8f1cd8ad5033482c781', 0);
INSERT INTO `administrator` VALUES (27, 'С��', '10027', '6eb887126d24e8f1cd8ad5033482c781', 0);
INSERT INTO `administrator` VALUES (0, '����Ա', 'admin', 'd41d8cd98f00b204e9800998ecf8427e', 1);

-- ----------------------------
-- Table structure for books_info
-- ----------------------------
DROP TABLE IF EXISTS `books_info`;
CREATE TABLE `books_info`  (
  `BookName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '����',
  `SumQuantity` int(11) UNSIGNED NOT NULL COMMENT '�ܲ���',
  `Quantity` int(11) UNSIGNED NOT NULL COMMENT 'ʣ�����',
  `LendTime` int(11) UNSIGNED NOT NULL COMMENT '�������',
  `BookID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '�鿯���',
  `BookBarcode` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '�鿯����',
  `BookClassify` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '�鿯����',
  `BookThem` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '�����',
  `Author` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '����',
  `Publisher` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '������',
  `PublishTime` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '������',
  `PublishDate` date NULL DEFAULT NULL COMMENT '��������',
  `BookType` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '�ݲط���',
  `Stack` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '��������',
  `BookShelf` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '�������',
  `Price` float NULL DEFAULT NULL COMMENT '�۸�',
  `ContentText` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '���',
  `Remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '��ע',
  `BookPage` int(11) NULL DEFAULT NULL COMMENT 'ҳ��',
  `WordsNumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '����',
  PRIMARY KEY (`BookID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'ͼ����Ϣ��' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of books_info
-- ----------------------------
INSERT INTO `books_info` VALUES ('ë��˼����й���ɫ�������������ϵ����', 5, 1, 7, '00001', '9787040494815', '˼�롢���ۡ���ʷ', 'ë�󶫡��й���ɫ�������', '��Ӣ', '�ߵȽ���������', '��6��', '2018-09-01', 'ͼ��', '��һ����', '��һ���', 25, '�й���ɫ����������ʷ', '����̿���', 313, '260ǧ��');
INSERT INTO `books_info` VALUES ('��������', 17, 0, 30, '000010', '9367566799036', 'С˵', '�ܿա�����', '���˫��', '�Ϻ���ͨ������', '��5��', '2018-08-16', 'ͼ��', '��������', '�������', 29.99, '�����Ĭ���������ռ�', '�ж������롢����������Ȥ', 487, '393ǧ��');
INSERT INTO `books_info` VALUES ('����2018��϶���', 90, 52, 68, '00002', '9771007384158', '��־�����ġ��ഺ��ѧ', '���ģ�СС��', '��Ӣ', '������־��', '��1��', '2019-01-01', '�ڿ�', '��������', '�ڶ����', 29.8, '��������ѧ��������ߣ������ѧ�����յ��ڿ�', '�ڿ��϶���', 744, '540ǧ��');
INSERT INTO `books_info` VALUES ('�±��̷���������ָ��', 8, 7, 12, '00003', '9771557384236', '����', '�̷� ���� ����', '��ѡ��', '����Ժ����', '��1��', '2017-05-01', '�ڿ�', '��������', '�ڶ����', 9.8, '�����̷����˽�', '�ڿ��϶���', 264, '54ǧ��');
INSERT INTO `books_info` VALUES ('c++�������(�ر��)', 4, 2, 13, '00004', '9559338384236', '�Զ������������������', ' c++ ������� �������', 'Special', '����ͨ������', '��2��', '2018-09-03', 'ͼ��', '��������', '��һ���', 34.6, 'C++���Ե��˽�������', '��һ�̿���', 504, '344ǧ��');
INSERT INTO `books_info` VALUES ('Ӣ��רҵ�ļ�����', 15, 3, 26, '00005', '9534457984236', '����������', '�ļ� Ӣ��', 'ʢ��', '���ʳ�����', '��5��', '2019-02-23', 'ͼ��', '��������', '�������', 26, '�����ļ�', '��ѧӢ���ļ���������', 198, '194ǧ��');
INSERT INTO `books_info` VALUES ('�������ʷ�о�', 10, 7, 9, '00006', '7715534569036', '����', '�̷� ���� ����', '���ڻ�', '�й�������ѧ������', '��3��', '2018-09-01', 'ͼ��', '��������', '�ڶ����', 30.8, '�����̷����˽�', '�����̷����˽�', 334, '670ǧ��');
INSERT INTO `books_info` VALUES ('��̺�������', 13, 7, 10, '00007', '4563552169036', '�Զ������������������', '������ߡ��������', '������', '����ͨ������', '��1��', '2018-04-24', 'ͼ��', '��������', '�ڶ����', 35, '��������Եķ�չ����', '������Ե���չ', 264, '145ǧ��');
INSERT INTO `books_info` VALUES ('Ĭ��', 13, 0, 25, '00008', '2453466799036', 'С˵', '���� ����', 'priest', '����ͨ������', '��1��', '2018-05-16', 'ͼ��', '��������', '�������', 36.8, '���꣬���ԣ��ư�', '������������', 564, '445ǧ��');
INSERT INTO `books_info` VALUES ('ë�����۸���', 9, 2, 7, '00009', '4652377494815', '˼�롢���ۡ���ʷ', 'ë�󶫡�ë�����ۡ��й���ɫ�������', '����������ѧ�о���', '�ߵȽ���������', '��7��', '2019-04-11', 'ͼ��', '��һ����', '��һ���', 32, '�й���ɫ����������ʷ��ë�����۵ķ�չ����', 'ë������', 453, '540ǧ��');
INSERT INTO `books_info` VALUES ('������ѧ�ߵ���ѧ�������塤����ƪ', 12, 12, 0, '00011', '9787576404319', '����', '���塢��������ѧ', '���ҷ�', '�й�������ѧ������', '��1��', '2023-03-08', 'ͼ��', '��һ����', '�ڶ����', 68, 'һ���ܴ�ѧ��ϲ���Ŀ��и��������顣', '��', 126, '205ǧ��');
INSERT INTO `books_info` VALUES ('ë��˼����й���ɫ�������������ϵ����', 5, 2, 5, '00071', '9787040494815', '˼�롢���ۡ���ʷ', 'ë�󶫡��й���ɫ�������', '��Ӣ', '�ߵȽ���������', '��6��', '2018-09-01', 'ͼ��', '��һ����', '��һ���', 25, '�й���ɫ����������ʷ', '����̿���', 313, '260ǧ��');
INSERT INTO `books_info` VALUES ('����2018��϶���', 90, 20, 27, '00702', '9771007384158', '��־�����ġ��ഺ��ѧ', '���ģ�СС��', '��Ӣ', '������־��', '��1��', '2019-01-01', '�ڿ�', '��������', '�ڶ����', 29.8, '��������ѧ��������ߣ������ѧ�����յ��ڿ�', '�ڿ��϶���', 744, '540ǧ��');

-- ----------------------------
-- Table structure for reader
-- ----------------------------
DROP TABLE IF EXISTS `reader`;
CREATE TABLE `reader`  (
  `ReaderID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ѧ��',
  `ReaderName` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '����',
  `Apart` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ѧԺ',
  `Sex` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '��',
  `Class` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '�༶',
  `TelNo` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '�绰',
  PRIMARY KEY (`ReaderID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of reader
-- ----------------------------
INSERT INTO `reader` VALUES ('5056', '����', '��ϢѧԺ', '��', '6233', '00000011');
INSERT INTO `reader` VALUES ('5156', '���', '����ѧԺ', '��', '6345', '00000012');
INSERT INTO `reader` VALUES ('5256', '����', '�����ѧԺ', 'Ů', '6453', '00000013');
INSERT INTO `reader` VALUES ('5356', '����', '�����赸ѧԺ', '��', '6571', '00000014');
INSERT INTO `reader` VALUES ('5456', '����', '���������ѧԺ', 'Ů', '6655', '00000015');
INSERT INTO `reader` VALUES ('5556', '����', '��ϢѧԺ', 'Ů', '6259', '00000016');
INSERT INTO `reader` VALUES ('5656', '����', '��ѧԺ', '��', '6782', '00000017');
INSERT INTO `reader` VALUES ('5756', '�Ʒ�', '����ѧԺ', 'Ů', '6890', '00000018');
INSERT INTO `reader` VALUES ('5856', '����', '��ѧԺ', 'Ů', '6934', '00000019');
INSERT INTO `reader` VALUES ('5956', '����', '��ѧԺ', '��', '6075', '00000020');
INSERT INTO `reader` VALUES ('5966', '����', '��ѧ�����Ժ', '��', '6137', '00000021');
INSERT INTO `reader` VALUES ('5976', '����', '���˼����ѧԺ', 'Ů', '6953', '00000022');
INSERT INTO `reader` VALUES ('5986', '����', '����ѧԺ', '��', '6370', '00000023');

-- ----------------------------
-- Table structure for t_borrow
-- ----------------------------
DROP TABLE IF EXISTS `t_borrow`;
CREATE TABLE `t_borrow`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `reader_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '����ID',
  `book_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '��ID',
  `borrow_admin_id` int(11) NOT NULL COMMENT '���������',
  `return_admin_id` int(11) NULL DEFAULT NULL COMMENT '�黹������',
  `book_number` int(10) UNSIGNED NOT NULL DEFAULT 1 COMMENT '�������',
  `duration` bigint(20) UNSIGNED NOT NULL COMMENT '���ʱ��������ʱ����������Ӧ��Ҫ�黹��ʱ��',
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'ͼ��״̬��0������1���飬2�������أ�3��ʧ',
  `penalty` float(10, 0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '����',
  `create_timestamp` bigint(20) UNSIGNED NOT NULL COMMENT '����ʱ��',
  `update_timestamp` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '�޸�ʱ��',
  `return_timestamp` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '�黹ʱ�䣬0��ʾδ�黹',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_borrow
-- ----------------------------
INSERT INTO `t_borrow` VALUES (1, '5056', '00001', 1, 1, 1, 6666, 0, 0, 32131231, 0, 56);
INSERT INTO `t_borrow` VALUES (2, '5056', '00001', 1, 1, 11, 9293132, 2, 1, 234523455, 12, 12);
INSERT INTO `t_borrow` VALUES (4, '5056', '00001', 0, NULL, 1, 92000000, 0, 0, 1678328726532, 0, 0);
INSERT INTO `t_borrow` VALUES (5, '5256', '00004', 0, NULL, 1, 92000000, 0, 0, 1678329110424, 0, 0);
INSERT INTO `t_borrow` VALUES (6, '5056', '00009', 0, NULL, 1, 1964596224, 0, 0, 1678361846829, 0, 0);
INSERT INTO `t_borrow` VALUES (7, '5056', '00001', 0, NULL, 1, 15552000000, 0, 0, 1678363298525, 0, 0);
INSERT INTO `t_borrow` VALUES (8, '5056', '00001', 0, 0, 1, 2592000000, 0, 10, 1678365120956, 1678445264698, 1678445264698);
INSERT INTO `t_borrow` VALUES (9, '5956', '00004', 0, NULL, 1, 2592000000, 0, 0, 1678418785460, 0, 0);
INSERT INTO `t_borrow` VALUES (10, '5356', '00007', 0, NULL, 1, 2592000000, 0, 0, 1678419222757, 0, 0);
INSERT INTO `t_borrow` VALUES (11, '5056', '00007', 0, NULL, 1, 2592000000, 0, 0, 1678419237168, 0, 0);
INSERT INTO `t_borrow` VALUES (12, '5156', '000010', 0, NULL, 1, 2592000000, 0, 0, 1678419268072, 0, 0);
INSERT INTO `t_borrow` VALUES (13, '5156', '000010', 0, NULL, 1, 2592000000, 0, 0, 1678419274655, 0, 0);
INSERT INTO `t_borrow` VALUES (14, '5156', '000010', 0, NULL, 1, 2592000000, 0, 0, 1678419281022, 0, 0);
INSERT INTO `t_borrow` VALUES (15, '5056', '00002', 0, NULL, 1, 2592000000, 0, 0, 1678419696487, 0, 0);
INSERT INTO `t_borrow` VALUES (16, '5056', '00002', 0, 0, 1, 5184000000, 0, 0, 1678419702617, 1678445472187, 0);

SET FOREIGN_KEY_CHECKS = 1;
