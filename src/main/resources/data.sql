INSERT INTO account(id, name, email, password, dob) VALUES(9999, 'Samer Elias', 'samere@gmail.com', 'Testing123@', '1995-07-21') ON CONFLICT DO NOTHING;
