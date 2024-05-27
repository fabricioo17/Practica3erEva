package Piezas.Tipos;
import Piezas.Pieza;
import Tablero.Tablero;
import java.util.Scanner;
public class Rey extends Pieza{

    public Rey(Boolean roja, int posicionX, int y) {
        super(roja, posicionX, y);
    }


    /**
     * obtendremos los posibles movimientos y los verificaremos con los otros metodos
     * @param teclado
     * @param tablero
     * @return devuelve un 0 si todo es correcto y un 1 si hay algun error
     */
    public int elegirMovimiento(Scanner teclado, Tablero tablero) {
        System.out.println("ingrese a que fila quiere mover el rey ");
        int x = teclado.nextInt()-1;
        System.out.println("ingresa la columna");
        int y = teclado.nextInt()-1;
            int opcion=    verificarMovientoRey(tablero,x,y);

            if ( opcion==0){
                System.out.println("pieza movida");
                return 0;
            }
            else if (opcion==1){
                System.out.println("pieza comida");
                return 0;
            }
            else if (opcion==2){
                System.out.println("no puedes comer tu propia pieza");
                return 1;
            }
            else  {
                System.out.println(" error movimiento");
                return 1;
            }




    }


    /**
     * verifica si el posible movimineto del rey es correcto
     * @param tablero tablero actual
     * @param movimientoX   moviemitno eje x
     * @param movimientoY moviemiento eje Y
     * @return devuelve un 0 si el movimiento es correcto un 1 si come la pieza y si es otro numero es error
     */
   public int verificarMovientoRey(Tablero tablero, int movimientoX, int movimientoY) {// si devuelve  no se puede mover ahi
       int posicionOriginalX = posicionX;
       int posicionOriginalY = posicionY;
       Pieza piezaComida;
       Pieza[][] table = tablero.getTable();

       //-------------horizontal---------------------//
           if (movimientoX == posicionX) {
               if (movimientoY == posicionY - 1 || movimientoY == posicionY + 1) {
                   if (table[movimientoX][movimientoY]== null) {
                      cambiarPosicion(tablero,movimientoX,movimientoY);
                       if (table[movimientoX][movimientoY].confirmarJaque(tablero, movimientoX, movimientoY)) {// es verdad si luego de mover la pieza aun seguimos en jacke, entonces abajo debemos regresar el movimiento
                           if (table[movimientoX][movimientoY].regresarMovimiento(tablero,posicionOriginalX,posicionOriginalY)) {// si el valor es verdadero  habremos anulado el movimiento
                               return 3;
                           }
                       }


                       return 0; // pieza movida
                   }
                   ///-------------comer pieza---------------------//
                    else {
                       if (table[movimientoX][movimientoY].isRoja() != this.roja && !(table[movimientoX][movimientoY] instanceof  Rey)) {
                           piezaComida = tablero.obtenerPieza(movimientoX, movimientoY);
                           table[movimientoX][movimientoY] = table[posicionX][posicionY];
                           table[posicionX][posicionY] = null;
                           table[movimientoX][movimientoY].setPosicionX(movimientoX); //cambiamos la posicion de la nueva pieza movida, ya que aun mantenia la posicion anteiro
                           table[movimientoX][movimientoY].setPosicionY(movimientoY);
                           if (table[movimientoX][movimientoY].confirmarJaque(tablero, movimientoX, movimientoY)) {// es verdad si luego de mover la pieza aun seguimos en jacke, entonces abajo debemos regresar el movimiento
                               if (table[movimientoX][movimientoY].regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, piezaComida) == true) {// si el valor es verdadero  habremos anulado el movimiento
                                   return 3;
                               }
                           }
                           return 1; // pieza comida
                       }

                   }



               }
           }


           //----------------vertical-------------------------------
           else if (posicionY == movimientoY) {
               if (movimientoX == posicionX - 1 || movimientoX == posicionX + 1) {
                   if (table[movimientoX][movimientoY] == null) {
                      cambiarPosicion(tablero,movimientoX,movimientoY);

                       if (table[movimientoX][movimientoY].confirmarJaque(tablero, movimientoX ,movimientoY)) {// es verdad si luego de mover la pieza aun seguimos en jacke, entonces abajo debemos regresar el movimiento
                           if (table[movimientoX][movimientoY].regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY)) {// si el valor es verdadero  habremos anulado el movimiento
                               return 3;
                           }
                       }

                       return 0;
                   }
                   else {
                       if (table[movimientoX][movimientoY].isRoja() != this.roja && !(table[movimientoX][movimientoY] instanceof  Rey)) {
                           piezaComida = tablero.obtenerPieza(movimientoX, movimientoY);
                           table[movimientoX][movimientoY] = table[posicionX][posicionY];
                           table[posicionX][posicionY] = null;
                           table[movimientoX][movimientoY].setPosicionX(movimientoX); //cambiamos la posicion de la nueva pieza movida, ya que aun mantenia la posicion anteiro
                           table[movimientoX][movimientoY].setPosicionY(movimientoY);
                           if (table[movimientoX][movimientoY].confirmarJaque(tablero,  movimientoX, movimientoY)) {// es verdad si luego de mover la pieza aun seguimos en jacke, entonces abajo debemos regresar el movimiento
                               if (table[movimientoX][movimientoY].regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, piezaComida) == true) {// si el valor es verdadero  habremos anulado el movimiento

                                   return 3;
                               }
                           }

                           return 1; // pieza comida
                       }
                   }

               }
           }
//-------------------------------------------------------------------------------------------------------------/


           //----------------------------------diagonal arriba derecha------------------------------------//
           else if (posicionX - 1 == movimientoX && posicionY + 1 == movimientoY) {
               if (table[movimientoX][movimientoY] == null) {
                   cambiarPosicion(tablero,movimientoX,movimientoY);

                   if (table[movimientoX][movimientoY].confirmarJaque(tablero, movimientoX, movimientoY)) {// es verdad si luego de mover la pieza aun seguimos en jacke, entonces abajo debemos regresar el movimiento
                       if (table[movimientoX][movimientoY].regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY)) {// si el valor es verdadero  habremos anulado el movimiento
                           return 3;
                       }
                   }

                   return 0;
               }
               else {
                   if (table[movimientoX][movimientoY].isRoja() != this.roja && !(table[movimientoX][movimientoY] instanceof  Rey)) {
                       piezaComida = tablero.obtenerPieza(movimientoX, movimientoY);
                       table[movimientoX][movimientoY] = table[posicionX][posicionY];
                       table[posicionX][posicionY] = null;
                       table[movimientoX][movimientoY].setPosicionX(movimientoX); //cambiamos la posicion de la nueva pieza movida, ya que aun mantenia la posicion anteiro
                       table[movimientoX][movimientoY].setPosicionY(movimientoY);
                       if (table[movimientoX][movimientoY].confirmarJaque(tablero,movimientoX, movimientoY)) {// es verdad si luego de mover la pieza aun seguimos en jacke, entonces abajo debemos regresar el movimiento
                           if (table[movimientoX][movimientoY].regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, piezaComida) == true) {// si el valor es verdadero  habremos anulado el movimiento

                               return 3;
                           }
                       }

                       return 1; // pieza comida
                   }
               }
           }

           //----------------------------------------------------------------------------------------------//


           //-----------------------------diagonal arriba izquierda---------------------------------------------------//
           else if (posicionX - 1 == movimientoX && posicionY - 1 == movimientoY) {
               if (table[movimientoX][movimientoY] == null) {
                   cambiarPosicion(tablero,movimientoX,movimientoY);

                   if (table[movimientoX][movimientoY].confirmarJaque(tablero, movimientoX, movimientoY)) {// es verdad si luego de mover la pieza aun seguimos en jacke, entonces abajo debemos regresar el movimiento
                       if (table[movimientoX][movimientoY].regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY)) {// si el valor es verdadero  habremos anulado el movimiento
                           return 3;
                       }
                   }

                   return 0;
               }
               else {
                   if (table[movimientoX][movimientoY].isRoja() != this.roja && !(table[movimientoX][movimientoY] instanceof  Rey)) {
                       piezaComida = tablero.obtenerPieza(movimientoX, movimientoY);
                       table[movimientoX][movimientoY] = table[posicionX][posicionY];
                       table[posicionX][posicionY] = null;
                       table[movimientoX][movimientoY].setPosicionX(movimientoX); //cambiamos la posicion de la nueva pieza movida, ya que aun mantenia la posicion anteiro
                       table[movimientoX][movimientoY].setPosicionY(movimientoY);
                       if (table[movimientoX][movimientoY].confirmarJaque(tablero,  movimientoX, movimientoY)) {// es verdad si luego de mover la pieza aun seguimos en jacke, entonces abajo debemos regresar el movimiento
                           if (table[movimientoX][movimientoY].regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, piezaComida) == true) {// si el valor es verdadero  habremos anulado el movimiento

                               return 3;
                           }
                       }

                       return 1; // pieza comida
                   }
               }
           }
           //----------------------------------------------------------------------------------------------//


           //--------------------------diagonal abajo derecha--------------------------------------------------------//
           else if (posicionX + 1 == movimientoX && posicionY + 1 == movimientoY) {
               if (table[movimientoX][movimientoY] == null) {
                   cambiarPosicion(tablero,movimientoX,movimientoY);

                   if (table[movimientoX][movimientoY].confirmarJaque(tablero, movimientoX, movimientoY)) {// es verdad si luego de mover la pieza aun seguimos en jacke, entonces abajo debemos regresar el movimiento
                       if (table[movimientoX][movimientoY].regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY)) {// si el valor es verdadero  habremos anulado el movimiento
                           return 3;
                       }
                   }

                   return 0;
               }
               else {
                   if (table[movimientoX][movimientoY].isRoja() != this.roja && !(table[movimientoX][movimientoY] instanceof  Rey)) {
                       piezaComida = tablero.obtenerPieza(movimientoX, movimientoY);
                       table[movimientoX][movimientoY] = table[posicionX][posicionY];
                       table[posicionX][posicionY] = null;
                       table[movimientoX][movimientoY].setPosicionX(movimientoX); //cambiamos la posicion de la nueva pieza movida, ya que aun mantenia la posicion anteiro
                       table[movimientoX][movimientoY].setPosicionY(movimientoY);
                       if (table[movimientoX][movimientoY].confirmarJaque(tablero, movimientoX, movimientoY)) {// es verdad si luego de mover la pieza aun seguimos en jacke, entonces abajo debemos regresar el movimiento
                           if (table[movimientoX][movimientoY].regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, piezaComida) == true) {// si el valor es verdadero  habremos anulado el movimiento

                               return 3;
                           }
                       }

                       return 1; // pieza comida
                   }
               }
           }
           //----------------------------------------------------------------------------------------------//


           //----------------------------diagonal abajo izquierda------------------------------------------------//
           else if (posicionX + 1 == movimientoX && posicionY - 1 == movimientoY) {
               if (table[movimientoX][movimientoY] == null) {
                   cambiarPosicion(tablero,movimientoX,movimientoY);

                   if (table[movimientoX][movimientoY].confirmarJaque(tablero,  movimientoX, movimientoY)) {// es verdad si luego de mover la pieza aun seguimos en jacke, entonces abajo debemos regresar el movimiento
                       if (table[movimientoX][movimientoY].regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY)) {// si el valor es verdadero  habremos anulado el movimiento
                           return 3;
                       }
                   }

                   return 0;
               }
               else {
                   if (table[movimientoX][movimientoY].isRoja() != this.roja && !(table[movimientoX][movimientoY] instanceof  Rey)) {
                       piezaComida = tablero.obtenerPieza(movimientoX, movimientoY);
                       table[movimientoX][movimientoY] = table[posicionX][posicionY];
                       table[posicionX][posicionY] = null;
                       table[movimientoX][movimientoY].setPosicionX(movimientoX); //cambiamos la posicion de la nueva pieza movida, ya que aun mantenia la posicion anteiro
                       table[movimientoX][movimientoY].setPosicionY(movimientoY);
                       if (table[movimientoX][movimientoY].confirmarJaque(tablero, movimientoX, movimientoY)) {// es verdad si luego de mover la pieza aun seguimos en jacke, entonces abajo debemos regresar el movimiento
                           if (table[movimientoX][movimientoY].regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, piezaComida) == true) {// si el valor es verdadero  habremos anulado el movimiento

                               return 3;
                           }
                       }

                       return 1; // pieza comida
                   }
               }
           }

       return  3;
   }


    /**
     * no usaremos este metedo ya que un rey no puede protegerse a si mismo
     * @param tablero
     * @param posicionX
     * @param posicionY
     * @param blanco
     * @return
     */
    @Override
    public boolean protegerRey(Tablero tablero, int posicionX, int posicionY, boolean blanco) {// no lo usaremos pues el rey no puede proteger al rey
        return false;
    }


    /**
     * este metedo nos indicara si el rey ya nop uede moverse por el tablero
     * @param tablero
     * @param blanco
     * @return devuelve un true  si no puede moverse y  false si aun tiene algun movimiento
     */
    public  boolean reySinMovimiento(Tablero tablero, Boolean blanco) {
      int contador = 0;
      int posicionOriginalX= posicionX;
      int posicionOriginalY=posicionY;
      Pieza rey=this;
      Pieza comida;
    Pieza [][ ]table = tablero.getTable();
int movimientoX;
int movimientoY;

          // movimiento arriba
      if (posicionX-1 >=0) {
          if (table[posicionX - 1][posicionY] == null) {
              movimientoX = posicionX - 1;
              movimientoY = posicionY;
              cambiarPosicion(tablero,movimientoX,movimientoY);
              if ((confirmarJaque(tablero, posicionX, posicionY) == true)) {// ahora posicionX = a movimientoX
                  contador++;
              }
              regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);
          }
          else {
              if (table[posicionX - 1][posicionY].isRoja() != this.roja && !(table[posicionX - 1][posicionY] instanceof Rey)) {
                  movimientoX = posicionX - 1;
                  movimientoY = posicionY;
                  comida = tablero.obtenerPieza(posicionX - 1, posicionY);
                  cambiarPosicion(tablero,movimientoX,movimientoY);

                  if ((confirmarJaque(tablero, posicionX, posicionY) == true)) { // la posicion en esta pieza de rey ya cambio y ahora es la poisicion original-1
                      contador++;
                  }
                  regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, comida);
              }
              else {// si hay una pieza nuestra tambien cuenta como espacio bloqueado
                  contador++;  //si ese movieminto esta ocupado por una pieza del mismo color no se podra mover
              }
          }
      }
      else {
          contador++;
      }

                //---------------------------abajo---------------------//
      if (posicionX+1<=7) {
          if (table[posicionX + 1][posicionY] == null) {
              movimientoX = posicionX + 1;
              movimientoY = posicionY;
              table[posicionX + 1][posicionY] = table[posicionX][posicionY];
              table[posicionX][posicionY] = null; // la ubicacion original la dejamos en nulo
              table[movimientoX][movimientoY].setPosicionX(movimientoX);
              table[movimientoX][movimientoY].setPosicionY(movimientoY);
              // abajo

              if (confirmarJaque(tablero , posicionX, posicionY)) {
                  contador++;
              }
              regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);
          } else {
              if (table[posicionX + 1][posicionY].isRoja() != this.roja && !(table[posicionX + 1][posicionY] instanceof Rey)) {
                  movimientoX = posicionX + 1;
                  movimientoY = posicionY;
                  comida = tablero.obtenerPieza(posicionX + 1, posicionY);
                  table[posicionX + 1][posicionY] = table[posicionX][posicionY];
                  table[posicionX][posicionY] = null;
                  table[movimientoX][movimientoY].setPosicionX(movimientoX);
                  table[movimientoX][movimientoY].setPosicionY(movimientoY);

                  if (confirmarJaque(tablero,  posicionX, posicionY)) {
                      contador++;
                  }
                  regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, comida);

              }
              else {//debo colocar aqui si es que esa pieza puede proteger al rey

                  contador++;  //si ese movieminto esta ocupado por una pieza del mismo color no se podra mover
              }

          }
      }
      else {
          contador++;
      }

          //----------------------izquierda-----------------------------------//
        if (posicionY-1>=0) {
            if (table[posicionX][posicionY - 1] == null) {
                movimientoX = posicionX;
                movimientoY = posicionY - 1;
                table[posicionX][posicionY - 1] = table[posicionX][posicionY];
                table[posicionX][posicionY] = null;
                table[movimientoX][movimientoY].setPosicionX(movimientoX);
                table[movimientoX][movimientoY].setPosicionY(movimientoY);

                //izquierda
                if (confirmarJaque(tablero, posicionX, posicionY)) {
                    contador++;
                }
                regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);
            } else {
                if (table[posicionX][posicionY - 1].isRoja() != this.roja && !(table[posicionX][posicionY - 1] instanceof Rey)) {
                    movimientoX = posicionX;
                    movimientoY = posicionY - 1;
                    comida = tablero.obtenerPieza(posicionX, posicionY - 1);
                    table[posicionX][posicionY - 1] = table[posicionX][posicionY];
                    table[posicionX][posicionY] = null;
                    table[movimientoX][movimientoY].setPosicionX(movimientoX);
                    table[movimientoX][movimientoY].setPosicionY(movimientoY);

                    if (confirmarJaque(tablero, posicionX, posicionY)) {
                        contador++;
                    }
                    regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, comida);

                }
                else {
                    contador++;  //si ese movieminto esta ocupado por una pieza del mismo color no se podra mover
                }
            }
        }
        else {
            contador++;// si esta en el limite del tablero ,cuenta como que tmapoco se puede mover ahi
        }


          //-----------------derecha------------------------------------------//
      if (posicionY+1<=7) {
          if (table[posicionX][posicionY + 1] == null) {
              // derecha
              movimientoX = posicionX;
              movimientoY = posicionY + 1;
              table[posicionX][posicionY + 1] = table[posicionX][posicionY];
              table[posicionX][posicionY] = null;
              table[movimientoX][movimientoY].setPosicionX(movimientoX);
              table[movimientoX][movimientoY].setPosicionY(movimientoY);

              if (confirmarJaque(tablero, posicionX, posicionY )) {
                  contador++;
              }
              regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);

          } else {
              if (table[posicionX][posicionY + 1].isRoja() != this.roja && !(table[posicionX][posicionY + 1] instanceof Rey)) {
                  movimientoX = posicionX;
                  movimientoY = posicionY + 1;
                  comida = tablero.obtenerPieza(posicionX, posicionY + 1);
                  table[posicionX][posicionY + 1] = table[posicionX][posicionY];
                  table[posicionX][posicionY] = null;
                  table[movimientoX][movimientoY].setPosicionX(movimientoX);
                  table[movimientoX][movimientoY].setPosicionY(movimientoY);

                  if (confirmarJaque(tablero,  posicionX, posicionY)) {
                      contador++;
                  }
                  regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, comida);

              }
              else {

                  contador++;  //si ese movieminto esta ocupado por una pieza del mismo color no se podra mover
              }
          }

      }
      else {
          contador++;
      }

          // -------------------------------------------arriba derecha --------------------------------------------------/
        if (posicionX-1>=0 && posicionY-1<=7) {

            if (table[posicionX - 1][posicionY + 1] == null) {
                // diagonal arriba derecha
                movimientoX = posicionX - 1;
                movimientoY = posicionY + 1;
                table[posicionX - 1][posicionY + 1] = table[posicionX][posicionY];
                table[posicionX][posicionY] = null;
                table[movimientoX][movimientoY].setPosicionX(movimientoX);
                table[movimientoX][movimientoY].setPosicionY(movimientoY);

                if (confirmarJaque(tablero,  posicionX, posicionY)) {
                    contador++;
                }
                regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);

            } else {
                if (table[posicionX - 1][posicionY + 1].isRoja() != this.roja && !(table[posicionX - 1][posicionY + 1] instanceof Rey)) {
                    movimientoX = posicionX - 1;
                    movimientoY = posicionY + 1;
                    comida = tablero.obtenerPieza(posicionX - 1, posicionY + 1);
                    table[posicionX - 1][posicionY + 1] = table[posicionX][posicionY];
                    table[posicionX][posicionY] = null;
                    table[movimientoX][movimientoY].setPosicionX(movimientoX);
                    table[movimientoX][movimientoY].setPosicionY(movimientoY);

                    if (confirmarJaque(tablero, posicionX, posicionY)) {
                        contador++;
                    }
                    regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, comida);

                }
                else {

                    contador++;  //si ese movieminto esta ocupado por una pieza del mismo color no se podra mover
                }
            }

        }
        else {
            contador++;
        }
          //------------arriba izquierda----------------------------------------------------------------------------//
          if (posicionX-1>=0 && posicionY-1>=0) {
              if (table[posicionX - 1][posicionY - 1] == null) {
                  // diagonal arriba izquierda
                  movimientoX = posicionX - 1;
                  movimientoY = posicionY - 1;
                  table[posicionX - 1][posicionY - 1] = table[posicionX][posicionY];
                  table[posicionX][posicionY] = null;
                  table[movimientoX][movimientoY].setPosicionX(movimientoX);
                  table[movimientoX][movimientoY].setPosicionY(movimientoY);

                  if (confirmarJaque(tablero, posicionX, posicionY)) {
                      contador++;
                  }
                  regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);

              } else {
                  if (table[posicionX - 1][posicionY - 1].isRoja() != this.roja && !(table[posicionX - 1][posicionY - 1] instanceof Rey)) {
                      movimientoX = posicionX - 1;
                      movimientoY = posicionY - 1;
                      comida = tablero.obtenerPieza(posicionX - 1, posicionY - 1);
                      table[posicionX - 1][posicionY - 1] = table[posicionX][posicionY];
                      table[posicionX][posicionY] = null;
                      table[movimientoX][movimientoY].setPosicionX(movimientoX);
                      table[movimientoX][movimientoY].setPosicionY(movimientoY);

                      if (confirmarJaque(tablero, posicionX, posicionY)) {
                          contador++;
                      }
                      regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, comida);

                  }
                  else {

                      contador++;  //si ese movieminto esta ocupado por una pieza del mismo color no se podra mover
                  }
              }
          }
          else {
              contador++;
          }
          //------------------------------------abajo derecha--------------------------------//

         if (posicionX+1<=7 && posicionY +1<=7) {
             if (table[posicionX + 1][posicionY + 1] == null) {
                 // diagonal abajo derecha
                 movimientoX = posicionX + 1;
                 movimientoY = posicionY + 1;
                 table[posicionX + 1][posicionY + 1] = table[posicionX][posicionY];
                 table[posicionX][posicionY] = null;
                 table[movimientoX][movimientoY].setPosicionX(movimientoX);
                 table[movimientoX][movimientoY].setPosicionY(movimientoY);

                 if (confirmarJaque(tablero, posicionX, posicionY)) {
                     contador++;
                 }
                 regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);

             } else {
                 if (table[posicionX + 1][posicionY + 1].isRoja() != this.roja && !(table[posicionX + 1][posicionY + 1] instanceof Rey)) {
                     movimientoX = posicionX + 1;
                     movimientoY = posicionY + 1;
                     comida = tablero.obtenerPieza(posicionX + 1, posicionY + 1);
                     table[posicionX + 1][posicionY + 1] = table[posicionX][posicionY];
                     table[posicionX][posicionY] = null;
                     table[movimientoX][movimientoY].setPosicionX(movimientoX);
                     table[movimientoX][movimientoY].setPosicionY(movimientoY);

                     if (confirmarJaque(tablero,  posicionX, posicionY)) {
                         contador++;
                     }
                     regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, comida);

                 }
                 else {//debo colocar aqui si es que esa pieza puede proteger al rey

                     contador++;  //si ese movieminto esta ocupado por una pieza del mismo color no se podra mover
                 }
             }
         }
         else {
             contador++;
         }

            //-------------------abajo izquierda-------------------------------//
         if (posicionX +1 <=7 && posicionY -1 >=0) {

             if (table[posicionX + 1][posicionY - 1] == null) {
                 // diagonal abajo izquierda
                 movimientoX = posicionX + 1;
                 movimientoY = posicionY - 1;
                 table[posicionX + 1][posicionY - 1] = table[posicionX][posicionY];
                 table[posicionX][posicionY] = null;
                 table[movimientoX][movimientoY].setPosicionX(movimientoX);
                 table[movimientoX][movimientoY].setPosicionY(movimientoY);

                 if (confirmarJaque(tablero, posicionX, posicionY)) {
                     contador++;
                 }
                 regresarMovimiento(tablero, posicionOriginalX, posicionOriginalY);

             } else {
                 if (table[posicionX + 1][posicionY - 1].isRoja() != this.roja && !(table[posicionX + 1][posicionY - 1] instanceof Rey)) {
                     movimientoX = posicionX + 1;
                     movimientoY = posicionY - 1;
                     comida = tablero.obtenerPieza(posicionX + 1, posicionY - 1);
                     table[posicionX + 1][posicionY - 1] = table[posicionX][posicionY];
                     table[posicionX][posicionY] = null;
                     table[movimientoX][movimientoY].setPosicionX(movimientoX);
                     table[movimientoX][movimientoY].setPosicionY(movimientoY);

                     if (confirmarJaque(tablero, posicionX, posicionY)) {
                         contador++;
                     }
                     regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, comida);

                 }
                 else {//debo colocar aqui si es que esa pieza puede proteger al rey

                     contador++;  //si ese movieminto esta ocupado por una pieza del mismo color no se podra mover
                 }
             }
         }
         else {
             contador++;
         }

                    if (contador == 8) {
                        return true;
                    }


      return false;
  }


    public void imprimirRey(){
        if (this.roja) {
            System.out.print(red + "♔" + reset);
        }
        else {
            System.out.print(green + "♔" + reset);
        }
    }


//--------------------espacio abajo
public int  comerRey(Tablero tablero, int movimientoX,int movimientoY) {
    int posicionOriginalX = posicionX;
    int posicionOriginalY = posicionY;
    Pieza piezaComida;
    Pieza[][] table = tablero.getTable();
    //horizontal
    if (table[movimientoX][movimientoY] != null) {
        if (movimientoX == posicionX) {
            if (movimientoY == posicionY - 1 || movimientoY == posicionY + 1) {
                if (table[movimientoX][movimientoY].isRoja() != this.roja) {
                    piezaComida = tablero.obtenerPieza(movimientoX, movimientoY);
                    table[movimientoX][movimientoY] = table[posicionX][posicionY];
                    table[posicionX][posicionY] = null;
                    table[movimientoX][movimientoY].setPosicionX(movimientoX); //cambiamos la posicion de la nueva pieza movida, ya que aun mantenia la posicion anteiro
                    table[movimientoX][movimientoY].setPosicionY(movimientoY);
                    if (table[movimientoX][movimientoY].confirmarJaque(tablero, movimientoX, movimientoY)) {// es verdad si luego de mover la pieza aun seguimos en jacke, entonces abajo debemos regresar el movimiento
                        if (table[movimientoX][movimientoY].regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, piezaComida) == true) {// si el valor es verdadero  habremos anulado el movimiento

                            return 3;

                        }
                    }
                } else {
                    return 2;
                }
            }
        }


        //----------------vertical-------------------------------
        else if (posicionY == movimientoY) {
            if (movimientoX == posicionX - 1 || movimientoX == posicionX + 1) {
                if (table[movimientoX][movimientoY].isRoja() != this.roja) {
                    piezaComida = tablero.obtenerPieza(movimientoX, movimientoY);
                    table[movimientoX][movimientoY] = table[posicionX][posicionY];
                    table[posicionX][posicionY] = null;
                    table[movimientoX][movimientoY].setPosicionX(movimientoX); //cambiamos la posicion de la nueva pieza movida, ya que aun mantenia la posicion anteiro
                    table[movimientoX][movimientoY].setPosicionY(movimientoY);
                    if (table[movimientoX][movimientoY].confirmarJaque(tablero,  movimientoX, movimientoY)) {// es verdad si luego de mover la pieza aun seguimos en jacke, entonces abajo debemos regresar el movimiento
                        if (table[movimientoX][movimientoY].regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, piezaComida) == true) {// si el valor es verdadero  habremos anulado el movimiento

                            return 3;

                        }
                    }
                }
                else {
                    return 2;
                }
            }
        }
//-------------------------------------------------------------------------------------------------------------/


        //----------------------------------diagonal arriba derecha------------------------------------//

        else if (posicionX -1== movimientoX && posicionY+1==movimientoY) {
            if (table[movimientoX][movimientoY].isRoja() != this.roja) {
                piezaComida = tablero.obtenerPieza(movimientoX, movimientoY);
                table[movimientoX][movimientoY] = table[posicionX][posicionY];
                table[posicionX][posicionY] = null;
                table[movimientoX][movimientoY].setPosicionX(movimientoX); //cambiamos la posicion de la nueva pieza movida, ya que aun mantenia la posicion anteiro
                table[movimientoX][movimientoY].setPosicionY(movimientoY);
                if (table[movimientoX][movimientoY].confirmarJaque(tablero, movimientoX, movimientoY)) {// es verdad si luego de mover la pieza aun seguimos en jacke, entonces abajo debemos regresar el movimiento
                    if (table[movimientoX][movimientoY].regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, piezaComida) == true) {// si el valor es verdadero  habremos anulado el movimiento

                        return 3;

                    }
                }
            }
            else {
                return 2;
            }
        }

        //----------------------------------------------------------------------------------------------//

        //-----------------------------diagonal arriba izquierda---------------------------------------------------//
        else if (posicionX -1== movimientoX && posicionY+1==movimientoY) {
            if (table[movimientoX][movimientoY].isRoja() != this.roja) {
                piezaComida = tablero.obtenerPieza(movimientoX, movimientoY);
                table[movimientoX][movimientoY] = table[posicionX][posicionY];
                table[posicionX][posicionY] = null;
                table[movimientoX][movimientoY].setPosicionX(movimientoX); //cambiamos la posicion de la nueva pieza movida, ya que aun mantenia la posicion anteiro
                table[movimientoX][movimientoY].setPosicionY(movimientoY);
                if (table[movimientoX][movimientoY].confirmarJaque(tablero, movimientoX, movimientoY)) {// es verdad si luego de mover la pieza aun seguimos en jacke, entonces abajo debemos regresar el movimiento
                    if (table[movimientoX][movimientoY].regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, piezaComida) == true) {// si el valor es verdadero  habremos anulado el movimiento

                        return 3;

                    }
                }
            }
            else {
                return 2;
            }
        }
        //----------------------------------------------------------------------------------------------//

        //--------------------------diagonal abajo derecha--------------------------------------------------------//
        else if (posicionX -1== movimientoX && posicionY+1==movimientoY) {
            if (table[movimientoX][movimientoY].isRoja() != this.roja) {
                piezaComida = tablero.obtenerPieza(movimientoX, movimientoY);
               cambiarPosicion(tablero,movimientoX,movimientoY);
                if (table[movimientoX][movimientoY].confirmarJaque(tablero, movimientoX, movimientoY)) {// es verdad si luego de mover la pieza aun seguimos en jacke, entonces abajo debemos regresar el movimiento
                    if (table[movimientoX][movimientoY].regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY, piezaComida) == true) {// si el valor es verdadero  habremos anulado el movimiento

                        return 3;

                    }
                }
            }
            else {
                return 2;
            }
        }
        //----------------------------------------------------------------------------------------------//

        //----------------------------diagonal abajo izquierda------------------------------------------------//
        else if (posicionX -1== movimientoX && posicionY+1==movimientoY) {
            if (table[movimientoX][movimientoY].isRoja() != this.roja) {
                piezaComida = tablero.obtenerPieza(movimientoX, movimientoY);
               cambiarPosicionPieza(tablero,movimientoX,movimientoY);
                if (table[movimientoX][movimientoY].confirmarJaque(tablero, movimientoX, movimientoY)) {// es verdad si luego de mover la pieza aun seguimos en jacke, entonces abajo debemos regresar el movimiento
                    if (table[movimientoX][movimientoY].regresarPiezaComida(tablero, posicionOriginalX, posicionOriginalY,piezaComida) == true) {// si el valor es verdadero  habremos anulado el movimiento

                        return 3;

                    }
                }
            }
            else {
                return 2;
            }
        }

    }

    else {
        return 3;
    }
    return 3;
}



    public String mostrarDatosRey(){


        return toString() + ",Rey";
    }
}



