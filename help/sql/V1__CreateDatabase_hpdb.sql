CREATE DATABASE hpdb DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

use mysql;
CREATE USER 'hpadmin'@'%'IDENTIFIED BY 'Hpadmin0301';
CREATE USER 'hpadmin'@'localhost'IDENTIFIED BY 'Hpadmin0301';
INSERT INTO db (HOST,Db,USER,Select_priv,Insert_priv,Update_priv,Delete_priv,Create_priv,Drop_priv,Index_priv, Alter_priv)
 VALUES('%','hpdb','hpadmin','Y','Y','Y','Y','Y','Y','Y','Y');
FLUSH PRIVILEGES;
