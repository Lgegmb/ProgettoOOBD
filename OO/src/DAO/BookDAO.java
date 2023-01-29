package DAO;

import Model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public interface BookDAO {

    void create(String doi_B, String ISBN_B, int edition, String publishingHouse,
                       String language, String title, String accessMode, String argument,
                       boolean reprint, Timestamp releaseDate, String releaseLocation, String PresentationName,
                       String FK_author, String FK_Series);


    ArrayList<Book> readAll();

    ArrayList<Book> SearchByTitle(String title);

    void deleteBook(String doi_B);

    void updateBook(String doi_B, String ISBN_B, int edition, String publishingHouse,
                       String language, String title, String accessMode, String argument,
                       boolean reprint, Timestamp releaseDate, String releaseLocation, String PresentationName,
                       String FK_author, String FK_Series);
    ArrayList<Book> SearchByAuthor(String author);
}