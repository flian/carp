CREATE TABLE `carp_showcase_card` (
id BIGINT(50)  AUTO_INCREMENT COMMENT '逻辑主键',
card_id varchar(64) NOT NULL COMMENT '卡号，业务主键',
card_type varchar(4) NOT NULL  DEFAULT '0001' COMMENT '卡片类型',
issue_value  decimal(12,2) NOT NULL COMMENT '卡片发行时价格',
frozen_value decimal(12,2) NOT NULL COMMENT '已冻结金额',
balance_value decimal(12,2) NOT NULL COMMENT '当前余额',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



