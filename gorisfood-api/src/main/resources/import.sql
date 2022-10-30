INSERT into cozinha (nome) value ('Tailandesa');
INSERT into cozinha (nome) value ('Indiana');

INSERT into restaurante (nome, taxa_frete, cozinha_id) value ('Maluquinho Pizzaria', 5.99, 1);
INSERT into restaurante (nome, taxa_frete, cozinha_id) value ('Reis Hot Dogs', 1.75, 2);
INSERT into restaurante (nome, taxa_frete, cozinha_id) value ('New York Burguer', 0.00, 2);

INSERT INTO forma_pagamento(descricao) value ('Dinheiro');
INSERT INTO forma_pagamento(descricao) value ('Cartão de Crédito');
INSERT INTO forma_pagamento(descricao) value ('Cartão de Débito');
INSERT INTO forma_pagamento(descricao) value ('PIX');

INSERT INTO permissao(nome, descricao) value ('Cadastro', 'Permite Cadastrar usuários');
INSERT INTO permissao(nome, descricao) value ('Cliente', 'Permite fazer pedidos');

INSERT INTO estado (uf, nome) value ('SP', 'São Paulo');
INSERT INTO estado (uf, nome) value ('MG', 'Minas Gerais');

INSERT INTO cidade (nome, estado_id) value ('Franco da Rocha', 1);
INSERT INTO cidade (nome, estado_id) value ('Uberlândia', 2);

insert into restaurante_forma_pagamento values (1, 1), (1, 2), (1, 3), (1, 4), (2, 2), (2, 3), (3, 1);