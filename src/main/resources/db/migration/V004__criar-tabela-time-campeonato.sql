create table time_campeonato (

	time_id bigint not null,
	campeonato_id bigint not null,
	
	primary key (time_id, campeonato_id),
	constraint fk_time_campeonato_time foreign key (time_id) references time (id),
	constraint fk_time_campeonato_campeonato foreign key (campeonato_id) references campeonato (id)
	
) engine=InnoDB default charset=utf8;