-- Séquences
CREATE SEQUENCE seq_bloc nomaxvalue increment by 1 start with 1;
CREATE SEQUENCE seq_transformation nomaxvalue increment by 1 start with 1;
CREATE SEQUENCE seq_usuel nomaxvalue increment by 1 start with 1;
CREATE SEQUENCE seq_teta nomaxvalue increment by 1 start with 1;
CREATE SEQUENCE seq_type_mvt_stock nomaxvalue increment by 1 start with 1;
CREATE SEQUENCE seq_machine nomaxvalue increment by 1 start with 1;
CREATE SEQUENCE seq_unite nomaxvalue increment by 1 start with 1;
CREATE SEQUENCE seq_mvt_stock nomaxvalue increment by 1 start with 1;
CREATE SEQUENCE seq_mvt_stock_detail nomaxvalue increment by 1 start with 1;
CREATE SEQUENCE seq_fabrication nomaxvalue increment by 1 start with 1;
CREATE SEQUENCE seq_consommable nomaxvalue increment by 1 start with 1;
CREATE SEQUENCE seq_formule_fabrication nomaxvalue increment by 1 start with 1;
CREATE SEQUENCE seq_achat_consommable nomaxvalue increment by 1 start with 1;

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

-- --machine
CREATE OR REPLACE FUNCTION seq_machine_format(p_num NUMBER) RETURN VARCHAR2 IS
    v_result VARCHAR2(255);
BEGIN
    v_result := 'MACH' || LPAD(TO_CHAR(p_num), 4, '0');
    RETURN v_result;
END;

CREATE FUNCTION get_seq_machine
    RETURN NUMBER
    IS
    retour NUMBER;
BEGIN
    SELECT seq_machine.NEXTVAL INTO retour FROM DUAL;

    RETURN retour;
END;

-- --unite
CREATE OR REPLACE FUNCTION seq_unite_format(p_num NUMBER) RETURN VARCHAR2 IS
    v_result VARCHAR2(255);
BEGIN
    v_result := 'UNIT' || LPAD(TO_CHAR(p_num), 4, '0');
    RETURN v_result;
END;

CREATE FUNCTION get_seq_unite
    RETURN NUMBER
    IS
    retour NUMBER;
BEGIN
    SELECT seq_unite.NEXTVAL INTO retour FROM DUAL;

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

-- --fabrication
CREATE OR REPLACE FUNCTION seq_fabrication_format(p_num NUMBER) RETURN VARCHAR2 IS
    v_result VARCHAR2(255);
BEGIN
    v_result := 'FABR' || LPAD(TO_CHAR(p_num), 4, '0');
    RETURN v_result;
END;

CREATE FUNCTION get_seq_fabrication
    RETURN NUMBER
    IS
    retour NUMBER;
BEGIN
    SELECT seq_fabrication.NEXTVAL INTO retour FROM DUAL;

    RETURN retour;
END;

-- --consommable
CREATE OR REPLACE FUNCTION seq_consommable_format(p_num NUMBER) RETURN VARCHAR2 IS
    v_result VARCHAR2(255);
BEGIN
    v_result := 'CONS' || LPAD(TO_CHAR(p_num), 4, '0');
    RETURN v_result;
END;

CREATE FUNCTION get_seq_consommable
    RETURN NUMBER
    IS
    retour NUMBER;
BEGIN
    SELECT seq_consommable.NEXTVAL INTO retour FROM DUAL;

    RETURN retour;
END;

-- --achat_consommable
CREATE OR REPLACE FUNCTION seq_achat_consommable_format(p_num NUMBER) RETURN VARCHAR2 IS
    v_result VARCHAR2(255);
BEGIN
    v_result := 'ACHA' || LPAD(TO_CHAR(p_num), 4, '0');
    RETURN v_result;
END;

CREATE FUNCTION get_seq_achat_consommable
    RETURN NUMBER
    IS
    retour NUMBER;
BEGIN
    SELECT seq_achat_consommable.NEXTVAL INTO retour FROM DUAL;

    RETURN retour;
END;

-- --formule_fabrication
CREATE OR REPLACE FUNCTION seq_formule_fabrication_format(p_num NUMBER) RETURN VARCHAR2 IS
    v_result VARCHAR2(255);
BEGIN
    v_result := 'FORM' || LPAD(TO_CHAR(p_num), 4, '0');
    RETURN v_result;
END;

CREATE FUNCTION get_seq_formule_fabrication
    RETURN NUMBER
    IS
    retour NUMBER;
BEGIN
    SELECT seq_formule_fabrication.NEXTVAL INTO retour FROM DUAL;

    RETURN retour;
END;

-- Tables
CREATE TABLE machine
(
    id  VARCHAR2(50),
    val VARCHAR2(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE bloc
(
    id                     VARCHAR2(50),
    daty_entree            DATE          NOT NULL,
    daty_sortie            DATE,
    prix_revient_theorique NUMBER(15, 5) DEFAULT 0,
    prix_revient_pratique  NUMBER(15, 5) DEFAULT 0,
    longueur               NUMBER(15, 5) NOT NULL,
    largeur                NUMBER(15, 5) NOT NULL,
    hauteur                NUMBER(15, 5) NOT NULL,
    id_bloc_mere           VARCHAR2(50),
    id_machine             VARCHAR2(50),
    PRIMARY KEY (id),
    FOREIGN KEY (id_bloc_mere) REFERENCES bloc (id),
    FOREIGN KEY (id_machine) REFERENCES machine (id)
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

CREATE TABLE unite
(
    id      VARCHAR2(50),
    val     VARCHAR2(50) NOT NULL,
    symbole VARCHAR2(50) NOT NULL,
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

CREATE TABLE fabrication
(
    id         VARCHAR2(50),
    daty       DATE         NOT NULL,
    id_machine VARCHAR2(50) NOT NULL,
    id_bloc    VARCHAR2(50) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_machine) REFERENCES machine (id),
    FOREIGN KEY (id_bloc) REFERENCES bloc (id)
);

CREATE TABLE consommable
(
    id       VARCHAR2(50),
    val      VARCHAR2(50) NOT NULL,
    id_unite VARCHAR2(50) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_unite) REFERENCES unite (id)
);

CREATE TABLE formule_fabrication
(
    id             VARCHAR2(50),
    qte            NUMBER(15, 5) NOT NULL,
    id_consommable VARCHAR2(50)  NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_consommable) REFERENCES consommable (id)
);

CREATE TABLE achat_consommable
(
    id             VARCHAR2(50),
    daty           DATE         NOT NULL,
    qte            NUMBER(15, 5),
    reste          NUMBER(15, 5),
    pu             NUMBER(15, 5),
    id_consommable VARCHAR2(50) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_consommable) REFERENCES consommable (id)
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

CREATE OR REPLACE VIEW minVolume AS
select *
from (SELECT *
      FROM USUEL_LIB
      ORDER BY volume)
where ROWNUM <= 1;

CREATE OR REPLACE VIEW bloc_stock_lib AS
select b.id                    as id_bloc,
       u.id                    as id_usuel,
       u.prix_vente            as pv_usuel,
       b.vol_bloc,
       u.volume                as vol_usuel,
       u.rapport,
       b.prix_revient_pratique as pr_bloc
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
       b.prix_revient_pratique                                                               as pr_bloc,
       TRUNC((b.LONGUEUR * b.LARGEUR * b.HAUTEUR) / minVolume.VOLUME) * minVolume.PRIX_VENTE as pv_bloc
from bloc b
         cross join minVolume
where DATY_SORTIE is null;

CREATE OR REPLACE VIEW pr_pratique_lib AS
select sum(PRIX_REVIENT_PRATIQUE)                                     as sum_pr_pratique,
       sum(HAUTEUR * LARGEUR * LONGUEUR)                              as sum_volume,
       sum(PRIX_REVIENT_PRATIQUE) / sum(HAUTEUR * LARGEUR * LONGUEUR) as pr_pratique_volumique,
       avg(PRIX_REVIENT_PRATIQUE / (HAUTEUR * LARGEUR * LONGUEUR))    as avg_pr_pratique_volumique
from bloc b;

CREATE OR REPLACE VIEW perfo_lib AS
select ID_MACHINE,
       sum(LONGUEUR * LARGEUR * HAUTEUR)                                                                as vol_total,
       sum(PRIX_REVIENT_PRATIQUE)                                                                       as sum_pr_pratique,
       sum(PRIX_REVIENT_THEORIQUE)                                                                      as sum_pr_theorique,
       sum(PRIX_REVIENT_THEORIQUE) - sum(PRIX_REVIENT_PRATIQUE)                                         as diff_th_reel,
       ((sum(PRIX_REVIENT_THEORIQUE) - sum(PRIX_REVIENT_PRATIQUE)) / sum(PRIX_REVIENT_THEORIQUE)) * 100 as performance
from bloc b
where b.ID_MACHINE is not null
group by ID_MACHINE
order by performance desc;
