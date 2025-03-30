CREATE TABLE IF NOT EXISTS usuario_role (
    username VARCHAR(64),
    role VARCHAR(64),
    PRIMARY KEY (username, role),
    CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES usuario(username),
    CONSTRAINT fk_autoridade FOREIGN KEY (role) REFERENCES role(role)
);