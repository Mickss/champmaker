CREATE TABLE champmaker.registered_team (
    id SERIAL PRIMARY KEY,
    champ_id INT NOT NULL,
    team_id INT NOT NULL,
    registration_date TIMESTAMP,
    champ_group VARCHAR(100)
);
