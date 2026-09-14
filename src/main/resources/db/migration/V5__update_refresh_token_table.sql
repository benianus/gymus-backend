alter table public.refresh_tokens
    alter column revoked_at type timestamp using revoked_at::timestamp;

alter table public.refresh_tokens
    alter column expires_at type timestamp using expires_at::timestamp;