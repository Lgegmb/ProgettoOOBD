package ImplementazioneDAO;

import DAO.PresentationDAO;
import Database.ConnessioneDatabase;
import Model.Conference;
import Model.Presentation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The type Implementazione presentation.
 */
public class ImplementazionePresentation implements PresentationDAO {

    private static final String GET_ALL_PRESENTATION = "SELECT * FROM mtl.presentation where title like '%'|| ? ||'%';";
    private Connection connection;

    /**
     * Instantiates a new Implementazione presentation.
     */
    public ImplementazionePresentation() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Presentation> getAllPresentation(String title) {
        ArrayList<Presentation> presentations = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_PRESENTATION)) {
            statement.setString(1, title);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                title = rs.getString("Title");
                String fname = rs.getString("Fname");
                String lname = rs.getString("Lname");
                String presentationName = rs.getString("PresentationName");
                String releaseLocation = rs.getString("ReleaseLocation");
                String releaseDate = rs.getString("ReleaseDate");
                presentations.add(new Presentation( title, fname, lname, presentationName, releaseLocation, releaseDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return presentations;
    }
}
