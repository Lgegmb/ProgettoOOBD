package DAO;

import Model.Series;

import java.util.ArrayList;

public interface SeriesDAO {

    ArrayList<Series> readAll(Integer edition, String name_s);

    ArrayList<String> getAllEditions();

}