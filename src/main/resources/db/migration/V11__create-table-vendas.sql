CREATE TABLE vendas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    funcionario_id BIGINT NOT NULL,
    quantidade INT NOT NULL,
    data_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    forma_pagamento VARCHAR(255) NOT NULL,
    valor_total DECIMAL(10, 2) NOT NULL,
    tipo_venda ENUM('PRODUTO', 'SERVIÇO', 'AMBOS') NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    FOREIGN KEY (funcionario_id) REFERENCES funcionarios(id)
);
