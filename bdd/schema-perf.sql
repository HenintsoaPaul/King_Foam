CREATE TABLE bloc
(
    id                     VARCHAR2(50),
    daty_entree            DATE          NOT NULL,
    daty_sortie            DATE,
    prix_revient_theorique NUMBER(15, 5) NOT NULL,
    prix_revient_pratique  NUMBER(15, 5) NOT NULL,
    longueur               NUMBER(15, 5) NOT NULL,
    largeur                NUMBER(15, 5) NOT NULL,
    hauteur                NUMBER(15, 5) NOT NULL,
    id_bloc_mere           VARCHAR2(50),
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

CREATE TABLE machine
(
    id  VARCHAR2(50),
    val VARCHAR2(50) NOT NULL,
    PRIMARY KEY (id)
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
