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
        (id, nombre, email, contraseña, tarifa) VALUES
        (1 , 'Carmen Martínez', 'carmenmartinez@gmail.com' , 'contraseña1', '4 clases'),
        (2 , 'Carlos López', 'carlos.lopez@gmail.com', 'contraseña2','8 clases');


        //falta  ghhj