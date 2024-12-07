--
-- PostgreSQL database dump
--

-- Dumped from database version 17rc1
-- Dumped by pg_dump version 17.0

-- Started on 2024-12-07 11:38:31 CST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
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
-- TOC entry 220 (class 1259 OID 24584)
-- Name: customerinfo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customerinfo (
    firstname character(30) NOT NULL,
    lastname character varying(30),
    email character varying(30),
    phone character varying(10),
    firstoccupation character(3),
    preferredstand character varying(30),
    reservationtime timestamp without time zone
);


ALTER TABLE public.customerinfo OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16418)
-- Name: preferences; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.preferences (
    standposition character(5) NOT NULL,
    numberseats integer,
    numberpreferred integer,
    discountprice numeric(5,2),
    totalearnings numeric(5,2) GENERATED ALWAYS AS (((numberpreferred)::numeric * discountprice)) STORED
);


ALTER TABLE public.preferences OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16400)
-- Name: standinfo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.standinfo (
    positionid integer NOT NULL,
    standposition character varying(6),
    seatnumber character varying(6)
);


ALTER TABLE public.standinfo OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16399)
-- Name: standinfo_positionid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.standinfo_positionid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.standinfo_positionid_seq OWNER TO postgres;

--
-- TOC entry 3621 (class 0 OID 0)
-- Dependencies: 217
-- Name: standinfo_positionid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.standinfo_positionid_seq OWNED BY public.standinfo.positionid;


--
-- TOC entry 3458 (class 2604 OID 16403)
-- Name: standinfo positionid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.standinfo ALTER COLUMN positionid SET DEFAULT nextval('public.standinfo_positionid_seq'::regclass);


--
-- TOC entry 3615 (class 0 OID 24584)
-- Dependencies: 220
-- Data for Name: customerinfo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customerinfo (firstname, lastname, email, phone, firstoccupation, preferredstand, reservationtime) FROM stdin;
First2                        	Last2	this2@email.com	1231244123	EDU	West	2024-12-02 12:52:34.635226
First1                        	Last1	this1@email.com	1231234123	MLT	North	2024-12-02 12:52:34.650891
First4                        	Last4	this4@email.com	1231264123	EDU	West	2024-12-02 12:53:05.195141
First3                        	Last3	this3@email.com	1231254123	EDU	North	2024-11-20 17:39:16.520009
\.


--
-- TOC entry 3614 (class 0 OID 16418)
-- Dependencies: 219
-- Data for Name: preferences; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.preferences (standposition, numberseats, numberpreferred, discountprice) FROM stdin;
East 	3	2	10.00
West 	3	16	5.00
South	5	2	2.00
North	5	1	2.00
\.


--
-- TOC entry 3613 (class 0 OID 16400)
-- Dependencies: 218
-- Data for Name: standinfo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.standinfo (positionid, standposition, seatnumber) FROM stdin;
1	North	N601
2	North	N602
3	North	N603
4	North	N604
5	North	N605
6	South	S101
7	South	S102
8	South	S103
9	South	S104
10	North	S105
11	East	E001
12	East	E002
13	East	E003
14	West	W501
15	West	W502
16	West	W503
\.


--
-- TOC entry 3622 (class 0 OID 0)
-- Dependencies: 217
-- Name: standinfo_positionid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.standinfo_positionid_seq', 16, true);


--
-- TOC entry 3465 (class 2606 OID 24588)
-- Name: customerinfo first_name; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customerinfo
    ADD CONSTRAINT first_name PRIMARY KEY (firstname);


--
-- TOC entry 3463 (class 2606 OID 16423)
-- Name: preferences position_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.preferences
    ADD CONSTRAINT position_pk PRIMARY KEY (standposition);


--
-- TOC entry 3461 (class 2606 OID 16405)
-- Name: standinfo standinfo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.standinfo
    ADD CONSTRAINT standinfo_pkey PRIMARY KEY (positionid);


--
-- TOC entry 3466 (class 2606 OID 24589)
-- Name: customerinfo customerinfo_preferredstand_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customerinfo
    ADD CONSTRAINT customerinfo_preferredstand_fkey FOREIGN KEY (preferredstand) REFERENCES public.preferences(standposition);


-- Completed on 2024-12-07 11:38:31 CST

--
-- PostgreSQL database dump complete
--

