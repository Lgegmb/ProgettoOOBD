package Model;

import java.sql.Timestamp;

/**
 * The type Article.
 */
public class Article {
    private String doi_A;
    private String title;
    private String accessMode;
    private String editor;
    private String topic;
    private Timestamp releaseDate;
    private String releaseLocation;
    private String conferenceName;
    private String FK_author;
    private String FK_Magazine;

    /**
     * Instantiates a new Article.
     *
     * @param doi_A           the doi a
     * @param title           the title
     * @param accessMode      the access mode
     * @param editor          the editor
     * @param topic           the topic
     * @param releaseDate     the release date
     * @param releaseLocation the release location
     * @param conferenceName  the conference name
     * @param FK_author       the fk author
     * @param FK_Magazine     the fk magazine
     */
    public Article(String doi_A, String title, String accessMode, String editor, String topic, Timestamp releaseDate, String releaseLocation, String conferenceName, String FK_author, String FK_Magazine) {
        this.doi_A = doi_A;
        this.title = title;
        this.accessMode = accessMode;
        this.editor = editor;
        this.topic = topic;
        this.releaseDate = releaseDate;
        this.releaseLocation = releaseLocation;
        this.conferenceName = conferenceName;
        this.FK_author = FK_author;
        this.FK_Magazine = FK_Magazine;
    }

    /**
     * Gets doi a.
     *
     * @return the doi a
     */
    public String getDoi_A() {
        return doi_A;
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
     * Gets editor.
     *
     * @return the editor
     */
    public String getEditor() {
        return editor;
    }


    /**
     * Gets topic.
     *
     * @return the topic
     */
    public String getTopic() {
        return topic;
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
     * Gets release location.
     *
     * @return the release location
     */
    public String getReleaseLocation() {
        return releaseLocation;
    }


    /**
     * Gets conference name.
     *
     * @return the conference name
     */
    public String getConferenceName() {
        return conferenceName;
    }


    /**
     * Gets author.
     *
     * @return the author
     */
    public String getAuthor() {
        return FK_author;
    }


    /**
     * Gets fk magazine.
     *
     * @return the fk magazine
     */
    public String getFK_Magazine() {
        return FK_Magazine;
    }

    @Override
    public String toString() {
        return "Article{" +
                "doi_B='" + doi_A + '\'' +
                ", title='" + title + '\'' +
                ", accessMode='" + accessMode + '\'' +
                ", editor='" + editor + '\'' +
                ", topic='" + topic + '\'' +
                ", releaseDate=" + releaseDate + '\'' +
                ", releaseLocation='" + releaseLocation + '\'' +
                ", conferenceName='" + conferenceName + '\'' +
                ", FK_author='" + FK_author + '\'' +
                ", FK_Magazine='" + FK_Magazine + '\'' +
                '}';
    }
}
