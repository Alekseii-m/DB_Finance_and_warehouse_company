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

public class Cont_CardService {

    private final ObservableList<Table_CardService> Table_Data = FXCollections.observableArrayList();
   // private final ObservableList<Table_AddSelect> Table_Data2 = FXCollections.observableArrayList();


    //переменные для заполнения БД----------------------------------------------->
      int _id_Service =0;//
      String _Name_Service ="";//
     int _UnitInitialCost_Service =0;//
     int _UnitWithAddedCost_Service =0;//
     String _DateRecording_Service ="";//
     String _Additionally_Service ="";//
    //переменные  для заполнения БД-----------------------------------------------<

    int _id_Service_del =0;// для удаления строки в таблице и БД
    int n =0;    // удаление выделенной строки в таблице

    @FXML
    private AnchorPane AnchorPane_Servis;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Label_1;

    @FXML
    private Button Button_FormClear;

    @FXML
    private Button Button_EditLine;

    @FXML
    private Button Button_SaveService;

    @FXML
    private TextArea TextArea_Comment;

    @FXML
    private TableView<Table_CardService> TableView_Service;

    @FXML
    private TableColumn<Table_CardService, String> Column_NameService;

    @FXML
    private TableColumn<Table_CardService, Integer> Column_StartPrice;

    @FXML
    private TableColumn<Table_CardService, Integer> Column_AdditionalPrice;

    @FXML
    private TableColumn<Table_CardService, String> Column_DateRecords;

    @FXML
    private TableColumn<Table_CardService, String> Column_Comment;

    @FXML
    private TableColumn<Table_CardService, Integer> Column_Id;

//    @FXML
// ?   private Button Button_Insert_BaySell;

    @FXML
    private Button ButtonClearTableView;

    @FXML
    private Button Button_SaveLine;

    @FXML
    private Button Button_DeleteDataLine;

    @FXML
    private DatePicker DataPicker_DateRecords;

    @FXML
    private TextField TextField_Service;

    @FXML
    private Button Button_SelectService;

    @FXML
    private TextField TextField_SelectService;

    @FXML
    private TableView<Table_AddSelect> TableView_Select_Service;

    @FXML
    private TableColumn<Table_AddSelect, String> Column_ListService;

    @FXML
    private TableColumn<Table_AddSelect, Integer> Column_ServiceId;

    @FXML
    private TextField TextField_Select_NameService;

    @FXML
    private Button Button_EditService;

    @FXML
    private Button ButtonSelect_SimilarName;

    @FXML
    private TextField TextField_StartingPrice;

    @FXML
    private TextField TextField_AdditionalPrice;

    @FXML
    private Button Button_Select_NameService_TimeRecords;

    @FXML
    private Button Button_AllService;

    @FXML
    private DatePicker DataPicker_DateStart;

    @FXML
    private DatePicker DataPicker_DateFinal;

    @FXML
    private Button Button_TimeRecords;

    @FXML
    private Button Button_Copy_NameService;

    @FXML
    private Button Button_Go_BaySell;



    @FXML
    void initialize() {

        //-------------------------------------------------------------------------------------------------------

        Button_Copy_NameService.setOnAction(event -> {
            // Внесение выделенной строки таблицы в данные по сервису (name , id)
            if (TableView_Service.getSelectionModel().getSelectedItem() != null) {
                Table_CardService selectedPerson = TableView_Service.getSelectionModel().getSelectedItem();

                Const.CardService_NameService = selectedPerson.getColumn1();
                Const.Id_NameService = selectedPerson.getColumn6();

                System.out.println(Const.CardService_NameService);
                System.out.println(Const.Id_NameService);
            }
        });


        //-------------------------------------------------------------------------------------------------------

        Button_Go_BaySell.setOnAction(event -> {
            //Загрузка окна
            OpenNewWindow_Class.OpenNewWindow_ ("/BD_fxml/BuySell_Service.fxml");
        });

        //-------------------------------------------------------------------------------------------------------

        // Скрыть столбец id таблицы
//        Column_ServiceId.setVisible(false);

        // Скрыть столбец id таблицы
        Column_Id.setVisible(false);

        //-------------------------------------------------------------------------------------------------------

        //Внесение данных из бд в таблицу по первым буквам
        Button_SelectService.setOnAction(event -> {
            // Инициализация таблицы адресатов сo столбцами.
            InitializationTable();

            // Выборка данных по условию если пусто-ChangSelect1, если нет ChangeSelekt2 :
            if(TextField_SelectService.getText().trim().equals(""))
            { SelectProductString_Select1 (); }  //
            else {
                SelectProductString_Select2 ();
            }
        });

        //-------------------------------------------------------------------------------------------------------

        //Внесение данных из бд в таблицу по временному периоду
        Button_TimeRecords.setOnAction(event -> {
            // Инициализация таблицы адресатов сo столбцами.
            InitializationTable();

            // Выборка данных по условию если пусто-ChangSelect1, если нет ChangeSelekt2 :
            if(DataPicker_DateStart.getValue().equals("") |
                    DataPicker_DateFinal.getValue().equals("")  )
            {  }  //2010-01-03 00:00:00
            //  SelectService_String_TimeRecords_1 ();
            else {
                SelectService_String_TimeRecords_2 ();
            }
        });

        //-------------------------------------------------------------------------------------------------------

        //Внесение данных из бд в таблицу по первым буквам и временному периоду
        Button_Select_NameService_TimeRecords.setOnAction(event -> {
            // Инициализация таблицы адресатов сo столбцами.
            InitializationTable();

            // Выборка данных по условию если пусто-ChangSelect1, если нет ChangeSelekt2 :
            if(DataPicker_DateStart.getValue().equals("") |
                    DataPicker_DateFinal.getValue().equals("") |
                    TextField_SelectService.getText().trim().equals("") )
            {  }  //2010-01-03 00:00:00
            //  SelectService_String_TimePeriod_1 ();
            else {
                SelectService_String_TimePeriod_2 ();
            }
        });

        //-------------------------------------------------------------------------------------------------------

        // вставка-сохранение из формы-интерф. в БД
        Button_SaveService.setOnAction(event -> {

            // Запись значений из текст-филдов в переменные.
            Record_TextField_Variable();

            //Вставить данные в БД в таблицу CardService
            DbConnectDataBase.insert_CardService(
                    _Name_Service,
                    _UnitInitialCost_Service,
                    _UnitWithAddedCost_Service,
                    _DateRecording_Service,
                    _Additionally_Service
            );

            // Вставка значений из текст-филдов в таблицу формы.
            InitializationTable();
            // Insert_ValuesTextField_TableView();
            Insert_ValuesTextField_TableView2();

        });

//-------------------------------------------------------------------------------------------------------

        // Внесение выделенной строки таблицы в ячейки редактирования
        Button_EditLine.setOnAction(event -> {
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

        // Удалить строку из TableViewClient
        Button_DeleteDataLine.setOnAction(event -> {
            GetIdSel();
            Delete_Rows();
            delRow_TableView();
        });

        //-------------------------------------------------------------------------------------------------------

        // Обновить данные строки в БДButton_SaveEditLine
        Button_SaveLine.setOnAction(event -> {
            Update_stringBD();

            // Обновление выделенной строки таблицы формы
            UpdateStringTableView();
        });

        //-------------------------------------------------------------------------------------------------------





    }

    //------------------------------------------------------------------------------------------------------------------
    //******************************************************************************************************************
    //------------------------------------------------------------------------------------------------------------------


    // Обновить данные строки в БД (сборка)
    private void Update_stringBD(){
        //   UPDATE `bd_company`.`assemblyproducts` SET `id_Name_AssemblyProducts`='12' WHERE `id_AssemblyProducts`='27';

        // Запись значений из текст-филдов в переменные.
        Record_TextField_Variable();

        //Получение id таблицы БД выделенной строки таблицы:
        GetIdSel();

        //обновить данные строки в БД в таблице
        String update =
                //   UPDATE `bd_company`.`assemblyproducts` SET `id_Name_AssemblyProducts`='12' WHERE `id_AssemblyProducts`='27';
                "UPDATE"+ "`bd_company`.`directory_service`"+ "SET "+
                        "Name_Service"+"="+"'"+_Name_Service+"'"+ ","+ "UnitInitialCost_Service"+"="+"'"+_UnitInitialCost_Service+"'"+","+
                        "UnitWithAddedCost_Service"+"="+"'"+_UnitWithAddedCost_Service+"'"+ ","+ "DateRecording_Service"+"="+"'"+_DateRecording_Service+"'"+","+
                        "Additionally_Service"+"="+"'"+_Additionally_Service+"'"+
                        "WHERE"+ "`id_Service`"+"="+"'"+_id_Service_del+"'";

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
    n =  TableView_Service.getSelectionModel().getSelectedIndex();
    TableView_Service.getItems().remove(n);
}


    //Получение id таблицы БД выделенной строки таблицы:
    private void GetIdSel(){
        if (TableView_Service.getSelectionModel().getSelectedItem() != null) {
            Table_CardService selectedPerson = TableView_Service.getSelectionModel().getSelectedItem();
            _id_Service_del= selectedPerson.getColumn6();
        }

    }


    // Удаляем строку таблицы БД по id
    private void Delete_Rows() {

     //   String DelR =  "DELETE "+ " FROM " + Const.TABLE_CardClient + " WHERE " + Const.COLUMN_CardClient_Id_+ "= ?" ;
        String DelR =  "DELETE "+ " FROM " + "`bd_company`.`directory_service`"  +
                " WHERE " +  "id_Service"+ "= ?";

        try (Connection conn = DbConnectDataBase.connect();
             PreparedStatement pstmt = conn.prepareStatement(DelR)) {

            int n = _id_Service_del ; //  id столбца
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
    private void ClearTextFild(){

        TextField_Select_NameService.clear();
        TextField_StartingPrice.clear();
        TextField_AdditionalPrice.clear();

        DataPicker_DateRecords.getEditor().clear();
        DataPicker_DateRecords.setValue(null);

        TextArea_Comment.clear();
        _id_Service= 0;

    }

//-------------------------------------------------------------------------------------------------------------------

    //Очищаем табле вив:
    private void ClearTableView(){
        TableView_Service.getItems().clear();
    }

//-------------------------------------------------------------------------------------------------------------------


    private void onEdit() {
        // Внесение выделенной строки таблицы в ячейки редактирования
        if (TableView_Service.getSelectionModel().getSelectedItem() != null) {
            Table_CardService selectedPerson = TableView_Service.getSelectionModel().getSelectedItem();



            TextField_Select_NameService.setText(selectedPerson.getColumn1());
            TextField_StartingPrice.setText(String.valueOf(selectedPerson.getColumn2()));
            TextField_AdditionalPrice.setText(String.valueOf(selectedPerson.getColumn3()));

            // Преобразуем стринговую переменную в локал дат - для внесения DatePicker
            String st_DateRecords =selectedPerson.getColumn4();
            LocalDate ld_DateRecords = LocalDate.parse(st_DateRecords);
            System.out.println("DataPicker_DateRecords"+ld_DateRecords);
            DataPicker_DateRecords.setValue(ld_DateRecords);

            TextArea_Comment.setText(selectedPerson.getColumn5());
            _id_Service= selectedPerson.getColumn6();

           // System.out.println("Comment"+selectedPerson.getColumn5());
        }
    }

    //-------------------------------------------------------------------------------------------------------------------

    // Обновление выделенной строки таблицы формы
    private void UpdateStringTableView() {
        if (TableView_Service.getSelectionModel().getSelectedItem() != null) {
            Table_CardService selectedPerson = TableView_Service.getSelectionModel().getSelectedItem();

            //заполнение ячеек выделенной строки новыми значениями
            selectedPerson.setColumn1(_Name_Service);
            selectedPerson.setColumn2(_UnitInitialCost_Service);
            selectedPerson.setColumn3(_UnitWithAddedCost_Service);
            selectedPerson.setColumn4(_DateRecording_Service);
            selectedPerson.setColumn5(_Additionally_Service);
            selectedPerson.setColumn6(_id_Service_del);

            //Обновление таблицы формы
            TableView_Service.refresh();

           // System.out.println("Comment5 -"+selectedPerson.getColumn5());
        }
    }
//------------------------------------------------------------------------------------------------------------------

    // Вставка значений из текст-филдов в таблицу формы.
    private void Insert_ValuesTextField_TableView(){

        Table_Data.add(new Table_CardService(
                _Name_Service,
                _UnitInitialCost_Service,
                _UnitWithAddedCost_Service,
                _DateRecording_Service,
                _Additionally_Service,
                null)
        );
        TableView_Service.setItems(Table_Data);

    }

    // или так :

    // Вставка значений из текст-филдов в таблицу формы
    public void Insert_ValuesTextField_TableView2(){
        //предварительная очистка обсервайбл лист
        //Table_Data.clear();
        String Select2 = "SELECT "+
                "Name_Service" + "," +
                "UnitInitialCost_Service" + "," +
                "UnitWithAddedCost_Service" + "," +
                "DATE"+"("+"DateRecording_Service"+")" + " AS " +"_DateRecording_Service"+"," +
                "Additionally_Service" + "," +
                "id_Service" +
                " FROM " +
                "bd_company.directory_service"+
                " WHERE " + "Name_Service" +
                " LIKE " +"'"+TextField_Select_NameService.getText()+"%'";

        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select2)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_CardService(
                        ResSet.getString("Name_Service"),
                        ResSet.getInt("UnitInitialCost_Service"),
                        ResSet.getInt("UnitWithAddedCost_Service"),
                        ResSet.getString("_DateRecording_Service"),
                        ResSet.getString("Additionally_Service"),
                        ResSet.getInt("id_Service"))
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Service.setItems(Table_Data);
    }

    //------------------------------------------------------------------------------------------------------------------

    // Запись значений из текст-филдов в переменные.
    private void Record_TextField_Variable() {

        _Name_Service = TextField_Select_NameService.getText();//
        _UnitInitialCost_Service = Integer.parseInt(TextField_StartingPrice.getText());//
        _UnitWithAddedCost_Service = Integer.parseInt(TextField_AdditionalPrice.getText());//

        // Преобразуем DatePicker В стринговую переменную
        LocalDate ld_DateRecords = DataPicker_DateRecords.getValue();
         _DateRecording_Service = String.valueOf(ld_DateRecords);//

        _Additionally_Service = TextArea_Comment.getText();//
    }

    //------------------------------------------------------------------------------------------------------------------

    // Инициализация таблицы адресатов сo столбцами.
    private void InitializationTable() {
        Column_NameService.setCellValueFactory(new PropertyValueFactory<Table_CardService, String>("Column1"));
        Column_StartPrice.setCellValueFactory(new PropertyValueFactory<Table_CardService, Integer>("Column2"));
        Column_AdditionalPrice.setCellValueFactory(new PropertyValueFactory<Table_CardService, Integer>("Column3"));
        Column_DateRecords.setCellValueFactory(new PropertyValueFactory<Table_CardService, String>("Column4"));
        Column_Comment.setCellValueFactory(new PropertyValueFactory<Table_CardService, String>("Column5"));
        Column_Id.setCellValueFactory(new PropertyValueFactory<Table_CardService, Integer>("Column6"));
    }

    //------------------------------------------------------------------------------------------------------------------

    //Внесение всех данных из бд в таблицу
    public void SelectProductString_Select1(){
        //предварительная очистка обсервайбл лист
        Table_Data.clear();

        String Select2 = "SELECT "+
                "Name_Service" + "," +
                "UnitInitialCost_Service" + "," +
                "UnitWithAddedCost_Service" + "," +
                "DATE"+"("+"DateRecording_Service"+")" + " AS " +"_DateRecording_Service"+"," +
                "Additionally_Service" + "," +
                "id_Service" +
                " FROM " +
                "bd_company.directory_service";

        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select2)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_CardService(
                        ResSet.getString("Name_Service"),
                        ResSet.getInt("UnitInitialCost_Service"),
                        ResSet.getInt("UnitWithAddedCost_Service"),
                        ResSet.getString("_DateRecording_Service"),
                        ResSet.getString("Additionally_Service"),
                        ResSet.getInt("id_Service"))
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Service.setItems(Table_Data);
    }

    //------------------------------------------------------------------------------------------------------------------

    //Внесение данных из бд в таблицу по первым буквам названия
    public void SelectProductString_Select2(){
        //предварительная очистка обсервайбл лист
        Table_Data.clear();

        String Select2 = "SELECT "+
                "Name_Service" + "," +
                "UnitInitialCost_Service" + "," +
                "UnitWithAddedCost_Service" + "," +
                "DATE"+"("+"DateRecording_Service"+")" + " AS " +"_DateRecording_Service"+"," +
                "Additionally_Service" + "," +
                "id_Service" +
                " FROM " +
                "bd_company.directory_service"+
                " WHERE " + "Name_Service" +
                " LIKE " +"'"+TextField_SelectService.getText()+"%'";

        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select2)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_CardService(
                        ResSet.getString("Name_Service"),
                        ResSet.getInt("UnitInitialCost_Service"),
                        ResSet.getInt("UnitWithAddedCost_Service"),
                        ResSet.getString("_DateRecording_Service"),
                        ResSet.getString("Additionally_Service"),
                        ResSet.getInt("id_Service"))
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Service.setItems(Table_Data);
    }

//------------------------------------------------------------------------------------------------------------------


    //Внесение данных из бд в таблицу по временному периоду
    public void SelectService_String_TimeRecords_2(){
        //преобразование даты в текст для выборки sql
        LocalDate ld_BeginningPeriod = DataPicker_DateStart.getValue();
        LocalDate ld_EndPeriod = DataPicker_DateFinal.getValue();
        String st_BeginningPeriod =ld_BeginningPeriod.toString();
        String st_EndPeriod =ld_EndPeriod.toString();

        //предварительная очистка обсервайбл лист
        Table_Data.clear();

        String Select2 = "SELECT "+
                "Name_Service" + "," +
                "UnitInitialCost_Service" + "," +
                "UnitWithAddedCost_Service" + "," +
                "("+"DATE"+"("+"DateRecording_Service"+")" +")" + " AS " +"_DateRecording_Service_"+"," +

                "Additionally_Service" + "," +
                "id_Service" +
                " FROM " +
                "bd_company.directory_service"+
                " WHERE " + "("+"DATE"+"("+"DateRecording_Service"+")" +")"+" BETWEEN "+"'"+ st_BeginningPeriod+"'"+
                " AND "+"'"+ st_EndPeriod +"'";

        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select2)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_CardService(
                        ResSet.getString("Name_Service"),
                        ResSet.getInt("UnitInitialCost_Service"),
                        ResSet.getInt("UnitWithAddedCost_Service"),
                        ResSet.getString("_DateRecording_Service_"),
                        ResSet.getString("Additionally_Service"),
                        ResSet.getInt("id_Service"))
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Service.setItems(Table_Data);
    }

//------------------------------------------------------------------------------------------------------------------


    //Внесение данных из бд в таблицу по первым буквам названия и временному периоду
    public void SelectService_String_TimePeriod_2(){
        //преобразование даты в текст для выборки sql
        LocalDate ld_BeginningPeriod = DataPicker_DateStart.getValue();
        LocalDate ld_EndPeriod = DataPicker_DateFinal.getValue();
        String st_BeginningPeriod =ld_BeginningPeriod.toString();
        String st_EndPeriod =ld_EndPeriod.toString();

        //предварительная очистка обсервайбл лист
        Table_Data.clear();

        String Select2 = "SELECT "+
                "Name_Service" + "," +
                "UnitInitialCost_Service" + "," +
                "UnitWithAddedCost_Service" + "," +
                "DATE"+"("+"DateRecording_Service"+")" + " AS " +"_DateRecording_Service_"+"," +
                "Additionally_Service" + "," +
                "id_Service" +
                " FROM " +
                "bd_company.directory_service"+
                " WHERE " + "("+"("+"DATE"+"("+"DateRecording_Service"+")" +")"+" BETWEEN "+"'"+ st_BeginningPeriod+"'"+
                " AND "+"'"+ st_EndPeriod +"'"+ ")" +  " AND " + "Name_Service" +
                " LIKE " +"'"+TextField_SelectService.getText()+"%'";

        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select2)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_CardService(
                        ResSet.getString("Name_Service"),
                        ResSet.getInt("UnitInitialCost_Service"),
                        ResSet.getInt("UnitWithAddedCost_Service"),
                        ResSet.getString("_DateRecording_Service_"),
                        ResSet.getString("Additionally_Service"),
                        ResSet.getInt("id_Service"))
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Service.setItems(Table_Data);
    }

//------------------------------------------------------------------------------------------------------------------





}
