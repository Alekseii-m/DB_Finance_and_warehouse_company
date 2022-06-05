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
import ObservListTable.Table_CardCounterparty;
import ObservListTable.Table_CardOrder;
import ObservListTable.Table_CardService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class Cont_CardCounterparty {

    private final ObservableList<Table_CardCounterparty> Table_Data = FXCollections.observableArrayList();

    //переменные для заполнения БД----------------------------------------------->
    int _id_Counterparty =0;
    String _Name_Counterparty ="";
    String _Company_Counterparty ="";
    String _Address_Counterparty ="";
    String _Phone_Counterparty ="";
    String _Mail_Counterparty ="";
    String _DateRecording_Counterparty ="";
    String _Specialization ="";
    String _Additionally_Counterparty ="";
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
    private TableView<Table_CardCounterparty> TableViewContragent;

    @FXML
    private TableColumn<Table_CardCounterparty, String> Column_Name;

    @FXML
    private TableColumn<Table_CardCounterparty, String> Column_Specialization;

    @FXML
    private TableColumn<Table_CardCounterparty, String> Column_CompanyContragent;

    @FXML
    private TableColumn<Table_CardCounterparty, String> Column_Tell;

    @FXML
    private TableColumn<Table_CardCounterparty, String> Column_Mail;

    @FXML
    private TableColumn<Table_CardCounterparty, String> Column_Address;

    @FXML
    private TableColumn<Table_CardCounterparty, String> Column_DateRecords;

    @FXML
    private TableColumn<Table_CardCounterparty, String> Column_Comment;

    @FXML
    private TableColumn<Table_CardCounterparty, Integer> Column_Id;

    @FXML
    private Button ButtonSelectData;

    @FXML
    private TextField TextFildSelectContragent;

    @FXML
    private Button ButtonClearTextFild;

    @FXML
    private Button ButtonClearTableView;

    @FXML
    private TextArea TextAreaKomment;

    @FXML
    private Button ButtonSaveChangeLine;

    @FXML
    private Button ButtonSelectContragentService;

    @FXML
    private Button ButtonSelectContragentService1;

    @FXML
    private Button ButtonSelectContragentService2;

    @FXML
    private TextArea TextAreaAddress;

    @FXML
    private DatePicker DataPicker_DateRecords;

    @FXML
    private TextField TextFildCompany;

    @FXML
    private TextField TextFildSpecialization;

    @FXML
    private Button Button_SelectSimilarName;

    @FXML
    private Button Button_InsertSpecialization;

    @FXML
    private Button Button_SelectCompany;

    @FXML
    private Button Button_InsertCompany;




    @FXML
    void initialize() {

        // Скрыть столбец id таблицы
        Column_Id.setVisible(false);

//------------------------------------------------------------------------------------------------------------------

        Button_SelectSimilarName.setOnAction(event -> {
            System.out.println("Button_SelectSimilarName");
            //Загрузка окна
            OpenNewWindow_Class.OpenNewWindow_ ("/BD_fxml/AddSelectSpecialization.fxml");
        });

        Button_InsertSpecialization.setOnAction(event -> {
            //внести в текст-филд название специализации
            TextFildSpecialization.setText(Const.CardCounterparty_Specialization);
        });

        //------------------------------------------------------------------------------------------------------------------

        Button_SelectCompany.setOnAction(event -> {
           // System.out.println("Button_SelectSimilarName");
            //Загрузка окна
            OpenNewWindow_Class.OpenNewWindow_ ("/BD_fxml/AddSelectCompany.fxml");
        });

        Button_InsertCompany.setOnAction(event -> {
            //внести в текст-филд название специализации
            TextFildCompany.setText(Const.CardCounterparty_Company_Counterparty);
        });

//------------------------------------------------------------------------------------------------------------------

        ButtonSelectContragentService1.setOnAction(event -> {
            // Внесение выделенной строки таблицы в данные по контрагенту предл. (name , id)
            FillNameCounterparty1();
        });

        ButtonSelectContragentService2.setOnAction(event -> {
            // Внесение выделенной строки таблицы в данные по контрагенту покуп. (name , id)
            FillNameCounterparty2();
        });








//------------------------------------------------------------------------------------------------------------------

        //Внесение данных из бд в таблицу
        ButtonSelectData.setOnAction(event -> {
            // Инициализация таблицы адресатов сo столбцами.
            InitializationTable();

            DbConnectDataBase dbConnectionCardClient = new DbConnectDataBase();

            // Выборка данных по условию если пусто-ChangSelect1, если нет ChangeSelekt2 :
            if(TextFildSelectContragent.getText().trim().equals(""))
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

            //Вставить данные в БД в таблицу CardCounterparty
            DbConnectDataBase.insert_CardCounterparty(
            _Name_Counterparty ,
            _Company_Counterparty ,
            _Address_Counterparty ,
            _Phone_Counterparty ,
             _Mail_Counterparty ,
            _DateRecording_Counterparty ,
            _Specialization ,
            _Additionally_Counterparty
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
        if (TableViewContragent.getSelectionModel().getSelectedItem() != null) {
            Table_CardCounterparty selectedPerson = TableViewContragent.getSelectionModel().getSelectedItem();

            //заполнение ячеек выделенной строки новыми значениями
            selectedPerson.setColumn1(_Name_Counterparty);
            selectedPerson.setColumn2(_Specialization );
            selectedPerson.setColumn3(_Company_Counterparty);
            selectedPerson.setColumn4(_Phone_Counterparty);
            selectedPerson.setColumn5(_Mail_Counterparty);
            selectedPerson.setColumn6(_Address_Counterparty);
            selectedPerson.setColumn7(_DateRecording_Counterparty);
            selectedPerson.setColumn8(_Additionally_Counterparty);
            selectedPerson.setColumn9(_id_del);

            //Обновление таблицы формы
            TableViewContragent.refresh();

            // System.out.println("Comment5 -"+selectedPerson.getColumn5());
        }
    }
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
                "UPDATE"+ "`bd_company`.`directory_counterparty`"+ "SET "+
                        "Name_Counterparty" +"="+"'"+_Name_Counterparty+"'"+ "," +
                        "Company_Counterparty" +"="+"'"+ _Company_Counterparty+"'"+ "," +
                        "Address_Counterparty" +"="+"'"+_Address_Counterparty +"'"+ "," +
                        "Phone_Counterparty" +"="+"'"+_Phone_Counterparty +"'"+ "," +
                        "Mail_Counterparty" +"="+"'"+ _Mail_Counterparty+"'"+ "," +
                        "DateRecording_Counterparty" +"="+"'"+_DateRecording_Counterparty +"'"+ "," +
                        "Specialization" +"="+"'"+_Specialization +"'"+ "," +
                        "Additionally_Counterparty" +"="+"'"+ _Additionally_Counterparty+"'"+
                        "WHERE"+ "`id_Counterparty`"+"="+"'"+_id_del+"'";

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
        n =  TableViewContragent.getSelectionModel().getSelectedIndex();
        TableViewContragent.getItems().remove(n);
    }


    //Получение id таблицы БД выделенной строки таблицы для дальнейшего удаления:
    private void GetIdSel(){
        if (TableViewContragent.getSelectionModel().getSelectedItem() != null) {
            Table_CardCounterparty selectedPerson = TableViewContragent.getSelectionModel().getSelectedItem();
            _id_del= selectedPerson.getColumn9();
        }

    }


    // Удаляем строку таблицы БД по id
    private void Delete_Rows() {

        //   String DelR =  "DELETE "+ " FROM " + Const.TABLE_CardClient + " WHERE " + Const.COLUMN_CardClient_Id_+ "= ?" ;
        String DelR =  "DELETE "+ " FROM " + "`bd_company`.`directory_counterparty`"  +
                " WHERE " +  "id_Counterparty"+ "= ?";

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
        TextFildSpecialization.clear();
        TextFildCompany.clear();
        TextFildTell.clear();
        TextFildMail.clear();
        TextAreaAddress.clear();

        DataPicker_DateRecords.getEditor().clear();
        DataPicker_DateRecords.setValue(null);

        TextAreaKomment.clear();
        _id_Counterparty = 0;
    }

//-------------------------------------------------------------------------------------------------------------------

    //Очищаем табле вив:
    public void ClearTableView(){
        TableViewContragent.getItems().clear();
    }

//-------------------------------------------------------------------------------------------------------------------



    private void onEdit() {
        // Внесение выделенной строки таблицы в ячейки редактирования
        if (TableViewContragent.getSelectionModel().getSelectedItem() != null) {
            Table_CardCounterparty selectedPerson = TableViewContragent.getSelectionModel().getSelectedItem();

            TextFildName.setText(selectedPerson.getColumn1());
            TextFildSpecialization.setText(selectedPerson.getColumn2());
            TextFildCompany.setText(selectedPerson.getColumn3());
            TextFildTell.setText(selectedPerson.getColumn4());
            TextFildMail.setText(selectedPerson.getColumn5());
            TextAreaAddress.setText(selectedPerson.getColumn6());

            // Преобразуем стринговую переменную в локал дат - для внесения DatePicker   TextField_CompanySeller
            String st_DateRecords =selectedPerson.getColumn7();
            LocalDate ld_DateRecords = LocalDate.parse(st_DateRecords);
            System.out.println("DataPicker_DateRecords"+ld_DateRecords);
            DataPicker_DateRecords.setValue(ld_DateRecords);

            TextAreaKomment.setText(selectedPerson.getColumn8());
            _id_Counterparty = selectedPerson.getColumn9();
//            System.out.println("Comment"+selectedPerson.getColumn5());

        }
    }

//------------------------------------------------------------------------------------------------------------------


    // Вставка значений из текст-филдов в таблицу формы.
    private void Insert_ValuesTextField_TableView(){

        Table_Data.add(new Table_CardCounterparty(
                _Name_Counterparty,
                _Specialization,
                _Company_Counterparty,
                _Phone_Counterparty,
                _Mail_Counterparty,
                _Address_Counterparty,
                _DateRecording_Counterparty,
                _Additionally_Counterparty,
                null)
        );
        TableViewContragent.setItems(Table_Data);
    }

    // или
    // Вставка значений из текст-филдов в таблицу формы. по значению в текстфилде
    public void Insert_ValuesTextField_TableView2(){
        // предварительная очистка обсервайбл лист
        // Table_Data.clear();

        String Select2 = "SELECT " +
                "Name_Counterparty" + "," +
                "Company_Counterparty" + "," +
                "Address_Counterparty" + "," +
                "Phone_Counterparty" + "," +
                "Mail_Counterparty" + "," +
                //"DateRecording_Counterparty" + "," +
                "("+"DATE"+"("+"DateRecording_Counterparty"+")" +")"+ " AS " +"_DateRecording_"+"," +
                "Specialization" + "," +
                "Additionally_Counterparty" + ","+
                "id_Counterparty" +
                " FROM " +
                "directory_counterparty" +
                " WHERE " + "Name_Counterparty" +
                " LIKE " +"'"+TextFildName.getText()+"%'";

        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select2)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_CardCounterparty(
                        ResSet.getString("Name_Counterparty"),
                        ResSet.getString("Specialization"),
                        ResSet.getString("Company_Counterparty"),
                        ResSet.getString("Phone_Counterparty"),
                        ResSet.getString("Mail_Counterparty"),
                        ResSet.getString("Address_Counterparty"),
                        ResSet.getString("_DateRecording_"),
                        ResSet.getString("Additionally_Counterparty"),
                        ResSet.getInt("id_Counterparty"))

                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableViewContragent.setItems(Table_Data);
    }






    //------------------------------------------------------------------------------------------------------------------


    // Запись значений из текст-филдов в переменные.
    private void Record_TextField_Variable() {

        _Name_Counterparty = TextFildName.getText();
        _Company_Counterparty = TextFildCompany.getText();
        _Address_Counterparty = TextAreaAddress.getText();
        _Phone_Counterparty = TextFildTell.getText();
        _Mail_Counterparty = TextFildMail.getText();

        // Преобразуем DatePicker В стринговую переменную
        LocalDate ld_DateRecords1 = DataPicker_DateRecords.getValue();
        _DateRecording_Counterparty = String.valueOf(ld_DateRecords1);

        _Specialization = TextFildSpecialization.getText();
        _Additionally_Counterparty = TextAreaKomment.getText();

    }


    //------------------------------------------------------------------------------------------------------------------


    // Внесение выделенной строки таблицы в данные по контрагенту предлагающему (name , id)
    private void FillNameCounterparty1() {

        if (TableViewContragent.getSelectionModel().getSelectedItem() != null) {
            Table_CardCounterparty selectedPerson1 = TableViewContragent.getSelectionModel().getSelectedItem();

            Const.CardCounterparty_NameCounterparty1 = selectedPerson1.getColumn1();
            Const.Id_NameCounterparty1 = selectedPerson1.getColumn9();
        }
    }


    // Внесение выделенной строки таблицы в данные по контрагенту покупающему (name , id)
    private void FillNameCounterparty2() {

        if (TableViewContragent.getSelectionModel().getSelectedItem() != null) {
            Table_CardCounterparty selectedPerson = TableViewContragent.getSelectionModel().getSelectedItem();

            Const.CardCounterparty_NameCounterparty2 = selectedPerson.getColumn1();
            Const.Id_NameCounterparty2 = selectedPerson.getColumn9();
        }
    }


    //------------------------------------------------------------------------------------------------------------------

    //Часть кода ( Внесение данных из бд в таблицу)
    public void ChangSelect1 () {
        //предварительная очистка обсервайбл лист
        Table_Data.clear();
        String Select1 = "SELECT " +
                "Name_Counterparty" + "," +
                "Company_Counterparty" + "," +
                "Address_Counterparty" + "," +
                "Phone_Counterparty" + "," +
                "Mail_Counterparty" + "," +
                //"DateRecording_Counterparty" + "," +
                "("+"DATE"+"("+"DateRecording_Counterparty"+")" +")"+ " AS " +"_DateRecording_"+"," +
                "Specialization" + "," +
                "Additionally_Counterparty" + ","+
                "id_Counterparty" +
                " FROM " +
                "directory_counterparty";
        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select1)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_CardCounterparty(
                        ResSet.getString("Name_Counterparty"),
                        ResSet.getString("Specialization"),
                        ResSet.getString("Company_Counterparty"),
                        ResSet.getString("Phone_Counterparty"),
                        ResSet.getString("Mail_Counterparty"),
                        ResSet.getString("Address_Counterparty"),
                        ResSet.getString("_DateRecording_"),
                        ResSet.getString("Additionally_Counterparty"),
                        ResSet.getInt("id_Counterparty"))
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableViewContragent.setItems(Table_Data);

    }

    //Внесение данных из бд в таблицу по значению в текстфилде
    public void ChangeSelekt2(){
        //предварительная очистка обсервайбл лист
        Table_Data.clear();

        String Select2 = "SELECT " +
                "Name_Counterparty" + "," +
                "Company_Counterparty" + "," +
                "Address_Counterparty" + "," +
                "Phone_Counterparty" + "," +
                "Mail_Counterparty" + "," +
                //"DateRecording_Counterparty" + "," +
                "("+"DATE"+"("+"DateRecording_Counterparty"+")" +")"+ " AS " +"_DateRecording_"+"," +
                "Specialization" + "," +
                "Additionally_Counterparty" + ","+
                "id_Counterparty" +
                " FROM " +
                "directory_counterparty" +
                " WHERE " + "Name_Counterparty" +
                " LIKE " +"'"+TextFildSelectContragent.getText()+"%'";

        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select2)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_CardCounterparty(
                        ResSet.getString("Name_Counterparty"),
                        ResSet.getString("Company_Counterparty"),
                        ResSet.getString("Address_Counterparty"),
                        ResSet.getString("Phone_Counterparty"),
                        ResSet.getString("Mail_Counterparty"),
                        ResSet.getString("_DateRecording_"),
                        ResSet.getString("Specialization"),
                        ResSet.getString("Additionally_Counterparty"),
                        ResSet.getInt("id_Counterparty"))
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableViewContragent.setItems(Table_Data);
    }
//------------------------------------------------------------------------------------------------------------------


    // Инициализация таблицы адресатов сo столбцами.
    private void InitializationTable() {

        // Инициализация таблицы адресатов сo столбцами.
        Column_Name.setCellValueFactory(new PropertyValueFactory<Table_CardCounterparty, String>("Column1"));
        Column_Specialization.setCellValueFactory(new PropertyValueFactory<Table_CardCounterparty, String>("Column2"));
        Column_CompanyContragent.setCellValueFactory(new PropertyValueFactory<Table_CardCounterparty, String>("Column3"));
        Column_Tell.setCellValueFactory(new PropertyValueFactory<Table_CardCounterparty, String>("Column4"));
        Column_Mail.setCellValueFactory(new PropertyValueFactory<Table_CardCounterparty, String>("Column5"));
        Column_Address.setCellValueFactory(new PropertyValueFactory<Table_CardCounterparty, String>("Column6"));
        Column_DateRecords.setCellValueFactory(new PropertyValueFactory<Table_CardCounterparty, String>("Column7"));
        Column_Comment.setCellValueFactory(new PropertyValueFactory<Table_CardCounterparty, String>("Column8"));
        Column_Id.setCellValueFactory(new PropertyValueFactory<Table_CardCounterparty, Integer>("Column9"));

    }

//------------------------------------------------------------------------------------------------------------------


}
