import Tablero.Tablero;

import java.util.Scanner;

public class probar {
    public static void main(String[] args) {
        Scanner teclado=new Scanner(System.in);
        Tablero tablero = new Tablero();
         tablero.startTablero();
         tablero.play(teclado,"a","d",0);
    }
}
