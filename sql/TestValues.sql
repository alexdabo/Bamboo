UPDATE public.entity SET 
	province='Cimborazo', canton='penipe', community='San antonio de bayushig', 
	address='Av baños de agua santa', telephone='0973456418', email='japbayushig@bamboo.com', ruc='0934512983453';
	
INSERT INTO public.operator(roleid,username, password, email, dni, firstname, lastname, telephone, address) VALUES
	(2,'cobros', '20aabadb6855268577d5a66f0d9346d7','cobros@bamboo.com', '1111111111', 'cobros', 'cobros', '1111111111',''),
    (3,'medidas', '794544c88ec2aa0fdeaa3e7a4fad6454','medidas@bamboo.com', '222222222', 'medidas', 'medidas', '2222222222','');

INSERT INTO village(name) values
  	('Bayushig'),
  	('Bilbao'),
  	('La Candelaria'),
  	('Matus');


INSERT INTO public.sap(name, basevolume, baseprice, extraprice) VALUES
	('SAP Empresarial',           50,   10,   0.10  ),
	('SAP Comercial',             25,   10,   0.10  ),
	('SAP Residencial ',          15,   10,   0.10  ),
	('SAP para tercera edad ',    15,   10,   0.00  ),
    ('SAP para discapacitados ',  15,   10,   0.00  );


INSERT INTO public.anotherservice(name, price) VALUES
  	('Traspaso de medidor',           5),
  	('Rehabilitación del servicio',   12.5),
  	('Instalacion de medidor',        10);


  INSERT INTO public.beneficiary( villageid, dni, lastname, firstname, telephone, address, placereference) VALUES
    (1,   '0604059741',  'bonilla adriano',    'alexander david',      '0979728686',     'Av. Cevallos',             'Junto a la parada de la linea 10'),
    (2,   '0546643356',  'vera correa',        'jairo alexander',      '0934529845',     'La paz',                   'Perpendicular a la farmacia'),
    (3,   '0987654345',  'velez rocero',       'jose eduardo',         '0934235464',     'Av la principal',          'Junto al parque miraflores.'),
    (4,   '3456785676',  'porras vásconez',    'erick joaquin',        '0987655653',     'AvAntonio Jose de Sucre',  'Frente a la junta parroquial'),
    (1,   '9876578965',  'sisa sisa',          'angel isaias',         '0983746237',     'La primera constituyente', 'Junto a la fotocopiadora el copion'),
    (2,   '2345678896',  'ortega siguencia',   'gabriela sanndy',      '0973828118',     '11 de noviembre y colon',  'Frente al coliseo'),
    (3,   '3245677654',  'palmay vargas',      'brigith lisbeth',      '0918872646',     'Av Baños de agua santa',   'Junto a la Hacienda los altares'),
	(2,   '1722414667',  'Olmedo Lara',        'lourdes lucia',        '0934171845',     'La paz',                   'Perpendicular a la farmacia'),
    (3,   '0981524345',  'escobar amaguaya',   'gladis marina',        '0931371464',     'Av la principal',          'Junto al parque miraflores.'),    
    (4,   '0801556561',  'cando guaman',       'bety elizabeth',       '0945733653',     'AvAntonio Jose de Sucre',  'Frente a la junta parroquial'),
    (1,   '9811238965',  'villarreal panchi',  'andres sebastian',     '0983463237',     'La primera constituyente', 'Junto a la fotocopiadora el copion'),
    (2,   '2345111296',  'naranjo ponluisa',   'andrea paola',         '0973899618',     '11 de noviembre y colon',  'Frente al coliseo'),
    (3,   '0601534284',  'merino achance',     'jennifer alexandra',   '0973822518',     'Primavera',		     	 'Frente al parque Industrial'), 
    (1,   '0601264341',  'delgado velasco',    'fausto rodrigo',       '0979235286',     'Av. Cevallos',             'Junto a la parada de la linea 10'),
    (2,   '1722345567',  'coello gavilanez',   'magali jasmin',        '0932362445',     'La paz',                   'Perpendicular a la farmacia'),
    (3,   '0985552345',  'cardenas guzman',    'daniela milagros',     '0936584444',     'Av la principal',          'Junto al parque miraflores.'),
    (4,   '0802355761',  'bayas cauritongo',   'maria augusta',        '0982457853',     'AvAntonio Jose de Sucre',  'Frente a la junta parroquial'),
    (1,   '9876457765',  'escobar basantes',   'katia maribel',        '0924724737',     'La primera constituyente', 'Junto a la fotocopiadora el copion'),
    (2,   '2345457396',  'palacios casterlo',  'edwin marcelo',        '0978888518',     '11 de noviembre y colon',  'Frente al coliseo'),
    (1,   '9874572865',  'andino najera',      'lorena paulina',       '0983275237',     'La primera constituyente', 'Junto a la fotocopiadora el copion'),    
    (3,   '3245600454',  'fernadez ramos',     'diego alfonso',        '0918886446',     'Av Baños de agua santa',   'Junto a la Hacienda los altares');


INSERT INTO PUBLIC.measurer(sapid,	number) values
  (1,'1'),
  (2,'2'),
  (3,'3'),
  (4,'4'),
  (5,'5'),
  (1,'6'),
  (2,'7'),
  (2,'8'),
  (5,'9'),
  (3,'10'),
  (2,'11'),
  (5,'12'),
  (5,'13'),
  (4,'14'),
  (2,'15'),
  (1,'16'),
  (1,'17'),
  (1,'18'),
  (5,'19'),
  (5,'20'),
  (4,'21'),
  (3,'22');


INSERT INTO PUBLIC.assigned(beneficiaryid,	measurerid) values
  (1,1),
  (1,2),
  (2,3),
  (3,4),
  (4,5),
  (5,6),
  (6,7),
  (7,8),
  (8,9),
  (9,10),
  (10,11),
  (11,12),
  (12,13),
  (13,14),
  (14,15),
  (15,16),
  (16,17),
  (17,18),
  (18,19),
  (19,20),
  (20,21),
  (21,22);

INSERT INTO PUBLIC.uptake(measurerid,currentvaluetaken) values
  (1,45),
  (2,35),
  (3,24),
  (4,15),
  (5,26),
  (6,27),
  (7,18),
  (8,59),
  (9,31),
  (10,19),
  (11,15),
  (12,19),
  (13,33),
  (14,51),
  (15,19),
  (16,15),
  (17,8),
  (18,99),
  (19,20),
  (20,23),
  (21,24),
  (22,32);