create table users
(
    id         int          not null primary key generated always as identity,
    username   varchar(255) not null unique,
    password   varchar(255) not null,
    role       varchar(50)  not null default 'OWNER',
    created_at timestamp(2)          default current_timestamp(2),
    updated_at timestamp(2)          default current_timestamp(2)
);

create table members
(
    id          int          not null primary key generated always AS IDENTITY,
    first_name  varchar(100) not null,
    last_name   varchar(100) not null,
    birthdate   date         not null,
    email       varchar(255) not null unique,
    phoneNumber varchar(25)  not null unique,
    address     text         not null,
    created_by  int          not null references users (id) on delete cascade,
    created_at  timestamp(2) default current_timestamp(2),
    updated_at  timestamp(2) default current_timestamp(2)
);

create table member_documents
(
    id                     int  not null primary key generated always as identity,
    birth_certificate      text not null,
    medical_certificate    text not null,
    personal_photo         text not null,
    parental_authorization text         default null,
    member_id              int  not null unique references members (id) on delete cascade,
    added_by               int  not null references users (id) on delete cascade,
    created_at             timestamp(2) default current_timestamp(2),
    updated_at             timestamp(2) default current_timestamp(2)
);

create table membership_type
(
    id         int          not null primary key generated always as identity,
    name       varchar(255) not null,
    price      numeric      not null check ( price > 0 ),
    created_at timestamp(2) default current_timestamp(2),
    updated_at timestamp(2) default current_timestamp(2)
);

create table memberships
(
    id                 int not null primary key generated always as identity,
    member_id          int not null unique references members (id) on delete cascade,
    start_date         date         default current_date,
    end_date           date         default (current_date + 30),
    membership_type_id int not null references membership_type (id) on delete cascade,
    created_by         int not null references users (id) on delete cascade,
    created_at         timestamp(2) default current_timestamp(2),
    updated_at         timestamp(2) default current_timestamp(2)
);

create table members_cards
(
    id            int        not null primary key generated always as identity,
    join_date     date       not null default current_date,
    member_id     int unique not null references members (id) on delete cascade,
    membership_id int unique not null references memberships (id) on delete cascade,
    created_by    int        not null references users (id) on delete cascade,
    created_at    timestamp(2)        default current_timestamp(2),
    updated_at    timestamp(2)        default current_timestamp(2)
);

create table membership_renewals
(
    id            int  not null primary key generated always as identity,
    member_id     int  not null references members (id) on delete cascade,
    renewed_date  date not null default current_date,
    membership_id int  not null references memberships (id) on delete cascade,
    created_by    int  not null references users (id) on delete cascade,
    created_at    timestamp(2)  default current_timestamp(2),
    updated_at    timestamp(2)  default current_timestamp(2)
);

create table member_attendances
(
    id           int  not null primary key generated always as identity,
    checked_date date not null default current_date,
    member_id    int  not null references members (id) on delete cascade,
    created_by   int  not null references users (id) on delete cascade,
    created_at   timestamp(2)  default current_timestamp(2),
    updated_at   timestamp(2)  default current_timestamp(2)
);

create table session_types
(
    id         int          not null primary key generated always as identity,
    name       varchar(100) not null,
    price      numeric      not null,
    created_at timestamp(2) default current_timestamp(2),
    updated_at timestamp(2) default current_timestamp(2)
);

create table sessions
(
    id              int          not null primary key generated always as identity,
    full_name       varchar(255) not null,
    session_type_id int          not null references session_types (id) on delete cascade,
    created_by      int          not null references users (id) on delete cascade,
    created_at      timestamp(2) default current_timestamp(2),
    updated_at      timestamp(2) default current_timestamp(2)
);

create table products
(
    id                  int          not null primary key generated always as identity,
    product_name        varchar(255) not null,
    product_description text         not null,
    product_image       text         not null,
    quantity            int          not null,
    price               numeric      not null,
    added_by            int          not null references users (id) on delete cascade,
    created_at          timestamp(2) default current_timestamp(2),
    updated_at          timestamp(2) default current_timestamp(2)
);

create table sales
(
    id          int     not null primary key generated always as identity,
    product_id  int     not null references products (id) on delete cascade,
    quantity    int     not null,
    total_price numeric not null check ( total_price > 0 ),
    made_by     int     null default null references users (id) on delete cascade,
    created_at  timestamp(2) default current_timestamp(2),
    updated_at  timestamp(2) default current_timestamp(2)
);

create table refresh_tokens
(
    token      text primary key,
    created_at timestamp not null,
    updated_at timestamp not null,
    user_id    int       not null references users (id) on delete cascade,
    expires_at timestamp,
    revoked_at timestamp
);
-- users
-- members
-- memberships
-- member_documents
-- membership_types
-- membership_renewals
-- member_attendances
-- members_cards
-- sessions
-- session_types
-- products
-- sales