public interface ICommand {
    void execute(String[] args) throws InvalidCatalogException;
}
