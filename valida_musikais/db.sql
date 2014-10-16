CREATE DATABASE if NOT EXISTS `musikais`;
USE musikais;

CREATE TABLE `pesquisa_humor`(
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `humor` VARCHAR(200) DEFAULT NULL,
    `local` VARCHAR(200) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;

CREATE TABLE `tipo_emocao`(
    `id_tipo_emocao` INT(11) NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(200) DEFAULT NULL,
    PRIMARY KEY (`id_tipo_emocao`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;

CREATE TABLE `musica`(
    `id_musica` INT(11) NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(200) DEFAULT NULL,
    `id_tipo_emocao` INT(11) DEFAULT NULL,
    PRIMARY KEY (`id_musica`),
    CONSTRAINT `fk_tipo_emocao` FOREIGN KEY (`id_tipo_emocao`) REFERENCES `tipo_emocao`(`id_tipo_emocao`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;

CREATE TABLE `tipo_local`(
    `id_tipo_local` INT(11) NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(200) DEFAULT NULL,
    PRIMARY KEY (`id_tipo_local`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;

CREATE TABLE `clima_local`(
    `id_clima_local` INT(11) NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(200) DEFAULT NULL,
    PRIMARY KEY (`id_clima_local`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;

CREATE TABLE `periodo_local`(
    `id_periodo_local` INT(11) NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(200) DEFAULT NULL,
    PRIMARY KEY (`id_periodo_local`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;

CREATE TABLE `local`(
    `id_local` INT(11) NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(200) DEFAULT NULL,
    `id_tipo_local` INT(11) DEFAULT NULL,
    `id_clima_local` INT(11) DEFAULT NULL,
    `id_periodo_local` INT(11) DEFAULT NULL,
    PRIMARY KEY (`id_local`),
    CONSTRAINT `fk_tipo_local`FOREIGN KEY (`id_tipo_local`) REFERENCES `tipo_local`(`id_tipo_local`),
    CONSTRAINT `fk_clima_local`FOREIGN KEY (`id_clima_local`) REFERENCES `clima_local`(`id_clima_local`),
    CONSTRAINT `fk_periodo_local`FOREIGN KEY (`id_periodo_local`) REFERENCES `periodo_local`(`id_periodo_local`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;

CREATE TABLE `musica_local`(
    `id_musica_local`INT(11) NOT NULL AUTO_INCREMENT,
    `id_musica` INT(11) NOT NULL,
    `id_local` INT(11) NOT NULL,
    `voto` VARCHAR(200) NOT NULL,
    `data` INT(11) NOT NULL,
    PRIMARY KEY (`id_musica_local`),
    CONSTRAINT `fk_ml_musica` FOREIGN KEY (`id_musica`) REFERENCES `musica`(`id_musica`),
    CONSTRAINT `fk_ml_local` FOREIGN KEY (`id_local`) REFERENCES `local`(`id_local`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;

DROP TABLE `musica`;
DROP TABLE `musica_local`;
drop table `tipo_local`;
drop table `local`;
insert into `musikais`.`tipo_emocao`(nome) values ('alegre');
insert into `musikais`.`tipo_emocao`(nome) values ('nostalgica');

insert into `musikais`.`musica` (nome,id_tipo_emocao) values ('Ciganisses.mp3', (select id_tipo_emocao from `musikais`.`tipo_emocao` where nome = 'nostalgica'));
insert into `musikais`.`musica` (nome,id_tipo_emocao) values ('Me_Llaman_Calle.mp3', (select id_tipo_emocao from `musikais`.`tipo_emocao` where nome = 'alegre'));

insert into `musikais`.`tipo_local`(nome) values ('moderno');
insert into `musikais`.`tipo_local`(nome) values ('rustico');
insert into `musikais`.`tipo_local`(nome) values ('antigo');

insert into `musikais`.`clima_local`(nome) values ('ensolarado');
insert into `musikais`.`clima_local`(nome) values ('nublado');
insert into `musikais`.`clima_local`(nome) values ('chuvoso');

insert into `musikais`.`periodo_local`(nome) values ('amanhecer');
insert into `musikais`.`periodo_local`(nome) values ('manha');
insert into `musikais`.`periodo_local`(nome) values ('meio-dia');
insert into `musikais`.`periodo_local`(nome) values ('tarde');
insert into `musikais`.`periodo_local`(nome) values ('entardecer');
insert into `musikais`.`periodo_local`(nome) values ('noite');
insert into `musikais`.`periodo_local`(nome) values ('madrugada');

insert into `musikais`.`local` (nome,id_tipo_local,id_clima_local,id_periodo_local) values ('img2.jpg', (select id_tipo_local from `musikais`.`tipo_local` where nome = 'moderno'),(select id_clima_local from `musikais`.`clima_local` where nome = 'chuvoso'),(select id_periodo_local from `musikais`.`periodo_local` where nome = 'noite'));
insert into `musikais`.`local` (nome,id_tipo_local,id_clima_local,id_periodo_local) values ('img11.jpg', (select id_tipo_local from `musikais`.`tipo_local` where nome = 'rustico'),(select id_clima_local from `musikais`.`clima_local` where nome = 'nublado'),(select id_periodo_local from `musikais`.`periodo_local` where nome = 'manha'));
insert into `musikais`.`local` (nome,id_tipo_local,id_clima_local,id_periodo_local) values ('img12.jpg', (select id_tipo_local from `musikais`.`tipo_local` where nome = 'antigo'),(select id_clima_local from `musikais`.`clima_local` where nome = 'ensolarado'),(select id_periodo_local from `musikais`.`periodo_local` where nome = 'tarde'));
