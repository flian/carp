CREATE TABLE carp_customer (
  id        BIGINT      NOT NULL AUTO_INCREMENT,
  avatar    VARCHAR(64),
  gender    INTEGER,
  mobile    VARCHAR(11) NOT NULL,
  nick_name VARCHAR(20) NOT NULL,
  password  VARCHAR(64) NOT NULL,
  user_name VARCHAR(20) NOT NULL,
  uuid      VARCHAR(64) NOT NULL,
  `last_password_reset_date` datetime DEFAULT NULL,
  from_channel      VARCHAR(64) NOT NULL,
  wx_open_id      VARCHAR(32),
  wx_union_id      VARCHAR(32),
  PRIMARY KEY (id)
);
ALTER TABLE carp_customer
  ADD CONSTRAINT UK_user_name UNIQUE (user_name);
ALTER TABLE carp_customer
  ADD CONSTRAINT UK_uuid UNIQUE (uuid);
