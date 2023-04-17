package DAO;

import Model.Book;

import java.util.ArrayList;

public interface BookDAO {


    ArrayList<Book> readAll(String arg, String lang, String accessMode, boolean reprint, String title, String lname);


    ArrayList<String> getAllArguments();

    ArrayList<String> getAllLanguages();

    ArrayList<String> getAllAccess();

}