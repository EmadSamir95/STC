create table file (id bigserial not null, bytes oid, item_id bigint, primary key (id));
create table item (id bigserial not null, name varchar(255), type varchar(255) check (type in ('Space','Folder','File')), permission_group_id bigint, primary key (id));
create table permission (id bigserial not null, permission_level varchar(255) check (permission_level in ('VIEW','EDIT')), user_email varchar(255), group_id bigint, primary key (id));
create table permission_group (id bigserial not null, name varchar(255), primary key (id));
alter table if exists file add constraint UK_10slfrt3wo3riqlc5olxv0rse unique (item_id);
alter table if exists file add constraint FK5gx9qw46gc6mr17noimfp2o4y foreign key (item_id) references item;
alter table if exists item add constraint FKjtcinrue6wu7cvno03243s3jy foreign key (permission_group_id) references permission_group;
alter table if exists permission add constraint FKqp7umovkuakff1jilk6dp9l1x foreign key (group_id) references permission_group;