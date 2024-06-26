package utillidades;
import Piezas.*;
import Piezas.Tipos.*;
import Tablero.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class PartidaGuardada {

    static File fichero;
    static File ficheroJugadores;
    static  FileWriter sobreEscrbir;


    /**
     * cargamos en el tablero los datos del tablero guardado
     * @param nuevo tablero donde jugaremos la partida guardada
     * @param nombre nombre del archivo que tiene los datos guardados
     */
    public static void cargarPartida(Tablero nuevo,String nombre){
        cargarPiezas(nuevo,nombre);
        borrarFichero(fichero);
    }


    /**
     *cargamos la informacion de jugadores del txt en un hashmap
     * @param nombre nombre del fichero donde guardamos los datos
     * @return devuelve un hashmap donde se guarda el el id de los jugadores con una llave que es o y 1 para saber quien es primero
     */
    public static HashMap cargarInforJugador (String nombre) {

        HashMap <Integer,String>listaInfo =new HashMap<>();
        if (confirmarExistenciaFichero(nombre)) {
            ficheroJugadores = new File("jugadoresGuardados/jugadoresInf" + nombre + ".txt");
            String ID;
            String linea;
            String roja;
            String[] separarLinea;
            String[] separarDato;
            try {
                FileReader lector = new FileReader(ficheroJugadores);
                BufferedReader lectura = new BufferedReader(lector);
                linea = lectura.readLine();
                int i=0;
                while (linea != null) {

                    if (linea.startsWith("turno")){
                        separarLinea = linea.split("=");
                        listaInfo.put(i,separarLinea[1]);//obtenemos el turno en que se guardo la partida
                    }
                    else {

                        //-----------primero la fila de datos las eparamos por ","
                        separarLinea = linea.split(",");

                        //-------------- nos vamos a la parte de color rojo y tambien separamos mediante el "="
                        separarDato = separarLinea[1].split("=");
                        //--------------guardamos el true/false del color-----------//
                       roja=separarDato[1];
                       //-----------------volvemos a la misma  fila y la separamos
                        separarLinea = linea.split(",");
                        //--------------pero esat vez agarramos el primer  dato que sera el ID
                        separarDato = separarLinea[0].split("=");
                        //------------separamos el dato mediante su "="
                            ID=separarDato[1];
                        listaInfo.put(i,ID);
                    }
                    linea = lectura.readLine();

                            i++;
                }
                lector.close();
                borrarFichero(ficheroJugadores);
                return listaInfo;
            } catch (Exception e) {
                System.out.println("no xiste esa partida");
            }

        }
        return null;
    }

    /**
     *guardaremos dentro de un txt la informacion basica de los jugadores en la partida actual
     * @param turno turno donde se quedo la partida guardada
     * @param ID1 identificador 1
     * @param ID2 identificador 2
     * @param nombre nombre dcomo queremos llamar a nuestro archivo
     */
    public static  void guardarInfoJugadores(int turno,  String ID1 , String ID2,String nombre){
    ficheroJugadores= new File("jugadoresGuardados/jugadoresInf"+nombre+".txt");
    String infoTurno="turno="+ turno;
    String infoJugador1="jugador1=" + ID1+ ", rojo=true";
    String infoJugador2="jugador2=" + ID2+ ", rojo=false";
        guardarInformacion(infoJugador1,ficheroJugadores);
        guardarInformacion(infoJugador2,ficheroJugadores);
        guardarInformacion(infoTurno,ficheroJugadores);
}

    /**
     * guardamos cada pieza del tablero en un txt
     * @param tablero tablero actual
     * @param nombre nombre del txt
     */
    public static void guardarPiezas(Tablero tablero,String nombre){
        fichero= new File("partidasGuardadas/PartidaGuardada"+nombre+".txt");
        Pieza[][] table=tablero.getTable();
        for (int i=0;i<=7;i++){
            for (int j=0;j<=7;j++) {
                if (table[i][j] instanceof Caballo){
                    String contenido = ((Caballo) table[i][j]).mostrarDatos();
                    guardarInformacion(contenido,fichero);
                 }
                if (table[i][j] instanceof Rey){
                    String contenido = ((Rey) table[i][j]).mostrarDatosRey();
                    guardarInformacion(contenido,fichero);
                }
                if (table[i][j] instanceof Reina){
                    String contenido = ((Reina) table[i][j]).mostrarDatosReina();
                    guardarInformacion(contenido,fichero);
                }
                if (table[i][j] instanceof Torre){
                    String contenido = ((Torre) table[i][j]).mostrarDatosTorre();
                    guardarInformacion(contenido,fichero);
                }
                if (table[i][j] instanceof Alfil){
                    String contenido = ((Alfil) table[i][j]).mostrarDatosAlfil();
                    guardarInformacion(contenido,fichero);
                }
                if (table[i][j] instanceof Peon){
                    String contenido = ((Peon) table[i][j]).mostrarDatosPeon();
                    guardarInformacion(contenido,fichero);
                }
            }

        }

    }

    /**
     * guarda la informacion dentro de un txt
     * @param contenido es la informacion a guardar
     * @param fichero el fichero txt
     */
    public static void guardarInformacion(String contenido, File fichero){;
        try {
             sobreEscrbir = new FileWriter(fichero,true);// true append es para no machacar la informacion obtenida
            sobreEscrbir.write(contenido + "\n");
            sobreEscrbir.close();
        } catch (IOException ie) {
            System.out.println("no se logro subir los datos");
        }
    }


    /**
     * recuperamos las piezas guardadas en el txt
     * @param tablero tablero actual
     * @param nombre nombre del fichero
     */
    public static void cargarPiezas(Tablero tablero, String nombre){
if (confirmarExistenciaFichero(nombre)) {
    fichero= new File("partidasGuardadas/PartidaGuardada"+nombre+".txt");
    Pieza[][] table = tablero.getTable();
    String tipoPieza;
    String linea;
    String[] separarLinea;
    String[] separarDato;
    int posicionX;
    int posicionY;
    boolean roja;
    try {
        FileReader lector = new FileReader(fichero);
        BufferedReader lectura = new BufferedReader(lector);
        linea = lectura.readLine();
        while (linea != null) {
            separarLinea = linea.split(",");// separamos los datos mediante las comas
            separarDato = separarLinea[0].split("=");
            roja = Boolean.parseBoolean(separarDato[1]);
            separarDato = separarLinea[1].split("="); // ahora separamos en dos el dato de posicionX=" numero" usando como separador el "="
            posicionX = Integer.parseInt(separarDato[1]);//obtenemos la posicionX
            separarDato = separarLinea[2].split("=");
            posicionY = Integer.parseInt(separarDato[1]);//POSICIONY

            tipoPieza = separarLinea[6];//tipo dato
            //----------cargar pieza-------------//
            table[posicionX][posicionY] = cargarPieza(tipoPieza, roja, posicionX, posicionY);
            //--------------leer siguiente linea
            linea = lectura.readLine();
        }


        lector.close();
    } catch (Exception e) {
        System.out.println("no xiste esa partida");
    }
}
    //-------------ahora borrare lap partida guarada ---------- por si quieren volver a guardar



    }

    /**
     * borramos el fichero luego de cargar la partida
     * @param fichero necesitamos el fichero para borrarlo
     */
    public static void borrarFichero (File fichero){

       try {
           Files.delete(fichero.toPath());
       }
       catch (Exception e){
           System.out.println("no se pudo eliminar");
       }

    }

    /**
     * verifica la existencia del fichero
     * @param nombre nombre del fichero
     * @return devuelve un true si es que existe el fichero y un false si es que no
     */
    public static boolean confirmarExistenciaFichero(String nombre){
    Path ruta = Path.of("partidasGuardadas/PartidaGuardada" + nombre + ".txt"); // transformamos el string en una ruta
        try {
        Files.exists(ruta);
        return true;
        }
        catch (Exception e){
            System.out.println(" no existe esa partida");
        }
        return false;
}


    /**
     *  creamos  las piezas con la informacion guardada en el archivo txt
     * @param tipo  tipo de pieza
     * @param rojas color
     * @param possicionX posicion en el eje x
     * @param posicionY POSICION EN EL eje Y
     * @return devuelve la pieza creada
     */
    public static Pieza cargarPieza(String tipo,boolean rojas, int possicionX, int posicionY){
    if (tipo.compareToIgnoreCase("caballo")==0){
            return  new Caballo(rojas,possicionX,posicionY);
    }
    if (tipo.compareToIgnoreCase("torre")==0){
        return  new Torre(rojas,possicionX,posicionY);
    }
    if (tipo.compareToIgnoreCase("rey")==0){
        return  new Rey(rojas,possicionX,posicionY);
    }
    if (tipo.compareToIgnoreCase("alfil")==0){
        return  new Alfil(rojas,possicionX,posicionY);
    }
    if (tipo.compareToIgnoreCase("reina")==0){
        return  new Reina(rojas,possicionX,posicionY);
    }
    if (tipo.compareToIgnoreCase("peon")==0){
        return  new Peon(rojas,possicionX,posicionY);
    }
return null;
}


}
