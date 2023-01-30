package DAO;

import Model.Magazine;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface MagazineDAO {

    void create(String ISSN_M, String name_M, String argument, String manager, Timestamp yearRelease, String publicationPeriod, String publishingHouse, String accessMode, String FK_author);

    ArrayList<Magazine> readAll();

    void update(String ISSN_M, String name_M, String argument, String manager, Timestamp yearRelease, String publicationPeriod, String publishingHouse, String accessMode, String FK_author);

    void delete(String ISSN_M);

    ArrayList<Magazine> searchByMagazineName(String name_M);

    ArrayList<Magazine> searchByMagazinePublicationPeriod(String publicationPeriod);

    ArrayList<Magazine> getAllPeriodicities();
}