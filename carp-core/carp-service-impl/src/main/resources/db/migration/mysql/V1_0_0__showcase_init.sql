CREATE TABLE `carp_showcase_card` (
id BIGINT(50)  AUTO_INCREMENT COMMENT '逻辑主键',
card_id varchar(64) COMMENT '卡号，业务主键',
card_type varchar(4) NOT NULL  DEFAULT '0001' COMMENT '卡片类型',
issue_value  decimal(12,2) COMMENT '卡片发行时价格',
frozen_value decimal(12,2) COMMENT '已冻结金额',
balance_value decimal(12,2) COMMENT '当前余额',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `carp_showcase_card` VALUES (1, 'card001', '0001', 1000.00, 1.00, 1000.00);
INSERT INTO `carp_showcase_card` VALUES (2, 'card002', '0001', 100.00, 0.00, 100.00);
INSERT INTO `carp_showcase_card` VALUES (3, 'card003', '0001', 1000.00, 1.00, 1000.00);
INSERT INTO `carp_showcase_card` VALUES (4, 'card004', '0001', 100.00, 0.00, 100.00);
INSERT INTO `carp_showcase_card` VALUES (5, 'card005', '0001', 1000.00, 1.00, 1000.00);
INSERT INTO `carp_showcase_card` VALUES (6, 'card006', '0001', 100.00, 0.00, 100.00);
INSERT INTO `carp_showcase_card` VALUES (7, 'card007', '0001', 1000.00, 1.00, 1000.00);
INSERT INTO `carp_showcase_card` VALUES (8, 'card008', '0001', 100.00, 0.00, 100.00);
INSERT INTO `carp_showcase_card` VALUES (9, 'card009', '0001', 1000.00, 1.00, 1000.00);
INSERT INTO `carp_showcase_card` VALUES (10, 'card010', '0001', 100.00, 0.00, 100.00);
INSERT INTO `carp_showcase_card` VALUES (11, 'card011', '0001', 1000.00, 1.00, 1000.00);
INSERT INTO `carp_showcase_card` VALUES (12, 'card012', '0001', 100.00, 0.00, 100.00);

