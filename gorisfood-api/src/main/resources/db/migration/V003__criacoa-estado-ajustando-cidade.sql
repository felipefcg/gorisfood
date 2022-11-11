CREATE TABLE estado (
	id BIGINT auto_increment NOT NULL,
	nome varchar(80) NOT NULL,
	CONSTRAINT estado_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

ALTER TABLE cidade CHANGE nome_cidade nome varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL;

ALTER TABLE cidade CHANGE nome_estado estado_id BIGINT NOT NULL;

ALTER TABLE cidade ADD CONSTRAINT cidade_FK FOREIGN KEY (estado_id) REFERENCES estado(id);

