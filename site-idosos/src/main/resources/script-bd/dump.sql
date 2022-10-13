--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5 (Ubuntu 14.5-0ubuntu0.22.04.1)
-- Dumped by pg_dump version 14.5 (Ubuntu 14.5-0ubuntu0.22.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: mydb; Type: SCHEMA; Schema: -; Owner: ti2cc
--

CREATE SCHEMA mydb;


ALTER SCHEMA mydb OWNER TO ti2cc;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: categoria; Type: TABLE; Schema: mydb; Owner: ti2cc
--

CREATE TABLE mydb.categoria (
    id_categoria integer NOT NULL,
    nome character varying(255) NOT NULL
);


ALTER TABLE mydb.categoria OWNER TO ti2cc;

--
-- Name: categoria_id_categoria_seq; Type: SEQUENCE; Schema: mydb; Owner: ti2cc
--

CREATE SEQUENCE mydb.categoria_id_categoria_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mydb.categoria_id_categoria_seq OWNER TO ti2cc;

--
-- Name: categoria_id_categoria_seq; Type: SEQUENCE OWNED BY; Schema: mydb; Owner: ti2cc
--

ALTER SEQUENCE mydb.categoria_id_categoria_seq OWNED BY mydb.categoria.id_categoria;


--
-- Name: comentario; Type: TABLE; Schema: mydb; Owner: ti2cc
--

CREATE TABLE mydb.comentario (
    comentario_id integer NOT NULL,
    descricao character varying(20000) NOT NULL,
    data_publicacao date NOT NULL,
    publicado integer DEFAULT 1 NOT NULL,
    autor integer NOT NULL,
    tutorial integer NOT NULL
);


ALTER TABLE mydb.comentario OWNER TO ti2cc;

--
-- Name: comentario_comentario_id_seq; Type: SEQUENCE; Schema: mydb; Owner: ti2cc
--

CREATE SEQUENCE mydb.comentario_comentario_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mydb.comentario_comentario_id_seq OWNER TO ti2cc;

--
-- Name: comentario_comentario_id_seq; Type: SEQUENCE OWNED BY; Schema: mydb; Owner: ti2cc
--

ALTER SEQUENCE mydb.comentario_comentario_id_seq OWNED BY mydb.comentario.comentario_id;


--
-- Name: tutorial; Type: TABLE; Schema: mydb; Owner: ti2cc
--

CREATE TABLE mydb.tutorial (
    id_tutorial integer NOT NULL,
    texto character varying(20000) NOT NULL,
    titulo character varying(45) NOT NULL,
    autor integer NOT NULL,
    data_criacao date NOT NULL,
    publicado integer DEFAULT 1 NOT NULL,
    link_youtube character varying(45) NOT NULL,
    id_categoria integer
);


ALTER TABLE mydb.tutorial OWNER TO ti2cc;

--
-- Name: tutorial_id_tutorial_seq; Type: SEQUENCE; Schema: mydb; Owner: ti2cc
--

CREATE SEQUENCE mydb.tutorial_id_tutorial_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mydb.tutorial_id_tutorial_seq OWNER TO ti2cc;

--
-- Name: tutorial_id_tutorial_seq; Type: SEQUENCE OWNED BY; Schema: mydb; Owner: ti2cc
--

ALTER SEQUENCE mydb.tutorial_id_tutorial_seq OWNED BY mydb.tutorial.id_tutorial;


--
-- Name: usuario; Type: TABLE; Schema: mydb; Owner: ti2cc
--

CREATE TABLE mydb.usuario (
    username character varying(16) NOT NULL,
    nome character varying(255),
    email character varying(255),
    password character varying(32) NOT NULL,
    tipo_usuario integer NOT NULL DEFAULT 0,
    telefone character(11),
    id_hash integer NOT NULL
);


ALTER TABLE mydb.usuario OWNER TO ti2cc;

--
-- Name: usuario_id_hash_seq; Type: SEQUENCE; Schema: mydb; Owner: ti2cc
--

CREATE SEQUENCE mydb.usuario_id_hash_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mydb.usuario_id_hash_seq OWNER TO ti2cc;

--
-- Name: usuario_id_hash_seq; Type: SEQUENCE OWNED BY; Schema: mydb; Owner: ti2cc
--

ALTER SEQUENCE mydb.usuario_id_hash_seq OWNED BY mydb.usuario.id_hash;


--
-- Name: categoria id_categoria; Type: DEFAULT; Schema: mydb; Owner: ti2cc
--

ALTER TABLE ONLY mydb.categoria ALTER COLUMN id_categoria SET DEFAULT nextval('mydb.categoria_id_categoria_seq'::regclass);


--
-- Name: comentario comentario_id; Type: DEFAULT; Schema: mydb; Owner: ti2cc
--

ALTER TABLE ONLY mydb.comentario ALTER COLUMN comentario_id SET DEFAULT nextval('mydb.comentario_comentario_id_seq'::regclass);


--
-- Name: tutorial id_tutorial; Type: DEFAULT; Schema: mydb; Owner: ti2cc
--

ALTER TABLE ONLY mydb.tutorial ALTER COLUMN id_tutorial SET DEFAULT nextval('mydb.tutorial_id_tutorial_seq'::regclass);


--
-- Name: usuario id_hash; Type: DEFAULT; Schema: mydb; Owner: ti2cc
--

ALTER TABLE ONLY mydb.usuario ALTER COLUMN id_hash SET DEFAULT nextval('mydb.usuario_id_hash_seq'::regclass);


--
-- Name: categoria categoria_pkey; Type: CONSTRAINT; Schema: mydb; Owner: ti2cc
--

ALTER TABLE ONLY mydb.categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id_categoria);


--
-- Name: comentario comentario_pkey; Type: CONSTRAINT; Schema: mydb; Owner: ti2cc
--

ALTER TABLE ONLY mydb.comentario
    ADD CONSTRAINT comentario_pkey PRIMARY KEY (comentario_id);


--
-- Name: tutorial tutorial_pkey; Type: CONSTRAINT; Schema: mydb; Owner: ti2cc
--

ALTER TABLE ONLY mydb.tutorial
    ADD CONSTRAINT tutorial_pkey PRIMARY KEY (id_tutorial);


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: mydb; Owner: ti2cc
--

ALTER TABLE ONLY mydb.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id_hash);


--
-- Name: tutorial id_categoria; Type: FK CONSTRAINT; Schema: mydb; Owner: ti2cc
--

ALTER TABLE ONLY mydb.tutorial
    ADD CONSTRAINT id_categoria FOREIGN KEY (id_categoria) REFERENCES mydb.categoria(id_categoria);


--
-- Name: tutorial id_hash; Type: FK CONSTRAINT; Schema: mydb; Owner: ti2cc
--

ALTER TABLE ONLY mydb.tutorial
    ADD CONSTRAINT id_hash FOREIGN KEY (autor) REFERENCES mydb.usuario(id_hash);


--
-- Name: comentario id_hash; Type: FK CONSTRAINT; Schema: mydb; Owner: ti2cc
--

ALTER TABLE ONLY mydb.comentario
    ADD CONSTRAINT id_hash FOREIGN KEY (autor) REFERENCES mydb.usuario(id_hash) ON UPDATE CASCADE;


--
-- Name: comentario id_tutorial; Type: FK CONSTRAINT; Schema: mydb; Owner: ti2cc
--

ALTER TABLE ONLY mydb.comentario
    ADD CONSTRAINT id_tutorial FOREIGN KEY (tutorial) REFERENCES mydb.tutorial(id_tutorial);


--
-- PostgreSQL database dump complete
--

