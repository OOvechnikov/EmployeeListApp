insert into users (username, password, enabled) values ('user', '$2a$12$2Ki9bFgkSbGVip4qNL.rX.u/tWXvkIKE/435PcqX3DQH60Coim1be', true);  --123456
insert into users (username, password, enabled) values ('admin', '$2a$12$vlmKO9U5NgIDSO71XTVcnurzCyhVBB.oWk4B/8DifI.F0lqMYHb5S', true);  --qwerty

insert into authorities(username, authority) values ('user', 'ROLE_USER');
insert into authorities(username, authority) values ('admin', 'ROLE_ADMIN')
