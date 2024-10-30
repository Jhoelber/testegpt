CREATE TABLE produtos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    quantidade INT NOT NULL,
    valor_compra DECIMAL(10, 2) NOT NULL,
    valor_venda DECIMAL(10, 2) NOT NULL,
    codigo VARCHAR(100) NOT NULL,
    unidade_de_medida VARCHAR(9) NOT NULL,
    min_estoque INT NOT NULL,
    ativo TINYINT NOT NULL,
    PRIMARY KEY (id)
);