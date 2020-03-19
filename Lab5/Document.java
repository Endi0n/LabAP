import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Document implements Serializable {
    private final String id;
    private String name;
    private String path;
    private Map<String, Object> tags;


    public Document(String id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.tags = new HashMap<>();
    }

    public Document(String id, String name, String path, Map<String, Object> tags) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, Object> getTags() {
        return tags;
    }

    public void addTag(String key, Object value) {
        this.tags.put(key, value);
    }

    public void view() throws InvalidCatalogException {
        var desktop = Desktop.getDesktop();
        try {
            desktop.open(new File(this.path));
        } catch (IOException e) {
            throw new InvalidCatalogException("Could not open document.", e);
        }
    }
}
