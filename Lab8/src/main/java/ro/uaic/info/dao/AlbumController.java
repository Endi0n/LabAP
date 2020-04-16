package ro.uaic.info.dao;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AlbumController {
    public static void create(String name, int artistId, int releaseYear) throws DatabaseException {
        var sql = "insert into albums(name, artist_id, release_year) values(?, ?, ?)";

        try {
            var stmt = Database.getConnection().prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setInt(2, artistId);
            stmt.setInt(3, releaseYear);

            stmt.execute();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public static List<AlbumDAO> findByArtist(int artistId) throws DatabaseException {
        var sql = "select id, name, artist_id, release_year from albums where artist_id = ?";

        try {
            var stmt = Database.getConnection().prepareStatement(sql);
            stmt.setInt(1, artistId);

            var rs = stmt.executeQuery();
            if(!rs.next()) return null;

            var albumList = new LinkedList<AlbumDAO>();

            do {
                albumList.add(new AlbumDAO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
            } while (rs.next());

            return albumList;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public static AlbumDAO findByName(String name) throws DatabaseException {
        var sql = "select id, name, artist_id, release_year from albums where name = ?";
        try {
            var stmt = Database.getConnection().prepareStatement(sql);
            stmt.setString(1, name);

            var rs = stmt.executeQuery();
            if(!rs.next()) return null;

            return new AlbumDAO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public static AlbumDAO findById(int id) throws DatabaseException {
        var sql = "select id, name, artist_id, release_year from albums where id = ?";
        try {
            var stmt = Database.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);

            var rs = stmt.executeQuery();
            if(!rs.next()) return null;

            return new AlbumDAO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
