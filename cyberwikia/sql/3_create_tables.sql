USE `cyberwikia`;

CREATE TABLE country
(
    id        TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
    name      VARCHAR(50),
    code      CHAR(2),
    icon_file VARCHAR(4096),
    CONSTRAINT PK_country PRIMARY KEY (id),
    CONSTRAINT UQ_country UNIQUE (id, name, code)
);
CREATE INDEX IDX_county_id_name ON country (name);

CREATE TABLE tournament_tier
(
    id   INT UNSIGNED AUTO_INCREMENT,
    name VARCHAR(30),
    CONSTRAINT PK_tournament_tier PRIMARY KEY (id)
);

CREATE TABLE game
(
    id        INT UNSIGNED NOT NULL,
    title     VARCHAR(50)  NOT NULL,
    icon_file VARCHAR(4096),
    CONSTRAINT PK_games PRIMARY KEY (id),
    CONSTRAINT UQ_games UNIQUE (title)
);

CREATE TABLE user
(
    id       INT UNSIGNED        NOT NULL AUTO_INCREMENT,
    login    VARCHAR(100)        NOT NULL,
    email    VARCHAR(254)        NOT NULL,
    #Argon2 hashing algorithm.
    password CHAR(99)            NOT NULL,
    role     TINYINT(1) UNSIGNED NOT NULL,
    INDEX (login),
    CONSTRAINT PK_user PRIMARY KEY (id),
    CONSTRAINT UQ_user UNIQUE (login, email)
);

CREATE TABLE player
(
    id         INT UNSIGNED     NOT NULL AUTO_INCREMENT,
    nickname   VARCHAR(50)      NOT NULL,
    firstName  VARCHAR(30)      NOT NULL,
    lastName   VARCHAR(30)      NOT NULL,
    birth      DATE,
    country_id TINYINT UNSIGNED NOT NULL,
    INDEX (nickname),
    CONSTRAINT FK_player_id FOREIGN KEY (id) REFERENCES user (id),
    CONSTRAINT FK_player_country_id FOREIGN KEY (country_id) REFERENCES country (id),
    CONSTRAINT PK_player PRIMARY KEY (id)
);

CREATE TABLE team
(
    id         INT UNSIGNED     NOT NULL AUTO_INCREMENT,
    name       VARCHAR(50)      NOT NULL,
    logo_file  VARCHAR(4096),
    country_id TINYINT UNSIGNED NOT NULL,
    creator    INT UNSIGNED     NOT NULL,
    captain    INT UNSIGNED,
    coach      INT UNSIGNED,
    game       INT UNSIGNED     NOT NULL,
    overview   MEDIUMTEXT,
    INDEX (name),
    CONSTRAINT PK_team PRIMARY KEY (id),
    CONSTRAINT UQ_team UNIQUE (id, name),
    CONSTRAINT FK_team_creator FOREIGN KEY (creator) REFERENCES player (id),
    CONSTRAINT FK_team_location FOREIGN KEY (country_id) REFERENCES country (id),
    CONSTRAINT FK_team_captain FOREIGN KEY (coach) REFERENCES player (id),
    CONSTRAINT FK_team_game FOREIGN KEY (game) REFERENCES game (id)
);

CREATE TABLE tournament
(
    id         INT UNSIGNED AUTO_INCREMENT,
    name       VARCHAR(100),
    tier       INT UNSIGNED NOT NULL,
    prize      INT UNSIGNED,
    overview   MEDIUMTEXT,
    start_date DATE         NOT NULL,
    end_date   DATE         NOT NULL,
    CONSTRAINT PK_tournament PRIMARY KEY (id),
    CONSTRAINT FK_tournament_tier FOREIGN KEY (tier) REFERENCES tournament_tier (id),
    CONSTRAINT CH_tournament_start_end_date CHECK ( DATEDIFF(end_date, start_date) >= 0 )
);
CREATE INDEX IDX_tournament_name ON tournament (name);

CREATE TABLE m2m_tournament_team
(
    tournament_id INT UNSIGNED NOT NULL,
    team_id       INT UNSIGNED NOT NULL,
    placement     TINYINT UNSIGNED,
    CONSTRAINT PK_tournament_team PRIMARY KEY (tournament_id, team_id),
    CONSTRAINT FK_m2m_tournament_team_tournament_id FOREIGN KEY (tournament_id) REFERENCES tournament (id),
    CONSTRAINT FK_m2m_tournament_team_team_id FOREIGN KEY (team_id) REFERENCES team (id)
);
CREATE INDEX IDX_tournament_team_team_id ON m2m_tournament_team (team_id);

CREATE TABLE m2m_player_team
(
    player_id  INT UNSIGNED NOT NULL,
    team_id    INT UNSIGNED NOT NULL,
    active     BOOL         NOT NULL,
    join_date  DATE         NOT NULL,
    leave_date DATE,
    CONSTRAINT PK_player_team PRIMARY KEY (player_id, team_id),
    CONSTRAINT FK_user_team_player_id FOREIGN KEY (player_id) REFERENCES player (id),
    CONSTRAINT FK_user_team_team_id FOREIGN KEY (team_id) REFERENCES team (id),
    CONSTRAINT CH_player_team_join_leave_date CHECK ( DATEDIFF(leave_date, join_date) >= 0 )
);
CREATE INDEX IDX_player_team_team_id ON m2m_player_team (team_id);