create table refresh_tokens
(
    token      text primary key,
    created_at timestamp not null,
    updated_at timestamp not null,
    user_id    int       not null references users (id) on delete cascade,
    expires_at timestamp,
    revoked_at timestamp
);