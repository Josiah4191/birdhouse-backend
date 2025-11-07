create table employee
(
    employee_id    int generated always as identity primary key,
    last_name      varchar(50) not null,
    first_name     varchar(50) not null,
    job_title      varchar(50) null,
    street_address varchar(200) null,
    city           varchar(50) null,
    state          varchar(50) null,
    zip            varchar(10) null,
    phone          varchar(20) null,
    email          varchar(254) null,
    hire_date      date null,
    release_date   date null,
    birth_date     date null,
    salary         decimal(19, 2) null check (salary >= 0),
    created_at     timestamp default current_timestamp,
    updated_at     timestamp default current_timestamp
);

create table customer
(
    customer_id  int generated always as identity primary key,
    company_name varchar(50) not null,
    first_name   varchar(50) not null,
    last_name    varchar(50) not null,
    title        varchar(50) null,
    address      varchar(200) null,
    city         varchar(50) null,
    state        varchar(50) null,
    zip          varchar(10) null,
    phone        varchar(20) null,
    email        varchar(254) null,
    created_at   timestamp default current_timestamp,
    updated_at   timestamp default current_timestamp
);

create table customer_order
(
    order_id      int         not null primary key,
    customer_id   int         not null references customer (customer_id),
    order_status  varchar(30) not null default 'NEW',
    order_date    date        not null,
    required_date date null,
    created_at    timestamp            default current_timestamp,
    updated_at    timestamp            default current_timestamp
);

create table shipper
(
    shipper_id   int generated always as identity primary key,
    shipper_name varchar(50) not null,
    contact_name varchar(50) null,
    phone        varchar(20) null,
    email        varchar(254) null,
    created_at   timestamp default current_timestamp,
    updated_at   timestamp default current_timestamp
);

create table shipment
(
    shipment_id     int generated always as identity primary key,
    order_id        int         not null references customer_order (order_id),
    shipper_id      int         not null references shipper (shipper_id),
    shipment_status varchar(30) not null default 'PENDING',
    first_name      varchar(50) null,
    last_name       varchar(50) null,
    address         varchar(200) null,
    city            varchar(50) null,
    state           varchar(50) null,
    zip             varchar(10) null,
    created_at      timestamp            default current_timestamp,
    updated_at      timestamp            default current_timestamp
);

create table packing_slip
(
    packing_slip_id int generated always as identity primary key,
    package_number  int not null,
    shipment_id     int not null references shipment (shipment_id) on delete cascade,
    employee_id     int not null references employee (employee_id),
    shipped_date    date null,
    unique (package_number, shipment_id),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

create table inventory_part
(
    inventory_part_id int generated always as identity primary key,
    part_number       varchar(50)    not null unique,
    description       varchar(255)   not null,
    stock_price       decimal(19, 2) not null check (stock_price >= 0),
    weight            decimal(10, 2) not null check (weight >= 0),
    stock_level       int            not null check (stock_level >= 0),
    reorder_level     int            not null check (reorder_level >= 0),
    stock_on_order    int null,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);

create table customer_order_line
(
    order_line_id     int generated always as identity primary key,
    order_id          int            not null references customer_order (order_id) on delete cascade,
    inventory_part_id int            not null references inventory_part (inventory_part_id),
    quantity          int            not null check (quantity >= 0),
    unit_price        decimal(19, 2) not null check (unit_price >= 0),
    discount          decimal(19, 2) null check (discount >= 0 and discount <= unit_price),
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp,
    unique (order_id, inventory_part_id)
);

create index ix_customer_order_customer_id on customer_order (customer_id);
create index ix_shipment_order_id on shipment (order_id);
create index ix_shipment_shipper_id on shipment (shipper_id);
create index ix_packing_slip_shipment_id on packing_slip (shipment_id);
create index ix_packing_slip_employee_id on packing_slip (employee_id);
create index ix_customer_order_line_order_id on customer_order_line (order_id);
create index ix_customer_order_line_inventory_part_id on customer_order_line (inventory_part_id);

