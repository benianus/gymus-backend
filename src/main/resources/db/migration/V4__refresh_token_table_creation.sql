create table refresh_tokens
(
    id            int not null primary key generated always as identity,
    user_id       int not null unique references users (id),
    refresh_token text,
    revoked_at    timestamp(2),
    expires_at    timestamp(2)
)