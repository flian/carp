-- ----------------------------
--  给admin role赋予全部的菜单和功能
-- ----------------------------
INSERT IGNORE INTO `carp_role_menu` (`role_id`,`menu_id`) SELECT 1,m.id FROM `carp_menu` m;
INSERT IGNORE INTO `carp_role_action` (`role_id`,`action_id`) SELECT 1,m.id FROM `carp_action` m;