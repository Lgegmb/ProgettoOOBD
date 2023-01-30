package Controller;

import DAO.*;
import ImplementazioneDAO.*;
import Model.*;

import java.io.Serial;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;


public class Controller {

    public Controller() {

    }

    ///////////////////////////AUTHOR/////////////////////////////////////

    public void createAuthor(String codauthor, String fName, String lName) {
        AuthorDAO authorDAO = new ImplementazioneAuthor();
        authorDAO.create(codauthor, fName, lName);
    }

    public void deleteAuthor(String codauthor) {
        AuthorDAO authorDAO = new ImplementazioneAuthor();
        authorDAO.delete(codauthor);
    }

    public ArrayList<Author> readAllAuthors() {
        AuthorDAO authorDAO = new ImplementazioneAuthor();
        return authorDAO.readAll();
    }

    ///////////////////////////ARTICLE/////////////////////////////////////
    public ArrayList<Article> readAllArticles() throws SQLException {
        ArticleDAO articleDAO = new ImplementazioneArticle();
        return articleDAO.readAll();
    }

    public ArrayList<Article> CercaPerTitoloArticle(String titolo) throws SQLException {
        ArticleDAO articleDAO = new ImplementazioneArticle();
        return articleDAO.CercaPerTitolo(titolo);
    }

    public ArrayList<Article> CercaPerAutoreArticle(String autore) throws SQLException {
        ArticleDAO articleDAO = new ImplementazioneArticle();
        return articleDAO.CercaPerAutore(autore);
    }

    public void deleteArticle(String doi_A) throws SQLException {
        ArticleDAO articleDAO = new ImplementazioneArticle();
        articleDAO.delete(doi_A);
    }

    public void updateArticle(String doi_A, String title, String accessMode, String editor, String topic, Timestamp releaseDate,
                              String releaseLocation, String conferenceName, String FK_author, String FK_Magazine) throws SQLException {
        ArticleDAO articleDAO = new ImplementazioneArticle();
        articleDAO.update(doi_A, title, accessMode, editor, topic, releaseDate, releaseLocation, conferenceName, FK_author, FK_Magazine);
    }

    public ArrayList<Article> CercaPerEditoreArticle(String editore) throws SQLException {
        ArticleDAO articleDAO = new ImplementazioneArticle();
        return articleDAO.CercaPerEditore(editore);
    }

    public ArrayList<Article> CercaPerTopicArticle(String topic) throws SQLException {
        ArticleDAO articleDAO = new ImplementazioneArticle();
        return articleDAO.CercaPerTopic(topic);
    }

    ///////////////////////////MAGAZINE/////////////////////////////////////
    public ArrayList<Magazine> CercaPerMagazine(String name_M) throws SQLException {
        MagazineDAO magazineDAO = new ImplementazioneMagazine();
        return magazineDAO.searchByMagazineName(name_M);
    }

    public void createMagazine(String ISSN_M, String name_M, String argument, String manager,
                               Timestamp yearRelease, String publicationPeriod, String publishingHouse,
                               String accessMode, String FK_author) {
        MagazineDAO magazineDAO = new ImplementazioneMagazine();
        magazineDAO.create(ISSN_M, name_M, argument, manager, yearRelease, publicationPeriod, publishingHouse, accessMode, FK_author);
    }

    public void deleteMagazine(String ISSN_M) throws SQLException {
        MagazineDAO magazineDAO = new ImplementazioneMagazine();
        magazineDAO.delete(ISSN_M);
    }

    public void updateMagazine(String ISSN_M, String name_M, String argument, String manager,
                               Timestamp yearRelease, String publicationPeriod, String publishingHouse,
                               String accessMode, String FK_author) throws SQLException {
        MagazineDAO magazineDAO = new ImplementazioneMagazine();
        magazineDAO.update(ISSN_M, name_M, argument, manager, yearRelease, publicationPeriod, publishingHouse, accessMode, FK_author);
    }

    public ArrayList<Magazine> readAllMagazines() throws SQLException {
        MagazineDAO magazineDAO = new ImplementazioneMagazine();
        return magazineDAO.readAll();
    }
    ///////////////////////////BOOK/////////////////////////////////////

    public void createBook(String doi_B, String ISBN_B, int edition_B, String publishingHouse, String language, String title, String accessMode,
                           String argument, boolean reprint, Timestamp releaseDate, String ReleaseLocation, String PresentationName,
                           String FK_Author, String FK_Series) throws SQLException {
        BookDAO bookDAO = new ImplementazioneBook();
        bookDAO.create(doi_B, ISBN_B, edition_B, publishingHouse, language, title, accessMode, argument, reprint, releaseDate, ReleaseLocation,
                PresentationName, FK_Author, FK_Series);
    }

    public void deleteBook(String doi_B) throws SQLException {
        BookDAO bookDAO = new ImplementazioneBook();
        bookDAO.deleteBook(doi_B);
    }

    public void updateBook(String doi_B, String ISBN_B, int edition, String publishingHouse, String language, String title,
                           String accessMode, String argument, boolean reprint, Timestamp releaseDate, String releaseLocation,
                           String PresentationName, String FK_author, String FK_Series) throws SQLException {
        BookDAO bookDAO = new ImplementazioneBook();
        bookDAO.updateBook(doi_B, ISBN_B, edition, publishingHouse, language, title, accessMode, argument, reprint, releaseDate, releaseLocation,
                PresentationName, FK_author, FK_Series);
    }


    public ArrayList<Book> SearchByAuthorBook(String Author) throws SQLException {
        BookDAO bookDAO = new ImplementazioneBook();
        return bookDAO.SearchByAuthor(Author);
    }

    public ArrayList<Book> SearchByTitleBook(String Title) {
        BookDAO bookDAO = new ImplementazioneBook();
        return bookDAO.SearchByTitle(Title);
    }

    public ArrayList<Book> readAllBooks() throws SQLException {
        BookDAO bookDAO = new ImplementazioneBook();
        return bookDAO.readAll();
    }

    ///////////////////////////SERIES/////////////////////////////////////


    public void createSeries(String ISSN_S, String Curator, int Edition, String NameS, Serial Code) {
        SeriesDAO seriesDAO = new ImplementazioneSeries();
        seriesDAO.create(ISSN_S, Curator, Edition, NameS, Code);
    }

    public void deleteSeries(String ISSN_S) throws SQLException {
        SeriesDAO seriesDAO = new ImplementazioneSeries();
        seriesDAO.delete(ISSN_S);
    }

    public void updateSeries(String ISSN_S, String Curator, int Edition, String NameS, Serial Code) throws SQLException {
        SeriesDAO seriesDAO = new ImplementazioneSeries();
        seriesDAO.update(ISSN_S, Curator, Edition, NameS, Code);
    }

    public ArrayList<Series> readAllSeries() throws SQLException {
        SeriesDAO seriesDAO = new ImplementazioneSeries();
        return seriesDAO.readAll();
    }

    public ArrayList<Series> SearchSeriesByName(String NameS) {
        SeriesDAO seriesDAO = new ImplementazioneSeries();
        return seriesDAO.CercaPerNome(NameS);
    }

}