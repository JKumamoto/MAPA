
CREATE DATABASE IF NOT EXISTS `MAPA`;

DROP USER IF EXISTS 'mapa'@'localhost';
CREATE USER 'mapa'@'localhost' IDENTIFIED BY 'senha';
GRANT SELECT, INSERT, DELETE, UPDATE ON MAPA.* TO 'mapa'@'localhost';

USE `MAPA`;

DROP TABLE IF EXISTS `Entidade`;
CREATE TABLE `Entidade` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`nome` varchar(100) NOT NULL,
	`email` varchar(100) NOT NULL,
	`senha` varchar(100) NOT NULL,
	`fundacao` date DEFAULT NULL,
	`reconhecimento_institucional` boolean DEFAULT FALSE,
	`descricao` varchar(10000) DEFAULT NULL,
	`comunicacao_externa` varchar(100) DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `Tag`;
CREATE TABLE `Tag` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`nome` varchar(100) NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `entidade_tags`;
CREATE TABLE `entidade_tags` (
	`entidade_id` int(11) NOT NULL,
	`tag_id` int(11) NOT NULL,
	PRIMARY KEY (`entidade_id`, `tag_id`),
	KEY `fk_entidade` (`entidade_id`),
	KEY `fk_tag` (`tag_id`),
	CONSTRAINT `fk_entidade` FOREIGN KEY (`entidade_id`) REFERENCES `Entidade` (`id`),
	CONSTRAINT `fk_tag` FOREIGN KEY (`tag_id`) REFERENCES `Tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `Setor`;
CREATE TABLE `Setor` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`nome` varchar(100) NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `entidade_setor`;
CREATE TABLE `entidade_setor` (
	`entidade_id` int(11) NOT NULL,
	`setor_id` int(11) NOT NULL,
	PRIMARY KEY (`entidade_id`, `setor_id`),
	KEY `fk_entidade` (`entidade_id`),
	KEY `fk_setor` (`setor_id`),
	CONSTRAINT `fk_entidade` FOREIGN KEY (`entidade_id`) REFERENCES `Entidade` (`id`),
	CONSTRAINT `fk_setor` FOREIGN KEY (`setor_id`) REFERENCES `Setor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `Entidade` VALUES (1, 'MAPA', 'mapa@mapa.com', 'mapa', NULL, FALSE, NULL, NULL);

