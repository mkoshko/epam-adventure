CREATE DATABASE cyberwikia_test;

CREATE USER 'cyberwikia_test'@'localhost'
    IDENTIFIED WITH sha256_password
    BY '5Js7kS#QePN&M%V+2@fc5DpA*A!R8?';

GRANT SELECT, UPDATE, INSERT, DELETE
ON cyberwikia_test.*
TO 'cyberwikia_test'@'localhost';