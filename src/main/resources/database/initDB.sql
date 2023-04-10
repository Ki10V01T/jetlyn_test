/*Создание таблиц*/

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
/*===============================*/


CREATE TABLE IF NOT EXISTS public.animal_types (
	id uuid NOT NULL,
	"type" varchar(30) NOT NULL,
	CONSTRAINT animal_types_pk PRIMARY KEY (id),
	CONSTRAINT animal_types_un UNIQUE (type)
);

CREATE TABLE IF NOT EXISTS public.food_types (
	id uuid NOT NULL,
	"type" varchar(30) NOT NULL,
	CONSTRAINT food_types_pk PRIMARY KEY (id),
	CONSTRAINT food_types_un UNIQUE (type)
);

CREATE TABLE IF NOT EXISTS public.measurement_units (
	id uuid NOT NULL,
	"name" varchar(30) NOT NULL,
	CONSTRAINT measurement_units_pk PRIMARY KEY (id),
	CONSTRAINT measurement_units_un UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS public.animals (
	id uuid NOT NULL,
	"name" varchar(30) NOT NULL,
	"type" varchar(30) NOT NULL,
	is_predator bool NOT NULL,
	CONSTRAINT animals_pk PRIMARY KEY (id),
	CONSTRAINT animals_un UNIQUE ("name"),
	CONSTRAINT animals_fk FOREIGN KEY ("type") REFERENCES public.animal_types("type") ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS public.foods (
	id uuid NOT NULL,
	"name" varchar(30) NOT NULL,
	"left" int4 NOT NULL DEFAULT 0,
	measurement_unit varchar(30) NOT NULL,
	"type" varchar(30) NOT NULL,
	CONSTRAINT foods_pk PRIMARY KEY (id),
	CONSTRAINT foods_un UNIQUE (name),
	CONSTRAINT food_type_fk FOREIGN KEY ("type") REFERENCES public.food_types("type") ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT foods_fk FOREIGN KEY (measurement_unit) REFERENCES public.measurement_units("name") ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE if not exists public.animals_foods_menu (
	id uuid NOT NULL,
	animal_name varchar(30) NOT NULL,
	food_name varchar(30) NOT NULL,
	per_day float8 NOT NULL,
	CONSTRAINT animal_food_menu_pk PRIMARY KEY (id),
	CONSTRAINT animals_fd_mu_animal_fk FOREIGN KEY ("animal_name") REFERENCES public.animals("name") ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT animals_fd_mu_foods_fk FOREIGN KEY ("food_name") REFERENCES public.foods("name") ON DELETE CASCADE ON UPDATE CASCADE
);