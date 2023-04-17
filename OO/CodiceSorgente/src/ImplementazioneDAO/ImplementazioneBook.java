package ImplementazioneDAO;

import DAO.BookDAO;
import Database.ConnessioneDatabase;
import Model.Book;

import java.sql.*;
import java.util.ArrayList;

/**
 * The type Implementazione book.
 */
public class ImplementazioneBook implements BookDAO {
    private static final String GET_ALL_ACCESS = "SELECT DISTINCT accessmode FROM mtl.book;";
    private static final String GET_ALL_ARGUMENTS = "SELECT  DISTINCT argument FROM mtl.book;";
    private static final String GET_ALL_LANGUAGES = "SELECT DISTINCT language FROM mtl.book;";
    /**
     * The constant READ_ALL_BOOK.
     */
    public static String READ_ALL_BOOK = "SELECT *" +
                                        " FROM (mtl.Book b JOIN mtl.author_books au on b.isbn_b = au.booksfk) JOIN mtl.author a" +
                                        " ON a.codauthor= au.authorsfk" +
                                        " WHERE b.argument = rtrim(?) " +
                                            "AND b.language = rtrim(?) " +
                                            "AND b.accessmode = rtrim(?) " +
                                            "AND b.reprint = ? " +
                                            "AND b.title LIKE '%'|| ? ||'%' " +
                                            "AND a.lname LIKE '%'|| ? ||'%';";

    private Connection connection;

    /**
     * Instantiates a new Implementazione book.
     */
    public ImplementazioneBook() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Book> readAll(String arg, String lang, String accessMode, boolean reprint, String title, String lname) {
        ArrayList<Book> books = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(READ_ALL_BOOK)) {
            statement.setString(1, arg);
            statement.setString(2, lang);
            statement.setString(3, accessMode);
            statement.setBoolean(4, reprint);
            statement.setString(5, title);
            statement.setString(6, lname);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String ISBN_B = rs.getString("isbn_b");
                int edition = rs.getInt("Edition");
                String publishingHouse = rs.getString("PublishingHouse");
                lang = rs.getString("Language");
                title = rs.getString("Title");
                arg = rs.getString("Argument");
                accessMode = rs.getString("AccessMode");
                reprint = rs.getBoolean("Reprint");
                Timestamp releaseDate = rs.getTimestamp("ReleaseDate");
                String releaseLocation = rs.getString("ReleaseLocation");
                String PresentationName = rs.getString("PresentationName");
                String FK_Series = rs.getString("FK_Series");
                lname = rs.getString("Lname");
                books.add(new Book(ISBN_B, edition, publishingHouse, lang, title, arg, accessMode, reprint, releaseDate, releaseLocation, PresentationName, FK_Series, lname));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    @Override
    public ArrayList<String> getAllArguments() {
        ArrayList<String> arguments = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_ARGUMENTS)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String argument1 = rs.getString("Argument");
                arguments.add(argument1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return arguments;
    }

    /**
     * @return
     */
    @Override
    public ArrayList<String> getAllLanguages() {
        ArrayList<String> languages = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_LANGUAGES)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String language = statement.getResultSet().getString("Language");
                languages.add(language);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return languages;
    }

    /**
     * @return
     */
    @Override
    public ArrayList<String> getAllAccess() {
        ArrayList<String> access = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_ACCESS)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String accessMode = statement.getResultSet().getString("AccessMode");
                access.add(accessMode);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return access;
    }

}
