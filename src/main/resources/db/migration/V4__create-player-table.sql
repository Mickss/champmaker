CREATE TABLE champmaker.player (
    id SERIAL PRIMARY KEY,
    team_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    shirt_number INT,
    birth_date DATE
);