CREATE TABLE servicio
(
    id     SERIAL PRIMARY KEY,
    tipo   VARCHAR(50)    NOT NULL,
    precio DECIMAL(10, 2) NOT NULL
);

CREATE TABLE automotor
(
    id     SERIAL PRIMARY KEY,
    marca  VARCHAR(100) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    year   INT          NOT NULL
);

CREATE TABLE cliente
(
    id           SERIAL PRIMARY KEY,
    nombres      VARCHAR(100) NOT NULL,
    apellidos    VARCHAR(100) NOT NULL,
    vip          BOOLEAN      NOT NULL,
    id_servicio  INTEGER      NOT NULL,
    id_automotor INTEGER      NOT NULL,
    FOREIGN KEY (id_servicio) REFERENCES servicio (id),
    FOREIGN KEY (id_automotor) REFERENCES automotor (id)
);

INSERT INTO servicio (tipo, precio)
VALUES ('Motocicleta', 2.75),
       ('Sedan', 3.50),
       ('Camioneta', 4.00),
       ('Microbús', 5.00),
       ('Autobús', 7.00);
