-- Notes
create table notes
(
    note_id  bigserial not null,
    user_id  bigint,
    priority varchar(255) check (priority in ('LOW', 'MEDIUM', 'HIGH')),
    text     varchar(255),
    title    varchar(255),
    primary key (note_id)
);
-- User role
create table user_role
(
    user_id bigint not null,
    role    varchar(255) check (role in ('USER', 'ADMIN'))
);
-- Users
create table users
(
    user_id   bigserial    not null,
    email     varchar(100) unique,
    full_name varchar(255),
    password  varchar(255) not null,
    primary key (user_id)
);

-- Constraints
alter table if exists notes
    add constraint fk_notes_user_id_users_id
        foreign key (user_id)
            references users;
alter table if exists user_role
    add constraint fk_user_role_user_id_users_id
        foreign key (user_id)
            references users;

-- Indexes
create index users_email_idx
    on users (email);
create index users_id_idx
    on users (user_id);