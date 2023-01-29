package ImplementazioneDAO;

import DAO.BookDAO;
import Database.ConnessioneDatabase;
import Model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ImplementazioneBook implements BookDAO {
    private Connection connection;

    public static String INSERT_BOOK = "INSERT INTO mtl.Book (Doi_B, ISBN_B, Edition, PublishingHouse, Language, Title, Argument, AccessMode, " +
            "Reprint, ReleaseDate, ReleaseLocation, PresentationName, FK_author, FK_Series) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public static String READ_ALL_BOOK = "SELECT * FROM mtl.Book b ";

    public static String UPDATE_BOOK = "UPDATE mtl.Book SET Doi_B = ?, ISBN_B = ?, Edition = ?, PublishingHouse = ?, Language = ?, Title = ?, Argument = ?, AccessMode = ?, " +
            "Reprint = ?, ReleaseDate = ?, ReleaseLocation = ?, PresentationName = ?, FK_author = ?, FK_Series = ? WHERE Doi_B = ?;";
    public static String DELETE_BOOK = "DELETE FROM mtl.Book WHERE Doi_B = ?;";

    public static String SEARCH_BY_TITLE = "SELECT * FROM mtl.Book b WHERE b.Title = '%'||?||'%';";

    public static String SEARCH_BY_AUTHOR = "SELECT * FROM mtl.Book b JOIN mtl.author a ON b.fk_author = a.codauthor WHERE a.lname = ? OR a.fname = ?;";

    public ImplementazioneBook() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * @param doi_B
     * @param ISBN_B
     * @param edition
     * @param publishingHouse
     * @param language
     * @param title
     * @param accessMode
     * @param argument
     * @param reprint
     * @param releaseDate
     * @param releaseLocation
     * @param PresentationName
     * @param FK_author
     * @param FK_Series
     * @throws SQLException
     */
    @Override
    public void create(String doi_B, String ISBN_B, int edition, String publishingHouse,
                       String language, String title, String accessMode, String argument, boolean reprint,
                       Timestamp releaseDate, String releaseLocation, String PresentationName, String FK_author, String FK_Series) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_BOOK)) {
            statement.setString(1, doi_B);
            statement.setString(2, ISBN_B);
            statement.setInt(3, edition);
            statement.setString(4, publishingHouse);
            statement.setString(5, language);
            statement.setString(6, title);
            statement.setString(7, argument);
            statement.setString(8, accessMode);
            statement.setBoolean(9, reprint);
            statement.setTimestamp(10, releaseDate);
            statement.setString(11, releaseLocation);
            statement.setString(12, PresentationName);
            statement.setString(13, FK_author);
            statement.setString(14, FK_Series);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Book> readAll() {
        ArrayList<Book> books = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(READ_ALL_BOOK)) {
            statement.executeQuery();
            while (statement.getResultSet().next()) {
                String doi_B = statement.getResultSet().getString("Doi_B");
                String ISBN_B = statement.getResultSet().getString("ISBN_B");
                int edition = statement.getResultSet().getInt("Edition");
                String publishingHouse = statement.getResultSet().getString("PublishingHouse");
                String language = statement.getResultSet().getString("Language");
                String title = statement.getResultSet().getString("Title");
                String argument = statement.getResultSet().getString("Argument");
                String accessMode = statement.getResultSet().getString("AccessMode");
                boolean reprint = statement.getResultSet().getBoolean("Reprint");
                Timestamp releaseDate = statement.getResultSet().getTimestamp("ReleaseDate");
                String releaseLocation = statement.getResultSet().getString("ReleaseLocation");
                String PresentationName = statement.getResultSet().getString("PresentationName");
                String FK_author = statement.getResultSet().getString("FK_author");
                String FK_Series = statement.getResultSet().getString("FK_Series");
                books.add(new Book(doi_B, ISBN_B, edition, publishingHouse, language, title, argument,
                        accessMode, reprint, releaseDate, releaseLocation, PresentationName, FK_author, FK_Series));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    public ArrayList<Book> SearchByTitle(String Title) {
        ArrayList<Book> books = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SEARCH_BY_TITLE)) {
            statement.setString(1, Title);
            statement.executeQuery();
            while (statement.getResultSet().next()) {
                String doi_B = statement.getResultSet().getString("Doi_B");
                String ISBN_B = statement.getResultSet().getString("ISBN_B");
                int edition = statement.getResultSet().getInt("Edition");
                String publishingHouse = statement.getResultSet().getString("PublishingHouse");
                String language = statement.getResultSet().getString("Language");
                String title = statement.getResultSet().getString("Title");
                String argument = statement.getResultSet().getString("Argument");
                String accessMode = statement.getResultSet().getString("AccessMode");
                boolean reprint = statement.getResultSet().getBoolean("Reprint");
                Timestamp releaseDate = statement.getResultSet().getTimestamp("ReleaseDate");
                String releaseLocation = statement.getResultSet().getString("ReleaseLocation");
                String PresentationName = statement.getResultSet().getString("PresentationName");
                String FK_author = statement.getResultSet().getString("FK_author");
                String FK_Series = statement.getResultSet().getString("FK_Series");
                books.add(new Book(doi_B, ISBN_B, edition, publishingHouse, language, title, argument,
                        accessMode, reprint, releaseDate, releaseLocation, PresentationName, FK_author, FK_Series));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    public ArrayList<Book> SearchByAuthor(String Author) {
        ArrayList<Book> books = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SEARCH_BY_AUTHOR)) {
            statement.setString(1, Author);
            statement.setString(2, Author);
            statement.executeQuery();
            while (statement.getResultSet().next()) {
                String doi_B = statement.getResultSet().getString("Doi_B");
                String ISBN_B = statement.getResultSet().getString("ISBN_B");
                int edition = statement.getResultSet().getInt("Edition");
                String publishingHouse = statement.getResultSet().getString("PublishingHouse");
                String language = statement.getResultSet().getString("Language");
                String title = statement.getResultSet().getString("Title");
                String argument = statement.getResultSet().getString("Argument");
                String accessMode = statement.getResultSet().getString("AccessMode");
                boolean reprint = statement.getResultSet().getBoolean("Reprint");
                Timestamp releaseDate = statement.getResultSet().getTimestamp("ReleaseDate");
                String releaseLocation = statement.getResultSet().getString("ReleaseLocation");
                String PresentationName = statement.getResultSet().getString("PresentationName");
                String FK_author = statement.getResultSet().getString("FK_author");
                String FK_Series = statement.getResultSet().getString("FK_Series");
                books.add(new Book(doi_B, ISBN_B, edition, publishingHouse, language, title,
                        argument, accessMode, reprint, releaseDate, releaseLocation, PresentationName, FK_author, FK_Series));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    public void deleteBook(String Doi_B) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_BOOK)) {
            statement.setString(1, Doi_B);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateBook(String doi_B, String ISBN_B, int edition, String publishingHouse, String language, String title,
                           String accessMode, String argument, boolean reprint, Timestamp releaseDate, String releaseLocation,
                           String PresentationName, String FK_author, String FK_Series) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_BOOK)) {
            statement.setString(1, ISBN_B);
            statement.setInt(2, edition);
            statement.setString(3, publishingHouse);
            statement.setString(4, language);
            statement.setString(5, title);
            statement.setString(6, accessMode);
            statement.setString(7, argument);
            statement.setBoolean(8, reprint);
            statement.setTimestamp(9, releaseDate);
            statement.setString(10, releaseLocation);
            statement.setString(11, PresentationName);
            statement.setString(12, FK_author);
            statement.setString(13, FK_Series);
            statement.setString(14, doi_B);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
