CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE  incomes (
  id uuid DEFAULT uuid_generate_v4(),
  user_id uuid NOT NULL,
  description VARCHAR NOT NULL,
  value BIGINT NOT NULL,
  income_date DATE NOT NULL,
  updated_at TIMESTAMP NOT NULL DEFAULT NOW(),

  FOREIGN KEY (user_id) references users (id),
  PRIMARY KEY (id, user_id)
)