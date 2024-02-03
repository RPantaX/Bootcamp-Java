create database Spring;
USE Spring;

CREATE TABLE libros(
	ISBN varchar(40) primary key,
	titulo varchar(40) unique not null
);

CREATE TABLE Autores(
	autor_id int primary key,
	nombre varchar(80) unique not null
);

CREATE TABLE autores_libros (
    ISBN VARCHAR(40),
    autor_id INT,
    PRIMARY KEY (ISBN, autor_id)
);
alter table autores_libros add constraint fk_libros_aut FOREIGN KEY (ISBN) REFERENCES libros(ISBN);
alter table autores_libros add constraint fk_autores_lib FOREIGN KEY (autor_id) REFERENCES Autores(autor_id);

CREATE TABLE Categorias(
	categoria_id int primary key,
	nombre varchar(80) unique
);

CREATE TABLE Libros_categorias(
	ISBN VARCHAR(40),
    categoria_id INT,
    PRIMARY KEY (ISBN, categoria_id)
);

alter table Libros_categorias add constraint fk_libros_cat FOREIGN KEY (ISBN) REFERENCES libros(ISBN);
alter table Libros_categorias add constraint fk_categorias_lib FOREIGN KEY (categoria_id) REFERENCES Categorias(categoria_id);


CREATE TABLE lectores(
	lector_id int primary key,
	nombre varchar(80) unique not null
);

CREATE TABLE Prestamo(
	prestamo_id int primary key,
	fecha_prestamo date not null,
	Fecha_Devolucion_Esperada date not null,
	ISBN VARCHAR(40),
	lector_ID int
);
alter table Prestamo add constraint fk_prestamo_lect FOREIGN KEY (ISBN) REFERENCES libros(ISBN);
alter table Prestamo add constraint fk_prestamo_lib	FOREIGN KEY (lector_ID) REFERENCES lectores(lector_id) on delete cascade;

