CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1 NO CYCLE;

CREATE TABLE volunteer (
  volunteer_login VARCHAR(50) NOT NULL,
  volunteer_password VARCHAR(50) NOT NULL,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  user_token VARCHAR(250),
  PRIMARY KEY (volunteer_login)
);

CREATE TABLE project (
  id BIGINT NOT NULL,
  project_name VARCHAR(50) NOT NULL,
  description VARCHAR(500),
  expiry_date VARCHAR(50),
  event_date VARCHAR(50),
  volunteer_limit BIGINT,
  PRIMARY KEY (id)
);

CREATE TABLE organisation (
  organisation_login VARCHAR(50) NOT NULL,
  organisation_password VARCHAR(50) NOT NULL,
  organisation_field VARCHAR(50),
  introduction VARCHAR(500),
  organisation_token VARCHAR(250),
  PRIMARY KEY (organisation_login)
);

CREATE TABLE volunteer_to_project (
  volunteer_login VARCHAR(50) NOT NULL,
  id BIGINT NOT NULL,
  FOREIGN KEY (volunteer_login) REFERENCES volunteer (volunteer_login),
  FOREIGN KEY (id) REFERENCES project (id),
  PRIMARY KEY (volunteer_login, id)
);
