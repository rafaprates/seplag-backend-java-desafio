CREATE TABLE IF NOT EXISTS foto_pessoa (
    fp_id SERIAL PRIMARY KEY,
    pes_id BIGINT NOT NULL,
    fp_data DATE NOT NULL,
    fp_bucket VARCHAR(50) NOT NULL,
    fp_hash VARCHAR(50) NOT NULL,
    CONSTRAINT fk_foto_pessoa_pessoa FOREIGN KEY (pes_id) REFERENCES pessoa(pes_id) ON DELETE CASCADE
);