CREATE TABLE champmaker.registered_player (
    id SERIAL PRIMARY KEY,
    champ_id INT NOT NULL,
    player_id INT NOT NULL,
    meal_id INT
);
