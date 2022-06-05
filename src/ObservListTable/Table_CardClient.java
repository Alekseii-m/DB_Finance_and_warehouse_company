package ObservListTable;

public class Table_CardClient {
    private String Column1;
    private String Column2;
    private String Column3;
    private String Column4;
    private String Column5;
    private String Column6;
    private Integer Column7;

    public Table_CardClient(String Column1, String Column2, String Column3,
                             String Column4, String Column5, String Column6, Integer Column7 ) {
        this.Column1 = Column1;
        this.Column2 = Column2;
        this.Column3 = Column3;
        this.Column4 = Column4;
        this.Column5 = Column5;
        this.Column6 = Column6;
        this.Column7 = Column7;
    }

    public Table_CardClient() {
    }

    public String getColumn1() { return Column1; }
    public void setColumn1(String Column1) { this.Column1 = Column1; }

    public String getColumn2() {
        return Column2;
    }
    public void setColumn2(String Column2) {
        this.Column2 = Column2;
    }

    public String getColumn3() {
        return Column3;
    }
    public void setColumn3(String Column3) {
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

    public String getColumn6() {
        return Column6;
    }
    public void setColumn6(String Column6) {
        this.Column6 = Column6;
    }

    public Integer getColumn7() {
        return Column7;
    }
    public void setColumn7(Integer Column7) {
        this.Column7 = Column7;
    }

}
