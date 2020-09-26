# java-all


CREATE TABLE `t_account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT '',
  `age` int DEFAULT '0',
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
