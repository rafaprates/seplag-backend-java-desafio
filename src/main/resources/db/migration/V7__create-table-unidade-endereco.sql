CREATE TABLE IF NOT EXISTS unidade_endereco (
    unid_id BIGINT NOT NULL,
    end_id BIGINT NOT NULL,
    CONSTRAINT fk_unidade FOREIGN KEY (unid_id) REFERENCES unidade(unid_id),
    CONSTRAINT fk_endereco FOREIGN KEY (end_id) REFERENCES endereco(end_id)
);
