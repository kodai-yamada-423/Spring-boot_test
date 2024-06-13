CREATE TABLE pictures (
    id SERIAL PRIMARY KEY,
    image TEXT NOT NULL ,
    title TEXT ,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id INTEGER NOT NULL REFERENCES users(id)
)