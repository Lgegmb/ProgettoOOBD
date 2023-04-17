package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.System.exit;

/**
 * The type Connessione database.
 */
public class ConnessioneDatabase {

    // ATTRIBUTI
    private static ConnessioneDatabase instance;
    private final String nome = "postgres";
    private final String password = "0000";
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static String driver =  "org.postgresql.Driver";
    /**
     * The Connection.
     */
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

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }


    /**
     * Gets instance.
     *
     * @return the instance
     * @throws SQLException the sql exception
     */
    public static ConnessioneDatabase getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConnessioneDatabase();
        } else if (instance.connection.isClosed()) {
            instance = new ConnessioneDatabase();
        }
        return instance;
    }
}