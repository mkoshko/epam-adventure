SELECT user.username, team.name
FROM ((user_team
INNER JOIN team ON team.id = user_team.team_id)
INNER JOIN user ON user.id = user_team.user_id);