/*******************************************************************
*                          INDEPENDENT TABLES                      *
********************************************************************/
CREATE TABLE entity(
  province CHARACTER VARYING (30) NOT NULL,
  canton CHARACTER VARYING  (30) NOT NULL,
  community CHARACTER VARYING  (30) NOT NULL,
  address CHARACTER VARYING (50) NOT NULL,
  telephone CHARACTER VARYING (13) NOT NULL,
  email CHARACTER VARYING (30) NOT NULL,
  ruc CHARACTER VARYING  (15) NOT NULL
);

CREATE TABLE role(
  id SERIAL NOT NULL,
  name CHARACTER VARYING (30) UNIQUE NOT NULL,
  CONSTRAINT pk_role PRIMARY KEY(id)
);


CREATE TABLE village(
  id SERIAL NOT NULL,
  name CHARACTER VARYING (50) UNIQUE NOT NULL,
  CONSTRAINT pk_village PRIMARY KEY(id)
);


CREATE TABLE sap(
  id SERIAL NOT NULL,
  name CHARACTER VARYING (50) NOT NULL,
  basevolume	REAL NOT NULL DEFAULT 0,
  baseprice	DOUBLE PRECISION  NOT NULL DEFAULT 0,
  extraprice	DOUBLE PRECISION  NOT NULL DEFAULT 0,
  CONSTRAINT pk_sap PRIMARY KEY(id)
);

CREATE TABLE anotherservice(
  id	SERIAL NOT NULL,
  name	CHARACTER VARYING(50) NOT NULL,
  price	DOUBLE PRECISION NOT NULL,
  CONSTRAINT pk_anotherservice PRIMARY KEY(id)
);


CREATE TABLE status(
  id SERIAL NOT NULL,
  name CHARACTER VARYING (40) UNIQUE NOT NULL,
  CONSTRAINT pk_status PRIMARY KEY(id)
);

/*******************************************************************
*                      TABLES WITH DEPENDENCIES                    *
********************************************************************/


CREATE TABLE operator(
  id	SERIAL NOT NULL,
  roleid	INTEGER NOT NULL,
  username	CHARACTER VARYING	(30) UNIQUE NOT NULL,
  password	CHARACTER VARYING	(40) NOT NULL,
  email	CHARACTER VARYING	(100) UNIQUE NOT NULL,
  dni	CHARACTER VARYING	(10) UNIQUE NOT NULL,
  firstname	CHARACTER VARYING	(30) NOT NULL,
  lastname	CHARACTER VARYING	(30) NOT NULL,
  telephone	CHARACTER VARYING	(13) UNIQUE,
  address	TEXT,
  CONSTRAINT pk_operator PRIMARY KEY(id)
);


CREATE TABLE audit(
  id	SERIAL NOT NULL,
  operatorid	INTEGER NOT NULL,
  executeddate	TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  tableaffected	CHARACTER VARYING	(25	) NOT NULL,
  actionname	CHARACTER VARYING	(15	) NOT NULL,
  description	TEXT,
  CONSTRAINT pk_audit PRIMARY KEY(id)
);


CREATE TABLE measurer(
  id	SERIAL NOT NULL,
  sapid	INTEGER	NOT NULL,
  statusid	INTEGER DEFAULT 1	NOT NULL,
  number	CHARACTER VARYING	(10) UNIQUE NOT NULL,
  installationdate	DATE	DEFAULT current_date NOT NULL,
  CONSTRAINT pk_measurer PRIMARY KEY(id)
);


CREATE TABLE beneficiary(
  id	SERIAL NOT NULL,
  villageid	INTEGER	NOT NULL,
  dni	CHARACTER VARYING	(10) UNIQUE NOT NULL,
  lastname	CHARACTER VARYING	(30) NOT NULL,
  firstname	CHARACTER VARYING	(30) NOT NULL,
  telephone	CHARACTER VARYING	(13) UNIQUE,
  address	TEXT	NOT NULL,
  placereference	TEXT,
  CONSTRAINT pk_beneficiary PRIMARY KEY(id)
);


CREATE TABLE uptake(
  id	SERIAL NOT NULL,
  measurerid	INTEGER	NOT NULL,
  datetaked	DATE DEFAULT CURRENT_DATE	NOT NULL,
  lastvaluetaken	REAL	NOT NULL,
  currentvaluetaken	REAL	NOT NULL,
  basevolume	REAL	NOT NULL,
  baseprice	DOUBLE PRECISION NOT NULL,
  extraprice	DOUBLE PRECISION	NOT NULL,
  volumeconsumed	REAL	NOT NULL,
  volumeexceeded	REAL	NOT NULL,
  totalprice	DOUBLE PRECISION	NOT NULL,
  billed	BOOLEAN DEFAULT FALSE	NOT NULL,
  CONSTRAINT pk_uptake PRIMARY KEY(id)
);



CREATE TABLE invoice(
  id	SERIAL NOT NULL,
  beneficiaryid	INTEGER	NOT NULL,
  debtcollectorid	INTEGER	NOT NULL,
  number CHARACTER VARYING (10) NOT NULL,
  dateofissue	TIMESTAMP	DEFAULT	CURRENT_TIMESTAMP NOT NULL,
  totaltopay	DOUBLE PRECISION NOT NULL,
  payed	BOOLEAN	DEFAULT	FALSE NOT NULL,
  CONSTRAINT pk_invoice PRIMARY KEY(id)
);


/*******************************************************************
*                         INTERMEDIATE TABLES                      *
********************************************************************/

CREATE TABLE assigned(
  beneficiaryid	INTEGER NOT NULL,
  measurerid	INTEGER NOT NULL,
  debt DOUBLE PRECISION NOT NULL DEFAULT 0,
  assignmentdate	DATE DEFAULT CURRENT_DATE NOT NULL,
  status	CHARACTER VARYING	(10) DEFAULT	'enable' NOT NULL
);


CREATE TABLE sapdetail(
  invoiceid	INTEGER NOT NULL,
  uptakeid	INTEGER NOT NULL
);


CREATE TABLE anotherservicedetail(
  invoiceid	INTEGER NOT NULL,
  anotherserviceid	INTEGER NOT NULL,
  price	DOUBLE PRECISION NOT NULL
);
