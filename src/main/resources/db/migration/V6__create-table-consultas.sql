create table consultas(

    id bigint not null auto_increment,
    veterinarios_id bigint not null,
    paciente_id bigint not null,
    data datetime not null,

    primary key(id),
    constraint fk_consultas_veterinarios_id foreign key(veterinarios_id) references veterinarios(id),
    constraint fk_consultas_paciente_id foreign key(paciente_id) references pacientes(id)

);