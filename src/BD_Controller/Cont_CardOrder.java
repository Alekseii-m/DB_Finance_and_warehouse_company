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
import ObservListTable.Table_CardCounterparty;
import ObservListTable.Table_CardOrder;
import ObservListTable.Table_CardProduct;
import ObservListTable.Table_CardService;
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

import javax.swing.*;

public class Cont_CardOrder {

    private final ObservableList<Table_CardOrder> Table_Data = FXCollections.observableArrayList();

    //переменные для заполнения БД----------------------------------------------->
    int _id_Orders =0;
    String _id_NameClient_Orders = ""; // имя текстовое по факту
    String _NumberOrder_Orders ="";
    int _AmountPrepaymentOrder_Orders =0;
    int _AmountlastpaymentOrder_Orders =0;
    String _DateOrderAcception_Orders ="";
    String _DateDeliveryOrder_Orders ="";
    String _DateCompletionOrder_Orders ="";
    String _DatePrepaymentOrder_Orders ="";
    String _DatelastpaymentOrder_Orders ="";
    String _Coment_Orders ="";
    int  _TotalPaymentCounterparty = 0;
    int  _TotalAddedCost = 0;
    int  _TotalCost = 0;
    //переменные  для заполнения БД-----------------------------------------------<
    int _id_del =0;// для удаления строки в таблице и БД
    int n =0;    // удаление выделенной строки в таблице


    @FXML
    private AnchorPane AnchorPane_Order;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Label_1;

    @FXML
    private TextField TextFildOrderNumber;

    @FXML
    private DatePicker DataPicker_DateAcceptanceOrder;

    @FXML
    private Button Button_FormClear;

    @FXML
    private Button Button_EditLine;

    @FXML
    private Button Button_SaveDataForm;

    @FXML
    private Button Button_FillOrder_Service_Product;

    @FXML
    private TextArea TextAreaComment;

    @FXML
    private TextField TextFild_СlientsСhoice;

    @FXML
    private Button Button_EnterClient;

    @FXML
    private TableView<Table_CardOrder> TableView_Order;

    @FXML
    private TableColumn<Table_CardOrder, String> ColumnOrderNumber;

    @FXML
    private TableColumn<Table_CardOrder, String> Column_DateAcceptanceOrder;

    @FXML
    private TableColumn<Table_CardOrder, String> Column_DateOrderDelivery;

    @FXML
    private TableColumn<Table_CardOrder, String> Column_DateOrderCompletion;

    @FXML
    private TableColumn<Table_CardOrder, String> Column_Client;

    @FXML
    private TableColumn<Table_CardOrder, Integer> Column_PrepaymentAmount;

    @FXML
    private TableColumn<Table_CardOrder, String> Column_DatePrepaymentAccept;

    @FXML
    private TableColumn<Table_CardOrder, Integer> Column_PostpaidAmount;

    @FXML
    private TableColumn<Table_CardOrder, String> Column_DatePostpaidAccept;

    @FXML
    private TableColumn<Table_CardOrder, String> Column_Comment;

    @FXML
    private TableColumn<Table_CardOrder, Integer> Column_Id;

    @FXML
    private TableColumn<Table_CardOrder, Integer> Column_TotalPaymentCounterparty;

    @FXML
    private TableColumn<Table_CardOrder, Integer> Column_TotalAddedCost;

    @FXML
    private TableColumn<Table_CardOrder, Integer> Column_OrderCost;

    @FXML
    private Button Button_SelectOrderYear;

    @FXML
    private TextField TextFild_OrderSelectYear;

    @FXML
    private Button Button_ClearTableView;

    @FXML
    private Button Button_SaveEditLine;

    @FXML
    private Button Button_DeleteDataLine;

    @FXML
    private Button Button_InsertDataClient;

    @FXML
    private Button Button_SelectOrderClient;

    @FXML
    private TextField TextFild_OrderSelectClient;

    @FXML
    private Button Button_SelectOrderNumber;

    @FXML
    private TextField TextFild_OrderSelectNumber;

    @FXML
    private DatePicker DataPicker_DateOrderCompletion;

    @FXML
    private DatePicker DataPicker_DateOrderDelivery;

    @FXML
    private TextField Text_PrepaymentAmount;

    @FXML
    private DatePicker DataPicker_Date_PrepaymentAmount;

    @FXML
    private TextField Text_PostPaymentAmount;

    @FXML
    private DatePicker DataPicker_Date_PostpaymentAmount;

    @FXML
    private TextField TextFild_OrderCost;

    @FXML
    private Button Button_Сalculate_OrderCost;

    //-----------------------------------------------------------------------------------------
    @FXML
    private Button Button_Go_Bay_Sell_Servis;
    @FXML
    private Button Button_EnterCalcData;
    @FXML
    private TextField TextFild_TotalPaymentCounterparty;
    @FXML
    private TextField TextFild_TotalAddedCost;
    @FXML
    private TextField TextFild_TotalCost;
    //-----------------------------------------------------------------------------------------



    @FXML
    void initialize() {

        // Скрыть столбец id таблицы
        Column_Id.setVisible(false);

        Button_InsertDataClient.setOnAction(event -> {
            //Загрузка окна
            OpenNewWindow_Class.OpenNewWindow_ ("/BD_fxml/CardClient.fxml");
        });


        //-------------------------------------------------------------------------------------------------------

        //Внесение данных из бд в таблицу по году
        Button_SelectOrderYear.setOnAction(event -> {
            // Инициализация таблицы адресатов сo столбцами.
            InitializationTable();

            // Выборка данных по условию если пусто-ChangSelect1, если нет ChangeSelekt2 :
            if(TextFild_OrderSelectYear.getText().trim().equals(""))
            {  }  //SelectYear1 ()
            else {
                SelectOrderYear_Select2 ();
            }
        });

        //-------------------------------------------------------------------------------------------------------

        //Внесение данных из бд в таблицу по клиенту
        Button_SelectOrderClient.setOnAction(event -> {
            // Инициализация таблицы адресатов сo столбцами.
            InitializationTable();

            // Выборка данных по условию если пусто-ChangSelect1, если нет ChangeSelekt2 :
            if(TextFild_OrderSelectClient.getText().trim().equals(""))
            {  }  //SelectClient1 ()
            else {
                SelectOrderClient_Select2 ();
            }
        });

        //-------------------------------------------------------------------------------------------------------

        //Внесение данных из бд в таблицу по номеру заказа
        Button_SelectOrderNumber.setOnAction(event -> {
            // Инициализация таблицы адресатов сo столбцами.
            InitializationTable();

            // Выборка данных по условию если пусто-ChangSelect1, если нет ChangeSelekt2 :
            if(TextFild_OrderSelectClient.getText().trim().equals(""))
            {  }  //SelectOrderNumber_Select1 ()
            else {
                SelectOrderNumber_Select2 ();
            }
        });

        //-------------------------------------------------------------------------------------------------------

        //Внесение 
        Button_EnterClient.setOnAction(event -> {

            //
            TextFild_СlientsСhoice.setText(Const.CardClient_NameClient);

        });

        //-------------------------------------------------------------------------------------------------------

        Button_Go_Bay_Sell_Servis.setOnAction(event -> {

            //Загрузка окна
            OpenNewWindow_Class.OpenNewWindow_ ("/BD_fxml/BuySell_Service.fxml");
        });

        //-------------------------------------------------------------------------------------------------------

        //Копировать номер заказа и id заказа
        Button_FillOrder_Service_Product.setOnAction(event -> {
                    // Внесение выделенной строки таблицы в данные по заказу (name , id)
                    if (TableView_Order.getSelectionModel().getSelectedItem() != null) {
                        Table_CardOrder selectedPerson = TableView_Order.getSelectionModel().getSelectedItem();

                        Const.CardOrder_OrderNumber = selectedPerson.getColumn1();
                        Const.Id_OrderNumber = selectedPerson.getColumn11();

                    }
        });

        //-------------------------------------------------------------------------------------------------------

       // вставка-сохранение из формы-интерф. в БД
        Button_SaveDataForm.setOnAction(event -> {

            // Запись значений из текст-филдов в переменные.
            Record_TextField_Variable();

            //Вставить данные в БД в таблицу CardOrder
            DbConnectDataBase.insert_CardOrder(
             _id_NameClient_Orders ,
             _NumberOrder_Orders ,
             _AmountPrepaymentOrder_Orders ,
             _AmountlastpaymentOrder_Orders ,
             _DateOrderAcception_Orders ,
             _DateDeliveryOrder_Orders ,
             _DateCompletionOrder_Orders ,
             _DatePrepaymentOrder_Orders ,
             _DatelastpaymentOrder_Orders ,
             _Coment_Orders,
            _TotalPaymentCounterparty ,
            _TotalAddedCost ,
            _TotalCost

            );

            // Вставка значений из текст-филдов в таблицу формы.
            InitializationTable();
           // Insert_ValuesTextField_TableView();
            Insert_ValuesTextField_TableView2();
        });


        //------------------------------------------------------------------------------------------------------------------

        //Внесение в текст-фильд
        Button_EnterCalcData.setOnAction(event -> {
            //
            TextFild_TotalPaymentCounterparty.setText(Const._TotalPaymentCounterparty_);
            TextFild_TotalAddedCost.setText(Const._TotalAddedCost_);
            TextFild_OrderCost.setText(Const._TotalCost_);
        });

        //------------------------------------------------------------------------------------------------------------------

        // расчет суммы постоплаты
        Button_Сalculate_OrderCost.setOnAction(event -> {
            //TextFild_OrderCost Text_PrepaymentAmount
            int _OrderCost =Integer.valueOf(TextFild_OrderCost.getText());
            int _PrepaymentAmount =Integer.valueOf(Text_PrepaymentAmount.getText());
            int difference =0;
            difference=_OrderCost-_PrepaymentAmount;
            Text_PostPaymentAmount.setText(String.valueOf(difference));

        });

        //------------------------------------------------------------------------------------------------------------------

        // Внесение выделенной строки таблицы в ячейки редактирования
        Button_EditLine.setOnAction(event -> {
            onEdit();
        });

//-------------------------------------------------------------------------------------------------------0

        // очистка формы интерфейса
        Button_FormClear.setOnAction(event -> {
            ClearTextFild();
        });

        //-------------------------------------------------------------------------------------------------------

        //  очистка таблицы интерфейса
        Button_ClearTableView.setOnAction(event -> {
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

        // Обновить данные строки в БД
        Button_SaveEditLine.setOnAction(event -> {
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
        if (TableView_Order.getSelectionModel().getSelectedItem() != null) {
            Table_CardOrder selectedPerson = TableView_Order.getSelectionModel().getSelectedItem();

            //заполнение ячеек выделенной строки новыми значениями
            selectedPerson.setColumn5(_id_NameClient_Orders );
            selectedPerson.setColumn1(_NumberOrder_Orders);
            selectedPerson.setColumn6(_AmountPrepaymentOrder_Orders);
            selectedPerson.setColumn8(_AmountlastpaymentOrder_Orders);
            selectedPerson.setColumn2(_DateOrderAcception_Orders);
            selectedPerson.setColumn3(_DateDeliveryOrder_Orders);
            selectedPerson.setColumn4(_DateCompletionOrder_Orders);
            selectedPerson.setColumn7(_DatePrepaymentOrder_Orders);
            selectedPerson.setColumn9(_DatelastpaymentOrder_Orders);
            selectedPerson.setColumn10(_Coment_Orders);
            selectedPerson.setColumn12(_TotalPaymentCounterparty);
            selectedPerson.setColumn13(_TotalAddedCost);
            selectedPerson.setColumn14(_TotalCost);
            selectedPerson.setColumn11(_id_del);


            //Обновление таблицы формы
            TableView_Order.refresh();

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
                "UPDATE"+ "`bd_company`.`directory_orders`"+ "SET "+

                        "id_NameClient_Orders" +"="+ "(" + "SELECT " + "id_Client" + " FROM " + "Directory_Client " +
                         "WHERE " + "Name_Client" + "=" + "'" + _id_NameClient_Orders + "'" + ")"+ "," +

                        "NumberOrder_Orders" +"="+"'"+_NumberOrder_Orders+"'"+ "," +
                        "AmountPrepaymentOrder_Orders" +"="+"'"+ _AmountPrepaymentOrder_Orders+"'"+ "," +
                        "AmountlastpaymentOrder_Orders" +"="+"'"+_AmountlastpaymentOrder_Orders +"'"+ "," +
                        "DateOrderAcception_Orders" +"="+"'"+_DateOrderAcception_Orders +"'"+ "," +
                        "DateDeliveryOrder_Orders" +"="+"'"+ _DateDeliveryOrder_Orders+"'"+ "," +
                        "DateCompletionOrder_Orders" +"="+"'"+_DateCompletionOrder_Orders +"'"+ "," +
                        "DatePrepaymentOrder_Orders" +"="+"'"+_DatePrepaymentOrder_Orders +"'"+ "," +
                        "DatelastpaymentOrder_Orders" +"="+"'"+ _DatelastpaymentOrder_Orders+"'"+ "," +
                        "Coment_Orders" +"="+"'"+ _Coment_Orders+"'"+ "," +
                        "TotalPaymentCounterparty_Orders" +"="+"'"+ _TotalPaymentCounterparty+"'"+ "," +
                        "TotalAddedCost_Orders" +"="+"'"+ _TotalAddedCost+"'"+ "," +
                        "TotalCostOrder_Orders" +"="+"'"+ _TotalCost+"'"+
                        "WHERE"+ "`id_Orders`"+"="+"'"+_id_del+"'";

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
        n =  TableView_Order.getSelectionModel().getSelectedIndex();
        TableView_Order.getItems().remove(n);
    }


    //Получение id таблицы БД выделенной строки таблицы для дальнейшего удаления:
    private void GetIdSel(){
        if (TableView_Order.getSelectionModel().getSelectedItem() != null) {
            Table_CardOrder selectedPerson = TableView_Order.getSelectionModel().getSelectedItem();
            _id_del= selectedPerson.getColumn11();
        }

    }


    // Удаляем строку таблицы БД по id
    private void Delete_Rows() {

        //   String DelR =  "DELETE "+ " FROM " + Const.TABLE_CardClient + " WHERE " + Const.COLUMN_CardClient_Id_+ "= ?" ;
        String DelR =  "DELETE "+ " FROM " + "`bd_company`.`directory_orders`"  +
                " WHERE " +  "id_Orders"+ "= ?";

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

        TextFildOrderNumber.clear();

        DataPicker_DateAcceptanceOrder.getEditor().clear();
        DataPicker_DateAcceptanceOrder.setValue(null);

        DataPicker_DateOrderDelivery.getEditor().clear();
        DataPicker_DateOrderDelivery.setValue(null);

        DataPicker_DateOrderCompletion.getEditor().clear();
        DataPicker_DateOrderCompletion.setValue(null);

        TextFild_СlientsСhoice.clear();
        Text_PrepaymentAmount.clear();

        DataPicker_Date_PrepaymentAmount.getEditor().clear();
        DataPicker_Date_PrepaymentAmount.setValue(null);

        Text_PostPaymentAmount.clear();

        DataPicker_Date_PostpaymentAmount.getEditor().clear();
        DataPicker_Date_PostpaymentAmount.setValue(null);

        TextAreaComment.clear();
        _id_Orders = 0;
        TextFild_TotalPaymentCounterparty.clear();
        TextFild_TotalAddedCost.clear();
        TextFild_OrderCost.clear();

    }

//-------------------------------------------------------------------------------------------------------------------

    //Очищаем табле вив:
    public void ClearTableView(){
        TableView_Order.getItems().clear();
    }

//-------------------------------------------------------------------------------------------------------------------


    private void onEdit() {
        // Внесение выделенной строки таблицы в ячейки редактирования
        if (TableView_Order.getSelectionModel().getSelectedItem() != null) {
            Table_CardOrder selectedPerson = TableView_Order.getSelectionModel().getSelectedItem();

            TextFildOrderNumber.setText(selectedPerson.getColumn1());

            // Преобразуем стринговую переменную в локал дат - для внесения DatePicker   TextField_CompanySeller
            String st_DateRecords =selectedPerson.getColumn2();
            LocalDate ld_DateRecords = LocalDate.parse(st_DateRecords);
            DataPicker_DateAcceptanceOrder.setValue(ld_DateRecords);

            // Преобразуем стринговую переменную в локал дат - для внесения DatePicker   TextField_CompanySeller
            String st_DateRecords3 =selectedPerson.getColumn3();
            LocalDate ld_DateRecords3 = LocalDate.parse(st_DateRecords3);
            DataPicker_DateOrderDelivery.setValue(ld_DateRecords3);

            // Преобразуем стринговую переменную в локал дат - для внесения DatePicker   TextField_CompanySeller
            String st_DateRecords4 =selectedPerson.getColumn4();
            LocalDate ld_DateRecords4 = LocalDate.parse(st_DateRecords4);
            DataPicker_DateOrderCompletion.setValue(ld_DateRecords4);

            TextFild_СlientsСhoice.setText(selectedPerson.getColumn5());
            Text_PrepaymentAmount.setText(String.valueOf(selectedPerson.getColumn6()));

            // Преобразуем стринговую переменную в локал дат - для внесения DatePicker   TextField_CompanySeller
            String st_DateRecords5 =selectedPerson.getColumn7();
            LocalDate ld_DateRecords5 = LocalDate.parse(st_DateRecords5);
            DataPicker_Date_PrepaymentAmount.setValue(ld_DateRecords5);

            Text_PostPaymentAmount.setText(String.valueOf(selectedPerson.getColumn8()));

            // Преобразуем стринговую переменную в локал дат - для внесения DatePicker   TextField_CompanySeller
            String st_DateRecords6 =selectedPerson.getColumn9();
            LocalDate ld_DateRecords6 = LocalDate.parse(st_DateRecords6);
            DataPicker_Date_PostpaymentAmount.setValue(ld_DateRecords6);

            TextAreaComment.setText(selectedPerson.getColumn10());
            _id_Orders = selectedPerson.getColumn11();
            TextFild_TotalPaymentCounterparty.setText(String.valueOf(selectedPerson.getColumn12()));
            TextFild_TotalAddedCost.setText(String.valueOf(selectedPerson.getColumn13()));
            TextFild_OrderCost.setText(String.valueOf(selectedPerson.getColumn14()));

//            System.out.println("_id_Service"+selectedPerson.getColumn6());
        }
    }

//------------------------------------------------------------------------------------------------------------------

    // Вставка значений из текст-филдов в таблицу формы.
    private void Insert_ValuesTextField_TableView(){

        Table_Data.add(new Table_CardOrder(
                _NumberOrder_Orders ,//
                _DateOrderAcception_Orders ,//
                _DateDeliveryOrder_Orders ,//
                _DateCompletionOrder_Orders ,
                _id_NameClient_Orders ,
                _AmountPrepaymentOrder_Orders ,
                _DatePrepaymentOrder_Orders ,
                _AmountlastpaymentOrder_Orders ,
                _DatelastpaymentOrder_Orders ,
                _Coment_Orders,
                0,
                _TotalPaymentCounterparty ,
                _TotalAddedCost ,
                _TotalCost
                )
        );
        TableView_Order.setItems(Table_Data);
    }

    // или так :

    // Вставка значений из текст-филдов в таблицу формы. - номеру заказа
    private void Insert_ValuesTextField_TableView2 () {
        //предварительная очистка обсервайбл лист
        //Table_Data.clear();
        String Select2 =
                " SELECT "   + "directory_orders.NumberOrder_Orders"+ "," +
                        "("+"DATE"+"("+"directory_orders.DateOrderAcception_Orders"+")" +")"+ " AS " +"DateOrderAcception_"+"," +
                        "("+"DATE"+"("+"directory_orders.DateDeliveryOrder_Orders"+")" +")"+ " AS " +"DateDeliveryOrder_"+"," +
                        "("+"DATE"+"("+"directory_orders.DateCompletionOrder_Orders"+")" +")"+ " AS " +"DateCompletionOrder_"+"," +
                        "directory_client.Name_Client"+","+
                        "directory_orders.AmountPrepaymentOrder_Orders"+","+
                        "("+"DATE"+"("+"directory_orders.DatePrepaymentOrder_Orders"+")" +")"+ " AS " +"DatePrepaymentOrder_"+"," +
                        "directory_orders.AmountlastpaymentOrder_Orders"+","+
                        "("+"DATE"+"("+"directory_orders.DatelastpaymentOrder_Orders"+")" +")"+ " AS " +"DatelastpaymentOrder_"+"," +
                        "directory_orders.Coment_Orders"+","+
                        "directory_orders.id_Orders"+","+
                        "directory_orders.TotalPaymentCounterparty_Orders"+","+
                        "directory_orders.TotalAddedCost_Orders"+","+
                        "directory_orders.TotalCostOrder_Orders"+

                        " FROM "+
                        "directory_orders"+
                        " JOIN "+ "directory_client" +" ON "+ "directory_client.id_Client" +"="+ "directory_orders.id_NameClient_Orders"+
                        " WHERE "+ "directory_orders.NumberOrder_Orders"+
                        " LIKE " +"'"+TextFildOrderNumber.getText()+"%'";

        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select2)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_CardOrder(
                                ResSet.getString("directory_orders.NumberOrder_Orders"),
                                ResSet.getString("DateOrderAcception_"),
                                ResSet.getString("DateDeliveryOrder_"),
                                ResSet.getString("DateCompletionOrder_"),
                                ResSet.getString("directory_client.Name_Client"),
                                ResSet.getInt("directory_orders.AmountPrepaymentOrder_Orders"),
                                ResSet.getString("DatePrepaymentOrder_"),
                                ResSet.getInt("directory_orders.AmountlastpaymentOrder_Orders"),
                                ResSet.getString("DatelastpaymentOrder_"),
                                ResSet.getString("directory_orders.Coment_Orders"),
                                ResSet.getInt("directory_orders.id_Orders"),
                                ResSet.getInt("directory_orders.TotalPaymentCounterparty_Orders"),
                                ResSet.getInt("directory_orders.TotalAddedCost_Orders"),
                                ResSet.getInt("directory_orders.TotalCostOrder_Orders")
                        )
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

            //Диалог о "неправильности" введенного клиента
//            JOptionPane.showMessageDialog(null, " Несовпадение вносимого клиента с уже имеющимися в БД ! ",
//                    "Ошибка !", JOptionPane.PLAIN_MESSAGE);
        }
        TableView_Order.setItems(Table_Data);
    }

    //------------------------------------------------------------------------------------------------------------------

    // Запись значений из текст-филдов в переменные.
    private void Record_TextField_Variable() {

        _id_NameClient_Orders = TextFild_СlientsСhoice.getText();
        _NumberOrder_Orders =TextFildOrderNumber.getText();
        _AmountPrepaymentOrder_Orders = Integer.parseInt(Text_PrepaymentAmount.getText());
        _AmountlastpaymentOrder_Orders = Integer.parseInt(Text_PostPaymentAmount.getText());

        // Преобразуем DatePicker В стринговую переменную
        LocalDate ld_DateRecords1 = DataPicker_DateAcceptanceOrder.getValue();
        _DateOrderAcception_Orders = String.valueOf(ld_DateRecords1);

        // Преобразуем DatePicker В стринговую переменную
        LocalDate ld_DateRecords2 = DataPicker_DateOrderDelivery.getValue();
        _DateDeliveryOrder_Orders = String.valueOf(ld_DateRecords2);

        // Преобразуем DatePicker В стринговую переменную
        LocalDate ld_DateRecords3 = DataPicker_DateOrderCompletion.getValue();
        _DateCompletionOrder_Orders = String.valueOf(ld_DateRecords3);

        // Преобразуем DatePicker В стринговую переменную
        LocalDate ld_DateRecords4 = DataPicker_Date_PrepaymentAmount.getValue();
        _DatePrepaymentOrder_Orders = String.valueOf(ld_DateRecords4);

        // Преобразуем DatePicker В стринговую переменную
        LocalDate ld_DateRecords5 = DataPicker_Date_PostpaymentAmount.getValue();
        _DatelastpaymentOrder_Orders = String.valueOf(ld_DateRecords5);

        _Coment_Orders = TextAreaComment.getText();

        _TotalPaymentCounterparty = Integer.parseInt(TextFild_TotalPaymentCounterparty.getText());
        _TotalAddedCost = Integer.parseInt(TextFild_TotalAddedCost.getText());
        _TotalCost = Integer.parseInt(TextFild_OrderCost.getText());

    }

    //------------------------------------------------------------------------------------------------------------------

    // Инициализация таблицы адресатов сo столбцами.
    private void InitializationTable() {
        ColumnOrderNumber.setCellValueFactory(new PropertyValueFactory<Table_CardOrder, String>("Column1"));
        Column_DateAcceptanceOrder.setCellValueFactory(new PropertyValueFactory<Table_CardOrder, String>("Column2"));
        Column_DateOrderDelivery.setCellValueFactory(new PropertyValueFactory<Table_CardOrder, String>("Column3"));
        Column_DateOrderCompletion.setCellValueFactory(new PropertyValueFactory<Table_CardOrder, String>("Column4"));
        Column_Client.setCellValueFactory(new PropertyValueFactory<Table_CardOrder, String>("Column5"));
        Column_PrepaymentAmount.setCellValueFactory(new PropertyValueFactory<Table_CardOrder, Integer>("Column6"));
        Column_DatePrepaymentAccept.setCellValueFactory(new PropertyValueFactory<Table_CardOrder, String>("Column7"));
        Column_PostpaidAmount.setCellValueFactory(new PropertyValueFactory<Table_CardOrder, Integer>("Column8"));
        Column_DatePostpaidAccept.setCellValueFactory(new PropertyValueFactory<Table_CardOrder, String>("Column9"));
        Column_Comment.setCellValueFactory(new PropertyValueFactory<Table_CardOrder, String>("Column10"));
        Column_Id.setCellValueFactory(new PropertyValueFactory<Table_CardOrder, Integer>("Column11"));
        Column_TotalPaymentCounterparty.setCellValueFactory(new PropertyValueFactory<Table_CardOrder, Integer>("Column12"));
        Column_TotalAddedCost.setCellValueFactory(new PropertyValueFactory<Table_CardOrder, Integer>("Column13"));
        Column_OrderCost.setCellValueFactory(new PropertyValueFactory<Table_CardOrder, Integer>("Column14"));

    }


    //------------------------------------------------------------------------------------------------------------------


    //Внесение данных из бд в таблицу по значению в текстфилде - год,месяц,день
    private void SelectOrderYear_Select2 () {
        //предварительная очистка обсервайбл лист
        Table_Data.clear();
           String Select2 =" SELECT "   + "directory_orders.NumberOrder_Orders"+ "," +
                   "("+"DATE"+"("+"directory_orders.DateOrderAcception_Orders"+")" +")"+ " AS " +"DateOrderAcception_"+"," +
                   "("+"DATE"+"("+"directory_orders.DateDeliveryOrder_Orders"+")" +")"+ " AS " +"DateDeliveryOrder_"+"," +
                   "("+"DATE"+"("+"directory_orders.DateCompletionOrder_Orders"+")" +")"+ " AS " +"DateCompletionOrder_"+"," +
                "directory_client.Name_Client"+","+
                "directory_orders.AmountPrepaymentOrder_Orders"+","+
                   "("+"DATE"+"("+"directory_orders.DatePrepaymentOrder_Orders"+")" +")"+ " AS " +"DatePrepaymentOrder_"+"," +
                "directory_orders.AmountlastpaymentOrder_Orders"+","+
                   "("+"DATE"+"("+"directory_orders.DatelastpaymentOrder_Orders"+")" +")"+ " AS " +"DatelastpaymentOrder_"+"," +
                "directory_orders.Coment_Orders"+","+
                "directory_orders.id_Orders"+","+
                   "directory_orders.TotalPaymentCounterparty_Orders"+","+
                   "directory_orders.TotalAddedCost_Orders"+","+
                   "directory_orders.TotalCostOrder_Orders"+
        " FROM "+
                "directory_orders"+
        " JOIN "+ "directory_client" +" ON "+ "directory_client.id_Client" +"="+ "directory_orders.id_NameClient_Orders"+
        " WHERE "+ "directory_orders.DateOrderAcception_Orders"+
                   " LIKE " +"'"+TextFild_OrderSelectYear.getText()+"%'";


            try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
                 PreparedStatement PrSt = conn.prepareStatement(Select2)) {
                ResultSet ResSet = PrSt.executeQuery();

                while (ResSet.next()) {
                    Table_Data.add(new Table_CardOrder(
                            ResSet.getString("directory_orders.NumberOrder_Orders"),
                            ResSet.getString("DateOrderAcception_"),
                            ResSet.getString("DateDeliveryOrder_"),
                            ResSet.getString("DateCompletionOrder_"),
                            ResSet.getString("directory_client.Name_Client"),
                            ResSet.getInt("directory_orders.AmountPrepaymentOrder_Orders"),
                            ResSet.getString("DatePrepaymentOrder_"),
                            ResSet.getInt("directory_orders.AmountlastpaymentOrder_Orders"),
                            ResSet.getString("DatelastpaymentOrder_"),
                            ResSet.getString("directory_orders.Coment_Orders"),
                            ResSet.getInt("directory_orders.id_Orders"),
                            ResSet.getInt("directory_orders.TotalPaymentCounterparty_Orders"),
                            ResSet.getInt("directory_orders.TotalAddedCost_Orders"),
                            ResSet.getInt("directory_orders.TotalCostOrder_Orders")
                            )
                    );
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        TableView_Order.setItems(Table_Data);
        }

//------------------------------------------------------------------------------------------------------------------


    //Внесение данных из бд в таблицу по значению в текстфилде - клиенту
    private void SelectOrderClient_Select2 () {
        //предварительная очистка обсервайбл лист
        Table_Data.clear();
        String Select2 =
                " SELECT "   + "directory_orders.NumberOrder_Orders"+ "," +
                        "("+"DATE"+"("+"directory_orders.DateOrderAcception_Orders"+")" +")"+ " AS " +"DateOrderAcception_"+"," +
                        "("+"DATE"+"("+"directory_orders.DateDeliveryOrder_Orders"+")" +")"+ " AS " +"DateDeliveryOrder_"+"," +
                        "("+"DATE"+"("+"directory_orders.DateCompletionOrder_Orders"+")" +")"+ " AS " +"DateCompletionOrder_"+"," +
                        "directory_client.Name_Client"+","+
                        "directory_orders.AmountPrepaymentOrder_Orders"+","+
                        "("+"DATE"+"("+"directory_orders.DatePrepaymentOrder_Orders"+")" +")"+ " AS " +"DatePrepaymentOrder_"+"," +
                        "directory_orders.AmountlastpaymentOrder_Orders"+","+
                        "("+"DATE"+"("+"directory_orders.DatelastpaymentOrder_Orders"+")" +")"+ " AS " +"DatelastpaymentOrder_"+"," +
                        "directory_orders.Coment_Orders"+","+
                        "directory_orders.id_Orders"+","+
                        "directory_orders.TotalPaymentCounterparty_Orders"+","+
                        "directory_orders.TotalAddedCost_Orders"+","+
                        "directory_orders.TotalCostOrder_Orders"+

                " FROM "+
                "directory_orders"+
                " JOIN "+ "directory_client" +" ON "+ "directory_client.id_Client" +"="+
                "directory_orders.id_NameClient_Orders"+
                " WHERE "+ "directory_client.Name_Client"+
                " LIKE " +"'"+TextFild_OrderSelectClient.getText()+"%'";


        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select2)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_CardOrder(
                                ResSet.getString("directory_orders.NumberOrder_Orders"),
                                ResSet.getString("DateOrderAcception_"),
                                ResSet.getString("DateDeliveryOrder_"),
                                ResSet.getString("DateCompletionOrder_"),
                                ResSet.getString("directory_client.Name_Client"),
                                ResSet.getInt("directory_orders.AmountPrepaymentOrder_Orders"),
                                ResSet.getString("DatePrepaymentOrder_"),
                                ResSet.getInt("directory_orders.AmountlastpaymentOrder_Orders"),
                                ResSet.getString("DatelastpaymentOrder_"),
                                ResSet.getString("directory_orders.Coment_Orders"),
                                ResSet.getInt("directory_orders.id_Orders"),
                                ResSet.getInt("directory_orders.TotalPaymentCounterparty_Orders"),
                                ResSet.getInt("directory_orders.TotalAddedCost_Orders"),
                                ResSet.getInt("directory_orders.TotalCostOrder_Orders")

                        )
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Order.setItems(Table_Data);
    }
//------------------------------------------------------------------------------------------------------------------


    //Внесение данных из бд в таблицу по значению в текстфилде - номеру заказа
    private void SelectOrderNumber_Select2 () {
        //предварительная очистка обсервайбл лист
        Table_Data.clear();
        String Select2 =
                " SELECT "   + "directory_orders.NumberOrder_Orders"+ "," +
                        "("+"DATE"+"("+"directory_orders.DateOrderAcception_Orders"+")" +")"+ " AS " +"DateOrderAcception_"+"," +
                        "("+"DATE"+"("+"directory_orders.DateDeliveryOrder_Orders"+")" +")"+ " AS " +"DateDeliveryOrder_"+"," +
                        "("+"DATE"+"("+"directory_orders.DateCompletionOrder_Orders"+")" +")"+ " AS " +"DateCompletionOrder_"+"," +
                        "directory_client.Name_Client"+","+
                        "directory_orders.AmountPrepaymentOrder_Orders"+","+
                        "("+"DATE"+"("+"directory_orders.DatePrepaymentOrder_Orders"+")" +")"+ " AS " +"DatePrepaymentOrder_"+"," +
                        "directory_orders.AmountlastpaymentOrder_Orders"+","+
                        "("+"DATE"+"("+"directory_orders.DatelastpaymentOrder_Orders"+")" +")"+ " AS " +"DatelastpaymentOrder_"+"," +
                        "directory_orders.Coment_Orders"+","+
                        "directory_orders.id_Orders"+","+
                        "directory_orders.TotalPaymentCounterparty_Orders"+","+
                        "directory_orders.TotalAddedCost_Orders"+","+
                        "directory_orders.TotalCostOrder_Orders"+

                " FROM "+
                "directory_orders"+
                " JOIN "+ "directory_client" +" ON "+ "directory_client.id_Client" +"="+ "directory_orders.id_NameClient_Orders"+
                " WHERE "+ "directory_orders.NumberOrder_Orders"+
                " LIKE " +"'"+TextFild_OrderSelectNumber.getText()+"%'";

        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select2)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_CardOrder(
                                ResSet.getString("directory_orders.NumberOrder_Orders"),
                                ResSet.getString("DateOrderAcception_"),
                                ResSet.getString("DateDeliveryOrder_"),
                                ResSet.getString("DateCompletionOrder_"),
                                ResSet.getString("directory_client.Name_Client"),
                                ResSet.getInt("directory_orders.AmountPrepaymentOrder_Orders"),
                                ResSet.getString("DatePrepaymentOrder_"),
                                ResSet.getInt("directory_orders.AmountlastpaymentOrder_Orders"),
                                ResSet.getString("DatelastpaymentOrder_"),
                                ResSet.getString("directory_orders.Coment_Orders"),
                                ResSet.getInt("directory_orders.id_Orders"),
                                ResSet.getInt("directory_orders.TotalPaymentCounterparty_Orders"),
                                ResSet.getInt("directory_orders.TotalAddedCost_Orders"),
                                ResSet.getInt("directory_orders.TotalCostOrder_Orders")
                        )
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Order.setItems(Table_Data);
    }
//------------------------------------------------------------------------------------------------------------------




}

