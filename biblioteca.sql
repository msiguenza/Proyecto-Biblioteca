create database biblioteca;

use biblioteca;

CREATE TABLE usuario(
dni VARCHAR(9) NOT NULL,
nombre VARCHAR(20) NOT NULL,
apellidos VARCHAR(45) DEFAULT NULL,
nick VARCHAR(45) NOT NULL,
clave VARCHAR(30) NOT NULL,
direccion VARCHAR(50) DEFAULT NULL,
telefono VARCHAR(9) NOT NULL,
penalizado BOOLEAN DEFAULT FALSE,
alquiler BOOLEAN DEFAULT FALSE,
tipo INT(1) DEFAULT 0,
UNIQUE KEY (nick),
PRIMARY KEY (dni));

insert into usuario values
("00000000X","Null","Null","administrador","123456","Null",0000000,0,0,1),
("77844261W","Gonzalo","Bootello","gbootello","123456","Calle 1",636992626,0,0,0),
("11111111Z","Fernando","Rodriguez","frodriguez","123456","Calle 2",666321321,0,0,0),
("22222222X","Miguel Angel","Siguenza","msiguenza","123456","Calle 3",654111222,0,0,0),
("45454545V","Juan Jose","Gonzalez","jgonzalez","123456","Calle 4",666111333,0,0,0);

CREATE TABLE material(
cod_material INT(4) AUTO_INCREMENT NOT NULL,
isbn INT(13) DEFAULT NULL,
issn INT(7) DEFAULT NULL,
autor_director VARCHAR(100) NOT NULL,
titulo VARCHAR(50) NOT NULL,
editorial VARCHAR(30) DEFAULT NULL,
ano VARCHAR(15) DEFAULT NULL,
paginas_duracion INT DEFAULT NULL,
genero VARCHAR(30),
tipo VARCHAR(7),
disponible BOOLEAN DEFAULT TRUE,
PRIMARY KEY(cod_material),
UNIQUE KEY(isbn),
UNIQUE KEY(issn),
UNIQUE KEY (titulo,tipo));

insert into material values
(1,111111,null,"Miguel de Cervantes","El Quijote de la Mancha","Anaya","1984/01/01",456,"Aventuras","Libro",1),
(2,null,222222,"Stephen Hawkins","Ciencia Actual","Editorial Z","2013/04/26",122,"Ciencia","Revista",1),
(3,null,null,"Peter Jackson","El Señor de los Anillos: La Comunidad del Anillo",null,"2001/10/25",180,"Ciencia Ficcion","DVD",1),
(4,null,333333,"Instituto Cervantes","PC Actual","Editorial Cervantes","2013/04/14",100,"Informatica","Revista",1),
(5,444444,NULL,"Becquer","Rimas","Anaya","1956/01/14",167,"Poesia","Libro",1),
(6,NULL,NULL,"Samuel Jackson","El Rey Escorpion",NULL,"2008/04/20",120,"Accion","DVD",1),
(7,NULL,555555,"Editorial X","Interviu","Editorial X","2013/04/30",87,"Cronicas","Revista",1),
(8,666666,NULL,"Dan Brown","Inferno","Alfaguara","2013/04/16",234,"Ciencia ficcion","Libro",1),
(9,NULL,NULL,"Peter Jackson","El Hobbit: Un viaje inesperado",NULL,"2012/11/10",180,"Aventuras","DVD",1),
(10,NULL,777777,"Editorial B","Rolling Stone","Editorial B","2013/04/01",100,"Musica","Revista",1),
(11,888888,NULL,"Peter Jackson","El Señor de los Anillos: La Comunidad del Anillo","Editorial B","1998/04/04",345,"Ciencia Ficcion","Libro",1);

CREATE TABLE articulo(
cod_articulo INT(4) AUTO_INCREMENT NOT NULL,
issn INT(7) NOT NULL,
autor VARCHAR(50) NOT NULL,
titulo VARCHAR(25) NOT NULL,
ano DATE DEFAULT NULL,
pagina_ini INT(4) DEFAULT NULL,
pagina_fin INT(4) DEFAULT NULL,
PRIMARY KEY(cod_articulo),
FOREIGN KEY (issn) REFERENCES material (issn)
	ON DELETE CASCADE ON UPDATE CASCADE);

insert into articulo values
(1,222222,"Pedro Ramirez","Acelerador de particulas","2013/04/14",45,45),
(2,333333,"Bill Gates","Windows 8","2013/03/10",23,25),
(3,222222,"Jose Luis Garcia","Protones","2013/04/04",32,33),
(4,333333,"Steve Jobs","Iphone 5","2013/10/10",55,56),
(5,555555,"Mario Garcia","Mente Humana","2012/05/05",55,55),
(6,777777,"Juan Ramirez","Musica de los 80","2013/01/10",45,46),
(8,555555,"Felipe Lopez","Futbol Australiano","2012/05/28",12,13),
(9,777777,"Julio Gomez","Dubstep","2012/4/15",30,31),
(10,333333,"Juan Gonzalez","El nuevo Android","2013/01/28",4,5);

CREATE TABLE reserva(
cod_reserva INT(8) AUTO_INCREMENT NOT NULL,
cod_material INT(4) NOT NULL,
dni VARCHAR(9) NOT NULL,
fecha_res DATE NOT NULL,
fecha_dev DATE NOT NULL,
devolucion BOOLEAN DEFAULT FALSE,
PRIMARY KEY(cod_reserva),
FOREIGN KEY (cod_material) REFERENCES material (cod_material)
	ON DELETE NO ACTION ON UPDATE CASCADE,
FOREIGN KEY (dni) REFERENCES usuario (dni)
	ON DELETE NO ACTION ON UPDATE CASCADE);

CREATE TABLE nota(
id_nota INT(8) AUTO_INCREMENT NOT NULL,
tema VARCHAR(30) NOT NULL,
dni VARCHAR(9) NOT NULL,
cod_material INT(4) DEFAULT NULL,
cod_articulo INT(4) DEFAULT NULL,
comentario BLOB NOT NULL,
PRIMARY KEY(id_nota),
FOREIGN KEY (cod_material) REFERENCES material (cod_material)
	ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (dni) REFERENCES usuario (dni)
	ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (cod_articulo) REFERENCES articulo (cod_articulo)
	ON DELETE CASCADE ON UPDATE CASCADE);

insert into nota values 
(1,"Novela","77844261W",1,NULL,"Esta novela es una de las mejores que se ha escrito a lo largo de la historia"),
(2,"Ciencia Actual","11111111Z",2,NULL,"Stephen Hawkins uno de los mejores científicos de la historia"),
(3,"Efectos Especiales","22222222X",3,NULL,"Una de las mejores peliculas con los mejores efectos especiales del momento"),
(4,"Neutrones","77844261W",2,NULL,"El acelerador de particulas ha conseguido grandes avances a lo largo de este siglo"),
(5,"Software","11111111Z",4,NULL,"Windows 8 es un sistema operativo bastante intuitivo"),
(6,"Poesia","22222222X",5,NULL,"La poesia de Becquer es excelente"),
(7,"Directores","77844261W",6,NULL,"Este director es uno de los mejores que existe"),
(8,"Software","11111111Z",4,NULL,"Linux es uno de los mejores inventos de la historia de la informática"),
(9,"Novela de accion","22222222X",8,NULL,"Esta novela tiene un argumento increíble. Para los amantes de la acción es ideal"),
(10,"Musica","77844261W",10,NULL,"Gran revista para los que le gusta la buena musica"),
(11,"Ser Humano","11111111Z",2,5,"El ser humano es asombroso"),
(12,"Android","77844261W",4,4,"Si no tiene android no me gusta"),
(13,"Musica","22222222X",10,6,"La musica de los 80 no me gusta");

DELIMITER $$
DROP FUNCTION IF EXISTS cuantasnotas $$
CREATE FUNCTION cuantasnotas() 
RETURNS int(11)
BEGIN
DECLARE n INT;
SELECT COUNT(*) INTO n FROM nota;
RETURN n;
END;$$

DELIMITER $$
DROP FUNCTION IF EXISTS cuantosmateriales $$
CREATE FUNCTION cuantosmateriales() 
RETURNS int(11)
BEGIN
DECLARE n INT;
SELECT COUNT(*) INTO n FROM material;
RETURN n;
END;$$

DELIMITER $$
DROP FUNCTION IF EXISTS cuantosarticulos $$
CREATE FUNCTION cuantosarticulos() 
RETURNS int(11)
BEGIN
DECLARE n INT;
SELECT COUNT(*) INTO n FROM articulo;
RETURN n;
END;$$

DELIMITER $$
DROP FUNCTION IF EXISTS cuantasreservas $$
CREATE FUNCTION cuantasreservas() 
RETURNS int(11)
BEGIN
DECLARE n INT;
SELECT COUNT(*) INTO n FROM reserva;
RETURN n;
END;$$

DELIMITER $$
DROP FUNCTION IF EXISTS cuantosusuarios $$
CREATE FUNCTION cuantosusuarios() 
RETURNS int(11)
BEGIN
DECLARE n INT;
SELECT COUNT(*) INTO n FROM usuario;
RETURN n;
END;$$