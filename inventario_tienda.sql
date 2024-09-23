CREATE DATABASE inventario_tienda;

CREATE TABLE public.usuarios (
	id serial4 NOT NULL,
	username varchar(50) NOT NULL,
	"password" varchar(50) NOT NULL,
	CONSTRAINT usuarios_pkey PRIMARY KEY (id),
	CONSTRAINT usuarios_username_key UNIQUE (username)
);

insert into public.usuarios(username,'password') values ('leo','123');

CREATE TABLE public.producto (
	id serial4 NOT NULL,
	nombre varchar(100) NOT NULL,
	descripcion text NULL,
	precio numeric(10, 2) NOT NULL,
	stock int4 NOT NULL,
	imagen text NULL,
	estado bool NULL,
	CONSTRAINT producto_pkey PRIMARY KEY (id)
);

CREATE TABLE public.historial (
	id serial4 NOT NULL,
	fkproducto int4 NOT NULL,
	cantidad int4 NOT NULL,
	tipo bpchar(1) NOT NULL,
	fecha date DEFAULT now() NULL,
	CONSTRAINT historial_pkey PRIMARY KEY (id),
	CONSTRAINT historial_fkproducto_fkey FOREIGN KEY (fkproducto) REFERENCES public.producto(id)
);

CREATE OR REPLACE VIEW public.vhistorial
AS SELECT h.id,
    h.fkproducto,
    p.nombre,
    p.stock,
    h.tipo,
    h.cantidad,
    h.fecha
   FROM historial h
     JOIN producto p ON p.id = h.fkproducto;