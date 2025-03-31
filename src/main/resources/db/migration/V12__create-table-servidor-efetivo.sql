CREATE TABLE IF NOT EXISTS servidor_efetivo (
    pes_id BIGINT PRIMARY KEY,
    se_matricula VARCHAR(20) UNIQUE NOT NULL,
    CONSTRAINT fk_servidor_efetivo_pessoa FOREIGN KEY (pes_id) REFERENCES pessoa(pes_id)
);