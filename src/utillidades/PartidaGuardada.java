package utillidades;
import Piezas.*;
import Piezas.Tipos.*;
import Tablero.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class PartidaGuardada {

    static File fichero;
    static  FileWriter sobreEscrbir;
    public static void main(String[] args) {
        Tablero tablero = new Tablero();

cargarPiezas(tablero,"pruebaultimo");



    }

    public static void cargarPartida(Tablero nuevo,String nombre){
        cargarPiezas(nuevo,nombre);
        borrarFichero();
    }


    public static void guardarPiezas(Tablero tablero,String nombre){
        fichero= new File("src/PartidaGuardada"+nombre+".txt");
        Pieza[][] table=tablero.getTable();
        for (int i=0;i<=7;i++){
            for (int j=0;j<=7;j++) {
                if (table[i][j] instanceof Caballo){
                    String contenido = ((Caballo) table[i][j]).mostrarDatos();
                    guardarInformacion(contenido);
                 }
                if (table[i][j] instanceof Rey){
                    String contenido = ((Rey) table[i][j]).mostrarDatosRey();
                    guardarInformacion(contenido);
                }
                if (table[i][j] instanceof Reina){
                    String contenido = ((Reina) table[i][j]).mostrarDatosReina();
                    guardarInformacion(contenido);
                }
                if (table[i][j] instanceof Torre){
                    String contenido = ((Torre) table[i][j]).mostrarDatosTorre();
                    guardarInformacion(contenido);
                }
                if (table[i][j] instanceof Alfil){
                    String contenido = ((Alfil) table[i][j]).mostrarDatosAlfil();
                    guardarInformacion(contenido);
                }
                if (table[i][j] instanceof Peon){
                    String contenido = ((Peon) table[i][j]).mostrarDatosPeon();
                    guardarInformacion(contenido);
                }
            }

        }
    }

    public static void guardarInformacion(String contenido){;
        try {
             sobreEscrbir = new FileWriter(fichero,true);// true append es para no machacar la informacion obtenida
            sobreEscrbir.write(contenido + "\n");
            sobreEscrbir.close();
        } catch (IOException ie) {
            System.out.println("no se logro subir los datos");
        }
    }

    public  static void  borrarDatos(){
        try {
            FileWriter sobreEscrbir = new FileWriter(fichero);// true append es para no machacar la informacion obtenida
            sobreEscrbir.write("");
            sobreEscrbir.close();
        } catch (IOException ie) {
            System.out.println("no se logro borrar");
        }
    }

    public static void cargarPiezas(Tablero tablero, String nombre){
if (confirmarExistenciaFichero(nombre)) {
    fichero= new File("src/PartidaGuardada"+nombre+".txt");
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

    public static void borrarFichero (){

       try {
           Files.delete(fichero.toPath());
       }
       catch (Exception e){
           System.out.println("no se pudo eliminar");
       }

    }

public static boolean confirmarExistenciaFichero(String nombre){
    Path ruta = Path.of("src/PartidaGuardada" + nombre + ".txt"); // transformamos el string en una ruta
        try {
        Files.exists(ruta);
        return true;
        }
        catch (Exception e){
            System.out.println(" no existe esa partida");
        }
        return false;
}







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
