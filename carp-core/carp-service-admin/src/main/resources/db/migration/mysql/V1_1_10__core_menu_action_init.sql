#初始化commerce菜单
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('70', '\0', '客户管理', '2', '1', '#');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('75', '', '客户管理', '70', '1', '/customer');

ALTER TABLE `carp_menu` AUTO_INCREMENT=82;

#初始化commerce功能权限
INSERT INTO `carp_action` (`id`, `action_method`, `action_url`, `leaf`, `name`, `parent_id`, `priority`) VALUES (11, 'ALL', '#', '\0', '客户管理', 2, 1);

INSERT INTO `carp_action` (`id`, `action_method`, `action_url`, `leaf`, `name`, `parent_id`, `priority`) VALUES (16, 'ALL', '/customer.*', '', '客户管理', 11, 1);

ALTER TABLE `carp_action` AUTO_INCREMENT=23;