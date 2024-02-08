create table tbl_producto(
	id SERIAL PRIMARY KEY,
	nombre varchar(255),
	descripcion varchar(500),
	precio numeric(30) CHECK(precio > 0),
	referencia varchar(255),
	cantidad_stock numeric(255)
);

create table tbl_usuario(
	identificacion varchar(100) NOT NULL,
	tipo_identificacion varchar(10) NOT NULL,
	nombre varchar(255) NOT NULL,
	celular varchar(255) NOT NULL,
	correo varchar(255) NOT NULL,
	primary key (identificacion),
	unique(identificacion,tipo_identificacion)
);

create table tbl_pedido(
	id SERIAL PRIMARY KEY,
	fecha_creacion timestamp NOT NULL,
	fecha_modificacion timestamp NOT NULL,
	estado varchar(255) NOT NULL,
	detalle varchar(255) NOT NULL,
	nombre_cliente varchar(255) NOT NULL,
	documento_cliente varchar(255) NOT NULL,
	tipo_documento_cliente varchar(255) NOT NULL,
	telefono_cliente varchar(255) NOT NULL,
	correo_cliente varchar(255) NOT NULL,
	direccion_cliente varchar(255) NOT NULL,
	fk_usuario_comercial varchar(500) NOT NULL,
	foreign key (fk_usuario_comercial)references tbl_usuario (identificacion)
);

create table tbl_pedidos_productos(
	id SERIAL PRIMARY KEY,
	fk_pedido int NOT NULL,
	fk_producto int NOT NULL,
	cantidad int NOT NULL,
	total int NOT NULL,
	foreign key (fk_pedido) references tbl_pedido (id),
	foreign key (fk_producto) references tbl_producto (id)
);
  
insert into tbl_producto (nombre,descripcion,precio,referencia,cantidad_stock)
values ('Zapatos Old Style','zapato de abuelita',250000,'zap00123',1000);

insert into tbl_producto (nombre,descripcion,precio,referencia,cantidad_stock)
values ('Camiseta Polo','Camiseta casual de cuello',45000,'caPol',4000);

insert into tbl_usuario (identificacion,tipo_identificacion,nombre,celular,correo)
values ('123','CC','pepito perez','3128344636','juang.js80@gmail.com');

insert into tbl_pedido (fecha_creacion,fecha_modificacion,estado,detalle,nombre_cliente,documento_cliente,tipo_documento_cliente,
					   telefono_cliente,correo_cliente,direccion_cliente,fk_usuario_comercial)
values ('2024-02-04 21:50:02'::timestamp,'2024-02-04 21:50:02'::timestamp,'pendiente','Pedido creado',
		'Juan Guillermo','71223490','CC','3128344636','juang.js80@gmail.com','calle 100 Sur 33','123');

insert into tbl_pedidos_productos (fk_pedido,fk_producto,cantidad,total)
values('1','1',1,1000);

insert into tbl_pedidos_productos (fk_pedido,fk_producto,cantidad,total)
values('1','2',2,8000);