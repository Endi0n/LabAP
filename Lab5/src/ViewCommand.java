public class ViewCommand implements ICommand {
    @Override
    public void execute(String[] args) throws InvalidCatalogException {
        var i = Integer.parseInt(args[0]);
        var document = Shell.currentCatalog.getDocuments().get(i - 1);
        CatalogUtil.view(document);
    }
}
