create EXTENSION IF NOT EXISTS "uuid-ossp";

create table if not exists "public"."states"(
  id UUID default uuid_generate_v4() not null primary key,
  name varchar(100) not null,
  description text,
  created_on timestamp default current_timestamp,
  modified_on timestamp default current_timestamp
);

create table if not exists "public"."cards"(
  id UUID default uuid_generate_v4() not null primary key,
  title varchar(100) not null,
  description text,
  deadline timestamp,
  state_id UUID references "public"."states"(id),
  created_on timestamp default current_timestamp,
  modified_on timestamp default current_timestamp
  );

create index card_title_idx on "public"."cards" (title);
create index state_name_idx on "public"."states" (name);
