CREATE DATABASE cyberwikia;

CREATE USER 'cyberwikia_app'@'localhost';

GRANT SELECT, UPDATE, INSERT, DELETE
ON cyberwikia.*
TO 'cyberwikia_app'@'localhost';