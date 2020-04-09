package ro.uaic.info.dao;

import java.sql.SQLException;

public class ArtistController {
    public static boolean create(String name, String country) throws DatabaseException {
        var sql = "insert into artists(name, country) values(?, ?)";

        try {
            var stmt = Database.getConnection().prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, country);

            return stmt.execute();
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
}
