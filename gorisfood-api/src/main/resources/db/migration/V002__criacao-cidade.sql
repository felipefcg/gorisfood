CREATE TABLE cidade (
	id BIGINT auto_increment NOT NULL,
	nome_cidade varchar(80) NOT NULL,
	nome_estado varchar(80) NOT NULL,
	CONSTRAINT cidade_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;