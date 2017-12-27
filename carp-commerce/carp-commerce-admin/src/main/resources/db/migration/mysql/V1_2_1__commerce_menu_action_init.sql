#初始化commerce菜单
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('70', '\0', '客户管理', '2', '1', '#');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('71', '\0', '产品分类', '2', '1', '#');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('72', '\0', '价格管理', '2', '2', '#');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('73', '\0', '库存管理', '2', '3', '#');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('74', '\0', '订单管理', '2', '4', '#');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('75', '', '客户管理', '70', '1', '/customer');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('76', '', '分类管理', '71', '2', '/category');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('77', '', '产品管理', '71', '1', '/product');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('78', '', '基础价格管理', '72', '1', '/list/price');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('79', '', '促销价格管理', '72', '1', '/sale/price');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('80', '', '基础库存管理', '73', '1', '/stock');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('81', '', '订单管理', '74', '1', '/order');
ALTER TABLE `carp_menu` AUTO_INCREMENT=82;

#初始化commerce功能权限
INSERT INTO `carp_action` (`id`, `action_method`, `action_url`, `leaf`, `name`, `parent_id`, `priority`) VALUES (11, 'ALL', '#', '\0', '客户管理', 2, 1);
INSERT INTO `carp_action` (`id`, `action_method`, `action_url`, `leaf`, `name`, `parent_id`, `priority`) VALUES (12, 'ALL', 'NONE', '\0', '产品分类', 2, 2);
INSERT INTO `carp_action` (`id`, `action_method`, `action_url`, `leaf`, `name`, `parent_id`, `priority`) VALUES (13, 'ALL', 'NONE', '\0', '价格管理', 2, 3);
INSERT INTO `carp_action` (`id`, `action_method`, `action_url`, `leaf`, `name`, `parent_id`, `priority`) VALUES (14, 'ALL', 'NONE', '\0', '库存管理', 2, 4);
INSERT INTO `carp_action` (`id`, `action_method`, `action_url`, `leaf`, `name`, `parent_id`, `priority`) VALUES (15, 'ALL', 'NONE', '\0', '订单管理', 2, 5);
INSERT INTO `carp_action` (`id`, `action_method`, `action_url`, `leaf`, `name`, `parent_id`, `priority`) VALUES (16, 'ALL', '/customer.*', '', '客户管理', 11, 1);
INSERT INTO `carp_action` (`id`, `action_method`, `action_url`, `leaf`, `name`, `parent_id`, `priority`) VALUES (17, 'ALL', '/category.*', '', '分类管理', 12, 1);
INSERT INTO `carp_action` (`id`, `action_method`, `action_url`, `leaf`, `name`, `parent_id`, `priority`) VALUES (18, 'ALL', '/product.*', '', '产品管理', 12, 1);
INSERT INTO `carp_action` (`id`, `action_method`, `action_url`, `leaf`, `name`, `parent_id`, `priority`) VALUES (19, 'ALL', '/list/price.*', '', '基础价格管理', 13, 1);
INSERT INTO `carp_action` (`id`, `action_method`, `action_url`, `leaf`, `name`, `parent_id`, `priority`) VALUES (20, 'ALL', '/sale/price.*', '', '促销价格管理', 13, 1);
INSERT INTO `carp_action` (`id`, `action_method`, `action_url`, `leaf`, `name`, `parent_id`, `priority`) VALUES (21, 'ALL', '/stock.*', '', '基础库存管理', 14, 1);
INSERT INTO `carp_action` (`id`, `action_method`, `action_url`, `leaf`, `name`, `parent_id`, `priority`) VALUES (22, 'ALL', '/order.*', '', '订单管理', 15, 1);
ALTER TABLE `carp_action` AUTO_INCREMENT=23;