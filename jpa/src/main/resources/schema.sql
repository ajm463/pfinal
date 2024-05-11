DROP TABLE IF EXISTS usuario;

CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    tarifa VARCHAR(255) NOT NULL,
    clases_asistidas INTEGER NOT NULL DEFAULT 0,
    clases_quedan INTEGER NOT NULL
);

DROP TABLE IF EXISTS clase;
CREATE TABLE clase (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    nivel VARCHAR(255) NOT NULL,
    capacidad INTEGER NOT NULL
);

DROP TABLE IF EXISTS operacion;
CREATE TABLE operacion (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    clase_id BIGINT NOT NULL,
    hora_operacion TIME NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (clase_id) REFERENCES clase(id)
);

DROP TABLE IF EXISTS horario;
-- Crea la tabla horario
CREATE TABLE horario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    clase_id BIGINT NOT NULL,
    horario VARCHAR(255) NOT NULL,
    FOREIGN KEY (clase_id) REFERENCES clase(id)
);
DROP TABLE IF EXISTS token;
-- Crea la tabla token
CREATE TABLE token (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    token VARCHAR(255) NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);