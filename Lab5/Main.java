public class Main {

    public static void main(String[] args) {
        var catalog = new Catalog("Test", "./catalog");

        var doc = new Document(
                "doc_class",
                "Document",
                "./src/Document.java"
        );

        doc.addTag("type", "Java Class Source Code");

        catalog.add(doc);

        try {
            catalog.save();
        } catch (InvalidCatalogException e) {
            System.out.println(e.getMessage());
        }

        try {
            var catalog2 = Catalog.load("./catalog");
            catalog2.findDocById("doc_class").view();
        } catch (InvalidCatalogException e) {
            System.out.println(e.getMessage());
        }
    }
}
