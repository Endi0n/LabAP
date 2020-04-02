public final class Game {
    private static Game currentGame;

    public static Game getInstance() {
        return currentGame;
    }

    public static void setInstance(Board board, Player[] players) {
        currentGame = new Game(board, players);
    }

    private final Board board;
    private final Player[] players;

    private Game(Board board, Player[] players) {
        this.board = board;
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void start() {
        for (var player : players)
           new Thread(player).start();
    }
}
