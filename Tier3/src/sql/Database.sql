-- User table --

CREATE TABLE IF NOT EXISTS users (
    id            SERIAL,
    username      VARCHAR(15),
    password      VARCHAR(15),
    securityLevel INTEGER
);