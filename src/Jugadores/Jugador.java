package Jugadores;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class Jugador {
    String nombre;
    String id;
    LocalDate fecha;
    int partidasJugadas=0;
    int partidasGanadas=0;
    double porcentaje=0;
    public Jugador(String nombre,String id, int partidasGanadas,int partidasJugadas) {
        this.nombre =nombre;
        this.id = id;
        this.partidasGanadas=partidasGanadas;
        this.partidasJugadas=partidasJugadas;
        this.porcentaje= porcentaje;
    }

    public Jugador(Scanner teclado) {
        System.out.println("ingrese su nombre");
        this.nombre = teclado.next();
        System.out.println("ingrese un identificador");
        this.id = teclado.next();
        this.fecha = LocalDate.now();
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getPartidasJugadas() {
        return partidasJugadas;
    }

    public void setPartidasJugadas(int partidasJugadas) {
        this.partidasJugadas = partidasJugadas;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

public  int aumentarPartidaJugada(){
    setPartidasJugadas(partidasJugadas+1);

        return partidasJugadas;

}


}
