CREATE TABLE pedido (
	id BIGINT auto_increment NOT NULL,
	subtotal DECIMAL(10,2) NOT NULL,
	taxa_frete DECIMAL(10,2) NOT NULL,
	valor_total DECIMAL(10,2) NOT NULL,
	
	endereco_cidade_id BIGINT NOT NULL,
	endereco_cep varchar(9) NOT NULL,
	endereco_logradouro varchar(100) NOT NULL,
	endereco_numero varchar(15) NOT NULL,
	endereco_complemento varchar(15) NULL,
	endereco_bairro varchar(80) NOT NULL,
	
	usuario_cliente_id BIGINT NOT NULL,
	restaurante_id BIGINT NOT NULL,
	forma_pagamento_id BIGINT NOT NULL,	
	
	data_criacao DATETIME NOT NULL,
	data_confirmacao DATETIME NULL,
	data_cancelamento DATETIME NULL,
	data_entrega DATETIME NULL,
	status VARCHAR(10) NOT NULL,
		
	CONSTRAINT pedido_pk PRIMARY KEY (id),
	CONSTRAINT pedido_cidade_fk FOREIGN KEY (endereco_cidade_id) REFERENCES cidade(id),
	CONSTRAINT pedido_usuario_fk FOREIGN KEY (usuario_id) REFERENCES usuario(id),
	CONSTRAINT pedido_restaurante_fk FOREIGN KEY (restaurante_id) REFERENCES restaurante(id),
	CONSTRAINT pedido_forma-pagament_fk FOREIGN KEY (forma_pagamento_id) REFERENCES forma_pagamento(id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

CREATE TABLE item_pedido (
	id BIGINT auto_increment NOT NULL,
	quantidade INT NOT NULL,
	preco_unitario DECIMAL(10,2) NOT NULL,
	preco_total DECIMAL(10,2) NOT NULL,
	observacao TEXT NULL,
	pedido_id BIGINT NOT NULL,
	produto_id BIGINT NOT NULL,
	CONSTRAINT item_pedido_pk PRIMARY KEY (id),
	CONSTRAINT `item-pedido_pedido_fk` FOREIGN KEY (pedido_id) REFERENCES pedido(id),
	CONSTRAINT `item-pedido_produto_fk` FOREIGN KEY (produto_id) REFERENCES produto(id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
