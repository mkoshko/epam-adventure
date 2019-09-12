USE `cyberwikia`;

CREATE TABLE country
(
    id        TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
    name      VARCHAR(50),
    code      CHAR(2),
    icon_file VARCHAR(4096),
    INDEX (id, name),
    CONSTRAINT PK_country PRIMARY KEY (id),
    CONSTRAINT UQ_country UNIQUE (id, name, code)
);

CREATE TABLE role
(
    id TINYINT(1) UNSIGNED,
    CONSTRAINT PK_role PRIMARY KEY (id)
);

CREATE TABLE user
(
    id       INT UNSIGNED        NOT NULL AUTO_INCREMENT,
    login    VARCHAR(100)        NOT NULL,
    email    VARCHAR(254)        NOT NULL,
    password CHAR(64)            NOT NULL,
    role     TINYINT(1) UNSIGNED NOT NULL,
    INDEX (id, login),
    CONSTRAINT PK_user PRIMARY KEY (id),
    CONSTRAINT UQ_user UNIQUE (login, email)
);

CREATE TABLE player
(
    id              INT UNSIGNED     NOT NULL AUTO_INCREMENT,
    nickname        VARCHAR(50)      NOT NULL,
    firstName       VARCHAR(30)      NOT NULL,
    lastName        VARCHAR(30)      NOT NULL,
    birth           DATE,
    nationality     TINYINT UNSIGNED NOT NULL,
    team            INT UNSIGNED,
    approx_winnings INT,
    CONSTRAINT PK_player PRIMARY KEY (id)
);

CREATE TABLE team
(
    id              INT UNSIGNED     NOT NULL AUTO_INCREMENT,
    name            VARCHAR(50)      NOT NULL,
    logo_file       VARCHAR(4096),
    location        TINYINT UNSIGNED NOT NULL,
    creator         INT UNSIGNED,
    captain         INT UNSIGNED,
    coach           INT UNSIGNED,
    approx_winnings INT,
    game            INT UNSIGNED     NOT NULL,
    overview        MEDIUMTEXT,
    CONSTRAINT PK_team PRIMARY KEY (id),
    CONSTRAINT UQ_team UNIQUE (id, name)
);

CREATE TABLE game
(
    id        INT UNSIGNED NOT NULL,
    title     VARCHAR(50)  NOT NULL,
    icon_file VARCHAR(4096),
    CONSTRAINT PK_games PRIMARY KEY (id),
    CONSTRAINT UQ_games UNIQUE (title)
);

CREATE TABLE m2m_player_team_invite
(
    id        INT UNSIGNED NOT NULL,
    player_id INT UNSIGNED NOT NULL,
    team_id   INT UNSIGNED NOT NULL,
    CONSTRAINT PK_m2m_player_team_invite PRIMARY KEY (id)
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
    CONSTRAINT PK_tournament PRIMARY KEY (id)
);

CREATE TABLE tournament_tier
(
    id   INT UNSIGNED AUTO_INCREMENT,
    name VARCHAR(30),
    CONSTRAINT PK_tournament_tier PRIMARY KEY (id)
);

CREATE TABLE m2m_tournament_team
(
    tournament_id INT UNSIGNED NOT NULL,
    team_id       INT UNSIGNED NOT NULL,
    INDEX (tournament_id, team_id),
    CONSTRAINT PK_tournament_team PRIMARY KEY (tournament_id, team_id)
);

CREATE TABLE m2m_user_team
(
    user_id    INT UNSIGNED NOT NULL,
    team_id    INT UNSIGNED NOT NULL,
    active     BOOL         NOT NULL,
    join_date  DATE         NOT NULL,
    leave_date DATE         NOT NULL,
    CONSTRAINT PK_user_team PRIMARY KEY (user_id, team_id)
);

CREATE TABLE m2m_user_player
(
    user_id   INT UNSIGNED NOT NULL,
    player_id INT UNSIGNED NOT NULL
);

ALTER TABLE user
    ADD CONSTRAINT FK_user_role FOREIGN KEY (role) REFERENCES role (id);

ALTER TABLE player
    ADD CONSTRAINT FK_player_nationality FOREIGN KEY (nationality) REFERENCES country (id),
    ADD CONSTRAINT FK_player_team FOREIGN KEY (team) REFERENCES team (id);

ALTER TABLE team
    ADD CONSTRAINT FK_team_creator FOREIGN KEY (creator) REFERENCES player (id),
    ADD CONSTRAINT FK_team_location FOREIGN KEY (location) REFERENCES country (id),
    ADD CONSTRAINT FK_team_captain FOREIGN KEY (coach) REFERENCES player (id),
    ADD CONSTRAINT FK_team_game FOREIGN KEY (game) REFERENCES game (id);

ALTER TABLE tournament
    ADD CONSTRAINT FK_tournament_tier FOREIGN KEY (tier) REFERENCES tournament_tier (id);

ALTER TABLE m2m_tournament_team
    ADD CONSTRAINT FK_m2m_tournament_team_tournament_id FOREIGN KEY (tournament_id) REFERENCES tournament (id),
    ADD CONSTRAINT FK_m2m_tournament_team_team_id FOREIGN KEY (team_id) REFERENCES team (id);

ALTER TABLE m2m_user_team
    ADD CONSTRAINT FK_user_team_user_id FOREIGN KEY (user_id) REFERENCES user (id),
    ADD CONSTRAINT FK_user_team_team_id FOREIGN KEY (team_id) REFERENCES team (id);

ALTER TABLE m2m_user_player
    ADD CONSTRAINT FK_m2m_user_player_user_id FOREIGN KEY (user_id) REFERENCES user (id),
    ADD CONSTRAINT FK_m2m_user_player_player_id FOREIGN KEY (player_id) REFERENCES player (id);

ALTER TABLE m2m_player_team_invite
    ADD CONSTRAINT FK_m2m_player_team_invite_player_id FOREIGN KEY (player_id) REFERENCES player (id),
    ADD CONSTRAINT FK_m2m_player_team_invite_team_id FOREIGN KEY (team_id) REFERENCES team (id);