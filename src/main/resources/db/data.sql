INSERT INTO USERS (USERNAME, FULL_NAME, PASSWORD, EMAIL, GENDER, AGE, WEIGHT, HEIGHT, ACTIVITY_LEVEL)
VALUES ('user1', 'henrikas','$2a$12$3sZZG59f7t/LC7otGjnufu9ASzdExbQwy.j46Zg9qewCQ20ugUM.O','d@d.com','men', 25, 85, 185, 'moderately');

INSERT INTO food (name, calories, protein, carbs, fat, fiber)
VALUES( 'smoked salmon', 200,20,0,13,0),
      ('chicken', 120,20,0,4,0);

INSERT INTO CALORIES ( GOAL_CALORIES, CALORIES_LEFT, CALORIES_CONSUMED)
VALUES (3087,3087,0);

INSERT INTO ROLE (id, role_name)
VALUES (1, 'USER'),
       (2, 'ADMIN');


-- INSERT INTO USERS_ROLES  (user_id, role_id)
-- VALUES (1, 1);


