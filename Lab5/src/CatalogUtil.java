import com.google.gson.Gson;

import java.awt.*;
import java.io.*;

public final class CatalogUtil {
    public static Catalog load(String path) throws InvalidCatalogException{
        // switch by file extension
        switch (path.substring(path.lastIndexOf("."))) {
            case ".os":
                return CatalogUtil.loadObjectStream(path);
            case ".json":
                return CatalogUtil.loadFromJson(path);
            default:
                throw new InvalidCatalogException("Invalid catalog extension.", null);
        }
    }

    public static void save(Catalog catalog) throws InvalidCatalogException {
        // switch by file extension
        switch (catalog.getPath().substring(catalog.getPath().lastIndexOf("."))) {
            case ".os":
                CatalogUtil.saveObjectStream(catalog);
                break;
            case ".json":
                CatalogUtil.saveAsJson(catalog);
                break;
            default:
                throw new InvalidCatalogException("Invalid catalog extension.", null);
        }
    }

    private static Catalog loadObjectStream(String path) throws InvalidCatalogException {
        try (var ois = new ObjectInputStream(new FileInputStream(path))) {
            return (Catalog) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new InvalidCatalogException("Catalog could not be loaded.", e);
        }
    }

    private static void saveObjectStream(Catalog catalog) throws InvalidCatalogException {
        try (var oos = new ObjectOutputStream(new FileOutputStream(catalog.getPath()))) {
            oos.writeObject(catalog);
        } catch (IOException e) {
            throw new InvalidCatalogException("Catalog could not be saved.", e);
        }
    }

    private static Catalog loadFromJson(String path) throws InvalidCatalogException{
        try (var fis = new FileInputStream(path)) {
            // FileReader nu suporta readAll, FileInputStream este mai eficient in acest caz
            return (new Gson()).fromJson(new String(fis.readAllBytes()), Catalog.class);
        } catch (IOException e) {
            throw new InvalidCatalogException("Catalog could not be loaded.", e);
        }
    }

    private static void saveAsJson(Catalog catalog) throws InvalidCatalogException {
        try (var fw = new FileWriter(catalog.getPath())) {
            fw.write((new Gson().toJson(catalog)));
        } catch (IOException e) {
            throw new InvalidCatalogException("Catalog could not be saved.", e);
        }
    }

    public static void view(Document document) throws InvalidCatalogException {
        var desktop = Desktop.getDesktop();
        try {
            desktop.open(new File(document.getPath()));
        } catch (IOException e) {
            throw new InvalidCatalogException("Could not open document.", e);
        }
    }
}
