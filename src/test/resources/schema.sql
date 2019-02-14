drop table currencies if exists
create table currencies (id bigint auto_increment not null, code varchar(3) not null, number varchar(3) not null, scaling bigint, title varchar(255) not null, primary key (id))
alter table currencies add constraint ux_code unique(code)

drop table requestlogs if exists
create table requestlogs (id bigint auto_increment not null, date varchar(255) not null, request varchar(255) not null, ip varchar(255) not null, primary key (id))