package DAO;

import Model.Article;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ArticleDAO {

    ArrayList<Article> readAll(String topic, String title) throws SQLException;

    ArrayList<String> getAllTopics();

}