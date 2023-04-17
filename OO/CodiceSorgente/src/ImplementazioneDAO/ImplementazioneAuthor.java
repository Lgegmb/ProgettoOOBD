package ImplementazioneDAO;

import DAO.AuthorDAO;
import Database.ConnessioneDatabase;
import Model.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The type Implementazione author.
 */
public class ImplementazioneAuthor implements AuthorDAO {
    /**
     * The constant GET_ALL_AUTHROS.
     */
    public static String GET_ALL_AUTHROS = "SELECT * FROM mtl.Author";

    private Connection connection;

    /**
     * Instantiates a new Implementazione author.
     */
    public ImplementazioneAuthor() {
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
     public ArrayList<Author> readAll() {
        ArrayList<Author> books = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_AUTHROS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String authorID = resultSet.getString("codauthor");
                String fName = resultSet.getString("FName");
                String lName = resultSet.getString("LName");
                books.add(new Author(authorID, fName, lName));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }
}

