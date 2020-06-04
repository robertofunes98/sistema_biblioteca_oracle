/**comentar si es la primera vez que corres el script, 
asegurate de no tener ninguna tabla en la base da datos a usar**/
DROP TABLE oina_prestamo;

DROP TABLE oina_usuario;
DROP TABLE oina_libro;
DROP TABLE oina_autor;
DROP TABLE oina_categoria;
DROP TABLE oina_datos;
DROP TABLE oina_logs;

drop table oina_libros_borrados;
drop table oina_libros_modificados;


--creación de la tabla usuario
CREATE TABLE oina_usuario(
    nombre VARCHAR2(15 BYTE) NOT NULL,
    clave VARCHAR2(256 BYTE) NOT NULL,
    nombres varchar2(50 BYTE) null, 
    apellidos varchar2(50 BYTE) null,
    tipo_usuario NUMBER(1) NOT NULL /*0=usuario,1=admin, 2 = prestamista"*/,
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
    precio_euros NUMBER(9,2) NULL,
    estado NUMBER NOT NULL /* 0=No disponible, 1= disponible*/,
    id_autor NUMBER (9),
    id_categoria NUMBER(9), 
    CONSTRAINT pk_libro PRIMARY KEY (id_libro)
);

--creacion de tabla prestamo
CREATE TABLE oina_prestamo(
    id_prestamo NUMBER GENERATED by default on null as IDENTITY(START WITH 1 INCREMENT BY 1),
    fecha_prestamo DATE NOT NULL,
    fecha_devolucion_estimada DATE NOT NULL,
    fecha_devolucion_real DATE NULL,
    estado varchar2(15 BYTE) not null,/*activo, inactivo*/
    id_usuario VARCHAR2(15 BYTE) NOT NULL, 
    id_libro VARCHAR2(10 BYTE)
);

--creación de la tabla log
CREATE TABLE oina_logs(
    id NUMBER GENERATED by default on null as IDENTITY(START WITH 1 INCREMENT BY 1), 
    accion VARCHAR2(300 BYTE),
    fecha DATE, 
    usuario VARCHAR2(15 BYTE)
);

--creación de la tabla datos
CREATE TABLE oina_datos(
    id_datos NUMBER GENERATED by default on null as IDENTITY(START WITH 1 INCREMENT BY 1), 
    nombre VARCHAR2(300 BYTE),
    valor VARCHAR2(300 BYTE)
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
insert into oina_usuario values('admin', 'NFZDYXgzUTMvdnBkbnF6QWVtd1hRdz09OjoAAAAAAAAAAAAAAAAAAAAAOjokMmEkMTAkbA==', 'Juan Ernesto', 'Perez Hernandez', 1);

insert into oina_datos values(null,'precio en euros',null);
insert into oina_datos values(null,'precio en dolares',null);
insert into oina_datos values(null,'cantidad total de libros',null);
insert into oina_datos values(null,'cantidad total de autores',null);
insert into oina_datos values(null,'promedio libros por autor',null);

insert into oina_autor values(null,'Platón');
insert into oina_autor values(null,'Shakespeare');

insert into oina_categoria values(null, 'Filosofía');
insert into oina_categoria values(null, 'Tragedia');

insert into oina_libro values('F-001','La República - Tomo 1',1,60.58, 0.00,1,1,1);
insert into oina_libro values('T-001','Hamlet',1,89.26, 12.3, 1,2,2);

insert into oina_prestamo 
values(null, TO_DATE('2020-5-30', 'YYYY-MM-DD'), TO_DATE('2020-6-10', 'YYYY-MM-DD'), null, 'activo', 'admin', 'F-001');

commit;


/*Procedimiento cambiar a estado inactivo una categoria completa de libro (para dar mantenimiento)*/

CREATE OR REPLACE PROCEDURE oina_proc_mant_cat (id_cat IN NUMBER) AS
CURSOR c_lib IS SELECT id_libro FROM oina_libro WHERE id_categoria=id_cat;  
idlib VARCHAR2(10 BYTE);
BEGIN
    FOR v_lib IN c_lib LOOP
    idlib := v_lib.id_libro;
    UPDATE oina_libro SET estado = 0 WHERE id_libro = idlib;
    END LOOP;
END;
/
/*Procedimiento para revisar libro por libro, 
para cambiar su estado a no disponible si ya no hay 
en existencia en la biblioteca*/

CREATE OR REPLACE PROCEDURE oina_proc_check_disp IS
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
 /
/*TRIGGER PARA INVOCARLO*/

CREATE OR REPLACE TRIGGER oina_trigg_check_dispo AFTER INSERT ON oina_prestamo
FOR EACH ROW
BEGIN
    UPDATE oina_libro set cantidad = (cantidad-1) where id_libro = :NEW.id_libro;
    oina_proc_check_disp;
END;
/
/*VISTA LISTADO DE LIBROS ----- no se usara*/
CREATE OR REPLACE VIEW v_lista_libros as
select l.id_libro, 
       l.nombre,
       l.cantidad,
       l.precio,
       l.id_autor,
       l.id_categoria
from oina_libro l
order by l.id_libro asc;
/
/*VISTA  LISTA AUTORES*/
CREATE OR REPLACE VIEW v_lista_autores as 
select a.id_autor,
       a.nombre
from oina_autor a
order by a.id_autor asc;
/
/*VISTA LISTA DE CATEGORIAS*/
CREATE OR REPLACE VIEW v_lista_categorias as
select c.id_categoria,
       c.nombre
from oina_categoria c
order by c.id_categoria asc;
/



/*FUNCION TOTAL LIBROS POR AUTOR*/
CREATE OR REPLACE FUNCTION oina_funct_libros_autor(autor in number)
RETURN NUMBER
IS libros NUMBER;
BEGIN
  select count(oina_libro.nombre) as "Cantidad de libros" into libros from oina_autor
  inner join oina_libro on oina_libro.id_autor = oina_autor.id_autor
  where oina_autor.id_autor = autor
  group by oina_autor.nombre;
  RETURN (libros);
END;
/

/*FUNCION TOTAL PRECIO CONJUNTO DE LIBROS*/
CREATE OR REPLACE FUNCTION f_mayor_precio 
RETURN VARCHAR
IS libro_mayor_precio VARCHAR(30);
BEGIN   
    select nombre into libro_mayor_precio from(select nombre, (precio * cantidad) as maximo from oina_libro
    order by maximo desc)
    where rownum = 1;
    RETURN (libro_mayor_precio);
END;
/

--funcion que se encarga de crear nuevos usarios revisando que este no exista en la base de datos
create or replace function oina_func_crearNuevoUsuario(
    username in VARCHAR2, 
    password in VARCHAR2, 
    nombres in varchar2,
    apellidos in varchar2,
    user_type in number, 
    user_thats_create in varchar2
) 
return number
is total number;
begin
    select count(*) into total from oina_usuario where oina_usuario.nombre = username;
    
    if total > 0 then
        raise_application_error( -20001, 'el usuario ya existe en la base de datos');
        dbms_output.put_line('el usuario ya existe en la base de datos');
    else
        insert into oina_usuario values(username, password, nombres, apellidos, user_type);
        
        insert into oina_logs values(null, 'usuario nuevo creado', SYSDATE, user_thats_create);
    end if;
    
    COMMIT;

    RETURN 1;
exception
    WHEN OTHERS THEN
    dbms_output.put_line('Error en la transaccion:' || SQLERRM);
    dbms_output.put_line('Se deshacen las modificaciones');
    ROLLBACK;
    RETURN 0;
end;
/
/*Triggers********************/

/*1) Libros borrados

nota: seria de crear una tabla que se llame oina_libros_borrados mejor, que sea exactamente igual a la orinal 
y pues guarde el vlaor del libro borrado, solo que seria de agregar un id auto increment como lalve primaria y el codigo de libro
pasaria  ser simplemente un campo
*/

CREATE TABLE oina_libros_borrados(
    id_libro_borrado NUMBER GENERATED by default on null as IDENTITY(START WITH 1 INCREMENT BY 1),
    id_libro VARCHAR2(10 BYTE) NOT NULL,
    nombre VARCHAR2(50 BYTE) NOT NULL, 
    cantidad NUMBER(9) NOT NULL,
    precio NUMBER(9,2) NOT NULL,
    precio_euros NUMBER(9,2) NULL,
    estado NUMBER NOT NULL,
    id_autor NUMBER (9),
    id_categoria NUMBER(9), 
    CONSTRAINT pk_libro_borrado PRIMARY KEY (id_libro_borrado)
);
/

CREATE OR REPLACE TRIGGER oina_registrar_cambios
AFTER DELETE ON oina_libro
FOR EACH ROW
BEGIN
  INSERT INTO oina_libros_borrados(id_libro_borrado, id_libro, nombre, cantidad, precio, precio_euros, estado, id_autor, id_categoria)
  VALUES (null, :OLD.id_libro, :OLD.nombre, :OLD.cantidad, :OLD.precio, :OLD.precio_euros, :OLD.estado, :OLD.id_autor, :OLD.id_categoria);
END;
/

/*2) Registro de prestamos
nota: este trigger se debe activar al insertar o modificar un registro de la tabla oina_prestamo
*/

CREATE OR REPLACE TRIGGER oina_registro_prestamo
BEFORE INSERT OR UPDATE ON oina_prestamo
DECLARE
  accion VARCHAR2(20);
  v_user VARCHAR (20);
BEGIN
  SELECT user INTO v_user FROM dual;

  IF INSERTING THEN
  accion := 'Nuevo prestamo';
  INSERT INTO oina_logs (id, accion, fecha, usuario)
  VALUES(null, accion, sysdate, v_user);
  ELSIF UPDATING THEN
  accion := 'Libro devuelto';
  INSERT INTO oina_logs (id, accion, fecha, usuario)
  VALUES(null, accion, sysdate, v_user);
  ELSE
  accion := 'Error al prestar libro';
  INSERT INTO oina_logs (id, accion, fecha, usuario)
  VALUES(null, accion, sysdate, v_user);
  END IF;
END;
/

/*Modificacion libro*/

CREATE TABLE oina_libros_modificados(
    id_modificacion NUMBER GENERATED by default on null as IDENTITY(START WITH 1 INCREMENT BY 1),
    usuario VARCHAR2(50 BYTE) NOT NULL, 
    fecha DATE NOT NULL,
    hora VARCHAR(15 BYTE) NOT NULL,
    CONSTRAINT pk_modificacion PRIMARY KEY (id_modificacion)
);
/

CREATE OR REPLACE TRIGGER oina_registro_detalle_libro
   AFTER UPDATE ON oina_libro
DECLARE
  v_user VARCHAR (20);
BEGIN
  SELECT user INTO v_user FROM dual;
  INSERT INTO oina_libros_modificados(id_modificacion, usuario, fecha, hora)
  VALUES (null, v_user, sysdate, TO_CHAR(sysdate, 'hh24:mi:ss'));
END;
/


--procedimeintos usando transacciones

--este se encarga de actualziar los prescios de los libros en auros segun vaya cambiando de vlaor
create or replace procedure oina_proc_actualizarPrecioEuros (nuevoValorEuro in number) 
as
valor_total VARCHAR2(15);
begin
    
    update oina_libro set precio_euros = (precio * nuevoValorEuro);
    
    select sum(precio) into valor_total from oina_libro;
    
    update oina_datos set valor = valor_total where id_datos = 1;
    
    select sum(precio_euros) into valor_total from oina_libro;
    
    update oina_datos set valor = valor_total where id_datos = 2;

    COMMIT;
exception
    WHEN OTHERS THEN
    dbms_output.put_line('Error en la transaccion:' || SQLERRM);
    dbms_output.put_line('Se deshacen las modificaciones');
    ROLLBACK;
end;
/

--este se encarga de actualizar los datos de la DB, especificamente el total de libros, el total de autores
--, el promedio de libros por autor. Estos se guardan en un tabla para su consulta rapida, evitando hacer 
--los procesois cada vez que se necesten saber
create or replace procedure oina_proc_actualizarDatosLibros
as
total_libros number;
total_autores number;
begin
    select sum(l.cantidad) into total_libros from oina_libro l;

    select count(a.id_autor) into total_autores from oina_autor a;

    update oina_datos set valor = total_libros where id_datos = 3;
    update oina_datos set valor = total_autores where id_datos = 4;

    update oina_datos set valor = (total_libros/total_autores) where id_datos = 5;

    COMMIT;
exception
    WHEN OTHERS THEN
    dbms_output.put_line('Error en la transaccion:' || SQLERRM);
    dbms_output.put_line('Se deshacen las modificaciones');
    ROLLBACK;
end;
/

--funcion que se encarga de crear nuevos usarios revisando que este no exista en la base de datos
create or replace function oina_func_registrarPrestamo(
    fechaDevolucionEstimada in VARCHAR2, 
    usuario in VARCHAR2, 
    idLibro in varchar2
) 
return varchar2 
is
total number(10);
retorno varchar2(100 BYTE);
begin

    select sum(cantidad) into total from oina_libro where id_libro = idLibro;

    if total > 0 then
        INSERT INTO OINA_PRESTAMO VALUES(null, SYSDATE, TO_DATE(fechaDevolucionEstimada, 'YYYY-MM-DD'), null, 'activo', usuario, idLibro);
        retorno:= 'Prestamo registrado correctamente';
    else
        retorno:= 'No hay suficientes existencias de este libro para registrar su prestamo';
    end if;
    
    COMMIT;

    RETURN retorno;
exception
    WHEN OTHERS THEN
    dbms_output.put_line('Error en la transaccion:' || SQLERRM);
    dbms_output.put_line('Se deshacen las modificaciones');
    ROLLBACK;
    retorno:= 'Hubo un error interno: ' || SQLERRM;
    RETURN retorno;
end;
/
CREATE OR REPLACE TRIGGER oina_trigg_actualizarDisponibilidad AFTER UPDATE ON oina_prestamo
FOR EACH ROW
BEGIN
    UPDATE oina_libro set cantidad = (cantidad+1) where id_libro = :NEW.id_libro;
END;
/
set serveroutput on;