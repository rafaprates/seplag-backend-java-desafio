CREATE TABLE IF NOT EXISTS usuario (
    username VARCHAR(64) PRIMARY KEY, -- Chave primária natural
    senha VARCHAR(72) NOT NULL, -- Comprimento do hash produzido pelo BCrypt
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);