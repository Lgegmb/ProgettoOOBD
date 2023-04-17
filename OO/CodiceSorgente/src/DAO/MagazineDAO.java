package DAO;

import Model.Magazine;

import java.util.ArrayList;

public interface MagazineDAO {

    ArrayList<Magazine> readAll(String publicationPeriod, String argument, String name_m);

    ArrayList<String> getAllPeriodicities();

    ArrayList<String> getAllArguments();
}