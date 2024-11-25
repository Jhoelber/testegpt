CREATE TABLE agendas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    data DATETIME  NOT NULL,
    animal_id BIGINT NOT NULL,
    servico_id BIGINT NOT NULL,
    status VARCHAR(50),
    FOREIGN KEY (animal_id) REFERENCES animais(id),
    FOREIGN KEY (servico_id) REFERENCES servicos(id)
);