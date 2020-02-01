create table campeonato (

	id bigint not null auto_increment,
	nome varchar(255) not null,
	data_inicio datetime not null,
	quantidade_times int not null,
	
	primary key (id),
	unique key (nome)
	
) engine=InnoDB default charset=utf8;