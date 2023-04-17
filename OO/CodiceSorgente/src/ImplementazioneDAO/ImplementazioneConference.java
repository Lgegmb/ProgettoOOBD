package ImplementazioneDAO;

import DAO.ConferenceDAO;
import Database.ConnessioneDatabase;
import Model.Conference;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The type Implementazione conference.
 */
public class ImplementazioneConference implements ConferenceDAO {

    private static final String GET_ALL_CONERENCE = "SELECT * FROM mtl.conference WHERE title like '%'|| ? ||'%';";
    private Connection connection;

    /**
     * Instantiates a new Implementazione conference.
     */
    public ImplementazioneConference() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Conference> getAllConference(String title) {
        ArrayList<Conference> conferences = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_CONERENCE)) {
            statement.setString(1, title);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                title = rs.getString("Title");
                String fname = rs.getString("Fname");
                String lname = rs.getString("Lname");
                String conferenceName = rs.getString("conferenceName");
                String releaseLocation = rs.getString("ReleaseLocation");
                String releaseDate = rs.getString("ReleaseDate");
                conferences.add(new Conference( title, fname, lname, conferenceName, releaseLocation, releaseDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conferences;
    }
}