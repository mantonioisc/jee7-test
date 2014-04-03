--Order in drops is important to avoid foreign key violations 
DROP TABLE users_consoles;
DROP TABLE games_consoles;
DROP TABLE users_games;
DROP TABLE games_tags;
DROP TABLE users;
DROP TABLE consoles;
DROP TABLE games;
DROP TABLE developers;
DROP TABLE companies;
DROP TABLE tags;

--Main tables creation 
CREATE TABLE developers 
  ( 
     id      SERIAL PRIMARY KEY, 
     name    VARCHAR(30), 
     country VARCHAR(20), 
     url     VARCHAR(100) 
  ); 

CREATE TABLE games 
  ( 
     code           VARCHAR(9) PRIMARY KEY, 
     title          VARCHAR(30) UNIQUE NOT NULL, 
     description    VARCHAR(300), 
     media          VARCHAR(6) NOT NULL CHECK(media IN ('CD', 'DVD', 'UMD', 'BluRay')),
     released_year  INTEGER NOT NULL, 
     price          INTEGER NOT NULL, 
     players_number INTEGER, 
     storage_space  VARCHAR(10), 
     rate           VARCHAR(1), 
     average_rating INTEGER, 
     developer_id   INTEGER NOT NULL REFERENCES developers(id) ON DELETE CASCADE 
  ); 

CREATE TABLE companies 
  ( 
     id        SERIAL PRIMARY KEY, 
     name      VARCHAR(30), 
     full_name VARCHAR(100), 
     country   VARCHAR(20), 
     url       VARCHAR(100) 
  ); 

CREATE TABLE consoles 
  ( 
     id          SERIAL PRIMARY KEY, 
     name        VARCHAR(10) NOT NULL, 
     full_name   VARCHAR(50), 
     description VARCHAR(300), 
     price       NUMERIC(6, 2) NOT NULL, 
     max_players INTEGER NOT NULL, 
     wireless    BOOLEAN DEFAULT true NOT NULL, 
     network     BOOLEAN DEFAULT true NOT NULL, 
     hd          BOOLEAN DEFAULT true NOT NULL, 
     media       VARCHAR(6) NOT NULL CHECK(media IN ('CD', 'DVD', 'UMD', 'BluRay')),
     company_id  INTEGER NOT NULL REFERENCES companies(id) ON DELETE CASCADE 
  ); 

CREATE TABLE users 
  ( 
     id         SERIAL PRIMARY KEY, 
     name       VARCHAR(10) NOT NULL, 
     last_name  VARCHAR(10) NOT NULL, 
     birth_date TIMESTAMP 
  ); 

CREATE TABLE tags 
  ( 
     id   SERIAL PRIMARY KEY, 
     name VARCHAR(10) UNIQUE NOT NULL 
  ); 

--Join tables creation 
CREATE TABLE users_consoles 
  ( 
     user_id            INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE, 
     console_id         INTEGER NOT NULL REFERENCES consoles(id) ON DELETE CASCADE,
     acquired_date      TIMESTAMP, 
     acquired_condition VARCHAR(4) DEFAULT 'NEW' NOT NULL CHECK (acquired_condition IN ('NEW', 'USED')),
     PRIMARY KEY (user_id, console_id) 
  ); 

CREATE TABLE users_games 
  ( 
     user_id            INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE, 
     game_code          VARCHAR(9) NOT NULL REFERENCES games(code) ON DELETE CASCADE,
     acquired_date      TIMESTAMP, 
     acquired_condition VARCHAR(4) DEFAULT 'NEW' NOT NULL CHECK (acquired_condition IN ('NEW', 'USED')),
     current_condition  VARCHAR(8) DEFAULT 'OPENED' NOT NULL CHECK (current_condition IN ('OPENED', 'BRANDNEW')),
     greatest_hits      BOOLEAN DEFAULT false NOT NULL, 
     PRIMARY KEY (user_id, game_code) 
  ); 

CREATE TABLE games_consoles 
  ( 
     game_code  VARCHAR(9) NOT NULL REFERENCES games(code) ON DELETE CASCADE, 
     console_id INTEGER NOT NULL REFERENCES consoles(id) ON DELETE CASCADE, 
     PRIMARY KEY(game_code, console_id) 
  ); 

CREATE TABLE games_tags 
  ( 
     game_code   VARCHAR(9) NOT NULL REFERENCES games(code) ON DELETE CASCADE, 
     tag_id      INTEGER NOT NULL REFERENCES tags(id) ON DELETE CASCADE, 
     date_tagged TIMESTAMP DEFAULT current_timestamp NOT NULL, 
     PRIMARY KEY (game_code, tag_id) 
  ); 
