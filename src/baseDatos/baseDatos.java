package baseDatos;
import Jugadores.Jugador;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class baseDatos {


    /**
     * insertamos un jugador en la tabla con un id nuevo
     * @param id pedimos un nuevo ID para crear el registro
     * @param nombre pedimos un nombre
     * @return  si se logra insertar el nuevo jugador devolvera un true, caso contrario un false
     */
    public static boolean InsertarJugador2(String id, String nombre){

            try {
                Class.forName("oracle.jdbc.OracleDriver");//Cargar el driver
                // Establecemos la conexion con la BD
                Connection conexion = DriverManager.getConnection
                        ("jdbc:oracle:thin:@localhost:1521:XE", "C##FABRICIO", "2004");
                int partidaJugadas = 0; //todos empiezan con partidas igual a 0
                int partidasGanadas = 0;
                LocalDate fechaCreada = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // cambiamos el formato de la fecha para que oracle lo acepte
                String formatoNuevo = fechaCreada.format(formatter);

                //construir orden INSERT
                String sql = "INSERT INTO tablaproyecto VALUES "
                        + "('" + id + "','" + nombre + "','" + partidaJugadas + "','" + partidasGanadas + "','" + formatoNuevo + "')";
                //System.out.println(sql); si queremos saber que estamos enviando a oracle, imprimimos la orden de insert
                Statement sentencia = conexion.createStatement(); // ejecutamos la consulta en la conexion que hicimos con la tabla de oracle
                int filas = sentencia.executeUpdate(sql);// nos indica la cantidad de filas afectadas
                System.out.println("Filas afectadas: " + filas);
                System.out.println("cuenta creada, por favor recordar ID");
                sentencia.close();         // Cerrar sentencia
                conexion.close();         //Cerrar conexion
                return true;
            }
            catch (SQLIntegrityConstraintViolationException sicv)// indica que si la primary key se repite nos dara una excepcion
            {
                System.out.println("esa ID ya esta registrada");
                System.out.println( " ");
            }

            catch (SQLException | ClassNotFoundException cq) {  // excepciones por si ocurre algun error con el SQL
                cq.printStackTrace();
            }
        return false;
    }



    public static void eliminarJugador(String ID){
        try {
            //Cargar el driver
            Class.forName("oracle.jdbc.OracleDriver");
            // Establecemos la conexion con la BD
            Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "C##FABRICIO", "2004");
            //creamos la sentencia de eliminacion
            String sql = "DELETE FROM tablaproyecto WHERE ID=" + ID;
            System.out.println(sql);
            // Preparamos la sentencia y la ejecutamos
            PreparedStatement sentencia = conexion.prepareStatement(sql);

//obtenemos la cantidad de filas afectadas
            int filas = sentencia.executeUpdate();
            System.out.println("Filas afectadas: "+filas);
            // Cerrar Statement
            sentencia.close();
            //Cerrar conexion
            conexion.close();
        }
        catch (ClassNotFoundException cn) {cn.printStackTrace();}
        catch (SQLException e) {e.printStackTrace();}

    }


    /**
     * actualizamos los datos de un jugador existente
     * @param partidaJ indica la cantidad de partidas jugadas
     * @param partidaG la cantidad de partidas ganadas
     * @param ID necesitamos el ID  para poder encontrar el jugador en la tabla de oracle mediante el where
     */
    public  static void actualizarJugador(int partidaJ , int partidaG,String ID){
        try
        {
            //Cargar el driver
            Class.forName("oracle.jdbc.OracleDriver");
            // Establecemos la conexion con la BD
            Connection conexion = DriverManager.getConnection
                    ("jdbc:oracle:thin:@localhost:1521:XE","C##FABRICIO", "2004");

            //construir orden UPDATE
            String sql= "UPDATE tablaproyecto" +
                    " SET  partidas_jugadas= partidas_jugadas +"+ partidaJ + ", partidas_ganadas=partidas_ganadas+ "+ partidaG+" WHERE ID= '"+ID+ "'";
            System.out.println(sql);
            // Preparamos la sentencia
            PreparedStatement sentencia = conexion.prepareStatement(sql);

            int filas = sentencia.executeUpdate ();// cantidad de filas afectadas
            System.out.println("Filas afectadas: "+filas);

            sentencia.close();
            conexion.close();
        } catch (Exception e) {e.printStackTrace();}    }


    /**
     *
     * @param ID necesitamos el id del jugador para encontrar sus datos
     */
        public static void mostrarDatos(String ID){
        try {
            //Cargar el driver
            Class.forName("oracle.jdbc.OracleDriver");
            // Establecemos la conexion con la BD
            Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","C##FABRICIO", "2004");

            String sql = "select * from tablaproyecto where ID= '" + ID+"'"; //sentencia SQL
           // System.out.println(sql);
            PreparedStatement sentencia=conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery ();// sireve para obtener los datos de una sentencia de SQL
while (resultado.next()) {
    System.out.println("ID: " + resultado.getString("ID") + " , "
            + "NOMBRE:" + resultado.getString("NOMBRE") + " , PARTIDAS JUGADAS: " + resultado.getInt("Partidas_Jugadas") + " , PARTIDAS GANADAS: " + resultado.getInt("partidas_ganadas") + " , Fecha de creacion: " + resultado.getDate("cuenta_creada"));
}
            // Cerrar Statement
            sentencia.close();
            //Cerrar conexion
            conexion.close();
        }
        catch (Exception e){
            System.out.println("no existe un usuario con ese ID");
            e.printStackTrace();
        }



        }

    /**
     * obtenemos el jugador mediante su identificacion
     * @param ID usamos el ID para obtener el jugador en especifico
     * @return  retornamos el jugador
     */
        public  static Jugador obtenerJugador(String ID){
        try {
            String IDguardada;
            String nombre;
            int partidas;
            int ganadas;
            Class.forName("oracle.jdbc.OracleDriver");
            // Establecemos la conexion con la BD
            Connection conexion = DriverManager.getConnection
                    ("jdbc:oracle:thin:@localhost:1521:XE","C##FABRICIO", "2004");
            String sql = "select * from tablaproyecto where ID= '" + ID+ "'";
            //System.out.println(sql);
            PreparedStatement sentencia=conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery ();
            while (resultado.next()){
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
            // Cerrar Statement
            sentencia.close();
            //Cerrar conexion1

            conexion.close();


        }
        catch (Exception e){
                e.printStackTrace();
        }
        return null;
        }

    }


