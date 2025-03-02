#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER "dynamic-encounter-generator" WITH PASSWORD 'dynamic-encounter-generator';
    CREATE DATABASE "dynamic-encounter-generator" with owner "dynamic-encounter-generator";
    GRANT ALL PRIVILEGES ON DATABASE "dynamic-encounter-generator" TO "dynamic-encounter-generator";
EOSQL