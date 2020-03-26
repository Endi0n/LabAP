public class LoadCommand implements ICommand {
    @Override
    public void execute(String[] args) throws InvalidCatalogException {
        Shell.currentCatalog = CatalogUtil.load(args[0]);
        Shell.currentCommandMap = Shell.catalogCommandMap;
    }
}
