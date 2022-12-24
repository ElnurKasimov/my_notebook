--TODO encrypt password
INSERT INTO users (id, user_name, email, password)
VALUES('e95c01cc-bdb3-42ae-a57d-d71d6b1f56ca','admin','admin@test.com','super_secret_password');
INSERT INTO roles (id, role) VALUES ('e95c01cc-bdb3-42ae-a57d-d71d6b1f56ca', 'ROLE_ADMIN');