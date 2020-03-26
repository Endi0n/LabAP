public class CloseCommand implements ICommand {
    @Override
    public void execute(String[] args) {
        Shell.currentCommandMap = Shell.menuCommandMap;
    }
}
