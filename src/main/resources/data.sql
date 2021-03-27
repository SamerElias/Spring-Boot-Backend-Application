--INSERT INTO auth_user(user_id, username, password) VALUES(10, 'samergun', 'Testing123@') ON CONFLICT DO NOTHING;
--INSERT INTO account(account_id, name, email, date_of_birth, user_id) VALUES(9999, 'Samer Elias', 'samere@gmail.com', '1995-07-21', 10) ON CONFLICT DO NOTHING;
--INSERT INTO account(account_id, name, email, date_of_birth, user_id) VALUES(9911, 'ExistingAccount', 'existing@gmail.com', '1995-07-21', 10) ON CONFLICT DO NOTHING;
insert into cartItem(description, name, price, item_id) values('this is a real cool cartItem', 'real cool cartItem', 99999.9, 9191) ON CONFLICT DO NOTHING;
insert into item_images(item_id, img_url) values(9191, 'yooo where we goin?') ON CONFLICT DO NOTHING;
insert into item_images(item_id, img_url) values(9191, 'idk man where we going!!?') ON CONFLICT DO NOTHING;

insert into cartItem(description, name, price, item_id) values('this is.. an cartItem', 'ok it sucks', 19.1, 9192) ON CONFLICT DO NOTHING;
insert into item_images(item_id, img_url) values(9192, 'yyooo wheres my car?') ON CONFLICT DO NOTHING;
insert into item_images(item_id, img_url) values(9192, 'dudeeee wheres my car?????') ON CONFLICT DO NOTHING;
insert into item_images(item_id, img_url) values(9192, 'idk dude wheres my caaR???') ON CONFLICT DO NOTHING;
