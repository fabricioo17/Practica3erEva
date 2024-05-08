package Piezas.movimientosReutilizables;

import Piezas.Pieza;
import Piezas.Tipos.Rey;
import Tablero.Tablero;

public interface MovimientoDiagonal {
    default int  movimientoDiagonal(Tablero tablero, int movimientoX, int movimientoY, int posicionX, int posicionY){
        int posicionOriginalX= posicionX;
        int posicionOriginalY=posicionY;
        Pieza piezaComida;
        Pieza  [][] table= tablero.getTable();
        Pieza actual= table[posicionX][posicionY];
        Rey rey = tablero.obtenerPiezaReyBlanco(table[posicionOriginalX][posicionOriginalY].isBlancas());
        int posicionReyX=rey.getPosicionX();
        int posicionReyY= rey.getPosicionY();
        if (tablero.obtenerPieza(posicionX,posicionY)!= tablero.obtenerPieza(movimientoX,movimientoY)) {
            //arriba izquierda
            // ARRIBA IZQUIERDA
            if (movimientoX<posicionX && movimientoY<posicionY){
                for (int i = posicionX-1, j = posicionY-1  ; i >=0 && j >=0; i--,j--){
                    if ( table[i][j] instanceof Pieza || (i==0 || j==0)) {// I==0 o j==0  es porque si no logramos encontrar un limite ocn una pieza, usaremos como limite el final del tablero

                        if ((movimientoX >=i && movimientoY>=j) && table[movimientoX][movimientoY] == null) {// al tener los limites de arriba y abajo, el movimiento debe estar entre esos dos valores sino seria error
                           actual.cambiarPosicion(tablero,movimientoX,movimientoY);

                           if (actual.confirmarJaque(tablero,posicionReyX,posicionReyY)){
                               actual.regresarMovimiento(tablero,posicionOriginalX,posicionOriginalY);
                               return 3;
                           }
                            return 0;
                        }
                        //-----------------------comer pieza--------------------------------//
                        if ((i == movimientoX && j == movimientoY) && table[movimientoX][movimientoY] != null) { // si el movimiento concuerda justo donde se encuetra una pieza(es el limite de hasta donde puede moverse la pieza )
                            if (table[movimientoX][movimientoY].isBlancas() != table[posicionX][posicionY].isBlancas()) {
                                        piezaComida=tablero.obtenerPieza(movimientoX,movimientoY);
                                       actual.cambiarPosicionPieza(tablero,movimientoX,movimientoY);
                                       if (actual.confirmarJaque(tablero,posicionReyX,posicionReyY)){
                                           actual.regresarPiezaComida(tablero,posicionOriginalX,posicionOriginalY,piezaComida);
                                           return 3;
                                       }
                                       else {
                                           return 1;// pieza comida
                                       }
                            }
                            else {
                                return 2;// no comer propia pieza
                            }
                        }
                    }
                }
            }

            //ARRIBA DERECHA
            if (movimientoX<posicionX && movimientoY>posicionY) {
                //arriba
                for (int i = posicionX-1, j = posicionY +1 ; i >=0 && j<=7; i--,j++){
                    if ((i == 0 || j==7)|| table[i][j] instanceof Pieza) {
                        if ((movimientoX >=i && movimientoY<=j) && table[movimientoX][movimientoY] == null) {// al tener los limites de arriba y abajo, el movimiento debe estar entre esos dos valores sino seria error
                            actual.cambiarPosicion(tablero,movimientoX,movimientoY);
                            if (actual.confirmarJaque(tablero,posicionReyX,posicionReyY)){
                                actual.regresarMovimiento(tablero,posicionOriginalX,posicionOriginalY);
                                return 3;
                            }

                            return 0;
                        }
                       //--------------------comer pieza--------------------------------------//
                        if ((i == movimientoX && j == movimientoY) && table[movimientoX][movimientoY] != null) { // si el movimineto conuerda con un lugar donde no sea nulo, identificara el dueño de la pieza y si es de otro se la come

                            if (table[movimientoX][movimientoY].isBlancas() != table[posicionX][posicionY].isBlancas()) {

                                piezaComida=tablero.obtenerPieza(movimientoX,movimientoY);
                                actual.cambiarPosicionPieza(tablero,movimientoX,movimientoY);
                                if (actual.confirmarJaque(tablero,posicionReyX,posicionReyY)){
                                    actual.regresarPiezaComida(tablero,posicionOriginalX,posicionOriginalY,piezaComida);
                                    return 3;
                                }
                                else {
                                    return 1;// pieza comida
                                }

                            }
                            else {
                                return 2;
                            }
                        }

                    }
                }
            }






            // ABAJO IZQUIERDA
            if (movimientoX>posicionX && movimientoY<posicionY) {
                for (int i = posicionX+1, j = posicionY -1 ; i <=7 && j>=0; i++,j--){
                    if ((i == 7 || j==0)|| table[i][j] instanceof Pieza) {
                        if ((movimientoX <=i && movimientoY>=j) && table[movimientoX][movimientoY] == null) {// al tener los limites de arriba y abajo, el movimiento debe estar entre esos dos valores sino seria error
                            actual.cambiarPosicion(tablero,movimientoX,movimientoY);
                            if (actual.confirmarJaque(tablero,posicionReyX,posicionReyY)){
                                actual.regresarMovimiento(tablero,posicionOriginalX,posicionOriginalY);
                                return 3;
                            }
                            return 0;
                        }

                        //----------------comer pieza----------------------------//
                        if ((i == movimientoX && j == movimientoY) && table[movimientoX][movimientoY] != null) { // si el movimineto conuerda con un lugar donde no sea nulo, identificara el dueño de la pieza y si es de otro se la come
                            if (table[movimientoX][movimientoY].isBlancas() != table[posicionX][posicionY].isBlancas()) {

                                piezaComida=tablero.obtenerPieza(movimientoX,movimientoY);
                                actual.cambiarPosicionPieza(tablero,movimientoX,movimientoY);
                                if (actual.confirmarJaque(tablero,posicionReyX,posicionReyY)){
                                    actual.regresarPiezaComida(tablero,posicionOriginalX,posicionOriginalY,piezaComida);
                                    return 3;
                                }
                                else {
                                    return 1;// pieza comida
                                }
                            }

                            else {
                                return 2;
                            }
                        }

                    }
                }
            }





            //ABAJO DERECHA
            if (movimientoX>posicionX && movimientoY>posicionY) {
                for (int i = posicionX+1, j = posicionY+1  ; i <=7 && j <=7; i++,j++){
                    if ((i == 7 || j==7)|| table[i+1][j+1] instanceof Pieza) {
                        if ((movimientoX <=i && movimientoY<=j) && table[movimientoX][movimientoY] == null)
                        {// al tener los limites de arriba y abajo, el movimiento debe estar entre esos dos valores sino seria error
                            actual.cambiarPosicion(tablero,movimientoX,movimientoY);
                            if (actual.confirmarJaque(tablero,posicionReyX,posicionReyY)){
                                actual.regresarMovimiento(tablero,posicionOriginalX,posicionOriginalY);
                                return 3;
                            }
                            return 0;
                        }

                        if ((i == movimientoX && j == movimientoY) && table[movimientoX][movimientoY] != null) { // si el movimineto conuerda con un lugar donde no sea nulo, identificara el dueño de la pieza y si es de otro se la come
                            if (table[movimientoX][movimientoY].isBlancas() != table[posicionX][posicionY].isBlancas()) {

                                piezaComida=tablero.obtenerPieza(movimientoX,movimientoY);
                                actual.cambiarPosicionPieza(tablero,movimientoX,movimientoY);
                                if (actual.confirmarJaque(tablero,posicionReyX,posicionReyY)){
                                    actual.regresarPiezaComida(tablero,posicionOriginalX,posicionOriginalY,piezaComida);
                                    return 3;
                                }
                                else {
                                    return 1;// pieza comida
                                }

                            }
                            else {
                                return 2;
                            }
                        }

                    }
                }
            }
        }
        return 3;
    }
    default boolean protegerReyAlfil(Tablero tablero, int posicionX, int posicionY, boolean blanco){
        Pieza piezaComida;
        Rey rey = tablero.obtenerPiezaReyBlanco(blanco);
        int posicionReyX= rey.getPosicionX();
        int posicionReyY= rey.getPosicionY();
        Pieza [][] table = tablero.getTable();
        Pieza actual= table[posicionX][posicionY];
        // obtenemos el rey para que en cada movieminto veamos si podemos proteger al rey
        int posicionOriginalX= posicionX;
        int posicionOriginalY=posicionY;


        // ARRIBA IZQUIERDA
            for (int i = posicionX-1, j = posicionY-1  ; i >=0 && j >=0; i--,j--){
                    if (table[i][j] == null) {// al tener los limites de arriba y abajo, el movimiento debe estar entre esos dos valores sino seria error
                        actual.cambiarPosicion(tablero,i,j);

                        if (actual.confirmarJaque(tablero,posicionReyX,posicionReyY)==false){
                            actual.regresarMovimiento(tablero,posicionOriginalX,posicionOriginalY);
                            return true;
                        }
                        actual.regresarMovimiento(tablero,posicionOriginalX,posicionOriginalY);
                    }
                    //-----------------------comer pieza--------------------------------//
                   else  { // si el movimiento concuerda justo donde se encuetra una pieza(es el limite de hasta donde puede moverse la pieza )
                        if (table[i][j].isBlancas() != table[posicionX][posicionY].isBlancas()) {
                            piezaComida=tablero.obtenerPieza(i,j);
                            actual.cambiarPosicionPieza(tablero,i,j);
                            if (actual.confirmarJaque(tablero,posicionReyX,posicionReyY)==false){
                                actual.regresarPiezaComida(tablero,posicionOriginalX,posicionOriginalY,piezaComida);
                                return true;
                            }
                            actual.regresarPiezaComida(tablero,posicionOriginalX,posicionOriginalY,piezaComida);
                        }
                        else {
                            break;
                        }
                    }

            }

// ARRIBA derecha
        for (int i = posicionX-1, j = posicionY+1  ; i >=0 && j<=7; i--,j++){
            if (table[i][j] == null) {// al tener los limites de arriba y abajo, el movimiento debe estar entre esos dos valores sino seria error
                actual.cambiarPosicion(tablero,i,j);

                if (actual.confirmarJaque(tablero,posicionReyX,posicionReyY)==false){
                    actual.regresarMovimiento(tablero,posicionOriginalX,posicionOriginalY);
                    return true;
                }
                actual.regresarMovimiento(tablero,posicionOriginalX,posicionOriginalY);
            }
            //-----------------------comer pieza--------------------------------//
            else  { // si el movimiento concuerda justo donde se encuetra una pieza(es el limite de hasta donde puede moverse la pieza )
                if (table[i][j].isBlancas() != table[posicionX][posicionY].isBlancas()) {
                    piezaComida=tablero.obtenerPieza(i,j);
                    actual.cambiarPosicionPieza(tablero,i,j);
                    if (actual.confirmarJaque(tablero,posicionReyX,posicionReyY)==false){
                        actual.regresarPiezaComida(tablero,posicionOriginalX,posicionOriginalY,piezaComida);
                        return true;
                    }
                    actual.regresarPiezaComida(tablero,posicionOriginalX,posicionOriginalY,piezaComida);
                }
                else {
                    break;
                }
            }

        }


        // Abajo IZQUIERDA
        for (int i = posicionX+1, j = posicionY-1  ; i <=7 && j >=0; i++,j--){
            if (table[i][j] == null) {// al tener los limites de arriba y abajo, el movimiento debe estar entre esos dos valores sino seria error
                actual.cambiarPosicion(tablero,i,j);

                if (actual.confirmarJaque(tablero,posicionReyX,posicionReyY)==false){
                    actual.regresarMovimiento(tablero,posicionOriginalX,posicionOriginalY);
                    return true;
                }
                actual.regresarMovimiento(tablero,posicionOriginalX,posicionOriginalY);
            }
            //-----------------------comer pieza--------------------------------//
            else  { // si el movimiento concuerda justo donde se encuetra una pieza(es el limite de hasta donde puede moverse la pieza )
                if (table[i][j].isBlancas() != table[posicionX][posicionY].isBlancas()) {
                    piezaComida=tablero.obtenerPieza(i,j);
                    actual.cambiarPosicionPieza(tablero,i,j);
                    if (actual.confirmarJaque(tablero,posicionReyX,posicionReyY)==false){
                        actual.regresarPiezaComida(tablero,posicionOriginalX,posicionOriginalY,piezaComida);
                        return true;
                    }
                    actual.regresarPiezaComida(tablero,posicionOriginalX,posicionOriginalY,piezaComida);
                }
                else {
                    break;
                }
            }

        }


// abajo derecha
        for (int i = posicionX+1, j = posicionY+1  ; i<=7 && j<=7; i++,j++){
            if (table[i][j] == null) {// al tener los limites de arriba y abajo, el movimiento debe estar entre esos dos valores sino seria error
                actual.cambiarPosicion(tablero,i,j);

                if (actual.confirmarJaque(tablero,posicionReyX,posicionReyY)==false){
                    actual.regresarMovimiento(tablero,posicionOriginalX,posicionOriginalY);
                    return true;
                }
                actual.regresarMovimiento(tablero,posicionOriginalX,posicionOriginalY);
            }
            //-----------------------comer pieza--------------------------------//
            else  { // si el movimiento concuerda justo donde se encuetra una pieza(es el limite de hasta donde puede moverse la pieza )
                if (table[i][j].isBlancas() != table[posicionX][posicionY].isBlancas()) {
                    piezaComida=tablero.obtenerPieza(i,j);
                    actual.cambiarPosicionPieza(tablero,i,j);
                    if (actual.confirmarJaque(tablero,posicionReyX,posicionReyY)==false){
                        actual.regresarPiezaComida(tablero,posicionOriginalX,posicionOriginalY,piezaComida);
                        return true;
                    }
                    actual.regresarPiezaComida(tablero,posicionOriginalX,posicionOriginalY,piezaComida);
                }
                else {
                    break;
                }
            }

        }




























        return false;
    }

}
