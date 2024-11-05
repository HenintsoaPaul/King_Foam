-- Séquences
CREATE SEQUENCE seq_bloc nomaxvalue increment by 1 start with 1;
CREATE SEQUENCE seq_transformation nomaxvalue increment by 1 start with 1;
CREATE SEQUENCE seq_forme nomaxvalue increment by 1 start with 1;
CREATE SEQUENCE seq_teta nomaxvalue increment by 1 start with 1;
CREATE SEQUENCE seq_type_mvt_stock nomaxvalue increment by 1 start with 1;
CREATE SEQUENCE seq_mvt_stock nomaxvalue increment by 1 start with 1;
CREATE SEQUENCE seq_mvt_stock_detail nomaxvalue increment by 1 start with 1;

-- Str Sequences
-- --bloc
CREATE OR REPLACE FUNCTION seq_bloc_format(p_num NUMBER) RETURN VARCHAR2 IS
    v_result VARCHAR2(255);
BEGIN
    v_result := 'BLOC' || LPAD(TO_CHAR(p_num), 4, '0');
    RETURN v_result;
END;

CREATE FUNCTION get_seq_bloc
    RETURN NUMBER
    IS
    retour NUMBER;
BEGIN
    SELECT seq_bloc.NEXTVAL INTO retour FROM DUAL;

    RETURN retour;
END;

-- --transformation
CREATE OR REPLACE FUNCTION seq_transformation_format(p_num NUMBER) RETURN VARCHAR2 IS
    v_result VARCHAR2(255);
BEGIN
    v_result := 'TRANSF' || LPAD(TO_CHAR(p_num), 4, '0');
    RETURN v_result;
END;

CREATE FUNCTION get_seq_transformation
    RETURN NUMBER
    IS
    retour NUMBER;
BEGIN
    SELECT seq_transformation.NEXTVAL INTO retour FROM DUAL;

    RETURN retour;
END;

-- --forme
CREATE OR REPLACE FUNCTION seq_forme_format(p_num NUMBER) RETURN VARCHAR2 IS
    v_result VARCHAR2(255);
BEGIN
    v_result := 'FORME' || LPAD(TO_CHAR(p_num), 4, '0');
    RETURN v_result;
END;

CREATE FUNCTION get_seq_forme
    RETURN NUMBER
    IS
    retour NUMBER;
BEGIN
    SELECT seq_forme.NEXTVAL INTO retour FROM DUAL;

    RETURN retour;
END;

-- --teta
CREATE OR REPLACE FUNCTION seq_teta_format(p_num NUMBER) RETURN VARCHAR2 IS
    v_result VARCHAR2(255);
BEGIN
    v_result := 'TETA' || LPAD(TO_CHAR(p_num), 4, '0');
    RETURN v_result;
END;

CREATE FUNCTION get_seq_teta
    RETURN NUMBER
    IS
    retour NUMBER;
BEGIN
    SELECT seq_teta.NEXTVAL INTO retour FROM DUAL;

    RETURN retour;
END;

-- --type_mvt_stock
CREATE OR REPLACE FUNCTION seq_type_mvt_stock_format(p_num NUMBER) RETURN VARCHAR2 IS
    v_result VARCHAR2(255);
BEGIN
    v_result := 'TYPMVST' || LPAD(TO_CHAR(p_num), 4, '0');
    RETURN v_result;
END;

CREATE FUNCTION get_seq_type_mvt_stock
    RETURN NUMBER
    IS
    retour NUMBER;
BEGIN
    SELECT seq_type_mvt_stock.NEXTVAL INTO retour FROM DUAL;

    RETURN retour;
END;

-- --mvt_stock
CREATE OR REPLACE FUNCTION seq_mvt_stock_format(p_num NUMBER) RETURN VARCHAR2 IS
    v_result VARCHAR2(255);
BEGIN
    v_result := 'MVST' || LPAD(TO_CHAR(p_num), 4, '0');
    RETURN v_result;
END;

CREATE FUNCTION get_seq_mvt_stock
    RETURN NUMBER
    IS
    retour NUMBER;
BEGIN
    SELECT seq_mvt_stock.NEXTVAL INTO retour FROM DUAL;

    RETURN retour;
END;

-- --mvt_stock_detail
CREATE OR REPLACE FUNCTION seq_mvt_stock_detail_format(p_num NUMBER) RETURN VARCHAR2 IS
    v_result VARCHAR2(255);
BEGIN
    v_result := 'MVSTDET' || LPAD(TO_CHAR(p_num), 4, '0');
    RETURN v_result;
END;

CREATE FUNCTION get_seq_mvt_stock_detail
    RETURN NUMBER
    IS
    retour NUMBER;
BEGIN
    SELECT seq_mvt_stock_detail.NEXTVAL INTO retour FROM DUAL;

    RETURN retour;
END;

-- Tables
CREATE TABLE bloc
(
    id            VARCHAR2(50),
    daty_entree   DATE          NOT NULL,
    daty_sortie   DATE,
    prix_revient  NUMBER(15, 2) NOT NULL,
    longueur      NUMBER(15, 2) NOT NULL,
    largeur       NUMBER(15, 2) NOT NULL,
    hauteur       NUMBER(15, 2) NOT NULL,
    id_bloc_mere VARCHAR2(50),
    PRIMARY KEY (id),
    FOREIGN KEY (id_bloc_mere) REFERENCES bloc (id)
);

CREATE TABLE transformation
(
    id             VARCHAR2(50),
    id_bloc_reste VARCHAR2(50),
    id_bloc_mere  VARCHAR2(50) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_bloc_reste) REFERENCES bloc (id),
    FOREIGN KEY (id_bloc_mere) REFERENCES bloc (id)
);

CREATE TABLE forme
(
    id         VARCHAR2(50),
    val        VARCHAR2(50)  NOT NULL,
    prix_vente NUMBER(15, 2),
    longueur   NUMBER(15, 2) NOT NULL,
    largeur    NUMBER(15, 2) NOT NULL,
    hauteur    NUMBER(15, 2) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (val)
);

CREATE TABLE teta
(
    id  VARCHAR2(50),
    val NUMBER(15, 2) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE type_mvt_stock
(
    id    VARCHAR2(50),
    val   VARCHAR2(50) NOT NULL,
    desce VARCHAR2(50),
    PRIMARY KEY (id),
    UNIQUE (val)
);

CREATE TABLE mvt_stock
(
    id                     VARCHAR2(50),
    designation            VARCHAR2(50),
    daty                   DATE          NOT NULL,
    prix_revient_volumique NUMBER(15, 2) NOT NULL,
    id_origine             VARCHAR2(50),
    id_type_mvt_stock      VARCHAR2(50)  NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (id_origine),
    FOREIGN KEY (id_origine) REFERENCES transformation (id),
    FOREIGN KEY (id_type_mvt_stock) REFERENCES type_mvt_stock (id)
);

CREATE TABLE mvt_stock_detail
(
    id           VARCHAR2(50),
    entree       NUMBER(10),
    sortie       NUMBER(10),
    prix_revient NUMBER(15, 2) NOT NULL,
    id_forme     VARCHAR2(50)  NOT NULL,
    id_mvt_stock VARCHAR2(50)  NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_forme) REFERENCES forme (id),
    FOREIGN KEY (id_mvt_stock) REFERENCES mvt_stock (id)
);