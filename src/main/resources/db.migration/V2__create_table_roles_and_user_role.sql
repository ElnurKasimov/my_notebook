CREATE TABLE roles
(
    id UUID NOT NULL,
    role VARCHAR(250) NOT NULL,
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE userRoles
(
    role_id UUID NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT pk_userroles PRIMARY KEY (role_id, user_id),
    CONSTRAINT uc_roles_role UNIQUE (role),
    CONSTRAINT fk_userroles_on_role_d_a_o FOREIGN KEY (role_id) REFERENCES roles (id),
    CONSTRAINT fk_userroles_on_user_d_a_o FOREIGN KEY (user_id) REFERENCES users (id)
);