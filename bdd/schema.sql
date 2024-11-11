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
    id_bloc_base VARCHAR2(50),
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
    id_usuel     VARCHAR2(50) NOT NULL,
    id_mvt_stock VARCHAR2(50) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_usuel) REFERENCES usuel (id),
    FOREIGN KEY (id_mvt_stock) REFERENCES mvt_stock (id)
);

-- Views
CREATE OR REPLACE VIEW bloc_lib AS
select b.*,
       b.LONGUEUR * b.LARGEUR * b.HAUTEUR as vol_bloc
from bloc b;

CREATE OR REPLACE VIEW usuel_lib AS
SELECT USUEL.*,
       LONGUEUR * LARGEUR * HAUTEUR                as volume,
       PRIX_VENTE / (LONGUEUR * LARGEUR * HAUTEUR) as rapport
FROM usuel;

CREATE OR REPLACE VIEW mvt_stock_detail_lib AS
select mv_fille.id,
       mv_fille.id_mvt_stock,
       mv_fille.id_usuel,
       u.val                                                       as val_usuel,
       mv_fille.entree,
       mv_fille.sortie,
       u.volume                                                    as vol_usuel,
       mv_mere.prix_revient_volumique                              as pr_vol,
       mv_mere.prix_revient_volumique * mv_fille.entree * u.volume as pr_total,
       u.PRIX_VENTE                                                as pv_usuel,
       u.prix_vente * mv_fille.entree                              as pv_total
from MVT_STOCK_DETAIL mv_fille
         join USUEL_LIB u on mv_fille.ID_USUEL = u.id
         join mvt_stock mv_mere on mv_fille.id_mvt_stock = mv_mere.id;

CREATE OR REPLACE VIEW v_stock_usuel AS
select ID_USUEL,
       sum(ENTREE)                 as qte_total,
       sum(PV_TOTAL)               as prix_vente_total,
       sum(PR_TOTAL)               as prix_revient_total,
       sum(PR_TOTAL) / sum(ENTREE) as prix_revient_avg
from MVT_STOCK_DETAIL_LIB
group by ID_USUEL;

-- CREATE OR REPLACE VIEW maxRapport AS
-- select *
-- from (SELECT *
--       FROM USUEL_LIB
--       ORDER BY rapport DESC)
-- where ROWNUM <= 1;

CREATE OR REPLACE VIEW minVolume AS
select *
from (SELECT *
      FROM USUEL_LIB
      ORDER BY volume)
where ROWNUM <= 1;

CREATE OR REPLACE VIEW bloc_stock_lib AS
select b.id           as id_bloc,
       u.id           as id_usuel,
       u.prix_vente   as pv_usuel,
       b.vol_bloc,
       u.volume       as vol_usuel,
       u.rapport,
       b.prix_revient as pr_bloc
from bloc_lib b
         inner join USUEL_LIB u on u.VOLUME <= b.VOL_BLOC
where b.DATY_SORTIE is null;

CREATE OR REPLACE VIEW v_stock_bloc_optim AS
select t.id_bloc,
       t.id_usuel,
       t.pv_usuel,
       t.vol_bloc,
       t.vol_usuel,
       TRUNC(t.vol_bloc / t.vol_usuel)              as qte_produit,
       t.pr_bloc,
       TRUNC(t.vol_bloc / t.vol_usuel) * t.pv_usuel as pv_bloc
from bloc_stock_lib t
         right join (select id_bloc, max(rapport) as rap
                     from bloc_stock_lib
                     group by id_bloc) t2 on t.id_bloc = t2.id_bloc and t.rapport = t2.rap;

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
