create database oina_biblioteca;

use oina_biblioteca;

create table usuario(
	id_usuario int not null auto_increment,
	nombre varchar(15) not null,
	clave varchar(200) not null,
	tipo_usuario int not null comment "0=usuario, 1=admin",
	primary key pk_usuario(id_usuario)
);

create table autor(
	id_autor int not null auto_increment,
	nombre varchar(50) not null,
	primary key pk_autor(id_autor)
) default charset= utf8;

create table categoria(
	id_categoria int not null auto_increment,
	nombre varchar(50) not null,
	primary key pk_categoria(id_categoria)
)default charset= utf8;

create table libro(
	id_libro varchar(10) not null,
	nombre varchar(50) not null,
	cantidad int not null,
	id_autor int not null,
	id_categoria int not null,
	primary key pk_libro(id_libro),
	foreign key fk_libro_autor(id_autor) references autor(id_autor),
	foreign key fk_libro_categoria(id_categoria) references categoria(id_categoria)
)default charset= utf8;

insert into usuario values(null, "admin", "admin", 1);

insert into autor values(null, "Platón");
insert into autor values(null, "Shakespeare");

insert into categoria values(null, "Filosofía");
insert into categoria values(null, "Tragedia");

insert into libro values("F-001","La República - Tomo 1",1,1,1);
insert into libro values("T-001","Hamlet",1,2,2);
