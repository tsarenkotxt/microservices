#!/bin/bash

 if [ -n ${INIT_DB} ] && [ ${INIT_DB} == true ]; then
   echo "Waiting for Cassandra to start..."

   while ! nc -z localhost 9042; do
      sleep 3
   done

   echo "Initialize a database."
   cqlsh -f /schema.cql
 fi &

 exec /docker-entrypoint.sh "$@"
