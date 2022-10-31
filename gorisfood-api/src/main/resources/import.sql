INSERT INTO estado (id, uf, nome) value (1, 'SP', 'São Paulo');
INSERT INTO estado (id, uf, nome) value (2, 'MG', 'Minas Gerais');

INSERT INTO cidade (id, nome, estado_id) value (1, 'Franco da Rocha', 1);
INSERT INTO cidade (id, nome, estado_id) value (2, 'Uberlândia', 2);

INSERT into cozinha (id, nome) value (1, 'Tailandesa');
INSERT into cozinha (id, nome) value (2, 'Indiana');

INSERT into restaurante (id, nome, taxa_frete, cozinha_id, endereco_bairro,endereco_cep,endereco_complemento,endereco_logradouro,endereco_numero,endereco_cidade_id, data_cadastro, data_atualizacao) 
	value (1, 'Maluquinho Pizzaria', 5.99, 1, 'Vila Zanela', '07846-130', 'N/A', 'Rua Roberto Simonsen', '99', 1, utc_timestamp, utc_timestamp);
INSERT into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) value (2, 'Reis Hot Dogs', 1.75, 2, utc_timestamp, utc_timestamp);
INSERT into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) value (3, 'New York Burguer', 0.00, 2, utc_timestamp, utc_timestamp);

INSERT INTO forma_pagamento(id, descricao) value (1, 'Dinheiro');
INSERT INTO forma_pagamento(id, descricao) value (2, 'Cartão de Crédito');
INSERT INTO forma_pagamento(id, descricao) value (3, 'Cartão de Débito');
INSERT INTO forma_pagamento(id, descricao) value (4, 'PIX');

INSERT INTO permissao(id, nome, descricao) value (1, 'Cadastro', 'Permite Cadastrar usuários');
INSERT INTO permissao(id, nome, descricao) value (2, 'Cliente', 'Permite fazer pedidos');

insert into restaurante_forma_pagamento values (1, 1), (1, 2), (1, 3), (1, 4), (2, 2), (2, 3), (3, 1);