create table usuarios (
    id bigint not null auto_increment,
    login varchar(100) not null,
    senha varchar(255) not null,
    role VARCHAR(50) NOT NULL,
    nome varchar(100) not null,
    telefone varchar(100) not null,
    cargo varchar(100) not null,
    email varchar(100) not null,
    cpf varchar(15) not null,
    endereco_id BIGINT,
    ativo TINYINT NOT NULL,
    primary key(id),
    foreign key (endereco_id) references enderecos(id)
);
