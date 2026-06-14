package Model;

public class Board {
    public static final int TAMANO_TABLERO = 10; // Tamaño del tablero de juego.
    
    public static final int AGUA = 0;    // Representa agua.
    public static final int BARCO = 1;   // Representa una celda ocupada por un barco.
    public static final int TOCADO = 2;  // Representa que un disparo impactó el barco.
    public static final int HUNDIDO = 3; // Representa que el barco quedó hundido.
    
    private static int[][] tableroJugador = new int[TAMANO_TABLERO][TAMANO_TABLERO];
    private static int[][] tableroComputadora = new int[TAMANO_TABLERO][TAMANO_TABLERO];

    /**
     * Inicializa el tablero llenándolo con AGUA.
     * @param tablero Matriz a inicializar.
     */
    public static void inicializarTableros(int[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = AGUA;
            }
        }
    }

    public static int[][] getTableroJugador() {
        return tableroJugador;
    }

    public static int[][] getTableroComputadora() {
        return tableroComputadora;
    }
}