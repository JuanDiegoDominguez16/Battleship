package Model;

public class Player {
    private String name;
    private int gamesWon;

    /**
     * Constructor del jugador.
     * @param name Nombre del jugador.
     * @param gamesWon Número de partidas ganadas inicialmente (generalmente 0).
     */
    public Player(String name, int gamesWon) {
        this.name = name;
        this.gamesWon = gamesWon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }
}