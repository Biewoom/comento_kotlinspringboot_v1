-- Album --
INSERT INTO ITEM
    (DTYPE, NAME, PRICE, QUANTITY, ARTIST)
VALUES
    ('album', 'Attention', 10000, 100, '뉴진스'),
    ('album', 'Dynamite', 10000, 100, 'BTS' );

-- Movie --
INSERT INTO ITEM
    (DTYPE, NAME, PRICE, QUANTITY, ACTOR, DIRECTOR)
VALUES
    ('movie', '범죄도시', 30000, 5, '마동석', '강윤성'),
    ('movie', '헤어질 결심', 30000, 5, '박해일', '박찬욱');

-- Book --
INSERT INTO ITEM
    (DTYPE, NAME, PRICE, QUANTITY, AUTHOR)
VALUES
    ('book', '토비의 스프링', 50000, 10,'토비'),
    ('book', '클린코드', 30000, 5,'켄트백');