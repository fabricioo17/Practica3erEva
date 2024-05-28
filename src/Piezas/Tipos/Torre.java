package Piezas.Tipos;

import Piezas.Pieza;
import Piezas.movimientosReutilizables.MovimientoHorizontalVertical;
import Tablero.Tablero;

import java.util.Scanner;

public class Torre extends Pieza implements MovimientoHorizontalVertical {
    public Torre(Boolean blancas, int posicionX, int posicionY) {super(blancas, posicionX,posicionY);
    }

    /**
     * sirve para saber que tipo de movimiento haremos luego de verificar si es posible
     * @param teclado
     * @param table
     * @return devuelve un 0 si el movimiento es correcto, un 1 si come pieza y otro numero si es error
     */
    public int obteneerMovimientoTorre(Scanner teclado, Tablero table) {

        System.out.println("ingrese a que fila quiere mover la torre");
        int x = teclado.nextInt() - 1;
        System.out.println("ingrese la columna");
        int y = teclado.nextInt() - 1;
    int opcionMovimiento=      movimientoVertical(table,x,y,posicionX,posicionY);
    if (opcionMovimiento==0){
        System.out.println("pieza movida");
        return 0;
    }
    else if (opcionMovimiento==1) {
        System.out.println("pieza comida");
        return 0;
    }
    else if (opcionMovimiento==2){
        System.out.println("no puedes comer tu propia pieza");
        return 1;
        }
        else {
        System.out.println("movimiento invalido");
        return 1;
            }
    }

    /**
     * no usaremos este metodo ya que el metodo de proteger rey esta en el interfaz
     * @param tablero
     * @param posicionX
     * @param posicionY
     * @param blanco
     * @return
     */
    @Override
    public boolean protegerRey(Tablero tablero, int posicionX, int posicionY, boolean blanco) {
        return false;
    }


    /**
     * muestra los datos de la torre
     * @return retorna los datos
     */
    public String mostrarDatosTorre(){


        return toString() + ",Torre";
    }

    /**
     * imprime la forma de la torre
     */
    public void imprimirTorre(){

        if (this.roja) {
            System.out.print(red + "♖" + reset);
        }
        else {
            System.out.print(green + "♖" + reset);
        }
    }



}
