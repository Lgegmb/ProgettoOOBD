package Model;

import java.sql.Timestamp;

public class Bibliography {
    private final String lname;
    private final Timestamp releaseDate;
    private final String title;

    public Bibliography(String title, Timestamp releaseDate, String lname) {

        this.title = title;
        this.releaseDate = releaseDate;
        this.lname = lname;
    }

    public String getLname() {
        return lname;
    }

    public Timestamp getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", releaseDate=" + releaseDate + '\'' +
                ", lname=" + lname +
                '}';
    }
}
