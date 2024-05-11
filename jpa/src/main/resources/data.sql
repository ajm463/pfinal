INSERT INTO Clase
    (id, nombre, nivel, capacidad) VALUES
    (1 , 'Pilates Reformer'  , 'Intermedio', 10),
    (2 , 'Pilates SpringBoard', 'Intermedio' , 12),
    (3 , 'Pilates Estiramiento', 'Bajo', 12),
    (4 , 'Pilates Core'       , 'Alto', 12);


INSERT INTO Horario
  (id, clase_id, horario) VALUES
  (1 , 1, 'Lunes 18:00'  ),
  (2 , 1, 'Miércoles 18:00'),
  (3 , 1, 'Viernes 18:00' ),
  (4 , 2, 'Martes 12:00'  ),
  (5 , 2, 'Jueves 12:00'),
  (6 , 3, 'Martes 17:00'   ),
  (7 , 3, 'Jueves 17:00 '  ),
  (8 , 4, 'Lunes 11:00'  ),
  (9 , 4, 'Miercoles 11:00'),
  (10 ,4, 'Viernes 11:00' );

INSERT INTO Usuario
    (nombre, email, contraseña, tarifa, clases_asistidas, clases_quedan) VALUES
    ('Carmen Martínez', 'carmenmartinez@gmail.com' , 'contraseña1', 8, 0, 8),
    ('Carlos López', 'carlos.lopez@gmail.com', 'contraseña2',4, 0, 4);


