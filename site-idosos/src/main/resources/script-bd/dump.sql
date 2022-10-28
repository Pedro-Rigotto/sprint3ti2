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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: usuario; Type: TABLE; Schema: mydb; Owner: ti2cc
--

CREATE TABLE mydb.usuario (
    username character varying(16) NOT NULL,
    nome character varying(255),
    email character varying(255),
    password character varying(32) NOT NULL,
    tipo_usuario integer DEFAULT 0 NOT NULL,
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
-- Name: usuario id_hash; Type: DEFAULT; Schema: mydb; Owner: ti2cc
--

ALTER TABLE ONLY mydb.usuario ALTER COLUMN id_hash SET DEFAULT nextval('mydb.usuario_id_hash_seq'::regclass);


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: mydb; Owner: ti2cc
--

COPY mydb.usuario (username, nome, email, password, tipo_usuario, telefone, id_hash) FROM stdin;
2	2	2@2	2	1	2          	1
1	1	1@1	1	0	1          	2
admin	admin	admin@admin.com	admin	1	1          	3
\.


--
-- Name: usuario_id_hash_seq; Type: SEQUENCE SET; Schema: mydb; Owner: ti2cc
--

SELECT pg_catalog.setval('mydb.usuario_id_hash_seq', 3, true);


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: mydb; Owner: ti2cc
--

ALTER TABLE ONLY mydb.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id_hash);


--
-- Name: usuario usuario_username_key; Type: CONSTRAINT; Schema: mydb; Owner: ti2cc
--

ALTER TABLE ONLY mydb.usuario
    ADD CONSTRAINT usuario_username_key UNIQUE (username);


--
-- PostgreSQL database dump complete
--

