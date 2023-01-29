package Controller;

import DAO.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;


public class Controller {
    private Connection connection;



    public void closeConnection() throws SQLException {
        connection.close();
    }
}

