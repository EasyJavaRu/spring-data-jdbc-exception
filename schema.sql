CREATE ROLE types WITH PASSWORD 'types';
ALTER ROLE types WITH LOGIN;

CREATE DATABASE types OWNER types;

\c types

CREATE OR REPLACE FUNCTION fail_me() RETURNS integer AS $carriers_beacon_edit$
  BEGIN
    RAISE SQLSTATE 'SJ001';
    RETURN 1;
  END
$carriers_beacon_edit$ LANGUAGE plpgsql;
