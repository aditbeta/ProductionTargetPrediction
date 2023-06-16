CREATE DATABASE IF NOT EXISTS `predictor`;

USE `predictor`;

CREATE TABLE IF NOT EXISTS `production` (
    `id` int NOT NULL AUTO_INCREMENT,
    `month` varchar(20) NOT NULL,
    `sell` double NOT NULL,
    `order` double NOT NULL,
    `target` double NOT NULL,
    `monthNum` int NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `prediction` (
    `id` int NOT NULL AUTO_INCREMENT,
    `totalX1` double DEFAULT NULL,
    `totalX2` double DEFAULT NULL,
    `totalY` double DEFAULT NULL,
    `totalX1X1` double DEFAULT NULL,
    `totalX2X2` double DEFAULT NULL,
    `totalYY` double DEFAULT NULL,
    `totalX1Y` double DEFAULT NULL,
    `totalX2Y` double DEFAULT NULL,
    `totalX1X2` double DEFAULT NULL,
    `b0` double DEFAULT NULL,
    `b1` double DEFAULT NULL,
    `b2` double DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
