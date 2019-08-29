create database desafiomvc;

use desafiomvc;

CREATE TABLE rubro(
	idRubro int AUTO_INCREMENT PRIMARY key,
    rubro varchar(100),
    descripcion text
);

insert into rubro (rubro, descripcion) values ('Algo','Descripcion');
select * from rubro;

create table empresa(
	id int auto_increment primary key,
    codigoEmpresa varchar(6),
    nombre varchar(100) not null,
    direccion varchar(200) not null,
    contacto varchar(100) not null,
    telefono varchar(10) not null,
    rubro int not null,
    cobro double not null default 0.0,
    foreign key(rubro) references rubro(idRubro)
);

delimiter //
create procedure insertarEmpresa(IN _nombre varchar(100), _direccion varchar(200), _contacto varchar(100), _telefono varchar(10), _rubro int(11), _cobro double)
begin
	set @_id = (SELECT `AUTO_INCREMENT`
	FROM  INFORMATION_SCHEMA.TABLES
	WHERE TABLE_SCHEMA = 'desafiomvc'
	AND   TABLE_NAME   = 'empresa');
	insert into empresa (nombre, codigoEmpresa, direccion, contacto, telefono, rubro, cobro) values
	(_nombre, concat('EMP',LPAD(@_id,3,'0')), _direccion, _contacto, _telefono, _rubro, _cobro);
end //
delimiter ;

call desafiomvc.insertarEmpresa('Davivienda', 'el centro we', 'Lic Davivienda', '2265-9601', 1, 10);

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
    descripcion varchar(200) not null default 'Sin descripcion',
    otrosDetalles varchar(200) not null default 'Sin detalles',
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

call desafiomvc.ingresarOferta('Dinero Gratis', 100, 99.99, '2019-08-29', '2019-08-30', '2019-08-30', 5, 'Aproveche we', 'No', 1);

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
    FOREIGN key (tipo) REFERENCES tipoUsuario(idTipo),
    foreign key (empresa) references empresa(id)
);
insert into usuario(nombres, apellidos, correo, contrasenia, tipo)
values ('Javier', 'Ibarra','eldios@hotmail.com','contrasenia',3);


create table cliente(
    usuario int primary key,
    dui varchar(15) not null unique,
    telefono varchar(10) not null,
    FOREIGN key (usuario) REFERENCES usuario(idUsuario)
);
insert cliente values (1,'12345678-9','6598-8963');


create table compra(
    idCompra int AUTO_INCREMENT PRIMARY key,
    fecha datetime not null,
    total double not null,
    cliente int not null,
    FOREIGN key (cliente) REFERENCES cliente(usuario)
);
insert into compra(fecha, total, cliente) values (current_timestamp(), 99.99, 1);


create table cupon(
	id int primary key auto_increment,
    random int,
    codigoCupon varchar(10),
    oferta int not null,
    compra int not null,
	canjeo bit not null DEFAULT 0,
    FOREIGN key (compra) REFERENCES compra(idCompra)
);

delimiter //
create procedure ingresarCupon(IN _oferta int, _compra int, _canjeo int)
begin
	set @_empresa = (select nombre from empresa join oferta on oferta.empresa = empresa.id where oferta.idOferta = _oferta limit 1);
    set @_codemp = substring(@_empresa,1,3);
    set @random = LPAD(FLOOR(1 + RAND() * (9999999 - 1 + 1)),7,'0');
    set @codCupon = concat(@_codemp,@random);
    
    /*while @random not in (select random from cupon) do
		begin*/
			insert into cupon (random, codigoCupon, oferta, compra, canjeo)
            values (@random, @codCupon, _oferta, _compra, _canjeo);
        /*end;
	end while;*/
end//
delimiter ;

call desafiomvc.ingresarCupon(1, 1, 0);

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
insert into usuario (nombres,apellidos,correo,contrasenia,tipo) VALUES ("Denys","Cruz","dennyscruz20@gmail.com",SHA2("Password01",256),2);
insert into usuario (nombres,apellidos,correo,contrasenia,tipo) VALUES ("Javier","Pablo","javierpablo@gmail.com",SHA2("Password01",256),3);
