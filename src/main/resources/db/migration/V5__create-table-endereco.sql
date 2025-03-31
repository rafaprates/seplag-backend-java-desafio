CREATE TABLE IF NOT EXISTS endereco (
    end_id SERIAL PRIMARY KEY,
    end_tipo_logradouro VARCHAR(50) NOT NULL,
    end_logradouro VARCHAR(200) NOT NULL,
    end_numero INT,
    end_bairro VARCHAR(100),
    cid_id INT NOT NULL,
    CONSTRAINT fk_cidade FOREIGN KEY (cid_id) REFERENCES cidade(cid_id)
);
