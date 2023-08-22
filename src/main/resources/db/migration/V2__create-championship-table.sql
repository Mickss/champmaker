CREATE TABLE champmaker.championship (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    city VARCHAR(100),
    date DATE,
    status VARCHAR(100)
);