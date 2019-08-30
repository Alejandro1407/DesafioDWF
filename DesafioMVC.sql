create database desafiomvc;

use desafiomvc;

CREATE TABLE rubro(
	idRubro int AUTO_INCREMENT PRIMARY key,
    rubro varchar(100),
    descripcion text
);

create table empresa(
	id int auto_increment primary key,
    codigoEmpresa varchar(6) UNIQUE,
    nombre varchar(100) not null,
    direccion varchar(200) not null,
    telefono varchar(10) not null,
    rubro int not null,
    cobro double not null default 0.0,
    foreign key(rubro) references rubro(idRubro)
);

  idUsuario int AUTO_INCREMENT primary key,
    nombres varchar(100) not null,
    apellidos varchar(100) null,
    correo varchar(200) not null unique,
    contrasenia varchar(100) not null,
    empresa int,
    tipo int not null,
    FOREIGN key (tipo) REFERENCES tipoUsuario(idTipo),
    foreign key (empresa) references empresa(id)

delimiter //
create procedure insertarEmpresa(IN _nombre varchar(100), _direccion varchar(200), _contacto varchar(100), _telefono varchar(10), _rubro int(11), _cobro double, _correo varchar(50),_contrasenia varchar(50))
begin
	set @_id = (SELECT `AUTO_INCREMENT`
	FROM  INFORMATION_SCHEMA.TABLES
	WHERE TABLE_SCHEMA = 'desafiomvc'
	AND  TABLE_NAME = 'empresa');
	insert into empresa (nombre, codigoEmpresa, direccion, telefono, rubro, cobro) values
	(_nombre, concat('EMP',LPAD(@_id,3,'0')), _direccion,_telefono, _rubro, _cobro);
    insert into usuario (nombres,correo,contrasenia,empresa,tipo,valid) values (_contacto,_correo,SHA2(_contrasenia,256),LAST_INSERT_ID(),2,1);
end //
delimiter ;

create table estado(
    idEstado int primary key auto_increment,
    estado varchar(100) not null
);

insert into estado(estado) values ('En espera de aprobacion');
insert into estado(estado) values ('Oferta aprobada');
insert into estado(estado) values ('Oferta rechazada');
insert into estado(estado) values ('Oferta descartada');
insert into estado(estado) values ('Oferta activa');
insert into estado(estado) values ('Oferta vencida');

create table oferta(
    idOferta int primary key auto_increment,
    titulo varchar (100) not null,
    precioRegular double not null,
    precioOferta double not null,
    fechaInicio datetime not null,
    fechaFin datetime not null,
    fechaLimite datetime not null ,
    limiteCupones int null,
    descripcion varchar(200) null default null,
    otrosDetalles varchar(200) null default null,
    empresa int not null,
    estado int not null default 1,
    foreign key(estado) references estado(idEstado),
    foreign key (empresa) references empresa(id)
);

delimiter //
create procedure ingresarOferta(IN _titulo varchar(100), _precioRegular double, _precioOferta double, _fechaInicio datetime, _fechaFin datetime, 
_fechaLimite datetime, _limiteCupones int, _descripcion varchar(200), _otrosDetalles varchar(200), _empresa int)
begin
	insert into oferta(titulo, precioRegular, precioOferta, fechaInicio, fechaFin, fechaLimite, limiteCupones, descripcion, otrosDetalles, empresa, estado)
    values (_titulo, _precioRegular, _precioOferta, _fechaInicio, _fechaFin, _fechaLimite, _limiteCupones, _descripcion, _otrosDetalles, _empresa, default);
end//
delimiter ;

CREATE table justificacionRechazos(
    oferta int PRIMARY key, 
    justificacion varchar(200),
    FOREIGN key (oferta) REFERENCES oferta(idOferta)
);

delimiter //
create procedure rechazarOferta(IN _idOferta int, _justificacion varchar(200))
begin
	update oferta set estado = 3 where idOferta = _idOferta;
    insert into justificacionRechazos values (_idOferta, _justificacion);
end//
delimiter ;

delimiter //
create procedure descartarOferta (IN _idOferta int) 
begin
	update oferta set estado = 4 where idOferta = _idOferta;
end//
delimiter ;

delimiter //

create procedure reintentarOferta (IN _idOferta int, _titulo varchar(100), _precioRegular double, _precioOferta double, _fechaInicio datetime, _fechaFin datetime, 
_fechaLimite datetime, _limiteCupones int, _descripcion varchar(200), _otrosDetalles varchar(200), _empresa int)
begin
	update oferta set titulo = _titulo, precioRegular = _precioRegular, precioOferta = _precioOferta, fechaInicio = _fechaInicio, fechaFin = _fechaFin,
    fechaLimite = _fechaLimite, limiteCupones = _limiteCupones, descripcion = _descripcion, otrosDetalles = _otrosDetalles, empresa = _empresa, estado = 1
    where idOferta = _idOferta;
end//
delimiter ;

create table tipoUsuario(
    idTipo int primary key auto_increment,
    tipo varchar(30),
    descripcion varchar(200) default 'sin descripcion'
);

create table usuario(
    idUsuario int AUTO_INCREMENT primary key,
    nombres varchar(100) not null,
    apellidos varchar(100) not null,
    correo varchar(200) not null unique,
    contrasenia varchar(100) not null,
    empresa int,
    tipo int not null,
    valid bit default 0,
    FOREIGN key (tipo) REFERENCES tipoUsuario(idTipo),
    foreign key (empresa) references empresa(id)
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

create table cupon(
    codigoCupon varchar(13) primary KEY,
    oferta int not null,
    compra int not null,
	canjeo bit not null DEFAULT 0,
    foreign key(oferta) references oferta(idOferta),
    FOREIGN key (compra) REFERENCES compra(idCompra)
);

/* Procedimiento para loguearse */
delimiter $$
CREATE PROCEDURE loguearse (Usuario VARCHAR(50),Contrasenia VARCHAR(50))
    BEGIN
        SELECT u.idUsuario,u.nombres,t.idTipo,u.empresa
        FROM usuario u
        INNER JOIN tipoUsuario t
        ON u.tipo = t.idTipo
        WHERE (u.correo = Usuario AND u.contrasenia = SHA2(Contrasenia,256)) AND u.valid = 1; 
    END $$
delimiter ;

delimiter //
    create procedure ChangePass(idusuario INT,OldPass VARCHAR(50),newcontrasenia VARCHAR(50))
    begin
        SET @valid = (select count(*) from usuario where idUsuario = idusuario AND contrasenia = SHA2(OldPass,256));
        if @valid > 0 then
            UPDATE usuario SET contrasenia=SHA2(newcontrasenia,256) WHERE idUsuario = idusuario;
            SELECT CONCAT("Actualizada correctamente");
        else
            SELECT CONCAT("Contraseña incorrecta");
        end if;
end //
delimiter ;

delimiter //
    create procedure ValidarToken(TOKEN VARCHAR(50))
    begin
        SET @valid = (select count(*) from Token where Token = TOKEN AND Valid = 1);
        if @valid > 0 then
            SET @Email = (SELECT Email FROM Token WHERE Token = TOKEN LIMIT 1);
            UPDATE usuario SET valid = b'1' WHERE correo = @Email;
            UPDATE Token SET Valid = 0 WHERE Token = TOKEN;
            SELECT CONCAT("true");
        else
            SELECT CONCAT("false");
        end if;
end //
delimiter ;

/* Tabla para los Tokens de correo */
CREATE TABLE Token(
	id INT NOT NULL auto_increment,
	Token VARCHAR(50) NOT NULL UNIQUE,
	Email VARCHAR(60) NOT NULL,
    Valid BIT DEFAULT 1,
	PRIMARY KEY(id)
);

delimiter //
create procedure ingresarCupon(IN _oferta int, _compra int, _canjeo int)
begin
    set @_codemp = (select codigoEmpresa  from empresa join oferta on oferta.empresa = empresa.id where oferta.idOferta = _oferta limit 1);
    set @random = LPAD(FLOOR(1 + RAND() * (9999999 - 1 + 1)),7,'0');
    set @codCupon = concat(@_codemp,@random);
    
    while @random not in (select random from cupon) do
		begin
			insert into cupon (random, codigoCupon, oferta, compra, canjeo)
            values (@random, @codCupon, _oferta, _compra, _canjeo);
        end;
	end while;
end//
delimiter;


/* Tipos de Usuarios */ 
INSERT INTO `tipousuario` (`tipo`, `descripcion`) VALUES ('Administrador', 'Administrador de La Cuponera');
INSERT INTO `tipousuario` (`tipo`, `descripcion`) VALUES ('Empresa', 'Administrador empresa cliente');
INSERT INTO `tipousuario` (`tipo`, `descripcion`) VALUES ('Cliente', 'Consumidor Final');

/* Usuarios por defecto */
insert into usuario (nombres,apellidos,correo,contrasenia,tipo,valid) VALUES ("Alejandro","Alejo","alejandroalejo714@gmail.com",SHA2("Password01",256),1,1);
insert into usuario (nombres,apellidos,correo,contrasenia,tipo,valid) VALUES ("Denys","Cruz","dennyscruz20@gmail.com",SHA2("Password01",256),2,1);
insert into usuario (nombres,apellidos,correo,contrasenia,tipo,valid) VALUES ("Javier","Pablo","javierpablo@gmail.com",SHA2("Password01",256),3,1);
