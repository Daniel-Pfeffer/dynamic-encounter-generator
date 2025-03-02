create type monster_source as enum ('MANUAL', 'EXTERNAL');

alter table monster
    add column source monster_source default 'EXTERNAL' not null;

alter table monster
    alter column external_id drop not null