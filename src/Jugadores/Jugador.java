package Jugadores;
import java.time.LocalDate;
public class Jugador {
    String nombre;
    String id;
    LocalDate fecha;
    int partidasJugadas;
    int partidasGanadas;


    /**
     *
     * @param nombre recibimos el nombre del jugador
     * @param id recibimos su identificar
     * @param partidasGanadas cantidad de partidas ganadas
     * @param partidasJugadas cantidad de partidas jugadas
     */
    public Jugador(String nombre,String id, int partidasGanadas,int partidasJugadas) {
        this.nombre =nombre;
        this.id = id;
        this.partidasGanadas=partidasGanadas;
        this.partidasJugadas=partidasJugadas;

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



}
