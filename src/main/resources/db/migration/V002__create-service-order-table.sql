create table service_order(
    id bigint not null auto_increment,
    client_id bigint not null,
    description text not null,
    price decimal(10, 2) not null,
    status varchar(20) not null,
    date_opening datetime not null,
    date_finish datetime,

    primary key(id),
    constraint fk_service_order foreign key(client_id) references client(id)
);