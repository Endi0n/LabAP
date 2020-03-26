import java.util.Map;
import java.util.Scanner;

public class Shell {
    public static Map<String, ICommand> menuCommandMap;
    public static Map<String, ICommand> catalogCommandMap;
    public static Map<String, ICommand> currentCommandMap;

    public static Catalog currentCatalog;

    static {
        menuCommandMap = Map.of(
                "load", (new LoadCommand()),
                "exit", (new ExitCommand())
        );

        catalogCommandMap = Map.of(
                "list", (new ListCommand()),
                "view", (new  ViewCommand()),
                "close", (new CloseCommand())
        );

        currentCommandMap = menuCommandMap;
    }

    static void loop() {
        var in = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            var command = in.nextLine().split(" ", 2);

            if (!currentCommandMap.containsKey(command[0])) {
                System.out.println("Invalid command.");
                continue;
            }

            try {
                if (command.length > 1)
                    currentCommandMap.get(command[0]).execute(command[1].split(" "));
                else
                    currentCommandMap.get(command[0]).execute(null);
            } catch (Exception e) {
                System.out.printf("An exception occurred: %s\n", e.getMessage());
            }
        }
    }
}
