/**descomentar si se borraran tablas para pruebas**/
/*
DROP TABLE oina_usuario;
DROP TABLE oina_autor;
DROP TABLE oina_categoria;
DROP TABLE oina_libro;
DROP TABLE oina_prestamo;
DROP TABLE oina_logs;
*/

--creación de la tabla usuario
CREATE TABLE oina_usuario(
    nombre VARCHAR2(15 BYTE) NOT NULL,
    clave VARCHAR2(256 BYTE) NOT NULL,
    tipo_usuario NUMBER(1) NOT NULL /*0=usuario,1=admin"*/,
    CONSTRAINT pk_usuario PRIMARY KEY(nombre)
);

--creación de la tabla autor
CREATE TABLE oina_autor(
    id_autor NUMBER GENERATED by default on null as IDENTITY (START WITH 1 INCREMENT BY 1), 
    nombre VARCHAR2(50 BYTE) NOT NULL, 
    CONSTRAINT pk_autor PRIMARY KEY(id_autor)
);

--creacion de la tabla categoria
CREATE TABLE oina_categoria(
    id_categoria NUMBER GENERATED by default on null as IDENTITY(START WITH 1 INCREMENT BY 1),
    nombre VARCHAR2(50 BYTE) NOT NULL,
    CONSTRAINT pk_categoria PRIMARY KEY (id_categoria)
);

--creación de la tabla libro
CREATE TABLE oina_libro(
    id_libro VARCHAR2(10 BYTE) NOT NULL,
    nombre VARCHAR2(50 BYTE) NOT NULL, 
    cantidad NUMBER(9) NOT NULL,
    precio NUMBER(9,2) NOT NULL,
    estado NUMBER NOT NULL /* 0=No disponible, 1= disponible*/,
    id_autor NUMBER (9),
    id_categoria NUMBER(9), 
    CONSTRAINT pk_libro PRIMARY KEY (id_libro)
);

--creacion de tabla prestamo
CREATE TABLE oina_prestamo(
    id_usuario VARCHAR2(15 BYTE) NOT NULL, 
    fecha_prestamo DATE NOT NULL,
    fecha_devolucion_estimada DATE NOT NULL,
    fecha_devolucion_real DATE NULL,
    id_libro VARCHAR2(10 BYTE)
);

--creación de la tabla log
CREATE TABLE oina_logs(
    id NUMBER GENERATED by default on null as IDENTITY(START WITH 1 INCREMENT BY 1), 
    accion VARCHAR2(300 BYTE),
    fecha DATE, 
    usuario VARCHAR2(15 BYTE)
);

/**alters**/

ALTER TABLE oina_libro ADD CONSTRAINT fk_libro_autor FOREIGN KEY (id_autor) REFERENCES
oina_autor (id_autor);

ALTER TABLE oina_libro ADD CONSTRAINT fk_libro_categoria FOREIGN KEY (id_categoria) 
REFERENCES oina_categoria(id_categoria);

ALTER TABLE oina_prestamo ADD
CONSTRAINT fk_prestamo_usuario 
FOREIGN KEY (id_usuario) REFERENCES oina_usuario(nombre);

ALTER TABLE oina_prestamo ADD
CONSTRAINT fk_prestamo_libro FOREIGN KEY (id_libro)
REFERENCES oina_libro(id_libro);

/**inserts**/
insert into oina_usuario values('admin', 'NFZDYXgzUTMvdnBkbnF6QWVtd1hRdz09OjoAAAAAAAAAAAAAAAAAAAAAOjokMmEkMTAkbA==', 1);

insert into oina_autor values(null,'Platón');
insert into oina_autor values(null,'Shakespeare');

insert into oina_categoria values(null, 'Filosofía');
insert into oina_categoria values(null, 'Tragedia');

insert into oina_libro values('F-001','La República - Tomo 1',1,60.58,1,1);
insert into oina_libro values('T-001','Hamlet',1,89.26,2,2);
commit;


/*Procedimiento cambiar a estado inactivo una categoria completa de libro (para dar mantenimiento)*/

CREATE OR REPLACE PROCEDURE proc_mant_cat (id_cat IN NUMBER) AS
    CURSOR c_lib IS SELECT id_libro FROM oina_libro WHERE id_categoria=id_cat;  
    idlib NUMBER;
        BEGIN
            FOR v_lib IN c_lib LOOP
            idlib := v_lib.id_libro;
            UPDATE oina_libro SET estado = 0 WHERE id_libro = idlib;
            END LOOP;
        END;

/*Procedimiento para revisar libro por libro, 
para cambiar su estado a no disponible si ya no hay 
en existencia en la biblioteca*/

CREATE OR REPLACE PROCEDURE proc_check_disp IS
    CURSOR c_estado IS SELECT id_libro, cantidad FROM oina_libro;
    cant NUMBER;
    id_lib VARCHAR2(10 BYTE);
        BEGIN
            FOR v_estado IN c_estado LOOP
            id_lib := v_estado.id_libro;
            cant := v_estado.cantidad;
            IF cant < 1 THEN
                UPDATE oina_libro SET estado = 0 WHERE id_libro = id_lib;
            END IF;
            END LOOP;
        END;

/*TRIGGER PARA INVOCARLO*/

CREATE OR REPLACE TRIGGER trigg_check_dispo AFTER INSERT ON oina_prestamo
BEGIN
    proc_check_disp();
END;