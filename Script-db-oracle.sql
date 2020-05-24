--creación de la tabla usuario
CREATE TABLE usuario(nombre VARCHAR2(15 BYTE) NOT NULL,
clave VARCHAR2(256 BYTE) NOT NULL,
tipo_usuario NUMBER(1) NOT NULL /*0=usuario,1=admin"*/,
CONSTRAINT pk_usuario PRIMARY KEY(nombre));

--creación de la tabla autor
CREATE TABLE autor(id_autor NUMBER GENERATED ALWAYS AS IDENTITY
(START WITH 1 INCREMENT BY 1), nombre VARCHAR2(50 BYTE) NOT NULL, 
CONSTRAINT pk_autor PRIMARY KEY(id_autor));

--creacion de la tabla categoria
CREATE TABLE categoria(id_categoria NUMBER GENERATED ALWAYS AS
IDENTITY(START WITH 1 INCREMENT BY 1), nombre VARCHAR2(50 BYTE)
NOT NULL, CONSTRAINT pk_categoria PRIMARY KEY (id_categoria));

--creación de la tabla libro
CREATE TABLE libro(id_libro VARCHAR2(10 BYTE) NOT NULL,
nombre VARCHAR2(50 BYTE) NOT NULL, cantidad NUMBER(9) NOT NULL,
precio NUMBER(9,2) NOT NULL, id_autor NUMBER (9),
id_categoria NUMBER(9), CONSTRAINT pk_libro PRIMARY KEY (id_libro));

ALTER TABLE libro ADD CONSTRAINT fk_libro_autor FOREIGN KEY (id_autor) REFERENCES
autor (id_autor);

ALTER TABLE libro ADD CONSTRAINT fk_libro_categoria FOREIGN KEY (id_categoria) 
REFERENCES categoria(id_categoria);

--creacion de tabla prestamo
CREATE TABLE prestamo(id_usuario VARCHAR2(15 BYTE) NOT NULL, 
fecha_devolucion DATE NOT NULL,id_libro VARCHAR2(10 BYTE));

ALTER TABLE prestamo ADD
CONSTRAINT fk_prestamo_usuario 
FOREIGN KEY (id_usuario) REFERENCES usuario(nombre);

ALTER TABLE prestamo ADD
CONSTRAINT fk_prestamo_libro FOREIGN KEY (id_libro)
REFERENCES libro(id_libro);

--creación de la tabla log
CREATE TABLE logs(id NUMBER GENERATED ALWAYS AS IDENTITY
(START WITH 1 INCREMENT BY 1), accion VARCHAR2(300 BYTE),
fecha DATE, usuario VARCHAR2(15 BYTE));