CREATE TABLE animais (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    especie VARCHAR(100) NOT NULL,
    vacina VARCHAR(100) NOT NULL,
    sexo VARCHAR(100) NOT NULL,
    cor VARCHAR(100) NOT NULL,
    dataNascimento DATE NOT NULL,
    descricao VARCHAR(100) NOT NULL,
    raca VARCHAR(100) NOT NULL,
    peso VARCHAR(100) NOT NULL,
    ativo TINYINT NOT NULL,
     PRIMARY KEY (id),

    clientes_id BIGINT,

    CONSTRAINT fk_animais_clientes_id FOREIGN KEY (clientes_id) REFERENCES clientes(id)
);
