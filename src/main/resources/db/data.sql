
INSERT INTO user_tb(username, password, phone, name, birth, gender, height, user_img, total_coin, goal_muscle, goal_fat, created_at)
VALUES ('ssar', '1234', '010-0100-0100', '김백미', '1994-04-22', 'F', 178.5, null, 500, 35.0, 12.0, now());

INSERT INTO activity_tb(user_id, walking,drink_water,created_at) VALUES (1,2222,500,now());

INSERT INTO body_tb(user_id, weight, muscle, fat, created_at) VALUES (1,75.8,27.6,20.1,now());

insert into which_challengenge_tb(challenge_name, challenge_filename, meter, badge_filename, location, content)
values ('챌린지명', '챌린지 사진경로', 100, '뱃지사진', '산 지역', '챌린지 내용'),
       ('챌린지명2', '챌린지 사진경로', 200, '뱃지사진', '산 지역2', '챌린지 내용2');

insert into challenge_tb(user_id, total_walking, which_challenge_id, opening_time, closing_time, status, created_at)
values (1, 100, 1, '2024-04-23', '2024-04-24', null, now());