package Piezas.Tipos;

import Piezas.Pieza;
import Tablero.Tablero;

import java.util.Scanner;

public class Caballo extends Pieza {

    /**
     *
     * @param roja color de la pieza
     * @param posicionX posicion en el eje X
     * @param posicionY posicion en el eje Y
     */
    public Caballo(Boolean roja, int posicionX, int posicionY) {
        super(roja,posicionX,posicionY);
    }


    /**
     * nos muestra si el movimiento del caballo es correcto o no
     * @param teclado parametro para ingresar los datos mediante teclado
     * @param tablero tablero donde jugamos
     * @return devuelve 0 si el movimeinto es correcto y 1 si no lo es
     */
    public  int obtenerMovientoCaballo(Scanner teclado, Tablero tablero){
        System.out.println("ingrese a que fila quiere mover el caballo");
        int x = teclado.nextInt()-1;
        System.out.println("ingrese la columna");
        int y = teclado.nextInt()-1;
        int opcionMovimiento= moverCaballo(tablero,x,y);
        if (opcionMovimiento==0){
            System.out.println("pieza movida");
            return 0;
        } else if (opcionMovimiento==1) {
            System.out.println("pieza comida");
            return 0;
        }
        else {
            System.out.println("movimiento invalido");
            return 1;
        }
    }


    /**
     *vemos si los valores de moviemiento concuerda con las restricciones del caballo
     * @param tablero
     * @param movimientoX
     * @param movimientoY
     * @return devuelve 0 si el movieminto es correcto , devuelve 1 si logro comer una pieza y otro nuemro si es incorrecto el moviemiento
     */
    public int moverCaballo(Tablero tablero, int movimientoX, int movimientoY) {
        // aqui no hay una forma de poder simplificar el movieminto del caballo por ello debes tomar cada caso y analizarlo
        Pieza[][] table = tablero.getTable();
        int posicionOriginalX= posicionX;
        int posicionOriginalY=posicionY;
        Rey rey = tablero.obtenerPiezaReyBlanco(table[posicionOriginalX][posicionOriginalY].isRoja());
        Pieza piezaComida;

         // arriba derecha lejos
            if (posicionX - 2==movimientoX && posicionY+1== movimientoY){
                if (table[movimientoX][movimientoY] ==null) {
                    cambiarPosicion(tablero,movimientoX,movimientoY);
                    if (confirmarJaque(tablero,rey.getPosicionX(),rey.getPosicionY())){
                        regresarMovimiento(tablero,posicionOriginalX,posicionOriginalY);
                        return 3;
                    }
                    return  0;
                }
                if (table[movimientoX][movimientoY] != null &&table[movimientoX][movimientoY].isRoja()!= roja){
                    piezaComida=tablero.obtenerPieza(movimientoX,movimientoY);
                    cambiarPosicionPieza(tablero,movimientoX,movimientoY);
                    if (confirmarJaque(tablero,rey.getPosicionX(),rey.getPosicionY())){
                        regresarPiezaComida(tablero,posicionOriginalX,posicionOriginalY,piezaComida);
                        return 3;
                    }
                    return 1;
                }
            }

        //arriba izquierda

            if (posicionX - 2== movimientoX && posicionY - 1== movimientoY) {
                if (table[movimientoX][movimientoY] == null) {
                    cambiarPosicion(tablero,movimientoX,movimientoY);
                    if (confirmarJaque(tablero,rey.getPosicionX(),rey.getPosicionY())){
                        regresarMovimiento(tablero,posicionOriginalX,posicionOriginalY);
                        return 3;
                    }
                    return  0;
                }
                if (table[posicionX - 2][posicionY - 1] instanceof Pieza  &&table[movimientoX][movimientoY].isRoja()!= roja){
                    piezaComida=tablero.obtenerPieza(movimientoX,movimientoY);
                    cambiarPosicionPieza(tablero,movimientoX,movimientoY);
                    if (confirmarJaque(tablero,rey.getPosicionX(),rey.getPosicionY())){
                        regresarPiezaComida(tablero,posicionOriginalX,posicionOriginalY,piezaComida);
                        return 3;
                    }
                    return 1;
                }
            }





            //  derecha arriba
            if (posicionX - 1== movimientoX && posicionY + 2== movimientoY) {
                if (table[movimientoX][movimientoY] ==null) {
                    cambiarPosicion(tablero,movimientoX,movimientoY);
                    if (confirmarJaque(tablero,rey.getPosicionX(),rey.getPosicionY())){
                        regresarMovimiento(tablero,posicionOriginalX,posicionOriginalY);
                        return 3;
                    }
                    return  0;
                }
                if (table[posicionX -1][posicionY + 2] instanceof Pieza  &&table[movimientoX][movimientoY].isRoja()!= roja){
                    piezaComida=tablero.obtenerPieza(movimientoX,movimientoY);
                    cambiarPosicionPieza(tablero,movimientoX,movimientoY);
                    if (confirmarJaque(tablero,rey.getPosicionX(),rey.getPosicionY())){
                        regresarPiezaComida(tablero,posicionOriginalX,posicionOriginalY,piezaComida);
                        return 3;
                    }
                    return 1;
                }
            }


        //izquierda arriba

            if (posicionX - 1== movimientoX&&posicionY - 2== movimientoY) {
                if (table[movimientoX][movimientoY] ==null) {
                    cambiarPosicion(tablero,movimientoX,movimientoY);
                    if (confirmarJaque(tablero,rey.getPosicionX(),rey.getPosicionY())){
                        regresarMovimiento(tablero,posicionOriginalX,posicionOriginalY);
                        return 3;
                    }
                    return  0;
                }
                if (table[posicionX -1][posicionY - 2] instanceof Pieza  &&table[movimientoX][movimientoY].isRoja()!= roja){
                    piezaComida=tablero.obtenerPieza(movimientoX,movimientoY);
                    cambiarPosicionPieza(tablero,movimientoX,movimientoY);
                    if (confirmarJaque(tablero,rey.getPosicionX(),rey.getPosicionY())){
                        regresarPiezaComida(tablero,posicionOriginalX,posicionOriginalY,piezaComida);
                        return 3;
                    }
                    return 1;
                }
            }




            //abajo derecha
            if (posicionX + 2==movimientoX &&posicionY + 1==movimientoY)  {
                if (table[movimientoX][movimientoY] == null) {
                    cambiarPosicion(tablero,movimientoX,movimientoY);
                    if (confirmarJaque(tablero,rey.getPosicionX(),rey.getPosicionY())){
                        regresarMovimiento(tablero,posicionOriginalX,posicionOriginalY);
                        return 3;
                    }
                    return  0;
                }
                if (table[posicionX + 2][posicionY + 1] != null  &&table[movimientoX][movimientoY].isRoja()!= roja){
                    piezaComida=tablero.obtenerPieza(movimientoX,movimientoY);
                   cambiarPosicionPieza(tablero,movimientoX,movimientoY);
                   if (confirmarJaque(tablero,rey.getPosicionX(),rey.getPosicionY())){
                       regresarPiezaComida(tablero,posicionOriginalX,posicionOriginalY,piezaComida);
                       return 3;
                   }
                    return 1;
                }
            }


            //abajo izquierda
            if (posicionX + 2 == movimientoX&& posicionY - 1== movimientoY ) {
                if (table[movimientoX][movimientoY]== null) {
                    cambiarPosicion(tablero,movimientoX,movimientoY);
                    if (confirmarJaque(tablero,rey.getPosicionX(),rey.getPosicionY())){
                        regresarMovimiento(tablero,posicionOriginalX,posicionOriginalY);
                        return 3;
                    }
                    return  0;
                }
                if (table[posicionX + 2][posicionY - 1] != null  &&table[movimientoX][movimientoY].isRoja()!= roja){
                    piezaComida=tablero.obtenerPieza(movimientoX,movimientoY);
                    cambiarPosicionPieza(tablero,movimientoX,movimientoY);
                    if (confirmarJaque(tablero,rey.getPosicionX(),rey.getPosicionY())){
                        regresarPiezaComida(tablero,posicionOriginalX,posicionOriginalY,piezaComida);
                        return 3;
                    }
                    return 1;
                }
            }


            // derecha abajo
            if (posicionX + 1== movimientoX &&posicionY + 2== movimientoY) {
                if (table[movimientoX][movimientoY] ==null) {
                    cambiarPosicion(tablero,movimientoX,movimientoY);
                    if (confirmarJaque(tablero,rey.getPosicionX(),rey.getPosicionY())){
                        regresarMovimiento(tablero,posicionOriginalX,posicionOriginalY);
                        return 3;
                    }
                    return  0;
                }


                if (table[posicionX +1][posicionY + 2] instanceof Pieza  && table[movimientoX][movimientoY].isRoja()!= roja){
                    piezaComida=tablero.obtenerPieza(movimientoX,movimientoY);
                    cambiarPosicionPieza(tablero,movimientoX,movimientoY);
                    if (confirmarJaque(tablero,rey.getPosicionX(),rey.getPosicionY())){
                        regresarPiezaComida(tablero,posicionOriginalX,posicionOriginalY,piezaComida);
                        return 3;
                    }
                    return 1;
                }
            }



            //izquierda abajo
            if (posicionX + 1== movimientoX && posicionY - 2== movimientoY) {
                if (table[movimientoX][movimientoY] == null) {
                    cambiarPosicion(tablero,movimientoX,movimientoY);
                    if (confirmarJaque(tablero,rey.getPosicionX(),rey.getPosicionY())){
                        regresarMovimiento(tablero,posicionOriginalX,posicionOriginalY);
                        return 3;
                    }
                    return  0;
                }
                if (table[posicionX +1][posicionY - 2] instanceof Pieza &&table[movimientoX][movimientoY].isRoja()!= roja){
                    piezaComida=tablero.obtenerPieza(movimientoX,movimientoY);
                    cambiarPosicionPieza(tablero,movimientoX,movimientoY);
                    if (confirmarJaque(tablero,rey.getPosicionX(),rey.getPosicionY())){
                        regresarPiezaComida(tablero,posicionOriginalX,posicionOriginalY,piezaComida);
                        return 3;
                    }
                    return 1;
                }
            }
            return 2;
        }
//-------------------proteger rey---------------------------------------------//


    /**
     *hacemos posibles movimeintos para lograr proteger al rey
     * @param tablero  tablero donde estamos jugando
     * @param posicionX  posicon en el eje X
     * @param posicionY posicion en el eje Y
     * @param roja color de la pieza es roja (true) o verde (false
     * @return devuelve un true si logramos proteger al rey con algun posible movimiento y false si no podemos
     */
    @Override
    public boolean protegerRey(Tablero tablero, int posicionX, int posicionY, boolean roja) {
        Pieza piezaComida;
        Rey rey = tablero.obtenerPiezaReyBlanco(roja);
        Pieza[][] table = tablero.getTable();
        // obtenemos el rey para que en cada movieminto veamos si podemos proteger al rey
        int posicionOriginalX = posicionX;
        int posicionOriginalY = posicionY;
        // arriba lejos derecha
if (posicionX - 2 >=0 && posicionY+1<=7) {
    if ((table[posicionX - 2][posicionY + 1] == null)) {
        cambiarPosicion(tablero, posicionX - 2, posicionY + 1);
        if (confirmarJaque(tablero, rey.getPosicionX(), rey.getPosicionY()) == false) {
            regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);
            return true;
        }
        regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);
    } else {
        if (table[posicionX - 2][posicionY + 1].isRoja() != this.roja) {
            piezaComida = tablero.obtenerPieza(posicionX - 2, posicionY + 1);
            cambiarPosicionPieza(tablero, posicionX - 2, posicionY + 1);
            if (confirmarJaque(tablero, rey.getPosicionX(), rey.getPosicionY())==false) {
                regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, piezaComida);
                return true;
            }
            regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, piezaComida);
        }
    }

}
        //arriba lejos izquierda

        if ((posicionX-2>=0 && posicionY-1>=0)) {
                        if (table[posicionX - 2][posicionY - 1] == null) {
                            cambiarPosicion(tablero, posicionX - 2, posicionY - 1);
                            if (confirmarJaque(tablero, rey.getPosicionX(), rey.getPosicionY())==false) {
                                regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);
                                return true;
                            }
                            regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);
                        } else {
                            if (table[posicionX - 2][posicionY - 1].isRoja() != this.roja) {
                                piezaComida = tablero.obtenerPieza(posicionX - 2, posicionY - 1);
                                cambiarPosicionPieza(tablero, posicionX - 2, posicionY - 1);
                                if (confirmarJaque(tablero, rey.getPosicionX(), rey.getPosicionY())==false) {
                                    regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, piezaComida);
                                    return true;
                                }
                                regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, piezaComida);
                            }
                        }
            }


        //  derecha lejos arriba
        if ((posicionX - 1 >=0 && posicionY+2<=7)) {
            if (table[posicionX - 1][posicionY + 2] == null) {
                cambiarPosicion(tablero, posicionX - 1, posicionY + 2);
                if (confirmarJaque(tablero, rey.getPosicionX(), rey.getPosicionY())==false) {
                    regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);
                    return true;
                }
                regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);
            } else {
                if (table[posicionX - 1][posicionY + 2].isRoja() != this.roja) {
                    piezaComida = tablero.obtenerPieza(posicionX - 1, posicionY + 2);
                    cambiarPosicionPieza(tablero, posicionX - 1, posicionY + 2);
                    if (confirmarJaque(tablero, rey.getPosicionX(), rey.getPosicionY())==false) {
                        regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, piezaComida);
                        return true;
                    }
                    regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, piezaComida);
                }
            }

        }
        //izquierda lejos arriba

        if ((posicionX - 1 >=0 && posicionY-2>=0)) {
            if (table[posicionX - 1][posicionY - 2] == null) {
                cambiarPosicion(tablero, posicionX - 1, posicionY - 2);
                if (confirmarJaque(tablero, rey.getPosicionX(), rey.getPosicionY())==false) {
                    regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);
                    return true;
                }
                regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);

            } else {
                if (table[posicionX - 1][posicionY - 2].isRoja() != this.roja) {
                    piezaComida = tablero.obtenerPieza(posicionX - 1, posicionY - 2);
                    cambiarPosicionPieza(tablero, posicionX - 1, posicionY - 2);
                    if (confirmarJaque(tablero, rey.getPosicionX(), rey.getPosicionY())==false) {
                        regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, piezaComida);
                        return true;
                    }
                    regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, piezaComida);
                }
            }

        }


        //abajo lejos derecha
        if ((posicionX +2<=7 && posicionY+1<=7)) {
            if (table[posicionX + 2][posicionY + 1] == null) {
                cambiarPosicion(tablero, posicionX + 2, posicionY + 1);
                if (confirmarJaque(tablero, rey.getPosicionX(), rey.getPosicionY())==false) {
                    regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);
                    return true;
                }
                regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);
            } else {
                if (table[posicionX + 2][posicionY + 1].isRoja() != this.roja) {
                    piezaComida = tablero.obtenerPieza(posicionX + 2, posicionY + 1);
                    cambiarPosicionPieza(tablero, posicionX + 2, posicionY + 1);
                    if (confirmarJaque(tablero, rey.getPosicionX(), rey.getPosicionY())==false) {
                        regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, piezaComida);
                        return true;
                    }
                    regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, piezaComida);
                }
            }
        }

        //abajo lejos izquierda
        if ((posicionX +2 <=7 && posicionY-1>=0)) {
            if (table[posicionX + 2][posicionY - 1] == null) {
                cambiarPosicion(tablero, posicionX + 2, posicionY - 1);
                if (confirmarJaque(tablero, rey.getPosicionX(), rey.getPosicionY())==false) {
                    regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);
                    return true;
                }
                regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);
            } else {
                if (table[posicionX + 2][posicionY - 1].isRoja() != this.roja) {
                    piezaComida = tablero.obtenerPieza(posicionX + 2, posicionY - 1);
                    cambiarPosicionPieza(tablero, posicionX + 2, posicionY - 1);
                    if (confirmarJaque(tablero, rey.getPosicionX(), rey.getPosicionY())==false) {
                        regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, piezaComida);
                        return true;
                    }
                    regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, piezaComida);
                }
            }

        }

        // derecha lejos abajo
        if ((posicionX + 1 <=7 && posicionY+2<=7)) {
            if (table[posicionX + 1][posicionY + 2] == null) {
                cambiarPosicion(tablero, posicionX + 1, posicionY + 2);
                if (confirmarJaque(tablero, rey.getPosicionX(), rey.getPosicionY())==false) {
                    regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);
                    return true;
                }
                regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);
            } else {
                if (table[posicionX + 1][posicionY + 2].isRoja() != this.roja) {
                    piezaComida = tablero.obtenerPieza(posicionX + 1, posicionY + 2);
                    cambiarPosicionPieza(tablero, posicionX + 1, posicionY + 2);
                    if (confirmarJaque(tablero, rey.getPosicionX(), rey.getPosicionY())==false) {
                        regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, piezaComida);
                        return true;
                    }
                    regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, piezaComida);
                }
            }


        }
        //izquierda lejos abajo
        if ((posicionX + 1 <=7 && posicionY-2>=0)) {
            if (table[posicionX + 1][posicionY - 2] == null) {
                cambiarPosicion(tablero, posicionX + 1, posicionY - 2);
                if (confirmarJaque(tablero, rey.getPosicionX(), rey.getPosicionY())==false) {
                    regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);
                    return true;
                }
                regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);
            } else {
                if (table[posicionX + 1][posicionY - 2].isRoja() != this.roja) {
                    piezaComida = tablero.obtenerPieza(posicionX + 1, posicionY - 2);
                    cambiarPosicionPieza(tablero, posicionX + 1, posicionY - 2);
                    if (confirmarJaque(tablero, rey.getPosicionX(), rey.getPosicionY())==false) {
                        regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, piezaComida);
                        return true;
                    }
                    regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, piezaComida);
                }
            }
        }
        return false;


    }

    /**
     * imprime la forma del caballo
     * @return retorna la forma del caballo
     */
    public String imprimirCaballo() {
        if (this.roja) {
            System.out.print(red + "♘" + reset);
            return red + "♘" + reset;
        } else {
            System.out.print(green + "♘" + reset);
        return green + "♘" + reset;
        }
    }


    /**
     * imprime los datos del caballo
     * @return retorna los datos
     */
    public String mostrarDatos(){


        return toString() + ",Caballo";
    }
}













