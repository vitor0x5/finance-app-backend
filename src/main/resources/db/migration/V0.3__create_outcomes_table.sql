CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

ALTER TABLE incomes
ADD COLUMN place VARCHAR;