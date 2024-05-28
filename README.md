# Ajedrez

##Descripcion
juego del ajedrez por computadora a base de una tabla y un objeto Pieza que hereda a diferentes piezas.
Tambien incluye una tabla de oracle para reistrar jugadores nuevos y actualizar datos.


**Table of Contents**

[TOCM]

[TOC]


#Uso

###Crear piezas
![](https://www.shutterstock.com/image-vector/set-chess-pieces-hand-drawn-600nw-2176975291.jpg)

####Peon
    Peon peon= newPeon(color: boolean,posicionX: int,posicionY: int);
    

####Caballo 
    Caballo caballo= newCaballo(color: boolean,posicionX: int,posicionY: int);
	
	
	
####Torre 
    Torre torre= newTorre(color: boolean,posicionX: int,posicionY: int);
    

####Alfil
    Alfil alfil= newAlfil(color: boolean,posicionX: int,posicionY: int);
    
####Reina
    Reina reina= newReina(color: boolean,posicionX: int,posicionY: int);
####Rey
    Rey rey= newRey(color: boolean,posicionX: int,posicionY: int);

  



Tabla de registro
-------------

###Descripcion
Enlazaremos nuestro proyecto a una tabla creada en Oracle para el guardado del registro, modificacion y obtencion de datos de cada jugador.


###Conexion:
####Requisitos
- descargar el contralador de oracle
- tener un usuario creado en la base de datos
- Item C
####Conectar la base de datos
				//cargamos el driver
            Class.forName("oracle.jdbc.OracleDriver");
            // Establecemos la conexion con la BD
            Connection conexion = DriverManager.getConnection
            ("jdbc:oracle : thin:@ localhost : 1521:XE","nombre_usuario", "contraseña");

####Forma de uso
####Insertar fila
   						 //construir orden INSERT
                String sql = "INSERT INTO tablaproyecto VALUES "
               + "('" + id + "','" + nombre +"','" +partidaJugadas +
			   "','"partidasGanadas + "','" + formatoNuevo + "')";
			   //ejecutar la orden
                Statement sentencia = conexion.createStatement();
    
####Modificar fila
    	      //construir orden UPDATE
               String sql= "UPDATE tablaproyecto" +
              " SET  partidas_jugadas= partidas_jugadas +"+ partidaJ + ",                                                  partidas_ganadas=partidas_ganadas+ "+ partidaG+" WHERE ID= '"+ID+ "'";
            // Preparamos la sentencia
            PreparedStatement sentencia = conexion.prepareStatement(sql);
####Obtener un jugador
       		String sql = "select * from tablaproyecto where ID= '" + ID+ "'";
           //ejecutamos la consulta
            PreparedStatement sentencia=conexion.prepareStatement(sql);
			//obtenemos el resultado
            ResultSet resultado = sentencia.executeQuery ();
            while (resultado.next()){
			//guardamos la informacion obtenida en variables para crear al jugador
                IDguardada= resultado.getString("ID");
                nombre=resultado.getString("NOMBRE");
                ganadas=resultado.getInt("PARTIDAS_GANADAS");
                partidas=resultado.getInt("PARTIDAS_JUGADAS");
                // Cerrar Statement
                sentencia.close();
                //Cerrar conexion
                conexion.close();

              return   new Jugador(nombre,IDguardada,ganadas,partidas);
            }
####Imprimir un Jugador
     String sql = "select * from tablaproyecto where ID= '" + ID+"'"; //sentencia SQL
           //ejecutamos la consulta
            PreparedStatement sentencia=conexion.prepareStatement(sql);
			;// sirve para obtener los datos de una sentencia de SQL
            ResultSet resultado = sentencia.executeQuery ()
           while (resultado.next()) {
            System.out.println("ID: " + resultado.getString("ID") + " , "
            + "NOMBRE:" + resultado.getString("NOMBRE") + 
			" , PARTIDAS JUGADAS: " + resultado.getInt("Partidas_Jugadas") + 
			" , PARTIDAS GANADAS: " + resultado.getInt("partidas_ganadas") +
			" , Fecha de creacion: " + resultado.getDate("cuenta_creada"));
            }

###End
