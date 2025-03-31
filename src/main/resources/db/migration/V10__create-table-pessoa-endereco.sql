CREATE TABLE IF NOT EXISTS pessoa_endereco (
    pes_id BIGINT NOT NULL,
    end_id SERIAL NOT NULL,
    CONSTRAINT fk_pessoa_endereco_pessoa FOREIGN KEY (pes_id) REFERENCES pessoa(pes_id) ON DELETE CASCADE,
    CONSTRAINT fk_pessoa_endereco_endereco FOREIGN KEY (end_id) REFERENCES endereco(end_id) ON DELETE CASCADE
);