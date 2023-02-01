package ImplementazioneDAO;

import DAO.ArticleDAO;
import Database.ConnessioneDatabase;
import Model.Article;

import java.sql.*;
import java.util.ArrayList;

public class ImplementazioneArticle implements ArticleDAO {

    private final Connection connection;

    public static String CREATE_ARTICLE = "INSERT INTO mtl.Series (ISSN_S, Curator, Edition, NameS, Code) VALUES (?, ?, ?, ?, ?)";
    public static String DELETE_ARTICLE = "DELETE FROM mtl.article WHERE ISSN_S = ?";
    public static String UPDATE_ARTICLE = "UPDATE mtl.article SET title = ?, accessmdode = ?, editor = ?, topic = ?, " +
            "releasedate=?, releaselocation=?,conferencename=?,fk_author=?,fk_magazine=? WHERE doi_a= ?;";
    public static String GET_ALL_ARTICLE = "SELECT * FROM mtl.article";
    public static String SEARCH_ARTICLE_BY_AUTHOR_NAME = "SELECT * FROM mtl.article a JOIN mtl.author au ON a.fk_author = au.codauthor WHERE au.Lname = ? OR au.Fname = ?  ";
    public static String SEARCH_ARTICLE_BY_TITLE = "SELECT * FROM mtl.Article WHERE title= '%'||?||'%'";
    public static String SEARCH_ARTICLE_BY_TOPIC = "SELECT * FROM mtl.article WHERE topic = '%'||?||'%'";

    public static String SEARCH_ARTICLE_BY_EDITOR = "SELECT * FROM mtl.article WHERE editor = '%'||?||'%'";
    public static String SEARCH_ARTICLE_BY_MAGAZINE_NAME = "SELECT * FROM mtl.article a JOIN mtl.magazine m ON a.fk_magazine = m.issn_m WHERE m.name ='%'||?||'%'";
    public static String GET_ALL_TOPICS = "SELECT DISTINCT topic FROM mtl.article";

    public ImplementazioneArticle() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void create(String doi_A, String title, String accessMode, String editor, String topic, Timestamp releaseDate, String releaseLocation, String conferenceName, String FK_author, String FK_Magazine) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_ARTICLE)) {
            statement.setString(1, doi_A);
            statement.setString(2, title);
            statement.setString(3, accessMode);
            statement.setString(4, editor);
            statement.setString(5, topic);
            statement.setTimestamp(6, releaseDate);
            statement.setString(7, releaseLocation);
            statement.setString(8, conferenceName);
            statement.setString(9, FK_author);
            statement.setString(10, FK_Magazine);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Article> readAll() {
        ArrayList<Article> articles = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_ARTICLE)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String doi_A = rs.getString("Doi_A");
                String title = rs.getString("Title");
                String accessMode = rs.getString("AccessMode");
                String editor = rs.getString("Editor");
                String topic = rs.getString("Topic");
                Timestamp releaseDate = rs.getTimestamp("ReleaseDate");
                String releaseLocation = rs.getString("ReleaseLocation");
                String conferenceName = rs.getString("ConferenceName");
                String FK_author = rs.getString("FK_Author");
                String FK_Magazine = rs.getString("FK_Magazine");
                articles.add(new Article(doi_A, title, accessMode, editor, topic, releaseDate, releaseLocation, conferenceName, FK_author, FK_Magazine));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return articles;
    }

    @Override
    public ArrayList<Article> CercaPerTitolo(String title) {
        ArrayList<Article> articles = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SEARCH_ARTICLE_BY_TITLE)) {
            statement.setString(1, title);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String doi_A = rs.getString("Doi_A");
                title = rs.getString("Title");
                String accessMode = rs.getString("AccessMode");
                String editor = rs.getString("Editor");
                String topic = rs.getString("Topic");
                Timestamp releaseDate = rs.getTimestamp("ReleaseDate");
                String releaseLocation = rs.getString("ReleaseLocation");
                String conferenceName = rs.getString("ConferenceName");
                String FK_author = rs.getString("FK_Author");
                String FK_Magazine = rs.getString("FK_Magazine");
                articles.add(new Article(doi_A, title, accessMode, editor, topic, releaseDate, releaseLocation, conferenceName, FK_author, FK_Magazine));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return articles;
    }

    @Override
    public ArrayList<Article> CercaPerAutore(String nome_generico) {
        ArrayList<Article> articles = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SEARCH_ARTICLE_BY_AUTHOR_NAME)) {
            statement.setString(1, nome_generico);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String doi_A = rs.getString("Doi_A");
                String title = rs.getString("Title");
                String accessMode = rs.getString("AccessMode");
                String editor = rs.getString("Editor");
                String topic = rs.getString("Topic");
                Timestamp releaseDate = rs.getTimestamp("ReleaseDate");
                String releaseLocation = rs.getString("ReleaseLocation");
                String conferenceName = rs.getString("ConferenceName");
                String FK_author = rs.getString("FK_Author");
                String FK_Magazine = rs.getString("FK_Magazine");
                articles.add(new Article(doi_A, title, accessMode, editor, topic, releaseDate, releaseLocation, conferenceName, FK_author, FK_Magazine));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return articles;
    }

    public void delete(String doi_a) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_ARTICLE)) {
            statement.setString(1, doi_a);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String doi_A, String title, String accessMode, String editor, String topic, Timestamp releaseDate, String releaseLocation, String conferenceName, String FK_author, String FK_Magazine) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_ARTICLE)) {
            statement.setString(1, title);
            statement.setString(2, accessMode);
            statement.setString(3, editor);
            statement.setString(4, topic);
            statement.setTimestamp(5, releaseDate);
            statement.setString(6, releaseLocation);
            statement.setString(7, conferenceName);
            statement.setString(8, FK_author);
            statement.setString(9, FK_Magazine);
            statement.setString(10, doi_A);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Article> CercaPerEditore(String editore) {
        ArrayList<Article> articles = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SEARCH_ARTICLE_BY_EDITOR)) {
            statement.setString(1, editore);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String doi_A = rs.getString("Doi_A");
                String title = rs.getString("Title");
                String accessMode = rs.getString("AccessMode");
                editore = rs.getString("Editor");
                String topic = rs.getString("Topic");
                Timestamp releaseDate = rs.getTimestamp("ReleaseDate");
                String releaseLocation = rs.getString("ReleaseLocation");
                String conferenceName = rs.getString("ConferenceName");
                String FK_author = rs.getString("FK_Author");
                String FK_Magazine = rs.getString("FK_Magazine");
                articles.add(new Article(doi_A, title, accessMode, editore, topic, releaseDate, releaseLocation, conferenceName, FK_author, FK_Magazine));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return articles;
    }

    public ArrayList<Article> CercaPerTopic(String topic) {
        ArrayList<Article> articles = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SEARCH_ARTICLE_BY_TOPIC)) {
            statement.setString(1, topic);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String doi_A = rs.getString("Doi_A");
                String title = rs.getString("Title");
                String accessMode = rs.getString("AccessMode");
                String editor = rs.getString("Editor");
                topic = rs.getString("Topic");
                Timestamp releaseDate = rs.getTimestamp("ReleaseDate");
                String releaseLocation = rs.getString("ReleaseLocation");
                String conferenceName = rs.getString("ConferenceName");
                String FK_author = rs.getString("FK_Author");
                String FK_Magazine = rs.getString("FK_Magazine");
                articles.add(new Article(doi_A, title, accessMode, editor, topic, releaseDate, releaseLocation, conferenceName, FK_author, FK_Magazine));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return articles;
    }

    public ArrayList<Article> CercaPerMagazine(String magazine) {
        ArrayList<Article> articles = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SEARCH_ARTICLE_BY_MAGAZINE_NAME)) {
            statement.setString(1, magazine);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String doi_A = rs.getString("Doi_A");
                String title = rs.getString("Title");
                String accessMode = rs.getString("AccessMode");
                String editor = rs.getString("Editor");
                String topic = rs.getString("Topic");
                Timestamp releaseDate = rs.getTimestamp("ReleaseDate");
                String releaseLocation = rs.getString("ReleaseLocation");
                String conferenceName = rs.getString("ConferenceName");
                String FK_author = rs.getString("FK_Author");
                String FK_Magazine = rs.getString("FK_Magazine");
                articles.add(new Article(doi_A, title, accessMode, editor, topic, releaseDate, releaseLocation, conferenceName, FK_author, FK_Magazine));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return articles;
    }

    /**
     * @return
     */
    @Override
    public ArrayList<String> getAllTopics() {
        ArrayList<String> topics = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_TOPICS)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String topic = rs.getString("Topic");
                topics.add(topic);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return topics;
    }


}





