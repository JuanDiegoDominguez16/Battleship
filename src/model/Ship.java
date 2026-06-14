package Model;

import java.util.ArrayList;

public class Ship {
    private ShipType type;
    private ArrayList<Coordinate> posiciones;
    private int hits;

    /**
     * Constructor que inicializa el barco con su tipo y posiciones.
     * @param type Tipo de barco.
     * @param posiciones Lista de coordenadas que ocupa.
     */
    public Ship(ShipType type, ArrayList<Coordinate> posiciones) {
        this.type = type;
        this.posiciones = posiciones;
        this.hits = 0;
    }

    /**
     * Registra el disparo recibido y actualiza la cantidad de impactos.
     * @param c Coordenada del disparo.
     * @return true si el disparo impacta una posición del barco.
     */
    public boolean recibirDisparo(Coordinate c) {
        for (Coordinate coord : posiciones) {
            if (coord.equals(c)) {
                hits++;
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si el barco se encuentra hundido.
     * @return true si la cantidad de impactos es igual o mayor al tamaño del barco.
     */
    public boolean estaHundido() {
        return hits >= type.getSize();
    }

    public ShipType getType() {
        return type;
    }

    public ArrayList<Coordinate> getPosiciones() {
        return posiciones;
    }
}