package DAO;

import Model.Series;

import java.io.Serial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SeriesDAO {
    void create(String ISSN_S, String Curator, int Edition, String NameS, Serial Code);

    ArrayList<Series> readAll();

    ArrayList<Series> CercaPerNome(String name_s);

    void update(String ISSN_S, String Curator, int Edition, String NameS, Serial Code);

    void delete(String ISSN_S);

}