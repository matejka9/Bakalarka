-- Pouzivatel 
-- DROP TABLE user;

CREATE TABLE user (
	id INT NOT NULL AUTO_INCREMENT,
	krstne VARCHAR(255) NOT NULL,
	priezvisko VARCHAR(255) NOT NULL,
	mail VARCHAR(255) NOT NULL,
	adresa VARCHAR(255) NOT NULL,
	administrator INT(1) NOT NULL DEFAULT 0,
	PRIMARY KEY(id);
)

-- ALTER TABLE user DROP INDEX user_administrator;

CREATE INDEX user_administrator
    ON user (administrator);

-- Administrator
CREATE TABLE administrator (
    id INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(id)
);

-- DROP TABLE podujatie;

-- Podujatie
CREATE TABLE podujatie (
    id INT NOT NULL AUTO_INCREMENT,
    administrator_id INT NOT NULL,
    nazov VARCHAR(255) NOT NULL,
    typ ENUM('festival', 'hody') NOT NULL,
    datum_od DATE NOT NULL,
    datum_do DATE NOT NULL,
    FOREIGN KEY (administrator_id) REFERENCES administrator (id),
    PRIMARY KEY(id)
);

-- ALTER TABLE podujatie DROP INDEX podujatie_hladat;

CREATE INDEX podujatie_hladat
    ON podujatie (typ, datum_od, datum_do);

-- ALTER TABLE podujatie DROP INDEX administrator_podujatia;

CREATE INDEX administrator_podujatia
    ON podujatie (administrator_id);

-- Mapa

-- DROP TABLE mapa;

CREATE TABLE mapa (
	id INT NOT NULL AUTO_INCREMENT,
	podujatie_id INT NOT NULL,
	typ ENUM('hlavna', 'vedlajsia') NOT NULL,
	obraz blob NOT NULL,
	lavy_horny_roh_gps_latitude DOUBLE NOT NULL,
	lavy_horny_roh_gps_longitude DOUBLE NOT NULL,
	pravy_spodny_roh_gps_latitude DOUBLE NOT NULL,
	pravy_spodny_roh_gps_longitude DOUBLE NOT NULL,
	FOREIGN KEY (podujatie_id) REFERENCES podujatie (id),
    PRIMARY KEY(id)
);

-- ALTER TABLE mapa DROP INDEX mapa_typ_podujatia;

CREATE INDEX mapa_typ_podujatia
    ON mapa (typ, podujatie_id);

-- ALTER TABLE mapa DROP INDEX mapa_podujatia;

CREATE INDEX mapa_podujatia
    ON mapa (podujatie_id);

-- Zaner

CREATE TABLE zaner (
    id INT NOT NULL AUTO_INCREMENT,
    nazov VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

-- Zaner festivalu

CREATE TABLE zaner_podujatia (
    id INT NOT NULL AUTO_INCREMENT,
    podujatie_id INT NOT NULL,
    zaner_id INT NOT NULL,
    FOREIGN KEY (podujatie_id) REFERENCES podujatie (id),
    FOREIGN KEY (zaner_id) REFERENCES zaner (id),
    PRIMARY KEY(id)
);

-- ALTER TABLE zaner_podujatia DROP INDEX podujatie_zaner_hladat;

CREATE INDEX podujatie_zaner_hladat
    ON zaner_podujatia (podujatie_id);

-- ALTER TABLE zaner_podujatia DROP INDEX zaner_podujatie_hladat;

CREATE INDEX zaner_podujatie_hladat
    ON zaner_podujatia (zaner_id);


-- Podia podujatia

-- DROP TABLE podium;

CREATE TABLE podium (
	id INT NOT NULL AUTO_INCREMENT,
	mapa_id INT NOT NULL,
	nazov VARCHAR(255) NOT NULL,
	lavy_horny_roh_x DOUBLE NOT NULL,
	lavy_horny_roh_y DOUBLE NOT NULL,
	pravy_spodny_roh_x DOUBLE NOT NULL,
	pravy_spodny_roh_y DOUBLE NOT NULL,
	FOREIGN KEY (mapa_id) REFERENCES mapa (id),
    PRIMARY KEY(id)
);

-- ALTER TABLE podium DROP INDEX podia_mapy;

CREATE INDEX podia_mapy
    ON podium (mapa_id);

-- Stanky podujatia

-- DROP TABLE stanok;

CREATE TABLE stanok (
	id INT NOT NULL AUTO_INCREMENT,
	mapa_id INT NOT NULL,
	nazov VARCHAR(255) NOT NULL,
	lavy_horny_roh_x DOUBLE NOT NULL,
	lavy_horny_roh_y DOUBLE NOT NULL,
	pravy_spodny_roh_x DOUBLE NOT NULL,
	pravy_spodny_roh_y DOUBLE NOT NULL,
	FOREIGN KEY (mapa_id) REFERENCES mapa (id),
    PRIMARY KEY(id)
);

-- ALTER TABLE stanok DROP INDEX stanky_mapy;

CREATE INDEX stanky_mapy
    ON stanok (mapa_id);



-- Tovar

-- DROP TABLE tovar;

CREATE TABLE tovar (
	id INT NOT NULL AUTO_INCREMENT,
	nazov VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

-- DROP TABLE tovar_stanku;

CREATE TABLE tovar_stanku (
	id INT NOT NULL AUTO_INCREMENT,
	stanok_id INT NOT NULL,
	tovar_id INT NOT NULL,
	nazov VARCHAR(255) NOT NULL,
	cena FLOAT NOT NULL,
	FOREIGN KEY (stanok_id) REFERENCES stanok (id),
	FOREIGN KEY (tovar_id) REFERENCES tovar (id),
    PRIMARY KEY(id)
);

-- ALTER TABLE tovar_stanku DROP INDEX tovar_podujatia;

CREATE INDEX tovar_podujatia
    ON tovar_stanku (stanok_id, tovar_id);

-- ALTER TABLE tovar_stanku DROP INDEX tovar_stan;

CREATE INDEX tovar_stan
    ON tovar_stanku (stanok_id);

-- Vystupenia podia

-- DROP TABLE skupina;

CREATE TABLE skupina (
	id INT NOT NULL AUTO_INCREMENT,
	nazov VARCHAR(255) NOT NULL,
	foto blob,
    PRIMARY KEY(id)
);

-- DROP TABLE skupina_podia;

CREATE TABLE skupina_podia (
	id INT NOT NULL AUTO_INCREMENT,
	skupina_id INT NOT NULL,
	podium_id INT NOT NULL,
	detail VARCHAR(1000),
	cas_od TIMESTAMP NOT NULL,
	cas_do TIMESTAMP NOT NULL,
	FOREIGN KEY (skupina_id) REFERENCES skupina (id),
	FOREIGN KEY (podium_id) REFERENCES podium (id),
    PRIMARY KEY(id)
);

-- ALTER TABLE tovar_stanku DROP INDEX tovar_stan;

CREATE INDEX skupiny_podia
    ON skupina_podia (podium_id);



