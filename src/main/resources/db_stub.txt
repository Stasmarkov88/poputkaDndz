insert into pnp (id, latitude, longitude, description, isleft)
values (1, 48.597634, 34.551059, 'Ул. Карла Маркса, 11-й мк. район', 1);

insert into pnp (id, latitude, longitude, description, isleft)
values (2, 48.592752, 34.551660, 'Пр. 50-летия СССР, 10-й мк. район', 1);

insert into pnp (id, latitude, longitude, description, isleft)
values (3, 48.596779, 34.556502, 'Пр. Победы, 10-й и 11-й мк. район', 1);

insert into pnp (id, latitude, longitude, description, isleft)
values (4, 48.592555, 34.561847, 'Кинотеатр Мир, 9-й мк. район', 1);

insert into pnp (id, latitude, longitude, description, isleft)
values (5, 48.597481, 34.568520, 'Наше дело, 1-й мк. район', 1);

insert into pnp (id, latitude, longitude, description, isleft)
values (6, 48.596602, 34.576498, 'Ул. Харьковская, 1-й мк. район', 1);

insert into pnp (id, latitude, longitude, description, isleft)
values (7, 48.599868, 34.571333, 'Поликлиника № 1, 2-й мк. район', 1);

insert into pnp (id, latitude, longitude, description, isleft)
values (8, 48.607253, 34.568464, 'Русалка, бульвар Героев, 3-й мк. район', 1);

insert into pnp (id, latitude, longitude, description, isleft)
values (9, 48.605518, 34.560214, 'Бульвар Героев, 4-й мк. район', 1);

insert into pnp (id, latitude, longitude, description, isleft)
values (10, 48.607008, 34.562875, 'Бульвар Героев, 5-й мк. район', 1);

-- Правый берег 

insert into pnp (id, latitude, longitude, description, isleft)
values (11, 48.516050, 34.579150, 'Комсомольский проспект', 0);

insert into pnp (id, latitude, longitude, description, isleft)
values (12, 48.509504, 34.589525, 'Ул. Димитрова', 0);

insert into pnp (id, latitude, longitude, description, isleft)
values (13, 48.522354, 34.605061, 'Пр. Пелина', 0);

insert into pnp (id, latitude, longitude, description, isleft)
values (14, 48.516299, 34.606681, 'Ул. Сыровца', 0);

insert into pnp (id, latitude, longitude, description, isleft)
values (15, 48.516057, 34.612260, 'Площадь Ленина', 0);

insert into pnp (id, latitude, longitude, description, isleft)
values (16, 48.510260, 34.610983, 'Рынок, ул. Ленина', 0);

insert into pnp (id, latitude, longitude, description, isleft)
values (17, 48.510454, 34.600900, 'Пр. Аношкина', 0);

insert into pnp (id, latitude, longitude, description, isleft)
values (18, 48.497386, 34.610794, 'Пр. Юбилейный', 0);

insert into pnp (id, latitude, longitude, description, isleft)
values (19, 48.478040, 34.665295, 'пр. Конституции', 0);

insert into pnp (id, latitude, longitude, description, isleft)
values (20, 48.536764, 34.571143, 'ул. Днепростроевская', 0);

-- Users

insert into user values(1, 'admin', 0, 'admin', 'admin', null, null, 'easygodndz@gmail.com');
insert into user_role values(1, 'admin', 'ROLE_ADMIN');

insert into user values(2, 'Станислав', 0, 'stasmarkov', '111111', 'Ланос серый', '0950005655', 'stasmarkov88@gmail.com');
insert into user_role values(2, 'stasmarkov', 'ROLE_USER');

insert into user values(3, 'Олег', 0, 'oleg', '111111', null, '0950025655', 'oleg@gmail.com');
insert into user_role values(3, 'oleg', 'ROLE_USER');

insert into user values(4, 'Степан', 0, 'stepan', '111111', null, '0950025755', 'stepan@ukr.net');
insert into user_role values(4, 'oleg', 'ROLE_USER');

insert into user values(5, 'Алла', 1, 'alla', '111111', null, '0950225755', 'alla@i.ua');
insert into user_role values(5, 'alla', 'ROLE_USER');

insert into user values(6, 'Максим', 0, 'maks', '111111', 'Пежо синий', '0960856655', 'maks@gmail.com');
insert into user_role values(6, 'maks', 'ROLE_USER');

insert into user values(7, 'Светлана', 1, 'svetlana', '111111', null, '0931125655', 'svetlana@gmail.com');
insert into user_role values(7, 'svetlana', 'ROLE_USER');

insert into user values(8, 'Валентина', 1, 'valya', '111111', null, '0950025222', 'valya@ukr.net');
insert into user_role values(8, 'valya', 'ROLE_USER');

insert into user values(9, 'Марина', 1, 'marina', '111111', null, '0631125755', 'marina@i.ua');
insert into user_role values(9, 'marina', 'ROLE_USER');

insert into user values(10, 'Никита', 0, 'nikita', '111111', 'Шевроле авео белый', '0960835655', 'nikita@gmail.com');
insert into user_role values(10, 'nikita', 'ROLE_USER');

insert into user values(11, 'Алексей', 0, 'alex', '111111', null, '0931125995', 'alex@gmail.com');
insert into user_role values(11, 'alex', 'ROLE_USER');

insert into user values(12, 'Лариса', 1, 'lora', '111111', null, '0950025122', 'lora@ukr.net');
insert into user_role values(12, 'lora', 'ROLE_USER');

insert into user values(13, 'Карина', 1, 'karina', '111111', null, '0631125785', 'karina@i.ua');
insert into user_role values(13, 'karina', 'ROLE_USER');

insert into user values(14, 'Сергей', 0, 'serg', '111111', 'Шкода серая', '0960877755', 'serg@gmail.com');
insert into user_role values(14, 'serg', 'ROLE_USER');

insert into user values(15, 'Евгений', 0, 'evgeniy', '111111', null, '0931120005', 'evgeniy@gmail.com');
insert into user_role values(15, 'evgeniy', 'ROLE_USER');

insert into user values(16, 'Эльвира', 1, 'elvira', '111111', null, '0959825122', 'elvira@ukr.net');
insert into user_role values(16, 'elvira', 'ROLE_USER');

insert into user values(17, 'Анна', 1, 'anna', '111111', null, '0637525785', 'anna@i.ua');
insert into user_role values(17, 'anna', 'ROLE_USER');

insert into user values(18, 'Иван', 0, 'ivan', '111111', 'Жигули синий', '0962874755', 'ivan@gmail.com');
insert into user_role values(18, 'ivan', 'ROLE_USER');

insert into user values(19, 'Борис', 0, 'boris', '111111', null, '0931155505', 'boris@gmail.com');
insert into user_role values(19, 'boris', 'ROLE_USER');

insert into user values(20, 'Марго', 1, 'margo', '111111', null, '0959825122', 'margo@ukr.net');
insert into user_role values(20, 'margo', 'ROLE_USER');

insert into user values(21, 'Анастасия', 1, 'annastasia', '111111', null, '0631111785', 'annastasia@i.ua');
insert into user_role values(21, 'annastasia', 'ROLE_USER');

insert into user values(22, 'Антон', 0, 'anton', '111111', 'Опель вектра сиреневый', '0962874719', 'anton@gmail.com');
insert into user_role values(22, 'anton', 'ROLE_USER');

insert into user values(23, 'Ярослав', 0, 'slavaY', '111111', null, '0931134905', 'slavaY@gmail.com');
insert into user_role values(23, 'slavaY', 'ROLE_USER');

insert into user values(24, 'Людмила', 1, 'luda', '111111', null, '0959987122', 'luda@ukr.net');
insert into user_role values(24, 'luda', 'ROLE_USER');

insert into user values(25, 'Дарья', 1, 'darya', '111111', null, '0631281785', 'darya@i.ua');
insert into user_role values(25, 'darya', 'ROLE_USER');

insert into trip values(1, '15-03-17 09:00', 2, 1, 6);

insert into trip_points values(1, 3);
insert into trip_points values(1, 4);
insert into trip_points values(1, 14);
insert into trip_points values(1, 15);

insert into trip_user values(3, 1);
insert into trip_user values(4, 1);
insert into trip_user values(5, 1);

insert into passenger_landing values(1, 1, 3, 'Кинотеатр Мир, 9-й мк. район');
insert into passenger_landing values(2, 1, 4, 'Кинотеатр Мир, 9-й мк. район');
insert into passenger_landing values(3, 1, 5, 'Пр. Победы, 10-й и 11-й мк. район, остановка ПриватБанк');

insert into trip values(2, '15-03-17 11:10', 6, 1, 7);

insert into trip_points values(2, 3);
insert into trip_points values(2, 4);
insert into trip_points values(2, 14);
insert into trip_points values(2, 18);

insert into trip_user values(7, 2);
insert into trip_user values(8, 2);
insert into trip_user values(9, 2);

insert into passenger_landing values(4, 2, 7, 'Кинотеатр Мир, 9-й мк. район');
insert into passenger_landing values(5, 2, 8, 'Кинотеатр Мир, 9-й мк. район');
insert into passenger_landing values(6, 2, 9, 'Пр. Победы, 10-й и 11-й мк. район, магазин Пчелка');

insert into trip values(3, '15-03-17 12:00', 10, 1, 6);

insert into trip_points values(3, 5);
insert into trip_points values(3, 6);
insert into trip_points values(3, 14);
insert into trip_points values(3, 18);

insert into trip_user values(11, 3);
insert into trip_user values(12, 3);
insert into trip_user values(13, 3);

insert into passenger_landing values(7, 3, 11, 'Наше дело, 1-й мк. район');
insert into passenger_landing values(8, 3, 12, 'Наше дело, 1-й мк. район');
insert into passenger_landing values(9, 3, 13, 'Ул. Харьковская, 1-й мк. район, перед поворотом на пьяный мост');

insert into trip values(4, '15-03-17 13:40', 14, 3, 5);

insert into trip_points values(4, 5);
insert into trip_points values(4, 6);
insert into trip_points values(4, 14);
insert into trip_points values(4, 18);

insert into trip values(5, '15-03-18 14:00', 14, 2, 5);

insert into trip_points values(5, 5);
insert into trip_points values(5, 6);
insert into trip_points values(5, 14);
insert into trip_points values(5, 18);

insert into trip values(6, '15-03-18 08:00', 18, 4, 6);

insert into trip_points values(6, 5);
insert into trip_points values(6, 6);
insert into trip_points values(6, 14);
insert into trip_points values(6, 18);

insert into trip values(7, '15-03-19 08:00', 18, 4, 6);

insert into trip_points values(7, 5);
insert into trip_points values(7, 6);
insert into trip_points values(7, 14);
insert into trip_points values(7, 18);

insert into trip values(8, '15-03-17 08:10', 22, 3, 5);

insert into trip_points values(8, 5);
insert into trip_points values(8, 6);
insert into trip_points values(8, 14);
insert into trip_points values(8, 18);





