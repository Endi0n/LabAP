import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    private String name;
    private String path;
    private List<Document> documents;

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
