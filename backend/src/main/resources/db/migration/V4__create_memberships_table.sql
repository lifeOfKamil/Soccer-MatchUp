CREATE TABLE memberships (
  id         BIGSERIAL PRIMARY KEY,
  user_id    BIGINT NOT NULL
    REFERENCES users(id)
    ON DELETE CASCADE,
  group_id   BIGINT NOT NULL
    REFERENCES groups(id)
    ON DELETE CASCADE,
  role       VARCHAR(255) NOT NULL,
  joined_at  TIMESTAMP NOT NULL
);