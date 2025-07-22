-- Users
CREATE TABLE users (
  id BIGSERIAL PRIMARY KEY,
  email VARCHAR(255) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  display_name VARCHAR(100),
  created_at TIMESTAMP NOT NULL
);
-- Groups
CREATE TABLE groups (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  description TEXT,
  owner_id BIGINT NOT NULL REFERENCES users(id),
  created_at TIMESTAMP NOT NULL
);
-- Fields
CREATE TABLE fields (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  address TEXT,
  latitude DOUBLE PRECISION,
  longitude DOUBLE PRECISION,
  created_at TIMESTAMP NOT NULL
);
-- Matches
CREATE TYPE match_status AS ENUM ('SCHEDULED', 'CANCELLED', 'COMPLETED');
CREATE TABLE matches (
  id BIGSERIAL PRIMARY KEY,
  group_id BIGINT NOT NULL REFERENCES groups(id),
  field_id BIGINT NOT NULL REFERENCES fields(id),
  match_date_time TIMESTAMP NOT NULL,
  max_players INT,
  status match_status NOT NULL,
  created_at TIMESTAMP NOT NULL
);