INSERT INTO public.operator(roleid,username, password, email, dni, firstname, lastname, telephone, address) VALUES
       (2,'cobros', '20aabadb6855268577d5a66f0d9346d7','cobros@bamboo.com', '1111111111', 'cobros', 'cobros', '1111111111',''),
       (3,'medidas', '794544c88ec2aa0fdeaa3e7a4fad6454','medidas@bamboo.com', '222222222', 'medidas', 'medidas', '2222222222','');

INSERT INTO village(name) values
  ('Riobamba'),
  ('Guano'),
  ('San Andres'),
  ('Yaruquies');


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
    (3,   '3245677654',  'palmay vargas',      'brigith lisbeth',      '0918872646',     'Av Baños de agua santa',   'Junto a la Hacienda los altares');

INSERT INTO PUBLIC.measurer(sapid,	number) values
  (1,'1'),
  (2,'2'),
  (3,'3'),
  (4,'4'),
  (5,'5'),
  (1,'6'),
  (2,'7'),
  (3,'8');


INSERT INTO PUBLIC.assigned(beneficiaryid,	measurerid) values
  (1,8),
  (2,7),
  (3,6),
  (4,5),
  (5,4),
  (6,3),
  (7,2),
  (1,1);