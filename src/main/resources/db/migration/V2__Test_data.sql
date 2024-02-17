INSERT INTO users (email, password, full_name)
VALUES ('test@gmail.com',
        '$2a$12$Nqrkpk0sBiQOjx/FT9HMJ.QMyBSps75zbTOcPKB.f82bZkQM04VWy', -- 1111
        'Anatolii Omelchenko');

INSERT INTO user_role
VALUES (1, 'USER');
INSERT INTO user_role
VALUES (1, 'ADMIN');

INSERT INTO notes (user_id, priority, text, title)
VALUES
    (1, 'MEDIUM', 'Запланувати зустріч з колегами для обговорення проекту', 'Зустріч з колегами'),
    (1, 'HIGH', 'Приготуватися до презентації на наступному засіданні', 'Підготовка до презентації'),
    (1, 'LOW', 'Купити інгредієнти для нового рецепту', 'Покупки для рецепту'),
    (1, 'MEDIUM', 'Відправити електронний лист з подякою клієнту за співпрацю', 'Лист клієнту'),
    (1, 'HIGH', 'Планування відпустки та бронювання готелю', 'Відпустка і бронювання');