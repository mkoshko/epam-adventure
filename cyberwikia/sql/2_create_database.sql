CREATE DATABASE cyberwikia;

CREATE USER 'cyberwikia_app'@'localhost'
    IDENTIFIED WITH sha256_password
    BY '5Js7kS#QePN&M%V+2@fc5DpA*A!R8?';

GRANT SELECT, UPDATE, INSERT, DELETE
ON cyberwikia.*
TO 'cyberwikia_app'@'localhost';