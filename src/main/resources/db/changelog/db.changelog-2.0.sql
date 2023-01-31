--liquibase formatted sql

--changeset abekmuratov:1
ALTER TABLE users
    ADD COLUMN created_at TIMESTAMP;

ALTER TABLE users
    ADD COLUMN modified_at TIMESTAMP;
