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

### Grains
insert into category (id, parent_id, english_name, korean_name)
values (41, null, '곡식', 'Grain'),
       (42, 41, '쌀', 'Rice'),
       (43, 41, '밀', 'Wheat'),
       (44, 41, '옥수수', 'Corn'),
       (45, 41, '메밀', 'Buckwheat'),
       (46, 41, '콩', 'Beans'),
       (47, 41, '기타 곡류', 'Other Grains');

### Meet
insert into category (id, parent_id, english_name, korean_name)
values (48, null, '고기', 'Meat'),
       (49, 48, '소고기', 'Beef'),
       (50, 48, '돼지고기', 'Pork'),
       (51, 48, '가금류', 'Poultry'),
       (52, 48, '양고기', 'Lamb'),
       (53, 48, '말고기', 'Horse Meat');

### Poultry
insert into category (id, parent_id, english_name, korean_name)
values (54, 51, '닭고기', 'Chicken'),
       (55, 51, '오리고기', 'Duck');

### Egg
insert into category (id, parent_id, english_name, korean_name)
values (56, null, '계란', 'Eggs');

### seafood
insert into category (id, parent_id, english_name, korean_name)
values (57, null, '어패류', 'Sea foods');

### depth 1
insert into category (id, parent_id, english_name, korean_name)
values (58, 57, '생선', 'fish'),
       (59, 57, '기타 연체류', 'Other Mollusks'),
       (60, 57, '갑각류', 'Crustaceans'),
       (61, 57, '조개류', 'Shellfish'),
       (62, 57, '기타 어패류', 'Other seafood');

### Fish
insert into category (id, parent_id, english_name, korean_name)
values (63, 58, '고등어', 'Mackerel'),
       (64, 58, '기타 생선', 'Other Fish');

### Crustaceans
insert into category (id, parent_id, english_name, korean_name)
values (65, 60, '새우', 'Shrimp'),
       (66, 60, '게', 'Crab'),
       (67, 60, '기타 갑각류', 'Other Crustaceans');

### Shellfish
insert into category (id, parent_id, english_name, korean_name)
values (68, 61, '전복', 'Abalone'),
       (69, 61, '굴', 'Oyster'),
       (70, 61, '홍합', 'Mussel'),
       (71, 61, '기타 조개류', 'Other Shellfish');


### Seasonings
insert into category (id, parent_id, english_name, korean_name)
values (72, null, '조미료', 'Seasonings'),
       (73, 72, '고추', 'Pepper'),
       (74, 72, '생강', 'Ginger'),
       (75, 72, '꿀', 'Honey'),
       (83, 72, '홍거', 'Asafoetida'),
       (76, 72, '기타 양념류', 'Other seasonings');

### Gluten
insert into category (id, parent_id, english_name, korean_name)
values (77, null, '글루텐', 'Gluten');

### Gluten
insert into category (id, parent_id, english_name, korean_name)
values (78, null, '유제품', 'Dairy'),
       (79, 78, '우유', 'Milk'),
       (80, 78, '치즈', 'Cheese'),
       (81, 78, '버터', 'Butter'),
       (82, 78, '기타 유제품', 'Other dairy products');