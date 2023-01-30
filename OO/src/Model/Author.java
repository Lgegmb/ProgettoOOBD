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
     * @param codauthor the author id
     * @param fName    the f name
     * @param lName    the l name
     */
    public Author(String codauthor, String fName, String lName) {
        this.codauthor = codauthor;
        this.fName = fName;
        this.lName = lName;
    }

    /**
     * Gets author id.
     *
     * @return the author id
     */
    public String getCodauthor() {
        return codauthor;
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
    public String getLName() {
        return lName;
    }

    /**
     * Sets name.
     *
     * @param lName the l name
     */
    public void setLName(String lName) {
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
