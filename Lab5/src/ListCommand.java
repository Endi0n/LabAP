public class ListCommand implements ICommand {
    @Override
    public void execute(String[] args) {
        var documents = Shell.currentCatalog.getDocuments();
        for (var i = 1; i <= documents.size(); ++i)
            System.out.printf("[%d] %s\n", i, documents.get(i - 1).getName());
    }
}
