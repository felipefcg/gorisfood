CREATE TABLE cozinha (
	id BIGINT auto_increment NOT NULL,
	nome varchar(60) NOT NULL,
	CONSTRAINT cozinha_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4;