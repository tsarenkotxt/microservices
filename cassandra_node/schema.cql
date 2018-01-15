CREATE KEYSPACE IF NOT EXISTS product_keyspace
WITH replication = {'class':'SimpleStrategy', 'replication_factor':'2'}
AND durable_writes = TRUE;

CREATE KEYSPACE IF NOT EXISTS user_keyspace
WITH replication = {'class':'SimpleStrategy', 'replication_factor':'2'}
AND durable_writes = TRUE;

CREATE TABLE IF NOT EXISTS product_keyspace.product (
id UUID,
price decimal,
description varchar,
imageUrl varchar,
PRIMARY KEY (id));


CREATE TABLE IF NOT EXISTS user_keyspace.user (
id UUID,
first_name varchar,
last_name varchar,
email varchar,
PRIMARY KEY (id));

CONSISTENCY QUORUM;