CREATE TABLE pictures (
        id SERIAL PRIMARY KEY,
        image BYTEA ,
        title TEXT ,
        create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        user_id INTEGER NOT NULL REFERENCES users(id)
);