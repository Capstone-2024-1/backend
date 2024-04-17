create table category
(
    id           bigint auto_increment,
    parent_id    bigint,
    english_name varchar(255),
    korean_name  varchar(255),
    primary key (id)
);

insert into category (id, parent_id, english_name, korean_name)
values (1, null, 'Fruits', '과일'),
       (2, null, 'Nuts', '견과'),
       (3, null, 'Vegetables', '채소'),
       (4, null, 'Grains', '곡식'),
       (5, null, 'Meet', '축산'),
       (6, null, 'Eggs', '계란'),
       (7, null, 'Seafood', '어패류'),
       (8, null, 'Seasonings', '조미료'),
       (9, null, 'Dairy', '유제품'),
       (10, null, 'Gluten', '글루텐');

## Fruits
insert into category (id, parent_id, english_name, korean_name)
values (11, 1, 'Apple', '사과'),
       (12, 1, 'Kiwi', '키위'),
       (13, 1, 'Peach', '복숭아'),
       (14, 1, 'Banana', '바나나'),
       (15, 1, 'mango', '망고'),
       (16, 1, 'Other fruits', '기타 과일류');

## Nuts
insert into category (id, parent_id, english_name, korean_name)
values (17, 2, '호두', 'Walnut'),
       (18, 2, '아몬드', 'Almond'),
       (19, 2, '피스타치오', 'Pistachio'),
       (20, 2, '헤이즐넛', 'Hazelnut'),
       (21, 2, '잣', 'Pinenuts'),
       (22, 2, '땅콩', 'Peanut'),
       (23, 2, '기타 견과류', 'Other nuts');

## Vegetables
insert into category (id, parent_id, english_name, korean_name)
values (24, 3, '잎줄기 채소', 'herbage crop'),
       (25, 3, '뿌리 채소', 'Root Vegetables'),
       (26, 3, '과일 채소', 'Fruiting Vegetables');

## herbage crop
insert into category (id, parent_id, english_name, korean_name)
values (27, 24, '양파', 'Onion'),
       (28, 24, '마늘', 'Garlic'),
       (29, 24, '파', 'Green onion'),
       (30, 24, '부추', 'Chives'),
       (31, 24, '기타 잎줄기 채소', 'Other herbage crop');

## Root Vegetables
insert into category (id, parent_id, english_name, korean_name)
values (32, 25, '감자', 'Potato'),
       (33, 25, '고구마', 'Sweet potato'),
       (34, 25, '무', 'Radish'),
       (35, 25, '달래', 'wild chive'),
       (36, 25, '기타 뿌리 채소', 'Other root vegetables');

## Fruiting Vegetables
insert into category (id, parent_id, english_name, korean_name)
values (37, 26, '오이', 'Cucumber'),
       (38, 26, '고추', 'Chilly'),
       (39, 26, '토마토', 'Tomato'),
       (40, 26, '기타 열매 채소', 'Other fruit vegetables');
