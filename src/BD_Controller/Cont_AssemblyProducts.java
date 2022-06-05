package BD_Controller;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

import BD_Common.Const;
import BD_Common.DbConnectDataBase;
import BD_Common.OpenNewWindow_Class;
import ObservListTable.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class Cont_AssemblyProducts {

    private final ObservableList<Table_AssemblyProducts> Table_Data = FXCollections.observableArrayList();
   // private final ObservableList<Table_AddSelect> Table_Data2 = FXCollections.observableArrayList();
   private int _Id_;

    //переменные для заполнения БД----------------------------------------------->
    int _id_AssemblyProducts =0;
    String _id_Name_AssemblyProducts = "";
    String _id_NameAssemblyItem_AssemblyProducts = "";
    int _AmountAssemblyItem_AssemblyProducts =0;
    String _DateRecording_AssemblyProducts ="";
    String _Additionally_AssemblyProducts ="";
    //переменные  для заполнения БД-----------------------------------------------<
    int _id_del =0;// для удаления строки в таблице и БД
    int n =0;    // удаление выделенной строки в таблице


    @FXML
    private AnchorPane Anchor_AssemblyProducts;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Label_1;

    @FXML
    private Button ButtonFormClear;

    @FXML
    private Button ButtonEdit_line;

    @FXML
    private Button ButtonSave_AssemblyItemProducts;

    @FXML
    private TextArea TextAreaComment;

    @FXML
    private TableView<Table_AssemblyProducts> TableView_AssemblyProducts;

    @FXML
    private TableColumn<Table_AssemblyProducts, String> Column_AssemblyProducts;

    @FXML
    private TableColumn<Table_AssemblyProducts, String> ColumnAssemblyItem;

    @FXML
    private TableColumn<Table_AssemblyProducts, Integer> Column_amount;

    @FXML
    private TableColumn<Table_AssemblyProducts, String> Column_DataRecords;

    @FXML
    private TableColumn<Table_AssemblyProducts, String> ColumnComment;

    @FXML
    private TableColumn<Table_AssemblyProducts, Integer> ColumnId;

    @FXML
    private Button ButtonClearTableView;

    @FXML
    private Button ButtonSaveEdit_line;

    @FXML
    private Button ButtonDelete_line;

    @FXML
    private DatePicker DataPickerDataRecords;

    @FXML
    private TextField TextField_AssemblyProducts;

    @FXML
    private Button ButtonSelectAssemblyProducts;

    @FXML
    private TextField TextFieldSelectAssemblyProducts;

//    @FXML
//    private TableView<Table_AddSelect> TableView_Select_AssemblyProducts;
//
//    @FXML
//    private TableColumn<Table_AddSelect, String> Column_AssemblyProducts_Select;
//
//    @FXML
//    private TableColumn<Table_AddSelect, Integer> Column_Select_AssemblyProducts_id;

    @FXML
    private TextField TextFild_AssemblyItem_amount;

    @FXML
    private Button ButtonSelect_AssemblyItem_AssemblyProducts;

    @FXML
    private TextField TextField_AssemblyItem;

    @FXML
    private Button ButtonSelectAssemblyItem;

    @FXML
    private Button Button_InsertAssemblyItems ;

//    @FXML
//    private TextField TextFieldSelectAssemblyItem;

//    @FXML
//    private TableView<Table_AddSelect> TableView_Select_AssemblyItem;
//
//    @FXML
//    private TableColumn<Table_AddSelect, String> Column_AssemblyItem_Select;
//
//    @FXML
   // private TableColumn<Table_AddSelect, Integer> Column_Select_AssemblyItem_id;

    @FXML
    void initialize() {

        // Скрыть столбец id таблицы
        //Column_Select_AssemblyProducts_id.setVisible(false);

        // Скрыть столбец id таблицы
       // Column_Select_AssemblyItem_id.setVisible(false);

        // Скрыть столбец id таблицы
       // ColumnId.setVisible(false);

        //-------------------------------------------------------------------------------------------------------

        // Внесение данных из бд в таблицу по значению в текстфилде (выбор сборных единиц продукта по
        // названию сборного продукта)
        ButtonSelectAssemblyProducts.setOnAction(event -> {
            // Инициализация таблицы адресатов сo столбцами.
            InitializationTable();

            // Выборка данных по условию если пусто-ChangSelect1, если нет ChangeSelekt2 :
            if(TextFieldSelectAssemblyProducts.getText().trim().equals(""))
            { SelectAssemblyProducts_Select1 (); }  //SelectAssemblyProducts_Select1 ()
            else {
                SelectAssemblyProducts_Select2 ();
            }

            //SelectAssemblyProducts_Select2 ();
        });

        //-------------------------------------------------------------------------------------------------------

        // Внесение данных из бд в таблицу по значению в текстфилде (выбор сборных единиц продукта по
        // названию сборного продукта)
        ButtonSelect_AssemblyItem_AssemblyProducts.setOnAction(event -> {
            // Инициализация таблицы адресатов сo столбцами.
            InitializationTable();

            // Выборка данных по условию если пусто-ChangSelect1, если нет ChangeSelekt2 :
            if(TextField_AssemblyProducts.getText().trim().equals(""))
            {  }  //SelectAssemblyItem_AssemblyProducts1 ()
            else {
                SelectAssemblyItem_AssemblyProducts2 ();
            }

            //SelectAssemblyItem_AssemblyProducts2 ();
        });

        //-------------------------------------------------------------------------------------------------------

        // Внесение выделенной строки таблицы в ячейки редактирования
        ButtonEdit_line.setOnAction(event -> {
            onEdit();
        });

       //------------------------------------------------------------------------------------------------------------------

        // вставка-сохранение из формы-интерф. в БД
        ButtonSave_AssemblyItemProducts.setOnAction(event -> {
            // Запись значений из текст-филдов в переменные.
            Record_TextField_Variable();

            //Вставить данные в БД в таблицу AssemblyProducts
            try {
                DbConnectDataBase.insert_AssemblyProducts(
                _id_Name_AssemblyProducts ,
                _id_NameAssemblyItem_AssemblyProducts ,
                _AmountAssemblyItem_AssemblyProducts ,
                _DateRecording_AssemblyProducts ,
                _Additionally_AssemblyProducts
                );
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            // Вставка значений из текст-филдов в таблицу формы.
            InitializationTable();
           // Insert_ValuesTextField_TableView();
            Insert_ValuesTextField_TableView2();
        });

        //------------------------------------------------------------------------------------------------------------------

        ButtonSelectAssemblyItem.setOnAction(event -> {
           // System.out.println("Button_SelectSimilarName");
            //Загрузка окна
            OpenNewWindow_Class.OpenNewWindow_ ("/BD_fxml/CardProduct.fxml");
        });

        //внести в текст-филд название продукта
        Button_InsertAssemblyItems.setOnAction(event -> {
            TextField_AssemblyItem.setText(Const.CardProduct_NameProduct);
        });

//-------------------------------------------------------------------------------------


        // очистка формы интерфейса
        ButtonFormClear.setOnAction(event -> {
            ClearTextFild();
        });

        //-------------------------------------------------------------------------------------------------------

        //  очистка таблицы интерфейса
        ButtonClearTableView.setOnAction(event -> {
            ClearTableView();
        });

        //-------------------------------------------------------------------------------------------------------

        // Удалить строку из TableView
        ButtonDelete_line.setOnAction(event -> {
            GetIdSel();
            Delete_Rows();
            delRow_TableView();
        });

        //-------------------------------------------------------------------------------------------------------

        // Обновить данные строки в БД
        ButtonSaveEdit_line.setOnAction(event -> {
            Update_stringBD();

            // Обновление выделенной строки таблицы формы
            UpdateStringTableView();
        });

        //-------------------------------------------------------------------------------------------------------





    }

    //------------------------------------------------------------------------------------------------------------------
    //******************************************************************************************************************
    //------------------------------------------------------------------------------------------------------------------



    // Обновление выделенной строки таблицы формы
    private void UpdateStringTableView() {
        if (TableView_AssemblyProducts.getSelectionModel().getSelectedItem() != null) {
            Table_AssemblyProducts selectedPerson = TableView_AssemblyProducts.getSelectionModel().getSelectedItem();

            //заполнение ячеек выделенной строки новыми значениями
            selectedPerson.setColumn1(_id_Name_AssemblyProducts);
            selectedPerson.setColumn2(_id_NameAssemblyItem_AssemblyProducts );
            selectedPerson.setColumn3(_AmountAssemblyItem_AssemblyProducts);
            selectedPerson.setColumn4(_DateRecording_AssemblyProducts);
            selectedPerson.setColumn5(_Additionally_AssemblyProducts);
            selectedPerson.setColumn6(_id_del);

            //Обновление таблицы формы
            TableView_AssemblyProducts.refresh();

            // System.out.println("Comment5 -"+selectedPerson.getColumn5());
        }
    }
//------------------------------------------------------------------------------------------------------------------


    // Обновить данные строки в БД (сборка)
    private void Update_stringBD(){

        // Запись значений из текст-филдов в переменные.
        Record_TextField_Variable();

        //Получение id таблицы БД выделенной строки таблицы:
        GetIdSel();

        //обновить данные строки в БД в таблице
        String update =
                "UPDATE"+ "`bd_company`.`assemblyproducts`"+ "SET "+

                        "id_Name_AssemblyProducts"+"=" + "(" + "SELECT " + "id_Product" + " FROM " + "Directory_Product" + " WHERE " +
                        "Name_Product" + "=" + "'" + _id_Name_AssemblyProducts + "'" + ")" + "," +

                        "id_NameAssemblyItem_AssemblyProducts"+"="+"(" + "SELECT " + "id_Product" + " FROM " + "Directory_Product" + " WHERE " +
                        "Name_Product" + "=" + "'" + _id_NameAssemblyItem_AssemblyProducts + "'" + ")" + "," +

                        "AmountAssemblyItem_AssemblyProducts" +"="+"'"+_AmountAssemblyItem_AssemblyProducts+"'"+ "," +
                        "DateRecording_AssemblyProducts" +"="+"'"+_DateRecording_AssemblyProducts+"'"+ "," +
                        "Additionally_AssemblyProducts"  +"="+"'"+_Additionally_AssemblyProducts+"'" +

                        "WHERE"+ "`id_AssemblyProducts`"+"="+"'"+_id_del+"'";



        try (Connection conn = DbConnectDataBase.connect();

             PreparedStatement PrSt = conn.prepareStatement(update)) {
            PrSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    private void delRow_TableView(){
        // удаление выделенной строки в таблице
        n =  TableView_AssemblyProducts.getSelectionModel().getSelectedIndex();
        TableView_AssemblyProducts.getItems().remove(n);
    }


    //Получение id таблицы БД выделенной строки таблицы для дальнейшего удаления:
    private void GetIdSel(){
        if (TableView_AssemblyProducts.getSelectionModel().getSelectedItem() != null) {
            Table_AssemblyProducts selectedPerson = TableView_AssemblyProducts.getSelectionModel().getSelectedItem();
            _id_del= selectedPerson.getColumn6();
        }

    }


    // Удаляем строку таблицы БД по id
    private void Delete_Rows() {

        //   String DelR =  "DELETE "+ " FROM " + Const.TABLE_CardClient + " WHERE " + Const.COLUMN_CardClient_Id_+ "= ?" ;
        String DelR =  "DELETE "+ " FROM " + "`bd_company`.`assemblyproducts`"  +
                " WHERE " +  "id_AssemblyProducts"+ "= ?";

        try (Connection conn = DbConnectDataBase.connect();
             PreparedStatement pstmt = conn.prepareStatement(DelR)) {

            int n = _id_del ; //  id столбца
            pstmt.setInt(1, n);

            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    //------------------------------------------------------------------------------------------------------------------


    //Очищаем ТекстФильд-ы
    public void ClearTextFild(){

        TextField_AssemblyProducts.clear();
        TextField_AssemblyItem.clear();
        TextFild_AssemblyItem_amount.clear();

        DataPickerDataRecords.getEditor().clear();
        DataPickerDataRecords.setValue(null);

        TextAreaComment.clear();
        _Id_= 0;
        // System.out.println(_Id_);

    }

//-------------------------------------------------------------------------------------------------------------------

    //Очищаем табле вив:
    public void ClearTableView(){
        TableView_AssemblyProducts.getItems().clear();
    }

//-------------------------------------------------------------------------------------------------------------------

    // Вставка значений из текст-филдов в таблицу формы.
    private void Insert_ValuesTextField_TableView(){

        Table_Data.add(new Table_AssemblyProducts(
                _id_Name_AssemblyProducts,
                _id_NameAssemblyItem_AssemblyProducts,
                _AmountAssemblyItem_AssemblyProducts,
                _DateRecording_AssemblyProducts,
                _Additionally_AssemblyProducts,
                null)
        );
        TableView_AssemblyProducts.setItems(Table_Data);

    }

    // или так

    // Внесение данных из бд в таблицу по значению в текстфилде (выбор сборных единиц продукта по
    // названию сборного продукта)
    private void Insert_ValuesTextField_TableView2 () {
        // отобразить скрытые столбцы
        ShowTableColumns();

        //названиt сборного продукта
      // String Name_AssemblyProducts = TextField_AssemblyProducts.getText();

        //предварительная очистка обсервайбл лист
        //Table_Data.clear();
        // выборка сборочных единиц по названию сборного продукта

        String Select2 =" SELECT "   +
                "directory_product.Name_Product"+ "," +
                "assemblyproducts.AmountAssemblyItem_AssemblyProducts" + "," +
                "DATE"+"("+"assemblyproducts.DateRecording_AssemblyProducts"+")" + " AS " +
                "DateRecording_AssemblyProducts" + "," +
                "assemblyproducts.Additionally_AssemblyProducts" +"," +
                "assemblyproducts.id_AssemblyProducts"+

                " FROM "+
                "assemblyproducts"+
                " JOIN "+ "directory_product" +" ON "+ "directory_product.id_Product" + " = " +
                "assemblyproducts.id_NameAssemblyItem_AssemblyProducts" +
                " WHERE " + "`assemblyproducts`.`id_AssemblyProducts`" +"=" +"'"+Const.id_AssemblyProducts+"'";

        try (Connection conn = DbConnectDataBase.connect();

             PreparedStatement PrSt = conn.prepareStatement(Select2)) {
            ResultSet ResSet = PrSt.executeQuery();

//             Statement statement = conn.createStatement()) {
//             ResultSet ResSet = statement.executeQuery(" SELECT directory_product.id_Product FROM " +
//                     " directory_product  WHERE  directory_product.Name_Product  = 'Евровинт 7х50'");

            while (ResSet.next()) {
                Table_Data.add(new Table_AssemblyProducts(
                        TextField_AssemblyProducts.getText() ,
                        ResSet.getString("directory_product.Name_Product"),
                        ResSet.getInt("assemblyproducts.AmountAssemblyItem_AssemblyProducts"),
                        ResSet.getString("DateRecording_AssemblyProducts"),
                        ResSet.getString("assemblyproducts.Additionally_AssemblyProducts"),
                        ResSet.getInt("assemblyproducts.id_AssemblyProducts"))
                );

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_AssemblyProducts.setItems(Table_Data);
    }
//------------------------------------------------------------------------------------------------------------------



    // Запись значений из текст-филдов в переменные.
    private void Record_TextField_Variable() {

        _id_Name_AssemblyProducts = TextField_AssemblyProducts.getText();//
        _id_NameAssemblyItem_AssemblyProducts = TextField_AssemblyItem.getText();//
        _AmountAssemblyItem_AssemblyProducts = Integer.parseInt(TextFild_AssemblyItem_amount.getText());

        // Преобразуем DatePicker В стринговую переменную
        LocalDate ld_DateRecords = DataPickerDataRecords.getValue();
        _DateRecording_AssemblyProducts = String.valueOf(ld_DateRecords);

        _Additionally_AssemblyProducts = TextAreaComment.getText();

    }

    //------------------------------------------------------------------------------------------------------------------

    private void onEdit() {
        // Внесение выделенной строки таблицы в ячейки редактирования
        if (TableView_AssemblyProducts.getSelectionModel().getSelectedItem() != null) {
            Table_AssemblyProducts selectedPerson = TableView_AssemblyProducts.getSelectionModel().getSelectedItem();

            TextField_AssemblyProducts.setText(selectedPerson.getColumn1());
            TextField_AssemblyItem.setText(selectedPerson.getColumn2());
            TextFild_AssemblyItem_amount.setText((String.valueOf(selectedPerson.getColumn3())));
           // DataPickerDataRecords.setText(selectedPerson.getColumn4());


//            String st_BeginningPeriod =ld_EndPeriod.toString();
//           LocalDate ld_EndPeriod = DataPicker_DateFinal.getValue();

          // Преобразуем стринговую переменную в локал дат - для внесения DatePicker
          String st_DateRecords =selectedPerson.getColumn4();
           LocalDate ld_DateRecords = LocalDate.parse(st_DateRecords);
            DataPickerDataRecords.setValue(ld_DateRecords);

            TextAreaComment.setText(selectedPerson.getColumn5());
            _Id_= selectedPerson.getColumn6();
            // System.out.println(_Id_);

        }
    }

//------------------------------------------------------------------------------------------------------------------

    // Инициализация таблицы адресатов сo столбцами.
    private void InitializationTable() {
        Column_AssemblyProducts.setCellValueFactory(new PropertyValueFactory<Table_AssemblyProducts, String>("Column1"));
        ColumnAssemblyItem.setCellValueFactory(new PropertyValueFactory<Table_AssemblyProducts, String>("Column2"));
        Column_amount.setCellValueFactory(new PropertyValueFactory<Table_AssemblyProducts, Integer>("Column3"));
        Column_DataRecords.setCellValueFactory(new PropertyValueFactory<Table_AssemblyProducts, String>("Column4"));
        ColumnComment.setCellValueFactory(new PropertyValueFactory<Table_AssemblyProducts, String>("Column5"));
        ColumnId.setCellValueFactory(new PropertyValueFactory<Table_AssemblyProducts, Integer>("Column6"));
    }


    //------------------------------------------------------------------------------------------------------------------

    // Внесение данных из бд в таблицу по значению в текстфилде (выбор сборного продукта по
    // названию )
    private void SelectAssemblyProducts_Select2 () {

        //предварительная очистка обсервайбл лист
        Table_Data.clear();

        // выборка сборочных единиц по названию сборного продукта
        String Select2 = " SELECT "+ " distinct " +
                " directory_product.Name_Product " +
                " FROM " + " assemblyproducts " +
                " JOIN " + " directory_product " + " ON " +
                " directory_product.id_Product " + " = " + " assemblyproducts.id_Name_AssemblyProducts "+
                " WHERE " +
                " directory_product.Name_Product "+ " LIKE "+"'"+TextFieldSelectAssemblyProducts.getText()+"%'";

        try (Connection conn = DbConnectDataBase.connect();

             PreparedStatement PrSt = conn.prepareStatement(Select2)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_AssemblyProducts(
                        ResSet.getString("directory_product.Name_Product"),
                        " ",
                        0,
                        " ",
                        " ",
                        0)
                );

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_AssemblyProducts.setItems(Table_Data);

        //скрыть ненужные столбцы
        HideTableColumns();
    }
//------------------------------------------------------------------------------------------------------------------

    // Внесение данных из бд в таблицу по значению в текстфилде (выбор сборных единиц продукта по
    // названию сборного продукта)
    private void SelectAssemblyProducts_Select1 () {

        //предварительная очистка обсервайбл лист
        Table_Data.clear();

        // выборка сборочных единиц по названию сборного продукта
//        String Select2 =" SELECT "   +
//                "directory_product.Name_Product"+ "," +
//                "assemblyproducts.AmountAssemblyItem_AssemblyProducts" + "," +
//                "assemblyproducts.DateRecording_AssemblyProducts" + "," +
//                "assemblyproducts.Additionally_AssemblyProducts" +"," +
//                "assemblyproducts.id_AssemblyProducts"+
//
//                " FROM "+
//                "assemblyproducts"+
//                " JOIN "+ "directory_product" +" ON "+ "directory_product.id_Product" + " = " +
//                "assemblyproducts.id_NameAssemblyItem_AssemblyProducts" +
//                " WHERE "+ "assemblyproducts.id_Name_AssemblyProducts" + " = "+"("+" SELECT "+ "directory_product.id_Product"+ " FROM "+
//                " directory_product "+ " WHERE "+ " directory_product.Name_Product " + " = "+ "'" + Name_AssemblyProducts  +"'"+")" ;

        try (Connection conn = DbConnectDataBase.connect();

//             PreparedStatement PrSt = conn.prepareStatement(Select2)) {
//            ResultSet ResSet = PrSt.executeQuery();

             Statement statement = conn.createStatement()) {
             ResultSet ResSet = statement.executeQuery(" SELECT distinct\n" +
                     "`directory_product`.`Name_Product`\n" +
                     "\n" +
                     "FROM `assemblyproducts`\n" +
                     "JOIN  \n" +
                     "`directory_product` ON   `directory_product`.`id_Product` =" +
                     " `assemblyproducts`.`id_Name_AssemblyProducts`;");

            while (ResSet.next()) {
                Table_Data.add(new Table_AssemblyProducts(
                        ResSet.getString("directory_product.Name_Product"),
                        " ",
                        0,
                        " ",
                        " ",
                        0)
                );

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_AssemblyProducts.setItems(Table_Data);

        //скрыть ненужные столбцы
        HideTableColumns();
    }
//------------------------------------------------------------------------------------------------------------------


    // Внесение данных из бд в таблицу по значению в текстфилде (выбор сборных единиц продукта по
    // названию сборного продукта)
    private void SelectAssemblyItem_AssemblyProducts2 () {
        // отобразить скрытые столбцы
        ShowTableColumns();

        //названиt сборного продукта
        String Name_AssemblyProducts = TextField_AssemblyProducts.getText();
        //предварительная очистка обсервайбл лист
        Table_Data.clear();
        // выборка сборочных единиц по названию сборного продукта

        String Select2 =" SELECT "   +
                "directory_product.Name_Product"+ "," +
                "assemblyproducts.AmountAssemblyItem_AssemblyProducts" + "," +
                "DATE"+"("+"assemblyproducts.DateRecording_AssemblyProducts"+")" + " AS " +
                "DateRecording_AssemblyProducts" + "," +
                "assemblyproducts.Additionally_AssemblyProducts" +"," +
                "assemblyproducts.id_AssemblyProducts"+

                " FROM "+
                "assemblyproducts"+
                " JOIN "+ "directory_product" +" ON "+ "directory_product.id_Product" + " = " +
                "assemblyproducts.id_NameAssemblyItem_AssemblyProducts" +
                " WHERE "+ "assemblyproducts.id_Name_AssemblyProducts" + " = "+"("+" SELECT "+ "directory_product.id_Product"+ " FROM "+
                " directory_product "+ " WHERE "+ " directory_product.Name_Product " + " = "+ "'" + Name_AssemblyProducts  +"'"+")" ;

        try (Connection conn = DbConnectDataBase.connect();

             PreparedStatement PrSt = conn.prepareStatement(Select2)) {
            ResultSet ResSet = PrSt.executeQuery();

//             Statement statement = conn.createStatement()) {
//             ResultSet ResSet = statement.executeQuery(" SELECT directory_product.id_Product FROM " +
//                     " directory_product  WHERE  directory_product.Name_Product  = 'Евровинт 7х50'");

            while (ResSet.next()) {
                Table_Data.add(new Table_AssemblyProducts(
                        Name_AssemblyProducts ,
                        ResSet.getString("directory_product.Name_Product"),
                        ResSet.getInt("assemblyproducts.AmountAssemblyItem_AssemblyProducts"),
                        ResSet.getString("DateRecording_AssemblyProducts"),
                        ResSet.getString("assemblyproducts.Additionally_AssemblyProducts"),
                        ResSet.getInt("assemblyproducts.id_AssemblyProducts"))
                );

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_AssemblyProducts.setItems(Table_Data);
    }
//------------------------------------------------------------------------------------------------------------------

    // скрыть столбцы таблицы
   private void HideTableColumns(){
       ColumnAssemblyItem.setVisible(false);
       Column_amount.setVisible(false);
       Column_DataRecords.setVisible(false);
       ColumnComment.setVisible(false);

   }

    // отобразить столбцы таблицы
    private void ShowTableColumns(){
        ColumnAssemblyItem.setVisible(true);
        Column_amount.setVisible(true);
        Column_DataRecords.setVisible(true);
        ColumnComment.setVisible(true);
    }

//------------------------------------------------------------------------------------------------------------------



}
