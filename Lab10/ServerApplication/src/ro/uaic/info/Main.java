package ro.uaic.info;

public class Main {

    public static void main(String[] args) {
        try {
            GameServer.start(8000, 8001);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
