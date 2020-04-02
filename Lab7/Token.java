public final class Token implements Comparable<Token> {
    final private int value;

    public Token(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(Token token) {
        return value - token.value;
    }
}
