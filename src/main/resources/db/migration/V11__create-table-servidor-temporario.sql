CREATE TABLE IF NOT EXISTS servidor_temporario (
    pes_id BIGINT PRIMARY KEY,
    st_data_admissao DATE NOT NULL,
    st_data_demissao DATE,
    CONSTRAINT fk_servidor_temporario_pessoa FOREIGN KEY (pes_id) REFERENCES pessoa(pes_id)
);