create table foto_produto (
	produto_id bigint notnull,
	nome_arquivo varchar(150) not null,
	descricao varchar(150),
	content_type varchar (80) not null,
	tamanho int not null,
	
	primery key (produto_id),
	constraint foto_produto_produto_fk foreign key (produto_id) references produto (id)
) engine=InnoDB default charset=utf8;