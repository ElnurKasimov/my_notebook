insert into roles(id, role) values('b7308272-2c87-4138-9680-a9fbbc1fed19', 'ROLE_USER');
insert into roles(id, role) values('fcf12a8d-91e9-4bb6-b60b-53d6d97a9f44', 'ROLE_ADMIN');

INSERT INTO users (id, user_name, email, password)
VALUES('e95c01cc-bdb3-42ae-a57d-d71d6b1f56ca','admin','admin@admin.com','$2a$10$LBMzPAUTWW.OGGRXRp42SOYaFYNsoo2qZUPKmKXEk8AazeF0HN71u');
INSERT INTO users (id, user_name, email, password)
VALUES('e95c01cc-bdb3-42ae-a57d-d71d6b1f56cb','user','user@user.com','$2a$10$DDaJSSI6Kc.kWthxggHl6ObfcwBpQkJuhIpZ3sXSKlKrQlYGKXj4C');


insert into userRoles (role_id, user_id) VALUES
('fcf12a8d-91e9-4bb6-b60b-53d6d97a9f44', 'e95c01cc-bdb3-42ae-a57d-d71d6b1f56ca');
insert into userRoles (role_id, user_id) VALUES
('b7308272-2c87-4138-9680-a9fbbc1fed19', 'e95c01cc-bdb3-42ae-a57d-d71d6b1f56cb');