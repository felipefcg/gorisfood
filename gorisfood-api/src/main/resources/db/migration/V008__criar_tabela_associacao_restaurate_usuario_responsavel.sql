CREATE TABLE restaurante_usuario_responsavel (
	restaurante_id BIGINT NOT NULL,
	usuario_id BIGINT NOT NULL,
	CONSTRAINT restauranteresponsavel_pk PRIMARY KEY (restaurante_id,usuario_id),
	CONSTRAINT restauranteresponsavel_restaurante_FK FOREIGN KEY (restaurante_id) REFERENCES restaurante(id),
	CONSTRAINT restauranteresponsavel_usuario_FK FOREIGN KEY (usuario_id) REFERENCES usuario(id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;