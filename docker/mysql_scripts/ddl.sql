create database if not exists skipthedishes;

create table accounts (
  id bigint auto_increment primary key,
	username varchar(255) not null unique,
	password varchar(255) not null
);

create table restaurants (
  id bigint auto_increment primary key,
	name varchar(255) not null unique,
	password varchar(255) not null
);

create table products (
  id bigint auto_increment primary key,
	name varchar(255) not null,
	price double not null,
	id_restaurant bigint not null,
	foreign key (id_restaurant) references restaurants(id)
);

create table orders (
  id bigint auto_increment primary key,
	id_account bigint not null,
	id_restaurant bigint not null,
	date_order datetime not null default CURRENT_TIMESTAMP,
	foreign key (id_account) references accounts(id),
	foreign key (id_restaurant) references restaurants(id)
);

create table orders_products (
  id bigint auto_increment primary key,
	id_order bigint not null,
	id_product bigint not null,
	quantity integer not null,
	foreign key (id_order) references orders(id),
	foreign key (id_product) references products(id)
);