create table commentary(
    id bigint not null auto_increment,
    service_order_id bigint not null,
    description text not null,
    dateCommentary datetime not null,

    primary key(id),
    constraint commentary_fk foreign key(service_order_id) references service_order(id)
)