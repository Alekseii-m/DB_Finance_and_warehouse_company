package ObservListTable;

public class Table_CardService {
    private String Column1;
    private Integer Column2;
    private Integer Column3;
    private String Column4;
    private String Column5;
    private Integer Column6;

    public Table_CardService(String Column1, Integer Column2, Integer Column3,
                                  String Column4, String Column5, Integer Column6 ) {
        this.Column1 = Column1;
        this.Column2 = Column2;
        this.Column3 = Column3;
        this.Column4 = Column4;
        this.Column5 = Column5;
        this.Column6 = Column6;
    }

    public Table_CardService() {
    }

    public String getColumn1() { return Column1; }
    public void setColumn1(String Column1) { this.Column1 = Column1; }

    public Integer getColumn2() {
        return Column2;
    }
    public void setColumn2(Integer Column2) {
        this.Column2 = Column2;
    }

    public Integer getColumn3() {
        return Column3;
    }
    public void setColumn3(Integer Column3) {
        this.Column3 = Column3;
    }

    public String getColumn4() { return Column4; }
    public void setColumn4(String Column4) { this.Column4 = Column4; }

    public String getColumn5() {
        return Column5;
    }
    public void setColumn5(String Column5) {
        this.Column5 = Column5;
    }

    public Integer getColumn6() {
        return Column6;
    }
    public void setColumn6(Integer Column6) {
        this.Column6 = Column6;
    }

}
