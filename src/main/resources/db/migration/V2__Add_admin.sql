insert into users (id, username, password)
values (1, 'admin', '$2y$08$txz2kl6Jmh59/wVJjwBmPu82sk1Gwt5eR331RQAKFzcGffp0cCFI6');

insert into user_role(user_id, roles)
values (1,'USER'), (1, 'ADMIN');