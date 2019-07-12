CREATE USER 'goulven'@'%' IDENTIFIED BY 'goulven';

CREATE DATABASE WEDDINGAPPS;

GRANT ALL ON WEDDINGAPPS.* TO 'goulven'@'%';

CREATE TABLE WEDDING (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`partner1` VARCHAR(50) NOT NULL,
`partner2` VARCHAR(50) NOT NULL,
`number1` VARCHAR(20) NULL,
`number2` VARCHAR(20) NULL,
`wedding_date` DATE NOT NULL,
`id_public` VARCHAR(10) NOT NULL,
`mail_creator` VARCHAR(30)
)ENGINE=InnoDB;

ALTER TABLE WEDDING ADD INDEX(id_public);

CREATE TABLE MESSAGE (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`wedding_id` INT NOT NULL,
`message` VARCHAR(300) NOT NULL,
`image` VARCHAR(300) NULL,
`number_sender` VARCHAR(20) NOT NULL,
`name_sender` VARCHAR(30) NOT NULL,
`message_sent` TINYINT(1) DEFAULT 0
)ENGINE=InnoDB;

ALTER TABLE MESSAGE ADD FOREIGN KEY (wedding_id) REFERENCES WEDDING(id);

/*TESTS*/
/*INSERT INTO `weddingapps`.`wedding` (`id`, `partner1`, `partner2`, `number1`, `number2`, `wedding_date`) VALUES ('1', 'bob', 'serge', '0225854584', '0665847458', '20180819');*/
