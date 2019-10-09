FROM postgres:11
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD 1234
ENV POSTGRES_DB atm
WORKDIR /docker-entrypoint-initdb.d/
COPY drop_table.sql 0.sql
COPY create_db_atm.sql 1.sql
COPY create_table_contas.sql 2.sql
COPY insert_into_contas.sql 3.sql