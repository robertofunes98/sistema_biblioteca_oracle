create database oina_biblioteca;

use oina_biblioteca;

create table autor(
	id_autor int not null auto_increment,
	nombre varchar(50) not null,
	primary key pk_autor(id_autor)
);

create table categoria(
	id_categoria int not null auto_increment,
	nombre varchar(50) not null,
	primary key pk_categoria(id_categoria)
);

create table libro(
	id_libro varchar(10) not null,
	nombre varchar(50) not null,
	cantidad int not null,
	id_autor int not null,
	id_categoria int not null,
	primary key pk_libro(id_libro),
	foreign key fk_libro_autor(id_autor) references autor(id_autor),
	foreign key fk_libro_categoria(id_categoria) references categoria(id_categoria)
);