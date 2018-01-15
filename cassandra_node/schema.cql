CREATE KEYSPACE IF NOT EXISTS product_keyspace
WITH replication = {'class':'SimpleStrategy', 'replication_factor':'2'}
AND durable_writes = true;

CREATE KEYSPACE IF NOT EXISTS user_keyspace
WITH replication = {'class':'SimpleStrategy', 'replication_factor':'2'}
AND durable_writes = true;

CREATE KEYSPACE IF NOT EXISTS order_keyspace
WITH replication = {'class':'SimpleStrategy', 'replication_factor':'2'}
AND durable_writes = true;

CREATE TABLE IF NOT EXISTS product_keyspace.product (
id UUID,
price decimal,
description varchar,
image_url varchar,
PRIMARY KEY (id));


CREATE TABLE IF NOT EXISTS user_keyspace.user (
id UUID,
first_name varchar,
last_name varchar,
email varchar,
PRIMARY KEY (id));

CREATE TYPE order_keyspace.order_address_type (
id UUID,
country varchar,
city varchar,
address varchar,
phone varchar
);

CREATE TYPE order_keyspace.order_item_type (
id UUID,
product_id UUID,
amount int,
price decimal,
);

CREATE TABLE IF NOT EXISTS order_keyspace.order_info (
id UUID,
user_id UUID,
address frozen <order_address_type>,
items list<frozen <order_item_type>>,
status varchar,
total_price decimal,
PRIMARY KEY (id, status));

CONSISTENCY QUORUM;