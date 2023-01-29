package Model;

/**
 * The type Author.
 */
public class Author {
    private String codauthor;
    private String fName;
    private String lName;


    /**
     * Instantiates a new Author.
     *
     * @param authorID the author id
     * @param fName    the f name
     * @param lName    the l name
     */
    public Author(String authorID, String fName, String lName) {
        this.codauthor = authorID;
        this.fName = fName;
        this.lName = lName;
    }

    /**
     * Gets author id.
     *
     * @return the author id
     */
    public String getAuthorID() {
        return codauthor;
    }

    /**
     * Sets email.
     *
     * @param authorID the author id
     */
    public void setEmail(String authorID) {
        this.codauthor = authorID;
    }

    /**
     * Gets f name.
     *
     * @return the f name
     */
    public String getFName() {
        return fName;
    }

    /**
     * Sets f name.
     *
     * @param fName the f name
     */
    public void setFName(String fName) {
        this.fName = fName;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getlName() {
        return lName;
    }

    /**
     * Sets name.
     *
     * @param lName the l name
     */
    public void setlName(String lName) {
        this.lName = lName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "codAuthor='" + codauthor + '\'' +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                '}';
    }
}
