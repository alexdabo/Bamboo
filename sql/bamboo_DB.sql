
/*******************************************************************
*                 INSERCION DE ROLES DE EMPLEADO                   *
********************************************************************/
INSERT INTO role(id, name) VALUES
  ('1','Administración'),
  ('2','Cobros'),
  ('3','Medidas');

/*******************************************************************
*                   INSERCION DE ESTADO DE MEDIDOR                 *
********************************************************************/
INSERT INTO status(id, name) VALUES
  ('1','Activo'),
  ('2','Suspendido'),
  ('3','Dañado');
  
/*******************************************************************
*                          INSERCION DE EMPLEADOS                  *
********************************************************************/
INSERT INTO public.operator(id,roleid,username, password, email, dni, firstname, lastname, telephone, address) VALUES  
       ('1',1,'admin', '21232f297a57a5a743894a0e4a801fc3','admin@bamboo.com', '0000000000', 'admin', 'admin', '0000000000',''),
       ('2',2,'cobros', '20aabadb6855268577d5a66f0d9346d7','cobros@bamboo.com', '1111111111', 'cobros', 'cobros', '1111111111',''),
       ('3',3,'medidas', '794544c88ec2aa0fdeaa3e7a4fad6454','medidas@bamboo.com', '222222222', 'medidas', 'medidas', '2222222222','');

/*******************************************************************
*                          INSERCION DE COMUNIDAD                  *
********************************************************************/
INSERT INTO village(id, name) values
  ('1','Riobamba'),
  ('2','Guano'),
  ('3','San Andres'),
  ('4','Yaruquies');


/*******************************************************************
*                     INSERCION DE SERVICIOS SAP                   *
********************************************************************/
INSERT INTO public.sap(id, name, basevolume, baseprice, extraprice) VALUES
	('1', 'SAP Empresarial',           50,   10,   0.10  ),
	('2', 'SAP Comercial',             25,   10,   0.10  ),
	('3', 'SAP Residencial ',          15,   10,   0.10  ),
	('4', 'SAP para tercera edad ',    15,   10,   0.00  ),
	('5', 'SAP para discapacitados ',  15,   10,   0.00  );


/*******************************************************************
*                   INSERCION DE OTROS SERVICIOS                   *
********************************************************************/
INSERT INTO public.anotherservice(id, name, price) VALUES
  ('1', 'Traspaso de medidor',              5),
  ('2', 'Rehabilitación del servicio',   12.5),
  ('3', 'Instalacion de medidor',          10);


/*******************************************************************
*                          INSERCION DE CLIENTES                   *
********************************************************************/
  INSERT INTO public.beneficiary(id, villageid, dni, lastname, firstname, telephone, address, placereference) VALUES
    ('1', 1,   '0604059741',  'Bonilla adriano',    'alexander david',      '0979728686',     'Av. Cevallos',             'Junto a la parada de la linea 10'),
    ('2', 2,   '1724094667',  'Vera correa',        'Jairo alexander',      '0934529845',     'La paz',                   'Perpendicular a la farmacia'),
    ('3', 3,   '0987654345',  'Velez rocero',       'Jose eduardo',         '0934235464',     'Av la principal',          'Junto al parque miraflores.'),
    ('4', 4,   '0803226561',  'porras vásconez',    'erick joaquin',        '0987655653',     'AvAntonio Jose de Sucre',  'Frente a la junta parroquial'),
    ('5', 1,   '9876578965',  'sisa sisa',          'angel isaias',         '0983746237',     'La primera constituyente', 'Junto a la fotocopiadora el copion'),
    ('6', 2,   '2345678896',  'ortega siguencia',   'gabriela sanndy',      '0973828118',     '11 de noviembre y colon',  'Frente al coliseo'),
    ('7', 3,   '0604150384',  'Montenegro Garrido', 'Jhonatan Israel',      '0973822411',     'Primavera',		     'Frente al parque Industrial'),  
    ('8', 1,   '0114059741',  'Parreño Parreño',    'Juan Jose',            '0934528686',     'Av. Cevallos',             'Junto a la parada de la linea 10'),
    ('9', 2,   '1722414667',  'Olmedo Lara',        'Lourdes Lucia',        '0934171845',     'La paz',                   'Perpendicular a la farmacia'),
    ('10',3,   '0981524345',  'Escobar Basantes',   'Katia Maribel',        '0931371464',     'Av la principal',          'Junto al parque miraflores.'),    
    ('11',4,   '0801556561',  'Cando Guaman',       'Bety Elizabeth',       '0945733653',     'AvAntonio Jose de Sucre',  'Frente a la junta parroquial'),
    ('12',1,   '9811238965',  'Villarreal Panchi',  'Andres Sebastian',     '0983463237',     'La primera constituyente', 'Junto a la fotocopiadora el copion'),
    ('13',2,   '2345111296',  'Naranjo Ponluisa',   'Andrea Paola',         '0973899618',     '11 de noviembre y colon',  'Frente al coliseo'),
    ('14',3,   '0601534284',  'Merino Achance',     'Jennifer Alexandra',   '0973822518',     'Primavera',		     'Frente al parque Industrial'), 
    ('15',1,   '0601264341',  'Delgado Velasco',    'Fausto Rodrigo',       '0979235286',     'Av. Cevallos',             'Junto a la parada de la linea 10'),
    ('16',2,   '1722345567',  'Coello Gavilanez',   'Magali Jasmin',        '0932362445',     'La paz',                   'Perpendicular a la farmacia'),
    ('17',3,   '0985552345',  'Cardenas Guzman',    'Daniela Milagros',     '0936584444',     'Av la principal',          'Junto al parque miraflores.'),
    ('18',4,   '0802355761',  'Bayas Cauritongo',   'Maria Augusta',        '0982457853',     'AvAntonio Jose de Sucre',  'Frente a la junta parroquial'),
    ('19',1,   '9876457765',  'Escobar Basantes',   'Katia Maribel',        '0924724737',     'La primera constituyente', 'Junto a la fotocopiadora el copion'),
    ('20',2,   '2345457396',  'Delgado Velasco',    'Fausto Rodrigo',       '0978888518',     '11 de noviembre y colon',  'Frente al coliseo'),
    ('21',1,   '9874572865',  'Andino Najera',      'Lorena Paulina',       '0983275237',     'La primera constituyente', 'Junto a la fotocopiadora el copion'),    
    ('22',3,   '3245600454',  'Palmay Vargas',      'Brigith Lisbeth',      '0918886446',     'Av Baños de agua santa',   'Junto a la Hacienda los altares');



/*******************************************************************
*                     INSERCION DE MEDIDORES                      *
********************************************************************/
INSERT INTO PUBLIC.measurer(id, sapid, statusid, "number", installationdate) values
  ('1', 1,1,'1','2019-02-22'),
  ('2', 2,1,'2','2018-08-12'),
  ('3', 3,1,'3','2013-11-07'),
  ('4', 4,1,'4','2018-08-12'),
  ('5', 5,1,'5','2013-05-16'),
  ('6', 1,1,'6','2019-10-02'),
  ('7', 2,1,'7','2018-04-12'),
  ('8', 3,1,'8','2019-02-21'),
  ('9', 2,1,'9','2018-08-12'),
  ('10', 3,1,'10','2013-11-07'),
  ('11',4,1,'11','2018-08-12'),
  ('12',5,1,'12','2013-05-16'),
  ('13',1,1,'13','2019-10-02'),
  ('14',2,1,'14','2018-04-12'),
  ('15',3,1,'15','2019-02-21'),
  ('16',4,1,'16','2018-08-12'),
  ('17',5,1,'17','2013-05-16'),
  ('18',1,1,'18','2019-10-02'),
  ('19',2,1,'19','2018-04-12'),
  ('20',3,1,'20','2019-02-21'),
  ('21',4,1,'21','2018-08-12'),
  ('22',5,1,'22','2013-05-16');
  


/*******************************************************************
*                     INSERCION DE SERVICIOS SAP                   *
********************************************************************/
INSERT INTO PUBLIC.assigned(beneficiaryid, measurerid, debt, assignmentdate, status) values
  (1,8,20,'2019-02-22','ENABLE'),
  (2,7,6,'2015-11-08','ENABLE'),
  (3,6,15,'2018-06-02','ENABLE'),
  (4,5,2,'2019-02-22','ENABLE'),
  (5,4,6,'2019-02-22','ENABLE'),
  (6,3,23,'2019-02-22','ENABLE'),
  (7,2,21,'2019-02-22','ENABLE'),
  (8,1,20,'2019-02-22','ENABLE'),
  (9,8,25,'2019-02-22','ENABLE'),
  (10,7,10,'2019-02-22','ENABLE'),
  (11,6,15,'2019-02-22','FALSE'),
  (12,5,16,'2019-02-22','ENABLE'),
  (13,4,26,'2019-02-22','ENABLE'),
  (14,3,12,'2019-02-22','ENABLE'),
  (15,2,3,'2019-02-22','ENABLE'),
  (16,1,2,'2019-02-22','FALSE'),
  (17,7,15,'2019-02-22','ENABLE'),
  (18,6,15,'2019-02-22','ENABLE'),
  (19,5,26,'2019-02-22','ENABLE'),
  (20,4,23,'2019-02-22','FALSE'),
  (21,3,8,'2019-02-22','ENABLE');


/*******************************************************************
*                      INSERCION DE FACTURA                        *
********************************************************************/
INSERT INTO invoice(id, beneficiaryid, debtcollectorid, "number", dateofissue, totaltopay, payed) VALUES 
  ('1', 1, 1, '1', '2019-02-1 00:00:00',  22, FALSE),
  ('2', 2, 1, '1', '2019-04-22 00:00:00', 12, TRUE),
  ('3', 3, 1, '1', '2019-01-18 00:00:00',  2, FALSE),
  ('4', 4, 1, '1', '2019-07-9 00:00:00',  11, TRUE),
  ('5', 5, 1, '1', '2019-08-29 00:00:00',  8, TRUE),
  ('6', 6, 1, '1', '2019-07-9 00:00:00',  11, TRUE),
  ('7', 7, 1, '1', '2019-08-29 00:00:00',  8, TRUE),
  ('8', 8, 1, '1', '2019-07-9 00:00:00',  11, TRUE),
  ('9', 9, 1, '1', '2019-08-29 00:00:00',  8, TRUE),
  ('10',10, 1, '1', '2019-07-9 00:00:00',  11, TRUE),
  ('11',11, 1, '1', '2019-08-29 00:00:00',  8, TRUE),
  ('12',12, 1, '1', '2019-07-9 00:00:00',  11, TRUE),
  ('13',13, 1, '1', '2019-08-29 00:00:00',  8, TRUE),
  ('14',14, 1, '1', '2019-07-9 00:00:00',  11, TRUE),
  ('15',15, 1, '1', '2019-08-29 00:00:00',  8, TRUE),
  ('16',16, 1, '1', '2019-07-9 00:00:00',  11, TRUE),
  ('17',17, 1, '1', '2019-08-29 00:00:00',  8, TRUE),
  ('18',18, 1, '1', '2019-07-9 00:00:00',  11, TRUE),
  ('19',19, 1, '1', '2019-08-29 00:00:00',  8, TRUE),
  ('20',20, 1, '1', '2019-07-9 00:00:00',  11, TRUE),
  ('21',21, 1, '1', '2019-08-29 00:00:00',  8, TRUE),
  ('22',22, 1, '1', '2019-02-12 00:00:00',100, FALSE);


  
/*******************************************************************
*             INSERCION DE FACTURA DE OTRO SERVICIO                *
********************************************************************/
INSERT INTO anotherservicedetail(invoiceid, anotherserviceid, price) VALUES 
  (1, 1, 22),
  (2, 3, 12),
  (3, 1, 5),
  (4, 3, 18),
  (5, 1, 35),
  (6, 1, 22),
  (7, 3, 12),
  (8, 1, 5),
  (9, 3, 18),
  (10, 1, 35),
  (11, 3, 18),
  (12, 1, 35),
  (13, 1, 22),
  (14, 3, 12),
  (15, 1, 5),
  (16, 3, 18),
  (17, 1, 35),
  (18, 1, 5),
  (19, 3, 18),
  (20, 1, 35),
  (21, 3, 18),
  (22, 3, 18);
  

/*******************************************************************
*             		INSERCION DE MEDICION                      *
********************************************************************/

INSERT INTO uptake(measurerid, datetaked, lastvaluetaken, currentvaluetaken, 
            basevolume, baseprice, extraprice, volumeconsumed, volumeexceeded, 
            totalprice, billed) VALUES 
    (1, '2019-02-16', 2, 2, 50, 10, 0.10, 2, 0, 10.1, TRUE),
    (2, '2018-05-13', 2, 3, 15, 10, 0.10, 2, 0, 10.1, TRUE),
    (3, '2018-06-01', 1, 5, 25, 10, 0.10, 2, 0, 10.1, TRUE),
    (4, '2014-12-04', 5, 5, 50, 10, 0.10, 2, 0, 10.1, TRUE),
    (5, '2013-06-09', 6,11, 15, 10, 0.10, 2, 0, 10.1, TRUE),
    (6, '2017-08-22', 2, 2, 15, 10, 0.10, 2, 0, 10.1, TRUE),
    (7, '2014-03-21', 5, 4, 50, 10, 0.10, 2, 0, 10.1, TRUE),
    (8, '2016-02-16', 2, 6, 25, 10, 0.10, 2, 0, 10.1, TRUE),
    (9, '2018-06-01', 1, 5, 25, 10, 0.10, 2, 0, 10.1, TRUE),
    (10, '2014-12-04', 5, 5, 50, 10, 0.10, 2, 0, 10.1, TRUE),
    (11, '2013-06-09', 6,11, 15, 10, 0.10, 2, 0, 10.1, TRUE),
    (12, '2017-08-22', 2, 2, 15, 10, 0.10, 2, 0, 10.1, TRUE),
    (13, '2014-03-21', 5, 4, 50, 10, 0.10, 2, 0, 10.1, TRUE),
    (14, '2016-02-16', 2, 6, 25, 10, 0.10, 2, 0, 10.1, TRUE);
    
  

    
/*******************************************************************
*             		INSERCION DE MEDICION                      *
********************************************************************/

INSERT INTO sapdetail(invoiceid, uptakeid) VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6),
    (7, 7),
    (8, 8),
    (9, 9),
    (10, 10),
    (11, 11),
    (12, 12),
    (13, 13),
    (14, 14);

    /**************************/






