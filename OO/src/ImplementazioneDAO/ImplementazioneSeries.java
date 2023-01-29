package ImplementazioneDAO;

import DAO.SeriesDAO;
import Database.ConnessioneDatabase;
import Model.Book;
import Model.Series;

import java.io.Serial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ImplementazioneSeries implements SeriesDAO {
    private Connection connection;
    private static String GET_ALL_SERIES = "SELECT * FROM MTL.SERIES";
    private static String SEARCH_SERIES_BY_NAME = "SELECT * FOMR MTL.SERIES WHERE NAME_S = ?";

    private static String DELETE_SERIES = "DELETE FROM MTL.SEREIS WHERE ISSN_S = ?";
    private static String CREATE_SERIES = "INSERT INTO mtl.Series (ISSN_S, Curator, Edition, NameS, Code) VALUES (?, ?, ?, ?, ?)";
    private static String UPDATE_SERIES = "UPDATE mtl.series SET curator = ?, edition = ?, code_s = ?, name_s = ? WHERE issn_s = ?;";


    public ImplementazioneSeries() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param ISSN_S
     * @param Curator
     * @param Edition
     * @param NameS
     * @param Code
     */
    @Override
    public void create(String ISSN_S, String Curator, int Edition, String NameS, Serial Code) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_SERIES)) {
            statement.setString(1, ISSN_S);
            statement.setString(2, Curator);
            statement.setInt(3, Edition);
            statement.setString(4, NameS);
            statement.setString(5, String.valueOf(Code));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * @return ArrayList of Series
     */
    @Override
    public ArrayList<Series> readAll() {
        ArrayList<Series> series = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_SERIES)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String ISSN_S = rs.getString("ISSN_S");
                String Curator = rs.getString("Curator");
                int Edition = rs.getInt("Edition");
                String NameS = rs.getString("NameS");
                String Code = rs.getString("Code");
                series.add(new Series(ISSN_S, Curator, Edition, NameS, Code));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return series;
    }


    /**
     * @param ISSN_S
     * @return ArrayList of Series
     */
    @Override
    public ArrayList<Series> CercaPerNome(String name_s) {
        ArrayList<Series> series = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SEARCH_SERIES_BY_NAME)) {
            statement.setString(1, name_s);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String ISSN_S = rs.getString("ISSN_S");
                String Curator = rs.getString("Curator");
                int Edition = rs.getInt("Edition");
                String NameS = rs.getString("NameS");
                String Code = rs.getString("Code");
                series.add(new Series(ISSN_S, Curator, Edition, NameS, Code));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return series;
    }

    /**
     * @param ISSN_S
     * @param Curator
     * @param Edition
     * @param NameS
     * @param Code
     */
    @Override
    public void update(String ISSN_S, String Curator, int Edition, String NameS, Serial Code) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_SERIES)) {
            statement.setString(1, Curator);
            statement.setInt(2, Edition);
            statement.setString(3, String.valueOf(Code));
            statement.setString(4, NameS);
            statement.setString(5, ISSN_S);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param ISSN_S
     */
    @Override
    public void delete(String ISSN_S) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_SERIES)) {
            statement.setString(1, ISSN_S);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
