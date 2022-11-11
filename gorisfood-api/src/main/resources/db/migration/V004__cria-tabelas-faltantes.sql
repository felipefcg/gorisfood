ALTER TABLE estado ADD UF varchar(2) NOT NULL;

CREATE TABLE forma_pagamento (
	id BIGINT auto_increment NOT NULL,
	descricao varchar(20) NOT NULL,
	CONSTRAINT forma_pagamento_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

CREATE TABLE grupo (
	id BIGINT auto_increment NOT NULL,
	nome varchar(20) NOT NULL,
	CONSTRAINT grupo_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

CREATE TABLE permissao (
	id BIGINT auto_increment NOT NULL,
	nome VARCHAR(20) NOT NULL,
	descricao varchar(60) NOT NULL,
	CONSTRAINT permissao_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

CREATE TABLE grupo_permissao (
	grupo_id BIGINT NOT NULL,
	permissao_id BIGINT NOT NULL,
	CONSTRAINT grupo_permissao_pk PRIMARY KEY (grupo_id,permissao_id),
	CONSTRAINT grupopermissao_grupo_FK FOREIGN KEY (grupo_id) REFERENCES grupo(id),
	CONSTRAINT grupopermissao_permissao_FK FOREIGN KEY (permissao_id) REFERENCES permissao(id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

CREATE TABLE restaurante (
	id BIGINT auto_increment NOT NULL,
	nome varchar(80) NOT NULL,
	taxa_frete DECIMAL(10,2) NOT NULL,
	cozinha_id BIGINT NOT NULL,
	endereco_cep varchar(9) NULL,
	endereco_logradouro varchar(100) NULL,
	endereco_numero varchar(15) NULL,
	endereco_complemento varchar(15) NULL,
	endereco_bairro varchar(60) NULL,
	endereco_cidade_id BIGINT NULL,
	data_cadastro DATETIME NOT NULL,
	data_atualizacao DATETIME NOT NULL,
	CONSTRAINT restaurante_pk PRIMARY KEY (id),
	CONSTRAINT restaurante_FK FOREIGN KEY (cozinha_id) REFERENCES cozinha(id),
	CONSTRAINT restaurante_FK_1 FOREIGN KEY (endereco_cidade_id) REFERENCES cidade(id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

CREATE TABLE produto (
	id BIGINT auto_increment NOT NULL,
	nome varchar(60) NOT NULL,
	descricao text NOT NULL,
	preco DECIMAL(10,2) NOT NULL,
	ativo BOOL NOT NULL,
	restaurante_id BIGINT NOT NULL,
	CONSTRAINT produto_pk PRIMARY KEY (id),
	CONSTRAINT produto_FK FOREIGN KEY (restaurante_id) REFERENCES restaurante(id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

CREATE TABLE restaurante_forma_pagamento (
	restaurante_id BIGINT NOT NULL,
	forma_pagamento_id BIGINT NOT NULL,
	CONSTRAINT restaurante_forma_pagamento_pk PRIMARY KEY (restaurante_id,forma_pagamento_id),
	CONSTRAINT restauranteformapagamento_restaurante_FK FOREIGN KEY (restaurante_id) REFERENCES restaurante(id),
	CONSTRAINT restauranteformapagamento_forma_pagamento_FK FOREIGN KEY (forma_pagamento_id) REFERENCES forma_pagamento(id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

CREATE TABLE usuario (
	id BIGINT auto_increment NOT NULL,
	nome varchar(80) NOT NULL,
	email varchar(50) NOT NULL,
	senha VARCHAR(255) NOT NULL,
	data_cadastro DATETIME NOT NULL,
	CONSTRAINT usuario_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

CREATE TABLE usuario_grupo (
	usuario_id BIGINT NOT NULL,
	grupo_id BIGINT NOT NULL,
	CONSTRAINT usuario_grupo_pk PRIMARY KEY (usuario_id,grupo_id),
	CONSTRAINT usuario_grupo_FK FOREIGN KEY (usuario_id) REFERENCES usuario(id),
	CONSTRAINT usuario_grupo_FK_1 FOREIGN KEY (grupo_id) REFERENCES grupo(id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

