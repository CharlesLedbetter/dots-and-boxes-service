-- to run this script place this on end of spring.datasource.url: ;INIT=runscript from './src/main/resources/init.sql'
CREATE TABLE IF NOT EXISTS Dots_Boxes_Game
(
    game_id INT AUTO_INCREMENT PRIMARY KEY,
    game_name VARCHAR(50) NOT NULL,
    width INT NOT NULL,
    height INT NOT NULL,
    owner VARCHAR(50) NOT NULL,
    winner VARCHAR(50) NULL,
    turn Int NOT NULL,
    number_of_players Int NOT NULL,
    UNIQUE (game_name, owner)
);

CREATE TABLE IF NOT EXISTS Line
(
    line_id INT AUTO_INCREMENT PRIMARY KEY,
    marked BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS Square
(
    square_id INT AUTO_INCREMENT PRIMARY KEY,
    game_id INT NOT NULL,
    FOREIGN KEY (game_id) REFERENCES Dots_Boxes_Game (game_id),
    square_x INT NOT NULL,
    square_y CHAR NOT NULL,
    UNIQUE (game_id, square_x, square_y),
    square_owner VARCHAR(50) NULL,
    up_line INT NOT NULL,
    right_line INT NOT NULL,
    down_line INT NOT NULL,
    left_line INT NOT NULL,
    FOREIGN KEY (up_line) REFERENCES Line (line_id),
    FOREIGN KEY (right_line) REFERENCES Line (line_id),
    FOREIGN KEY (down_line) REFERENCES Line (line_id),
    FOREIGN KEY (left_line) REFERENCES Line (line_id),
    UNIQUE (up_line, right_line, down_line, left_line)
);

CREATE TABLE IF NOT EXISTS Player
(
   player_id INT AUTO_INCREMENT PRIMARY KEY,
   game_id INT NOT NULL,
   FOREIGN KEY (game_id) REFERENCES Dots_Boxes_Game (game_id),
   player_order INT NOT NULL,
   player_name VARCHAR(50) NOT NULL,
   UNIQUE (game_id, player_name),
   UNIQUE (game_id, player_order)
)


