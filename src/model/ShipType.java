package Model;

public enum ShipType {
    LANCHA(1),
    BARCO_MEDICO(2),
    BARCO_MUNICION(3);
    // Otros tipos (ej. BUQUE_DE_GUERRA, PORTAAVIONES, etc.) se pueden añadir para partidas estándar o personalizadas.

    private final int size;

    ShipType(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}