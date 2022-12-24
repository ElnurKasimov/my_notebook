ALTER TABLE roles
    ADD CONSTRAINT uc_roles_role UNIQUE (role);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE userRoles
    ADD CONSTRAINT fk_userroles_on_role_d_a_o FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE userRoles
    ADD CONSTRAINT fk_userroles_on_user_d_a_o FOREIGN KEY (user_id) REFERENCES users (id);