package Controller;

import DAO.*;
import ImplementazioneDAO.*;
import Model.*;

import java.sql.SQLException;
import java.util.ArrayList;


/**
 * The type Controller.
 */
public class Controller {

    /**
     * Instantiates a new Controller.
     */
    public Controller() {

    }

    /**
     * Read all articles array list.
     *
     * @param topic the topic
     * @param title the title
     * @return the array list
     * @throws SQLException the sql exception
     */
///////////////////////////ARTICLE/////////////////////////////////////
    public ArrayList<Article> readAllArticles(String topic, String title) throws SQLException {
        ArticleDAO articleDAO = new ImplementazioneArticle();
        return articleDAO.readAll(topic, title);
    }


    /**
     * Gets all topics article.
     *
     * @return the all topics article
     * @throws SQLException the sql exception
     */
    public ArrayList<String> getAllTopicsArticle() throws SQLException {
        ArticleDAO articleDAO = new ImplementazioneArticle();
        return articleDAO.getAllTopics();
    }


    ///////////////////////////AUTHOR/////////////////////////////////////

    /**
     * Read all authors array list.
     *
     * @return the array list
     */
    public ArrayList<Author> readAllAuthors() {
        AuthorDAO authorDAO = new ImplementazioneAuthor();
        return authorDAO.readAll();
    }


    /**
     * Read all books array list.
     *
     * @param arg        the arg
     * @param lang       the lang
     * @param accessMode the access mode
     * @param reprint    the reprint
     * @param title      the title
     * @param lname      the lname
     * @return the array list
     * @throws SQLException the sql exception
     */
///////////////////////////BOOK/////////////////////////////////////
    public ArrayList<Book> readAllBooks(String arg, String lang, String accessMode, boolean reprint, String title, String lname) throws SQLException {
        BookDAO bookDAO = new ImplementazioneBook();
        return bookDAO.readAll(arg, lang, accessMode, reprint, title, lname);
    }

    /**
     * Gets all arguments book.
     *
     * @return the all arguments book
     * @throws SQLException the sql exception
     */
    public ArrayList<String> getAllArgumentsBook() throws SQLException {
        BookDAO bookDAO = new ImplementazioneBook();
        return bookDAO.getAllArguments();
    }

    /**
     * Gets all languages book.
     *
     * @return the all languages book
     * @throws SQLException the sql exception
     */
    public ArrayList<String> getAllLanguagesBook() throws SQLException {
        BookDAO bookDAO = new ImplementazioneBook();
        return bookDAO.getAllLanguages();
    }

    /**
     * Gets all access book.
     *
     * @return the all access book
     * @throws SQLException the sql exception
     */
    public ArrayList<String> getAllAccessBook() throws SQLException {
        BookDAO bookDAO = new ImplementazioneBook();
        return bookDAO.getAllAccess();
    }

    ///////////////////////////CONFERENCE/////////////////////////////////////

    /**
     * Gets all conference.
     *
     * @param title the title
     * @return the all conference
     * @throws SQLException the sql exception
     */
    public ArrayList<Conference> getAllConference(String title) throws SQLException{
        ConferenceDAO conferenceDAO = new ImplementazioneConference();
        return conferenceDAO.getAllConference(title);
    }

    /**
     * Read all presentations array list.
     *
     * @param title the title
     * @return the array list
     * @throws SQLException the sql exception
     */
///////////////////////////PRESENTATION/////////////////////////////////////
    public ArrayList<Presentation> readAllPresentations(String title) throws SQLException {
        PresentationDAO presentationDAO = new ImplementazionePresentation();
        return presentationDAO.getAllPresentation(title);
    }

    /**
     * Read all magazines array list.
     *
     * @param publicationPeriod the publication period
     * @param argument          the argument
     * @param name_m            the name m
     * @return the array list
     * @throws SQLException the sql exception
     */
///////////////////////////MAGAZINE////////////////////////////////////
    public ArrayList<Magazine> readAllMagazines(String publicationPeriod, String argument, String name_m) throws SQLException {
        MagazineDAO magazineDAO = new ImplementazioneMagazine();
        return magazineDAO.readAll(publicationPeriod, argument, name_m);
    }

    /**
     * Gets all perioditicita magazine.
     *
     * @return the all perioditicita magazine
     * @throws SQLException the sql exception
     */
    public ArrayList<String> getAllPerioditicitaMagazine() throws SQLException {
        MagazineDAO magazineDAO = new ImplementazioneMagazine();
        return magazineDAO.getAllPeriodicities();
    }

    /**
     * Gets all arguments magazine.
     *
     * @return the all arguments magazine
     * @throws SQLException the sql exception
     */
    public ArrayList<String> getAllArgumentsMagazine() throws SQLException {
        MagazineDAO magazineDAO = new ImplementazioneMagazine();
        return magazineDAO.getAllArguments();
    }

    /**
     * Read all series array list.
     *
     * @param edition the edition
     * @param name    the name
     * @return the array list
     * @throws SQLException the sql exception
     */
///////////////////////////SERIES/////////////////////////////////////
    public ArrayList<Series> readAllSeries(Integer edition, String name) throws SQLException {
        SeriesDAO seriesDAO = new ImplementazioneSeries();
        return seriesDAO.readAll(edition, name);
    }

    /**
     * Gets all edition.
     *
     * @return the all edition
     * @throws SQLException the sql exception
     */
    public ArrayList<String> getAllEdition() throws SQLException {
        SeriesDAO seriesDAO = new ImplementazioneSeries();
        return seriesDAO.getAllEditions();
    }

}