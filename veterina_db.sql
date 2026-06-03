CREATE DATABASE veterinarska_ambulanta_db; 
USE veterinarska_ambulanta_db; 

CREATE TABLE vlasnik ( 
    id BIGINT AUTO_INCREMENT PRIMARY KEY, 
    ime VARCHAR(100) NOT NULL, 
    prezime VARCHAR(100) NOT NULL, 
    telefon VARCHAR(30), 
    email VARCHAR(150) 
); 

CREATE TABLE ljubimac ( 
    id BIGINT AUTO_INCREMENT PRIMARY KEY, 
    broj_mikrocipa VARCHAR(50) NOT NULL UNIQUE, 
    vrsta VARCHAR(50) NOT NULL, 
    ime VARCHAR(100) NOT NULL, 
    godina_rodenja INT, 
    vlasnik_id BIGINT NOT NULL, 
    CONSTRAINT fk_ljubimac_vlasnik 
        FOREIGN KEY (vlasnik_id) REFERENCES vlasnik(id) 
); 

CREATE TABLE veterinar ( 
    id BIGINT AUTO_INCREMENT PRIMARY KEY, 
    ime VARCHAR(100) NOT NULL, 
    prezime VARCHAR(100) NOT NULL, 
    specijalizacija VARCHAR(150) 
); 

CREATE TABLE usluga ( 
    id BIGINT AUTO_INCREMENT PRIMARY KEY, 
    naziv VARCHAR(150) NOT NULL, 
    opis TEXT, 
    cena int NOT NULL 
); 
ALTER TABLE usluga MODIFY COLUMN cena DECIMAL(10,2) NOT NULL;

CREATE TABLE pregled ( 
    id BIGINT AUTO_INCREMENT PRIMARY KEY, 
    ljubimac_id BIGINT NOT NULL, 
    veterinar_id BIGINT NOT NULL, 
    datum_prijema DATE NOT NULL, 
    datum_zavrsetka DATE, 
    opis_dijagnoze TEXT NOT NULL, 
    status VARCHAR(30) NOT NULL, 
    CONSTRAINT fk_pregled_ljubimac 
        FOREIGN KEY (ljubimac_id) REFERENCES ljubimac(id), 
    CONSTRAINT fk_pregled_veterinar 
        FOREIGN KEY (veterinar_id) REFERENCES veterinar(id) 
); 

CREATE TABLE pregled_usluga ( 
    pregled_id BIGINT NOT NULL, 
    usluga_id BIGINT NOT NULL, 
    PRIMARY KEY (pregled_id, usluga_id), 
    CONSTRAINT fk_pregled_usluga_pregled 
        FOREIGN KEY (pregled_id) REFERENCES pregled(id), 
    CONSTRAINT fk_pregled_usluga_usluga 
        FOREIGN KEY (usluga_id) REFERENCES usluga(id) 
);

INSERT INTO vlasnik (ime, prezime, telefon, email) VALUES 
('Radomir', 'Danic', '8694059385', 'radomir@gmail.com'), 
('Dragana', 'Panic', '1059375869', 'dragana@gmail.com'), 
('Ivana', 'Jovic', '1948576029', 'ivana@gmail.com'); 

INSERT INTO ljubimac (broj_mikrocipa, vrsta, ime, godina_rodenja, vlasnik_id) VALUES 
('CIP1', 'Pas', 'Pera', 2001, 1), 
('CIP2', 'Mačka', 'Mara', 2002, 2), 
('CIP3', 'Papagaj', 'Papa', 2003, 3); 

INSERT INTO veterinar (ime, prezime, specijalizacija) VALUES 
('Petar', 'Petrovic', 'Hirurg'), 
('Milan', 'Milanovic', 'Doktor'), 
('Djordje', 'Djordjevic', 'Stomatolog'); 

INSERT INTO usluga (naziv, opis, cena) VALUES 
('Opsti pregled', 'Osnovni pregled zivotinje', 2000.00),
('Vakcinacija', 'Vakcinacija protiv bolesti', 2500.00), 
('Ultrazvuk', 'Ultrazvucni pregled zivotnje', 3000.00), 
('Popravka zuba', 'Popravka zuba', 3500.00), 
('Hirurska operacija', 'Operacija', 10000.00); 

INSERT INTO pregled 
(ljubimac_id, veterinar_id, datum_prijema, datum_zavrsetka, opis_dijagnoze, status) VALUES 
(1, 1, '2026-06-03', NULL, 'Pas sepa na jednu nogu.', 'U_TOKU'), 
(2, 3, '2026-06-04', '2026-06-04', 'Ciscenje zuba.', 'ZAVRSENO'), 
(3, 2, '2026-06-05', NULL, 'Operacija njuske', 'U_TOKU'); 

INSERT INTO pregled_usluga (pregled_id, usluga_id) VALUES 
(1, 1), 
(1, 3), 
(2, 4), 
(3, 1),
(3, 2);




