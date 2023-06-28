create EXTENSION IF NOT EXISTS "uuid-ossp";

create table IF not EXISTS "public"."users"(
  id UUID default uuid_generate_v4() not null primary key,
  email varchar(256) not null,
  password varchar(256) not null,
  first_name varchar(50),
  last_name varchar(50)
);

create index user_email_idx on "public"."users" using btree (email);

alter table "public"."cards" add column created_by UUID references "public"."users"(id);

create index card_user_idx on "public"."cards" (created_by);
