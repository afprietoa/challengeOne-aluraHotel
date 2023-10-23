# Alura-Hotel
Sistema de reserva de Hotel Alura trabajado en el marco del Programa ONE - Oracle Next Education


DROP DATABASE IF EXISTS alura_hotel;
CREATE DATABASE alura_hotel;

USE alura_hotel;

CREATE TABLE reserva(
id INT NOT NULL AUTO_INCREMENT,
fecha_entrada DATE NOT NULL,
fecha_salida DATE NOT NULL,
valor VARCHAR(50),
forma_de_pago VARCHAR(50) NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE huesped(
id INT NOT NULL AUTO_INCREMENT,
nombre VARCHAR(50) NOT NULL,
apellido VARCHAR(50) NOT NULL,
fecha_nacimiento DATE NOT NULL,
nacionalidad VARCHAR(50) NOT NULL,
telefono VARCHAR(50) NOT NULL,
id_reserva INT NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY(id_reserva) REFERENCES reserva(id)
);

CREATE TABLE usuario(
nombre VARCHAR(50),
contrasena VARCHAR(50)
);
