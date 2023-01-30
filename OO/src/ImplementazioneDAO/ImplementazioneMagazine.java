package ImplementazioneDAO;

import DAO.MagazineDAO;
import Database.ConnessioneDatabase;
import Model.Magazine;

import java.sql.*;
import java.util.ArrayList;

public class ImplementazioneMagazine implements MagazineDAO {

    private Connection connection;
    public static String INSERT_MAGAZINE = "INSERT INTO mtl.Magazine (ISSN_M, Name_M, Argument, Manager, " +
            "YearRelease, PublicationPeriod, PublishingHouse, AccessMode, FK_author) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static String READ_ALL_MAGAZINE = "SELECT * FROM mtl.Magazine";
    public static String DELETE_MAGAZINE = "DELETE FROM mtl.Magazine WHERE ISSN_M = ?";
    public static String UPDATE_MAGAZINE = "UPDATE mtl.Magazine SET Name_M = ?, Argument = ?, Manager = ?, " +
            "YearRelease = ?, PublicationPeriod = ?, PublishingHouse = ?, AccessMode = ?, FK_author = ? WHERE ISSN_M = ?";
    public static String SEARCH_MAGAZINE_BY_NAME = "SELECT * FROM mtl.Magazine WHERE Name_M = '%'||?||'%'";

    public static String SEARCH_MAGAZINE_BY_PUBLICATIONPERIOD = "SELECT * FROM mtl.Magazine WHERE PublicationPeriod = '%'||?||'%'";
    public static String GET_ALL_PERIODICITIES = "SELECT DISTINCT PublicationPeriod FROM mtl.Magazine";

    public ImplementazioneMagazine() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param ISSN_M
     * @param name_M
     * @param argument
     * @param manager
     * @param yearRelease
     * @param publicationPeriod
     * @param publishingHouse
     * @param accessMode
     * @param FK_author
     */
    @Override
    public void create(String ISSN_M, String name_M, String argument, String manager, Timestamp yearRelease, String publicationPeriod, String publishingHouse, String accessMode, String FK_author) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_MAGAZINE)) {
            statement.setString(1, ISSN_M);
            statement.setString(2, name_M);
            statement.setString(3, argument);
            statement.setString(4, manager);
            statement.setTimestamp(5, yearRelease);
            statement.setString(6, publicationPeriod);
            statement.setString(7, publishingHouse);
            statement.setString(8, accessMode);
            statement.setString(9, FK_author);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ArrayList<Magazine> readAll() {
        ArrayList<Magazine> magazines = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(READ_ALL_MAGAZINE)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String ISSN_M = resultSet.getString("ISSN_M");
                String name_M = resultSet.getString("Name_M");
                String argument = resultSet.getString("Argument");
                String manager = resultSet.getString("Manager");
                Timestamp yearRelease = Timestamp.valueOf(resultSet.getString("YearRelease"));
                String publicationPeriod = resultSet.getString("PublicationPeriod");
                String publishingHouse = resultSet.getString("PublishingHouse");
                String accessMode = resultSet.getString("AccessMode");
                magazines.add(new Magazine(ISSN_M, name_M, argument, manager, yearRelease, publicationPeriod, publishingHouse, accessMode));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return magazines;
    }

    @Override
    public void update(String ISSN_M, String name_M, String argument, String manager, Timestamp yearRelease, String publicationPeriod, String publishingHouse, String accessMode, String FK_author) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_MAGAZINE)) {
            statement.setString(1, ISSN_M);
            statement.setString(2, name_M);
            statement.setString(3, argument);
            statement.setString(4, manager);
            statement.setTimestamp(5, yearRelease);
            statement.setString(6, publicationPeriod);
            statement.setString(7, publishingHouse);
            statement.setString(8, accessMode);
            statement.setString(9, FK_author);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String ISSN_M) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_MAGAZINE)) {
            statement.setString(1, ISSN_M);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param name_M
     * @return
     */
    @Override
    public ArrayList<Magazine> searchByMagazineName(String name_M) {
        ArrayList<Magazine> magazines = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SEARCH_MAGAZINE_BY_NAME)) {
            statement.setString(1, name_M);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String ISSN_M = resultSet.getString("ISSN_M");
                name_M = resultSet.getString("Name_M");
                String argument = resultSet.getString("Argument");
                String manager = resultSet.getString("Manager");
                Timestamp yearRelease = Timestamp.valueOf(resultSet.getString("YearRelease"));
                String publicationPeriod = resultSet.getString("PublicationPeriod");
                String publishingHouse = resultSet.getString("PublishingHouse");
                String accessMode = resultSet.getString("AccessMode");
                String FK_author = resultSet.getString("FK_author");
                magazines.add(new Magazine(ISSN_M, name_M, argument, manager, yearRelease, publicationPeriod, publishingHouse, accessMode));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return magazines;
    }

    @Override
    public ArrayList<Magazine> searchByMagazinePublicationPeriod(String publicationPeriod) {
        ArrayList<Magazine> magazines = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SEARCH_MAGAZINE_BY_PUBLICATIONPERIOD)) {
            statement.setString(1, publicationPeriod);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String ISSN_M = resultSet.getString("ISSN_M");
                String name_M = resultSet.getString("Name_M");
                String argument = resultSet.getString("Argument");
                String manager = resultSet.getString("Manager");
                Timestamp yearRelease = Timestamp.valueOf(resultSet.getString("YearRelease"));
                publicationPeriod = resultSet.getString("PublicationPeriod");
                String publishingHouse = resultSet.getString("PublishingHouse");
                String accessMode = resultSet.getString("AccessMode");
                String FK_author = resultSet.getString("FK_author");
                magazines.add(new Magazine(ISSN_M, name_M, argument, manager, yearRelease, publicationPeriod, publishingHouse, accessMode));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return magazines;
    }

    /**
     * @return
     */
    @Override
    public ArrayList<String> getAllPeriodicities() {
        ArrayList<String> perioditcita = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_PERIODICITIES)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String publicationPeriod = resultSet.getString("PublicationPeriod");
                perioditcita.add(publicationPeriod);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return perioditcita;
    }
}


