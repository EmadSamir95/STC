

CREATE DATABASE "STC";


CREATE TABLE public.file (
    id bigint NOT NULL,
    bytes oid,
    item_id bigint
);


CREATE SEQUENCE public.file_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.file_id_seq OWNER TO postgres;


ALTER SEQUENCE public.file_id_seq OWNED BY public.file.id;



CREATE TABLE public.item (
    id bigint NOT NULL,
    name character varying(255),
    type character varying(255),
    permission_group_id bigint,
    CONSTRAINT item_type_check CHECK (((type)::text = ANY ((ARRAY['Space'::character varying, 'Folder'::character varying, 'File'::character varying])::text[])))
);


ALTER TABLE public.item OWNER TO postgres;


CREATE SEQUENCE public.item_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.item_id_seq OWNER TO postgres;


ALTER SEQUENCE public.item_id_seq OWNED BY public.item.id;


CREATE TABLE public.permission (
    id bigint NOT NULL,
    permission_level character varying(255),
    user_email character varying(255),
    group_id bigint,
    CONSTRAINT permission_permission_level_check CHECK (((permission_level)::text = ANY ((ARRAY['VIEW'::character varying, 'EDIT'::character varying])::text[])))
);


ALTER TABLE public.permission OWNER TO postgres;


CREATE TABLE public.permission_group (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE public.permission_group OWNER TO postgres;


CREATE SEQUENCE public.permission_group_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.permission_group_id_seq OWNER TO postgres;


ALTER SEQUENCE public.permission_group_id_seq OWNED BY public.permission_group.id;


CREATE SEQUENCE public.permission_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.permission_id_seq OWNER TO postgres;


ALTER SEQUENCE public.permission_id_seq OWNED BY public.permission.id;


CREATE TABLE public.users (
    id bigint NOT NULL,
    user_name character varying(255),
    is_enabled boolean,
    password character varying(255),
    permission_level bigint
);


ALTER TABLE public.users OWNER TO postgres;


CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_id_seq OWNER TO postgres;


ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


ALTER TABLE ONLY public.file ALTER COLUMN id SET DEFAULT nextval('public.file_id_seq'::regclass);


ALTER TABLE ONLY public.item ALTER COLUMN id SET DEFAULT nextval('public.item_id_seq'::regclass);


ALTER TABLE ONLY public.permission ALTER COLUMN id SET DEFAULT nextval('public.permission_id_seq'::regclass);


ALTER TABLE ONLY public.permission_group ALTER COLUMN id SET DEFAULT nextval('public.permission_group_id_seq'::regclass);


ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);



INSERT INTO public.permission (id, permission_level, user_email, group_id) VALUES (1, 'EDIT', 'emad@gmail.com', 1);
INSERT INTO public.permission (id, permission_level, user_email, group_id) VALUES (2, 'VIEW', 'shady@gmail.com', 1);


INSERT INTO public.permission_group (id, name) VALUES (1, 'admin');


INSERT INTO public.users (id, user_name, is_enabled, password, permission_level) VALUES (1, 'emad', true, '$2a$12$CgHl.kwJzslXZLU76z5s6emsLoG7OOtN6Tbpootg35R13L.yS/Hqm', 1);



SELECT pg_catalog.setval('public.file_id_seq', 1, false);



SELECT pg_catalog.setval('public.item_id_seq', 1, false);


SELECT pg_catalog.setval('public.permission_group_id_seq', 1, false);


SELECT pg_catalog.setval('public.permission_id_seq', 1, false);


SELECT pg_catalog.setval('public.users_id_seq', 1, false);

ALTER TABLE ONLY public.file
    ADD CONSTRAINT file_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.item
    ADD CONSTRAINT item_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.permission_group
    ADD CONSTRAINT permission_group_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.permission
    ADD CONSTRAINT permission_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.file
    ADD CONSTRAINT uk_10slfrt3wo3riqlc5olxv0rse UNIQUE (item_id);


ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.file
    ADD CONSTRAINT fk5gx9qw46gc6mr17noimfp2o4y FOREIGN KEY (item_id) REFERENCES public.item(id);



ALTER TABLE ONLY public.users
    ADD CONSTRAINT fkd2icf94tjgcco4uhtl1a2jldm FOREIGN KEY (permission_level) REFERENCES public.permission(id);



ALTER TABLE ONLY public.item
    ADD CONSTRAINT fkjtcinrue6wu7cvno03243s3jy FOREIGN KEY (permission_group_id) REFERENCES public.permission_group(id);



ALTER TABLE ONLY public.permission
    ADD CONSTRAINT fkqp7umovkuakff1jilk6dp9l1x FOREIGN KEY (group_id) REFERENCES public.permission_group(id);


