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
    CONSTRAINT pk_userroles PRIMARY KEY (role_id, user_id)
);