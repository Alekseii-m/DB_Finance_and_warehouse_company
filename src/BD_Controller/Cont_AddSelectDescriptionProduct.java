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
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.TableColumn;
        import javafx.scene.control.TableView;
        import javafx.scene.control.TextField;
        import javafx.scene.control.cell.PropertyValueFactory;

public class Cont_AddSelectDescriptionProduct {

    private final ObservableList<Table_AddSelect> Table_Data = FXCollections.observableArrayList();


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Label_1;

    @FXML
    private TextField TextField_DescritionProduct;

    @FXML
    private Button Button_SelectDescritionProduct;

    @FXML
    private TextField TextField_SelectDescritionProduct;

    @FXML
    private TableView<Table_AddSelect> TableView_ListDescritionProduct;

    @FXML
    private TableColumn<Table_AddSelect, String> Column_ListDescritionProduct;

    @FXML
    private TableColumn<Table_AddSelect, Integer> Column_ListDescritionProduct_Id;

    @FXML
    private Button Button_InsertCardProduct;

    @FXML
    void initialize() {

        // Скрыть столбец id таблицы
        Column_ListDescritionProduct_Id.setVisible(false);


        //-------------------------------------------------------------------------------------------------------ВИП101020м20
        //Внесение данных из бд в таблицу
        Button_SelectDescritionProduct.setOnAction(event -> {
            // Инициализация таблицы адресатов сo столбцами.
            Column_ListDescritionProduct.setCellValueFactory(new PropertyValueFactory<Table_AddSelect, String>("Column1"));
            Column_ListDescritionProduct_Id.setCellValueFactory(new PropertyValueFactory<Table_AddSelect, Integer>("Column2"));

            // Выборка данных по условию если пусто-ChangSelect1, если нет ChangeSelekt2 :
            if(TextField_SelectDescritionProduct.getText().trim().equals(""))
            { ChangSelect1 (); }
            else {
                ChangeSelekt2 ();
            }
        });
//-------------------------------------------------------------------------------------------------------

        Button_InsertCardProduct.setOnAction(event -> {
            // Внесение выделенной строки таблицы в данные описание продукта
            if (TableView_ListDescritionProduct.getSelectionModel().getSelectedItem() != null) {
                Table_AddSelect selectedPerson = TableView_ListDescritionProduct.getSelectionModel().getSelectedItem();

                Const.CardProduct_DescriptionProduct = selectedPerson.getColumn1();
                Const.Id_DescriptionProduct = selectedPerson.getColumn2();

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

        String Select2 = "SELECT "+"distinct "+
                "Product_Description" +
                " FROM " +
                "bd_company.directory_product";

        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select2)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_AddSelect(
                        ResSet.getString("Product_Description"),
                        0)
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_ListDescritionProduct.setItems(Table_Data);

    }

    //Внесение данных из бд в таблицу по значению в текстфилде
    public void ChangeSelekt2(){
        //предварительная очистка обсервайбл лист
        Table_Data.clear();

        String Select2 = "SELECT "+"distinct "+
                "Product_Description" +
                " FROM " +
                "bd_company.directory_product" +
                " WHERE " + "Product_Description" +
                " LIKE " +"'"+TextField_SelectDescritionProduct.getText()+"%'";

        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select2)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_AddSelect(
                        ResSet.getString("Product_Description"),
                        0)
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_ListDescritionProduct.setItems(Table_Data);
    }

//------------------------------------------------------------------------------------------------------------------





}

