CREATE TABLE carp_showcase_card
(
id BIGINT(50) PRIMARY KEY AUTO_INCREMENT COMMENT '逻辑主键',
card_id varchar(20) COMMENT '卡号，业务主键',
card_type varchar(4) NOT NULL  DEFAULT '0001' COMMENT '卡片类型',
issue_value  decimal(9,2) COMMENT '卡片发行时价格',
frozen_value decimal(9,2) COMMENT '已冻结金额',
balance_value decimal(9,2) COMMENT '当前余额'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

