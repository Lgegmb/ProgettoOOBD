package DAO;

import Model.Conference;
import Model.Presentation;

import java.util.ArrayList;

public interface PresentationDAO {
    ArrayList<Presentation> getAllPresentation(String title);
}
