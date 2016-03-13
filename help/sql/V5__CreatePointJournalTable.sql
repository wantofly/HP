drop table if exists hpdb.`point_journal`;

CREATE TABLE hpdb.`point_journal` (
  `pointJournalId` int(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `userId` int(15) NOT NULL,
  `journalType` int(1) NOT NULL COMMENT '1: Deposit 2: Withdraw',
  `orderId` int(20) DEFAULT NULL,
  `valueDay` date DEFAULT NULL,
  `point` decimal(10,0) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '0: pending 1: success',
  `createTs` date DEFAULT NULL,
  `createBy` varchar(50) DEFAULT NULL,
  `updateTs` date DEFAULT NULL,
  `updateBy` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pointJournalId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8