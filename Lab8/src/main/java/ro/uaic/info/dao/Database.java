package ro.uaic.info.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Connection con;

    public static Connection getConnection() throws DatabaseException {
        if (con == null) {
            try {
                Database.con = DriverManager.getConnection(
                        "jdbc:mariadb://localhost:3306/MusicAlbums",
                        "dba",
                        "sql");
            } catch (SQLException e) {
                throw new DatabaseException(e);
            }
        }

        return con;
    }

    public static void commit() throws DatabaseException {
        try {
            con.commit();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public static void closeConnection() throws DatabaseException {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
