FROM cassandra

ARG INIT_DB
ENV init $INIT_DB
COPY entrypoint-wrap.sh /entrypoint-wrap.sh
COPY schema.cql /schema.cql
RUN chmod +x /entrypoint-wrap.sh
RUN chmod +x /schema.cql
RUN apt-get update && apt-get -y install netcat && apt-get clean
ENTRYPOINT ["/entrypoint-wrap.sh"]
CMD ["cassandra", "-f"]