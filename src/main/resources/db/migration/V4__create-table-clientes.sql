CREATE TABLE clientes (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    telefone VARCHAR(20) NOT NULL,
    endereco_id BIGINT UNIQUE,
    FOREIGN KEY (endereco_id) REFERENCES enderecos(id),
    ativo TINYINT NOT NULL,
    PRIMARY KEY (id)
);