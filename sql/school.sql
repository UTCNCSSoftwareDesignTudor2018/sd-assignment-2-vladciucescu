-- PostgreSQL database dump

-- Dumped from database version 10.3
-- Dumped by pg_dump version 10.3

-- Started on 2018-04-12 23:11:25

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 16394)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

ALTER SCHEMA public OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 203 (class 1259 OID 16428)
-- Name: courses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.courses (
    id integer NOT NULL,
    name character varying NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    exam_date date NOT NULL,
    teacher_id integer NOT NULL
);


ALTER TABLE public.courses OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16426)
-- Name: courses_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.courses_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.courses_id_seq OWNER TO postgres;

--
-- TOC entry 2848 (class 0 OID 0)
-- Dependencies: 202
-- Name: courses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.courses_id_seq OWNED BY public.courses.id;


--
-- TOC entry 205 (class 1259 OID 16439)
-- Name: enrollments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.enrollments (
    id integer NOT NULL,
    student_id integer NOT NULL,
    course_id integer NOT NULL,
    grade double precision
);


ALTER TABLE public.enrollments OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16437)
-- Name: enrollments_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.enrollments_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.enrollments_id_seq OWNER TO postgres;

--
-- TOC entry 2849 (class 0 OID 0)
-- Dependencies: 204
-- Name: enrollments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.enrollments_id_seq OWNED BY public.enrollments.id;


--
-- TOC entry 201 (class 1259 OID 16411)
-- Name: students; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.students (
    id integer NOT NULL,
    year smallint NOT NULL,
    "groupp" integer NOT NULL
);


ALTER TABLE public.students OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16406)
-- Name: teachers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.teachers (
    id integer NOT NULL
);


ALTER TABLE public.teachers OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 16397)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    surname character varying NOT NULL,
    name character varying NOT NULL,
    id_card_number integer NOT NULL,
    id integer NOT NULL,
    address character varying,
    email character varying
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 16395)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 2850 (class 0 OID 0)
-- Dependencies: 198
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 2695 (class 2604 OID 16431)
-- Name: courses id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.courses ALTER COLUMN id SET DEFAULT nextval('public.courses_id_seq'::regclass);


--
-- TOC entry 2696 (class 2604 OID 16442)
-- Name: enrollments id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.enrollments ALTER COLUMN id SET DEFAULT nextval('public.enrollments_id_seq'::regclass);


--
-- TOC entry 2694 (class 2604 OID 16400)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 2840 (class 0 OID 16428)
-- Dependencies: 203
-- Data for Name: courses; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.courses (id, name, start_date, end_date, exam_date, teacher_id) FROM stdin;
1	Electrotechnics	2018-10-10	2019-02-02	2019-03-03	1
4	Image Processing	2018-11-11	2019-03-03	2020-04-04	1
5	Software Design	2018-09-14	2020-05-05	2020-05-06	3
6	Programming Techniques	2017-11-14	2018-06-06	2018-10-09	7
7	Logic Design	2019-03-15	2019-10-10	2019-10-14	7
8	Physics	2020-01-29	2020-08-01	2020-10-02	7
\.


--
-- TOC entry 2842 (class 0 OID 16439)
-- Dependencies: 205
-- Data for Name: enrollments; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.enrollments (id, student_id, course_id, grade) FROM stdin;
2	2	4	\N
3	4	5	1
4	5	6	5.7000000000000002
5	5	7	\N
6	5	8	7.2999999999999998
7	6	1	\N
8	6	4	10
9	2	5	\N
10	4	6	3.3999999999999999
11	6	7	\N
1	2	1	9.5
\.


--
-- TOC entry 2838 (class 0 OID 16411)
-- Dependencies: 201
-- Data for Name: students; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.students (id, year, "groupp") FROM stdin;
2	2	30323
4	1	42313
5	3	21332
6	4	65242
\.


--
-- TOC entry 2837 (class 0 OID 16406)
-- Dependencies: 200
-- Data for Name: teachers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.teachers (id) FROM stdin;
1
3
7
\.


--
-- TOC entry 2836 (class 0 OID 16397)
-- Dependencies: 199
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (surname, name, id_card_number, id, address, email) FROM stdin;
Pop	Ana	2314	1	strada Mica	pop.ana@gmail.com
Leustean	Minodora	1232	2	strada Mare	mino@yahoo.com
Nistrator	Admi	9785	3	strada Lunga	admin@outlook.com
Chifor	Nechifor	5644	4	strada Scurta	kifi@gmail.com
Morcov	Marius	4534	5	strada Joasa	morcovel@yahoo.com
Piersica	Catrinel	4343	6	strada Curbata	caisa@gmail.com
Doamna	Profesoara	2131	7	strada Dreapta	profa@utcn.ro
\.


--
-- TOC entry 2851 (class 0 OID 0)
-- Dependencies: 202
-- Name: courses_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.courses_id_seq', 8, true);


--
-- TOC entry 2852 (class 0 OID 0)
-- Dependencies: 204
-- Name: enrollments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.enrollments_id_seq', 11, true);


--
-- TOC entry 2853 (class 0 OID 0)
-- Dependencies: 198
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 7, true);


--
-- TOC entry 2702 (class 2606 OID 16410)
-- Name: teachers admins_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teachers
    ADD CONSTRAINT admins_pkey PRIMARY KEY (id);


--
-- TOC entry 2706 (class 2606 OID 16436)
-- Name: courses courses_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.courses
    ADD CONSTRAINT courses_pkey PRIMARY KEY (id);


--
-- TOC entry 2708 (class 2606 OID 16444)
-- Name: enrollments enrollments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.enrollments
    ADD CONSTRAINT enrollments_pkey PRIMARY KEY (id);


--
-- TOC entry 2704 (class 2606 OID 16415)
-- Name: students students_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.students
    ADD CONSTRAINT students_pkey PRIMARY KEY (id);


--
-- TOC entry 2698 (class 2606 OID 16497)
-- Name: users users_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_id_key UNIQUE (id);


--
-- TOC entry 2700 (class 2606 OID 16405)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2713 (class 2606 OID 16455)
-- Name: enrollments course_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.enrollments
    ADD CONSTRAINT course_id FOREIGN KEY (course_id) REFERENCES public.courses(id);


--
-- TOC entry 2712 (class 2606 OID 16450)
-- Name: enrollments student_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.enrollments
    ADD CONSTRAINT student_id FOREIGN KEY (student_id) REFERENCES public.students(id);


--
-- TOC entry 2711 (class 2606 OID 16445)
-- Name: courses teacher_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.courses
    ADD CONSTRAINT teacher_id FOREIGN KEY (teacher_id) REFERENCES public.teachers(id);


--
-- TOC entry 2710 (class 2606 OID 16416)
-- Name: students user_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.students
    ADD CONSTRAINT user_id FOREIGN KEY (id) REFERENCES public.users(id);


--
-- TOC entry 2709 (class 2606 OID 16421)
-- Name: teachers user_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teachers
    ADD CONSTRAINT user_id FOREIGN KEY (id) REFERENCES public.users(id);


-- Completed on 2018-04-12 23:11:26

--
-- PostgreSQL database dump complete
--


