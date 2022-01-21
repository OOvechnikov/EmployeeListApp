CREATE TABLE employees
(
    id           SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name  VARCHAR(100) NOT NULL,
    sur_name            VARCHAR(100),
    age                INT2 NOT NULL
);

CREATE TABLE addresses
(
    id         SERIAL PRIMARY KEY,
    employee_id     INT4 NOT NULL,
    address VARCHAR(256) NOT NULL,
    region  VARCHAR(256) NOT NULL,
    district VARCHAR(256) NOT NULL
);

CREATE TABLE work_time (
   id         SERIAL PRIMARY KEY,
   employee_id     INT4 NOT NULL,
   start           TIME NOT NULL,
   finish           TIME NOT NULL
);

CREATE TABLE users
(
    id                        SERIAL NOT NULL,
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password             VARCHAR(100) NOT NULL,
    enabled                   BOOLEAN NOT NULL
);

CREATE TABLE authorities
(
    id             SERIAL NOT NULL,
    username  VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL
)