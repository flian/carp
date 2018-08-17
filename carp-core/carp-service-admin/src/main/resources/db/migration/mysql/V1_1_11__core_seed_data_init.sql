#初始化card数据
INSERT INTO `carp_showcase_card` (`id`, `card_id`, `card_type`, `issue_value`, `frozen_value`, `balance_value`) VALUES (1, 'card001', '0001', 1000.00, 11.00, 1000.00);
INSERT INTO `carp_showcase_card` (`id`, `card_id`, `card_type`, `issue_value`, `frozen_value`, `balance_value`) VALUES (2, 'card002', '0001', 100.00, 0.00, 100.00);
INSERT INTO `carp_showcase_card` (`id`, `card_id`, `card_type`, `issue_value`, `frozen_value`, `balance_value`) VALUES (3, 'card003', '0001', 1000.00, 1.00, 1000.00);
INSERT INTO `carp_showcase_card` (`id`, `card_id`, `card_type`, `issue_value`, `frozen_value`, `balance_value`) VALUES (4, 'card004', '0001', 100.00, 0.00, 100.00);
INSERT INTO `carp_showcase_card` (`id`, `card_id`, `card_type`, `issue_value`, `frozen_value`, `balance_value`) VALUES (5, 'card005', '0001', 1000.00, 1.00, 1000.00);
INSERT INTO `carp_showcase_card` (`id`, `card_id`, `card_type`, `issue_value`, `frozen_value`, `balance_value`) VALUES (6, 'card006', '0001', 100.00, 0.00, 100.00);
INSERT INTO `carp_showcase_card` (`id`, `card_id`, `card_type`, `issue_value`, `frozen_value`, `balance_value`) VALUES (7, 'card007', '0001', 1000.00, 1.00, 1000.00);
INSERT INTO `carp_showcase_card` (`id`, `card_id`, `card_type`, `issue_value`, `frozen_value`, `balance_value`) VALUES (8, 'card008', '0001', 100.00, 0.00, 100.00);
INSERT INTO `carp_showcase_card` (`id`, `card_id`, `card_type`, `issue_value`, `frozen_value`, `balance_value`) VALUES (9, 'card009', '0001', 1000.00, 1.00, 1000.00);
INSERT INTO `carp_showcase_card` (`id`, `card_id`, `card_type`, `issue_value`, `frozen_value`, `balance_value`) VALUES (10, 'card010', '0001', 100.00, 0.00, 100.00);
INSERT INTO `carp_showcase_card` (`id`, `card_id`, `card_type`, `issue_value`, `frozen_value`, `balance_value`) VALUES (11, 'card011', '0001', 1000.00, 1.00, 1000.00);
INSERT INTO `carp_showcase_card` (`id`, `card_id`, `card_type`, `issue_value`, `frozen_value`, `balance_value`) VALUES (12, 'card012', '0001', 100.00, 0.00, 100.00);


#初始化用户种子数据
INSERT INTO `carp_customer` (`id`, `avatar`, `gender`, `mobile`, `nick_name`, `password`, `user_name`, `uuid`,`from_channel`) VALUES (1, '11', 1, '13333333333', '小白', '$2a$10$tpYOWdQtvOKLWN02mUbDMO/Y3aHE8dM4q9jc.7cYJh/9mFPPnIEIi', 'test_001', '$10$tpYOWdQtvOKLWN02mUbDMO/Y3aHE8dM4q9jc.7cYJh/9mF2PnIEIi','NORMAL');
INSERT INTO `carp_customer` (`id`, `avatar`, `gender`, `mobile`, `nick_name`, `password`, `user_name`, `uuid`,`from_channel`) VALUES (2, '222', 1, '13333333333', '大白', '$10$tpYOWdQtvOKLWN02mUbDMO/Y3aHE8dM4q9jc.7cYJh/9mFPPnIEIi', 'test_002', '$10$tpYOWdQtvOKLWN02mUbDMO/Y3aHE8dM4q9jc.7cYJh/9maPPnIEIi','NORMAL');
INSERT INTO `carp_customer` (`id`, `avatar`, `gender`, `mobile`, `nick_name`, `password`, `user_name`, `uuid`,`from_channel`) VALUES (3, '33', 1, '13333333333', '张三', '$10$tpYOWdQtvOKLWN02mUbDMO/Y3aHE8dM4q9jc.7cYJh/9mFPPnIEIi', 'test_003', '$10$tpYOWdQtvOKLWN02mUbDMO/Y3aHE8dM4q9jc.7cYJh/9mFdPnIEIi','NORMAL');
INSERT INTO `carp_customer` (`id`, `avatar`, `gender`, `mobile`, `nick_name`, `password`, `user_name`, `uuid`,`from_channel`) VALUES (4, '4', 1, '13333333333', '李四', '$10$tpYOWdQtvOKLWN02mUbDMO/Y3aHE8dM4q9jc.7cYJh/9mFPPnIEIi', 'test_004', '$10$tpYOWdQtvOKLWN02mUbDMO/Y3aHE8dM4q9jc.7cYJh/9mFxPnIEIi','NORMAL');
INSERT INTO `carp_customer` (`id`, `avatar`, `gender`, `mobile`, `nick_name`, `password`, `user_name`, `uuid`,`from_channel`) VALUES (5, '5', 1, '13333333333', '王五', '$10$tpYOWdQtvOKLWN02mUbDMO/Y3aHE8dM4q9jc.7cYJh/9mFPPnIEIi', 'test_005', '$10$tdYOWdQtvOKLWN02mUbDMO/Y3aHE8dM4q9jc.7cYJh/9mFPPnIEIi','NORMAL');
INSERT INTO `carp_customer` (`id`, `avatar`, `gender`, `mobile`, `nick_name`, `password`, `user_name`, `uuid`,`from_channel`) VALUES (6, '1', 1, '13333333333', '刘六', '$10$tpYOWdQtvOKLWN02mUbDMO/Y3aHE8dM4q9jc.7cYJh/9mFPPnIEIi', 'test_006', '$10$tpYOWdQtvOKLWN02mUbDMO/Y3aHE8dM4q9jc.7cYJh/9mXPPnIEIi','NORMAL');