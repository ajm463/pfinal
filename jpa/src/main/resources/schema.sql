CREATE TABLE IF NOT EXISTS Clase (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    nivel VARCHAR(255) NOT NULL,
    capacidad INT NOT NULL,
    plazasDisponibles INT NOT NULL
);

CREATE TABLE IF NOT EXISTS  Horario (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  clase_id BIGINT NOT NULL,
  horario TIME NOT NULL,
  dia VARCHAR(10) NOT NULL,
  FOREIGN KEY (clase_id) REFERENCES Clase(id)
);


CREATE TABLE IF NOT EXISTS  Operacion (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    clase_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    horaOperacion TIME NOT NULL,
    esInscripcion BOOLEAN NOT NULL,
    FOREIGN KEY (clase_id) REFERENCES Clase(id),
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id)
);

CREATE TABLE IF NOT EXISTS  Usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    tarifa INT NOT NULL,
    clasesQuedan INT NOT NULL,
    clasesAsistidas INT NOT NULL
);

CREATE TABLE IF NOT EXISTS  Token (
    id UUID PRIMARY KEY,
    usuario_id BIGINT NOT NULL UNIQUE,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id) ON DELETE CASCADE
);
