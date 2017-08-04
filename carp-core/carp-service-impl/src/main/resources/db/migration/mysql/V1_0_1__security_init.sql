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