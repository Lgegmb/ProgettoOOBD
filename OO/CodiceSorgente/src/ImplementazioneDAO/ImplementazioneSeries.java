package ImplementazioneDAO;

import DAO.SeriesDAO;
import Database.ConnessioneDatabase;
import Model.Series;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * The type Implementazione series.
 */
public class ImplementazioneSeries implements SeriesDAO {
    private static String GET_ALL_EDITIONS = "SELECT DISTINCT EDITION FROM MTL.SERIES";
    private static String GET_ALL_SERIES = "SELECT * FROM mtl.series WHERE edition = ? AND name_s LIKE '%'|| ? ||'%';";
    private Connection connection;


    /**
     * Instantiates a new Implementazione series.
     */
    public ImplementazioneSeries() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Series> readAll(Integer edition, String name_s) {
        ArrayList<Series> series = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_SERIES)) {
            statement.setInt(1, edition);
            statement.setString(2, name_s);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String ISSN_S = rs.getString("ISSN_S");
                String Curator = rs.getString("Curator");
                int Edition = rs.getInt("Edition");
                String NameS = rs.getString("Name_S");
                String Code = rs.getString("Code_S");
                series.add(new Series(ISSN_S, Curator, Edition, NameS, Code));
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return series;
    }

    /**
     * @return
     */
    @Override
    public ArrayList<String> getAllEditions() {
        ArrayList<String> editions = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_EDITIONS)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                editions.add(rs.getString("edition"));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return editions;

    }
}
