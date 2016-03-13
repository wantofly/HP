drop table if exists hpdb.`user`;

CREATE TABLE hpdb.`user` (
  `userId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userName` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `mobile` varchar(30) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `expiryDay` date DEFAULT NULL,
  `recommendCode` varchar(30) DEFAULT NULL,
  `recommendFrom` varchar(30) DEFAULT NULL,
  `loginCount` int(2) DEFAULT NULL,
  `loginMax` int(2) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '0: not verified  1: verified 2: frozen due to password wrong',
  `createTs` datetime DEFAULT NULL,
  `createBy` varchar(50) DEFAULT NULL,
  `updateTs` datetime DEFAULT NULL,
  `updateBy` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `index_userName` (`userName`),
  KEY `index_recommendCode` (`recommendCode`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8