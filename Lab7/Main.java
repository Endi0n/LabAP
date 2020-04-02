import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        var players = IntStream.rangeClosed(1, 2)
                .mapToObj(i -> new Player("P" + i)).toArray(Player[]::new);

        Game.setInstance(new Board(10), players);

        Game.getInstance().start();

        while (!Game.getInstance().getBoard().isEmpty());

        for (var player :players)
            System.out.println(player);
    }
}
