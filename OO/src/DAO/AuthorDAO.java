package DAO;

import ImplementazioneDAO.ImplementazioneAuthor;
import Model.Author;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface AuthorDAO {

    void create(String codauthor, String fName, String lName);

    void delete(String codauthor);

    ArrayList<Author> readAll();
}
