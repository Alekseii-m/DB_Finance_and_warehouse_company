
package BD_Controller;

        import java.net.URL;
        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.ResourceBundle;

        import BD_Common.Const;
        import BD_Common.DbConnectDataBase;
        import ObservListTable.Table_AddSelect;
        import ObservListTable.Table_CardClient;
        import ObservListTable.Table_CardProduct;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.TableColumn;
        import javafx.scene.control.TableView;
        import javafx.scene.control.TextField;
        import javafx.scene.control.cell.PropertyValueFactory;

public class Cont_AddSelectProduct {

    private final ObservableList<Table_AddSelect> Table_Data = FXCollections.observableArrayList();


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Label_1;

    @FXML
    private TextField TextField_Product;

    @FXML
    private Button Button_SelectProduct;

    @FXML
    private TextField TextField_SelectProduct;

    @FXML
    private TableView<Table_AddSelect> TableView_ListProduct;

    @FXML
    private TableColumn<Table_AddSelect, String> Column_ListProduct;

    @FXML
    private TableColumn<Table_AddSelect, Integer> Column_ListProduct_Id;

    @FXML
    private Button Button_InsertCardProduct;

    @FXML
    void initialize() {

        // Скрыть столбец id таблицы
        Column_ListProduct_Id.setVisible(false);

        //-------------------------------------------------------------------------------------------------------ВИП101020м20
        //Внесение данных из бд в таблицу
        Button_SelectProduct.setOnAction(event -> {
            // Инициализация таблицы адресатов сo столбцами.
            Column_ListProduct.setCellValueFactory(new PropertyValueFactory<Table_AddSelect, String>("Column1"));
            Column_ListProduct_Id.setCellValueFactory(new PropertyValueFactory<Table_AddSelect, Integer>("Column2"));

            // Выборка данных по условию если пусто-ChangSelect1, если нет ChangeSelekt2 :
            if(TextField_SelectProduct.getText().trim().equals(""))
            { ChangSelect1 (); }
            else {
                ChangeSelekt2 ();
            }
        });
//-------------------------------------------------------------------------------------------------------

        Button_InsertCardProduct.setOnAction(event -> {
            // Внесение выделенной строки таблицы в данные по сервису (name , id)
            if (TableView_ListProduct.getSelectionModel().getSelectedItem() != null) {
                Table_AddSelect selectedPerson = TableView_ListProduct.getSelectionModel().getSelectedItem();

                Const.CardProduct_NameProduct = selectedPerson.getColumn1();
                Const.Id_NameProduct = selectedPerson.getColumn2();

            }
        });



    }

    //------------------------------------------------------------------------------------------------------------------
    //******************************************************************************************************************
    //------------------------------------------------------------------------------------------------------------------


    //Часть кода ( Внесение данных из бд в таблицу)
    public void ChangSelect1 () {
        //предварительная очистка обсервайбл лист
        Table_Data.clear();

       // System.out.println("шаг2");

        String Select2 = "SELECT "+
                "Name_Product" + "," +
                "id_Product" +
                " FROM " +
                "bd_company.directory_product";

        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select2)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_AddSelect(
                        ResSet.getString("Name_Product"),
                        ResSet.getInt("id_Product"))
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_ListProduct.setItems(Table_Data);

    }

    //Внесение данных из бд в таблицу по значению в текстфилде
    public void ChangeSelekt2(){
        //предварительная очистка обсервайбл лист
        Table_Data.clear();

        String Select2 = "SELECT "+
                "Name_Product" + "," +
                "id_Product" +
                " FROM " +
                "bd_company.directory_product" +
                " WHERE " + "Name_Product" +
                " LIKE " +"'"+TextField_SelectProduct.getText()+"%'";

        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select2)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_AddSelect(
                        ResSet.getString("Name_Product"),
                        ResSet.getInt("id_Product"))
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_ListProduct.setItems(Table_Data);
    }

//------------------------------------------------------------------------------------------------------------------



}
