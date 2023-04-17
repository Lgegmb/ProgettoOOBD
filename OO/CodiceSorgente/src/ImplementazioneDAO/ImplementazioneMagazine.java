package ImplementazioneDAO;

import DAO.MagazineDAO;
import Database.ConnessioneDatabase;
import Model.Magazine;

import java.sql.*;
import java.util.ArrayList;


/**
 * The type Implementazione magazine.
 */
public class ImplementazioneMagazine implements MagazineDAO {

    /**
     * The constant READ_ALL_MAGAZINE.
     */
    public static String READ_ALL_MAGAZINE = "SELECT * FROM mtl.Magazine WHERE PublicationPeriod = ? AND Argument = ? AND name_m LIKE '%'|| ? ||'%'";
    /**
     * The constant GET_ALL_PERIODICITIES.
     */
    public static String GET_ALL_PERIODICITIES = "SELECT DISTINCT PublicationPeriod FROM mtl.Magazine";
    private static String GET_ALL_ARGUMENTS = "SELECT DISTINCT Argument FROM mtl.Magazine";
    private Connection connection;

    /**
     * Instantiates a new Implementazione magazine.
     */
    public ImplementazioneMagazine() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Magazine> readAll(String publicationPeriod, String argument, String name_m) {
        ArrayList<Magazine> magazines = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(READ_ALL_MAGAZINE)) {
            statement.setString(1, publicationPeriod);
            statement.setString(2, argument);
            statement.setString(3, name_m);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String ISSN_M = rs.getString("ISSN_M");
                String name_M = rs.getString("Name_M");
                argument = rs.getString("Argument");
                String manager = rs.getString("Manager");
                Timestamp yearRelease = Timestamp.valueOf(rs.getString("YearRelease"));
                publicationPeriod = rs.getString("PublicationPeriod");
                String publishingHouse = rs.getString("PublishingHouse");
                String accessMode = rs.getString("AccessMode");
                magazines.add(new Magazine(ISSN_M, name_M, argument, manager, yearRelease, publicationPeriod, publishingHouse, accessMode));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return magazines;
    }

    @Override
    public ArrayList<String> getAllPeriodicities() {
        ArrayList<String> perioditcita = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_PERIODICITIES)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String publicationPeriod = rs.getString("PublicationPeriod");
                perioditcita.add(publicationPeriod);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return perioditcita;
    }

    /**
     * @return
     */
    @Override
    public ArrayList<String> getAllArguments() {
        ArrayList<String> arguments = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_ARGUMENTS)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String argument = rs.getString("Argument");
                arguments.add(argument);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return arguments;
    }
}


