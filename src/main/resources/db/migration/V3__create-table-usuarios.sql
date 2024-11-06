create table usuarios(

    id bigint not null auto_increment,
    login varchar(100) not null,
    senha varchar(255) not null,
    role VARCHAR(50) NOT NULL,
    primary key(id)

);