INSERT INTO account(id, name, username, email, password, dateofbirth) VALUES(9999, 'Samer Elias', 'samergun', 'samere@gmail.com', 'Testing123@', '1995-07-21') ON CONFLICT DO NOTHING;
