package DAO;

import Model.Conference;

import java.util.ArrayList;

public interface ConferenceDAO {

    ArrayList<Conference> getAllConference(String title);
}
