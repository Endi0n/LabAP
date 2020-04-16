package ro.uaic.info.dao;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ArtistController {
    public static void create(String name, String country) throws DatabaseException {
        var sql = "insert into artists(name, country) values(?, ?)";

        try {
            var stmt = Database.getConnection().prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, country);

            stmt.execute();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public static ArtistDAO findByName(String name)  throws DatabaseException {
        var sql = "select id, name, country from artists where name = ?";

        try {
            var stmt = Database.getConnection().prepareStatement(sql);
            stmt.setString(1, name);

            var rs = stmt.executeQuery();
            if(!rs.next()) return null;

            return new ArtistDAO(rs.getInt(1), rs.getString(2), rs.getString(3));
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public static ArtistDAO findById(int id)  throws DatabaseException {
        var sql = "select id, name, country from artists where id = ?";

        try {
            var stmt = Database.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);

            var rs = stmt.executeQuery();
            if(!rs.next()) return null;

            return new ArtistDAO(rs.getInt(1), rs.getString(2), rs.getString(3));
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public static List<ArtistDAO> getRanking(int limit) throws DatabaseException {
        var sql = "select a.artist_id, sum(1 / c.position) as position from charts_content c join albums a on a.id = c.album_id group by a.artist_id order by position desc limit ?";

        try {
            var stmt = Database.getConnection().prepareStatement(sql);
            stmt.setInt(1, limit);

            var rs = stmt.executeQuery();
            if(!rs.next()) return null;

            var artistList = new LinkedList<ArtistDAO>();
            do {
                artistList.add(ArtistController.findById(rs.getInt(1)));
            } while(rs.next());

            return artistList;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
