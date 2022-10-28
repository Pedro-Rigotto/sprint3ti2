CREATE SCHEMA IF NOT EXISTS mydb;

CREATE TABLE IF NOT EXISTS mydb."usuario" (
  "username" VARCHAR(16) NOT NULL UNIQUE,
  "nome" VARCHAR(255) NULL,
  "email" VARCHAR(255) NULL,
  "password" VARCHAR(32) NOT NULL,
  "tipo_usuario" INT NOT NULL DEFAULT 0,
  "telefone" CHAR(11) NULL,
  "id_hash" SERIAL NOT NULL,
  PRIMARY KEY ("id_hash"));

CREATE TABLE IF NOT EXISTS "mydb"."categoria" (
  "id_categoria" SERIAL NOT NULL,
  "nome" VARCHAR(255) NOT NULL,
  "supercategoria" VARCHAR(255) NOT NULL,
  PRIMARY KEY ("id_categoria"));

CREATE TABLE IF NOT EXISTS "mydb"."tutorial" (
  "id_tutorial" SERIAL NOT NULL,
  "texto" VARCHAR(20000) NOT NULL,
  "titulo" VARCHAR(45) NOT NULL,
  "autor" INT NOT NULL,
  "data_criacao" DATE NOT NULL,
  "publicado" INT NOT NULL DEFAULT 1,
  "link_youtube" VARCHAR(45) NOT NULL,
  "id_categoria" INT NULL,
  PRIMARY KEY ("id_tutorial"),
  CONSTRAINT "id_hash"
    FOREIGN KEY ("autor")
    REFERENCES "mydb"."usuario" ("id_hash")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT "id_categoria"
    FOREIGN KEY ("id_categoria")
    REFERENCES "mydb"."categoria" ("id_categoria")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS "mydb"."comentario" (
  "comentario_id" SERIAL NOT NULL,
  "descricao" VARCHAR(20000) NOT NULL,
  "data_publicacao" DATE NOT NULL,
  "publicado" INT NOT NULL DEFAULT 1,
  "autor" INT NOT NULL,
  "tutorial" INT NOT NULL,
  PRIMARY KEY ("comentario_id"),
  CONSTRAINT "id_hash"
    FOREIGN KEY ("autor")
    REFERENCES "mydb"."usuario" ("id_hash")
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT "id_tutorial"
    FOREIGN KEY ("tutorial")
    REFERENCES "mydb"."tutorial" ("id_tutorial")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
 