create database desafiomvc;

use desafiomvc;

CREATE TABLE rubro(
idRubro int AUTO_INCREMENT PRIMARY key,
    rubro varchar(100),
    descripcion text
);

create table empresa(
    codigoEmpresa varchar(6) primary key,
    nombre varchar(100) not null,
    direccion varchar(200) not null,
    contacto varchar(100) not null,
    telefono varchar(10) not null,
    rubro int not null,
    cobro double not null default 0.0
);

create table estado(
    idEstado int primary key auto_increment,
    estado varchar(100) not null
);

create table oferta(
    idOferta int primary key,
    titulo varchar (100) not null,
    precioRegular double not null,
    precioOferta double not null,
    fechaInicio datetime not null,
    fechaFin datetime not null,
    fechaLimite datetime not null ,
    limiteCupones int null,
    descripcion varchar(200) not null default 'Sin descripcion',
    otrosDetalles varchar(200) not null default 'Sin descripcion',
    empresa varchar(6) not null,
    estado int not null,
    foreign key(estado) references estado(idEstado),
    foreign key (empresa) references empresa(codigoEmpresa)
);

CREATE table justificacionRechazos(
    oferta int PRIMARY key, 
    justificacion text,
    FOREIGN key (oferta) REFERENCES oferta(idOferta)
);
create table tipoUsuario(
    idTipo int primary key auto_increment,
    tipo varchar(30),
    descripcion varchar(200)
);
create table usuario(
    idUsuario int AUTO_INCREMENT primary key,
    nombres varchar(100) not null,
    apellidos varchar(100) not null,
    correo varchar(200) not null unique,
    contrasenia varchar(100) not null,
    tipo int not null,
    FOREIGN key (tipo) REFERENCES tipoUsuario(idTipo)  
);

create table gestorEmpresa(
    usuario int not null,
    empresa varchar(6) not null,
    PRIMARY key (usuario,empresa),
    FOREIGN key (usuario) references usuario(idUsuario),
    FOREIGN key (empresa) REFERENCES empresa(codigoEmpresa)
);

create table cliente(
    usuario int primary key,
    dui varchar(15) not null unique,
    telefono varchar(10) not null,
    FOREIGN key (usuario) REFERENCES usuario(idUsuario)
);
create table compra(
    idCompra int AUTO_INCREMENT PRIMARY key,
    fecha datetime not null,
    total double not null,
    cliente int not null,
    FOREIGN key (cliente) REFERENCES cliente(usuario)
);

CREATE table cupon(
    codigoCupon varchar(13) primary key,
    oferta int not null,
    compra int not null,
    FOREIGN key (compra) REFERENCES compra(idCompra)
);

/* Procedimiento para loguearse */
delimiter $$
CREATE PROCEDURE loguearse (Usuario VARCHAR(50),Contrasenia VARCHAR(50))
    BEGIN
        SELECT u.idUsuario,u.nombres,t.idTipo
        FROM usuario u
        INNER JOIN tipoUsuario t
        ON u.tipo = t.idTipo
        WHERE u.correo = Usuario AND u.contrasenia = SHA2(Contrasenia,256); 
    END $$
delimiter ;

/* Tipos de Usuarios */ 
INSERT INTO `tipousuario` (`tipo`, `descripcion`) VALUES ('Administrador', 'Administrador de La Cuponera');
INSERT INTO `tipousuario` (`tipo`, `descripcion`) VALUES ('Empresa', 'Cliente de Empreas');
INSERT INTO `tipousuario` (`tipo`, `descripcion`) VALUES ('Cliente', 'Consumidor Final');

/* Usuarios por defecto */
insert into usuario (nombres,apellidos,correo,contrasenia,tipo) VALUES ("Alejandro","Alejo","alejandroalejo714@gmail.com",SHA2("Password01",256),1);
insert into usuario (nombres,apellidos,correo,contrasenia,tipo) VALUES ("Dennis","Cruz","denniscruz@gmail.com",SHA2("Password01",256),2);
insert into usuario (nombres,apellidos,correo,contrasenia,tipo) VALUES ("Javier","Pablo","javierpablo@gmail.com",SHA2("Password01",256),3)

