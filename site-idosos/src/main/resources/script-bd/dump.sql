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
    nome character varying(255) NOT NULL,
    supercategoria character varying(255) NOT NULL
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
-- Data for Name: categoria; Type: TABLE DATA; Schema: mydb; Owner: ti2cc
--

COPY mydb.categoria (id_categoria, nome, supercategoria) FROM stdin;
1	iPhone	Celular
2	Android	Celular
3	Itaú	Banco
4	Bradesco	Banco
\.


--
-- Data for Name: comentario; Type: TABLE DATA; Schema: mydb; Owner: ti2cc
--

COPY mydb.comentario (comentario_id, descricao, data_publicacao, publicado, autor, tutorial) FROM stdin;
3	Muito bom esse tutorial	2022-11-07	1	1	4
4	Que legal! Gostei muito...	2022-11-08	1	2	4
6	Comentario muito legal	2022-11-08	1	1	5
7	Esse tutorial é muito interessante	2022-11-08	1	1	6
1	Hahaha achei instrutivo	2022-11-07	1	2	3
\.


--
-- Data for Name: tutorial; Type: TABLE DATA; Schema: mydb; Owner: ti2cc
--

COPY mydb.tutorial (id_tutorial, texto, titulo, autor, data_criacao, publicado, link_youtube, id_categoria) FROM stdin;
3	Esse tutorial explica como tirar print usando o iPhone. Lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum.	Como tirar print com o iPhone	3	2022-10-28	1	https://www.youtube.com/embed/zjZgLcO330o	1
4	Tire dinheiro no caixa de maneira fácil e rápida	Como tirar dinheiro no caixa eletrônico	1	2022-11-07	1	https://www.youtube.com/embed/PTrwhQKKqUM	3
5	Veja como abrir conta no Bradesco diretamente no app. Preparamos um vídeo com todas as etapas necessárias para abrir sua conta corrente sem anuidade. Para a abertura de conta, é importante que seu aplicativo Bradesco esteja atualizado. 	Como usar o Bradesco	1	2022-11-08	1	https://youtube.com/embed/27KXiswZKHU	4
6	Aprenda como usar o Android sistema do Google. Aplicativos, Botões, App Telefone, Conexões, Voltar, Home, Recentes e fazer ligações.  O Android do Google revolucionou o mercado de Smartphone devido a qualidade e facilidade do sistema que conquistou milhares de adeptos proporcionando estabilidade e facilidade no uso dos aplicativos.   Sendo assim o Android tornou-se rapidamente o sistema mais usado na maioria dos celulares vendidos no mundo (Samsung, Asus, Motorola, LG, Xiaomi Sony dentre outros) por isso pessoas procuram tutorial, curso ou aula para aprender a usar os recursos do Android de forma correta.   Pensando nisso preparamos essa vídeo-aula para que qualquer pessoa seja capaz de usar os principais recursos do Smartphone	Como usar o Android	1	2022-11-08	1	https://www.youtube.com/embed/MfLT6WHQ0GQ	2
\.


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: mydb; Owner: ti2cc
--

COPY mydb.usuario (username, nome, email, password, tipo_usuario, telefone, id_hash) FROM stdin;
admin	admin	admin@admin.com	admin	1	1234       	1
777	777	777@7	777	0	777        	4
3	3	3@3	3	1	3          	5
33	33	33@33	33	0	33         	3
1	João da Silva	1@1	1	0	1          	2
\.


--
-- Name: categoria_id_categoria_seq; Type: SEQUENCE SET; Schema: mydb; Owner: ti2cc
--

SELECT pg_catalog.setval('mydb.categoria_id_categoria_seq', 4, true);


--
-- Name: comentario_comentario_id_seq; Type: SEQUENCE SET; Schema: mydb; Owner: ti2cc
--

SELECT pg_catalog.setval('mydb.comentario_comentario_id_seq', 7, true);


--
-- Name: tutorial_id_tutorial_seq; Type: SEQUENCE SET; Schema: mydb; Owner: ti2cc
--

SELECT pg_catalog.setval('mydb.tutorial_id_tutorial_seq', 6, true);


--
-- Name: usuario_id_hash_seq; Type: SEQUENCE SET; Schema: mydb; Owner: ti2cc
--

SELECT pg_catalog.setval('mydb.usuario_id_hash_seq', 5, true);


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
-- Name: usuario usuario_username_key; Type: CONSTRAINT; Schema: mydb; Owner: ti2cc
--

ALTER TABLE ONLY mydb.usuario
    ADD CONSTRAINT usuario_username_key UNIQUE (username);


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

