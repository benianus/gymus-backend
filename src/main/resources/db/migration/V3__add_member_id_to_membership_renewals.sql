alter table membership_renewals
    add column member_id integer;

alter table membership_renewals
    add constraint member_id_fk foreign key (member_id) references members (id);