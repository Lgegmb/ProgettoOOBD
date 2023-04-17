package Model;

/**
 * The type Conference.
 */
public class Conference {

    private String title;
    private String firstName;
    private String lastName;
    private String conferenceName;

    private String releasLocation;
    private String releaseDate;

    /**
     * Instantiates a new Conference.
     *
     * @param title          the title
     * @param firstName      the first name
     * @param lastName       the last name
     * @param conferenceName the conference name
     * @param releasLocation the releas location
     * @param releaseDate    the release date
     */
    public Conference(String title, String firstName, String lastName, String conferenceName, String releasLocation, String releaseDate) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.conferenceName = conferenceName;
        this.releasLocation = releasLocation;
        this.releaseDate = releaseDate;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String   getTitle() {
        return title;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
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
     * Gets releas location.
     *
     * @return the releas location
     */
    public String getReleasLocation() {
        return releasLocation;
    }

    /**
     * Gets release date.
     *
     * @return the release date
     */
    public String getReleaseDate() {
        return releaseDate;
    }
}
