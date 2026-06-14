package Model;

public class Coordinate {
    private int fila;
    private int columna;

    /**
     * Constructor que inicializa la coordenada.
     * @param fila Fila (0-9).
     * @param columna Columna (0-9).
     */
    public Coordinate(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Coordinate))
            return false;
        Coordinate otro = (Coordinate) obj;
        return this.fila == otro.fila && this.columna == otro.columna;
    }
    
    @Override
    public int hashCode() {
        return fila * 31 + columna;
    }
}