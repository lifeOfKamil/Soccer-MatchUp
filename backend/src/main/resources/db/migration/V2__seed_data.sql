-- Insert a user
INSERT INTO users (email, password_hash, display_name, created_at)
VALUES (
    'tony@example.com',
    '<bcrypt‑hashed‑pw>',
    'Tony',
    '2025-07-14T10:00:00'
  );
-- Insert a field
INSERT INTO fields (name, address, latitude, longitude, created_at)
VALUES (
    'Lincoln Park Field',
    '2045 N Clark St, Chicago, IL',
    41.9250,
    -87.6417,
    '2025-07-14T10:00:00'
  );
-- Insert a group
INSERT INTO groups (name, description, owner_id, created_at)
VALUES (
    'Chicago Soccer League',
    'A local soccer league for enthusiasts',
    1,
    '2025-07-14T10:00:00'
  );
-- Insert a match
INSERT INTO matches (
    group_id,
    field_id,
    match_date_time,
    max_players,
    status,
    created_at
  )
VALUES (
    1,
    1,
    '2025-07-20T15:00:00',
    10,
    'SCHEDULED',
    '2025-07-14T10:00:00'
  );