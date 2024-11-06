create table consultas(

    id bigint not null auto_increment,
    veterinarios_id bigint not null,
    animal_id bigint not null,
    data datetime not null,

    primary key(id),
    constraint fk_consultas_veterinarios_id foreign key(veterinarios_id) references veterinarios(id),
    constraint fk_consultas_animal_id foreign key(animal_id) references animais(id)

);