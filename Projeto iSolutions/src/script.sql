-- APAGANDO TABELAS CASO EXISTAM

DROP TABLE t_isl_avaliacao CASCADE CONSTRAINTS;

DROP TABLE t_isl_avamec CASCADE CONSTRAINTS;

DROP TABLE t_isl_cliente CASCADE CONSTRAINTS;

DROP TABLE t_isl_marca CASCADE CONSTRAINTS;

DROP TABLE t_isl_mecanica CASCADE CONSTRAINTS;

DROP TABLE t_isl_peca CASCADE CONSTRAINTS;

DROP TABLE t_isl_pre_diagnostico CASCADE CONSTRAINTS;

DROP TABLE t_isl_problema CASCADE CONSTRAINTS;

DROP TABLE t_isl_prodiag CASCADE CONSTRAINTS;

DROP TABLE t_isl_servico CASCADE CONSTRAINTS;

DROP TABLE t_isl_servmeca CASCADE CONSTRAINTS;

DROP TABLE t_isl_servpeca CASCADE CONSTRAINTS;

DROP TABLE t_isl_veiculo CASCADE CONSTRAINTS;

DROP TABLE t_isl_veiserv CASCADE CONSTRAINTS;

-- CRIANDO AS TABELAS

CREATE TABLE t_isl_avaliacao (
    id_avaliacao   NUMBER(10) NOT NULL,
    data           DATE NOT NULL,
    classificacao  VARCHAR2(10) NOT NULL,
    comentario     VARCHAR2(30) NOT NULL
);

ALTER TABLE t_isl_avaliacao
    ADD CONSTRAINT t_isl_avaliacao_ck_1 CHECK ( (LENGTH(classificacao) >= 1)
                                                AND (LENGTH(classificacao) <= 5) );

ALTER TABLE t_isl_avaliacao ADD CONSTRAINT pk_t_isl_id_avaliacao PRIMARY KEY ( id_avaliacao );

CREATE TABLE t_isl_avamec (
    t_isl_id_mecanica   NUMBER(10) NOT NULL,
    t_isl_id_avaliacao  NUMBER(10) NOT NULL
);

ALTER TABLE t_isl_avamec ADD CONSTRAINT pk_t_isl_avamec PRIMARY KEY ( t_isl_id_mecanica,
                                                                      t_isl_id_avaliacao );

CREATE TABLE t_isl_cliente (
    nome        VARCHAR2(35) NOT NULL,
    cpf         VARCHAR2(11) NOT NULL,
    id_cliente  NUMBER(10) NOT NULL,
    login       VARCHAR2(30) NOT NULL,
    senha       VARCHAR2(20) NOT NULL
);

ALTER TABLE t_isl_cliente ADD CONSTRAINT ck_t_isl_cpf CHECK (LENGTH(CPF) = 11);

ALTER TABLE t_isl_cliente ADD CONSTRAINT ck_t_isl_senha CHECK (LENGTH(SENHA) >= 8);

ALTER TABLE t_isl_cliente ADD CONSTRAINT pk_t_isl_id_cliente PRIMARY KEY ( id_cliente );

ALTER TABLE t_isl_cliente ADD CONSTRAINT un_t_isl_cpf UNIQUE ( cpf );

CREATE TABLE t_isl_marca (
    id_marca        NUMBER(10) NOT NULL,
    nome            VARCHAR2(20) NOT NULL,
    pais_origem     VARCHAR2(15),
    ano_fabricacao  VARCHAR2(4),
    id_veiculo      NUMBER(10) NOT NULL
);

ALTER TABLE t_isl_marca ADD CONSTRAINT pk_t_isl_id_marca PRIMARY KEY ( id_marca );

CREATE TABLE t_isl_mecanica (
    id_mecanica  NUMBER(10) NOT NULL,
    cep          VARCHAR2(8) NOT NULL,
    nome         VARCHAR2(30) NOT NULL,
    telefone     VARCHAR2(14) NOT NULL
);

ALTER TABLE t_isl_mecanica ADD CONSTRAINT pk_t_isl_id_mecanica PRIMARY KEY ( id_mecanica );

CREATE TABLE t_isl_peca (
    id_peca       NUMBER(10) NOT NULL,
    nome          VARCHAR2(25) NOT NULL,
    numero_serie  NUMBER(15),
    fabricante    VARCHAR2(25) NOT NULL
);

ALTER TABLE t_isl_peca ADD CONSTRAINT pk_t_isl_id_peca PRIMARY KEY ( id_peca );

ALTER TABLE t_isl_peca ADD CONSTRAINT un_t_isl_numero_serie UNIQUE ( numero_serie );

CREATE TABLE t_isl_pre_diagnostico (
    id_diagnostico   NUMBER(10) NOT NULL,
    comentario       VARCHAR2(50) NOT NULL,
    valor_reparo     NUMBER(12, 2) NOT NULL,
    sugestao_reparo  VARCHAR2(50) NOT NULL
);

ALTER TABLE t_isl_pre_diagnostico ADD CONSTRAINT t_isl_pre_diagnostico_ck_1 CHECK (LENGTH(valor_reparo) >= 1);

ALTER TABLE t_isl_pre_diagnostico ADD CONSTRAINT pk_t_isl_id_diagnostico PRIMARY KEY ( id_diagnostico );

CREATE TABLE t_isl_problema (
    id_problema  NUMBER(10) NOT NULL,
    descricao    VARCHAR2(50) NOT NULL,
    tipo         VARCHAR2(20),
    gravidade    CHAR(1) NOT NULL,
    id_veiculo   NUMBER(10) NOT NULL
);

ALTER TABLE t_isl_problema ADD constraint ck_t_isl_problema CHECK ( (LENGTH(gravidade) >= 1) AND (LENGTH(gravidade) <= 5) );
ALTER TABLE t_isl_problema ADD CONSTRAINT pk_t_isl_id_problema PRIMARY KEY ( id_problema,
                                                                             id_veiculo );

CREATE TABLE t_isl_prodiag (
    t_isl_id_problema_prob  NUMBER(10) NOT NULL,
    t_isl_id_diagnostico    NUMBER(10) NOT NULL,
    t_isl_id_problema_vei   NUMBER(10) NOT NULL
);

ALTER TABLE t_isl_prodiag ADD CONSTRAINT pk_t_isl_prodiag PRIMARY KEY ( t_isl_id_problema_prob,
                                                                        t_isl_id_diagnostico );

CREATE TABLE t_isl_servico (
    id_servico       NUMBER(10) NOT NULL,
    data             DATE NOT NULL,
    custo            NUMBER(12, 2) NOT NULL,
    horario_servico  VARCHAR2(8),
    status_servico   VARCHAR2(20)
);

ALTER TABLE t_isl_servico ADD CONSTRAINT ck_t_isl_servico CHECK (LENGTH(custo) > 1);

ALTER TABLE t_isl_servico ADD CONSTRAINT pk_t_isl_id_servico PRIMARY KEY ( id_servico );

CREATE TABLE t_isl_servmeca (
    t_isl_id_servico   NUMBER(10) NOT NULL,
    t_isl_id_mecanica  NUMBER(10) NOT NULL
);

ALTER TABLE t_isl_servmeca ADD CONSTRAINT pk_servmeca PRIMARY KEY ( t_isl_id_servico,
                                                                    t_isl_id_mecanica );

CREATE TABLE t_isl_servpeca (
    t_isl_id_servico  NUMBER(10) NOT NULL,
    t_isl_id_peca     NUMBER(10) NOT NULL
);

ALTER TABLE t_isl_servpeca ADD CONSTRAINT pk_servpeca PRIMARY KEY ( t_isl_id_servico,
                                                                    t_isl_id_peca );

CREATE TABLE t_isl_veiculo (
    id_veiculo  NUMBER(10) NOT NULL,
    modelo      VARCHAR2(15) NOT NULL,
    ano         VARCHAR2(4),
    placa       VARCHAR2(7),
    id_cliente  NUMBER(10) NOT NULL,
    id_marca    NUMBER(10) NOT NULL
);

ALTER TABLE t_isl_veiculo ADD CONSTRAINT pk_t_isl_id_veiculo PRIMARY KEY ( id_veiculo );

ALTER TABLE t_isl_veiculo ADD CONSTRAINT un_t_isl_placa UNIQUE ( placa );

CREATE TABLE t_isl_veiserv (
    t_isl_id_veiculo  NUMBER(10) NOT NULL,
    t_isl_id_servico  NUMBER(10) NOT NULL
);

ALTER TABLE t_isl_veiserv ADD CONSTRAINT pk_t_isl_veiserv PRIMARY KEY ( t_isl_id_veiculo,
                                                                        t_isl_id_servico );

ALTER TABLE t_isl_avamec
    ADD CONSTRAINT fk_t_isl_id_avaliacao FOREIGN KEY ( t_isl_id_avaliacao )
        REFERENCES t_isl_avaliacao ( id_avaliacao );

ALTER TABLE t_isl_veiculo
    ADD CONSTRAINT fk_t_isl_id_cliente FOREIGN KEY ( id_cliente )
        REFERENCES t_isl_cliente ( id_cliente );

ALTER TABLE t_isl_veiculo
    ADD CONSTRAINT fk_t_isl_id_marca FOREIGN KEY ( id_marca )
        REFERENCES t_isl_marca ( id_marca );

ALTER TABLE t_isl_avamec
    ADD CONSTRAINT fk_t_isl_id_mecanica FOREIGN KEY ( t_isl_id_mecanica )
        REFERENCES t_isl_mecanica ( id_mecanica );

ALTER TABLE t_isl_servmeca
    ADD CONSTRAINT fk_t_isl_id_mecanicav1 FOREIGN KEY ( t_isl_id_mecanica )
        REFERENCES t_isl_mecanica ( id_mecanica );

ALTER TABLE t_isl_servpeca
    ADD CONSTRAINT fk_t_isl_id_peca FOREIGN KEY ( t_isl_id_peca )
        REFERENCES t_isl_peca ( id_peca );

ALTER TABLE t_isl_servpeca
    ADD CONSTRAINT fk_t_isl_id_servico FOREIGN KEY ( t_isl_id_servico )
        REFERENCES t_isl_servico ( id_servico );

ALTER TABLE t_isl_servmeca
    ADD CONSTRAINT fk_t_isl_id_servicov1 FOREIGN KEY ( t_isl_id_servico )
        REFERENCES t_isl_servico ( id_servico );

ALTER TABLE t_isl_veiserv
    ADD CONSTRAINT fk_t_isl_id_servicov2 FOREIGN KEY ( t_isl_id_servico )
        REFERENCES t_isl_servico ( id_servico );

ALTER TABLE t_isl_problema
    ADD CONSTRAINT fk_t_isl_id_veiculo FOREIGN KEY ( id_veiculo )
        REFERENCES t_isl_veiculo ( id_veiculo );

ALTER TABLE t_isl_veiserv
    ADD CONSTRAINT fk_t_isl_id_veiculov1 FOREIGN KEY ( t_isl_id_veiculo )
        REFERENCES t_isl_veiculo ( id_veiculo );

ALTER TABLE t_isl_prodiag
    ADD CONSTRAINT fk_t_isl_pre_diagnostico FOREIGN KEY ( t_isl_id_diagnostico )
        REFERENCES t_isl_pre_diagnostico ( id_diagnostico );

ALTER TABLE t_isl_prodiag
    ADD CONSTRAINT fk_t_isl_problema FOREIGN KEY ( t_isl_id_problema_prob,
                                                   t_isl_id_problema_vei )
        REFERENCES t_isl_problema ( id_problema,
                                    id_veiculo );

DROP SEQUENCE CLIENTE_SEQ;

DROP SEQUENCE SERVICO_SEQ;

CREATE SEQUENCE CLIENTE_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

CREATE SEQUENCE SERVICO_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

CREATE OR REPLACE TRIGGER CLIENTE_TRIGGER
BEFORE INSERT ON T_ISL_CLIENTE
FOR EACH ROW
BEGIN
    IF :NEW.ID_CLIENTE IS NULL THEN
        :NEW.ID_CLIENTE := CLIENTE_SEQ.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER SERVICO_TRIGGER
BEFORE INSERT ON T_ISL_SERVICO
FOR EACH ROW
BEGIN
    IF :NEW.ID_SERVICO IS NULL THEN
        :NEW.ID_SERVICO := SERVICO_SEQ.NEXTVAL;
    END IF;
END;
/