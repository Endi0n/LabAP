package ro.uaic.info.dao;

public class AlbumDAO {
    private final int id;
    private final String name;
    private final int artistId;
    private final int releaseYear;

    public AlbumDAO(int id, String name, int artistId, int releaseYear) {
        this.id = id;
        this.name = name;
        this.artistId = artistId;
        this.releaseYear = releaseYear;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getArtistId() {
        return artistId;
    }

    public int getReleaseYear() {
        return releaseYear;
    }
}
