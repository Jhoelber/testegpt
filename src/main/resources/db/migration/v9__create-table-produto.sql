create table produtos(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    categoria varchar(100) not null,
    quantidade  varchar(6) not null,
    valor_compra varchar(100) not null,
    valor_venda varchar(100) not null,
    codigo varchar(100) not null,
    unidade_de_medida varchar(9) not null,
    min_Estoque varchar(100) not null,

    primary key(id)
);
