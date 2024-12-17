INSERT INTO enderecos (logradouro, bairro, cep, complemento, numero, uf, cidade) VALUES
    ('Rua das Flores', 'Centro', '12345-678', 'Apto 101', '123', 'SP', 'São Paulo'),
    ('Avenida Paulista', 'Bela Vista', '12345-679', 'Sala 201', '200', 'SP', 'São Paulo'),
    ('Rua XV de Novembro', 'Centro', '23456-789', 'Casa', '305', 'PR', 'Curitiba'),
    ('Rua Goiás', 'Bairro Alto', '34567-890', 'Casa', '402', 'MG', 'Belo Horizonte'),
    ('Rua da Praia', 'Centro', '45678-901', 'Loja 10', '510', 'RS', 'Porto Alegre'),
    ('Avenida Brasil', 'Zona Norte', '56789-012', 'Ponto Comercial', '15', 'RJ', 'Rio de Janeiro'),
    ('Rua da Paz', 'Vila Mariana', '67890-123', 'Apartamento 303', '711', 'SP', 'São Paulo'),
    ('Rua dos Três Irmãos', 'Jardim Botânico', '78901-234', 'Prédio 2', '82', 'MG', 'Belo Horizonte'),
    ('Avenida das Américas', 'Recreio', '89012-345', 'Casa 12', '33', 'RJ', 'Rio de Janeiro'),
    ('Rua Major Sertório', 'Vila Progredir', '90123-456', 'Casa', '25', 'BA', 'Salvador');







INSERT INTO clientes (nome, email, cpf, telefone, endereco_id, ativo) VALUES
    ('Ana Souza', 'ana.souza@email.com', '123.456.789-00', '11 98765-4321', 1, 1),
    ('Pedro Silva', 'pedro.silva@email.com', '987.654.321-00', '11 98876-5432', 2, 1),
    ('Juliana Costa', 'juliana.costa@email.com', '111.222.333-44', '11 99987-6543', 3, 1),
    ('Lucas Oliveira', 'lucas.oliveira@email.com', '555.444.333-22', '11 96666-7777', 4, 1),
    ('Mariana Lima', 'mariana.lima@email.com', '777.666.555-11', '11 95555-8888', 5, 1),
    ('Carlos Souza', 'carlos.souza@email.com', '888.777.666-33', '11 94444-9999', 6, 1),
    ('Fernanda Ribeiro', 'fernanda.ribeiro@email.com', '999.888.777-44', '11 93333-0000', 7, 1),
    ('João Santos', 'joao.santos@email.com', '111.222.444-55', '11 92222-1111', 8, 1),
    ('Luana Pereira', 'luana.pereira@email.com', '333.444.555-66', '11 91111-2222', 9, 1),
    ('Ricardo Almeida', 'ricardo.almeida@email.com', '444.555.666-77', '11 90000-3333', 10, 1);

INSERT INTO animais (nome, especie, vacina, sexo, cor, data_nascimento, descricao, raca, peso, foto, ativo, clientes_id) VALUES
    ('Rex', 'Cão', 'Raiva, Polivalente', 'Macho', 'Marrom', '2020-01-15', 'Cachorro de grande porte, muito ativo e amigável.', 'Pitbull', '30kg', 'https://res.cloudinary.com/dfgbasaxa/image/upload/v1734376537/ohmt4kgaihblqnq2gnxd.webp', 1, 1),
    ('Mia', 'Cão', 'Polivalente', 'Fêmea', 'Branco', '2019-05-23', 'Cão de porte pequeno, muito carinhosa e independente.', 'Pug', '4kg', 'https://res.cloudinary.com/dfgbasaxa/image/upload/v1734376537/emj9ilokueoh9kaobqqb.webp', 1, 2),
    ('Toby', 'Cão', 'Raiva, Polivalente', 'Macho', 'Preto', '2018-08-14', 'Cão de pequeno porte, muito inteligente e protetor.', 'Pinscher', '6kg', 'https://res.cloudinary.com/dfgbasaxa/image/upload/v1734376537/pigatbllmb3tgtobzuyi.webp', 1, 3),
    ('Bella', 'Cão', 'Raiva, Polivalente', 'Fêmea', 'Branca', '2021-12-01', 'Cachorra muito ativa e dócil, ótima para famílias.', 'chihuahua', '25kg', 'https://res.cloudinary.com/dfgbasaxa/image/upload/v1734376537/geubvdcdltpoj8spthtd.webp', 1, 4),
    ('Simba', 'Gato', 'Raiva, Polivalente', 'Macho', 'Dourado', '2020-03-10', 'Gato grande, muito brincalhão e curioso.', 'Beagle', '7kg', 'https://res.cloudinary.com/dfgbasaxa/image/upload/v1734376537/j6st9mqljpg8vbzvqgpk.webp', 1, 5),
    ('Luna', 'Cão', 'Raiva, Polivalente', 'Fêmea', 'Cinza', '2019-07-20', 'Cachorra de médio porte, muito amiga das crianças.', 'Bulldog', '18kg', 'https://res.cloudinary.com/dfgbasaxa/image/upload/v1734376537/d2q3eddzzgqraubrfzkm.webp', 1, 6),
    ('Zeus', 'Cão', 'Raiva, Polivalente', 'Macho', 'Branco', '2018-10-05', 'Cachorro forte e protetor, muito leal ao dono.', 'Gato abissínio', '40kg', 'https://res.cloudinary.com/dfgbasaxa/image/upload/v1734376537/ia5ur30plxq70k95kj2m.webp', 1, 7),
    ('Lola', 'cobra', 'Raiva, Polivalente', 'Fêmea', 'Preto', '2020-06-13', 'cobra muito tranquila e afetuosa.', 'Jiboia', '5kg', 'https://res.cloudinary.com/dfgbasaxa/image/upload/v1734376537/vqdefigucycg0tizfgnn.webp', 1, 8),
    ('Charlie', 'Cão', 'Raiva, Polivalente', 'Macho', 'Preto e Branco', '2019-11-22', 'Cachorro amigável e obediente.', 'Golden Retriever', '28kg', 'https://res.cloudinary.com/dfgbasaxa/image/upload/v1734376537/b36eqdoeugexfdvvesks.webp', 1, 10);

INSERT INTO produtos (nome, categoria, quantidade, valor_compra, valor_venda, codigo, unidade_de_medida, min_estoque, ativo) VALUES
    ('Ração para Cães', 'Alimentação', 100, 50.00, 80.00, 'RC123', 'kg', 10, 1),
    ('Ração para Gatos', 'Alimentação', 150, 45.00, 70.00, 'RG456', 'kg', 15, 1),
    ('Shampoo para Pets', 'Higiene', 200, 25.00, 40.00, 'SP789', 'unidade', 20, 1),
    ('Brinquedo para Cães', 'Brinquedos', 120, 10.00, 20.00, 'BC012', 'unidade', 5, 1),
    ('Brinquedo para Gatos', 'Brinquedos', 100, 8.00, 15.00, 'BG345', 'unidade', 5, 1),
    ('Coleira para Cães', 'Acessórios', 50, 30.00, 60.00, 'CC678', 'unidade', 5, 1),
    ('Peitoral para Gatos', 'Acessórios', 80, 20.00, 40.00, 'PG901', 'unidade', 10, 1),
    ('Medicamento Antipulgas', 'Medicamentos', 200, 15.00, 30.00, 'MA234', 'unidade', 10, 1),
    ('Vetores de Pulgas', 'Medicamentos', 250, 10.00, 20.00, 'VP567', 'unidade', 15, 1),
    ('Cama para Cães', 'Acessórios', 30, 70.00, 120.00, 'CC890', 'unidade', 5, 1);
