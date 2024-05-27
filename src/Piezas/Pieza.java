package Piezas;

import Piezas.Tipos.*;
import Tablero.Tablero;

public abstract  class Pieza {
    protected boolean roja;
    protected int posicionX;
    protected int posicionY;

    protected final String red = "\u001B[31m";
    protected final String green = "\u001B[32m";
    protected final String reset = "\u001B[0m";

    public boolean isRoja() {
        return roja;
    }

    public void setRoja(boolean roja) {
        this.roja = roja;
    }

    public Pieza(Boolean roja, int posicionX, int posicionY) {
        this.roja = roja;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    public Pieza(boolean roja) {
        this.roja = roja;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }


    /**
     * este metodo nos indica si nuestro rey esta en ajque y sera ultil para saber que una piesa no puede moverse a cualquier lado si no es para proteger a su rey
     * @param tablero
     * @param posicionReyX
     * @param posicionReyY
     * @return devuelve un true si hay jaque y un false si es que no hay
     */
    public boolean confirmarJaque(Tablero tablero, int posicionReyX, int posicionReyY) {
        Pieza[][] table = tablero.getTable();
        //hacia arriba
        for (int i = posicionReyX - 1; i >= 0; i--) {
            if ((table[i][posicionReyY] instanceof Torre || table[i][posicionReyY] instanceof Reina)) {
                if (table[i][posicionReyY].isRoja() != table[posicionReyX][posicionReyY].isRoja()) {// si el color de la torre en la recta vertical es diferente al  color del rey devuelve un true
                    return true;
                } else {
                    break;
                }
            }

            if (table[i][posicionReyY] instanceof Pieza) {
                break;
            }
        }


        // hacia abajo
        for (int i = posicionReyX + 1; i < 8; i++) {
            if (table[i][posicionReyY] instanceof Torre || table[i][posicionReyY] instanceof Reina) {
                if (table[i][posicionReyY].isRoja() != table[posicionReyX][posicionReyY].isRoja()) {
                    return true;
                } else {
                    break;
                }
            }
            if (table[i][posicionReyY] instanceof Pieza) {
                break;
            }
        }


        //hacia la derecha
        for (int i = posicionReyY + 1; i < 8; i++) {
            if (table[posicionReyX][i] instanceof Torre || table[posicionReyX][i] instanceof Reina) {
                if (table[posicionReyX][i].isRoja() != table[posicionReyX][posicionReyY].isRoja()) {
                    return true;
                } else {
                    break;
                }
            }
            if (table[posicionReyX][i] instanceof Pieza) {

                break;
            }
        }


        //hacia la izquierda
        for (int i = posicionReyY - 1; i >= 0; i--) {
            if (table[posicionReyX][i] instanceof Torre || table[posicionReyX][i] instanceof Reina) {
                if (table[posicionReyX][i].isRoja() != table[posicionReyX][posicionReyY].isRoja()) {
                    return true;
                } else {
                    break;
                }
            }
            if (table[posicionReyX][i] instanceof Pieza) {

                break;
            }
        }
//-------------------------------peon------------------------------------------//

        // la segunda linea en los if luego del && significa que si luego de comprobar que son de distintos color las piezas si esa pieza esta arriba necesariaemnte debe ser blanca para causarnos jaque ya que solo las blancas bajan
        //arriba izquierda
        if (posicionReyY > 0 && posicionReyX > 0) {
            if (table[posicionReyX - 1][posicionReyY - 1] instanceof Peon) {
                if ((table[posicionReyX - 1][posicionReyY - 1].isRoja() != table[posicionReyX][posicionReyY].isRoja()) && table[posicionReyX - 1][posicionReyY - 1].isRoja() == true) {
                    return true;
                }
            }
        }
        //-------arriba derecha
        if (posicionReyX > 0 && posicionReyY < 7) {
            if (table[posicionReyX - 1][posicionReyY + 1] instanceof Peon) {
                if (table[posicionReyX - 1][posicionReyY + 1].isRoja() != table[posicionReyX][posicionReyY].isRoja() && table[posicionReyX - 1][posicionReyY - 1].isRoja() == true) {
                    return true;
                }
            }
        }
        //------------------abajo izquierda
        if (posicionReyX < 7 && posicionReyY > 0) {
            if (table[posicionReyX + 1][posicionReyY - 1] instanceof Peon) {
                if (table[posicionReyX + 1][posicionReyY - 1].isRoja() != table[posicionReyX][posicionReyY].isRoja() && table[posicionReyX - 1][posicionReyY - 1].isRoja() == false) {
                    return true;
                }
            }

//-----------------------abajo derecha--------------------------//
            if (posicionReyX<7  && posicionReyY < 7){
                if (table[posicionReyX + 1][posicionReyY + 1] instanceof Peon) {
                    if (table[posicionReyX + 1][posicionReyY + 1].isRoja() != table[posicionReyX][posicionReyY].isRoja() && table[posicionReyX - 1][posicionReyY - 1].isRoja() == false) {
                        return true;
                    }
                }
        }
    }






        //------------------------diagonal----------------------------------------------//
        for (int i = posicionReyX - 1, j = posicionReyY - 1; i >= 0 && j >= 0; i--, j--) {
            //arriba izquierda
            if (table[i][j] instanceof Reina || table[i][j] instanceof Alfil ) {

                if (table[i][j].isRoja() != table[posicionReyX][posicionReyY].isRoja()) {
                    return true;
                }
                else {
                    break;
                }
            }

             if (table[i][j] instanceof Pieza) {
                break;
            }


        }

        //arriba a la derecha
        for (int i = posicionReyX - 1, j = posicionReyY + 1; i >= 0 && j < 8; i--, j++) {
            //arriba izquierda
            if (table[i][j] instanceof Reina || table[i][j] instanceof Alfil ) {
                if (table[i][j].isRoja() != table[posicionReyX][posicionReyY].isRoja()) {
                    return true;
                }
                else {
                    break;
                }

            }
            if (table[i][j] instanceof Pieza && table[i][j].isRoja() == table[posicionReyX][posicionReyY].isRoja()) {
                break;
            }
        }


        //abajo a la izquierda

        for (int i = posicionReyX + 1, j = posicionReyY - 1; i < 8 && j >= 0; i++, j--) {
            //arriba izquierda
            if (table[i][j] instanceof Reina || table[i][j] instanceof Alfil) {
                if (table[i][j].isRoja() != table[posicionReyX][posicionReyY].isRoja()) {
                    return true;
                }
                else {
                    break;
                }
            }
            if (table[i][j] instanceof Pieza) { // cambiar a cualquier pieza , ya que al no ser ni alfil o reina no hay eligro alguno
                break;
            }
        }


        for (int i = posicionReyX + 1, j = posicionReyY + 1; i < 8 && j < 8; i++, j++) {
            //abajo derecha
            if (table[i][j] instanceof Reina || table[i][j] instanceof Alfil ) {
                if (table[i][j].isRoja() != table[posicionReyX][posicionReyY].isRoja()) {
                    return true;
                }
                else {
                    break;
                }
            }
            if (table[i][j] instanceof Pieza) {
                break;
            }

        }


        //------------------------------------------------------------------------------------//


//----------------------------evitar caballo-----------------------//


        // hacia arriba derecha
        if (posicionReyX - 2 >= 0 && posicionReyY + 1 <= 7) {// por si al querer identificar el caballo en un lugar que este fuera del tablero usamos un if
            if (table[posicionReyX - 2][posicionReyY + 1] instanceof Caballo) {
                if (table[posicionReyX - 2][posicionReyY + 1].isRoja() != table[posicionReyX][posicionReyY].isRoja()) {
                    return true;
                }
            }
        }


        //arriba izquierda
        if (posicionReyX - 2 >= 0 && posicionReyY - 1 >= 0) {
            if (table[posicionReyX - 2][posicionReyY - 1] instanceof Caballo) {
                if (table[posicionReyX - 2][posicionReyY - 1].isRoja() != table[posicionReyX][posicionReyY].isRoja()) {
                    return true;
                }
            }
        }


// derecha arriba
        if (posicionReyX - 1 >= 0 && posicionReyY + 2 <= 7) {
            if (table[posicionReyX - 1][posicionReyY + 2] instanceof Caballo) {
                if (table[posicionReyX - 1][posicionReyY + 2].isRoja() != table[posicionReyX][posicionReyY].isRoja()) {
                    return true;
                }
            }
        }


        //derecha abajo
        if (posicionReyX + 1 <= 7 && posicionReyY + 2 <= 7) {
            if (table[posicionReyX + 1][posicionReyY + 2] instanceof Caballo) {
                if (table[posicionReyX + 1][posicionReyY + 2].isRoja() != table[posicionReyX][posicionReyY].isRoja()) {
                    return true;
                }
            }
        }


        //abajo derecha
        if (posicionReyX + 2 <= 7 && posicionReyY + 1 <= 7) {
            if (table[posicionReyX + 2][posicionReyY + 1] instanceof Caballo) {
                if (table[posicionReyX + 2][posicionReyY + 1].isRoja() != table[posicionReyX][posicionReyY].isRoja()) {
                    return true;
                }
            }
        }

//abajo izquierda
        if (posicionReyX + 2 <= 7 && posicionReyY - 1 >= 0) {
            if (table[posicionReyX + 2][posicionReyY - 1] instanceof Caballo) {
                if (table[posicionReyX + 2][posicionReyY - 1].isRoja() != table[posicionReyX][posicionReyY].isRoja()) {
                    return true;
                }
            }

        }


// izquierda arriba
        if (posicionReyX - 1 >= 0 && posicionReyY - 2 >= 0) {
            if (table[posicionReyX - 1][posicionReyY - 2] instanceof Caballo) {
                if (table[posicionReyX - 1][posicionReyY - 2].isRoja() != table[posicionReyX][posicionReyY].isRoja()) {
                    return true;
                }
            }
        }

//izquierda abajo
        if (posicionReyX + 1 <= 7 && posicionReyY - 2 >= 0) {
            if (table[posicionReyX + 1][posicionReyY - 2] instanceof Caballo) {
                if (table[posicionReyX + 1][posicionReyY - 2].isRoja() != table[posicionReyX][posicionReyY].isRoja()) {
                    return true;
                }
            }
        }

//---------------------------------------------------------------------------------------------------------------------//

        return false;


    }


    /**
     * este metodo sirve para regresar la pieza que comimos luego de darnos cuenta que no era lo correcto
     * @param tablero
     * @param posicionOriginalX
     * @param posicionOriginalY
     * @param piezaComida
     * @return  si lo regresa devuelve un true
     */
    public boolean regresarPiezaComida(Tablero tablero, int posicionOriginalX, int posicionOriginalY, Pieza piezaComida) {
        Pieza[][] table = tablero.getTable();
        table[posicionOriginalX][posicionOriginalY] = table[posicionX][posicionY];
        table[posicionX][posicionY] = piezaComida;
        table[posicionX][posicionY].setPosicionX(posicionX);
        table[posicionX][posicionY].setPosicionY(posicionY);
        table[posicionOriginalX][posicionOriginalY].setPosicionX(posicionOriginalX);
        table[posicionOriginalX][posicionOriginalY].setPosicionY(posicionOriginalY);
        return true;

    }

    /**
     * sirve para resar los movimientos luego de comer una pieza
     * @param tablero
     * @param posicionOriginalX
     * @param posicionOriginalY
     * @return devuelve un true si regresamos los movimientos
     */
    public boolean regresarMovimiento(Tablero tablero, int posicionOriginalX, int posicionOriginalY) {
        Pieza[][] table = tablero.getTable();
        table[posicionOriginalX][posicionOriginalY] = table[posicionX][posicionY];// al confirmar que se puede mover table esa posicion,
        //igualamos el contenido de la ubicacion orignal de la pieza al nuevo lugar

        table[posicionX][posicionY] = null;// la ubicacion original la dejamos en nulo
       table[posicionOriginalX][posicionOriginalY].setPosicionX(posicionOriginalX);//cambiamos la posicion de la nueva pieza movida, ya que aun mantenia la posicion anteiro
        table[posicionOriginalX][posicionOriginalY].setPosicionY(posicionOriginalY);

        return true;

    }
        public  void cambiarPosicion(Tablero tablero, int movimientoX, int movimientoY){
            Pieza[][] table = tablero.getTable();
            table[movimientoX][movimientoY] = table[posicionX][posicionY];
           table[posicionX][posicionY] = null;
            table[movimientoX][movimientoY].setPosicionX(movimientoX); //cambiamos la posicion de la nueva pieza movida, ya que aun mantenia la posicion anteiro
            table[movimientoX][movimientoY].setPosicionY(movimientoY);

        }

    /**
     * sirve para cambiar la posicion con una pieza
     * @param tablero
     * @param movimientoX movimiento en el eje X
     * @param movimientoY movieminto en el eje Y
     */
        public  void cambiarPosicionPieza(Tablero tablero, int movimientoX, int movimientoY){
            Pieza[][] table = tablero.getTable();
            table[movimientoX][movimientoY] = table[posicionX][posicionY];
            table[posicionX][posicionY] = null;
            table[movimientoX][movimientoY].setPosicionX(movimientoX); //cambiamos la posicion de la nueva pieza movida, ya que aun mantenia la posicion anteiro
            table[movimientoX][movimientoY].setPosicionY(movimientoY);
        }


    /**
     * este metodo sera aplicado de manera diferente por las diferentes piezas ya que cada uno defiendo a su rey de forma distitna
     * @param tablero
     * @param posicionX
     * @param posicionY
     * @param blanco
     * @return devuelve un true si logra proteger y un false si no lo logra
     */
    public  abstract  boolean protegerRey(Tablero tablero, int posicionX, int posicionY, boolean blanco);

    @Override
    public String toString() {
        return "Pieza(" +
                "roja=" + roja +
                ", posicionX=" + posicionX +
                ", posicionY=" + posicionY +
                ", red='" + red + '\'' +
                ", green='" + green + '\'' +
                ", reset='" + reset + '\'' +
                ')';
    }
}






