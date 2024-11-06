-- Séquences
CREATE SEQUENCE seq_bloc nomaxvalue increment by 1 start with 1;
CREATE SEQUENCE seq_transformation nomaxvalue increment by 1 start with 1;
CREATE SEQUENCE seq_usuel nomaxvalue increment by 1 start with 1;
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

-- --usuel
CREATE OR REPLACE FUNCTION seq_usuel_format(p_num NUMBER) RETURN VARCHAR2 IS
    v_result VARCHAR2(255);
BEGIN
    v_result := 'FORME' || LPAD(TO_CHAR(p_num), 4, '0');
    RETURN v_result;
END;

CREATE FUNCTION get_seq_usuel
    RETURN NUMBER
    IS
    retour NUMBER;
BEGIN
    SELECT seq_usuel.NEXTVAL INTO retour FROM DUAL;

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
    id           VARCHAR2(50),
    daty_entree  DATE          NOT NULL,
    daty_sortie  DATE,
    prix_revient NUMBER(15, 5) NOT NULL,
    longueur     NUMBER(15, 5) NOT NULL,
    largeur      NUMBER(15, 5) NOT NULL,
    hauteur      NUMBER(15, 5) NOT NULL,
    id_bloc_mere VARCHAR2(50),
    PRIMARY KEY (id),
    FOREIGN KEY (id_bloc_mere) REFERENCES bloc (id)
);

CREATE TABLE transformation
(
    id            VARCHAR2(50),
    daty          DATE         NOT NULL,
    id_bloc_reste VARCHAR2(50),
    id_bloc_mere  VARCHAR2(50) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_bloc_reste) REFERENCES bloc (id),
    FOREIGN KEY (id_bloc_mere) REFERENCES bloc (id)
);

CREATE TABLE usuel
(
    id         VARCHAR2(50),
    val        VARCHAR2(50)  NOT NULL,
    prix_vente NUMBER(15, 5),
    longueur   NUMBER(15, 5) NOT NULL,
    largeur    NUMBER(15, 5) NOT NULL,
    hauteur    NUMBER(15, 5) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (val)
);

CREATE TABLE teta
(
    id  VARCHAR2(50),
    val NUMBER(15, 5) NOT NULL,
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
    prix_revient_volumique NUMBER(15, 5) NOT NULL,
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
    prix_revient NUMBER(15, 5) NOT NULL,
    id_usuel     VARCHAR2(50)  NOT NULL,
    id_mvt_stock VARCHAR2(50)  NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_usuel) REFERENCES usuel (id),
    FOREIGN KEY (id_mvt_stock) REFERENCES mvt_stock (id)
);

-- Views
CREATE OR REPLACE VIEW usuel_lib AS
SELECT USUEL.*,
       LONGUEUR * LARGEUR * HAUTEUR                as volume,
       PRIX_VENTE / (LONGUEUR * LARGEUR * HAUTEUR) as rapport
FROM usuel;

-- CREATE OR REPLACE VIEW v_stock_usuel AS
-- select t.*,
--        u.PRIX_VENTE               as pu_vente,
--        u.PRIX_VENTE * t.qte_total as p_vente,
--        t.pu_revient * t.qte_total as p_revient
-- from USUEL u
--          join (select ID_USUEL,
--                       sum(ENTREE)       as qte_total,
--                       sum(PRIX_REVIENT) as pu_revient
--                from mvt_stock_detail
--                group by ID_USUEL) t
--               on u.ID = t.ID_USUEL;

CREATE OR REPLACE VIEW v_stock_usuel AS
select t.*,
       u.PRIX_VENTE                 as pu_vente,
       u.PRIX_VENTE * t.qte_total   as p_vente,
       avg_pu_revient * t.qte_total as p_revient
from USUEL u
         join (select ID_USUEL,
                      sum(ENTREE)                as qte_total,
                      avg(PRIX_REVIENT / ENTREE) as avg_pu_revient
               from mvt_stock_detail
               group by ID_USUEL) t
              on u.ID = t.ID_USUEL;

CREATE OR REPLACE VIEW maxRapport AS
select *
from (SELECT *
      FROM USUEL_LIB
      ORDER BY rapport DESC)
where ROWNUM <= 1;

CREATE OR REPLACE VIEW minVolume AS
select *
from (SELECT *
      FROM USUEL_LIB
      ORDER BY volume)
where ROWNUM <= 1;

CREATE OR REPLACE VIEW v_stock_bloc_optim AS
select b.id                                                                                    as id_bloc,
       maxRapport.id                                                                           as id_usuel,
       maxRapport.PRIX_VENTE                                                                   as pv_usuel,
       b.LONGUEUR * b.LARGEUR * b.HAUTEUR                                                      as vol_block,
       maxRapport.VOLUME                                                                       as vol_usuel,
       TRUNC((b.LONGUEUR * b.LARGEUR * b.HAUTEUR) / maxRapport.VOLUME)                         as qte_produit,
       b.PRIX_REVIENT                                                                          as pr_bloc,
       TRUNC((b.LONGUEUR * b.LARGEUR * b.HAUTEUR) / maxRapport.VOLUME) * maxRapport.PRIX_VENTE as pv_bloc
from bloc b
         cross join maxRapport
where DATY_SORTIE is null;

CREATE OR REPLACE VIEW v_stock_bloc_min AS
select b.id                                                                                  as id_bloc,
       minVolume.id                                                                          as id_usuel,
       minVolume.PRIX_VENTE                                                                  as pv_usuel,
       b.LONGUEUR * b.LARGEUR * b.HAUTEUR                                                    as vol_block,
       minVolume.VOLUME                                                                      as vol_usuel,
       TRUNC((b.LONGUEUR * b.LARGEUR * b.HAUTEUR) / minVolume.VOLUME)                        as qte_produit,
       b.PRIX_REVIENT                                                                        as pr_bloc,
       TRUNC((b.LONGUEUR * b.LARGEUR * b.HAUTEUR) / minVolume.VOLUME) * minVolume.PRIX_VENTE as pv_bloc
from bloc b
         cross join minVolume
where DATY_SORTIE is null;
