create table (

	id bigint not null auto_increment,
	nome varchar(255) not null,
	data_fundacao datetime not null;
	
	primary key (id),
	unique key (nome)
	
) engine=InnoDB default charset=utf8;