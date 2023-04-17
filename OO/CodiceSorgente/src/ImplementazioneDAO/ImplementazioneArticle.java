package ImplementazioneDAO;

import DAO.ArticleDAO;
import Database.ConnessioneDatabase;
import Model.Article;

import java.sql.*;
import java.util.ArrayList;

/**
 * The type Implementazione article.
 */
public class ImplementazioneArticle implements ArticleDAO {
    /**
     * The constant GET_ALL_ARTICLE.
     */
    public static String GET_ALL_ARTICLE = "SELECT *" +
            " FROM (mtl.article ar JOIN mtl.author_article au on ar.doi_a = au.articlesfk) JOIN mtl.author a" +
            " ON a.codauthor= au.authorsfk" +
            " WHERE ar.topic = ?  AND ar.title LIKE '%'|| ? ||'%'";

    /**
     * The constant GET_ALL_TOPICS.
     */
    public static String GET_ALL_TOPICS = "SELECT DISTINCT topic FROM mtl.article";
    private final Connection connection;

    /**
     * Instantiates a new Implementazione article.
     */
    public ImplementazioneArticle() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public ArrayList<Article> readAll(String topic, String title) throws SQLException {
        ArrayList<Article> articles = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_ARTICLE)) {
            statement.setString(1, topic);
            statement.setString(2, title);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String doi_A = rs.getString("Doi_A");
                title = rs.getString("Title");
                String accessMode = rs.getString("AccessMode");
                String editor = rs.getString("Editor");
                topic = rs.getString("Topic");
                Timestamp releaseDate = rs.getTimestamp("ReleaseDate");
                String releaseLocation = rs.getString("ReleaseLocation");
                String conferenceName = rs.getString("ConferenceName");
                String FK_author = rs.getString("Fname");
                String FK_Magazine = rs.getString("FK_Magazine");
                articles.add(new Article(doi_A, title, accessMode, editor, topic, releaseDate, releaseLocation, conferenceName, FK_author, FK_Magazine));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return articles;
    }

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