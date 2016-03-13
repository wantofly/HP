drop table if exists hpdb.`mobile_verification`;

CREATE TABLE hpdb.`mobile_verification` (
  `mobileVerifId` int(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `mobile` varchar(30) NOT NULL,
  `verifyCode` varchar(10) NOT NULL,
  `activeInd` varchar(1) DEFAULT NULL,
  `createTs` datetime DEFAULT NULL,
  `createBy` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`mobileVerifId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8