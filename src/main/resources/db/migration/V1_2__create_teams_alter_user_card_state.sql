create EXTENSION IF NOT EXISTS "uuid-ossp";

create table if not exists "public"."teams"(
	id UUID default uuid_generate_v4() not null primary key,
	name varchar(100) not null,
	created_on timestamp default current_timestamp,
    modified_on timestamp default current_timestamp
);

create index team_name_idx on "public"."teams" using btree (name);

alter table public.cards add column assigned_to UUID references public.users(id);
alter table public.cards add column team_id UUID references public.teams(id);
alter table public.users add column team_id UUID references public.teams(id);
alter table public.users add column created_on timestamp default current_timestamp;
alter table public.users add column modified_on timestamp default current_timestamp;
alter table public.states add column team_id UUID references public.teams(id);



