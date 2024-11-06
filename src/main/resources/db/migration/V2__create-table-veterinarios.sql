create table veterinarios(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    crm varchar(6) not null unique,
    cpf varchar(100) not null,
    telefone varchar(100) not null,
    especialidade varchar(100) not null,
    ativo TINYINT NOT NULL,
    endereco_id BIGINT UNIQUE,
    FOREIGN KEY (endereco_id) REFERENCES enderecos(id),
    primary key(id)

);