SELECT * 
  FROM games,developers 
 WHERE games.developer_id = developers.id; 

SELECT * 
  FROM tags,games_tags,games 
 WHERE tags.id = games_tags.tag_id 
       AND games_tags.game_code = games.code; 

SELECT * 
  FROM consoles,companies 
 WHERE consoles.company_id = companies.id; 

SELECT * 
  FROM consoles,games_consoles,games 
 WHERE consoles.id = games_consoles.console_id 
       AND games_consoles.game_code = games.code; 

SELECT * 
  FROM users,users_consoles,consoles 
 WHERE users.id = users_consoles.user_id 
       AND users_consoles.console_id = consoles.id; 

SELECT * 
  FROM users,users_games,games 
 WHERE users.id = users_games.user_id 
       AND users_games.game_code = games.code; 
