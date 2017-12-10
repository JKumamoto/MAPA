
CREATE DATABASE IF NOT EXISTS `MAPA`;

DROP USER IF EXISTS 'mapa'@'localhost';
CREATE USER 'mapa'@'localhost' IDENTIFIED BY 'senha';
GRANT SELECT, INSERT, DELETE, UPDATE ON MAPA.* TO 'mapa'@'localhost';

USE `MAPA`;

DROP TABLE IF EXISTS `entidade_tags`;
DROP TABLE IF EXISTS `tag`;
DROP TABLE IF EXISTS `entidade`;

CREATE TABLE `entidade` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`nome` varchar(100) NOT NULL,
	`email` varchar(100) NOT NULL,
	`senha` varchar(100) NOT NULL,
	`descricao` varchar(10000) DEFAULT NULL,
	`resumo` varchar(100) DEFAULT NULL,
	`imagem` LONGBLOB DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tag` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`nome` varchar(100) NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `entidade_tags` (
	`entidade_id` int(11) NOT NULL,
	`tag_id` int(11) NOT NULL,
	PRIMARY KEY (`entidade_id`, `tag_id`),
	KEY `fk_entidade` (`entidade_id`),
	KEY `fk_tag` (`tag_id`),
	CONSTRAINT `fk_entidade` FOREIGN KEY (`entidade_id`) REFERENCES `entidade` (`id`),
	CONSTRAINT `fk_tag` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- INSERT INTO `Entidade` VALUES (1, 'MAPA', 'mapa@mapa.com', 'mapa', NULL, NULL);

