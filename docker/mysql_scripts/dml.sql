insert into restaurants (name) values ( 'Burger King' );

insert into products (name, price, id_restaurant) values ('Whopper', 15, ( select  restaurants.id from restaurants where restaurants.name = 'Burger King'));
insert into products (name, price, id_restaurant) values ('Chicken Crisp', 10, ( select  restaurants.id from restaurants where restaurants.name = 'Burger King'));