CREATE TABLE itensvenda (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    venda_id BIGINT NOT NULL,
    produto_id BIGINT,
    servico_id BIGINT,
    quantidade INT NOT NULL,
    valor_unitario DECIMAL(10, 2) NOT NULL,
    valor_total DECIMAL(10, 2) NOT NULL,

    CONSTRAINT fk_venda FOREIGN KEY (venda_id) REFERENCES vendas(id),
    CONSTRAINT fk_produto FOREIGN KEY (produto_id) REFERENCES produtos(id),
    CONSTRAINT fk_servico FOREIGN KEY (servico_id) REFERENCES servicos(id)
);
