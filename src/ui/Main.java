package ui;

import Model.Controller;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Controller controller = new Controller();
        boolean jugando = true;

        System.out.println("# Bienvenido a la simulación de Batalla Naval para los estudiantes de Economía de la Universidad ICESI.");
        while (jugando) {
            System.out.println("# Te presentamos las siguientes opciones, ingresa:");
            System.out.println("# 1. Para jugar");
            System.out.println("# 2. Para conocer las estadísticas de victorias");
            System.out.println("# 3. Para salir del programa");
            System.out.print("> ");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("# Selecciona el modo de partida:");
                    System.out.println("# 1. Modo Estándar");
                    System.out.println("# 2. Modo Personalizado");
                    System.out.print("> ");
                    int modo = sc.nextInt();
                    if (modo == 1) {
                        System.out.println("# A continuación, coloca tus barcos (modo Estándar).");
                        colocarTodosLosBarcos(sc, controller);
                    } else if (modo == 2) {
                        System.out.println("# Configuración de partida personalizada:");
                        colocarBarcosPersonalizados(sc, controller);
                    } else {
                        System.out.println("Modo inválido, se asignará el modo Estándar por defecto.");
                        colocarTodosLosBarcos(sc, controller);
                    }
                    jugarPartida(sc, controller);
                    break;
                case 2:
                    System.out.println(controller.obtenerEstadisticas());
                    break;
                case 3:
                    System.out.println("Gracias por jugar. ¡Hasta la próxima!");
                    jugando = false;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
        sc.close();
    }

    /**
     * Permite al usuario colocar sus barcos para la partida estándar.
     * Para cada barco se usa un bucle que verifica que la posición ingresada es válida (incluyendo sus espacios).
     */
    private static void colocarTodosLosBarcos(Scanner sc, Controller controller) {
        // Lancha (1 casilla)
        System.out.println("# Coloca tu Lancha (1 casilla):");
        boolean colocado = false;
        while (!colocado) {
            int[] coords = leerCoordenada(sc);
            if (controller.colocarBarcoJugador(coords[0], coords[1], coords[0], coords[1])) {
                colocado = true;
                System.out.println("# Estado actual de tu tablero:");
                System.out.println(controller.obtenerTableroJugador());
            } else {
                System.out.println("Posición inválida para la Lancha. Intenta nuevamente.");
            }
        }

        // Barco Médico (2 casillas, vertical)
        System.out.println("# Coloca el Barco Médico (2 casillas, vertical):");
        colocado = false;
        while (!colocado) {
            int[] coords = leerCoordenada(sc);
            if (controller.colocarBarcoJugador(coords[0], coords[1], coords[0], coords[1] + 1)) {
                colocado = true;
                System.out.printf("# El Barco Médico se colocará verticalmente en las coordenadas (%d, %d) y (%d, %d).\n",
                        coords[0] + 1, coords[1] + 1, coords[0] + 1, coords[1] + 2);
                System.out.println("# Estado actual de tu tablero:");
                System.out.println(controller.obtenerTableroJugador());
            } else {
                System.out.println("Ubicación inválida para el Barco Médico. Intenta nuevamente.");
            }
        }

        // Barco de Provisiones (3 casillas, horizontal)
        System.out.println("# Coloca el Barco de Provisiones (3 casillas, horizontal):");
        colocado = false;
        while (!colocado) {
            int[] coords = leerCoordenada(sc);
            if (controller.colocarBarcoJugador(coords[0], coords[1], coords[0] + 2, coords[1])) {
                colocado = true;
                System.out.printf("# El Barco de Provisiones se colocará horizontalmente en las coordenadas (%d, %d), (%d, %d) y (%d, %d).\n",
                        coords[0] + 1, coords[1] + 1, coords[0] + 2, coords[1] + 1, coords[0] + 3, coords[1] + 1);
                System.out.println("# Estado actual de tu tablero:");
                System.out.println(controller.obtenerTableroJugador());
            } else {
                System.out.println("Ubicación inválida para el Barco de Provisiones. Intenta nuevamente.");
            }
        }

        // Barco de Munición (3 casillas, vertical)
        System.out.println("# Coloca el Barco de Munición (3 casillas, vertical):");
        colocado = false;
        while (!colocado) {
            int[] coords = leerCoordenada(sc);
            if (controller.colocarBarcoJugador(coords[0], coords[1], coords[0], coords[1] + 2)) {
                colocado = true;
                System.out.printf("# El Barco de Munición se colocará verticalmente en las coordenadas (%d, %d), (%d, %d) y (%d, %d).\n",
                        coords[0] + 1, coords[1] + 1, coords[0] + 1, coords[1] + 2, coords[0] + 1, coords[1] + 3);
                System.out.println("# Estado actual de tu tablero:");
                System.out.println(controller.obtenerTableroJugador());
            } else {
                System.out.println("Ubicación inválida para el Barco de Munición. Intenta nuevamente.");
            }
        }

        // Buque de Guerra (4 casillas, horizontal)
        System.out.println("# Coloca el Buque de Guerra (4 casillas, horizontal):");
        colocado = false;
        while (!colocado) {
            int[] coords = leerCoordenada(sc);
            if (controller.colocarBarcoJugador(coords[0], coords[1], coords[0] + 3, coords[1])) {
                colocado = true;
                System.out.printf("# El Buque de Guerra se colocará horizontalmente desde (%d, %d) hasta (%d, %d).\n",
                        coords[0] + 1, coords[1] + 1, coords[0] + 4, coords[1] + 1);
                System.out.println("# Estado actual de tu tablero:");
                System.out.println(controller.obtenerTableroJugador());
            } else {
                System.out.println("Ubicación inválida para el Buque de Guerra. Intenta nuevamente.");
            }
        }

        // Portaaviones (5 casillas, vertical)
        System.out.println("# Coloca el Portaaviones (5 casillas, vertical):");
        colocado = false;
        while (!colocado) {
            int[] coords = leerCoordenada(sc);
            if (controller.colocarBarcoJugador(coords[0], coords[1], coords[0], coords[1] + 4)) {
                colocado = true;
                System.out.printf("# El Portaaviones se colocará verticalmente desde (%d, %d) hasta (%d, %d).\n",
                        coords[0] + 1, coords[1] + 1, coords[0] + 1, coords[1] + 5);
                System.out.println("# Estado actual de tu tablero:");
                System.out.println(controller.obtenerTableroJugador());
            } else {
                System.out.println("Ubicación inválida para el Portaaviones. Intenta nuevamente.");
            }
        }
    }

    /**
     * Permite configurar y colocar los barcos en el modo personalizado.
     * El jugador ingresa la cantidad de barcos (máximo 10) y, para cada uno, la longitud, la orientación y la posición inicial.
     * Se valida iterativamente cada colocación.
     */
    private static void colocarBarcosPersonalizados(Scanner sc, Controller controller) {
        System.out.print("# Ingresa la cantidad de barcos que deseas colocar (1-10):\n> ");
        int numBarcos = sc.nextInt();
        int[] longitudes = new int[numBarcos];
        boolean[] orientaciones = new boolean[numBarcos]; // true = vertical; false = horizontal

        for (int i = 0; i < numBarcos; i++) {
            System.out.printf("# Para el barco %d:\n", i + 1);
            int longitud;
            do {
                System.out.print("# Ingresa la longitud del barco (1-5):\n> ");
                longitud = sc.nextInt();
                if (longitud < 1 || longitud > 5) {
                    System.out.println("Longitud inválida. Debe ser entre 1 y 5.");
                }
            } while (longitud < 1 || longitud > 5);
            longitudes[i] = longitud;

            System.out.print("# Ingresa la orientación (v para vertical, h para horizontal):\n> ");
            char ori = sc.next().toLowerCase().charAt(0);
            boolean vertical = (ori == 'v');
            orientaciones[i] = vertical;
        }
        // Configura la partida personalizada en el controlador:
        // Reinicializa ambos tableros y coloca los barcos de la computadora con la misma cantidad y longitudes.
        if (!controller.configurarPartidaPersonalizada(numBarcos, longitudes, orientaciones)) {
            System.out.println("Error en la configuración de la partida personalizada.");
            return;
        }
        
        // Colocación de barcos del jugador, uno a uno
        for (int i = 0; i < numBarcos; i++) {
            boolean colocado = false;
            while (!colocado) {
                System.out.printf("# Coloca el barco %d (longitud: %d, orientación: %s):\n",
                                  i + 1, longitudes[i], orientaciones[i] ? "vertical" : "horizontal");
                int[] coords = leerCoordenada(sc);
                if (controller.colocarBarcoPersonalizado(coords[0], coords[1], longitudes[i], orientaciones[i])) {
                    colocado = true;
                    System.out.printf("# Barco %d colocado correctamente.\n", i + 1);
                    System.out.println("# Estado actual de tu tablero:");
                    System.out.println(controller.obtenerTableroJugador());
                } else {
                    System.out.println("Posición inválida para este barco. Intenta nuevamente.");
                }
            }
        }
    }

    /**
     * Lee una coordenada ingresada por el usuario (valores 1-10) y la ajusta al índice 0-9.
     * @param sc Scanner de entrada.
     * @return Arreglo de enteros {x, y} ajustado.
     */
    private static int[] leerCoordenada(Scanner sc) {
        System.out.print("# Ingresa la coordenada X (1-10):\n> ");
        int x = sc.nextInt() - 1;
        System.out.print("# Ingresa la coordenada Y (1-10):\n> ");
        int y = sc.nextInt() - 1;
        return new int[]{x, y};
    }

    /**
     * Controla el desarrollo de la partida, alternando turnos entre jugador y máquina.
     * Se verifica en cada turno si hay un ganador.
     */
    private static void jugarPartida(Scanner sc, Controller controller) {
        while (controller.checkWinner() == null) {
            if (controller.isTurnoHumano()) {
                System.out.println("# Tu turno de disparar.");
                int[] disparo = leerCoordenada(sc);
                if (controller.jugarTurnoHumano(disparo[0], disparo[1])) {
                    System.out.println(controller.getHumanMessage());
                    controller.mostrarEstadoTableros();
                    controller.switchTurn();
                } else {
                    System.out.println("Coordenada inválida. Intenta de nuevo.");
                }
            } else {
                System.out.println("# Turno de la máquina...");
                controller.jugarTurnoMaquina();
                System.out.println(controller.getMachineMessage());
                controller.mostrarEstadoTableros();
                controller.switchTurn();
            }
        }
        String ganador = controller.checkWinner();
        System.out.println("# ¡La partida ha terminado! El ganador es: " + ganador);
    }

    
}