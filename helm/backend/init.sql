CREATE TABLE IF NOT EXISTS REQUEST_HISTORY (
    ID SERIAL PRIMARY KEY,
    URL TEXT NOT NULL,
    SUMMARY TEXT NOT NULL,
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);