    create table funcionarios(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    telefone varchar(100) not null,
    cargo  varchar(100) not null,
    email varchar(100) not null,
    endereco_id BIGINT UNIQUE,
    FOREIGN KEY (endereco_id) REFERENCES enderecos(id),
    ativo TINYINT NOT NULL,

    primary key(id)
);
