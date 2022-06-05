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
import ObservListTable.Table_AssemblyProducts;
import ObservListTable.Table_CardClient;
import ObservListTable.Table_CardCounterparty;
import ObservListTable.Table_CardProduct;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;



public class Cont_CardClient {

    private final ObservableList<Table_CardClient> Table_Data = FXCollections.observableArrayList();

    //переменные для заполнения БД----------------------------------------------->
    int _id_Client =0;
    String _Name_Client ="";
    String _Address_Client ="";
    String _Phone_Client ="";
    String _Mail_Client ="";
    String _DateRecording_Client ="";
    String _Additionally_Client ="";
    //переменные  для заполнения БД-----------------------------------------------<
    int _id_del =0;// для удаления строки в таблице и БД
    int n =0;    // удаление выделенной строки в таблице

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorPaneCardClient;

    @FXML
    private Label Label_1;

    @FXML
    private TextField TextFildName;

    @FXML
    private TextField TextFildMail;

    @FXML
    private TextField TextFildTell;

    @FXML
    private Button ButtonInsertData;

    @FXML
    private Button ButtonChangeLine;

    @FXML
    private Button ButtonDeleteLine;

    @FXML
    private TableView<Table_CardClient> TableViewClient;

    @FXML
    private TableColumn<Table_CardClient, String> Column_NameClient;

    @FXML
    private TableColumn<Table_CardClient, String> Column_Address;

    @FXML
    private TableColumn<Table_CardClient, String> Column_Tell;

    @FXML
    private TableColumn<Table_CardClient, String> Column_Mail;

    @FXML
    private TableColumn<Table_CardClient, String> Column_DataRecords;

    @FXML
    private TableColumn<Table_CardClient, String> Column_Comment;

    @FXML
    private TableColumn<Table_CardClient, Integer> Id;

    @FXML
    private Button ButtonSelectClientBD;

    @FXML
    private TextField TextFildSelectClient;

    @FXML
    private Button ButtonClearTextFild;

    @FXML
    private Button ButtonClearTableView;

    @FXML
    private TextArea TextAreaKomment;

    @FXML
    private Button ButtonSaveChangeLine;

    @FXML
    private Button Button_Go_CardOrder;

    @FXML
    private Button ButtonSelectClient_Buy_Sell;

    @FXML
    private TextArea TextAreaAddress;

    @FXML
    private DatePicker DataPicker_DateRecords;


    @FXML
    void initialize() {

        // Скрыть столбец id таблицы
        Id.setVisible(false);

        //-------------------------------------------------------------------------------------------------------ВИП101020м20
// Выбрать клиента для карточки заказа
        ButtonSelectClient_Buy_Sell.setOnAction(event -> {
            // Выбрать клиента для карточки заказа
            FillNameClient();
        });

        Button_Go_CardOrder.setOnAction(event -> {
            //Загрузка окна
            OpenNewWindow_Class.OpenNewWindow_ ("/BD_fxml/CardOrder.fxml");
        });

        //-------------------------------------------------------------------------------------------------------ВИП101020м20
        //Внесение данных из бд в таблицу
        ButtonSelectClientBD.setOnAction(event -> {
           // Инициализация таблицы адресатов сo столбцами.
            InitializationTable();

            // Выборка данных по условию если пусто-ChangSelect1, если нет ChangeSelekt2 :
            if(TextFildSelectClient.getText().trim().equals(""))
            { ChangSelect1 (); }
            else {
                ChangeSelekt2 ();
            }
        });


        //------------------------------------------------------------------------------------------------------------------

        // вставка-сохранение данных из формы-интерф. в БД
        ButtonInsertData.setOnAction(event -> {
            // Запись значений из текст-филдов в переменные.
            Record_TextField_Variable();

            //Вставить данные в БД в таблицу CardClient
            DbConnectDataBase.insert_CardClient(
            _Name_Client ,
            _Address_Client ,
            _Phone_Client ,
            _Mail_Client ,
            _DateRecording_Client ,
            _Additionally_Client
            );

            // Вставка значений из текст-филдов в таблицу формы.
            InitializationTable();
            //Insert_ValuesTextField_TableView();
            Insert_ValuesTextField_TableView2();

        });

        //------------------------------------------------------------------------------------------------------------------

        // Внесение выделенной строки таблицы в ячейки редактирования
        ButtonChangeLine.setOnAction(event -> {

            onEdit();

        });

       //-------------------------------------------------------------------------------------------------------

        // очистка формы интерфейса
        ButtonClearTextFild.setOnAction(event -> {
            ClearTextFild();
        });
        //-------------------------------------------------------------------------------------------------------

        //  очистка таблицы интерфейса
        ButtonClearTableView.setOnAction(event -> {
            ClearTableView();
        });
        //-------------------------------------------------------------------------------------------------------

        // Удалить строку из TableView
        ButtonDeleteLine.setOnAction(event -> {
            GetIdSel();
            Delete_Rows();
            delRow_TableView();
        });

        //-------------------------------------------------------------------------------------------------------

        // Обновить данные строки в БД
        ButtonSaveChangeLine.setOnAction(event -> {
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
        if (TableViewClient.getSelectionModel().getSelectedItem() != null) {
            Table_CardClient selectedPerson = TableViewClient.getSelectionModel().getSelectedItem();

            //заполнение ячеек выделенной строки новыми значениями
            selectedPerson.setColumn1( _Name_Client);
            selectedPerson.setColumn2(_Address_Client);
            selectedPerson.setColumn3(_Phone_Client);
            selectedPerson.setColumn4(_Mail_Client);
            selectedPerson.setColumn5(_DateRecording_Client);
            selectedPerson.setColumn6(_Additionally_Client);
            selectedPerson.setColumn7(_id_del);

            //Обновление таблицы формы
            TableViewClient.refresh();

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
                "UPDATE"+ "`bd_company`.`directory_client`"+ "SET "+
                        "Name_Client" +"="+"'"+_Name_Client+"'"+ "," +
                        "Address_Client" +"="+"'"+ _Address_Client +"'"+ "," +
                        "Phone_Client" +"="+"'"+_Phone_Client +"'"+ "," +
                        "Mail_Client" +"="+"'"+ _Mail_Client +"'"+ "," +
                        "DateRecording_Client" +"="+"'"+ _DateRecording_Client+"'"+ "," +
                        "Additionally_Client" +"="+"'"+ _Additionally_Client+"'"+
                        "WHERE"+ "`id_Client`"+"="+"'"+_id_del+"'";




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
        n =  TableViewClient.getSelectionModel().getSelectedIndex();
        TableViewClient.getItems().remove(n);
    }


    //Получение id таблицы БД выделенной строки таблицы для дальнейшего удаления:
    private void GetIdSel(){
        if (TableViewClient.getSelectionModel().getSelectedItem() != null) {
            Table_CardClient selectedPerson = TableViewClient.getSelectionModel().getSelectedItem();
            _id_del= selectedPerson.getColumn7();
        }

    }


    // Удаляем строку таблицы БД по id
    private void Delete_Rows() {

        //   String DelR =  "DELETE "+ " FROM " + Const.TABLE_CardClient + " WHERE " + Const.COLUMN_CardClient_Id_+ "= ?" ;
        String DelR =  "DELETE "+ " FROM " + "`bd_company`.`directory_client`"  +
                " WHERE " +  "id_Client"+ "= ?";

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

        TextFildName.clear();
        TextFildTell.clear();
        TextFildMail.clear();
        TextAreaAddress.clear();

        DataPicker_DateRecords.getEditor().clear();
        DataPicker_DateRecords.setValue(null);

        TextAreaKomment.clear();
        _id_Client = 0;
    }

//-------------------------------------------------------------------------------------------------------------------

    //Очищаем табле вив:
    public void ClearTableView(){
        TableViewClient.getItems().clear();
    }

//-------------------------------------------------------------------------------------------------------------------


    private void onEdit() {
        // Внесение выделенной строки таблицы в ячейки редактирования
        if (TableViewClient.getSelectionModel().getSelectedItem() != null) {
            Table_CardClient selectedPerson = TableViewClient.getSelectionModel().getSelectedItem();

            TextFildName.setText(selectedPerson.getColumn1());
            TextAreaAddress.setText(selectedPerson.getColumn2());
            TextFildTell.setText(selectedPerson.getColumn3());
            TextFildMail.setText(selectedPerson.getColumn4());

            // Преобразуем стринговую переменную в локал дат - для внесения DatePicker   TextField_CompanySeller
            String st_DateRecords =selectedPerson.getColumn5();
            LocalDate ld_DateRecords = LocalDate.parse(st_DateRecords);
            System.out.println("DataPicker_DateRecords"+ld_DateRecords);
            DataPicker_DateRecords.setValue(ld_DateRecords);

            TextAreaKomment.setText(selectedPerson.getColumn6());
            _id_Client= selectedPerson.getColumn7();
//            System.out.println("Comment"+selectedPerson.getColumn5());

        }
    }

//------------------------------------------------------------------------------------------------------------------


    // Вставка значений из текст-филдов в таблицу формы.
    private void Insert_ValuesTextField_TableView(){

        Table_Data.add(new Table_CardClient(
                _Name_Client,
                _Address_Client,
                _Phone_Client,
                _Mail_Client,
                _DateRecording_Client,
                _Additionally_Client,
                null)
        );
        TableViewClient.setItems(Table_Data);

    }

    // или так

    //Вставка значений из текст-филдов в таблицу формы.
    private void Insert_ValuesTextField_TableView2(){
        //предварительная очистка обсервайбл лист
       // Table_Data.clear();

        String Select2 = "SELECT "+
                "Name_Client" + "," +
                "Address_Client" + "," +
                "Phone_Client" + "," +
                "Mail_Client" + "," +
                //"DateRecording_Client" + "," +
                "("+"DATE"+"("+"DateRecording_Client"+")" +")"+ " AS " +"_DateRecording_"+"," +
                "Additionally_Client" + "," +
                "id_Client" +
                " FROM " +
                "bd_company.directory_client" +
                " WHERE " + "Name_Client" +
                " LIKE " +"'"+TextFildName.getText()+"%'";

        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select2)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_CardClient(
                        ResSet.getString("Name_Client"),
                        ResSet.getString("Address_Client"),
                        ResSet.getString("Phone_Client"),
                        ResSet.getString("Mail_Client"),
                        ResSet.getString("_DateRecording_"),
                        ResSet.getString("Additionally_Client"),
                        ResSet.getInt("id_Client"))
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableViewClient.setItems(Table_Data);
    }

    //------------------------------------------------------------------------------------------------------------------

    // Запись значений из текст-филдов в переменные.
    private void Record_TextField_Variable() {

        _Name_Client = TextFildName.getText();
        _Address_Client = TextAreaAddress.getText();
        _Phone_Client = TextFildTell.getText();
        _Mail_Client = TextFildMail.getText();

        // Преобразуем DatePicker В стринговую переменную
        LocalDate ld_DateRecords1 = DataPicker_DateRecords.getValue();
        _DateRecording_Client = String.valueOf(ld_DateRecords1);

        _Additionally_Client = TextAreaKomment.getText();

    }

    //------------------------------------------------------------------------------------------------------------------

    //Часть кода ( Внесение данных из бд в таблицу)
    public void ChangSelect1 () {
        //предварительная очистка обсервайбл лист
        Table_Data.clear();

        String Select1 = "SELECT " +
                "Name_Client" + "," +
                "Address_Client" + "," +
                "Phone_Client" + "," +
                "Mail_Client" + "," +
               // "DateRecording_Client" + "," +
                "("+"DATE"+"("+"DateRecording_Client"+")" +")"+ " AS " +"_DateRecording_"+"," +
                "Additionally_Client" + "," +
                "id_Client" +
                " FROM " +
                "directory_client";
        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select1)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_CardClient(
                        ResSet.getString("Name_Client"),
                        ResSet.getString("Address_Client"),
                        ResSet.getString("Phone_Client"),
                        ResSet.getString("Mail_Client"),
                        ResSet.getString("_DateRecording_"),
                        ResSet.getString("Additionally_Client"),
                        ResSet.getInt("id_Client"))
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableViewClient.setItems(Table_Data);

    }

    //Внесение данных из бд в таблицу по значению в текстфилде
    public void ChangeSelekt2(){
        //предварительная очистка обсервайбл лист
        Table_Data.clear();

        String Select2 = "SELECT "+
                "Name_Client" + "," +
                "Address_Client" + "," +
                "Phone_Client" + "," +
                "Mail_Client" + "," +
                //"DateRecording_Client" + "," +
                "("+"DATE"+"("+"DateRecording_Client"+")" +")"+ " AS " +"_DateRecording_"+"," +
                "Additionally_Client" + "," +
                "id_Client" +
                " FROM " +
                "bd_company.directory_client" +
                " WHERE " + "Name_Client" +
        " LIKE " +"'"+TextFildSelectClient.getText()+"%'";

        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select2)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_CardClient(
                        ResSet.getString("Name_Client"),
                        ResSet.getString("Address_Client"),
                        ResSet.getString("Phone_Client"),
                        ResSet.getString("Mail_Client"),
                        ResSet.getString("_DateRecording_"),
                        ResSet.getString("Additionally_Client"),
                        ResSet.getInt("id_Client"))
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableViewClient.setItems(Table_Data);
    }
//------------------------------------------------------------------------------------------------------------------
private void FillNameClient() {
    // Внесение выделенной строки таблицы в данные по клиенту (name , id)
    if (TableViewClient.getSelectionModel().getSelectedItem() != null) {
        Table_CardClient selectedPerson = TableViewClient.getSelectionModel().getSelectedItem();

        Const.CardClient_NameClient = selectedPerson.getColumn1();
        Const.Id_NameClient = selectedPerson.getColumn7();
    }
}

//------------------------------------------------------------------------------------------------------------------

    // Инициализация таблицы адресатов сo столбцами.
    private void InitializationTable() {
        // Инициализация таблицы адресатов сo столбцами.
        Column_NameClient.setCellValueFactory(new PropertyValueFactory<Table_CardClient, String>("Column1"));
        Column_Address.setCellValueFactory(new PropertyValueFactory<Table_CardClient, String>("Column2"));
        Column_Tell.setCellValueFactory(new PropertyValueFactory<Table_CardClient, String>("Column3"));
        Column_Mail.setCellValueFactory(new PropertyValueFactory<Table_CardClient, String>("Column4"));
        Column_DataRecords.setCellValueFactory(new PropertyValueFactory<Table_CardClient, String>("Column5"));
        Column_Comment.setCellValueFactory(new PropertyValueFactory<Table_CardClient, String>("Column6"));
        Id.setCellValueFactory(new PropertyValueFactory<Table_CardClient, Integer>("Column7"));

        //
    }

//------------------------------------------------------------------------------------------------------------------


}

