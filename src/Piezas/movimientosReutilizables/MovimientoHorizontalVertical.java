package Piezas.movimientosReutilizables;

import Piezas.Pieza;
import Piezas.Tipos.Rey;
import Tablero.Tablero;


public interface MovimientoHorizontalVertical {
    /**
     * verificamos si los valores de movimeinto estan dentro del rango permitido y sin desproteger al rey
     * @param tablero tablero actual
     * @param movimientoX movimiento en eje X
     * @param movimientoY movimiento en eje Y
     * @param posicionX posicion en eje X
     * @param posicionY posicion en eje Y
     * @return devuekve un 0 si es moviemiento correcto, 1 si come una pieza y cualquier otro si es error
     */
    default int movimientoVertical(Tablero tablero, int movimientoX, int movimientoY, int posicionX, int posicionY) {
       int posicionOriginalX= posicionX;
       int posicionOriginalY=posicionY;

Pieza piezaComida;
      Pieza  [][] table= tablero.getTable();

        Rey rey = tablero.obtenerPiezaReyBlanco(table[posicionOriginalX][posicionOriginalY].isRoja());
        //horizontal
        if (tablero.obtenerPieza(posicionX,posicionY)!= tablero.obtenerPieza(movimientoX,movimientoY)) {// esto es para que no elija el mismo sitio de donde ya se encuentra
            if (posicionX == movimientoX) {// si se mueve en la misma fila
                // primero vemos si la pieza esta al extemo derecha y evitar el error de cantidad de arryar
                for (int i = posicionY ; i < 8; i++) {
                    if (i +1== 8 || table[posicionX][i+1] instanceof Pieza) {
                        //hacia la izquierda y derecha//
                        for (int j = posicionY ; j >= 0; j--) {
                            if (j -1== -1 || table[posicionX][j-1] instanceof Pieza) {
                                if ((movimientoY < i+1 && movimientoY > j-1) && table[posicionX][movimientoY] == null) {// al tener los limites de arriba y abajo, el movimiento debe estar entre esos dos valores sino seria error
//------------------------------------------------cambiat de posicion ---------------------------------------------------------//
                                    table[posicionX][posicionY].cambiarPosicion(tablero,movimientoX,movimientoY);

                                    if(table[movimientoX][movimientoY].confirmarJaque(tablero,rey.getPosicionX(),rey.getPosicionY())){// si  aun asi sigue en jacke regresaremos todo table como estaba antes
                                        table[movimientoX][movimientoY].regresarMovimiento(tablero,posicionOriginalX,posicionOriginalY);
                                        return 3;
                                    }

                                    return 0;
                                }

                                //-------------------------------comer pieza--------------//
                                if ((i+1 == movimientoY || j-1 == movimientoY) && table[posicionX][movimientoY] != null) { // si el movimineto conuerda con un lugar donde no sea nulo, identificara el dueño de la pieza y si es de otro se la come
                                    if (table[movimientoX][movimientoY].isRoja() != table[posicionX][posicionY].isRoja())
                                        {
                                        // guardamos en una variable la pieza comida ya que si aun seguimos en jacke debemos regresar todo table la normalidad
                                            piezaComida=tablero.obtenerPieza(movimientoX,movimientoY);
    //------------------------------------------------cambiar de posicion con una pieza -------------------------------------------------------------------------//
                                            table[posicionX][posicionY].cambiarPosicionPieza(tablero,movimientoX,movimientoY);


                                            //----------------------------------verificar jaque-------------------------------------------//
                                            if ( table[movimientoX][movimientoY].confirmarJaque(tablero,rey.getPosicionX(),rey.getPosicionY()))//aqui colocamos la nuevaposicion del rey
                                            {// es verdad si luego de mover la pieza aun seguimos en jacke, entonces abajo debemos regresar el movimiento

                                                           //-----------------------------------regresar pieza comida------------------------------------//
                                                            if (table[movimientoX][movimientoY].regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, piezaComida) == true) {// si el valor es verdadero  habremos anulado el movimiento

                                                                return 3;

                                                            }
                                            }
                                            else
                                            {
                                                return 1;// pieza comida
                                            }

                                        }
                                    else
                                        {
                                        return 2;// no se puede comer pieza propia
                                         }
                                }

                            }
                        }
                    }
                }
            }
            // movimiento vertical
            if (posicionY == movimientoY) {
                // si la posicion horizontal menos 1 no es menor que 0 ingresa,
                //hacia arriba
                for (int i = posicionX ; i >= 0; i--) {// al ser un for ingresara directamente en el bucle, en caso de que sea i =-1
                    if (i-1 < 0 || ((table[i-1][posicionY]) instanceof Pieza)) { //si la i = -1 ingresa al siguieente for , sino comprueba hasta que encuente una pieza hacia arriba que seria el limitearriba
                        //abajo
                        for (int j = posicionX; j < 8; j++) {
                            if (j+1 == 8 || ((table[j+1][posicionY]) instanceof Pieza)) {// en caso de que j =8 entra al if, sino se pone table buscar una pieza hacia abjao para encontra el limite
                                if ((movimientoX > i-1 && movimientoX < j+1) && table[movimientoX][posicionY] == null) {// al tener los limites de arriba y abajo, el movimiento debe estar entre esos dos valores sino seria error

                                table[posicionX][posicionY].cambiarPosicion(tablero,movimientoX,movimientoY);


                                    if(table[movimientoX][movimientoY].confirmarJaque(tablero,rey.getPosicionX(),rey.getPosicionY())){// si  aun asi sigue en jacke regresaremos todo table como estaba antes
                                        table[movimientoX][movimientoY].regresarMovimiento(tablero,posicionOriginalX,posicionOriginalY);
                                        return 3;// aun ahy jacke, error
                                    }

                                    return 0;// pieza movida

                                }
//---------------------------------------comer pieza envertical-----------------------------------------------------------//
                                if ((i -1== movimientoX || j+1 == movimientoX) && table[movimientoX][posicionY] != null) { // si el movimineto conuerda con un lugar donde no sea nulo, identificara el dueño de la pieza y si es de otro se la come

                                    if (table[movimientoX][movimientoY].isRoja() != table[posicionX][posicionY].isRoja()) {
                                        piezaComida=tablero.obtenerPieza(movimientoX,movimientoY);

                                        table[posicionX][posicionY].cambiarPosicionPieza(tablero,movimientoX,movimientoY);

                                        if ( table[movimientoX][movimientoY].confirmarJaque(tablero,rey.getPosicionX(),rey.getPosicionY())) // ahora la torre es ta pieza, ya que le acmbiamos de posicion
                                        {// es verdad si luego de mover la pieza aun seguimos en jacke, entonces abajo debemos regresar el movimiento
                                            if (table[movimientoX][movimientoY].regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, piezaComida) == true) {// si el valor es verdadero  habremos anulado el movimiento

                                                return 3;

                                            }
                                        }
                                        return 1;
                                    }
                                    else {
                                        return 2;
                                    }
                                }
                                return 3;
                            }
                        }
                    }
                }
            }
        }
        return 3;
    }




// luego de ver que el rey no se puede mover en ningun lado viene ahora la proteccion de cada pieza si ninguna pieza sobrante logra protege al rey, seera jaque mate

    /**
     *hacemos posibles movientos para ver si podemos proteger al rey
     * @param tablero requerimos el tablero para poder movilizarnos
     * @param posicionX
     * @param posicionY
     * @param roja necesitamos el color de la pieza para poder encontrar el rey que debemos proteger
     * @return devuelve un true si es que logramos proteger el rey, para elloprimero veremos si logra proteger hacia arriba si no lo logra empieza hacia abajo y luego derecha izquierda hasta encontrar el true, si no lo encunetra devuelve un flase
     */
    default boolean protegerReyTorre(Tablero tablero, int posicionX, int posicionY, boolean roja){
 Pieza comida;
 Rey rey = tablero.obtenerPiezaReyBlanco(roja);
 int posicionReyX= rey.getPosicionX();
 int posicionReyY= rey.getPosicionY();
   Pieza [][] table = tablero.getTable();
   Pieza actual= table[posicionX][posicionY];
    // obtenemos el rey para que en cada movieminto veamos si podemos proteger al rey
    int posicionOriginalX= posicionX;
    int posicionOriginalY=posicionY;

//-----------hacai arriba---------------------//
    for (int i =posicionOriginalX-1;i>=0;i-- ){

        if (table[i][posicionY] == null) {
            actual.cambiarPosicion(tablero,i,posicionY);
            if ((actual.confirmarJaque(tablero,  posicionReyX,posicionReyY) == false)) {// si llega a ser falso es porque no hay jague hacia arriba
                actual.regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);
                return true; // logramos bloquear el jaque
            }
            actual.regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);
        }
        else {
            if (table[posicionX][posicionY].isRoja() !=table[i][posicionY].isRoja()) {

                comida = tablero.obtenerPieza(i, posicionY);// obtenemos la pieza enemiga que se encuentra en la parte de arriba
                actual.cambiarPosicion(tablero,i,posicionY);

                if ((actual.confirmarJaque(tablero, posicionReyX,posicionReyY) == false)) { // la posicion en esta pieza de rey ya cambio y ahora es la poisicion original-1
                    actual.regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, comida);
                    return true;
                }
                actual.regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, comida);
            }
            else {
                break; // si encontramos una pieza nuestra  ya no podremos subir mas por ende rompemos el bucle
            }
        }
    }




    //-----------hacai abajo---------------------//
    for (int i =posicionOriginalX+1;i<=7;i++ ){

        if (table[i][posicionY] == null) {
            actual.cambiarPosicion(tablero,i,posicionY);
            if ((actual.confirmarJaque(tablero, posicionReyX,posicionReyY) == false)) {// si llega a ser falso es porque no hay jague hacia arriba
                actual.regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);
                return true; // logramos bloquear el jaque
            }
            actual.regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);
        }
        else {
            if (table[posicionX][posicionY].isRoja() !=table[i][posicionY].isRoja()) {

                comida = tablero.obtenerPieza(i, posicionY);// obtenemos la pieza enemiga que se encuentra en la parte de arriba
                actual.cambiarPosicion(tablero,i,posicionY);

                if ((actual.confirmarJaque(tablero, posicionReyX,posicionReyY) == false)) { // la posicion en esta pieza de rey ya cambio y ahora es la poisicion original-1
                    actual.regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, comida);
                    return true;
                }
                actual.regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, comida); //añadir un break ya que si hay una pieza enemiga no puede avanzar masy
            }
            else {
                break; // si encontramos una pieza nuestra  ya no podremos subir mas por ende rompemos el bucle
            }
        }
    }

//-----------hacai derecha---------------------//
    for (int i =posicionOriginalY+1;i<=7;i++ ){

        if (table[posicionX][i] == null) {
            actual.cambiarPosicion(tablero,posicionX,i);
            if ((actual.confirmarJaque(tablero, posicionReyX,posicionReyY) == false)) {// si llega a ser falso es porque no hay jague hacia arriba
                actual.regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);
                return true; // logramos bloquear el jaque
            }
            actual.regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);
        }
        else {
            if (table[posicionX][posicionY].isRoja() !=table[posicionX][i].isRoja()) {

                comida = tablero.obtenerPieza(posicionX, i);// obtenemos la pieza enemiga que se encuentra en la parte de arriba
                actual.cambiarPosicion(tablero,posicionX,i);// en movimientos la posicionX se mantiene igual ya que solo cambia la i

                if ((actual.confirmarJaque(tablero, posicionReyX,posicionReyY) == false)) { // la posicion en esta pieza de rey ya cambio y ahora es la poisicion original-1
                    actual.regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, comida);
                    return true;
                }
                actual.regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, comida);
            }
            else {
                break; // si encontramos una pieza nuestra  ya no podremos subir mas por ende rompemos el bucle
            }
        }
    }


//-----------hacai izquierda---------------------//
    for (int i =posicionOriginalY-1;i>=0;i-- ){

        if (table[posicionX][i] == null) {
            actual.cambiarPosicion(tablero,posicionX,i);
            if ((actual.confirmarJaque(tablero, posicionReyX,posicionReyY) == false)) {// si llega a ser falso es porque no hay jague hacia arriba
                actual.regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);
                return true; // logramos bloquear el jaque
            }
            actual.regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);
        }
        else {
            if (table[posicionX][posicionY].isRoja() !=table[posicionX][i].isRoja()) {

                comida = tablero.obtenerPieza(posicionX, i);// obtenemos la pieza enemiga que se encuentra en la parte de arriba
                actual.cambiarPosicion(tablero,posicionX,i);// en movimientos la posicionX se mantiene igual ya que solo cambia la i

                if ((actual.confirmarJaque(tablero, posicionReyX,posicionReyY) == false)) { // la posicion en esta pieza de rey ya cambio y ahora es la poisicion original-1
                    actual.regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, comida);
                    return true;
                }
                actual.regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, comida);
            }
            else {
                break; // si encontramos una pieza nuestra  ya no podremos subir mas por ende rompemos el bucle
            }
        }
    }
    return false;
}


}
