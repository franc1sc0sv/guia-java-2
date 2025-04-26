CREATE TABLE contacto
(
    id       SERIAL PRIMARY KEY,
    nombre   VARCHAR(100) NOT NULL,
    telefono VARCHAR(20)  NOT NULL,
    correo   VARCHAR(100) NOT NULL
);
