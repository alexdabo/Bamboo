INSERT INTO role(name) VALUES
  ('Administración'),
  ('Cobros'),
  ('Medidas');

INSERT INTO status(name) VALUES
  ('Activo'),
  ('Suspendido'),
  ('Dañado');

INSERT INTO public.operator(roleid,username, password, email, dni, firstname, lastname, telephone, address) VALUES
       (1,'admin', '21232f297a57a5a743894a0e4a801fc3','admin@bamboo.com', '0000000000', 'admin', 'admin', '0000000000','');



