#!/bin/bash

cd cassandra_node && docker build -t cassandra-node . && cd ../
cd api_service && ./mvnw -Dmaven.test.skip=true clean install dockerfile:build && cd ../
cd user_service && ./mvnw -Dmaven.test.skip=true clean install dockerfile:build && cd ../
cd order_service && ./mvnw -Dmaven.test.skip=true clean install dockerfile:build && cd ../
cd product_service && ./mvnw -Dmaven.test.skip=true clean install dockerfile:build && cd ../
cd consumer_service && ./mvnw -Dmaven.test.skip=true clean install dockerfile:build && cd ../
