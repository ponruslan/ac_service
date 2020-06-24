create table brands (
    id bigint not null auto_increment,
    name varchar(255),
    filename varchar(255),
    primary key (id)
    ) engine=InnoDB;
create table connections (
    id bigint not null auto_increment,
    content varchar(5120),
    primary key (id)
    ) engine=InnoDB;
create table errors (
    id bigint not null auto_increment,
    name varchar(255),
    content varchar(5120),
    primary key (id)
    ) engine=InnoDB;
create table products (
    id bigint not null auto_increment,
    name varchar(255),
    type varchar(255),
    brand_id bigint,
    connect_id bigint,
    size_id bigint,
    error_id bigint,
    primary key (id)
    ) engine=InnoDB;
create table sizes (
    id bigint not null auto_increment,
    content varchar(5120),
    filename varchar(255),
    primary key (id)
    ) engine=InnoDB;
create table user_role (
    user_id bigint not null,
    roles varchar(255)
    ) engine=InnoDB;
create table users (
    id bigint not null auto_increment,
    username varchar(255),
    password varchar(255),
    primary key (id)
    ) engine=InnoDB;
alter table products
    add constraint FKa3a4mpsfdf4d2y6r8ra3sc8mv
    foreign key (brand_id) references brands (id);
alter table products
    add constraint FKhlrhcqvokigok8be3306u4xh1
    foreign key (connect_id) references connections (id);
alter table products
    add constraint FKthq684ivd9cqdytk4g3sc5rsm
    foreign key (error_id) references errors (id);
alter table products
    add constraint FKjtp03phh84ohguj0rhvlk81g7
    foreign key (size_id) references sizes (id);
alter table user_role
    add constraint FKj345gk1bovqvfame88rcx7yyx
    foreign key (user_id) references users (id);