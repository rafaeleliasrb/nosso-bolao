create table usuario (
	
	id bigint not null auto_increment,
	login varchar(255) not null,
	senha varchar(255) not null,
	data_criacao datetime not null,
	
	primary key(id),
	unique key uk_login (login)

) engine=InnoDB default charset=utf8;