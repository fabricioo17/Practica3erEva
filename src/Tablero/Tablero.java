package Tablero;
import utillidades.*;
import Piezas.Pieza;
import Piezas.Tipos.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Tablero {
    private Pieza[][] table;
    public Tablero()
    {
        table = new Pieza[8][8];
    }
    public Tablero(int x , int y ) {table= new Pieza[x][y];}//lo usareso en el tutorial
    public Pieza[][] getTable() {
        return table;
    }
    public void setTable(Pieza[][] table) {
        this.table = table;
    }


    /**
     * juntamos todos los metodos dentro de esta clase para formar el juego completo
     * @param teclado  recibe los datos introducidos por teclado
     * @param IDjugador1 el ID del jugagor numero 1
     * @param IDjugador2 el ID del ugador numero 2
     * @param turnoActual este valor es para indicar el turno de cada persona
     */
    public  void  play(Scanner teclado, String IDjugador1, String IDjugador2, int turnoActual){
int jugadorActual=turnoActual;
        int jaqueMate  ;
        boolean ganador;
        int salir2 = 0;
      do {
              imprimirTablero();
              jaqueMate= jugarPlayer(teclado, jugadorActual);// SIGUIENTE JUGADOR HACE REFERENCIA AL ACTUAL SI ES MULTIPLO DE 0 ES COLOR ROJO
                        if (jaqueMate==1 )
                        { //si es igual a 1 es jaquemate a la pieza actual
                            salir2 = 1;
                            break;
                        }
          System.out.println(" ");
          imprimirTablero();
            if (detenerJuego(teclado)){
                jugadorActual++;
                System.out.println("ingresa el nombre con la que la quieres guardar");
                String nombre=teclado.next();
                PartidaGuardada.guardarPiezas(this,nombre);
                PartidaGuardada.guardarInfoJugadores(jugadorActual,IDjugador1,IDjugador2,nombre);
                break;
            }
            jugadorActual++;

      }
      while (salir2 != 1);

        if (salir2==1) {
                if (jugadorActual % 2 == 0) {// si sobra 1 ganara el color rojo porque luego de hallar al ganador se aumenta +1 , osea si gana blancas que es multiplo de 2 antes qde acabar el bucle se aumenta 1
                    ganador = false;
                } else {
                    ganador = true;

                }
            mostrarGanador(ganador,IDjugador1,IDjugador2);

        }

      }

      public void mostrarGanador(boolean rojas, String IDjugador1, String IDjugador2){
        if (rojas){
            System.out.println("jugador 1 ganaste");
            baseDatos.baseDatos.actualizarJugador(1,1,IDjugador1);
            baseDatos.baseDatos.actualizarJugador(1,0,IDjugador2);
        }
        else {
            System.out.println("jugador 2 ganaste");
            baseDatos.baseDatos.actualizarJugador(1,1,IDjugador2);
            baseDatos.baseDatos.actualizarJugador(1,0,IDjugador1);
        }
      }


    /**
     *
     */
    public void  startTablero(){
        table[0][0] = new Torre(true,0,0) ;
       table[0][1]=new Caballo(true,0,1);
        table[0][2]=new Alfil(true,0,2);
        table[0][3] = new Rey(true,0,3) ;
        table[0][4]=new Reina(true,0,4);
        table[0][5]=new Alfil(true,0,5);
        table[0][6]=new Caballo(true,0,6);
        table[0][7] = new Torre(true,0,7) ;


        for (int i =1;i<2;i++){
            for (int j=0; j<8;j++){
                table[i][j]=new Peon(true,i,j);
            }
        }

        for (int i =2;i<6;i++){
            for (int j=0; j<8;j++){
                table[i][j]=null;
            }
        }
        for (int i =6;i<7;i++){
            for (int j=0; j<8;j++){
                table[i][j]=new Peon(false,i,j);
            }
        }

        table[7][0] = new Torre(false,7,0) ;
        table[7][1]=new Caballo(false,7,1);
        table[7][2]=new Alfil(false,7,2);
        table[7][3] = new Rey(false,7,3) ;
        table[7][4]=new Reina(false,7,4);
        table[7][5]=new Alfil(false,7,5);
        table[7][6]=new Caballo(false,7,6);
        table[7][7] = new Torre(false,7,7) ;

    }


    public void imprimirTablero(){
        System.out.print(" ");
        for (int i = 0; i < 8; i++) {
            System.out.print("      " + (i + 1) + "   ");
        }
        System.out.println();
        dibujarLineas();
        diferenciarContenidoTablero();
    }


    public void diferenciarContenidoTablero(){

        for (int i = 0; i < table.length; i++) {
            System.out.print((i + 1) + "  ┃");
            for (int j = 0; j < 8; j++) {
                if (table[i][j]==null){
                    System.out.print("  " + "  " + "   "); // cantidad de rayas por numero es 3
                    System.out.print(" ┃");
                }
                if(table[i][j] instanceof Peon){
                    System.out.print( "   ");
                    ((Peon) table[i][j]).imprimirPeon();
                    System.out.print("   ");
                    System.out.print("┃");
                }
                else if (table[i][j] instanceof Rey){
                    System.out.print( "   ");
                    ((Rey) table[i][j]).imprimirRey();
                    System.out.print( "   ");
                    System.out.print("┃");
                }
                else if (table[i][j] instanceof Reina){
                    System.out.print( "   ");
                    ((Reina) table[i][j]).imprimirReina();
                    System.out.print( "   ");
                    System.out.print("┃");
                }
                else if (table[i][j] instanceof Caballo){
                    System.out.print("   " );
                    ((Caballo) table[i][j]).imprimirCaballo();
                    System.out.print("   ");
                    System.out.print("┃");
                }
                else if (table [i][j]instanceof  Torre){
                    System.out.print("   ");
                    ((Torre) table[i][j]).imprimirTorre();
                    System.out.print("   " );
                    System.out.print("┃");
                }
                else if (table [i][j]instanceof  Alfil){
                    System.out.print("   ");
                    ((Alfil) table[i][j]).imprimirAlfil();
                    System.out.print("   " );
                    System.out.print("┃");
                }
            }
            System.out.println();
            dibujarLineas();
        }
    }


    /**
     *
     * @param teclado teclado donde recibiremos los datos introducidos por el jugador
     * @param numJugador  valor para saber el turno del jugador
     * @return si devuelve un 1 es porque el jugador actual tiene ajque mate
     */
    public int jugarPlayer(Scanner teclado, int numJugador) {
        boolean roja;
        if (numJugador%2 ==0){
            roja=true;
        }
        else {
            roja=false;
        }
      double contador=0;// si todas las piezas restantes devuelven falso aumentara en 1
      int piezasRestantes= contarPiezasRestantes(roja)-1; // si el contador es igual a las piezas restantes es jaque mate

        int num;
        if (obtenerPiezaReyBlanco(roja).reySinMovimiento(this, roja)) {
            for (int i=0; i<=7;i++){
                for (int j = 0 ; j<=7;j++){
                   if (table[i][j] != null && table[i][j].isRoja()==roja){
                      if (table[i][j] instanceof Torre){
                          if (((Torre) table[i][j]).protegerReyTorre(this, table[i][j].getPosicionX() ,table[i][j].getPosicionY(),roja)== false){
                                contador++;
                          }
                      }
                       if (table[i][j] instanceof Reina) {
                       if (((Reina) table[i][j]).protegerReyTorre(this, table[i][j].getPosicionX(), table[i][j].getPosicionY(), roja) == false) {
                               contador=contador+ 0.5;
                           }

                           if ( ((Reina) table[i][j]).protegerReyAlfil(this,table[i][j].getPosicionX(),table[i][j].getPosicionY(),roja) ==false){
                               contador=contador+ 0.5;
                           }

                       }
                       if (table[i][j] instanceof Caballo){
                           if (( table[i][j]).protegerRey(this, table[i][j].getPosicionX() ,table[i][j].getPosicionY(),roja)== false){
                               contador++;
                           }
                       }
                       if (table[i][j] instanceof Alfil){
                           if ( ((Alfil) table[i][j]).protegerReyAlfil(this,table[i][j].getPosicionX(),table[i][j].getPosicionY(),roja) ==false){
                               contador++;
                           }
                       }
                       if (table[i][j] instanceof Peon){
                           if ( ((Peon) table[i][j]).protegerRey(this,table[i][j].getPosicionX(),table[i][j].getPosicionY(),roja) ==false){
                               contador++;
                           }
                       }
                   }
                }
            }
            if (contador== piezasRestantes || (contador==0 && piezasRestantes==0)){
                return 1;//SI DEVUELVE UN 1 SIGNFICA QUE EL JUGADOR ACTUAL ESTA EN JAQUE MATE
            }
        }

        Boolean valorValido=false;
            boolean correcto = false;
            while (valorValido==false) {
                try {


                    do {
                        System.out.print(" jugador  ");
                        if (roja == true) {
                            System.out.println("rojo");
                        } else {
                            System.out.println("verde");
                        }
                        System.out.println("ingrese las coordenadas horizontal");
                        int posicionX = teclado.nextInt() - 1;
                        System.out.println("ingrese la coordenad vertical");
                        int posicionY = teclado.nextInt() - 1;
                        if (table[posicionX][posicionY] == null) {
                            System.out.print(" no hay pieza elegida  "); // cantidad de rayas por numero es 3
                            System.out.println("vuelva a ingresar las coordenadas");

                        } else {

                            if (table[posicionX][posicionY].isRoja() != roja) {
                                System.out.println(" esa pieza no es tuya");
                            } else {

                                if (table[posicionX][posicionY] instanceof Peon) {
                                    table[posicionX][posicionY].setPosicionX(posicionX);
                                    table[posicionX][posicionY].setPosicionY(posicionY);
                                    num = ((Peon) table[posicionX][posicionY]).movimientoPeon(teclado, this);
                                    if (num == 0) {
                                        correcto = true;valorValido=true;
                                    }
                                } else if (table[posicionX][posicionY] instanceof Rey) {
                                    num = ((Rey) table[posicionX][posicionY]).elegirMovimiento(teclado, this);
                                    if (num == 0) {
                                        correcto = true;valorValido=true;
                                    }

                                } else if (table[posicionX][posicionY] instanceof Reina) {
                                    table[posicionX][posicionY].setPosicionX(posicionX);
                                    table[posicionX][posicionY].setPosicionY(posicionY);
                                    num = ((Reina) table[posicionX][posicionY]).movimientoReina(teclado, this);
                                    if (num == 0) {
                                        correcto = true;valorValido=true;
                                    }
                                } else if (table[posicionX][posicionY] instanceof Caballo) {
                                    num = ((Caballo) table[posicionX][posicionY]).obtenerMovientoCaballo(teclado, this);
                                    if (num == 0) {
                                        correcto = true;valorValido=true;
                                    }
                                } else if (table[posicionX][posicionY] instanceof Torre) {

                                    num = ((Torre) table[posicionX][posicionY]).obteneerMovimientoTorre(teclado, this);

                                    if (num == 0) {
                                        correcto = true;valorValido=true;
                                    }
                                } else if (table[posicionX][posicionY] instanceof Alfil) {
                                    table[posicionX][posicionY].setPosicionX(posicionX);
                                    table[posicionX][posicionY].setPosicionY(posicionY);
                                    num = ((Alfil) table[posicionX][posicionY]).movimientoAlfil(teclado, this);
                                    if (num == 0) {
                                        correcto = true;
                                        valorValido=true;
                                    }
                                }
                            }
                        }
                    }
                    while (correcto == false);
                } catch (ArrayIndexOutOfBoundsException |InputMismatchException e) {
                    System.out.println("ingresa un valor valido");
                    teclado=new Scanner(System.in);
                }
            }
            return 0;
    }





public Pieza obtenerPieza(int posicionX, int posicionY){

         return table[posicionX][posicionY];
}

public Rey obtenerPiezaReyBlanco(Boolean blanco){
        for (int i = 0 ; i< 8;i++){
            for (int j =0; j<8;j++){
                if (table[i][j] instanceof  Rey  && table[i][j].isRoja()== blanco){
                    return (Rey) table[i][j];
                }

            }
    }
        return null;
}

    public void dibujarLineas() {
        int n = ((12) + ((7) * 9));//table.length es 10
        System.out.print("    ");
        for (int k = 1; k <= n; k++) {
            System.out.print("─");
        }
        System.out.println();
    }


    //----------------------contar piezas-------------------------------//

    /**
     * contara las piezas restantes de un color
     * @param roja color que queramos de pieza
     * @return devuelve la cantidad de piezas encontradas
     */
    public int contarPiezasRestantes(boolean roja){
       int rojas=0;
       int verdes =0;
        for (int i =0;i<=7;i++){
            for (int j=0 ; j<=7;j++) {
                if (table[i][j] != null) {
                    if (table[i][j].isRoja()) {
                        rojas++;
                    } else {
                        verdes++;
                    }
                }
            }
        }
        if (roja == true){
            return rojas;
        }
        else {
            return verdes;
        }
    }


    //--------------------------------detener juego---------------------------------------//
public boolean detenerJuego(Scanner teclado){
    System.out.println("quieren detener el juego y luego continuar?");
    String respuesta =teclado.next();
    if (respuesta.toLowerCase().contains("s")){
        return true;

    }
    return false;
}


    @Override
    public String toString() {
        return "Tablero{" +
                "table=" + Arrays.toString(table) +
                '}';
    }
}
