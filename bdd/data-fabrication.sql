INSERT INTO MACHINE (id, VAL)
VALUES ('M001', 'Machine1');
INSERT INTO MACHINE (id, VAL)
VALUES ('M002', 'Machine2');
INSERT INTO MACHINE (id, VAL)
VALUES ('M003', 'Machine3');
INSERT INTO MACHINE (id, VAL)
VALUES ('M004', 'Machine4');

INSERT INTO unite (id, val, symbole)
VALUES ('U001', 'Litre', 'l');
INSERT INTO unite (id, val, symbole)
VALUES ('U002', 'Kilogramme', 'kg');
INSERT INTO unite (id, val, symbole)
VALUES ('U003', 'Gramme', 'g');

INSERT INTO CONSOMMABLE (id, val, ID_UNITE)
VALUES ('C001', 'Essence', 'U001');
INSERT INTO CONSOMMABLE (id, val, ID_UNITE)
VALUES ('C002', 'Papier', 'U002');

INSERT INTO FORMULE_FABRICATION (id, QTE, ID_CONSOMMABLE)
VALUES ('F1', 1.5, 'C001');
INSERT INTO FORMULE_FABRICATION (id, QTE, ID_CONSOMMABLE)
VALUES ('F2', 0.2, 'C002');

INSERT INTO ACHAT_CONSOMMABLE (id, DATY, QTE, RESTE, PU, ID_CONSOMMABLE)
VALUES ('A001', TO_DATE('10-10-2022', 'dd-mm-yyyy'), 2, 2, 4900, 'C001');
INSERT INTO ACHAT_CONSOMMABLE (id, DATY, QTE, RESTE, PU, ID_CONSOMMABLE)
VALUES ('A002', TO_DATE('10-10-2022', 'dd-mm-yyyy'), 13.1, 13.1, 5100, 'C001');
INSERT INTO ACHAT_CONSOMMABLE (id, DATY, QTE, RESTE, PU, ID_CONSOMMABLE)
VALUES ('A003', TO_DATE('10-10-2022', 'dd-mm-yyyy'), 7.2, 7.2, 1200, 'C002');
