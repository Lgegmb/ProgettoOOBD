package Model;

import java.sql.Timestamp;

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

    public String getISSN_M() {
        return ISSN_M;
    }

    public void setISSN_M(String ISSN_M) {
        this.ISSN_M = ISSN_M;
    }

    public String getName() {
        return name_M;
    }

    public void setName(String name_M) {
        this.name_M = name_M;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Timestamp getYearRelease() {
        return yearRelease;
    }

    public void setYearRelease(Timestamp yearRelease) {
        this.yearRelease = yearRelease;
    }

    public String getPublicationPeriod() {
        return publicationPeriod;
    }

    public void setPublicationPeriod(String publicationPeriod) {
        this.publicationPeriod = publicationPeriod;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public String getAccessMode() {
        return accessMode;
    }

    public void setAccessMode(String accessMode) {
        this.accessMode = accessMode;
    }

    public String getFK_author() {
        return FK_author;
    }

    public void setFK_author(String FK_author) {
        this.FK_author = FK_author;
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
                ", FK_author='" + FK_author + '\'' +
                '}';
    }
}
