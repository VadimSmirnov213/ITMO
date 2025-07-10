CREATE TABLE IF NOT EXISTS GeneticMaterial (
    GeneticMaterialID SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL,
    size TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS Money (
    MoneyID SERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    date TIMESTAMP NOT NULL,
    amount INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS People (
    PeopleID SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    GeneticMaterialID INTEGER NOT NULL REFERENCES GeneticMaterial(GeneticMaterialID),
    role VARCHAR(50) NOT NULL,
    MoneyID INTEGER NOT NULL REFERENCES Money(MoneyID)
);

CREATE TABLE IF NOT EXISTS Location (
    LocationID SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    cool VARCHAR(50) NOT NULL,
    address TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS LocationOwner (
    LocationOwnerID SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    profession VARCHAR(100) NOT NULL,
    LocationID INTEGER NOT NULL REFERENCES Location(LocationID)
);


CREATE TABLE IF NOT EXISTS Place (
    PlaceID SERIAL PRIMARY KEY,
    LocationID INTEGER NOT NULL REFERENCES Location(LocationID),
    name VARCHAR(50) NOT NULL,
    shortname VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Visit (
    MeetingID SERIAL PRIMARY KEY,
    PeopleID INTEGER NOT NULL REFERENCES People(PeopleID),
    LocationID INTEGER NOT NULL REFERENCES Location(LocationID),
    time TIMESTAMP NOT NULL
);
