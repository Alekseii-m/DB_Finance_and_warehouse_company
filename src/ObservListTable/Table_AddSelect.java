package ObservListTable;

public class Table_AddSelect {
    private String Column1;
    private Integer Column2;

    public Table_AddSelect(String Column1, Integer Column2) {
        this.Column1 = Column1;
        this.Column2 = Column2;
    }

    public Table_AddSelect() { }

    public String getColumn1() { return Column1; }
    public void setColumn1(String Column1) { this.Column1 = Column1; }

    public Integer getColumn2() {
        return Column2;
    }
    public void setColumn2(Integer Column2) {
        this.Column2 = Column2;
    }
}