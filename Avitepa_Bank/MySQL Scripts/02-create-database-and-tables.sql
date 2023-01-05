CREATE DATABASE  IF NOT EXISTS `AvitepaBank`;
USE `AvitepaBank`;

DROP TABLE IF EXISTS `account`;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `account` (
  `acc_no` int(11) NOT NULL AUTO_INCREMENT,
  `account_type` varchar(45),
  `acc_balance` int(15),
  `customer_detail_id` int(15),
  PRIMARY KEY  (`acc_no`),
  KEY `FK_DETAIL_idx` (`customer_detail_id`),
  CONSTRAINT `FK_DETAIL` FOREIGN KEY (`customer_detail_id`) REFERENCES `customer_detail` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;



DROP TABLE IF EXISTS `customer_detail`;

CREATE TABLE `customer_detail` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(128) ,
  `last_name` varchar(45) ,
  `address` varchar(100) ,
  `email` varchar(100) ,
  `mobile_number` int(15),
  PRIMARY KEY  (`customer_id`)  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

commit;
