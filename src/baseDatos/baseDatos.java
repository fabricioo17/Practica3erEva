package baseDatos;
import Jugadores.Jugador;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class baseDatos {

    public static boolean InsertarJugador2(String id, String nombre){

            try {
                Class.forName("oracle.jdbc.OracleDriver");//Cargar el driver
                // Establecemos la conexion con la BD
                Connection conexion = DriverManager.getConnection
                        ("jdbc:oracle:thin:@localhost:1521:XE", "C##FABRICIO", "2004");
                //recuperar argumentos de main
                int partidaJugadas = 0;
                int partidasGanadas = 0;
                LocalDate fechaCreada = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String formatoNuevo = fechaCreada.format(formatter);

                //construir orden INSERT
                String sql = "INSERT INTO tablaproyecto VALUES "
                        + "('" + id + "','" + nombre + "','" + partidaJugadas + "','" + partidasGanadas + "','" + formatoNuevo + "')";
                System.out.println(sql);
                Statement sentencia = conexion.createStatement();
                int filas = sentencia.executeUpdate(sql);
                System.out.println("Filas afectadas: " + filas);
                System.out.println("cuenta creada, por favor recordar ID");
                sentencia.close();         // Cerrar Statement
                conexion.close();         //Cerrar conexiÃ³n
                return true;
            } catch (SQLIntegrityConstraintViolationException sicv)
            {
                System.out.println("esa ID ya esta registrada");
                System.out.println( " ");
            }

            catch (SQLException | ClassNotFoundException cq) {
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

    }//fin de main


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

            int filas = sentencia.executeUpdate ();
            System.out.println("Filas afectadas: "+filas);
            // Cerrar Statement
            sentencia.close();
            //Cerrar conexion
            conexion.close();
        } catch (Exception e) {e.printStackTrace();}    }


        public static void mostrarDatos(String ID){
        try {
            //Cargar el driver
            Class.forName("oracle.jdbc.OracleDriver");
            // Establecemos la conexion con la BD
            Connection conexion = DriverManager.getConnection
                    ("jdbc:oracle:thin:@localhost:1521:XE","C##FABRICIO", "2004");
            String sql = "select * from tablaproyecto where ID= '" + ID+"'";
            System.out.println(sql);
            PreparedStatement sentencia=conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery ();
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
            e.printStackTrace();
        }



        }


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
            System.out.println(sql);
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

                new Jugador(nombre,IDguardada,ganadas,partidas);
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


