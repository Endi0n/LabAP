import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    private String name;
    private String path;
    private List<Document> documents;

    public static Catalog load(String path) throws InvalidCatalogException {
        try (var ois = new ObjectInputStream(new FileInputStream(path))) {
            return (Catalog) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new InvalidCatalogException("Catalog could not be loaded.", e);
        }
    }

    public void save() throws InvalidCatalogException {
        try (var oos = new ObjectOutputStream(new FileOutputStream(this.path))) {
            oos.writeObject(this);
        } catch (IOException e) {
            throw new InvalidCatalogException("Catalog could not be saved.", e);
        }
    }

    public Catalog(String name, String path, List<Document> documents) {
        this.name = name;
        this.path = path;
        this.documents = documents;
    }

    public Catalog(String name, String path) {
        this.name = name;
        this.path = path;
        this.documents = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void add(Document document) {
        this.documents.add(document);
    }

    public Document findDocById(String id) throws InvalidCatalogException {
        return documents.stream()
                .filter(doc -> doc.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new InvalidCatalogException("Document not found.", null));
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
