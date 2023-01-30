package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.System.exit;

public class ConnessioneDatabase {

    // ATTRIBUTI
    private static ConnessioneDatabase instance;
    private final String nome = "postgres";
    private final String password = "0000";
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String driver = "org.postgresql.Driver";
    public Connection connection;

    // COSTRUTTORE
    private ConnessioneDatabase() throws SQLException {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, nome, password);

        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
            ex.printStackTrace();
            exit(-1);
        }
    }

    public Connection getConnection() {
        return connection;
    }


    public static ConnessioneDatabase getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConnessioneDatabase();
        } else if (instance.connection.isClosed()) {
            instance = new ConnessioneDatabase();
        }
        return instance;
    }
}