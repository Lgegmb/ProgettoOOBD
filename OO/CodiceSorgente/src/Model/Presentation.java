package Model;

/**
 * The type Presentation.
 */
public class Presentation {
    private String title;
    private String firstName;
    private String lastName;
    private String presentationName;

    private String releasLocation;
    private String releaseDate;

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
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
     * Gets presentation name.
     *
     * @return the presentation name
     */
    public String getPresentationName() {
        return presentationName;
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

    /**
     * Instantiates a new Presentation.
     *
     * @param title            the title
     * @param firstName        the first name
     * @param lastName         the last name
     * @param presentationName the presentation name
     * @param releasLocation   the releas location
     * @param releaseDate      the release date
     */
    public Presentation(String title, String firstName, String lastName, String presentationName, String releasLocation, String releaseDate) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.presentationName = presentationName;
        this.releasLocation = releasLocation;
        this.releaseDate = releaseDate;
    }
}
