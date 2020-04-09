package ro.uaic.info.app;

import ro.uaic.info.dao.AlbumController;
import ro.uaic.info.dao.ArtistController;
import ro.uaic.info.dao.Database;
import ro.uaic.info.dao.DatabaseException;

public class App {
    public static void main(String[] args) {
        try {
            ArtistController.create("O-Zone", "Romania");

            var band = ArtistController.findByName("O-Zone");

            AlbumController.create("DiscO-Zone", band.getId(), 2003);

            var album = AlbumController.findByArtist(band.getId());

            System.out.printf("Album title: %s (%d)\n", album.getName(), album.getReleaseYear());

            Database.closeConnection();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
}
