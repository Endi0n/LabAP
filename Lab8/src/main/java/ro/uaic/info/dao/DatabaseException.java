package ro.uaic.info.dao;

import java.sql.SQLException;

public class DatabaseException extends Exception {

    public DatabaseException(SQLException e) {
        super(e);
    }

    public DatabaseException(String message) {
        super(message);
    }

}
