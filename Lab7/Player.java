import java.util.ArrayList;
import java.util.List;

public final class Player implements Runnable {
    private final String name;
    private List<Token> tokens;

    public Player(String name) {
        this.name = name;
        tokens = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        var board = Game.getInstance().getBoard();
        while (true) {
            Token token = null;
            try {
                token = board.viewToken(0);
                board.getToken(token);
                tokens.add(token);
            } catch (Exception e) {
                System.out.printf("Player %s too slow to get token.\n", name);
            }
            if (board.isEmpty()) break;
        }
    }

    @Override
    public String toString() {
        var str = new StringBuilder();
        str.append(String.format("Player %s: ", name));

        for (var token : tokens) {
            str.append(String.format("Token(%d); ", token.getValue()));
        }

        return str.toString();
    }
}
