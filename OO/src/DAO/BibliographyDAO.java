package DAO;

import Model.Bibliography;
import Model.Book;

import java.sql.*;
import java.util.ArrayList;

public class BibliographyDAO {

    private Connection connection;

    public BibliographyDAO(Connection connection) {
        this.connection = connection;
    }


    public ArrayList<Bibliography> readAllBibliography() throws SQLException {
        String query = "SELECT * FROM mtl.Bibliography b ";
        ArrayList<Bibliography> books = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString("Title");
                Timestamp releaseDate = resultSet.getTimestamp("ReleaseDate");
                String Lname = resultSet.getString("Lname");
                books.add(new Bibliography(title, releaseDate, Lname));
            }
        }
        return books;
    }
}
