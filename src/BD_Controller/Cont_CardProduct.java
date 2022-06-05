package BD_Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import BD_Common.Const;
import BD_Common.DbConnectDataBase;
import BD_Common.OpenNewWindow_Class;
import ObservListTable.Table_CardClient;
import ObservListTable.Table_CardOrder;
import ObservListTable.Table_CardProduct;
import ObservListTable.Table_CardService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class Cont_CardProduct {

    private final ObservableList<Table_CardProduct> Table_Data = FXCollections.observableArrayList();

    //переменные для заполнения БД----------------------------------------------->
    int _id_Product =0;
    String _Name_Product ="";
    String _Product_Description ="";
    int _UnitInitialCost_Product =0;
    int _UnitWithAddedCost_Product =0;
    String _DateRecording_Product ="";
    String _Assembly_Product ="";
    String _Company_Product ="";
    String _Additionally_Product ="";
    //переменные  для заполнения БД-----------------------------------------------<

    int n =0;    // удаление выделенной строки в таблице
    int _id_del =0;// для удаления строки в таблице и БД



    @FXML
    private AnchorPane AnchorPane_Product; //


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Label_1;

    @FXML
    private Button Button_FormClear;

    @FXML
    private Button ButtonEditLine;

    @FXML
    private Button Button_SaveProduct;

    @FXML
    private TextArea TextArea_Comment;

    @FXML
    private TableView<Table_CardProduct> TableView_Product;

    @FXML
    private TableColumn<Table_CardProduct, String> Column_NameProduct;

    @FXML
    private TableColumn<Table_CardProduct, String> Column_DescriptionProduct;

    @FXML
    private TableColumn<Table_CardProduct, Integer> Column_StartingPrice;

    @FXML
    private TableColumn<Table_CardProduct, Integer> Column_AdditionalPrice;

    @FXML
    private TableColumn<Table_CardProduct, String> Column_CompanySeller;

    @FXML
    private TableColumn<Table_CardProduct, String> Column_AssembledProduct;

    @FXML
    private TableColumn<Table_CardProduct, String> Column_DateRecords;

    @FXML
    private TableColumn<Table_CardProduct, String> Column_Comment;

    @FXML
    private TableColumn<Table_CardProduct, Integer> Column_Id;

    @FXML
    private Button ButtonClearTableView;

    @FXML
    private Button ButtonSaveEditLine;

    @FXML
    private Button ButtonDeleteDataLine;

    @FXML
    private DatePicker DataPicker_DateRecords;

    @FXML
    private TextField TextField_Product;

    @FXML
    private Button Button_SelectProduct;

    @FXML
    private TextField TextField_SelectProduct;

    @FXML
    private TableView<?> TableView_ListProduct;

    @FXML
    private TableColumn<?, ?> Column_ListProduct;

    @FXML
    private TableColumn<?, ?> Column_ListProduct_Id;

    @FXML
    private TextField TextField_NameProduct;

    @FXML
    private Button Button_EditProduct;

    @FXML
    private Button Button_SelectSimilarProduct1;

    @FXML
    private TextField TextField_DescriptionProduct;

    @FXML
    private Button Button_SelectSimilarProduct2;

    @FXML
    private TextField TextField_StartingPrice;

    @FXML
    private TextField TextField_FinalPrice;

    @FXML
    private ChoiceBox<String> ChoiceBox_AssembledProduct_Y_N;

    @FXML
    private TextField TextField_CompanySeller;

    @FXML
    private Button Button_Select_CompanySeller;

    @FXML
    private Button Button_Select_NameProduct_TimePeriod;

    @FXML
    private Button Button_Select_TimePeriod_FirmSeller;

    @FXML
    private Button Button_Select_NameProduct_FirmSeller;

    @FXML
    private Button ButtonSelect_FirmSeller;

    @FXML
    private DatePicker DataPicker_BeginningPeriod;

    @FXML
    private DatePicker DataPicker_EndPeriod;

    @FXML
    private Button Button_Go_BaySell;

    @FXML
    private Button Button_Copy_Product;

    @FXML
    private Button Button_InsertNameProduct;

    @FXML
    private Button Button_InsertDescriptionProduct;

    @FXML
    private Button Button_Insert_CompanySeller;

//    @FXML
//    private Button ButtonEditLine;



    @FXML
    void initialize() {

        // Скрыть столбец id таблицы ButtonEditLine
       // Column_ListProduct_Id.setVisible(false);

        // Скрыть столбец id таблицы
        Column_Id.setVisible(false);


        //-------------------------------------------------------------------------------------------------------
        Button_Select_CompanySeller.setOnAction(event -> {
            //Загрузка окна  c названиями продуктов
            OpenNewWindow_Class.OpenNewWindow_ ("/BD_fxml/AddSelectCompany.fxml");
        });
        //внести в текст-филд в название фирмы
        Button_Insert_CompanySeller.setOnAction(event -> {
            TextField_CompanySeller.setText(Const.CardCounterparty_Company_Counterparty);
        });

        //-------------------------------------------------------------------------------------------------------

        //Добавить в ChoiceBox значения да и нет
       ChoiceBox_AssembledProduct_Y_N.getItems().addAll("Да", "Нет");

        ChoiceBox_AssembledProduct_Y_N.setOnAction(event -> {
            System.out.println("ChoiceBox "+ ChoiceBox_AssembledProduct_Y_N.getValue()); //
            // ChoiceBox_AssembledProduct_Y_N.getValue() - значение ChoiceBox
        });

        //-------------------------------------------------------------------------------------------------------

        Button_SelectSimilarProduct1.setOnAction(event -> {
            //Загрузка окна  c названиями продуктов
            OpenNewWindow_Class.OpenNewWindow_ ("/BD_fxml/AddSelectProduct.fxml");
        });
        Button_SelectSimilarProduct2.setOnAction(event -> {
            //Загрузка окна с описаниями продуктов
            OpenNewWindow_Class.OpenNewWindow_ ("/BD_fxml/AddSelectDescriptionProduct.fxml");
        });

        //-------------------------------------------------------------------------------------------------------
        //внести в текст-филд в  название продукта
        Button_InsertNameProduct.setOnAction(event -> {
            TextField_NameProduct.setText(Const.CardProduct_NameProduct);
        });

        //внести в текст-филд в описание продукта
        Button_InsertDescriptionProduct.setOnAction(event -> {
            TextField_DescriptionProduct.setText(Const.CardProduct_DescriptionProduct);
        });


        //-------------------------------------------------------------------------------------------------------

        Button_Copy_Product.setOnAction(event -> {
            // Внесение выделенной строки таблицы в данные по сервису (name , id)
            if (TableView_Product.getSelectionModel().getSelectedItem() != null) {
                Table_CardProduct selectedPerson = TableView_Product.getSelectionModel().getSelectedItem();

                Const.CardProduct_NameProduct = selectedPerson.getColumn1();
                Const.Id_NameProduct = selectedPerson.getColumn9();

//                System.out.println(Const.CardService_NameService);
//                System.out.println(Const.Id_NameService);
           }
        });

        //-------------------------------------------------------------------------------------------------------

        Button_Go_BaySell.setOnAction(event -> {
            //Загрузка окна
            OpenNewWindow_Class.OpenNewWindow_ ("/BD_fxml/BuySell_Service.fxml");
        });

        //-------------------------------------------------------------------------------------------------------

        //Внесение данных из бд в таблицу по первым буквам
        Button_SelectProduct.setOnAction(event -> {
            System.out.println("шаг1");
            // Инициализация таблицы адресатов сo столбцами.
            InitializationTable();

            // Выборка данных по условию если пусто-ChangSelect1, если нет ChangeSelekt2 :
            if(TextField_SelectProduct.getText().trim().equals(""))
            { SelectProductString_Select1 (); }  //
            else {
                SelectProductString_Select2 ();
            }
        });

        //-------------------------------------------------------------------------------------------------------

        //Внесение данных из бд в таблицу по первым буквам и временному периоду
        Button_Select_NameProduct_TimePeriod.setOnAction(event -> {
            // Инициализация таблицы адресатов сo столбцами.
            InitializationTable();

            // Выборка данных по условию если пусто-ChangSelect1, если нет ChangeSelekt2 :
            if(DataPicker_BeginningPeriod.getValue().equals("") |
                    DataPicker_EndPeriod.getValue().equals("") |
                    TextField_SelectProduct.getText().trim().equals("") )
            {  }  //2010-01-03 00:00:00
            //  SelectProduct_String_TimePeriod_1 ();
            else {
                SelectProduct_String_TimePeriod_2 ();
            }
        });

        //-------------------------------------------------------------------------------------------------------

        //Внесение данных из бд в таблицу по первым буквам и продавцу
        Button_Select_NameProduct_FirmSeller.setOnAction(event -> {
            // Инициализация таблицы адресатов сo столбцами.
            InitializationTable();

            // Выборка данных по условию если пусто-ChangSelect1, если нет ChangeSelekt2 :
            if(TextField_SelectProduct.getText().trim().equals("") |
                    TextField_CompanySeller.getText().trim().equals("") )
            {  }  //2010-01-03 00:00:00
            //  SelectProduct_NameProduct_FirmSeller_1 ();
            else {
                SelectProduct_NameProduct_FirmSeller_2 ();
            }
        });

        //-------------------------------------------------------------------------------------------------------


        //Внесение данных из бд в таблицу по продавцу товара и временному периоду
        Button_Select_TimePeriod_FirmSeller.setOnAction(event -> {
            // Инициализация таблицы адресатов сo столбцами.
            InitializationTable();

            // Выборка данных по условию если пусто-ChangSelect1, если нет ChangeSelekt2 :
            if(DataPicker_BeginningPeriod.getValue().equals("") |
                    DataPicker_EndPeriod.getValue().equals("") |
                    TextField_CompanySeller.getText().trim().equals("") )
            {  }  //2010-01-03 00:00:00
            //  SelectProduct_TimePeriod_FirmSeller_1 ();
            else {
                SelectProduct_TimePeriod_FirmSeller_2 ();
            }
        });

        //-------------------------------------------------------------------------------------------------------

        //Внесение данных из бд в таблицу по продавцу товара
        ButtonSelect_FirmSeller.setOnAction(event -> {
            // Инициализация таблицы адресатов сo столбцами.
            InitializationTable();

            // Выборка данных по условию если пусто-ChangSelect1, если нет ChangeSelekt2 :
            if(TextField_CompanySeller.getText().trim().equals(""))
            {  }  //SelectProduct_FirmSeller1 ();
            else {
                SelectProduct_FirmSeller2 ();
            }
        });

        //-------------------------------------------------------------------------------------------------------

        // вставка-сохранение из формы-интерф. в БД
        Button_SaveProduct.setOnAction(event -> {

            // Запись значений из текст-филдов в переменные.
            Record_TextField_Variable();

            //Вставить данные в БД в таблицу CardProduct
            DbConnectDataBase.insert_CardProduct(
             _Name_Product ,
             _Product_Description ,
             _UnitInitialCost_Product ,
             _UnitWithAddedCost_Product ,
             _DateRecording_Product ,
             _Assembly_Product ,
             _Company_Product ,
             _Additionally_Product
            );
            // Вставка значений из текст-филдов в таблицу формы.
            InitializationTable();
          //  Insert_ValuesTextField_TableView();
            Insert_ValuesTextField_TableView2();
        });



//-------------------------------------------------------------------------------------------------------

        // Внесение выделенной строки таблицы в ячейки редактирования
        ButtonEditLine.setOnAction(event -> {

           onEdit();

        });

//-------------------------------------------------------------------------------------------------------


        // очистка формы интерфейса
        Button_FormClear.setOnAction(event -> {
            ClearTextFild();
        });
        //-------------------------------------------------------------------------------------------------------

        //  очистка таблицы интерфейса
        ButtonClearTableView.setOnAction(event -> {
            ClearTableView();
        });
        //-------------------------------------------------------------------------------------------------------



        // Удалить строку из TableView
        ButtonDeleteDataLine.setOnAction(event -> {
            GetIdSel();
            Delete_Rows();
            delRow_TableView();
        });

        //-------------------------------------------------------------------------------------------------------

        // Обновить данные строки в БД
        ButtonSaveEditLine.setOnAction(event -> {
            Update_stringBD();

            // Обновление выделенной строки таблицы формы
            UpdateStringTableView();
        });

        //-------------------------------------------------------------------------------------------------------





    }

    //------------------------------------------------------------------------------------------------------------------
    //******************************************************************************************************************
    //------------------------------------------------------------------------------------------------------------------





    //-------------------------------------------------------------------------------------------------------------------

    // Обновление выделенной строки таблицы формы
    private void UpdateStringTableView() {
        if (TableView_Product.getSelectionModel().getSelectedItem() != null) {
            Table_CardProduct selectedPerson = TableView_Product.getSelectionModel().getSelectedItem();

            //заполнение ячеек выделенной строки новыми значениями
            selectedPerson.setColumn1(_Name_Product);
            selectedPerson.setColumn2(_Product_Description);
            selectedPerson.setColumn3(_UnitInitialCost_Product);
            selectedPerson.setColumn4(_UnitWithAddedCost_Product);
            selectedPerson.setColumn5(_Company_Product);
            selectedPerson.setColumn6(_Assembly_Product);
            selectedPerson.setColumn7(_DateRecording_Product);
            selectedPerson.setColumn8(_Additionally_Product);
            selectedPerson.setColumn9(_id_del);


            //Обновление таблицы формы
            TableView_Product.refresh();

            // System.out.println("Comment5 -"+selectedPerson.getColumn5());
        }
    }

    //-------------------------------------------------------------------------------------------------------------------

    // Обновить данные строки в БД
    private void Update_stringBD(){

        // Запись значений из текст-филдов в переменные.
        Record_TextField_Variable();

        //Получение id таблицы БД выделенной строки таблицы:
        GetIdSel();

        //обновить данные строки в БД в таблице
        String update =
                //   UPDATE `bd_company`.`assemblyproducts` SET `id_Name_AssemblyProducts`='12' WHERE `id_AssemblyProducts`='27';
                "UPDATE"+ " `bd_company`.`directory_product` "+ " SET "+
                        "Name_Product"+"="+"'"+_Name_Product+"'"+ ","+ "Product_Description"+"="+"'"+_Product_Description+"'"+","+
                        "UnitInitialCost_Product"+"="+"'"+_UnitInitialCost_Product+"'"+ ","+ "UnitWithAddedCost_Product"+"="+"'"+_UnitWithAddedCost_Product+"'"+","+
                        "DateRecording_Product"+"="+"'"+_DateRecording_Product+"'"+ ","+"Assembly_Product"+"="+"'"+_Assembly_Product+"'"+","+
                        "Company_Product"+"="+"'"+_Company_Product+"'"+ ","+"Additionally_Product"+"="+"'"+_Additionally_Product+"'"+
                        " WHERE " + "`directory_product`.`id_Product`"+"="+"'"+_id_del+"'";

        try (Connection conn = DbConnectDataBase.connect();

             PreparedStatement PrSt = conn.prepareStatement(update)) {
            PrSt.executeUpdate();
        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    //------------------------------------------------------------------------------------------------------------------


    private void delRow_TableView(){
        // удаление выделенной строки в таблице
        n =  TableView_Product.getSelectionModel().getSelectedIndex();
        TableView_Product.getItems().remove(n);
    }


    //Получение id таблицы БД выделенной строки таблицы:
    private void GetIdSel(){
        if (TableView_Product.getSelectionModel().getSelectedItem() != null) {
            Table_CardProduct selectedPerson = TableView_Product.getSelectionModel().getSelectedItem();
            _id_del= selectedPerson.getColumn9();
        }
    }


    // Удаляем строку таблицы БД по id
    private void Delete_Rows() {

        String DelR =  "DELETE "+ " FROM " + "`bd_company`.`directory_product`"  +
                " WHERE " +  "id_Product"+ "= ?";

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

        TextField_NameProduct.clear();
        TextField_DescriptionProduct.clear();
        TextField_StartingPrice.clear();
        TextField_FinalPrice.clear();
        TextField_CompanySeller.clear();

        ChoiceBox_AssembledProduct_Y_N.getSelectionModel().clearSelection();
        ChoiceBox_AssembledProduct_Y_N.setValue(null);

        DataPicker_DateRecords.getEditor().clear();
        DataPicker_DateRecords.setValue(null);

        TextArea_Comment.clear();
        _id_Product= 0;

    }

//-------------------------------------------------------------------------------------------------------------------

    //Очищаем табле вив:
    public void ClearTableView(){
        TableView_Product.getItems().clear();
    }

//-------------------------------------------------------------------------------------------------------------------

    private void onEdit() {
        // Внесение выделенной строки таблицы в ячейки редактирования
        if (TableView_Product.getSelectionModel().getSelectedItem() != null) {
            Table_CardProduct selectedPerson = TableView_Product.getSelectionModel().getSelectedItem();

            TextField_NameProduct.setText(selectedPerson.getColumn1());
            TextField_DescriptionProduct.setText(selectedPerson.getColumn2());
            TextField_StartingPrice.setText(String.valueOf(selectedPerson.getColumn3()));
            TextField_FinalPrice.setText(String.valueOf(selectedPerson.getColumn4()));
            TextField_CompanySeller.setText(selectedPerson.getColumn5());

            // Заполнение ChoiceBox--->
            ChoiceBox_AssembledProduct_Y_N.setValue(selectedPerson.getColumn6());

            // Преобразуем стринговую переменную в локал дат - для внесения DatePicker   TextField_CompanySeller
            String st_DateRecords =selectedPerson.getColumn7();
            LocalDate ld_DateRecords = LocalDate.parse(st_DateRecords);
            System.out.println("DataPicker_DateRecords"+ld_DateRecords);
            DataPicker_DateRecords.setValue(ld_DateRecords);

            TextArea_Comment.setText(selectedPerson.getColumn8());
            _id_Product= selectedPerson.getColumn9();

//            System.out.println("_id_Service"+selectedPerson.getColumn6());
        }
    }

//------------------------------------------------------------------------------------------------------------------


    // Вставка значений из текст-филдов в таблицу формы.
    private void Insert_ValuesTextField_TableView(){

        Table_Data.add(new Table_CardProduct(
                _Name_Product ,
                _Product_Description ,
                _UnitInitialCost_Product ,
                _UnitWithAddedCost_Product ,
                _Company_Product ,
                _Assembly_Product ,
                _DateRecording_Product ,
                _Additionally_Product,
                null)
        );
        TableView_Product.setItems(Table_Data);

    }

// или

    // Вставка значений из текст-филдов в таблицу формы
    public void Insert_ValuesTextField_TableView2(){
        //предварительная очистка обсервайбл лист
        //Table_Data.clear();

        String Select2 = "SELECT "+
                "Name_Product" + "," +
                "Product_Description" + "," +
                "UnitInitialCost_Product" + "," +
                "UnitWithAddedCost_Product" + "," +
                "Company_Product" + "," +
                "Assembly_Product" + "," +
                "("+"DATE"+"("+"DateRecording_Product"+")" +")"+ " AS " +"_DateRecording_Product_"+"," +
                "Additionally_Product" + "," +
                "id_Product" +
                " FROM " +
                "bd_company.directory_product" +
                " WHERE " + "Name_Product" +
                " LIKE " +"'"+TextField_NameProduct.getText()+"%'";

        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select2)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_CardProduct(
                        ResSet.getString("Name_Product"),
                        ResSet.getString("Product_Description"),
                        ResSet.getInt("UnitInitialCost_Product"),
                        ResSet.getInt("UnitWithAddedCost_Product"),
                        ResSet.getString("Company_Product"),
                        ResSet.getString("Assembly_Product"),
                        ResSet.getString("_DateRecording_Product_"),
                        ResSet.getString("Additionally_Product"),
                        ResSet.getInt("id_Product"))
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Product.setItems(Table_Data);
    }

    //------------------------------------------------------------------------------------------------------------------

    // Запись значений из текст-филдов в переменные.
    private void Record_TextField_Variable() {

        _Name_Product = TextField_NameProduct.getText();
        _Product_Description = TextField_DescriptionProduct.getText();
        _UnitInitialCost_Product = Integer.parseInt(TextField_StartingPrice.getText());
        _UnitWithAddedCost_Product = Integer.parseInt(TextField_FinalPrice.getText());

        // Преобразуем DatePicker В стринговую переменную
        LocalDate ld_DateRecords = DataPicker_DateRecords.getValue();
        _DateRecording_Product = String.valueOf(ld_DateRecords);

        _Assembly_Product = ChoiceBox_AssembledProduct_Y_N.getValue();
        _Company_Product = TextField_CompanySeller.getText();
        _Additionally_Product = TextArea_Comment.getText();

    }

    //------------------------------------------------------------------------------------------------------------------

    // Инициализация таблицы адресатов сo столбцами.
    private void InitializationTable() {
        Column_NameProduct.setCellValueFactory(new PropertyValueFactory<Table_CardProduct, String>("Column1"));
        Column_DescriptionProduct.setCellValueFactory(new PropertyValueFactory<Table_CardProduct, String>("Column2"));
        Column_StartingPrice.setCellValueFactory(new PropertyValueFactory<Table_CardProduct, Integer>("Column3"));
        Column_AdditionalPrice.setCellValueFactory(new PropertyValueFactory<Table_CardProduct, Integer>("Column4"));
        Column_CompanySeller.setCellValueFactory(new PropertyValueFactory<Table_CardProduct, String>("Column5"));
        Column_AssembledProduct.setCellValueFactory(new PropertyValueFactory<Table_CardProduct, String>("Column6"));
        Column_DateRecords.setCellValueFactory(new PropertyValueFactory<Table_CardProduct, String>("Column7"));
        Column_Comment.setCellValueFactory(new PropertyValueFactory<Table_CardProduct, String>("Column8"));
        Column_Id.setCellValueFactory(new PropertyValueFactory<Table_CardProduct, Integer>("Column9"));
    }

    //------------------------------------------------------------------------------------------------------------------

    //Внесение всех данных из бд в таблицу
    public void SelectProductString_Select1(){
        //предварительная очистка обсервайбл лист
        Table_Data.clear();
        System.out.println("шаг2");
        String Select2 = "SELECT "+
                "Name_Product" + "," +
                "Product_Description" + "," +
                "UnitInitialCost_Product" + "," +
                "UnitWithAddedCost_Product" + "," +
                "Company_Product" + "," +
                "Assembly_Product" + "," +
                "("+"DATE"+"("+"DateRecording_Product"+")" +")"+ " AS " +"_DateRecording_Product_"+"," +
                //"DateRecording_Product" + "," +
                "Additionally_Product" + "," +
                "id_Product" +
                " FROM " +
                "bd_company.directory_product";

        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select2)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_CardProduct(
                        ResSet.getString("Name_Product"),
                        ResSet.getString("Product_Description"),
                        ResSet.getInt("UnitInitialCost_Product"),
                        ResSet.getInt("UnitWithAddedCost_Product"),
                        ResSet.getString("Company_Product"),
                        ResSet.getString("Assembly_Product"),
                        ResSet.getString("_DateRecording_Product_"),
                        ResSet.getString("Additionally_Product"),
                        ResSet.getInt("id_Product"))
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Product.setItems(Table_Data);
    }

    //------------------------------------------------------------------------------------------------------------------

    //Внесение данных из бд в таблицу по первым буквам названия
    public void SelectProductString_Select2(){
        //предварительная очистка обсервайбл лист
        Table_Data.clear();

        String Select2 = "SELECT "+
                "Name_Product" + "," +
                "Product_Description" + "," +
                "UnitInitialCost_Product" + "," +
                "UnitWithAddedCost_Product" + "," +
                "Company_Product" + "," +
                "Assembly_Product" + "," +
                "("+"DATE"+"("+"DateRecording_Product"+")" +")"+ " AS " +"_DateRecording_Product_"+"," +
                "Additionally_Product" + "," +
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
                Table_Data.add(new Table_CardProduct(
                        ResSet.getString("Name_Product"),
                        ResSet.getString("Product_Description"),
                        ResSet.getInt("UnitInitialCost_Product"),
                        ResSet.getInt("UnitWithAddedCost_Product"),
                        ResSet.getString("Company_Product"),
                        ResSet.getString("Assembly_Product"),
                        ResSet.getString("_DateRecording_Product_"),
                        ResSet.getString("Additionally_Product"),
                        ResSet.getInt("id_Product"))
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Product.setItems(Table_Data);
    }

//------------------------------------------------------------------------------------------------------------------

    //Внесение данных из бд в таблицу по первым буквам названия
    public void SelectProduct_String_TimePeriod_2(){
        //преобразование даты в текст для выборки sql
        LocalDate ld_BeginningPeriod = DataPicker_BeginningPeriod.getValue();
        LocalDate ld_EndPeriod = DataPicker_EndPeriod.getValue();
        String st_BeginningPeriod =ld_BeginningPeriod.toString();
        String st_EndPeriod =ld_EndPeriod.toString();

        //предварительная очистка обсервайбл лист
        Table_Data.clear();

        String Select2 = "SELECT "+
                "Name_Product" + "," +
                "Product_Description" + "," +
                "UnitInitialCost_Product" + "," +
                "UnitWithAddedCost_Product" + "," +
                "Company_Product" + "," +
                "Assembly_Product" + "," +
                "("+"DATE"+"("+"DateRecording_Product"+")" +")" + " AS " +"_DateRecording_Product_"+","+
                "Additionally_Product" + "," +
                "id_Product" +
                " FROM " +
                "bd_company.directory_product" +
                        " WHERE " + "(" + "("+"DATE"+"("+"DateRecording_Product"+")" +")"+" BETWEEN "+"'"+ st_BeginningPeriod+"'"+
                " AND "+"'"+ st_EndPeriod +"'"+ ")" +  " AND " + "Name_Product" +
                " LIKE " +"'"+TextField_SelectProduct.getText()+"%'";

        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select2)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_CardProduct(
                        ResSet.getString("Name_Product"),
                        ResSet.getString("Product_Description"),
                        ResSet.getInt("UnitInitialCost_Product"),
                        ResSet.getInt("UnitWithAddedCost_Product"),
                        ResSet.getString("Company_Product"),
                        ResSet.getString("Assembly_Product"),
                        ResSet.getString("_DateRecording_Product_"),
                        ResSet.getString("Additionally_Product"),
                        ResSet.getInt("id_Product"))
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Product.setItems(Table_Data);
    }

//------------------------------------------------------------------------------------------------------------------

    //Внесение данных из бд в таблицу по первым буквам названия и по фирме продавцу
    public void SelectProduct_NameProduct_FirmSeller_2(){
        //предварительная очистка обсервайбл лист
        Table_Data.clear();

        String Select2 = "SELECT "+
                "Name_Product" + "," +
                "Product_Description" + "," +
                "UnitInitialCost_Product" + "," +
                "UnitWithAddedCost_Product" + "," +
                "Company_Product" + "," +
                "Assembly_Product" + "," +
                "("+"DATE"+"("+"DateRecording_Product"+")" +")" + " AS " +"_DateRecording_Product_"+"," +
                "Additionally_Product" + "," +
                "id_Product" +
                " FROM " +
                "bd_company.directory_product" +
                " WHERE " +"Name_Product" +
                " LIKE " +"'"+TextField_SelectProduct.getText()+"%'" +
                  " AND " + "Company_Product" +
                " LIKE " +"'"+TextField_CompanySeller.getText()+"%'";

        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select2)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_CardProduct(
                        ResSet.getString("Name_Product"),
                        ResSet.getString("Product_Description"),
                        ResSet.getInt("UnitInitialCost_Product"),
                        ResSet.getInt("UnitWithAddedCost_Product"),
                        ResSet.getString("Company_Product"),
                        ResSet.getString("Assembly_Product"),
                        ResSet.getString("_DateRecording_Product_"),
                        ResSet.getString("Additionally_Product"),
                        ResSet.getInt("id_Product"))
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Product.setItems(Table_Data);
    }
//------------------------------------------------------------------------------------------------------------------

    //Внесение данных из бд в таблицу по первым буквам названия
    public void SelectProduct_TimePeriod_FirmSeller_2(){
        //преобразование даты в текст для выборки sql
        LocalDate ld_BeginningPeriod = DataPicker_BeginningPeriod.getValue();
        LocalDate ld_EndPeriod = DataPicker_EndPeriod.getValue();
        String st_BeginningPeriod =ld_BeginningPeriod.toString();
        String st_EndPeriod =ld_EndPeriod.toString();

        //предварительная очистка обсервайбл лист
        Table_Data.clear();

        String Select2 = "SELECT "+
                "Name_Product" + "," +
                "Product_Description" + "," +
                "UnitInitialCost_Product" + "," +
                "UnitWithAddedCost_Product" + "," +
                "Company_Product" + "," +
                "Assembly_Product" + "," +
                "("+"DATE"+"("+"DateRecording_Product"+")" +")"+ " AS " +"_DateRecording_Product_"+"," +
                "Additionally_Product" + "," +
                "id_Product" +
                " FROM " +
                "bd_company.directory_product" +
                " WHERE " + "(" + "("+"DATE"+"("+"DateRecording_Product"+")" +")"+" BETWEEN "+"'"+ st_BeginningPeriod+"'"+
                " AND "+"'"+ st_EndPeriod +"'"+ ")" +  " AND " + "Company_Product" +
                " LIKE " +"'"+TextField_CompanySeller.getText()+"%'";

        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select2)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_CardProduct(
                        ResSet.getString("Name_Product"),
                        ResSet.getString("Product_Description"),
                        ResSet.getInt("UnitInitialCost_Product"),
                        ResSet.getInt("UnitWithAddedCost_Product"),
                        ResSet.getString("Company_Product"),
                        ResSet.getString("Assembly_Product"),
                        ResSet.getString("_DateRecording_Product_"),
                        ResSet.getString("Additionally_Product"),
                        ResSet.getInt("id_Product"))
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Product.setItems(Table_Data);
    }

//------------------------------------------------------------------------------------------------------------------


    //Внесение данных из бд в таблицу по первым буквам названия
    public void SelectProduct_FirmSeller2(){
        //предварительная очистка обсервайбл лист
        Table_Data.clear();

        String Select2 = "SELECT "+
                "Name_Product" + "," +
                "Product_Description" + "," +
                "UnitInitialCost_Product" + "," +
                "UnitWithAddedCost_Product" + "," +
                "Company_Product" + "," +
                "Assembly_Product" + "," +
                "("+"DATE"+"("+"DateRecording_Product"+")" +")"+ " AS " +"_DateRecording_Product_"+"," +
                "Additionally_Product" + "," +
                "id_Product" +
                " FROM " +
                "bd_company.directory_product" +
                " WHERE " + "Company_Product" +
                " LIKE " +"'"+TextField_CompanySeller.getText()+"%'";

        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select2)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_CardProduct(
                        ResSet.getString("Name_Product"),
                        ResSet.getString("Product_Description"),
                        ResSet.getInt("UnitInitialCost_Product"),
                        ResSet.getInt("UnitWithAddedCost_Product"),
                        ResSet.getString("Company_Product"),
                        ResSet.getString("Assembly_Product"),
                        ResSet.getString("_DateRecording_Product_"),
                        ResSet.getString("Additionally_Product"),
                        ResSet.getInt("id_Product"))
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Product.setItems(Table_Data);
    }

//------------------------------------------------------------------------------------------------------------------


}
