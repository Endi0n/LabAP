package ro.uaic.info.app;

import com.github.javafaker.Faker;
import ro.uaic.info.dao.*;

import java.util.LinkedList;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) {
        try {
            var faker = new Faker();

            var artists = IntStream.rangeClosed(1, 10).mapToObj(i -> faker.artist().name()).toArray(String[]::new);
            var countries = IntStream.rangeClosed(1, 10).mapToObj(i -> faker.country().name()).toArray(String[]::new);
            var albums = IntStream.rangeClosed(1, 10).mapToObj(i -> faker.ancient().titan()).toArray(String[]::new);

            for (int i = 0; i < 10; ++i)
                ArtistController.create(artists[i], countries[i]);

            for (int i = 0; i < 10; ++i) {
                var artist = (int) (Math.random() * 10);
                AlbumController.create(
                        albums[i],
                        ArtistController.findByName(artists[artist]).getId(),
                        (int) (Math.random() * 220) + 1800
                );
            }

            var charts = IntStream.range(1, 3).mapToObj(i -> faker.ancient().god()).toArray(String[]::new);

            for (var chart : charts) {
                var chartAlbums = new LinkedList<String>();
                for (int i = 0; i < (int) (Math.random() * 3) + 3; ++i)
                    chartAlbums.add(albums[(int)(Math.random() * albums.length)]);
                ChartController.create(chart, chartAlbums);
            }

            Database.commit();

            for (var artist : ArtistController.getRanking(3))
                System.out.println(artist.getName());

            Database.closeConnection();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
}
