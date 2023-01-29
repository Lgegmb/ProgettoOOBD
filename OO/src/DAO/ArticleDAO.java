package DAO;

import Model.Article;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public interface ArticleDAO {


    void create(String doi_A, String title, String accessMode, String editor, String topic, Timestamp releaseDate, String releaseLocation, String conferenceName, String FK_author, String FK_Magazine);


    ArrayList<Article> readAll() throws SQLException;

    ArrayList<Article> CercaPerTitolo(String title);

    void update(String doi_A, String title, String accessMode, String editor, String topic, Timestamp releaseDate, String releaseLocation, String conferenceName, String FK_author, String FK_Magazine);

    void delete(String doi_A);

    ArrayList<Article> CercaPerAutore(String nome_generico);

    ArrayList<Article> CercaPerEditore(String editore);

    ArrayList<Article> CercaPerTopic(String topic);

    ArrayList<Article> CercaPerMagazine(String magazine);

}