BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "admin" (
	"id"	INTEGER,
	"e_mail"	TEXT,
	"password"	TEXT,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "party" (
	"id"	INTEGER,
	"name_party"	TEXT,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "functions" (
	"id"	INTEGER,
	"function_name"	TEXT,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "voters" (
	"id"	INTEGER,
	"card_number"	TEXT NOT NULL UNIQUE,
	"jmbg"	INTEGER NOT NULL UNIQUE,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "candidats" (
	"id"	INTEGER,
	"party_id"	INTEGER,
	"name"	TEXT NOT NULL,
	"lastname"	TEXT NOT NULL,
	"birth_date"	DATE NOT NULL,
	"living_place"	TEXT NOT NULL,
	"function"	INTEGER NOT NULL,
	"vote_number"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("function") REFERENCES "functions"("id"),
	FOREIGN KEY("party_id") REFERENCES "party"("id")
);
INSERT INTO "admin" VALUES (1,'mpasic2@etf.unsa.ba','mpasic2');
INSERT INTO "admin" VALUES (2,'1','1');
INSERT INTO "party" VALUES (1,'SDA');
INSERT INTO "party" VALUES (2,'SNSD');
INSERT INTO "party" VALUES (3,'HDZ');
INSERT INTO "party" VALUES (4,'SBB');
INSERT INTO "party" VALUES (5,'SSK');
INSERT INTO "functions" VALUES (1,'Predsjednik');
INSERT INTO "functions" VALUES (2,'Potpredsjednik');
INSERT INTO "functions" VALUES (3,'Zamjenik');
INSERT INTO "candidats" VALUES (1,1,'Bakir','Izetbegovic',-426387600000,'Sarajevo',2,0);
INSERT INTO "candidats" VALUES (2,2,'Milorad','Dodik',-502160400000,'Laktasi',1,0);
INSERT INTO "candidats" VALUES (3,3,'Zeljko','Komsic',-154746000000,'Sarajevo',1,0);
INSERT INTO "candidats" VALUES (4,4,'Fahrudin','Radoncic',-470710800000,'Sarajevo',2,0);
INSERT INTO "candidats" VALUES (5,2,'Meho','Mehic',476060400000,'Bijeljina',3,0);
INSERT INTO "candidats" VALUES (6,1,'Fadil','Novalic',476060400000,'Zenica',3,0);
INSERT INTO "candidats" VALUES (7,3,'Jadranka','Kosor',-62989200000,'Mostar',3,0);
INSERT INTO "candidats" VALUES (8,4,'Huso','Husic',852246000000,'Sarajevo',2,0);
INSERT INTO "candidats" VALUES (9,1,'Sefik','Dzaferovic',126745200000,'Sarajevo',1,0);
INSERT INTO "candidats" VALUES (10,2,'Elvedin','Konakovic',-470710800000,'Sarajevo',2,0);
COMMIT;
