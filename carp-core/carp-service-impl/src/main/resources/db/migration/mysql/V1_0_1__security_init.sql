CREATE TABLE carp_user (
  id        BIGINT NOT NULL AUTO_INCREMENT,
  password  VARCHAR(255),
  user_name VARCHAR(20),
  PRIMARY KEY (id)
);
ALTER TABLE carp_user ADD CONSTRAINT UK_user_name UNIQUE (user_name);

CREATE TABLE carp_role (
  id        BIGINT NOT NULL AUTO_INCREMENT,
  role_code VARCHAR(30),
  role_name VARCHAR(50),
  PRIMARY KEY (id)
);
ALTER TABLE carp_role ADD CONSTRAINT UK_role_code UNIQUE (role_code);


CREATE TABLE carp_user_role (
  user_name VARCHAR(20) NOT NULL,
  role_code VARCHAR(30) NOT NULL,
  PRIMARY KEY (user_name, role_code)
);

INSERT INTO `carp_user` VALUES (1, '$2a$10$Ohb6kWObdqM231o8lzIkT.AWkApqhEe2XNL2akji9y0wzREdOa37q', 'admin');
INSERT INTO `carp_user` VALUES (2, '$2a$10$drUk/XnffBvEowXVjOB2c.yNvFgUb4uHu8/zMx.Cd385OCWGQThqa', 'user');
INSERT INTO `carp_role` VALUES (1, 'ROLE_ADMIN', '系统管理员');
INSERT INTO `carp_role` VALUES (2, 'ROLE_USER', '一般用户');
INSERT INTO `carp_user_role` VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO `carp_user_role` VALUES ('user', 'ROLE_USER');
