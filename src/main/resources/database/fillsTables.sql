/*Заполнение всех таблиц данными*/

insert into public.animal_types values
(uuid_generate_v4(), 'Млекопитающее'),
(uuid_generate_v4(), 'Птица');

insert into public.food_types values
(uuid_generate_v4(), 'Овощ'),
(uuid_generate_v4(), 'Фрукт'),
(uuid_generate_v4(), 'Мясо'),
(uuid_generate_v4(), 'Жидкость');

insert into public.measurement_units values
(uuid_generate_v4(), 'килограмм'),
(uuid_generate_v4(), 'штук'),
(uuid_generate_v4(), 'литров');

insert into public.animals values
(uuid_generate_v4(), 'Обезьяна', 'Млекопитающее', true),
(uuid_generate_v4(), 'Заяц', 'Млекопитающее', false),
(uuid_generate_v4(), 'Орёл', 'Птица', true),
(uuid_generate_v4(), 'Тигр', 'Млекопитающее', true),
(uuid_generate_v4(), 'Лошадь', 'Млекопитающее', false);

insert into public.foods values
(uuid_generate_v4(), 'Морковь', 80, 'штук', 'Овощ'),
(uuid_generate_v4(), 'Говядина', 60, 'килограмм', 'Мясо'),
(uuid_generate_v4(), 'Капуста', 40, 'штук', 'Овощ'),
(uuid_generate_v4(), 'Банан', 50, 'штук', 'Фрукт'),
(uuid_generate_v4(), 'Яблоко', 50, 'штук', 'Фрукт'),
(uuid_generate_v4(), 'Зерно',15, 'килограмм','Овощ'),
(uuid_generate_v4(), 'Вода', 10, 'литров', 'Жидкость');

insert into public.animals_foods_menu values
(uuid_generate_v4(), 'Обезьяна', 'Вода', 3),
(uuid_generate_v4(), 'Обезьяна', 'Банан', 2.5),
(uuid_generate_v4(), 'Обезьяна', 'Яблоко', 2.5),
(uuid_generate_v4(), 'Заяц', 'Вода', 0.5),
(uuid_generate_v4(), 'Заяц', 'Морковь', 2),
(uuid_generate_v4(), 'Заяц', 'Капуста', 1),
(uuid_generate_v4(), 'Орёл', 'Вода', 0.35),
(uuid_generate_v4(), 'Орёл', 'Говядина', 0.5),
(uuid_generate_v4(), 'Орёл', 'Зерно', 0.2),
(uuid_generate_v4(), 'Орёл', 'Яблоко', 0.2),
(uuid_generate_v4(), 'Тигр', 'Вода', 5),
(uuid_generate_v4(), 'Тигр', 'Говядина', 8),
(uuid_generate_v4(), 'Лошадь', 'Вода', 28),
(uuid_generate_v4(), 'Лошадь', 'Зерно', 2),
(uuid_generate_v4(), 'Лошадь', 'Яблоко', 10),
(uuid_generate_v4(), 'Лошадь', 'Морковь', 8);
