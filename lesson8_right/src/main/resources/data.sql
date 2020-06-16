insert into genre (id,name) values (1,'Роман');
insert into genre (id,name) values (3,'Роман в стихах');
insert into genre (id,name) values (4,'Детективные рассказы');
insert into genre (id,name) values (5,'Повесть');
insert into genre (id,name) values (6,'Сказки');
insert into genre (id,name) values (7,'Рассказ');



insert into author (id,name) values (1,'Виктор Гюго');
insert into author (id,name) values (2,'Александр Дюма');
insert into author (id,name) values (3,'Александр Пушкин');
insert into author (id,name) values (4,'Артур Конан Дойль');
insert into author (id,name) values (5,'Джек Лондон');
insert into author (id,name) values (6,'Ханс Кристиан Андерсен');
insert into author (id,name) values (7,'Джеймс Олдридж');




insert into book (id,title,genre_id,author_id) values (1,'Отверженные',1,1);
insert into book (id,title,genre_id,author_id) values (2,'Три мушкетера',1,2);
insert into book (id,title,genre_id,author_id) values (3,'Евгений Онегин',3,3);
insert into book (id,title,genre_id,author_id) values (4,'Шерлок Холмс',4,4);
insert into book (id,title,genre_id,author_id) values (5,'Зов предков',5,5);
insert into book (id,title,genre_id,author_id) values (6,'Гадкий утенок',6,6);
insert into book (id,title,genre_id,author_id) values (7,'Последний дюйм',7,7);

insert into comment (id,text,book_id) values(1,'История великого детектива ставшево легендой. Книга очень легко читается, прям на одном дыхании.',4);
insert into comment (id,text,book_id) values(2,'Очень хорошая книга, и фильм с Ливановым и Сломиным прекрасен',4);
insert into comment (id,text,book_id) values(3,'Еще один роман Виктора Гюго наполненный разноплановыми образами людей которые сильны духом. У каждого героя свои испытания и своя сложная судьба, которые переплетаются между собой создавая паутину на фоне французской революции. Проблема противостояния закона и справедливости, пожертвование одним человеком ради многих и готовность возложить свою жизнь на алтарь равноправия и правосудия',1);
insert into comment (id,text,book_id) values(4,'Рассказ пропитан не столь экстремальными событиями которые происходят в нем, сколь сложностями взаимоотношений между отцом и сыном. Неуверенность отца в возможностях сына порождают сомнения в самом ребенке. История о том как нужно прислушиваться к своим детям чтобы в один момент доверитьб им свою жизнь.',7);