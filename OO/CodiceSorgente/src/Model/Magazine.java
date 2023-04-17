package Model;

import java.sql.Timestamp;

/**
 * The type Magazine.
 */
public class Magazine {

    private String ISSN_M;
    private String name_M;
    private String argument;
    private String manager;
    private Timestamp yearRelease;
    private String publicationPeriod;
    private String publishingHouse;
    private String accessMode;
    private String FK_author;

    /**
     * Instantiates a new Magazine.
     *
     * @param ISSN_M            the issn m
     * @param name_M            the name m
     * @param argument          the argument
     * @param manager           the manager
     * @param yearRelease       the year release
     * @param publicationPeriod the publication period
     * @param publishingHouse   the publishing house
     * @param accessMode        the access mode
     */
    public Magazine(String ISSN_M, String name_M, String argument, String manager,
                    Timestamp yearRelease, String publicationPeriod, String publishingHouse, String accessMode) {
        this.ISSN_M = ISSN_M;
        this.name_M = name_M;
        this.argument = argument;
        this.manager = manager;
        this.yearRelease = yearRelease;
        this.publicationPeriod = publicationPeriod;
        this.publishingHouse = publishingHouse;
        this.accessMode = accessMode;
    }

    /**
     * Gets issn m.
     *
     * @return the issn m
     */
    public String getISSN_M() {
        return ISSN_M;
    }


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name_M;
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
     * Gets manager.
     *
     * @return the manager
     */
    public String getManager() {
        return manager;
    }


    /**
     * Gets year release.
     *
     * @return the year release
     */
    public Timestamp getYearRelease() {
        return yearRelease;
    }


    /**
     * Gets publication period.
     *
     * @return the publication period
     */
    public String getPublicationPeriod() {
        return publicationPeriod;
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
     * Gets access mode.
     *
     * @return the access mode
     */
    public String getAccessMode() {
        return accessMode;
    }


    @Override
    public String toString() {
        return "Series{" +
                "ISSN_S='" + ISSN_M + '\'' +
                ", name_M='" + name_M + '\'' +
                ", argument='" + argument + '\'' +
                ", manager='" + manager + '\'' +
                ", yearRelease='" + yearRelease + '\'' +
                ", publicationPeriod='" + publicationPeriod + '\'' +
                ", publishingHouse='" + publishingHouse + '\'' +
                ", accessMode='" + accessMode + '\'' +
                '}';
    }
}
