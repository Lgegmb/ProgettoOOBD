package Model;

import java.sql.Timestamp;

/**
 * The type Book.
 */
public class Book {

    private String ISBN_b;
    private int edition;
    private String publishingHouse;
    private String language;
    private String title;
    private String accessMode;
    private String argument;
    private Boolean reprint;
    private Timestamp releaseDate;
    private String releaseLocation;
    private String presentationName;
    private String FK_Series;
    private String author;


    /**
     * Instantiates a new Book.
     *
     * @param ISBN_b           the isbn b
     * @param edition          the edition
     * @param publishingHouse  the publishing house
     * @param language         the language
     * @param title            the title
     * @param accessMode       the access mode
     * @param argument         the argument
     * @param reprint          the reprint
     * @param releaseDate      the release date
     * @param releaseLocation  the release location
     * @param presentationName the presentation name
     * @param FK_Series        the fk series
     * @param author           the author
     */
    public Book(String ISBN_b, int edition, String publishingHouse, String language,
                String title, String accessMode, String argument, Boolean reprint, Timestamp releaseDate,
                String releaseLocation, String presentationName, String FK_Series, String author) {

        this.ISBN_b = ISBN_b;
        this.edition = edition;
        this.publishingHouse = publishingHouse;
        this.language = language;
        this.title = title;
        this.accessMode = accessMode;
        this.argument = argument;
        this.reprint = reprint;
        this.releaseDate = releaseDate;
        this.releaseLocation = releaseLocation;
        this.presentationName = presentationName;
        this.FK_Series = FK_Series;
        this.author = author;
    }

    /**
     * Gets author.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }


    /**
     * Gets access mode.
     *
     * @return the access mode
     */
    public String getAccessMode() {
        return accessMode;
    }


    /**
     * Gets release date.
     *
     * @return the release date
     */
    public Timestamp getReleaseDate() {
        return releaseDate;
    }


    /**
     * Gets edition.
     *
     * @return the edition
     */
    public int getEdition() {
        return edition;
    }


    /**
     * Gets fk series.
     *
     * @return the fk series
     */
    public String getFK_Series() {
        return FK_Series;
    }

    /**
     * Gets reprint.
     *
     * @return the reprint
     */
    public Boolean getReprint() {
        return reprint;
    }


    /**
     * Gets argument.
     *
     * @return the argument
     */
    public String getArgument() {
        return argument;
    }


    /**
     * Gets language.
     *
     * @return the language
     */
    public String getLanguage() {
        return language;
    }


    /**
     * Gets isbn b.
     *
     * @return the isbn b
     */
    public String getISBN_B() {
        return ISBN_b;
    }

    /**
     * Gets publishing house.
     *
     * @return the publishing house
     */
    public String getPublishingHouse() {
        return publishingHouse;
    }

    /**
     * Gets release location.
     *
     * @return the release location
     */
    public String getReleaseLocation() {
        return releaseLocation;
    }

    /**
     * Gets presentation name.
     *
     * @return the presentation name
     */
    public String getPresentationName() {
        return presentationName;
    }

    @Override
    public String toString() {
        return "Book{" +
                ", ISBN_b='" + ISBN_b + '\'' +
                ", edition='" + edition + '\'' +
                ", publishingHouse='" + publishingHouse + '\'' +
                ", language='" + language + '\'' +
                ", title='" + title + '\'' +
                ", argument='" + argument + '\'' +
                ", accessMode='" + accessMode + '\'' +
                ", reprint='" + reprint + '\'' +
                ", releaseDate=" + releaseDate + '\'' +
                ", releaseLocation='" + releaseLocation + '\'' +
                ", presentationName='" + presentationName + '\'' +
                ", FK_Series='" + FK_Series + '\'' +
                '}';
    }

}
