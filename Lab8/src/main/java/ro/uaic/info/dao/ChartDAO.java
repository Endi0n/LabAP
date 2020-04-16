package ro.uaic.info.dao;

import java.util.List;

public class ChartDAO {
    private final int id;
    private final String name;
    private final List<AlbumDAO> albums;

    public ChartDAO(int id, String name, List<AlbumDAO> albums) {
        this.id = id;
        this.name = name;
        this.albums = albums;
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public List<AlbumDAO> getAlbums() {
        return albums;
    }
}
