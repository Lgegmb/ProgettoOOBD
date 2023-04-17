package Model;

/**
 * The type Series.
 */
public class Series {

    private String ISSN_S;
    private String Curator;
    private int Edition;
    private String NameS;
    private String Code;

    /**
     * Instantiates a new Series.
     *
     * @param ISSN_S  the issn s
     * @param Curator the curator
     * @param Edition the edition
     * @param NameS   the name s
     * @param Code    the code
     */
    public Series(String ISSN_S, String Curator, int Edition, String NameS, String Code) {
        this.ISSN_S = ISSN_S;
        this.Curator = Curator;
        this.Edition = Edition;
        this.NameS = NameS;
        this.Code = Code;
    }

    /**
     * Gets issn s.
     *
     * @return the issn s
     */
    public String getISSN_S() {
        return ISSN_S;
    }


    /**
     * Gets curator.
     *
     * @return the curator
     */
    public String getCurator() {
        return Curator;
    }


    /**
     * Gets edition.
     *
     * @return the edition
     */
    public int getEdition() {
        return Edition;
    }


    /**
     * Gets name s.
     *
     * @return the name s
     */
    public String getNameS() {
        return NameS;
    }


    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return Code;
    }


    @Override
    public String toString() {
        return "Series{" +
                "ISSN_S='" + ISSN_S + '\'' +
                ", Curator='" + Curator + '\'' +
                ", Edition='" + Edition + '\'' +
                ", NameS='" + NameS + '\'' +
                ", Code='" + Code + '\'' +
                '}';
    }
}
