import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public final class Board {
    private final List<Token> availableTokens;

    public Board(int numberOfTokens) {
        availableTokens = new LinkedList<>();

        IntStream.rangeClosed(1, numberOfTokens + 1)
                .mapToObj(Token::new).forEach(availableTokens::add);

        Collections.shuffle(availableTokens);
    }

    public synchronized Token getToken(Token token) {
        availableTokens.remove(token);
        return token;
    }

    public synchronized Token viewToken(int i) {
        return availableTokens.get(i);
    }

    public synchronized int getTokenCount() {
        return availableTokens.size();
    }

    public synchronized boolean isEmpty() {
        return availableTokens.isEmpty();
    }

}
