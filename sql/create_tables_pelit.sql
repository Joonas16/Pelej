CREATE TABLE pelit(
id int auto_increment not null,
nimi varchar(20) not null ,
kuvaus varchar(40)not null,
kehittaja varchar(40)not null,
julkaisija varchar(40)not null,
julkaisuvuosi int(4)not null,
arvostelu double(4, 2)not null,
hinta double(20, 2)not null,
PRIMARY KEY(id)
)engine=InnoDB;