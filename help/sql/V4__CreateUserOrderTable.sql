drop table if exists hpdb.`user_order`;

CREATE TABLE hpdb.`user_order` (
  `orderId` int(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `userId` int(15) NOT NULL,
  `paydate` date NOT NULL,
  `productType` int(2) NOT NULL COMMENT '1: one month usage 2: one quarter usage',
  `amount` decimal(12,0) NOT NULL,
  `status` int(1) DEFAULT NULL COMMENT '0: pending 1: success',
  `expiryDay` date DEFAULT NULL,
  `createTs` datetime DEFAULT NULL,
  `createBy` varchar(50) DEFAULT NULL,
  `updateTs` datetime DEFAULT NULL,
  `updateBy` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8