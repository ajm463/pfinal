INSERT INTO Clase
    (nombre, nivel, capacidad, plazasDisponibles) VALUES
    ('Pilates Reformer'  , 'Intermedio', 10, 10),
    ('Pilates SpringBoard', 'Intermedio' , 12, 12),
    ('Pilates Estiramiento', 'Bajo', 12, 12),
    ('Pilates Core'       , 'Alto', 12, 12);


INSERT INTO Horario (clase_id, horario, dia) VALUES
  (1, '18:00:00', 'Lunes'),
  (1, '18:00:00', 'Miércoles'),
  (1, '18:00:00', 'Viernes'),
  (2, '12:00:00', 'Martes'),
  (2, '12:00:00', 'Jueves'),
  (3, '17:00:00', 'Martes'),
  (3, '17:00:00', 'Jueves'),
  (4, '11:00:00', 'Lunes'),
  (4, '11:00:00', 'Miercoles'),
  (4, '11:00:00', 'Viernes');


INSERT INTO Usuario
    (nombre, email, contrasena, tarifa, clases_asistidas, clases_quedan) VALUES
    ('Carmen Martínez', 'carmenmartinez@gmail.com' , 'contraseña1', 8, 0, 8),
    ('Carlos López', 'carlos.lopez@gmail.com', 'contraseña2',4, 0, 4);


