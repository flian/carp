CREATE TABLE carp_user (
  id        BIGINT       NOT NULL AUTO_INCREMENT
  COMMENT '逻辑主键',
  password  VARCHAR(255) NOT NULL
  COMMENT '密码',
  user_name VARCHAR(20)  NOT NULL
  COMMENT '用户名',
  PRIMARY KEY (id)
);
ALTER TABLE carp_user
  ADD CONSTRAINT UK_user_name UNIQUE (user_name);

CREATE TABLE carp_role (
  id        BIGINT      NOT NULL AUTO_INCREMENT
  COMMENT '逻辑主键',
  role_code VARCHAR(30) NOT NULL
  COMMENT '角色编码，业务主键。',
  role_name VARCHAR(50) NOT NULL
  COMMENT '角色名称。',
  PRIMARY KEY (id)
);
ALTER TABLE carp_role
  ADD CONSTRAINT UK_role_code UNIQUE (role_code);


CREATE TABLE carp_user_role (
  user_name VARCHAR(20) NOT NULL,
  role_code VARCHAR(30) NOT NULL,
  PRIMARY KEY (user_name, role_code)
);

CREATE TABLE carp_action (
  id            INTEGER      NOT NULL AUTO_INCREMENT,
  action_method VARCHAR(5),
  action_url    VARCHAR(100) NOT NULL,
  leaf          BIT          NOT NULL,
  name          VARCHAR(20)  NOT NULL,
  parent_id     INT                   DEFAULT -1,
  priority      INT                   DEFAULT 100,
  PRIMARY KEY (id)
);

CREATE TABLE carp_menu (
  id        INTEGER      NOT NULL AUTO_INCREMENT,
  leaf      BIT          NOT NULL,
  name      VARCHAR(20)  NOT NULL,
  parent_id INT                   DEFAULT -1,
  priority  INT                   DEFAULT 100,
  url       VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE carp_role_action (
  role_id   BIGINT  NOT NULL,
  action_id INTEGER NOT NULL,
  PRIMARY KEY (role_id, action_id)
);
CREATE TABLE carp_role_menu (
  role_id BIGINT  NOT NULL,
  menu_id INTEGER NOT NULL,
  PRIMARY KEY (role_id, menu_id)
);
  INSERT INTO `carp_user` VALUES (1, '$2a$10$Ohb6kWObdqM231o8lzIkT.AWkApqhEe2XNL2akji9y0wzREdOa37q', 'admin'
);
INSERT INTO `carp_user` VALUES (2, '$2a$10$drUk/XnffBvEowXVjOB2c.yNvFgUb4uHu8/zMx.Cd385OCWGQThqa', 'user');
INSERT INTO `carp_role` VALUES (1, 'ROLE_ADMIN', '系统管理员');
INSERT INTO `carp_role` VALUES (2, 'ROLE_USER', '一般用户');
INSERT INTO `carp_user_role` VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO `carp_user_role` VALUES ('user', 'ROLE_USER');

-- ----------------------------
-- Records of carp_menu 初始化菜单
-- ----------------------------
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('1', '\0', '系统管理', '-1', '1', '');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('2', '\0', '业务管理', '-1', '2', '');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('5', '\0', '权限中心', '1', '1', '');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('6', '', '用户管理', '5', '1', '/profile');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('7', '', '角色管理', '5', '2', '/roles');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('8', '', '菜单管理', '5', '3', '/menus');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('9', '', '功能管理', '5', '4', '/actions');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('10', '\0', '静态页面', '-1', '3', 'NONE');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('12', '\0', 'Dashboard', '10', '1', 'NONE');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('14', '', 'Dashboard V1', '12', '1', '/webjars/AdminLTE/index.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('15', '', 'dashboard v2', '12', '1', '/webjars/AdminLTE/index2.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('16', '\0', 'layout Options', '10', '2', 'NONE');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('18', '\0', 'Charts', '10', '4', 'NONE');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('19', '\0', 'Widgets', '10', '5', 'NONE');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('20', '\0', 'UI Elements', '10', '6', 'NONE');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('21', '\0', 'Forms', '10', '7', 'NONE');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('22', '\0', 'Tables', '10', '8', 'NONE');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('23', '\0', 'Calendar', '10', '9', 'NONE');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('24', '\0', 'Mailbox', '10', '10', 'NONE');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('27', '\0', 'Examples', '10', '13', 'NONE');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('30', '\0', 'Documents', '10', '16', 'NONE');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('31', '', 'documentation', '30', '1', '/webjars/AdminLTE/documentation/index.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('32', '', 'Top Navigation', '16', '1', '/webjars/AdminLTE/pages/layout/top-nav.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('33', '', 'Boxed', '16', '1', '/webjars/AdminLTE/pages/layout/boxed.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('34', '', 'Fixed', '16', '2', '/webjars/AdminLTE/pages/layout/fixed.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('35', '', 'Collapsed Sidebar', '16', '3', '/webjars/AdminLTE/pages/layout/collapsed-sidebar.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('36', '', 'ChartJS', '18', '1', '/webjars/AdminLTE/pages/charts/chartjs.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('37', '', 'Morris', '18', '1', '/webjars/AdminLTE/pages/charts/morris.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('38', '', 'Flot', '18', '2', '/webjars/AdminLTE/pages/charts/flot.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('39', '', 'Inline charts', '18', '3', '/webjars/AdminLTE/pages/charts/inline.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('40', '', 'Widgets', '19', '1', '/webjars/AdminLTE/pages/widgets.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('41', '', 'General', '20', '1', '/webjars/AdminLTE/pages/UI/general.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('42', '', 'Icons', '20', '1', '/webjars/AdminLTE/pages/UI/icons.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('43', '', 'Buttons', '20', '2', '/webjars/AdminLTE/pages/UI/buttons.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('44', '', 'Sliders', '20', '3', '/webjars/AdminLTE/pages/UI/sliders.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('45', '', 'Timeline', '20', '4', '/webjars/AdminLTE/pages/UI/timeline.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('46', '', 'Modals', '20', '5', '/webjars/AdminLTE/pages/UI/modals.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('51', '', 'General Elements', '21', '4', '/webjars/AdminLTE/pages/forms/general.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('52', '', 'Advanced Elements', '21', '5', '/webjars/AdminLTE/pages/forms/advanced.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('53', '', 'Editors', '21', '6', '/webjars/AdminLTE/pages/forms/editors.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('54', '', 'Simple Tables', '22', '1', '/webjars/AdminLTE/pages/tables/simple.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('55', '', 'Data tables', '22', '1', '/webjars/AdminLTE/pages/tables/data.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('56', '', 'Calendar', '23', '1', '/webjars/AdminLTE/pages/calendar.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('57', '', 'MainBox', '24', '1', '/webjars/AdminLTE/pages/mailbox/mailbox.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('58', '', 'Invoice', '27', '1', '/webjars/AdminLTE/pages/examples/invoice.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('59', '', 'Profile', '27', '1', '/webjars/AdminLTE/pages/examples/profile.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('60', '', 'Login', '27', '2', '/webjars/AdminLTE/pages/examples/login.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('61', '', 'Register', '27', '3', '/webjars/AdminLTE/pages/examples/register.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('62', '', 'Lockscreen', '27', '4', '/webjars/AdminLTE/pages/examples/lockscreen.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('63', '', '404 Error', '27', '5', '/webjars/AdminLTE/pages/examples/404.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('64', '', '500 Error', '27', '6', '/webjars/AdminLTE/pages/examples/500.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('65', '', 'Blank Page', '27', '7', '/webjars/AdminLTE/pages/examples/blank.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('66', '', 'Pace Page', '27', '8', '/webjars/AdminLTE/pages/examples/pace.html');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('67', '\0', '示例管理', '-1', '4', 'NONE');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('68', '\0', '示例中心', '67', '1', 'NONE');
INSERT INTO `carp_menu` (`id`, `leaf`, `name`, `parent_id`, `priority`, `url`) VALUES ('69', '', '卡片管理示范', '68', '1', '/cards');

-- ----------------------------
-- Auto increment value for carp_menu
-- ----------------------------
ALTER TABLE `carp_menu` AUTO_INCREMENT=70;


-- ----------------------------
-- Records of carp_action 初始化功能
-- ----------------------------
INSERT INTO `carp_action` (`id`, `action_method`, `action_url`, `leaf`, `name`, `parent_id`, `priority`) VALUES ('1', 'ALL', 'NONE', '\0', '系统管理', '-1', '1');
INSERT INTO `carp_action` (`id`, `action_method`, `action_url`, `leaf`, `name`, `parent_id`, `priority`) VALUES ('2', 'ALL', 'NONE', '\0', '业务管理', '-1', '1');
INSERT INTO `carp_action` (`id`, `action_method`, `action_url`, `leaf`, `name`, `parent_id`, `priority`) VALUES ('3', 'ALL', 'NONE', '\0', '示例管理', '-1', '2');
INSERT INTO `carp_action` (`id`, `action_method`, `action_url`, `leaf`, `name`, `parent_id`, `priority`) VALUES ('4', 'ALL', 'NONE', '\0', '权限中心', '1', '1');
INSERT INTO `carp_action` (`id`, `action_method`, `action_url`, `leaf`, `name`, `parent_id`, `priority`) VALUES ('5', 'ALL', 'NONE', '\0', '示例中心', '3', '1');
INSERT INTO `carp_action` (`id`, `action_method`, `action_url`, `leaf`, `name`, `parent_id`, `priority`) VALUES ('6', 'ALL', '/profile', '', '用户管理', '4', '1');
INSERT INTO `carp_action` (`id`, `action_method`, `action_url`, `leaf`, `name`, `parent_id`, `priority`) VALUES ('7', 'ALL', '/roles', '', '角色管理', '4', '1');
INSERT INTO `carp_action` (`id`, `action_method`, `action_url`, `leaf`, `name`, `parent_id`, `priority`) VALUES ('8', 'ALL', '/menus', '', '菜单管理', '4', '2');
INSERT INTO `carp_action` (`id`, `action_method`, `action_url`, `leaf`, `name`, `parent_id`, `priority`) VALUES ('9', 'ALL', '/actions', '', '功能管理', '4', '3');
INSERT INTO `carp_action` (`id`, `action_method`, `action_url`, `leaf`, `name`, `parent_id`, `priority`) VALUES ('10', 'ALL', '/cards', '', '卡片管理示范', '5', '1');
-- ----------------------------
-- Auto increment value for carp_action
-- ----------------------------
ALTER TABLE `carp_action` AUTO_INCREMENT=11;