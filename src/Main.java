import Jugadores.Jugador;
import Tablero.Tablero;
import baseDatos.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static  Jugador jugador1;
    static  Jugador jugador2;
    public static void main(String[] args) {
        String ID;
        boolean salir = false;
        int opcion1;
        boolean salir3=false;
        int turno = 1;
        boolean desbloqueado = false;
        Scanner teclado = new Scanner(System.in);
        boolean valorCorrecto = false;
        while (valorCorrecto == false) {
            try {

                do {
                    System.out.println("bienvenidoos");
                    System.out.println("1 ver estadisticas");
                    System.out.println("2 jugar");
                    System.out.println("3 salir");
                    int opcion2 = teclado.nextInt();
                    switch (opcion2) {
                        case 1:
                            System.out.println("ingresa tu ID");
                            ID = teclado.next();
                            baseDatos.mostrarDatos(ID);
                            break;


                        case 2:

                            do {
                                System.out.println("iniciamos con el jugador " + turno);

                                System.out.println("1 ya tienes cuenta");
                                System.out.println("2 no tienes cuenta");
                                System.out.println("3 salir ");
                                opcion1 = teclado.nextInt();
                                switch (opcion1) {
//------------------------------------------------iniciar sesion-----------------------------------------------//
                                    case 1:
                                        System.out.println("ingrese su ID");
                                        ID = teclado.next();
                                        if (baseDatos.obtenerJugador(ID) != null) {
                                            System.out.println(" bienvenido jugador: ");
                                            baseDatos.mostrarDatos(ID);
                                            if (turno == 1) {
                                                jugador1 = baseDatos.obtenerJugador(ID);
                                            }

                                            if (turno == 2) {
                                                jugador2 = baseDatos.obtenerJugador(ID);
                                            }
                                            turno++;// usamos este contador para pasar 2 veces el bucle
                                            if (turno > 2) {
                                                salir = true;
                                                valorCorrecto = true;
                                                desbloqueado = true;
                                                salir3=true;
                                            }

                                        } else {
                                            System.out.println(" no existe un usuario con ese ID");
                                        }
                                        break;
                                    //----------------------------CREAR CUENTA---------------------------------//

                                    case 2:

                                        boolean salir2 = false;
                                        do {


                                            System.out.println("Empezaremos a crear tu cuenta nueva");
                                            System.out.println("quieres continuar?");
                                            System.out.println("1 Si");
                                            System.out.println("2 No");
                                            int respuesta = teclado.nextInt();
                                            if (respuesta == 1) {
                                                System.out.println("ingresa un ID");
                                                String id = teclado.next();
                                                System.out.println("ingresa tu nombre");
                                                String nombre = teclado.next();
                                                if (baseDatos.InsertarJugador2(id, nombre)) {
                                                    if (turno == 1) {
                                                        jugador1 = baseDatos.obtenerJugador(id);
                                                    }
                                                    if (turno == 2) {
                                                        jugador2 = baseDatos.obtenerJugador(id);
                                                    }
                                                    turno++;
                                                    if (turno > 2) {
                                                        salir = true;
                                                        valorCorrecto = true;
                                                        desbloqueado = true;
                                                        salir3=true;
                                                    }

                                                    salir2 = true;
                                                }
                                            } else if (respuesta == 2) {
                                                salir2 = true;
                                            }
                                        }
                                        while (salir2 == false);
                                        break;

                                    case 3:
                                        salir = true;
                                        valorCorrecto = true;
                                        break;
                                    default:
                                        System.out.println("error de opcion");
                                }

                            }
                            while (salir == false);
                            break;

                        case 3:
                            salir3 = true;
                            valorCorrecto = true;
                            break;
                        default:
                            System.out.println("elige un valor correcto");
                    }
                }
                while (salir3 == false);

            } catch (InputMismatchException ime) {
                System.out.println("valor ingresado invalido");
                System.out.println("ingrese un valor correcto");
                valorCorrecto = false;
                teclado = new Scanner(System.in);
            }
        }







//----------------------------empezar el juego-------------------------------------------------//
       boolean valorCorrecto2=false;
        boolean salirJuego=false;
        while (valorCorrecto2==false) {
           try {

              do {


                  if (desbloqueado == true) {
                      Tablero tablero = new Tablero();
                      System.out.println(" 1 nueva partida ");
                      System.out.println("2 partida guardada");
                      int opcion = teclado.nextInt();
                      switch (opcion) {
                          case 1:
                              System.out.println("la partida va a empezar ");
                              System.out.println("el color rojo sera el jugador: " + jugador1.getNombre());
                              System.out.println("y el color verde sera el jugador: " + jugador2.getNombre());
                              tablero.startTablero();
                              tablero.play(teclado, jugador1.getId(), jugador2.getId());
                              break;
                          case 2:
                              System.out.println("ingrese el nombre de la partida");
                              String nombre = teclado.next();

                              break;

                          case 3:

                              break;

                          default:
                              System.out.println("elige un valor correcto");


                      }
                  }
              }
              while (salirJuego==false);





           } catch (InputMismatchException ime) {
               System.out.println("ingresar un valor adecuado");
               teclado = new Scanner(System.in);
           }

       }










    }
}