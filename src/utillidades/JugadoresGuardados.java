package utillidades;

import Jugadores.Jugador;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class JugadoresGuardados {
   static File fichero;
    static  FileWriter sobreEscrbir;
    //-----------------probando--------------------//
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Jugador jugador = new Jugador(teclado);

    }

public static void añadirDatos(  Jugador jugador)  {
        fichero= new File("src/Datos.txt");
        if (verificarExistenciaJugador(jugador.getId())==false) {
                    try {
                        sobreEscrbir = new FileWriter(fichero, true);// true append es para no machacar la informacion obtenida
                        sobreEscrbir.write("ID: " + jugador.getId()  + ", nombre: " + jugador.getNombre() + ", partidasGanadas: " +jugador.getPartidasGanadas() + ", partidasJugadas: " +jugador.getPartidasJugadas() + ", porcentajeGanadas: "+jugador.getPorcentaje()+"%" +"\n");
                        sobreEscrbir.close();
                    }
                    catch (IOException ie) {
                        System.out.println("no se logro subir los datos");
                    }
        }
else {
    System.out.println("ya existe un usuario con ese identificar");
}
    }

    public static  void leerFile(){
        String linea;
        try {
            FileReader lector= new FileReader(fichero);
            BufferedReader lectura= new BufferedReader(lector);
            linea=lectura.readLine();

            while (linea !=null){
                System.out.println(linea);
                linea=lectura.readLine();

            }
            lectura.close();
            lector.close();
        }
        catch (Exception e ){
            System.out.println("no se puede leer los datos");
        }

    }


    //-----------------mostrar datos de un jugador mediante su ID
    public static  void mostrarDatosJugador(String ID){
        String linea;
        try {
            FileReader lector= new FileReader(fichero);
            BufferedReader lectura= new BufferedReader(lector);
            linea=lectura.readLine();

            while (linea !=null){
                if (linea.startsWith("ID: " + ID)){
                    System.out.println(linea);
                break;
                }
                linea=lectura.readLine();

            }
            lectura.close();
            lector.close();
        }
        catch (Exception e ){
            System.out.println("no hay registro de ese jugador");
        }
    }

    public static boolean verificarExistenciaJugador(String ID){
        fichero= new File("src/Datos.txt");
        String linea;
        try {
            FileReader lector= new FileReader(fichero);
            BufferedReader lectura= new BufferedReader(lector);
            linea=lectura.readLine();

            while (linea !=null){
                if (linea.startsWith("ID: " + ID)){
                    return true;
                }
                linea=lectura.readLine();

            }
            lector.close();
            lectura.close();
        }
        catch (Exception e ){
          return false;
        }
        return false;
    }




    //--------------CARGAMOS LOS DATOS DEL TXT EN EL ARRAY PARA LUEGO ESCRBIRLOS EN OTRO TXT--------------//
public  static  ArrayList<String> cargarArrayDatos(){
        if (confirmarExistenciaFichero()) {
            ArrayList<String> listaDatos = new ArrayList<>();
            String linea;
            try {
                FileReader lector = new FileReader(fichero);
                BufferedReader lectura = new BufferedReader(lector);
                linea = lectura.readLine();
                while (linea != null) {
                    listaDatos.add(linea); // vamos creando un aary donde se guarde la informacion de cada usuario
                    linea = lectura.readLine();
                }
                lector.close();
                lectura.close();
                return listaDatos;
            } catch (Exception ignored) {
            }
        }
return null;
}
public static void modificarUsuario(Jugador jugador){
    ArrayList<String> listaDatos=  cargarArrayDatos();
    if (listaDatos!=null){
    sobreescrbirPartidaJugada(listaDatos,jugador);
    borrarFichero();
guardarJugadoresActualizados(listaDatos);}
}



//----------------------------aumentaremos uno a las partdias jugadas--------------------//
public static void sobreescrbirPartidaJugada(ArrayList<String> listaDatos, Jugador jugador){
    for (int i =0; i<listaDatos.size();i++){
        if (listaDatos.get(i).startsWith("ID: " + jugador.getId()))
        {
            listaDatos.set(i,"ID: " + jugador.getId()  + ", nombre: " + jugador.getNombre() + ", partidasGanadas: " +jugador.getPartidasGanadas() + ", partidasJugadas: " + jugador.aumentarPartidaJugada()+  ", porcentajeGanadas: "+jugador.getPorcentaje()+"%" +"\n");
        }
    }
}


public static void guardarJugadoresActualizados(ArrayList<String> listaDatos){
    fichero= new File("src/Datos.txt");
    try {
        sobreEscrbir = new FileWriter(fichero,true);// true append es para no machacar la informacion obtenida
        for (String linea : listaDatos) {
            sobreEscrbir.write(linea);
        }
        sobreEscrbir.close();
    }
    catch (IOException ie) {
        System.out.println("no se logro sobreescrbir los datos");
    }
}
    public static void borrarFichero (){
        System.out.println(fichero);
        try {
            Files.delete(fichero.toPath());
        }
        catch (Exception e){
            System.out.println("no se pudo eliminar");
            e.printStackTrace();
        }

    }



    public static Jugador obtenerJugador(String ID){
        String [] separarLinea;
        String linea;
        String [] separaDato;
        String [] separadorPorcentaje;
        String nombre;
        int partidasGanadas;
        int partidasJugadas;
        double porcentaje;


        try {
            FileReader lector= new FileReader(fichero);
            BufferedReader lectura= new BufferedReader(lector);
            linea=lectura.readLine();

            while (linea !=null){
                if (linea.startsWith("ID: " + ID)){
                    separarLinea=linea.split(",");// separamos en trozos la linea usando de divisor la ","
                    separaDato=separarLinea[1].split(": "); // ahora separaremos cada pedazos creado dividiendolo con ":"
                    nombre= separaDato[1];
                    separaDato=separarLinea[2].split(": ");
                    partidasGanadas= Integer.parseInt(separaDato[1]);
                    separaDato=separarLinea[3].split(": ");
                    partidasJugadas= Integer.parseInt(separaDato[1]);
                    separaDato=separarLinea[4].split(": ");
                    separadorPorcentaje=separaDato[1].split("%");
                    porcentaje= Double.parseDouble(separadorPorcentaje[0]);
                    lector.close();
                    lectura.close();
                    return new Jugador(nombre,ID,partidasGanadas,partidasJugadas);
                }
                linea=lectura.readLine();
            }
            lector.close();
            lectura.close();
        }
        catch (Exception e ){
            return null;
        }
        return null;
    }

    
    public static boolean confirmarExistenciaFichero(){
        Path ruta = Path.of(fichero.getPath()); // transformamos el string en una ruta
        try {
            Files.exists(ruta);
            return true;
        }
        catch (Exception e){
            System.out.println(" no existe esa partida");
        }
        return false;
    }
}
