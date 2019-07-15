create table user(
    id    integer     not null auto_increment,
    nome  VARCHAR(50) not null,
    email varchar(80),
    login varchar(20),
    senha varchar(40),
    primary key (id)
);


use artemis;
describe user;