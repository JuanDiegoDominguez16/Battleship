package Model;

import java.util.Random;

/**
 * Controlador para el juego Battleship.
 */
public class Controller {
    private Random random = new Random();
    private String humanMessage = "";
    private String machineMessage = "";
    private boolean turnoHumano = true; // true: turno del jugador humano

    private Player humanPlayer;
    private Player machinePlayer;

    /**
     * Constructor que inicializa los tableros y crea los jugadores.
     */
    public Controller() {
        // Se inicializan los tableros
        Model.Board.inicializarTableros(Model.Board.getTableroJugador());
        Model.Board.inicializarTableros(Model.Board.getTableroComputadora());
        // Se inicializan los jugadores
        humanPlayer = new Player("HUMANO", 0);
        machinePlayer = new Player("MÁQUINA", 0);
        // Para el modo estándar se colocan barcos en la computadora
        colocarBarcosAleatoriosComputadoraEstandar();
    }
    
    /**
     * Método wrapper para colocar un barco en el tablero del jugador,
     * de forma que Main no acceda directamente a la clase Table.
     * @param x1 Coordenada X inicial (0-9).
     * @param y1 Coordenada Y inicial (0-9).
     * @param x2 Coordenada X final.
     * @param y2 Coordenada Y final.
     * @return true si se pudo colocar el barco, false en caso contrario.
     */
    public boolean colocarBarcoJugador(int x1, int y1, int x2, int y2) {
        return colocarBarco(Model.Board.getTableroJugador(), x1, y1, x2, y2);
    }

    /**
     * Coloca un barco en el tablero recibido, verificando que la orientación sea horizontal o vertical,
     * que la longitud sea la correcta y que no existan solapamientos con otros barcos.
     * Se marca de forma inclusiva desde (x1,y1) hasta (x2,y2).
     * @param tablero El tablero donde se coloca el barco.
     * @param x1 Coordenada X inicial.
     * @param y1 Coordenada Y inicial.
     * @param x2 Coordenada X final.
     * @param y2 Coordenada Y final.
     * @return true si la colocación fue exitosa, false de lo contrario.
     */
    public boolean colocarBarco(int[][] tablero, int x1, int y1, int x2, int y2) {
        if (!isValid(x1, y1) || !isValid(x2, y2)) return false;
        // Solo se permiten ubicaciones horizontales o verticales
        if (x1 != x2 && y1 != y2) return false;

        int dx = Integer.compare(x2, x1);  // 0, 1 o -1
        int dy = Integer.compare(y2, y1);

        // Verificar que todas las posiciones estén libres (incluyendo la celda final)
        int x = x1, y = y1;
        while (true) {
            if (tablero[y][x] != Model.Board.AGUA) return false;
            if (x == x2 && y == y2) break;
            x += dx;
            y += dy;
        }
        
        // Colocar el barco (marcando cada celda con Table.BARCO)
        x = x1; y = y1;
        while (true) {
            tablero[y][x] = Model.Board.BARCO;
            if (x == x2 && y == y2) break;
            x += dx;
            y += dy;
        }
        return true;
    }

    /**
     * Configura una partida personalizada:
     * - Valida que el número de barcos y la longitud sean válidos.
     * - Reinicializa ambos tableros.
     * - Coloca en el tablero de la computadora tantos barcos como indique el usuario, usando los valores de longitudes.
     * @param numBarcos Número de barcos (entre 1 y 10).
     * @param longitudes Array con la longitud de cada barco (entre 1 y 5).
     * @param orientaciones Array con la orientación de cada barco (true = vertical, false = horizontal).
     * @return true si la configuración es válida y se realizó; false en caso de parámetros inválidos.
     */
    public boolean configurarPartidaPersonalizada(int numBarcos, int[] longitudes, boolean[] orientaciones) {
        // Validar parámetros
        if (numBarcos < 1 || numBarcos > 10 || 
            longitudes == null || orientaciones == null || 
            longitudes.length != numBarcos || orientaciones.length != numBarcos) {
            return false;
        }
        for (int longitud : longitudes) {
            if (longitud < 1 || longitud > 5) return false;
        }
        
        // Reinicializar tableros
        Model.Board.inicializarTableros(Model.Board.getTableroJugador());
        Model.Board.inicializarTableros(Model.Board.getTableroComputadora());
        
        // Coloca en el tablero de la computadora barcos con la configuración personalizada,
        // usando los valores de longitudes del arreglo.
        colocarBarcosAleatoriosComputadoraPersonalizados(numBarcos, longitudes);
        return true;
    }

    /**
     * Coloca un barco personalizado en el tablero del jugador.
     * Calcula la coordenada final a partir de la orientación y longitud, y llama a colocarBarco().
     * @param x Coordenada X inicial (0-9).
     * @param y Coordenada Y inicial (0-9).
     * @param longitud Longitud del barco (entre 1 y 5).
     * @param vertical true si el barco es vertical, false si es horizontal.
     * @return true si la colocación fue exitosa, false si la posición es inválida.
     */
    public boolean colocarBarcoPersonalizado(int x, int y, int longitud, boolean vertical) {
        int xFin = vertical ? x : x + longitud - 1;
        int yFin = vertical ? y + longitud - 1 : y;
        return colocarBarco(Model.Board.getTableroJugador(), x, y, xFin, yFin);
    }
    
    /////////////////////// Métodos de colocación en la computadora ///////////////////////
    
    /**
     * Coloca en el tablero de la computadora los barcos para el modo estándar.
     * Se usa una configuración fija: {2, 2, 3, 3, 4, 5}.
     */
    private void colocarBarcosAleatoriosComputadoraEstandar() {
        int[][] tablero = Model.Board.getTableroComputadora();
        int[] barcos = {2, 2, 3, 3, 4, 5};
        for (int longitud : barcos) {
            boolean colocado = false;
            while (!colocado) {
                int x = random.nextInt(Model.Board.TAMANO_TABLERO);
                int y = random.nextInt(Model.Board.TAMANO_TABLERO);
                boolean vertical = random.nextBoolean();
                int xFin = vertical ? x : x + longitud - 1;
                int yFin = vertical ? y + longitud - 1 : y;
                if (xFin < Model.Board.TAMANO_TABLERO && yFin < Model.Board.TAMANO_TABLERO) {
                    colocado = colocarBarco(tablero, x, y, xFin, yFin);
                }
            }
        }
    }
    
    /**
     * Coloca en el tablero de la computadora los barcos para el modo personalizado,
     * usando el número de barcos y el arreglo de longitudes especificado.
     * Cada barco se coloca de forma aleatoria.
     * @param numBarcos Número de barcos.
     * @param longitudes Array de longitudes para cada barco.
     */
    private void colocarBarcosAleatoriosComputadoraPersonalizados(int numBarcos, int[] longitudes) {
        int[][] tablero = Model.Board.getTableroComputadora();
        for (int i = 0; i < numBarcos; i++) {
            boolean colocado = false;
            int longitud = longitudes[i];
            while (!colocado) {
                int x = random.nextInt(Model.Board.TAMANO_TABLERO);
                int y = random.nextInt(Model.Board.TAMANO_TABLERO);
                boolean vertical = random.nextBoolean();
                int xFin = vertical ? x : x + longitud - 1;
                int yFin = vertical ? y + longitud - 1 : y;
                if (xFin < Model.Board.TAMANO_TABLERO && yFin < Model.Board.TAMANO_TABLERO) {
                    colocado = colocarBarco(tablero, x, y, xFin, yFin);
                }
            }
        }
    }
    
    /**
     * Ejecuta el turno del jugador humano, aplicando el disparo en el tablero de la computadora.
     * @param x Coordenada X del disparo.
     * @param y Coordenada Y del disparo.
     * @return true si el disparo fue válido y se aplicó, false en caso de error.
     */
    public boolean jugarTurnoHumano(int x, int y) {
        if (!isValid(x, y)) {
            humanMessage = "Coordenadas inválidas.";
            return false;
        }
        aplicarDisparo(Model.Board.getTableroComputadora(), x, y, true);
        return true;
    }

    /**
     * Ejecuta el turno de la máquina generando un disparo aleatorio en el tablero del jugador.
     */
    public void jugarTurnoMaquina() {
        int xM, yM;
        do {
            xM = random.nextInt(Model.Board.TAMANO_TABLERO);
            yM = random.nextInt(Model.Board.TAMANO_TABLERO);
        } while (!isValid(xM, yM));
        aplicarDisparo(Model.Board.getTableroJugador(), xM, yM, false);
    }

    /**
     * Aplica el disparo en el tablero indicado. Actualiza el valor de la celda:
     * - Si impacta un barco (valor BARCO), se marca TOCADO.
     * - Si tras el disparo se verifica que el barco esté hundido, se marca HUNDIDO.
     * - Si impacta agua, se indica fallo.
     * @param tablero Tablero objetivo del disparo.
     * @param x Coordenada X del disparo.
     * @param y Coordenada Y del disparo.
     * @param esHumano Indica si el disparo fue del jugador humano.
     */
    public void aplicarDisparo(int[][] tablero, int x, int y, boolean esHumano) {
        if (tablero[y][x] == Model.Board.BARCO) {
            tablero[y][x] = Model.Board.TOCADO;
            if (verificarHundimiento(tablero, x, y)) {
                marcarComoHundido(tablero, x, y);
                if (esHumano) {
                    humanMessage = "¡Hundiste un barco enemigo!";
                } else {
                    machineMessage = "¡La máquina hundió tu barco!";
                }
            } else {
                if (esHumano) {
                    humanMessage = "¡Le diste a un barco enemigo!";
                } else {
                    machineMessage = "¡La máquina te dio!";
                }
            }
        } else if (tablero[y][x] == Model.Board.AGUA) {
            if (esHumano) {
                humanMessage = "¡Fallaste!";
            } else {
                machineMessage = "La máquina falló.";
            }
        } else {
            if (esHumano) {
                humanMessage = "Ya disparaste ahí.";
            } else {
                machineMessage = "La máquina disparó a una casilla repetida.";
            }
        }
    }

    /**
     * Verifica si un barco se hunde en función de si todas las posiciones tocadas ya no tienen vecinos con barco.
     * @param tablero Tablero objetivo.
     * @param x Coordenada X.
     * @param y Coordenada Y.
     * @return true si se determina que el barco quedó hundido.
     */
    private boolean verificarHundimiento(int[][] tablero, int x, int y) {
        return !hayBarcoConectado(tablero, x, y, -1, 0) &&
               !hayBarcoConectado(tablero, x, y, 1, 0) &&
               !hayBarcoConectado(tablero, x, y, 0, -1) &&
               !hayBarcoConectado(tablero, x, y, 0, 1);
    }

    /**
     * Revisa en una dirección (dx, dy) si existe algún segmento de barco aún sin tocar.
     * @param tablero Tablero.
     * @param x Coordenada inicial.
     * @param y Coordenada inicial.
     * @param dx Desplazamiento en X.
     * @param dy Desplazamiento en Y.
     * @return true si encuentra una celda con BARCO.
     */
    private boolean hayBarcoConectado(int[][] tablero, int x, int y, int dx, int dy) {
        int nx = x + dx;
        int ny = y + dy;
        while (isValid(nx, ny) && tablero[ny][nx] != Model.Board.AGUA) {
            if (tablero[ny][nx] == Model.Board.BARCO) {
                return true;
            }
            nx += dx;
            ny += dy;
        }
        return false;
    }

    /**
     * Una vez detectado un hundimiento, se marcan de forma recursiva las casillas tocadas como hundidas.
     * @param tablero Tablero objetivo.
     * @param x Coordenada X.
     * @param y Coordenada Y.
     */
    private void marcarComoHundido(int[][] tablero, int x, int y) {
        marcarRecursivo(tablero, x, y);
    }

    private void marcarRecursivo(int[][] tablero, int x, int y) {
        if (!isValid(x, y) || tablero[y][x] != Model.Board.TOCADO) return;
        tablero[y][x] = Model.Board.HUNDIDO;
        marcarRecursivo(tablero, x + 1, y);
        marcarRecursivo(tablero, x - 1, y);
        marcarRecursivo(tablero, x, y + 1);
        marcarRecursivo(tablero, x, y - 1);
    }

    /**
     * Verifica si las coordenadas están dentro del tablero.
     * @param x Coordenada X.
     * @param y Coordenada Y.
     * @return true si son válidas.
     */
    private boolean isValid(int x, int y) {
        return x >= 0 && x < Model.Board.TAMANO_TABLERO && y >= 0 && y < Model.Board.TAMANO_TABLERO;
    }

    /**
     * Recorre el tablero para determinar si ya no quedan barcos (valor BARCO).
     * @param humano true para verificar si ganó el jugador humano (se revisa el tablero de la computadora),
     *               false para la máquina (se revisa el tablero del jugador).
     * @return true si el respectivo tablero ya no contiene ningún barco.
     */
    public boolean haGanadoJugador(boolean humano) {
        int[][] tablero = humano ? Model.Board.getTableroComputadora() : Model.Board.getTableroJugador();
        for (int i = 0; i < Model.Board.TAMANO_TABLERO; i++) {
            for (int j = 0; j < Model.Board.TAMANO_TABLERO; j++) {
                if (tablero[i][j] == Model.Board.BARCO) return false;
            }
        }
        return true;
    }

    /**
     * Comprueba si hay un ganador. Si se determina un ganador, se actualizan las estadísticas.
     * @return "HUMANO" si gana el jugador, "MÁQUINA" si gana la computadora; o null si no hay ganador.
     */
    public String checkWinner() {
        if (haGanadoJugador(true)) {
            // Significa que en el tablero de la computadora no quedan barcos
            humanPlayer.setGamesWon(humanPlayer.getGamesWon() + 1);
            return "HUMANO";
        } else if (haGanadoJugador(false)) {
            machinePlayer.setGamesWon(machinePlayer.getGamesWon() + 1);
            return "MÁQUINA";
        }
        return null; // No hay ganador aún
    }

    /**
     * Retorna el mensaje resultante del último turno del jugador humano.
     * @return String con el mensaje.
     */
    public String getHumanMessage() {
        return humanMessage;
    }

    /**
     * Retorna el mensaje resultante del último turno de la máquina.
     * @return String con el mensaje.
     */
    public String getMachineMessage() {
        return machineMessage;
    }

    /**
     * Imprime de forma formateada el estado actual de ambos tableros:
     * - Tablero del jugador (completo).
     * - Tablero rival (sólo se muestran las casillas de disparo).
     */
    public void mostrarEstadoTableros() {
        System.out.println("\n# ESTADO ACTUAL DEL JUEGO");
        System.out.println("# Tu tablero:");
        System.out.println(obtenerMatriz(Model.Board.getTableroJugador()));
        System.out.println("# Tablero rival (tus disparos):");
        System.out.println(obtenerMatriz(soloDisparos(Model.Board.getTableroComputadora())));
    }

    /**
     * Devuelve una copia en forma de String de la matriz del tablero, con cabecera de columnas.
     * @param matriz Matriz de tablero.
     * @return Representación textual del tablero.
     */
    public String obtenerMatriz(int[][] matriz) {
        String resultado = "   A B C D E F G H I J\n";
        for (int y = 0; y < Model.Board.TAMANO_TABLERO; y++) {
            resultado += String.format("%2d ", y + 1);
            for (int x = 0; x < Model.Board.TAMANO_TABLERO; x++) {
                resultado += matriz[y][x] + " ";
            }
            resultado += "\n";
        }
        return resultado;
    }

    /**
     * Devuelve una copia del tablero de la computadora, sustituyendo las posiciones de barco por agua,
     * de forma que el jugador solo vea los disparos (TOCADO o HUNDIDO).
     * @param tablero Tablero original.
     * @return Copia del tablero.
     */
    public int[][] soloDisparos(int[][] tablero) {
        int[][] copia = new int[Model.Board.TAMANO_TABLERO][Model.Board.TAMANO_TABLERO];
        for (int y = 0; y < Model.Board.TAMANO_TABLERO; y++) {
            for (int x = 0; x < Model.Board.TAMANO_TABLERO; x++) {
                if (tablero[y][x] == Model.Board.TOCADO || tablero[y][x] == Model.Board.HUNDIDO || tablero[y][x] == Model.Board.AGUA) {
                    copia[y][x] = tablero[y][x];
                } else {
                    copia[y][x] = Model.Board.AGUA;
                }
            }
        }
        return copia;
    }

    /**
     * Intercambia el turno actual entre el jugador humano y la máquina.
     */
    public void switchTurn() {
        turnoHumano = !turnoHumano;
    }

    /**
     * Indica si es actualmente el turno del jugador humano.
     * @return true si es turno del jugador, false en caso contrario.
     */
    public boolean isTurnoHumano() {
        return turnoHumano;
    }

    /**
     * Retorna las estadísticas actuales de victorias (acumuladas de ambos modos de juego).
     * @return String con el reporte de estadísticas.
     */
    public String obtenerEstadisticas() {
        return "# Estadísticas:\n# Jugador: " + humanPlayer.getGamesWon() + "\n# Máquina: " + machinePlayer.getGamesWon();
    }
    
    /**
     * Retorna el estado actual del tablero del jugador en forma de String.
     * @return Representación textual del tablero del jugador.
     */
    public String obtenerTableroJugador() {
        return obtenerMatriz(Model.Board.getTableroJugador());
    }
}