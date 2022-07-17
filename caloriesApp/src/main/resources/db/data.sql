INSERT INTO USER (id, email, lastname, name, password, phone, username)
VALUES (1, 'user1@email.com', 'user1', 'user1', '{bcrypt}$2y$12$y8uY.LDwuib2e.CFpoEF7.lWcG2P.n33o0jXBBUCc4pykroZn0RvS',
        '+37060090000', 'user1'),
       (2, 'user2@email.com', 'user2', 'user2', '{bcrypt}$2y$12$y8uY.LDwuib2e.CFpoEF7.lWcG2P.n33o0jXBBUCc4pykroZn0RvS',
        '+37060090000', 'user2'),
       (3, 'user3@email.com', 'user3', 'user3', '{bcrypt}$2y$12$y8uY.LDwuib2e.CFpoEF7.lWcG2P.n33o0jXBBUCc4pykroZn0RvS',
        '+37060090000', 'user3'),
       (4, 'user4@email.com', 'user4', 'user3', '{bcrypt}$2y$12$y8uY.LDwuib2e.CFpoEF7.lWcG2P.n33o0jXBBUCc4pykroZn0RvS',
        '+37060090000', 'user4'),
       (5, 'admin@email.com', 'adminas', 'adminukas',
        '{bcrypt}$2y$12$6C5T4j7HlR8CaokuYbtvMuKU5GAHJxVmq7v9oQonieq5jTAtEiRuG', '+37069099999', 'admin');

INSERT INTO ROLES (id, role_name)
VALUES (1, 'USER'),
       (2, 'ADMIN');

INSERT INTO USER_ROLES (user_id, role_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 2),
       (5, 1);
