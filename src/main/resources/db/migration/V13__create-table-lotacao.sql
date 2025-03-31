CREATE TABLE IF NOT EXISTS lotacao (
    lot_id SERIAL PRIMARY KEY,
    pes_id BIGINT NOT NULL,
    unid_id BIGINT NOT NULL,
    lot_data_lotacao DATE NOT NULL,
    lot_data_remocao DATE,
    lot_portaria VARCHAR(100),
    CONSTRAINT fk_lotacao_pessoa FOREIGN KEY (pes_id) REFERENCES pessoa(pes_id),
    CONSTRAINT fk_lotacao_unidade_organizacional FOREIGN KEY (unid_id) REFERENCES unidade(unid_id)
);