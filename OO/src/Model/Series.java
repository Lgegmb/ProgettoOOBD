package Model;

public class Series {

    private String ISSN_S;
    private String Curator;
    private int Edition;
    private String NameS;
    private String Code;

    public Series(String ISSN_S, String Curator, int Edition, String NameS, String Code) {
        this.ISSN_S = ISSN_S;
        this.Curator = Curator;
        this.Edition = Edition;
        this.NameS = NameS;
        this.Code = Code;
    }

    public String getISSN_S() {
        return ISSN_S;
    }

    public void setISSN_S(String ISSN_S) {
        this.ISSN_S = ISSN_S;
    }

    public String curator() {
        return Curator;
    }

    public void setCurator(String Curator) {
        this.Curator = Curator;
    }

    public int getEdition() {
        return Edition;
    }

    public void setAccessMode(int Edition) {
        this.Edition = Edition;
    }

    public String getNameS() {
        return NameS;
    }

    public void setNameS(String NameS) {
        this.NameS = NameS;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
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
