package ImplementazioneDAO;

import DAO.AuthorDAO;
import Database.ConnessioneDatabase;
import Model.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImplementazioneAuthor implements AuthorDAO {
    public static String GET_ALL_AUTHROS = "SELECT * FROM mtl.Author";
    public static String DELETE_AUTHOR = "DELETE FROM mtl.Author WHERE codauthor = ?";
public static String CREATE_AUTHOR = "INSERT INTO mtl.Author (codauthor, fName, lName) VALUES (?, ?, ?)";


    private Connection connection;

    public ImplementazioneAuthor() {
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        /**
         * @param codauthor
         * @param fName
         * @param lName
         */
    @Override
    public void create(String codauthor, String fName, String lName) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_AUTHOR)) {
            statement.setString(1, codauthor);
            statement.setString(2, fName);
            statement.setString(3, lName);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param codauthor
     */
    @Override
    public void delete(String codauthor) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_AUTHOR)) {
            statement.setString(1, codauthor);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

