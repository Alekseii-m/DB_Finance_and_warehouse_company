
package BD_Controller;

        import java.net.URL;
        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.time.LocalDate;
        import java.util.ListIterator;
        import java.util.ResourceBundle;

        import BD_Common.Const;
        import BD_Common.DbConnectDataBase;
        import BD_Common.OpenNewWindow_Class;
        import ObservListTable.*;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.fxml.FXML;
        import javafx.scene.control.*;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.scene.layout.AnchorPane;

        import javax.swing.*;


public class Cont_BuySell_Service {

    private final ObservableList<Table_BuySell_Service> Table_Data = FXCollections.observableArrayList();
 //   private final ObservableList<Table_AddSelect> Table_Data2 = FXCollections.observableArrayList();


    //переменные для заполнения БД--Service--------------------------------------------->
    int _id_BuySell_Service =0;
    String _id_NameService_BuySell_Service ="";
    String _id_OrderNumber_BuySell_Service ="";
    String _DateRecording_BuySell_Service ="";
    int _Amount_BuySell_Service =0;
    String _id_ServiceProvider_BuySell_Service ="";
    String _id_ServiceRecipient_BuySell_Service ="";
    String _Additionally_BuySell_Service ="";
    int _UnitInitialCost_Service =0;
    int _UnitWithAddedCost_Service =0;
    int _TotalCost_Service =0;
    //переменные  для заполнения БД-----------------------------------------------<

    //переменные для заполнения БД--Products--------------------------------------------->
    int _id_BuySell_Products =0;
    String _id_NameProduct_BuySell_Products ="";
    String _id_OrderNumber_BuySell_Products ="";
    String _DateRecording_BuySell_Products ="";
    int _Amount_BuySell_Products =0;
    String _id_ProductProvider_BuySell_Products ="";
    String _id_ProductRecipient_BuySell_Products ="";
    String _Additionally_BuySell_Products ="";
    int _UnitInitialCost_Products =0;
    int _UnitWithAddedCost_Products =0;
    int _TotalCost_Products =0;
    //переменные  для заполнения БД-----------------------------------------------<

    // переменные для переноса из таблицы интерф. в форму заполнения
    int _id =0;
    int Payments =0;
    int AddedCost =0;
    int TotalCost =0;
    // и наоборот

    int _id_del =0;// для удаления строки в таблице и БД
    int n =0;    // удаление выделенной строки в таблице
    String Name_Product_Service ="";

    @FXML
    private RadioButton Select_Service;

    @FXML
    private RadioButton Select_Product;

    @FXML
    private AnchorPane AnchorPane_Bay_Sell_Servis_Product; //

    @FXML
    private Button Button_SelectPrTasK;

    @FXML
    private Button Button_InsertPrTasK;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Label_1;

    @FXML
    private Button ButtonFormClear;

    @FXML
    private Button ButtonEditLine;

    @FXML
    private Button ButtonSave;

    @FXML
    private TextArea TextAreaComment;

    @FXML
    private TextField TextFildCounterpartyСhoice1;

    @FXML
    private TextField TextFild_TotalPaymentCounterparty;

    @FXML
    private Button ButtonInsertCounterparty1;

    @FXML
    private Button Button_Сalculation;

    @FXML
    private TableView<Table_BuySell_Service> TableView_Bay_Sell_Servis_Product;

    @FXML
    private TableColumn<Table_BuySell_Service, String> ColumnOrderNumber;

    @FXML
    private TableColumn<Table_BuySell_Service, String> Column_Service_Product;

    @FXML
    private TableColumn<Table_BuySell_Service, Integer> ColumnAmount;

    @FXML
    private TableColumn<Table_BuySell_Service, String> Column_DatePurchase;

    @FXML
    private TableColumn<Table_BuySell_Service, String> ColumnCounterpartyOffering;

    @FXML
    private TableColumn<Table_BuySell_Service, String> ColumnCounterpartyBuying;

    @FXML
    private TableColumn<Table_BuySell_Service, Integer> ColumnPayments;

    @FXML
    private TableColumn<Table_BuySell_Service, Integer> ColumnAddedCost;

    @FXML
    private TableColumn<Table_BuySell_Service, Integer> ColumnTotalCost;

    @FXML
    private TableColumn<Table_BuySell_Service, String> ColumnComment;

    @FXML
    private TableColumn<Table_BuySell_Service, Integer> ColumnId;

    @FXML
    private TableColumn<Table_BuySell_Service, String>  Column_select_s_p;

    @FXML
    private Button ButtonClearTableView;

    @FXML
    private Button ButtonSaveLine;

    @FXML
    private Button ButtonDeleteLine;

    @FXML
    private Button ButtonSelectCounterparty1;

    @FXML
    private Button Button_SelectServiceCounterpartyOffer_OrderNumber;

    @FXML
    private DatePicker DataPicker_RecordingDate;

    @FXML
    private TextField TextField_OrderNumber;

    @FXML
    private Button ButtonSelectPrTaskYear;

    @FXML
    private TextField TextField_Select_OrderNumber;

//    @FXML
//    private TableView<Table_AddSelect> TableView_Select_OrderNumber;

//    @FXML
//    private TableColumn<Table_AddSelect, String> Column_OrderNumber;
//
//    @FXML
//    private TableColumn<Table_AddSelect, Integer> Column_OrderNumber_id;

    @FXML
    private TextField TextFildCounterpartyСhoice2;

//    @FXML
//    private Button ButtonInsertCounterparty2;

//    @FXML
//    private Button ButtonSelectCounterparty2;

    @FXML
    private TextField TextFild_Servis_Product;

    @FXML
    private Button ButtonInsert_Servis_Product;

    @FXML
    private Button ButtonSelect_Servis_Product;

    @FXML
    private TextField TextFild_Amount;

    @FXML
    private Button Button_SelectServiceCounterpartyBay_OrderNumber;

    @FXML
    private Button Button_SelectService_CounterpartyBay_TimePeriod;

    @FXML
    private Button Button_SelectService_CounterpartyOffer_TimePeriod;

    @FXML
    private TextField TextFild_TotalCost;

    @FXML
    private TextField TextFild_TotalAddedCost;

    @FXML
    private DatePicker DataPicker_TimePeriod_Start;

    @FXML
    private DatePicker DataPicker_TimePeriod_End;

    @FXML
    private Button ButtonSelectService_TimePeriod;

    @FXML
    private Button ButtonSelectService_OrderNumber;

    @FXML
    private Button Button_SelectProductCounterpartyOffer_OrderNumber;

    @FXML
    private Button Button_SelectProductCounterpartyBay_OrderNumber;

    @FXML
    private Button Button_SelectProduct_CounterpartyBay_TimePeriod;

    @FXML
    private Button Button_SelectProduсt_CounterpartyOffer_TimePeriod;

    @FXML
    private Button ButtonSelectProduct_TimePeriod;

    @FXML
    private Button ButtonSelectProduct_OrderNumber;

    @FXML
    private Button ButtonSelect_Service_Product_OrderNumber;

    @FXML
    private Button ButtonSelect_Service_Product_TimePeriod;

    @FXML
    private Button ButtonSelectCalcData;

    @FXML
    void initialize() {
//---------------------------------------------------------
        // для группы переключателей "радио-буттон", дает возможность быть активным только одному выключателю
        ToggleGroup group = new ToggleGroup();
        // установка группы выключателей для этого правила
        Select_Service.setToggleGroup(group);
        Select_Product.setToggleGroup(group);
//-------------------------------------------------------


        // Скрыть столбец id таблицы
//        Column_OrderNumber_id.setVisible(false);

        // Скрыть столбец id таблицы
       // ColumnId.setVisible(false);
//-------------------------------------------------------------------------------------

        //внести в текст-филд в зависимости от переключателя название сервиса или продукта
        ButtonInsert_Servis_Product.setOnAction(event -> { //переключатель "радио-буттон"
            //
            if (Const.Choice_Service_Product == true){// true - Service ,false - Product
                TextFild_Servis_Product.setText(Const.CardService_NameService);
            }
            else {
                TextFild_Servis_Product.setText(Const.CardProduct_NameProduct);
            }

        });

//-------------------------------------------------------------------------------------
        Select_Service.setOnAction(event -> { //переключатель "радио-буттон"
            //
            if (Select_Service.isSelected()){
                Const.Choice_Service_Product = true ; // true - Service ,false - Product
            }
            else {}
            System.out.println(Const.Choice_Service_Product);
        });

//-------------------------------------------------------------------------------------

        Select_Product.setOnAction(event -> { //переключатель "радио-буттон"
            //
            if (Select_Product.isSelected()){
                Const.Choice_Service_Product = false ; // true - Service ,false - Product
            }
            else {}
            System.out.println(Const.Choice_Service_Product);
       });

//-------------------------------------------------------------------------------------

        ButtonSelectCounterparty1.setOnAction(event -> {
            //Загрузка окна
            OpenNewWindow_Class.OpenNewWindow_ ("/BD_fxml/CardCounterparty.fxml");
        });

//        ButtonSelectCounterparty2.setOnAction(event -> {
//            //Загрузка окна
//            OpenNewWindow_Class.OpenNewWindow_ ("/BD_fxml/CardCounterparty.fxml");
//        });
        //-------------------------------------------------------------------------------------------------------
        //Загрузка карточки сервиса либо карточки продукта
        ButtonSelect_Servis_Product.setOnAction(event -> {
           if (Const.Choice_Service_Product == true ) {  // true - Service ,false - Product
            //Загрузка окна
               System.out.println("-true-Service");
            OpenNewWindow_Class.OpenNewWindow_ ("/BD_fxml/CardService.fxml");}
            else  {  // true - Service ,false - Product
                //Загрузка окна
               System.out.println("-false - Product");
                OpenNewWindow_Class.OpenNewWindow_ ("/BD_fxml/CardProduct.fxml");}

        });
        //-------------------------------------------------------------------------------------------------------

//        //Внесение данных из бд в таблицу по номеру заказа и дате примерной
//        ButtonSelectPrTaskYear.setOnAction(event -> {
//            //предварительная очистка обсервайбл лист
//            Table_Data.clear();
//
//            // Инициализация таблицы адресатов сo столбцами.
//            InitializationTable();
//
//            // Выборка данных по условию если пусто-ChangSelect1, если нет ChangeSelekt2 :
//            if(TextField_Select_OrderNumber.getText().trim().equals(""))
//            { SelectPrTaskYear1_Service (); }  // Выборка всех заказов с услугами
//            else {
//               SelectPrTaskYear2_Service ();//
//            }
//        });


        //-------------------------------------------------------------------------------------------------------

        //Внесение данных из бд в таблицу по : Выбрать услуги по КонтрАгенту предлаг. услугу  и времен. периоду
        Button_SelectService_CounterpartyOffer_TimePeriod.setOnAction(event -> {

            //предварительная очистка обсервайбл лист
            Table_Data.clear();

            // Инициализация таблицы адресатов сo столбцами.
            InitializationTable();
            // Выборка данных по условию если пусто-ChangSelect1, если нет ChangeSelekt2 :
            if(TextFildCounterpartyСhoice1.getText().trim().equals(""))
            {  }  //SelectService_CounterpartyOffer_TimePeriod_1 ();
            else {

                SelectService_CounterpartyOffer_TimePeriod_2 ();//SelectService_CounterpartyOffer_TimePeriod_2 ();
            }
        });

        //-------------------------------------------------------------------------------------------------------

        //Внесение данных из бд в таблицу по : Выбрать услуги по КонтрАгенту предлаг. и номеру заказа
        Button_SelectServiceCounterpartyOffer_OrderNumber.setOnAction(event -> {
            //предварительная очистка обсервайбл лист
            Table_Data.clear();

            // Инициализация таблицы адресатов сo столбцами.
            InitializationTable();
            // Выборка данных по условию если пусто-ChangSelect1, если нет ChangeSelekt2 :
            if(TextFildCounterpartyСhoice1.getText().trim().equals(""))
            {  }  //SelectService_CounterpartyOffer_OrderNumber_1 ();
            else {

                SelectService_CounterpartyOffer_OrderNumber_2 ();//
            }
        });

        //-------------------------------------------------------------------------------------------------------

        //Внесение данных из бд в таблицу по : Выбрать услуги по КонтрАгенту получающ. услугу  и времен. периоду
        Button_SelectService_CounterpartyBay_TimePeriod.setOnAction(event -> {

            //предварительная очистка обсервайбл лист
            Table_Data.clear();

            // Инициализация таблицы адресатов сo столбцами.
            InitializationTable();
            // Выборка данных по условию если пусто-ChangSelect1, если нет ChangeSelekt2 :
            if(TextFildCounterpartyСhoice2.getText().trim().equals(""))
            {  }  //SelectService_CounterpartyBay_TimePeriod_1 ();
            else {

                SelectService_CounterpartyBay_TimePeriod_2 ();//SelectService_CounterpartyBay_TimePeriod_2 ();
            }
        });

        //-------------------------------------------------------------------------------------------------------


        //Внесение данных из бд в таблицу по : Выбрать услуги по КонтрАгенту получающ. услугу  и номеру заказа
        Button_SelectServiceCounterpartyBay_OrderNumber.setOnAction(event -> {

            //предварительная очистка обсервайбл лист
            Table_Data.clear();

            // Инициализация таблицы адресатов сo столбцами.
            InitializationTable();
            // Выборка данных по условию если пусто-ChangSelect1, если нет ChangeSelekt2 :
            if(TextFildCounterpartyСhoice2.getText().trim().equals(""))
            {  }  //SelectService_CounterpartyBay_OrderNumber_1 ();
            else {

                SelectService_CounterpartyBay_OrderNumber_2 ();//SelectService_CounterpartyBay_OrderNumber_2 ();
            }
        });

        //-------------------------------------------------------------------------------------------------------


        //Внесение данных из бд в таблицу по : Выбрать услуги по периоду дат заказа
        ButtonSelectService_TimePeriod.setOnAction(event -> {
        // System.out.println("Step 1*");

            //предварительная очистка обсервайбл лист
            Table_Data.clear();

            // Инициализация таблицы адресатов сo столбцами.
            InitializationTable();

                SelectService_TimePeriod ();//SelectService_TimePeriod ();

        });

        //-------------------------------------------------------------------------------------------------------


        //Внесение данных из бд в таблицу по : Выбрать услуги по номеру заказа
        ButtonSelectService_OrderNumber.setOnAction(event -> {
           // System.out.println("Step 1*");

            //предварительная очистка обсервайбл лист
            Table_Data.clear();

            // Инициализация таблицы адресатов сo столбцами.
            InitializationTable();

            SelectService_OrderNumber ();//SelectService_TimePeriod ();

        });


        //Внесение данных из бд в таблицу по : Выбрать продукты по КонтрАгенту предлаг. и времен. периоду
        Button_SelectProduсt_CounterpartyOffer_TimePeriod.setOnAction(event -> {
          //  System.out.println("Step 1*");

            //предварительная очистка обсервайбл лист
            Table_Data.clear();

            // Инициализация таблицы адресатов сo столбцами.
            InitializationTable();

            SelectProduсt_CounterpartyOffer_TimePeriod_2 ();//SelectService_TimePeriod ();

        });


        //Внесение данных из бд в таблицу по : Выбрать продукты по КонтрАгенту предлаг. и номеру заказа
        Button_SelectProductCounterpartyOffer_OrderNumber.setOnAction(event -> {
            //System.out.println("Step 1*");

            //предварительная очистка обсервайбл лист
            Table_Data.clear();

            // Инициализация таблицы адресатов сo столбцами.
            InitializationTable();

            SelectProduсt_CounterpartyOffer_OrderNumber_2 ();//SelectService_TimePeriod ();

        });



        //Внесение данных из бд в таблицу по : Выбрать продукты по КонтрАгенту покупающ. и времен. периоду
        Button_SelectProduct_CounterpartyBay_TimePeriod.setOnAction(event -> {
            //  System.out.println("Step 1*");

            //предварительная очистка обсервайбл лист
            Table_Data.clear();

            // Инициализация таблицы адресатов сo столбцами.
            InitializationTable();

            SelectProduсt_CounterpartyBay_TimePeriod_2 ();

        });


        //Внесение данных из бд в таблицу по : Выбрать продукты по КонтрАгенту покупающ. и номеру заказа
        Button_SelectProductCounterpartyBay_OrderNumber.setOnAction(event -> {
            //System.out.println("Step 1*");

            //предварительная очистка обсервайбл лист
            Table_Data.clear();

            // Инициализация таблицы адресатов сo столбцами.
            InitializationTable();

           SelectProduсt_CounterpartyBay_OrderNumber_2 ();

        });


        //Внесение данных из бд в таблицу по : Выбрать продукты по временному периоду
        ButtonSelectProduct_TimePeriod.setOnAction(event -> {
            //System.out.println("Step 1*");

            //предварительная очистка обсервайбл лист
            Table_Data.clear();

            // Инициализация таблицы адресатов сo столбцами.
            InitializationTable();

            SelectProduct_TimePeriod_2 ();

        });


        //Внесение данных из бд в таблицу по : Выбрать продукты по временному периоду
        ButtonSelectProduct_OrderNumber.setOnAction(event -> {
            //System.out.println("Step 1*");

            //предварительная очистка обсервайбл лист
            Table_Data.clear();

            // Инициализация таблицы адресатов сo столбцами.
            InitializationTable();

            SelectProduct_OrderNumber_2 ();

        });


        //Внесение данных из бд в таблицу по : Выбрать услуги и продукты по временному периоду
        ButtonSelect_Service_Product_TimePeriod.setOnAction(event -> {
            //System.out.println("Step 1*");

            //предварительная очистка обсервайбл лист
            Table_Data.clear();

            // Инициализация таблицы адресатов сo столбцами.
            InitializationTable();

            // выбор услуг по временному периоду
            SelectService_TimePeriod ();

            // выбор продуктов по временному периоду
            SelectProduct_TimePeriod_2 ();

        });


        //Внесение данных из бд в таблицу по : Выбрать услуги и  продукты по номеру заказа
        ButtonSelect_Service_Product_OrderNumber.setOnAction(event -> {
            //System.out.println("Step 1*");

            //предварительная очистка обсервайбл лист
            Table_Data.clear();

            // Инициализация таблицы адресатов сo столбцами.
            InitializationTable();

            // выбор услуг по номеру заказа
            SelectService_OrderNumber ();

            // выбор продуктов по номеру заказа
            SelectProduct_OrderNumber_2 ();

        });


        //расчсет итоговых цен контрагенту, добавочной цены и общей цены :
        Button_Сalculation.setOnAction(event -> {
            // расчсет итоговых цен
           Сalculation_TotalCost ();
        });


        // внести имя контрагента предлагающего
        ButtonInsertCounterparty1.setOnAction(event -> {
            //
            TextFildCounterpartyСhoice1.setText(Const.CardCounterparty_NameCounterparty1);
            TextFildCounterpartyСhoice2.setText(Const.CardCounterparty_NameCounterparty2);
        });


        Button_SelectPrTasK.setOnAction(event -> {
            //Загрузка окна
            OpenNewWindow_Class.OpenNewWindow_ ("/BD_fxml/CardOrder.fxml");
        });


        // внести номер заказа
        Button_InsertPrTasK.setOnAction(event -> {
            //
            TextField_OrderNumber.setText(Const.CardOrder_OrderNumber );
        });

        //------------------------------------------------------------------------------------------------------------------

        // вставка-сохранение данных из формы-интерф. в БД
         ButtonSave.setOnAction(event -> {
            // Запись значений из текст-филдов в переменные.
            Record_TextField_Variable();

            if (Const.Choice_Service_Product == true ) {  // true - Service ,false - Product
                //Вставить данные в БД в таблицу _BuySell_Service
                try {
                    DbConnectDataBase.insert_BuySell_Service(
                            _id_NameService_BuySell_Service ,
                            _id_OrderNumber_BuySell_Service ,
                            _DateRecording_BuySell_Service ,
                            _Amount_BuySell_Service ,
                            _id_ServiceProvider_BuySell_Service ,
                            _id_ServiceRecipient_BuySell_Service ,
                            _Additionally_BuySell_Service
                    );
                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                // Вставка значений из текст-филдов в таблицу формы.
                InitializationTable();
                Insert_ValuesTextField_TableView_Service();
            }
            else  {  // true - Service ,false - Product
                //Вставить данные в БД в таблицу _BuySell_Product
                try {
                    DbConnectDataBase.insert_BuySell_Product(
                    _id_NameProduct_BuySell_Products ,
                    _id_OrderNumber_BuySell_Products ,
                    _DateRecording_BuySell_Products ,
                    _Amount_BuySell_Products ,
                    _id_ProductProvider_BuySell_Products ,
                    _id_ProductRecipient_BuySell_Products ,
                    _Additionally_BuySell_Products
                    );
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                // Вставка значений из текст-филдов в таблицу формы.
                InitializationTable();
                Insert_ValuesTextField_TableView_Product();
            }

           // Insert_ValuesTextField_TableView();
        });

//-------------------------------------------------------------------------------------------------------

        // внести в перемен-константы - Расчетные данные из "Покупки-Продажи"
        ButtonSelectCalcData.setOnAction(event -> {

            Const._TotalCost_= TextFild_TotalCost.getText();
            Const._TotalAddedCost_= TextFild_TotalAddedCost.getText();
            Const._TotalPaymentCounterparty_= TextFild_TotalPaymentCounterparty.getText();

        });

//-------------------------------------------------------------------------------------------------------

        // Внесение выделенной строки таблицы в ячейки редактирования
        ButtonEditLine.setOnAction(event -> {
            onEdit();

        });

//-------------------------------------------------------------------------------------------------------


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
        ButtonDeleteLine.setOnAction(event -> {
            GetIdSel();   //Получение id таблицы БД выделенной строки таблицы для дальнейшего удаления:

            // Удаляем строку таблицы БД
            if (Const.Choice_Service_Product == true ) {  // true - Service ,false - Product
                Delete_Rows1(); } //Удаляем строку таблицы БД по id Service
            else  {  // true - Service ,false - Product
                Delete_Rows2();} //Удаляем строку таблицы БД по id Product

            delRow_TableView();            // удаление выделенной строки в таблице
        });

        //-------------------------------------------------------------------------------------------------------

        // Обновить данные строки в БД
        ButtonSaveLine.setOnAction(event -> {

            //Диалог о несовпадении "Направления учета"
            dialog1();


            if (Const.Choice_Service_Product == true ) {  // true - Service ,false - Product
                Update_stringBD_service();
                // Обновление выделенной строки таблицы формы
                Insert_ValuesTextField_TableView_Service_update();
            }
            else  {
                Update_stringBD_Product();
                // Обновление выделенной строки таблицы формы
                Insert_ValuesTextField_TableView_Product_update();
            }

        });

        //-------------------------------------------------------------------------------------------------------




    }

    //------------------------------------------------------------------------------------------------------------------
    //******************************************************************************************************************
    //******************************************************************************************************************
    //------------------------------------------------------------------------------------------------------------------

    private void dialog1() {
        //Диалог о несовпадении "Направления учета"
        JOptionPane.showMessageDialog(null, " Несовпадение _Выбора направления учета_ с названием _Услуга/Товар_ ! ",
                "Ошибка !", JOptionPane.PLAIN_MESSAGE);
    }

//------------------------------------------------------------------------------------------------------------------


    // Обновить данные строки в БД (сборка)
    private void Update_stringBD_service(){

        // Запись значений из текст-филдов в переменные.
        Record_TextField_Variable();

        //Получение id таблицы БД выделенной строки таблицы:
        if (TableView_Bay_Sell_Servis_Product.getSelectionModel().getSelectedItem() != null) {
            Table_BuySell_Service selectedPerson = TableView_Bay_Sell_Servis_Product.getSelectionModel().getSelectedItem();
            _id_del= selectedPerson.getColumn11();}

        //обновить данные строки в БД в таблице
        String update =
        "UPDATE"+ "`bd_company`.`buy_sell_service`"+ "SET "+
        "`id_NameService_BuySell_Service`" +"="+"(" + "SELECT " + "id_Service" + " FROM " + "Directory_Service" +
        " WHERE " + "Name_Service" + "=" + "'" + _id_NameService_BuySell_Service + "'" + ")"+ "," +

        "id_OrderNumber_BuySell_Service" +"="+"(" + " SELECT " + "id_Orders" + " FROM " + "Directory_Orders" +
        " WHERE " + "NumberOrder_Orders" + "=" + "'" + _id_OrderNumber_BuySell_Service + "'" + ")"+"," +

        "`DateRecording_BuySell_Service`" +"="+"'"+_DateRecording_BuySell_Service+"'"+ "," +

        "`Amount_BuySell_Service`" +"="+"'"+_Amount_BuySell_Service+"'"+ "," +

        "`id_ServiceProvider_BuySell_Service`" +"="+"(" + "SELECT" + "`id_Counterparty`" + " FROM" + "`Directory_Counterparty`" +
        " WHERE" + "`Name_Counterparty`" + "=" + "'" + _id_ServiceProvider_BuySell_Service + "'" + ")"+ "," +

        "`id_ServiceRecipient_BuySell_Service`" +"="+"(" + " SELECT " + "id_Counterparty" + " FROM " + "Directory_Counterparty" +
        " WHERE " + "Name_Counterparty" + "=" + "'" + _id_ServiceRecipient_BuySell_Service + "'" + ")"+ "," +

        "`Additionally_BuySell_Service`" +"="+"'"+_Additionally_BuySell_Service+"'"+

                "WHERE"+ "`id_BuySell_Service`"+"="+"'"+_id_del+"'"
                + " AND "+  "`select_product_service`"+"="+"'"+"s"+"'";

        try (Connection conn = DbConnectDataBase.connect();

             PreparedStatement PrSt = conn.prepareStatement(update)) {
            PrSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //-------

    // Обновить данные строки в БД (сборка)
    private void Update_stringBD_Product(){

        // Запись значений из текст-филдов в переменные.
        Record_TextField_Variable();

        //Получение id таблицы БД выделенной строки таблицы:
        if (TableView_Bay_Sell_Servis_Product.getSelectionModel().getSelectedItem() != null) {
            Table_BuySell_Service selectedPerson = TableView_Bay_Sell_Servis_Product.getSelectionModel().getSelectedItem();
            _id_del= selectedPerson.getColumn11();}

        //обновить данные строки в БД в таблице
        String update =
                "UPDATE"+ "`bd_company`.`buy_sell_products`"+ "SET "+


                        "`id_NameProduct_BuySell_Products`" +"="+"(" + "SELECT " + "id_Product" + " FROM " + "directory_product" + " WHERE " + "Name_Product" + "=" + "'" + _id_NameProduct_BuySell_Products + "'" + ")"+ "," +
                        "id_OrderNumber_BuySell_Products" +"="+"(" + " SELECT " + "id_Orders" + " FROM " + "Directory_Orders" + " WHERE " + "NumberOrder_Orders" + "=" + "'" + _id_OrderNumber_BuySell_Products + "'" + ")"+ "," +
                        "`DateRecording_BuySell_Products`" +"="+"'"+_DateRecording_BuySell_Products+"'"+ "," +
                        "`Amount_BuySell_Products`" +"="+"'"+_Amount_BuySell_Products+"'"+ "," +
                        "`id_ProductProvider_BuySell_Products`" +"="+"(" + "SELECT" + "`id_Counterparty`" + " FROM" + "`Directory_Counterparty`" + " WHERE" + "`Name_Counterparty`" + "=" + "'" + _id_ProductProvider_BuySell_Products + "'" + ")"+ "," +
                        "`id_ProductRecipient_BuySell_Products`" +"="+"(" + " SELECT " + "id_Counterparty" + " FROM " + "Directory_Counterparty" + " WHERE " + "Name_Counterparty" + "=" + "'" + _id_ProductRecipient_BuySell_Products + "'" + ")"+ "," +
                        "`Additionally_BuySell_Products`" +"="+"'"+_Additionally_BuySell_Products+"'"+

                        "WHERE"+ "`id_BuySell_Products`"+"="+"'"+_id_del+"'"
                        + " AND "+  "`select_product_service`"+"="+"'"+"p"+"'";
        
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
        n =  TableView_Bay_Sell_Servis_Product.getSelectionModel().getSelectedIndex();
        TableView_Bay_Sell_Servis_Product.getItems().remove(n);
        System.out.println("*1n - "+ n);
    }


    //Получение id таблицы БД выделенной строки таблицы для дальнейшего удаления:
    private void GetIdSel(){
        if (TableView_Bay_Sell_Servis_Product.getSelectionModel().getSelectedItem() != null) {
            Table_BuySell_Service selectedPerson = TableView_Bay_Sell_Servis_Product.getSelectionModel().getSelectedItem();
            _id_del= selectedPerson.getColumn11();
            System.out.println("*1_id_del - "+ _id_del);

            Name_Product_Service= selectedPerson.getColumn2();
        }

    }


    // Удаляем строку таблицы БД Service по id
    private void Delete_Rows1() {

        //   String DelR =  "DELETE "+ " FROM " + Const.TABLE_CardClient + " WHERE " + Const.COLUMN_CardClient_Id_+ "= ?" ;
        String DelR =  "DELETE "+ " FROM " + "`bd_company`.`buy_sell_service`"  +
                " WHERE " +  "id_BuySell_Service"+ "= ?" + " AND "+
                "(" + "(" + "SELECT " + "id_Service" + " FROM " + "Directory_Service" + " WHERE " + "Name_Service" + "=" + "'" + Name_Product_Service + "'" + ")"+")";

        try (Connection conn = DbConnectDataBase.connect();
             PreparedStatement pstmt = conn.prepareStatement(DelR)) {

             //  id столбца
            pstmt.setInt(1, _id_del);

            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    // Удаляем строку таблицы БД Product по id
    private void Delete_Rows2() {

        //   String DelR =  "DELETE "+ " FROM " + Const.TABLE_CardClient + " WHERE " + Const.COLUMN_CardClient_Id_+ "= ?" ;
        String DelR =  "DELETE "+ " FROM " + "`bd_company`.`buy_sell_products`"  +
                " WHERE " +  "id_BuySell_Products"+ "= ?" + " AND "+
                "(" + "(" + "SELECT " + "id_Product" + " FROM " + "directory_product" + " WHERE " + "Name_Product" + "=" + "'" + Name_Product_Service + "'" + ")"+")" ;

        try (Connection conn = DbConnectDataBase.connect();
             PreparedStatement pstmt = conn.prepareStatement(DelR)) {

            //  id столбца
            pstmt.setInt(1, _id_del);

            System.out.println("*2_id_del - "+ _id_del);
            System.out.println("*2n - "+ n);

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

        TextField_OrderNumber.clear();
        TextFild_Servis_Product.clear();
        TextFild_Amount.clear();

        DataPicker_RecordingDate.getEditor().clear();
        DataPicker_RecordingDate.setValue(null);

        TextFildCounterpartyСhoice1.clear();
        TextFildCounterpartyСhoice2.clear();

        TextAreaComment.clear();
        _id=0;

    }

//-------------------------------------------------------------------------------------------------------------------

    //Очищаем табле вив:
    public void ClearTableView(){
        TableView_Bay_Sell_Servis_Product.getItems().clear();
    }

//-------------------------------------------------------------------------------------------------------------------


    private void onEdit() {
        // Внесение выделенной строки таблицы в ячейки редактирования
        if (TableView_Bay_Sell_Servis_Product.getSelectionModel().getSelectedItem() != null) {
            Table_BuySell_Service selectedPerson = TableView_Bay_Sell_Servis_Product.getSelectionModel().getSelectedItem();

            TextField_OrderNumber.setText(selectedPerson.getColumn1());
            TextFild_Servis_Product.setText(selectedPerson.getColumn2());
            TextFild_Amount.setText(String.valueOf(selectedPerson.getColumn3()));

            // Преобразуем стринговую переменную в локал дат - для внесения DatePicker   TextField_CompanySeller
            String st_DateRecords =selectedPerson.getColumn4();
            LocalDate ld_DateRecords = LocalDate.parse(st_DateRecords);
            DataPicker_RecordingDate.setValue(ld_DateRecords);

            TextFildCounterpartyСhoice1.setText(selectedPerson.getColumn5());
            TextFildCounterpartyСhoice2.setText(selectedPerson.getColumn6());

            Payments=selectedPerson.getColumn7();
            AddedCost=selectedPerson.getColumn8();
            TotalCost=selectedPerson.getColumn9();
            TextAreaComment.setText(selectedPerson.getColumn10());
            _id=selectedPerson.getColumn11();
//            System.out.println("_id_Service"+selectedPerson.getColumn6());
        }
    }

//------------------------------------------------------------------------------------------------------------------

//    // Вставка значений из текст-филдов в таблицу формы.
//    private void Insert_ValuesTextField_TableView(){
//        // Преобразуем DatePicker В стринговую переменную
//        LocalDate ld_DateRecords10 = DataPicker_RecordingDate.getValue();
//       String _DateRecording_ = String.valueOf(ld_DateRecords10);
//
//        Table_Data.add(new Table_BuySell_Service(
//                TextField_OrderNumber.getText(),
//                TextFild_Servis_Product.getText(),
//                Integer.parseInt(TextFild_Amount.getText()),
//                _DateRecording_,
//                TextFildCounterpartyСhoice1.getText(),
//                TextFildCounterpartyСhoice2.getText(),
//                0,
//                0,
//                TextAreaComment.getText(),
//                0)
//        );
//        TableView_Bay_Sell_Servis_Product.setItems(Table_Data);
//
//    }

    // или так

    //Вставка значений из текст-филдов в таблицу формы. ( Внесение данных из бд в таблицу)
    private void Insert_ValuesTextField_TableView_Service() {

        String Select1 =

                " SELECT " +
                        "`directory_orders`.`NumberOrder_Orders`"+","+
                        "`directory_service`.`Name_Service`" + ", " +
                        "`buy_sell_service`.`Amount_BuySell_Service` " + "," +//
                        // "`buy_sell_service`.`DateRecording_BuySell_Service`" + " AS " + "DateRecording"+","+
                        "("+"DATE"+"("+"`buy_sell_service`.`DateRecording_BuySell_Service`"+")" +")"+" AS " + "DateRecording"+","+

                        "`t1`.`Name_Counterparty`"+ " AS " + "CounterpartyOffering"+","+
                        "`t2`.`Name_Counterparty`"+ " AS " + "CounterpartyBuying"+","+
                        "`directory_service`.`UnitInitialCost_Service`" +","+
                        "`directory_service`.`UnitWithAddedCost_Service`" +","+
// расчетный столбец
                        "("+" `buy_sell_service`.`Amount_BuySell_Service`"+"*"+"("+"`directory_service`.`UnitInitialCost_Service`"+"+"+
                        "`directory_service`.`UnitWithAddedCost_Service`"+")"+")" + " AS " + " TotalCost"+","+
                        "`buy_sell_service`.`Additionally_BuySell_Service`"+ " AS " + "Comment"+","+
                        "`buy_sell_service`.`id_BuySell_Service`"+ " AS " + "id"+

                        " FROM " +" `buy_sell_service` "+
                        " JOIN "+ "`directory_orders`" + " ON "+ "`directory_orders`.`id_Orders`"+ "="+ "`buy_sell_service`.`id_OrderNumber_BuySell_Service`"+
                        " JOIN "+ "`directory_service`" + " ON "+ "`directory_service`.`id_Service`" +"="+ "`buy_sell_service`.`id_NameService_BuySell_Service`"+
                        " JOIN "+ "directory_counterparty" + " AS " +"`t1`"+ " ON "+ "`t1`.id_Counterparty" +"=" +"buy_sell_service.id_ServiceProvider_BuySell_Service"+
                        " JOIN "+ "directory_counterparty" + " AS " +"`t2`"+ " ON "+ "`t2`.id_Counterparty" +"=" +"buy_sell_service.id_ServiceRecipient_BuySell_Service"+

                        " WHERE " + "`buy_sell_service`.`id_BuySell_Service`" +"=" +"'"+Const.id_BuySellService+"'";


        try (Connection conn = DbConnectDataBase.connect();
             PreparedStatement PrSt = conn.prepareStatement(Select1)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_BuySell_Service(
                        ResSet.getString("directory_orders.NumberOrder_Orders"),//
                        ResSet.getString("directory_service.Name_Service"), //
                        ResSet.getInt("buy_sell_service.Amount_BuySell_Service"),//
                        ResSet.getString("DateRecording"),//
                        ResSet.getString("CounterpartyOffering"),//
                        ResSet.getString("CounterpartyBuying"),//
                        ResSet.getInt("directory_service.UnitInitialCost_Service"),
                        ResSet.getInt("directory_service.UnitWithAddedCost_Service"),
                        ResSet.getInt("TotalCost"),
                        ResSet.getString("Comment"),
                        ResSet.getInt("id"),
                        "s"
                        )
                );
            }


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Bay_Sell_Servis_Product.setItems(Table_Data);

    }

    //Вставка значений из текст-филдов в таблицу формы. ( Внесение данных из бд в таблицу) по Выбрать  продукты по номеру заказа
    private void Insert_ValuesTextField_TableView_Product () {

        //предварительная очистка обсервайбл лист
        // Table_Data.clear();

        String Select1 =
                " SELECT " +
                        "`directory_orders`.`NumberOrder_Orders`"+","+
                        "`directory_product`.`Name_Product`" + ", " +//+
                        "`buy_sell_products`.`Amount_BuySell_Products` " + "," +//+
                        // "`buy_sell_products`.`DateRecording_BuySell_Products`" + " AS " + "DateRecording"+","+
                        "("+"DATE"+"("+"`buy_sell_products`.`DateRecording_BuySell_Products`"+")" +")"+" AS " + "DateRecording"+","+

                        "`t1`.`Name_Counterparty`"+ " AS " + "CounterpartyOffering"+","+
                        "`t2`.`Name_Counterparty`"+ " AS " + "CounterpartyBuying"+","+
                        "`directory_product`.`UnitInitialCost_Product`" +","+//
                        "`directory_product`.`UnitWithAddedCost_Product`" +","+//
                        // расчетный столбец
                        "("+" `buy_sell_products`.`Amount_BuySell_Products`"+"*"+"("+"`directory_product`.`UnitInitialCost_Product`"+"+"+
                        "`directory_product`.`UnitWithAddedCost_Product`"+")"+")" + " AS " + " TotalCost"+","+
                        "`buy_sell_products`.`Additionally_BuySell_Products`"+ " AS " + "Comment"+","+
                        "`buy_sell_products`.`id_BuySell_Products`"+ " AS " + "id"+

                        " FROM " +" `buy_sell_products` "+
                        " JOIN "+ "`directory_orders`" + " ON "+ "`directory_orders`.`id_Orders`"+ "="+ "`buy_sell_products`.`id_OrderNumber_BuySell_Products`"+
                        " JOIN "+ "`directory_product`" + " ON "+ "`directory_product`.`id_Product`" +"="+ "`buy_sell_products`.`id_NameProduct_BuySell_Products`"+
                        " JOIN "+ "directory_counterparty" + " AS " +"`t1`"+ " ON "+ "`t1`.id_Counterparty" +"=" +"`buy_sell_products`.`id_ProductProvider_BuySell_Products`"+
                        " JOIN "+ "directory_counterparty" + " AS " +"`t2`"+ " ON "+ "`t2`.id_Counterparty" +"=" +"buy_sell_products.id_ProductRecipient_BuySell_Products"+

                        " WHERE " + "`buy_sell_products`.`id_BuySell_Products`" +"=" +"'"+Const.id_BuySellProducts+"'";


        try (Connection conn = DbConnectDataBase.connect();
             PreparedStatement PrSt = conn.prepareStatement(Select1)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_BuySell_Service(
                        ResSet.getString("directory_orders.NumberOrder_Orders"),//
                        ResSet.getString("directory_product.Name_Product"), //
                        ResSet.getInt("buy_sell_products.Amount_BuySell_Products"),//
                        ResSet.getString("DateRecording"),//-
                        ResSet.getString("CounterpartyOffering"),//
                        ResSet.getString("CounterpartyBuying"),//
                        ResSet.getInt("directory_product.UnitInitialCost_Product"),//-
                        ResSet.getInt("directory_product.UnitWithAddedCost_Product"),
                        ResSet.getInt("TotalCost"),
                        ResSet.getString("Comment"),
                        ResSet.getInt("id"),
                        "p"
                        )
                );
            }


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Bay_Sell_Servis_Product.setItems(Table_Data);

    }


    //------------------------------------------------------------------------------------------------------------------


    // Запись значений из текст-филдов в переменные.
    private void Record_TextField_Variable() {

        if (Const.Choice_Service_Product==true) { // true - Service ,false - Product
            //переменные для заполнения БД--Service--------------------------------------------->
            _id_NameService_BuySell_Service = TextFild_Servis_Product.getText();
            _id_OrderNumber_BuySell_Service = TextField_OrderNumber.getText();

            // Преобразуем DatePicker В стринговую переменную
            LocalDate ld_DateRecords1 = DataPicker_RecordingDate.getValue();
            _DateRecording_BuySell_Service = String.valueOf(ld_DateRecords1);

            _Amount_BuySell_Service = Integer.parseInt(TextFild_Amount.getText());
            _id_ServiceProvider_BuySell_Service = TextFildCounterpartyСhoice1.getText();
            _id_ServiceRecipient_BuySell_Service = TextFildCounterpartyСhoice2.getText();
            _Additionally_BuySell_Service = TextAreaComment.getText();
            //переменные  для заполнения БД-----------------------------------------------<
        }

        if (Const.Choice_Service_Product==false) { // true - Service ,false - Product
            //переменные для заполнения БД--Products--------------------------------------------->
            _id_NameProduct_BuySell_Products = TextFild_Servis_Product.getText();
            _id_OrderNumber_BuySell_Products = TextField_OrderNumber.getText();

            // Преобразуем DatePicker В стринговую переменную
            LocalDate ld_DateRecords2 = DataPicker_RecordingDate.getValue();
            _DateRecording_BuySell_Products = String.valueOf(ld_DateRecords2);

            _Amount_BuySell_Products = Integer.parseInt(TextFild_Amount.getText());
            _id_ProductProvider_BuySell_Products = TextFildCounterpartyСhoice1.getText();
            _id_ProductRecipient_BuySell_Products = TextFildCounterpartyСhoice2.getText();
            _Additionally_BuySell_Products = TextAreaComment.getText();
            //переменные  для заполнения БД-----------------------------------------------<
        }

    }

    //------------------------------------------------------------------------------------------------------------------


    //расчсет итоговых цен по контрагенту, добавочной цены и общей цены
    private void  Сalculation_TotalCost () {

        int TotalCost_plus=0;
        int AddedCost_plus=0;
        int Payments_plus=0;

        int n_Row = 0;//количество строк в таблице

        // узнаём количество строк в таблице:
        ObservableList<Table_BuySell_Service> test = TableView_Bay_Sell_Servis_Product.getItems();
        ListIterator<Table_BuySell_Service> test1 = test.listIterator();
        while(test1.hasNext()) {
            n_Row++;
            test1.next();
        }

      //  TableView_Bay_Sell_Servis_Product.getSelectionModel().selectFirst();
        for (int i =0 ; i < n_Row;i++ ){

          //  Выбор номера строки
        TableView_Bay_Sell_Servis_Product.getSelectionModel().select(i);

        //создание атрибута модели
        Table_BuySell_Service selectedCost = TableView_Bay_Sell_Servis_Product.getSelectionModel().getSelectedItem();

            int TotalCost=0;
            int AddedCost=0;
            int Payments=0;

            // получение значения столбца на пересечении с выбранной строкой
            Payments = selectedCost.getColumn7();
            AddedCost = selectedCost.getColumn8();
            TotalCost = selectedCost.getColumn9();

            // складываем значения ячеек в столбцах расчетных
             TotalCost_plus=TotalCost_plus+TotalCost;
             AddedCost_plus=AddedCost_plus+AddedCost;
             Payments_plus=Payments_plus+Payments;

        }
        // запись результата в текстфилд
        TextFild_TotalPaymentCounterparty.setText(String.valueOf(Payments_plus));
        TextFild_TotalAddedCost.setText(String.valueOf(AddedCost_plus));
        TextFild_TotalCost.setText(String.valueOf(TotalCost_plus));

    }





    // Инициализация таблицы адресатов сo столбцами.
    private void InitializationTable() {
        ColumnOrderNumber.setCellValueFactory(new PropertyValueFactory<Table_BuySell_Service, String>("Column1"));
        Column_Service_Product.setCellValueFactory(new PropertyValueFactory<Table_BuySell_Service, String>("Column2"));
        ColumnAmount.setCellValueFactory(new PropertyValueFactory<Table_BuySell_Service, Integer>("Column3"));
        Column_DatePurchase.setCellValueFactory(new PropertyValueFactory<Table_BuySell_Service, String>("Column4"));
        ColumnCounterpartyOffering.setCellValueFactory(new PropertyValueFactory<Table_BuySell_Service, String>("Column5"));
        ColumnCounterpartyBuying.setCellValueFactory(new PropertyValueFactory<Table_BuySell_Service, String>("Column6"));
        ColumnPayments.setCellValueFactory(new PropertyValueFactory<Table_BuySell_Service, Integer>("Column7"));
        ColumnAddedCost.setCellValueFactory(new PropertyValueFactory<Table_BuySell_Service, Integer>("Column8"));
        ColumnTotalCost.setCellValueFactory(new PropertyValueFactory<Table_BuySell_Service, Integer>("Column9"));
        ColumnComment.setCellValueFactory(new PropertyValueFactory<Table_BuySell_Service, String>("Column10"));
        ColumnId.setCellValueFactory(new PropertyValueFactory<Table_BuySell_Service, Integer>("Column11"));
        Column_select_s_p.setCellValueFactory(new PropertyValueFactory<Table_BuySell_Service, String>("Column12"));
    }


    //------------------------------------------------------------------------------------------------------------------

    //Часть кода ( Внесение данных из бд в таблицу) выборка продуктов заказа
    private void SelectPrTaskYear1_Product () {
        //предварительная очистка обсервайбл лист
        Table_Data.clear();

        String Select1 =

        " SELECT " +
"`directory_orders`.`NumberOrder_Orders`"+","+
"`directory_product`.`Name_Product`" + ", " +
"`buy_sell_products`.`Amount_BuySell_Products` " + "," +
// "`buy_sell_products`.`DateRecording_BuySell_Products`" + " AS " + "DateRecording"+","+
"("+"DATE"+"("+"`buy_sell_products`.`DateRecording_BuySell_Products`"+")" +")"+" AS " + "DateRecording"+","+
"`t1`.`Name_Counterparty`"+ " AS " + "CounterpartyOffering"+","+
"`t2`.`Name_Counterparty`"+ " AS " + "CounterpartyBuying"+","+
"`directory_product`.`UnitInitialCost_Product`" +","+
"`directory_product`.`UnitWithAddedCost_Product`" +","+
// расчетный столбец
"("+" `buy_sell_products`.`Amount_BuySell_Products`"+"*"+"("+"`directory_product`.`UnitInitialCost_Product`"+"+"+
                "`directory_product`.`UnitWithAddedCost_Product`"+")"+")" + " AS " + " TotalCost"+","+
"`buy_sell_products`.`Additionally_BuySell_Products`"+ " AS " + "Comment"+","+
"`buy_sell_products`.`id_BuySell_Products`"+ " AS " + "id"+

    " FROM " +" `buy_sell_products` "+ "," + "`directory_orders`"+","+ "`directory_product`" +","+
    "`directory_counterparty` "+ " AS " + "`t1`" +","+ " `directory_counterparty` "+ " AS " + "`t2`"+

    " WHERE " +
   "`directory_orders`.`id_Orders`"+ "=" +"`buy_sell_products`.`id_OrderNumber_BuySell_Products`"+ " AND "+
    "`directory_product`.`id_Product`"+ "=" + "`buy_sell_products`.`id_NameProduct_BuySell_Products`"+ " AND "+
    "`t1`.`id_Counterparty`" +"=" + "`buy_sell_products`.`id_ProductProvider_BuySell_Products`" + " AND " +
     "`t2`.`id_Counterparty`" +"="+ "`buy_sell_products`.`id_ProductRecipient_BuySell_Products`" ;


        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select1)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_BuySell_Service(
                        ResSet.getString("directory_orders.NumberOrder_Orders"),
                        ResSet.getString("directory_product.Name_Product"),
                        ResSet.getInt("buy_sell_products.Amount_BuySell_Products"),
                        ResSet.getString("DateRecording"),
                        ResSet.getString("CounterpartyOffering"),
                        ResSet.getString("CounterpartyBuying"),
                        ResSet.getInt("directory_product.UnitInitialCost_Product"),
                        ResSet.getInt("directory_product.UnitWithAddedCost_Product"),
                        ResSet.getInt("TotalCost"),
                        ResSet.getString("Comment"),
                        ResSet.getInt("id"),
                        "p"
                        )
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Bay_Sell_Servis_Product.setItems(Table_Data);

    }

    //------------------------------------------------------------------------------------------------------------------


    //Часть кода ( Внесение данных из бд в таблицу) по году/месяцу выборка продуктов заказа
    private void SelectPrTaskYear2_Product () {
        //предварительная очистка обсервайбл лист
        Table_Data.clear();

        String Select1 =

                " SELECT " +
                        "`directory_orders`.`NumberOrder_Orders`"+","+
                        "`directory_product`.`Name_Product`" + ", " +
                        "`buy_sell_products`.`Amount_BuySell_Products` " + "," +
                        //"`buy_sell_products`.`DateRecording_BuySell_Products`" + " AS " + "DateRecording"+","+
                        "("+"DATE"+"("+"`buy_sell_products`.`DateRecording_BuySell_Products`"+")" +")"+" AS " + "DateRecording"+","+

                        "`t1`.`Name_Counterparty`"+ " AS " + "CounterpartyOffering"+","+
                        "`t2`.`Name_Counterparty`"+ " AS " + "CounterpartyBuying"+","+
                        "`directory_product`.`UnitInitialCost_Product`" +","+
                        "`directory_product`.`UnitWithAddedCost_Product`" +","+
// расчетный столбец
                        "("+" `buy_sell_products`.`Amount_BuySell_Products`"+"*"+"("+"`directory_product`.`UnitInitialCost_Product`"+"+"+
                        "`directory_product`.`UnitWithAddedCost_Product`"+")"+")" + " AS " + " TotalCost"+","+
                        "`buy_sell_products`.`Additionally_BuySell_Products`"+ " AS " + "Comment"+","+
                        "`buy_sell_products`.`id_BuySell_Products`"+ " AS " + "id"+

                        " FROM " +" `buy_sell_products` "+ "," + "`directory_orders`"+","+ "`directory_product`" +","+
                        "`directory_counterparty` "+ " AS " + "`t1`" +","+ " `directory_counterparty` "+ " AS " + "`t2`"+

                        " WHERE " +
                        "`directory_orders`.`id_Orders`"+ "=" +"`buy_sell_products`.`id_OrderNumber_BuySell_Products`"+ " AND "+
                        "`directory_product`.`id_Product`"+ "=" + "`buy_sell_products`.`id_NameProduct_BuySell_Products`"+ " AND "+
                        "`t1`.`id_Counterparty`" +"=" + "`buy_sell_products`.`id_ProductProvider_BuySell_Products`" + " AND " +
                        "`t2`.`id_Counterparty`" +"="+ "`buy_sell_products`.`id_ProductRecipient_BuySell_Products`" + " AND " +
                        "`buy_sell_products`.`DateRecording_BuySell_Products`"+ " LIKE "+"'"+TextField_Select_OrderNumber.getText()+"%'";


        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select1)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_BuySell_Service(
                        ResSet.getString("directory_orders.NumberOrder_Orders"),
                        ResSet.getString("directory_product.Name_Product"),
                        ResSet.getInt("buy_sell_products.Amount_BuySell_Products"),
                        ResSet.getString("DateRecording"),
                        ResSet.getString("CounterpartyOffering"),
                        ResSet.getString("CounterpartyBuying"),
                        ResSet.getInt("directory_product.UnitInitialCost_Product"),
                        ResSet.getInt("directory_product.UnitWithAddedCost_Product"),
                        ResSet.getInt("TotalCost"),
                        ResSet.getString("Comment"),
                        ResSet.getInt("id"),
                        "p"
                        )
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Bay_Sell_Servis_Product.setItems(Table_Data);

    }

//-------------------------------------------------------------------------------------------------------------------

//    //Часть кода ( Внесение данных из бд в таблицу) выборка услуг заказа
//    private void  SelectPrTaskYear1_Service () {
//        //предварительная очистка обсервайбл лист
//        Table_Data.clear();
//
//        String Select1 =
//
//        " SELECT " +
//                "`directory_orders`.`NumberOrder_Orders`"+","+
//                "`directory_service`.`Name_Service`" + ", " +
//                "`buy_sell_service`.`Amount_BuySell_Service` " + "," +//
//                "`buy_sell_service`.`DateRecording_BuySell_Service`" + " AS " + "DateRecording"+","+
//                "`t1`.`Name_Counterparty`"+ " AS " + "CounterpartyOffering"+","+
//                "`t2`.`Name_Counterparty`"+ " AS " + "CounterpartyBuying"+","+
//                "`directory_service`.`UnitInitialCost_Service`" +","+
//                "`directory_service`.`UnitWithAddedCost_Service`" +","+
//// расчетный столбец
//                "("+" `buy_sell_service`.`Amount_BuySell_Service`"+"*"+"("+"`directory_service`.`UnitInitialCost_Service`"+"+"+
//                "`directory_service`.`UnitWithAddedCost_Service`"+")"+")" + " AS " + " TotalCost"+","+
//                "`buy_sell_service`.`Additionally_BuySell_Service`"+ " AS " + "Comment"+","+
//                "`buy_sell_service`.`id_BuySell_Service`"+ " AS " + "id"+
//
//                " FROM " +" `buy_sell_service` "+ "," + "`directory_orders`"+","+ "`directory_service`" +","+
//                "`directory_counterparty` "+ " AS " + "`t1`" +","+ " `directory_counterparty` "+ " AS " + "`t2`"+
//
//                " WHERE " +
//                "`directory_orders`.`id_Orders`"+ "=" +"`buy_sell_service`.`id_OrderNumber_BuySell_Service`"+ " AND "+
//                "`directory_service`.`id_Service`"+ "=" + "`buy_sell_service`.`id_NameService_BuySell_Service`"+ " AND "+
//                "`t1`.`id_Counterparty`" +"=" + "`buy_sell_service`.`id_ServiceProvider_BuySell_Service`" + " AND " +
//                "`t2`.`id_Counterparty`" +"="+ "`buy_sell_service`.`id_ServiceRecipient_BuySell_Service`" ;
//
//
//        try (Connection conn = DbConnectDataBase.connect();
////             ResultSet ResSet = null;
//             PreparedStatement PrSt = conn.prepareStatement(Select1)) {
//            ResultSet ResSet = PrSt.executeQuery();
//
//            while (ResSet.next()) {
//                Table_Data.add(new Table_BuySell_Service(
//                        ResSet.getString("directory_orders.NumberOrder_Orders"),//
//                        ResSet.getString("directory_service.Name_Service"), //
//                        ResSet.getInt("buy_sell_service.Amount_BuySell_Service"),//
//                        ResSet.getString("DateRecording"),//
//                        ResSet.getString("CounterpartyOffering"),//
//                        ResSet.getString("CounterpartyBuying"),//
//                        ResSet.getInt("directory_service.UnitInitialCost_Service"),
//                        ResSet.getInt("directory_service.UnitWithAddedCost_Service"),
//                        ResSet.getInt("TotalCost"),
//                        ResSet.getString("Comment"),
//                        ResSet.getInt("id"))
//                );
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        TableView_Bay_Sell_Servis_Product.setItems(Table_Data);
//
//    }

    //------------------------------------------------------------------------------------------------------------------

//    //Часть кода ( Внесение данных из бд в таблицу) по дате заказа, выборка услуг заказа
//    private void SelectPrTaskYear2_Service () {
//        //предварительная очистка обсервайбл лист
//        Table_Data.clear();
//
//        String Select1 =
//
//                " SELECT " +
//                        "`directory_orders`.`NumberOrder_Orders`"+","+
//                        "`directory_service`.`Name_Service`" + ", " +
//                        "`buy_sell_service`.`Amount_BuySell_Service` " + "," +//
//                        "`buy_sell_service`.`DateRecording_BuySell_Service`" + " AS " + "DateRecording"+","+
//                        "`t1`.`Name_Counterparty`"+ " AS " + "CounterpartyOffering"+","+
//                        "`t2`.`Name_Counterparty`"+ " AS " + "CounterpartyBuying"+","+
//                        "`directory_service`.`UnitInitialCost_Service`" +","+
//                        "`directory_service`.`UnitWithAddedCost_Service`" +","+
//// расчетный столбец
//                        "("+" `buy_sell_service`.`Amount_BuySell_Service`"+"*"+"("+"`directory_service`.`UnitInitialCost_Service`"+"+"+
//                        "`directory_service`.`UnitWithAddedCost_Service`"+")"+")" + " AS " + " TotalCost"+","+
//                        "`buy_sell_service`.`Additionally_BuySell_Service`"+ " AS " + "Comment"+","+
//                        "`buy_sell_service`.`id_BuySell_Service`"+ " AS " + "id"+
//
//                        " FROM " +" `buy_sell_service` "+ "," + "`directory_orders`"+","+ "`directory_service`" +","+
//                        "`directory_counterparty` "+ " AS " + "`t1`" +","+ " `directory_counterparty` "+ " AS " + "`t2`"+
//
//                        " WHERE " +
//                        "`directory_orders`.`id_Orders`"+ "=" +"`buy_sell_service`.`id_OrderNumber_BuySell_Service`"+ " AND "+
//                        "`directory_service`.`id_Service`"+ "=" + "`buy_sell_service`.`id_NameService_BuySell_Service`"+ " AND "+
//                        "`t1`.`id_Counterparty`" +"=" + "`buy_sell_service`.`id_ServiceProvider_BuySell_Service`" + " AND " +
//                        "`t2`.`id_Counterparty`" +"="+ "`buy_sell_service`.`id_ServiceRecipient_BuySell_Service`"+ " AND " +
//                        "`buy_sell_service`.`DateRecording_BuySell_Service`"+ " LIKE "+"'"+TextField_Select_OrderNumber.getText()+"%'" ;
//
//
//        try (Connection conn = DbConnectDataBase.connect();
////             ResultSet ResSet = null;
//             PreparedStatement PrSt = conn.prepareStatement(Select1)) {
//            ResultSet ResSet = PrSt.executeQuery();
//
//            while (ResSet.next()) {
//                Table_Data.add(new Table_BuySell_Service(
//                        ResSet.getString("directory_orders.NumberOrder_Orders"),//
//                        ResSet.getString("directory_service.Name_Service"), //
//                        ResSet.getInt("buy_sell_service.Amount_BuySell_Service"),//
//                        ResSet.getString("DateRecording"),//
//                        ResSet.getString("CounterpartyOffering"),//
//                        ResSet.getString("CounterpartyBuying"),//
//                        ResSet.getInt("directory_service.UnitInitialCost_Service"),
//                        ResSet.getInt("directory_service.UnitWithAddedCost_Service"),
//                        ResSet.getInt("TotalCost"),
//                        ResSet.getString("Comment"),
//                        ResSet.getInt("id"))
//                );
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        TableView_Bay_Sell_Servis_Product.setItems(Table_Data);
//
//    }

    //------------------------------------------------------------------------------------------------------------------

    //*************************************************************************************************************************

    //------------------------------------------------------------------------------------------------------------------
    //выборка по услугам-------------------------------------------------------------------------------------------------------------


    //Внесение данных из бд в таблицу по : Выбрать услуги по КонтрАгенту предлаг. услугу  и времен. периоду
    private void SelectService_CounterpartyOffer_TimePeriod_2 () {
        //предварительная очистка обсервайбл лист
        //преобразование даты в текст для выборки sql________DataPicker_TimePeriod_End
        LocalDate ld_BeginningPeriod = DataPicker_TimePeriod_Start.getValue();
        LocalDate ld_EndPeriod = DataPicker_TimePeriod_End.getValue();
        String st_BeginningPeriod =ld_BeginningPeriod.toString();
        String st_EndPeriod =ld_EndPeriod.toString();

        //КонтрАгент предлаг. услугу
        String _CounterpartyOffering_ = TextFildCounterpartyСhoice1.getText();

        String Select1 =

                " SELECT " +
                        "`directory_orders`.`NumberOrder_Orders`"+","+
                        "`directory_service`.`Name_Service`" + ", " +
                        "`buy_sell_service`.`Amount_BuySell_Service` " + "," +//
                        // "`buy_sell_service`.`DateRecording_BuySell_Service`" +","+
                        "("+"DATE"+"("+"`buy_sell_service`.`DateRecording_BuySell_Service`"+")" +")"+" AS " + "DateRecording"+","+

                        "`directory_counterparty`.`Name_Counterparty`"+ " AS " + "CounterpartyBuying"+","+

                        "`directory_service`.`UnitInitialCost_Service`" +","+
                        "`directory_service`.`UnitWithAddedCost_Service`" +","+
// расчетный столбец
                        "("+" `buy_sell_service`.`Amount_BuySell_Service`"+"*"+"("+"`directory_service`.`UnitInitialCost_Service`"+"+"+
                        "`directory_service`.`UnitWithAddedCost_Service`"+")"+")" + " AS " + " TotalCost"+","+
                        "`buy_sell_service`.`Additionally_BuySell_Service`"+ " AS " + "Comment"+","+
                        "`buy_sell_service`.`id_BuySell_Service`"+ " AS " + "id"+

                        " FROM " +" `buy_sell_service` "+
        " JOIN "+ "`directory_orders`" + " ON "+ "`directory_orders`.`id_Orders`"+ "="+ "`buy_sell_service`.`id_OrderNumber_BuySell_Service`"+
        " JOIN "+ "`directory_service`" + " ON "+ "`directory_service`.`id_Service`" +"="+ "`buy_sell_service`.`id_NameService_BuySell_Service`"+
        " JOIN "+ "directory_counterparty" + " ON "+ "directory_counterparty.id_Counterparty" +"=" +"buy_sell_service.id_ServiceRecipient_BuySell_Service"+

        " WHERE " +
                        "("+"`buy_sell_service`.`DateRecording_BuySell_Service`"+" BETWEEN"+  "'"+ st_BeginningPeriod+"'"
                        + "AND"+ "'"+ st_EndPeriod +"'"+")" + " AND "+

                        "buy_sell_service.id_ServiceProvider_BuySell_Service" +"=" + "(" + " SELECT "+ "`id_Counterparty`"+" FROM "+"`directory_counterparty`" +
                        " WHERE " +"`Name_Counterparty`"+" =" +"'"+_CounterpartyOffering_+"'" + ")"  ;


        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select1)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_BuySell_Service(
                        ResSet.getString("directory_orders.NumberOrder_Orders"),//
                        ResSet.getString("directory_service.Name_Service"), //
                        ResSet.getInt("buy_sell_service.Amount_BuySell_Service"),//
                        ResSet.getString("DateRecording"),//
                        _CounterpartyOffering_,//
                        ResSet.getString("CounterpartyBuying"),//
                        ResSet.getInt("directory_service.UnitInitialCost_Service"),
                        ResSet.getInt("directory_service.UnitWithAddedCost_Service"),
                        ResSet.getInt("TotalCost"),
                        ResSet.getString("Comment"),
                        ResSet.getInt("id"),
                        "s"
                        )
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Bay_Sell_Servis_Product.setItems(Table_Data);

    }

    //------------------------------------------------------------------------------------------------------------------


    //Внесение данных из бд в таблицу по :  Выбрать услуги по КонтрАгенту предлаг. и номеру заказа
    private void SelectService_CounterpartyOffer_OrderNumber_2 () {
        System.out.println("Step 2");
        //предварительная очистка обсервайбл лист
        Table_Data.clear();

        String _CounterpartyOffering_ = TextFildCounterpartyСhoice1.getText();

        String Select1 =

                " SELECT " +
                        "`directory_orders`.`NumberOrder_Orders`"+","+
                        "`directory_service`.`Name_Service`" + ", " +
                        "`buy_sell_service`.`Amount_BuySell_Service` " + "," +//
                        //"`buy_sell_service`.`DateRecording_BuySell_Service`" +","+
                        "("+"DATE"+"("+"`buy_sell_service`.`DateRecording_BuySell_Service`"+")" +")"+" AS " + "DateRecording"+","+

                        "`directory_counterparty`.`Name_Counterparty`"+ " AS " + "CounterpartyBuying"+","+

                        "`directory_service`.`UnitInitialCost_Service`" +","+
                        "`directory_service`.`UnitWithAddedCost_Service`" +","+
// расчетный столбец
                        "("+" `buy_sell_service`.`Amount_BuySell_Service`"+"*"+"("+"`directory_service`.`UnitInitialCost_Service`"+"+"+
                        "`directory_service`.`UnitWithAddedCost_Service`"+")"+")" + " AS " + " TotalCost"+","+
                        "`buy_sell_service`.`Additionally_BuySell_Service`"+ " AS " + "Comment"+","+
                        "`buy_sell_service`.`id_BuySell_Service`"+ " AS " + "id"+

                        " FROM " +" `buy_sell_service` "+
                        " JOIN "+ "`directory_orders`" + " ON "+ "`directory_orders`.`id_Orders`"+ "="+ "`buy_sell_service`.`id_OrderNumber_BuySell_Service`"+
                        " JOIN "+ "`directory_service`" + " ON "+ "`directory_service`.`id_Service`" +"="+ "`buy_sell_service`.`id_NameService_BuySell_Service`"+
                        " JOIN "+ "directory_counterparty" + " ON "+ "directory_counterparty.id_Counterparty" +"=" +"buy_sell_service.id_ServiceRecipient_BuySell_Service"+

                        " WHERE " +
                        "`directory_orders`.`NumberOrder_Orders`" +"=" +"'"+TextField_OrderNumber.getText()+"'" + " AND "+
                        "buy_sell_service.id_ServiceProvider_BuySell_Service" +"=" + "(" + " SELECT "+ "`id_Counterparty`"+" FROM "+"`directory_counterparty`" +
                        " WHERE " +"`Name_Counterparty`"+" =" +"'"+_CounterpartyOffering_+"'" + ")"  ;



        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select1)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_BuySell_Service(
                        ResSet.getString("directory_orders.NumberOrder_Orders"),//
                        ResSet.getString("directory_service.Name_Service"), //
                        ResSet.getInt("buy_sell_service.Amount_BuySell_Service"),//
                        ResSet.getString("DateRecording"),//
                        _CounterpartyOffering_,//
                        ResSet.getString("CounterpartyBuying"),//
                        ResSet.getInt("directory_service.UnitInitialCost_Service"),
                        ResSet.getInt("directory_service.UnitWithAddedCost_Service"),
                        ResSet.getInt("TotalCost"),
                        ResSet.getString("Comment"),
                        ResSet.getInt("id"),
                        "s"
                        )
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Bay_Sell_Servis_Product.setItems(Table_Data);

    }

    //------------------------------------------------------------------------------------------------------------------
    //Внесение данных из бд в таблицу по : Выбрать услуги по КонтрАгенту получающ. услугу  и времен. периоду
    private void SelectService_CounterpartyBay_TimePeriod_2 () {

        //преобразование даты в текст для выборки sql________DataPicker_TimePeriod_End
        LocalDate ld_BeginningPeriod = DataPicker_TimePeriod_Start.getValue();
        LocalDate ld_EndPeriod = DataPicker_TimePeriod_End.getValue();
        String st_BeginningPeriod =ld_BeginningPeriod.toString();
        String st_EndPeriod =ld_EndPeriod.toString();

        //КонтрАгент предлаг. услугу
        String _CounterpartyBuying_ = TextFildCounterpartyСhoice2.getText();

        String Select1 =

                " SELECT " +
                        "`directory_orders`.`NumberOrder_Orders`"+","+
                        "`directory_service`.`Name_Service`" + ", " +
                        "`buy_sell_service`.`Amount_BuySell_Service` " + "," +//
                        //"`buy_sell_service`.`DateRecording_BuySell_Service`" +","+
                        "("+"DATE"+"("+"`buy_sell_service`.`DateRecording_BuySell_Service`"+")" +")"+" AS " + "DateRecording"+","+

                        "`directory_counterparty`.`Name_Counterparty`"+ " AS " + "CounterpartyOffering"+","+

                        "`directory_service`.`UnitInitialCost_Service`" +","+
                        "`directory_service`.`UnitWithAddedCost_Service`" +","+
// расчетный столбец
                        "("+" `buy_sell_service`.`Amount_BuySell_Service`"+"*"+"("+"`directory_service`.`UnitInitialCost_Service`"+"+"+
                        "`directory_service`.`UnitWithAddedCost_Service`"+")"+")" + " AS " + " TotalCost"+","+
                        "`buy_sell_service`.`Additionally_BuySell_Service`"+ " AS " + "Comment"+","+
                        "`buy_sell_service`.`id_BuySell_Service`"+ " AS " + "id"+

                        " FROM " +" `buy_sell_service` "+
                        " JOIN "+ "`directory_orders`" + " ON "+ "`directory_orders`.`id_Orders`"+ "="+ "`buy_sell_service`.`id_OrderNumber_BuySell_Service`"+
                        " JOIN "+ "`directory_service`" + " ON "+ "`directory_service`.`id_Service`" +"="+ "`buy_sell_service`.`id_NameService_BuySell_Service`"+
                        " JOIN "+ "directory_counterparty" + " ON "+ "directory_counterparty.id_Counterparty" +"=" +"buy_sell_service.id_ServiceProvider_BuySell_Service"+

                        " WHERE " +
                        "("+"`buy_sell_service`.`DateRecording_BuySell_Service`"+" BETWEEN"+  "'"+ st_BeginningPeriod+"'"
                        + "AND"+ "'"+ st_EndPeriod +"'"+")" + " AND "+
                        "buy_sell_service.id_ServiceRecipient_BuySell_Service" +"=" + "(" + " SELECT "+ "`id_Counterparty`"+" FROM "+"`directory_counterparty`" +
                        " WHERE " +"`Name_Counterparty`"+" =" +"'"+_CounterpartyBuying_+"'" + ")"  ;


        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select1)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_BuySell_Service(
                        ResSet.getString("directory_orders.NumberOrder_Orders"),//
                        ResSet.getString("directory_service.Name_Service"), //
                        ResSet.getInt("buy_sell_service.Amount_BuySell_Service"),//
                        ResSet.getString("DateRecording"),//
                        ResSet.getString("CounterpartyOffering"),//
                        _CounterpartyBuying_,//
                        ResSet.getInt("directory_service.UnitInitialCost_Service"),
                        ResSet.getInt("directory_service.UnitWithAddedCost_Service"),
                        ResSet.getInt("TotalCost"),
                        ResSet.getString("Comment"),
                        ResSet.getInt("id"),
                        "s"
                        )
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Bay_Sell_Servis_Product.setItems(Table_Data);

    }

    //------------------------------------------------------------------------------------------------------------------


    //Внесение данных из бд в таблицу по :  Выбрать услуги по КонтрАгенту получающему. и номеру заказа
    private void SelectService_CounterpartyBay_OrderNumber_2 () {


        //КонтрАгент покупающ. услугу
        String _CounterpartyBuying_ = TextFildCounterpartyСhoice2.getText();

        String Select1 =

                " SELECT " +
                        "`directory_orders`.`NumberOrder_Orders`"+","+
                        "`directory_service`.`Name_Service`" + ", " +
                        "`buy_sell_service`.`Amount_BuySell_Service` " + "," +//
                        //"`buy_sell_service`.`DateRecording_BuySell_Service`" +","+
                        "("+"DATE"+"("+"`buy_sell_service`.`DateRecording_BuySell_Service`"+")" +")"+" AS " + "DateRecording"+","+

                        "`directory_counterparty`.`Name_Counterparty`"+ " AS " + "CounterpartyOffering"+","+

                        "`directory_service`.`UnitInitialCost_Service`" +","+
                        "`directory_service`.`UnitWithAddedCost_Service`" +","+
// расчетный столбец
                        "("+" `buy_sell_service`.`Amount_BuySell_Service`"+"*"+"("+"`directory_service`.`UnitInitialCost_Service`"+"+"+
                        "`directory_service`.`UnitWithAddedCost_Service`"+")"+")" + " AS " + " TotalCost"+","+
                        "`buy_sell_service`.`Additionally_BuySell_Service`"+ " AS " + "Comment"+","+
                        "`buy_sell_service`.`id_BuySell_Service`"+ " AS " + "id"+

                        " FROM " +" `buy_sell_service` "+
                        " JOIN "+ "`directory_orders`" + " ON "+ "`directory_orders`.`id_Orders`"+ "="+ "`buy_sell_service`.`id_OrderNumber_BuySell_Service`"+
                        " JOIN "+ "`directory_service`" + " ON "+ "`directory_service`.`id_Service`" +"="+ "`buy_sell_service`.`id_NameService_BuySell_Service`"+
                        " JOIN "+ "directory_counterparty" + " ON "+ "directory_counterparty.id_Counterparty" +"=" +"buy_sell_service.id_ServiceProvider_BuySell_Service"+

                        " WHERE " +
                        "`directory_orders`.`NumberOrder_Orders`" +"=" +"'"+TextField_OrderNumber.getText()+"'" + " AND "+
                        "buy_sell_service.id_ServiceRecipient_BuySell_Service" +"=" + "(" + " SELECT "+ "`id_Counterparty`"+" FROM "+"`directory_counterparty`" +
                        " WHERE " +"`Name_Counterparty`"+" =" +"'"+_CounterpartyBuying_+"'" + ")"  ;


        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select1)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_BuySell_Service(
                        ResSet.getString("directory_orders.NumberOrder_Orders"),//
                        ResSet.getString("directory_service.Name_Service"), //
                        ResSet.getInt("buy_sell_service.Amount_BuySell_Service"),//
                        ResSet.getString("DateRecording"),//
                        ResSet.getString("CounterpartyOffering"),//
                        _CounterpartyBuying_,//
                        ResSet.getInt("directory_service.UnitInitialCost_Service"),
                        ResSet.getInt("directory_service.UnitWithAddedCost_Service"),
                        ResSet.getInt("TotalCost"),
                        ResSet.getString("Comment"),
                        ResSet.getInt("id"),
                        "s"
                        )
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Bay_Sell_Servis_Product.setItems(Table_Data);

    }

//-----------------------------------------------------------------------------------------------------------------------

    //Часть кода ( Внесение данных из бд в таблицу) по Выбрать услуги по временному периоду заказов
    private void SelectService_TimePeriod () {

        //предварительная очистка обсервайбл лист
        // Table_Data.clear();

        //преобразование даты в текст для выборки sql________DataPicker_TimePeriod_End
        LocalDate ld_BeginningPeriod = DataPicker_TimePeriod_Start.getValue();
        LocalDate ld_EndPeriod = DataPicker_TimePeriod_End.getValue();
        String st_BeginningPeriod =ld_BeginningPeriod.toString();
        String st_EndPeriod =ld_EndPeriod.toString();

        String Select1 =

                " SELECT " +
                        "`directory_orders`.`NumberOrder_Orders`"+","+
                        "`directory_service`.`Name_Service`" + ", " +
                        "`buy_sell_service`.`Amount_BuySell_Service` " + "," +//
                        //"`buy_sell_service`.`DateRecording_BuySell_Service`" + " AS " + "DateRecording"+","+
                        "("+"DATE"+"("+"`buy_sell_service`.`DateRecording_BuySell_Service`"+")" +")"+" AS " + "DateRecording"+","+

                        "`t1`.`Name_Counterparty`"+ " AS " + "CounterpartyOffering"+","+
                        "`t2`.`Name_Counterparty`"+ " AS " + "CounterpartyBuying"+","+
                        "`directory_service`.`UnitInitialCost_Service`" +","+
                        "`directory_service`.`UnitWithAddedCost_Service`" +","+
// расчетный столбец
                        "("+" `buy_sell_service`.`Amount_BuySell_Service`"+"*"+"("+"`directory_service`.`UnitInitialCost_Service`"+"+"+
                        "`directory_service`.`UnitWithAddedCost_Service`"+")"+")" + " AS " + " TotalCost"+","+
                        "`buy_sell_service`.`Additionally_BuySell_Service`"+ " AS " + "Comment"+","+
                        "`buy_sell_service`.`id_BuySell_Service`"+ " AS " + "id"+

                        " FROM " +" `buy_sell_service` "+
                        " JOIN "+ "`directory_orders`" + " ON "+ "`directory_orders`.`id_Orders`"+ "="+ "`buy_sell_service`.`id_OrderNumber_BuySell_Service`"+
                        " JOIN "+ "`directory_service`" + " ON "+ "`directory_service`.`id_Service`" +"="+ "`buy_sell_service`.`id_NameService_BuySell_Service`"+
                        " JOIN "+ "directory_counterparty" + " AS " +"`t1`"+ " ON "+ "`t1`.id_Counterparty" +"=" +"buy_sell_service.id_ServiceProvider_BuySell_Service"+
                        " JOIN "+ "directory_counterparty" + " AS " +"`t2`"+ " ON "+ "`t2`.id_Counterparty" +"=" +"buy_sell_service.id_ServiceRecipient_BuySell_Service"+

                        " WHERE " +
 "("+"`buy_sell_service`.`DateRecording_BuySell_Service`"+" BETWEEN"+
                        "'"+ st_BeginningPeriod+"'" + "AND"+ "'"+ st_EndPeriod +"'"+")" ;


        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select1)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_BuySell_Service(
                        ResSet.getString("directory_orders.NumberOrder_Orders"),//
                        ResSet.getString("directory_service.Name_Service"), //
                        ResSet.getInt("buy_sell_service.Amount_BuySell_Service"),//
                        ResSet.getString("DateRecording"),//
                        ResSet.getString("CounterpartyOffering"),//
                        ResSet.getString("CounterpartyBuying"),//
                        ResSet.getInt("directory_service.UnitInitialCost_Service"),
                        ResSet.getInt("directory_service.UnitWithAddedCost_Service"),
                        ResSet.getInt("TotalCost"),
                        ResSet.getString("Comment"),
                        ResSet.getInt("id"),
                        "s"
                        )
                );
            }


//            while (ResSet.next()) {
//            String directory_orders_NumberOrder_Orders = ResSet.getString("directory_orders.NumberOrder_Orders");
//            String Name_Service = ResSet.getString("directory_service.Name_Service");
//            int Amount_BuySell_Service = ResSet.getInt("buy_sell_service.Amount_BuySell_Service");
//            String DateRecording = ResSet.getString("DateRecording");
//            String CounterpartyOffering = ResSet.getString("CounterpartyOffering");
//            String CounterpartyBuying = ResSet.getString("CounterpartyBuying");
//            int UnitInitialCost_Service = ResSet.getInt("directory_service.UnitInitialCost_Service");
//            int UnitWithAddedCost_Service = ResSet.getInt("directory_service.UnitWithAddedCost_Service");
//            int TotalCost = ResSet.getInt("TotalCost");
//            String Comment = ResSet.getString("Comment");
//            int id = ResSet.getInt("id");
//                System.out.println(id +" "  );
////                directory_orders_NumberOrder_Orders,Name_Service,Amount_BuySell_Service,DateRecording,
////                        CounterpartyOffering,CounterpartyBuying,UnitInitialCost_Service,UnitWithAddedCost_Service, TotalCost,Comment,id
//            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Bay_Sell_Servis_Product.setItems(Table_Data);

    }

//-----------------------------------------------------------------------------------------------------------------------

    //Часть кода ( Внесение данных из бд в таблицу) по Выбрать услуги по номеру заказа
    private void SelectService_OrderNumber () {

        //предварительная очистка обсервайбл лист
        // Table_Data.clear();

        String Select1 =

                " SELECT " +
                        "`directory_orders`.`NumberOrder_Orders`"+","+
                        "`directory_service`.`Name_Service`" + ", " +
                        "`buy_sell_service`.`Amount_BuySell_Service` " + "," +//
                       // "`buy_sell_service`.`DateRecording_BuySell_Service`" + " AS " + "DateRecording"+","+
                        "("+"DATE"+"("+"`buy_sell_service`.`DateRecording_BuySell_Service`"+")" +")"+" AS " + "DateRecording"+","+

                        "`t1`.`Name_Counterparty`"+ " AS " + "CounterpartyOffering"+","+
                        "`t2`.`Name_Counterparty`"+ " AS " + "CounterpartyBuying"+","+
                        "`directory_service`.`UnitInitialCost_Service`" +","+
                        "`directory_service`.`UnitWithAddedCost_Service`" +","+
// расчетный столбец
                        "("+" `buy_sell_service`.`Amount_BuySell_Service`"+"*"+"("+"`directory_service`.`UnitInitialCost_Service`"+"+"+
                        "`directory_service`.`UnitWithAddedCost_Service`"+")"+")" + " AS " + " TotalCost"+","+
                        "`buy_sell_service`.`Additionally_BuySell_Service`"+ " AS " + "Comment"+","+
                        "`buy_sell_service`.`id_BuySell_Service`"+ " AS " + "id"+

                        " FROM " +" `buy_sell_service` "+
                        " JOIN "+ "`directory_orders`" + " ON "+ "`directory_orders`.`id_Orders`"+ "="+ "`buy_sell_service`.`id_OrderNumber_BuySell_Service`"+
                        " JOIN "+ "`directory_service`" + " ON "+ "`directory_service`.`id_Service`" +"="+ "`buy_sell_service`.`id_NameService_BuySell_Service`"+
                        " JOIN "+ "directory_counterparty" + " AS " +"`t1`"+ " ON "+ "`t1`.id_Counterparty" +"=" +"buy_sell_service.id_ServiceProvider_BuySell_Service"+
                        " JOIN "+ "directory_counterparty" + " AS " +"`t2`"+ " ON "+ "`t2`.id_Counterparty" +"=" +"buy_sell_service.id_ServiceRecipient_BuySell_Service"+

                        " WHERE " + "`directory_orders`.`NumberOrder_Orders`" +"=" +"'"+TextField_OrderNumber.getText()+"'";


        try (Connection conn = DbConnectDataBase.connect();
             PreparedStatement PrSt = conn.prepareStatement(Select1)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_BuySell_Service(
                        ResSet.getString("directory_orders.NumberOrder_Orders"),//
                        ResSet.getString("directory_service.Name_Service"), //
                        ResSet.getInt("buy_sell_service.Amount_BuySell_Service"),//
                        ResSet.getString("DateRecording"),//
                        ResSet.getString("CounterpartyOffering"),//
                        ResSet.getString("CounterpartyBuying"),//
                        ResSet.getInt("directory_service.UnitInitialCost_Service"),
                        ResSet.getInt("directory_service.UnitWithAddedCost_Service"),
                        ResSet.getInt("TotalCost"),
                        ResSet.getString("Comment"),
                        ResSet.getInt("id"),
                        "s"
                        )
                );
            }


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Bay_Sell_Servis_Product.setItems(Table_Data);

    }

//выборка по услугам-------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------

    //*************************************************************************************************************************

//-----------------------------------------------------------------------------------------------------------------------
//выборка по продуктам-----------------------------------------------------------------------------------------------------------------------

    //Внесение данных из бд в таблицу по : Выбрать продукты по КонтрАгенту предлаг. и времен. периоду
private void SelectProduсt_CounterpartyOffer_TimePeriod_2 () {

    //преобразование даты в текст для выборки sql________DataPicker_TimePeriod_End
    LocalDate ld_BeginningPeriod = DataPicker_TimePeriod_Start.getValue();
    LocalDate ld_EndPeriod = DataPicker_TimePeriod_End.getValue();
    String st_BeginningPeriod =ld_BeginningPeriod.toString();
    String st_EndPeriod =ld_EndPeriod.toString();

    //КонтрАгент предлаг. услугу
    String _CounterpartyOffering_ = TextFildCounterpartyСhoice1.getText();

    String Select1 =

            " SELECT " +
                    "`directory_orders`.`NumberOrder_Orders`"+","+
                    "`directory_product`.`Name_Product`" + ", " +//+
                    "`buy_sell_products`.`Amount_BuySell_Products` " + "," +//+
                    //"`buy_sell_products`.`DateRecording_BuySell_Products`" +","+//+
                    "("+"DATE"+"("+"`buy_sell_products`.`DateRecording_BuySell_Products`"+")" +")"+" AS " + "DateRecording"+","+
                    "`directory_counterparty`.`Name_Counterparty`"+ " AS " + "CounterpartyBuying"+","+

                    "`directory_product`.`UnitInitialCost_Product`" +","+//
                    "`directory_product`.`UnitWithAddedCost_Product`" +","+//
// расчетный столбец
                    "("+" `buy_sell_products`.`Amount_BuySell_Products`"+"*"+"("+"`directory_product`.`UnitInitialCost_Product`"+"+"+
                    "`directory_product`.`UnitWithAddedCost_Product`"+")"+")" + " AS " + " TotalCost"+","+
                    "`buy_sell_products`.`Additionally_BuySell_Products`"+ " AS " + "Comment"+","+
                    "`buy_sell_products`.`id_BuySell_Products`"+ " AS " + "id"+

                    " FROM " +" `buy_sell_products` "+
                    " JOIN "+ "`directory_orders`" + " ON "+ "`directory_orders`.`id_Orders`"+ "="+ "`buy_sell_products`.`id_OrderNumber_BuySell_Products`"+
                    " JOIN "+ "`directory_product`" + " ON "+ "`directory_product`.`id_Product`" +"="+ "`buy_sell_products`.`id_NameProduct_BuySell_Products`"+
                    " JOIN "+ "directory_counterparty" + " ON "+ "directory_counterparty.id_Counterparty" +"=" +"`buy_sell_products`.`id_ProductRecipient_BuySell_Products`"+

                    " WHERE " +
                    "("+"`buy_sell_products`.`DateRecording_BuySell_Products`"+" BETWEEN"+  "'"+ st_BeginningPeriod+"'"
                    + "AND"+ "'"+ st_EndPeriod +"'"+")" + " AND "+

                    "`buy_sell_products`.`id_ProductProvider_BuySell_Products`" +"=" + "(" + " SELECT "+ "`id_Counterparty`"+" FROM "+"`directory_counterparty`" +
                    " WHERE " +"`Name_Counterparty`"+" =" +"'"+_CounterpartyOffering_+"'" + ")"  ;


    try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
         PreparedStatement PrSt = conn.prepareStatement(Select1)) {
        ResultSet ResSet = PrSt.executeQuery();

        while (ResSet.next()) {
            Table_Data.add(new Table_BuySell_Service(
                    ResSet.getString("directory_orders.NumberOrder_Orders"),//+
                    ResSet.getString("directory_product.Name_Product"), //+
                    ResSet.getInt("buy_sell_products.Amount_BuySell_Products"),//+
                    ResSet.getString("DateRecording"),//+
                    _CounterpartyOffering_,//+
                    ResSet.getString("CounterpartyBuying"),//+
                    ResSet.getInt("directory_product.UnitInitialCost_Product"),//
                    ResSet.getInt("directory_product.UnitWithAddedCost_Product"),//
                    ResSet.getInt("TotalCost"),
                    ResSet.getString("Comment"),
                    ResSet.getInt("id"),
                    "p"
                    )
            );
        }
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
    TableView_Bay_Sell_Servis_Product.setItems(Table_Data);

}

    //------------------------------------------------------------------------------------------------------------------

    //Внесение данных из бд в таблицу по :  Выбрать продукты по КонтрАгенту предлаг. и номеру заказа
    private void SelectProduсt_CounterpartyOffer_OrderNumber_2 () {
        System.out.println("Step 2");
        //предварительная очистка обсервайбл лист
        //Table_Data.clear();

        String _CounterpartyOffering_ = TextFildCounterpartyСhoice1.getText();

        String Select1 =

                        " SELECT " +
                        "`directory_orders`.`NumberOrder_Orders`"+","+
                        "`directory_product`.`Name_Product`" + ", " +//+
                        "`buy_sell_products`.`Amount_BuySell_Products` " + "," +//+
                        //"`buy_sell_products`.`DateRecording_BuySell_Products`" +","+//+
                        "("+"DATE"+"("+"`buy_sell_products`.`DateRecording_BuySell_Products`"+")" +")"+" AS " + "DateRecording"+","+

                                "`directory_counterparty`.`Name_Counterparty`"+ " AS " + "CounterpartyBuying"+","+

                        "`directory_product`.`UnitInitialCost_Product`" +","+//
                        "`directory_product`.`UnitWithAddedCost_Product`" +","+//
// расчетный столбец
                        "("+" `buy_sell_products`.`Amount_BuySell_Products`"+"*"+"("+"`directory_product`.`UnitInitialCost_Product`"+"+"+
                        "`directory_product`.`UnitWithAddedCost_Product`"+")"+")" + " AS " + " TotalCost"+","+
                        "`buy_sell_products`.`Additionally_BuySell_Products`"+ " AS " + "Comment"+","+
                        "`buy_sell_products`.`id_BuySell_Products`"+ " AS " + "id"+

                        " FROM " +" `buy_sell_products` "+
                        " JOIN "+ "`directory_orders`" + " ON "+ "`directory_orders`.`id_Orders`"+ "="+ "`buy_sell_products`.`id_OrderNumber_BuySell_Products`"+
                        " JOIN "+ "`directory_product`" + " ON "+ "`directory_product`.`id_Product`" +"="+ "`buy_sell_products`.`id_NameProduct_BuySell_Products`"+
                        " JOIN "+ "directory_counterparty" + " ON "+ "directory_counterparty.id_Counterparty" +"=" +"`buy_sell_products`.`id_ProductRecipient_BuySell_Products`"+

                        " WHERE " +
                        "`directory_orders`.`NumberOrder_Orders`" +"=" +"'"+TextField_OrderNumber.getText()+"'" + " AND "+
                        "`buy_sell_products`.`id_ProductProvider_BuySell_Products`" +"=" + "(" + " SELECT "+ "`id_Counterparty`"+" FROM "+"`directory_counterparty`" +
                        " WHERE " +"`Name_Counterparty`"+" =" +"'"+_CounterpartyOffering_+"'" + ")"  ;


        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select1)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_BuySell_Service(
                        ResSet.getString("directory_orders.NumberOrder_Orders"),//
                        ResSet.getString("directory_product.Name_Product"), //
                        ResSet.getInt("buy_sell_products.Amount_BuySell_Products"),//
                        ResSet.getString("DateRecording"),//
                        _CounterpartyOffering_,//
                        ResSet.getString("CounterpartyBuying"),//
                        ResSet.getInt("directory_product.UnitInitialCost_Product"),
                        ResSet.getInt("directory_product.UnitWithAddedCost_Product"),
                        ResSet.getInt("TotalCost"),
                        ResSet.getString("Comment"),
                        ResSet.getInt("id"),
                        "p"
                        )
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Bay_Sell_Servis_Product.setItems(Table_Data);
    }

    //------------------------------------------------------------------------------------------------------------------
    //Внесение данных из бд в таблицу по : Выбрать продукты по КонтрАгенту получающ. услугу  и времен. периоду
    private void SelectProduсt_CounterpartyBay_TimePeriod_2 () {

        //преобразование даты в текст для выборки sql________DataPicker_TimePeriod_End
        LocalDate ld_BeginningPeriod = DataPicker_TimePeriod_Start.getValue();
        LocalDate ld_EndPeriod = DataPicker_TimePeriod_End.getValue();
        String st_BeginningPeriod =ld_BeginningPeriod.toString();
        String st_EndPeriod =ld_EndPeriod.toString();

        //КонтрАгент предлаг. услугу
        String _CounterpartyBuying_ = TextFildCounterpartyСhoice2.getText();

        String Select1 =
                        " SELECT " +
                        "`directory_orders`.`NumberOrder_Orders`"+","+
                        "`directory_product`.`Name_Product`" + ", " +//+
                        "`buy_sell_products`.`Amount_BuySell_Products` " + "," +//+
                       // "`buy_sell_products`.`DateRecording_BuySell_Products`" +","+//+
                        "("+"DATE"+"("+"`buy_sell_products`.`DateRecording_BuySell_Products`"+")" +")"+" AS " + "DateRecording"+","+

                        "`directory_counterparty`.`Name_Counterparty`"+ " AS " + "CounterpartyOffering"+","+
                        "`directory_product`.`UnitInitialCost_Product`" +","+//
                        "`directory_product`.`UnitWithAddedCost_Product`" +","+//
// расчетный столбец
                        "("+" `buy_sell_products`.`Amount_BuySell_Products`"+"*"+"("+"`directory_product`.`UnitInitialCost_Product`"+"+"+
                        "`directory_product`.`UnitWithAddedCost_Product`"+")"+")" + " AS " + " TotalCost"+","+
                        "`buy_sell_products`.`Additionally_BuySell_Products`"+ " AS " + "Comment"+","+
                        "`buy_sell_products`.`id_BuySell_Products`"+ " AS " + "id"+

                        " FROM " +" `buy_sell_products` "+
                        " JOIN "+ "`directory_orders`" + " ON "+ "`directory_orders`.`id_Orders`"+ "="+ "`buy_sell_products`.`id_OrderNumber_BuySell_Products`"+
                        " JOIN "+ "`directory_product`" + " ON "+ "`directory_product`.`id_Product`" +"="+ "`buy_sell_products`.`id_NameProduct_BuySell_Products`"+
                        " JOIN "+ "directory_counterparty" + " ON "+ "directory_counterparty.id_Counterparty" +"=" +"`buy_sell_products`.id_ProductProvider_BuySell_Products"+

                                " WHERE " +
                        "("+"`buy_sell_products`.`DateRecording_BuySell_Products`"+" BETWEEN"+  "'"+ st_BeginningPeriod+"'"
                        + "AND"+ "'"+ st_EndPeriod +"'"+")" + " AND "+
                        "`buy_sell_products`.`id_ProductRecipient_BuySell_Products`" +"=" + "(" + " SELECT "+ "`id_Counterparty`"+" FROM "+"`directory_counterparty`" +
                        " WHERE " +"`Name_Counterparty`"+" =" +"'"+_CounterpartyBuying_+"'" + ")"  ;

        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select1)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_BuySell_Service(
                        ResSet.getString("directory_orders.NumberOrder_Orders"),//
                        ResSet.getString("directory_product.Name_Product"), //
                        ResSet.getInt("buy_sell_products.Amount_BuySell_Products"),//
                        ResSet.getString("DateRecording"),//
                        ResSet.getString("CounterpartyOffering"),//
                        _CounterpartyBuying_,//
                        ResSet.getInt("directory_product.UnitInitialCost_Product"),
                        ResSet.getInt("directory_product.UnitWithAddedCost_Product"),
                        ResSet.getInt("TotalCost"),
                        ResSet.getString("Comment"),
                        ResSet.getInt("id"),
                        "p"
                        )
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Bay_Sell_Servis_Product.setItems(Table_Data);

    }

    //------------------------------------------------------------------------------------------------------------------


    //Внесение данных из бд в таблицу по :  Выбрать продукты по КонтрАгенту получающему. и номеру заказа
    private void SelectProduсt_CounterpartyBay_OrderNumber_2 () {


        //КонтрАгент покупающ. услугу
        String _CounterpartyBuying_ = TextFildCounterpartyСhoice2.getText();

        String Select1 =
                        " SELECT " +
                        "`directory_orders`.`NumberOrder_Orders`"+","+
                        "`directory_product`.`Name_Product`" + ", " +//+
                        "`buy_sell_products`.`Amount_BuySell_Products` " + "," +//+
                       // "`buy_sell_products`.`DateRecording_BuySell_Products`" +","+//+
                        "("+"DATE"+"("+"`buy_sell_products`.`DateRecording_BuySell_Products`"+")" +")"+" AS " + "DateRecording"+","+

                        "`directory_counterparty`.`Name_Counterparty`"+ " AS " + "CounterpartyOffering"+","+
                        "`directory_product`.`UnitInitialCost_Product`" +","+//
                        "`directory_product`.`UnitWithAddedCost_Product`" +","+//
// расчетный столбец
                        "("+" `buy_sell_products`.`Amount_BuySell_Products`"+"*"+"("+"`directory_product`.`UnitInitialCost_Product`"+"+"+
                        "`directory_product`.`UnitWithAddedCost_Product`"+")"+")" + " AS " + " TotalCost"+","+
                        "`buy_sell_products`.`Additionally_BuySell_Products`"+ " AS " + "Comment"+","+
                        "`buy_sell_products`.`id_BuySell_Products`"+ " AS " + "id"+

                    " FROM " +" `buy_sell_products` "+
                    " JOIN "+ "`directory_orders`" + " ON "+ "`directory_orders`.`id_Orders`"+ "="+ "`buy_sell_products`.`id_OrderNumber_BuySell_Products`"+
                    " JOIN "+ "`directory_product`" + " ON "+ "`directory_product`.`id_Product`" +"="+ "`buy_sell_products`.`id_NameProduct_BuySell_Products`"+
                    " JOIN "+ "directory_counterparty" + " ON "+ "directory_counterparty.id_Counterparty" +"=" +"`buy_sell_products`.`id_ProductProvider_BuySell_Products`"+

                                " WHERE " +
                        "`directory_orders`.`NumberOrder_Orders`" +"=" +"'"+TextField_OrderNumber.getText()+"'" + " AND "+
                        "buy_sell_products.id_ProductRecipient_BuySell_Products" +"=" + "(" + " SELECT "+ "`id_Counterparty`"+" FROM "+"`directory_counterparty`" +
                        " WHERE " +"`Name_Counterparty`"+" =" +"'"+_CounterpartyBuying_+"'" + ")"  ;

        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select1)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_BuySell_Service(
                        ResSet.getString("directory_orders.NumberOrder_Orders"),//
                        ResSet.getString("directory_product.Name_Product"), //
                        ResSet.getInt("buy_sell_products.Amount_BuySell_Products"),//
                        ResSet.getString("DateRecording"),//-
                        ResSet.getString("CounterpartyOffering"),//
                        _CounterpartyBuying_,//
                        ResSet.getInt("directory_product.UnitInitialCost_Product"),
                        ResSet.getInt("directory_product.UnitWithAddedCost_Product"),
                        ResSet.getInt("TotalCost"),
                        ResSet.getString("Comment"),
                        ResSet.getInt("id"),
                        "p"
                        )
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Bay_Sell_Servis_Product.setItems(Table_Data);

    }

//-----------------------------------------------------------------------------------------------------------------------

    //Часть кода ( Внесение данных из бд в таблицу) Выбрать продукты по временному периоду
    private void SelectProduct_TimePeriod_2 () {

        //предварительная очистка обсервайбл лист
        // Table_Data.clear();

        //преобразование даты в текст для выборки sql________DataPicker_TimePeriod_End
        LocalDate ld_BeginningPeriod = DataPicker_TimePeriod_Start.getValue();
        LocalDate ld_EndPeriod = DataPicker_TimePeriod_End.getValue();
        String st_BeginningPeriod =ld_BeginningPeriod.toString();
        String st_EndPeriod =ld_EndPeriod.toString();

        String Select1 =


                " SELECT " +
                        "`directory_orders`.`NumberOrder_Orders`"+","+
                        "`directory_product`.`Name_Product`" + ", " +//+
                        "`buy_sell_products`.`Amount_BuySell_Products` " + "," +//+
                        //"`buy_sell_products`.`DateRecording_BuySell_Products`" + " AS " + "DateRecording"+","+
                        "("+"DATE"+"("+"`buy_sell_products`.`DateRecording_BuySell_Products`"+")" +")"+" AS " + "DateRecording"+","+

                        "`t1`.`Name_Counterparty`"+ " AS " + "CounterpartyOffering"+","+
                        "`t2`.`Name_Counterparty`"+ " AS " + "CounterpartyBuying"+","+
                        "`directory_product`.`UnitInitialCost_Product`" +","+//
                        "`directory_product`.`UnitWithAddedCost_Product`" +","+//

// расчетный столбец
                        "("+" `buy_sell_products`.`Amount_BuySell_Products`"+"*"+"("+"`directory_product`.`UnitInitialCost_Product`"+"+"+
                        "`directory_product`.`UnitWithAddedCost_Product`"+")"+")" + " AS " + " TotalCost"+","+
                        "`buy_sell_products`.`Additionally_BuySell_Products`"+ " AS " + "Comment"+","+
                        "`buy_sell_products`.`id_BuySell_Products`"+ " AS " + "id"+

                        " FROM " +" `buy_sell_products` "+
                        " JOIN "+ "`directory_orders`" + " ON "+ "`directory_orders`.`id_Orders`"+ "="+ "`buy_sell_products`.`id_OrderNumber_BuySell_Products`"+
                        " JOIN "+ "`directory_product`" + " ON "+ "`directory_product`.`id_Product`" +"="+ "`buy_sell_products`.`id_NameProduct_BuySell_Products`"+
                        " JOIN "+ "directory_counterparty" + " AS " +"`t1`"+ " ON "+ "`t1`.id_Counterparty" +"=" +"`buy_sell_products`.`id_ProductProvider_BuySell_Products`"+
                        " JOIN "+ "directory_counterparty" + " AS " +"`t2`"+ " ON "+ "`t2`.id_Counterparty" +"=" +"buy_sell_products.id_ProductRecipient_BuySell_Products"+

                        " WHERE " +
                        "("+"`buy_sell_products`.`DateRecording_BuySell_Products`"+" BETWEEN"+
                        "'"+ st_BeginningPeriod+"'" + "AND"+ "'"+ st_EndPeriod +"'"+")" ;


        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select1)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_BuySell_Service(
                        ResSet.getString("directory_orders.NumberOrder_Orders"),//
                        ResSet.getString("directory_product.Name_Product"), //-
                        ResSet.getInt("buy_sell_products.Amount_BuySell_Products"),//
                        ResSet.getString("DateRecording"),//
                        ResSet.getString("CounterpartyOffering"),//
                        ResSet.getString("CounterpartyBuying"),//
                        ResSet.getInt("directory_product.UnitInitialCost_Product"),
                        ResSet.getInt("directory_product.UnitWithAddedCost_Product"),
                        ResSet.getInt("TotalCost"),
                        ResSet.getString("Comment"),
                        ResSet.getInt("id"),
                        "p"
                        )
                );
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Bay_Sell_Servis_Product.setItems(Table_Data);

    }

//-----------------------------------------------------------------------------------------------------------------------

    //Часть кода ( Внесение данных из бд в таблицу) по Выбрать  продукты по номеру заказа
    private void SelectProduct_OrderNumber_2 () {

        //предварительная очистка обсервайбл лист
        // Table_Data.clear();

        String Select1 =
                " SELECT " +
                        "`directory_orders`.`NumberOrder_Orders`"+","+
                        "`directory_product`.`Name_Product`" + ", " +//+
                        "`buy_sell_products`.`Amount_BuySell_Products` " + "," +//+
                       // "`buy_sell_products`.`DateRecording_BuySell_Products`" + " AS " + "DateRecording"+","+
                        "("+"DATE"+"("+"`buy_sell_products`.`DateRecording_BuySell_Products`"+")" +")"+" AS " + "DateRecording"+","+

                        "`t1`.`Name_Counterparty`"+ " AS " + "CounterpartyOffering"+","+
                        "`t2`.`Name_Counterparty`"+ " AS " + "CounterpartyBuying"+","+
                        "`directory_product`.`UnitInitialCost_Product`" +","+//
                        "`directory_product`.`UnitWithAddedCost_Product`" +","+//
                        // расчетный столбец
                        "("+" `buy_sell_products`.`Amount_BuySell_Products`"+"*"+"("+"`directory_product`.`UnitInitialCost_Product`"+"+"+
                        "`directory_product`.`UnitWithAddedCost_Product`"+")"+")" + " AS " + " TotalCost"+","+
                        "`buy_sell_products`.`Additionally_BuySell_Products`"+ " AS " + "Comment"+","+
                        "`buy_sell_products`.`id_BuySell_Products`"+ " AS " + "id"+
                        
                        " FROM " +" `buy_sell_products` "+
                        " JOIN "+ "`directory_orders`" + " ON "+ "`directory_orders`.`id_Orders`"+ "="+ "`buy_sell_products`.`id_OrderNumber_BuySell_Products`"+
                        " JOIN "+ "`directory_product`" + " ON "+ "`directory_product`.`id_Product`" +"="+ "`buy_sell_products`.`id_NameProduct_BuySell_Products`"+
                        " JOIN "+ "directory_counterparty" + " AS " +"`t1`"+ " ON "+ "`t1`.id_Counterparty" +"=" +"`buy_sell_products`.`id_ProductProvider_BuySell_Products`"+
                        " JOIN "+ "directory_counterparty" + " AS " +"`t2`"+ " ON "+ "`t2`.id_Counterparty" +"=" +"buy_sell_products.id_ProductRecipient_BuySell_Products"+

                        " WHERE " + "`directory_orders`.`NumberOrder_Orders`" +"=" +"'"+TextField_OrderNumber.getText()+"'";


        try (Connection conn = DbConnectDataBase.connect();
             PreparedStatement PrSt = conn.prepareStatement(Select1)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_BuySell_Service(
                        ResSet.getString("directory_orders.NumberOrder_Orders"),//
                        ResSet.getString("directory_product.Name_Product"), //
                        ResSet.getInt("buy_sell_products.Amount_BuySell_Products"),//
                        ResSet.getString("DateRecording"),//-
                        ResSet.getString("CounterpartyOffering"),//
                        ResSet.getString("CounterpartyBuying"),//
                        ResSet.getInt("directory_product.UnitInitialCost_Product"),//-
                        ResSet.getInt("directory_product.UnitWithAddedCost_Product"),
                        ResSet.getInt("TotalCost"),
                        ResSet.getString("Comment"),
                        ResSet.getInt("id"),
                        "p"
                        )
                );
            }


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Bay_Sell_Servis_Product.setItems(Table_Data);

    }

//выборка по продуктам <-----------------------------------------------------------------------------------------------------------------------


//-----------------------------------------------------------------------------------------------------------------------

//*************************************************************************************************************************

//-----------------------------------------------------------------------------------------------------------------------


    //Вставка значений из текст-филдов в таблицу формы. ( при обновлении строки таблицы)
    private void Insert_ValuesTextField_TableView_Service_update() {

        if (TableView_Bay_Sell_Servis_Product.getSelectionModel().getSelectedItem() != null) {
            Table_BuySell_Service selectedPerson = TableView_Bay_Sell_Servis_Product.getSelectionModel().getSelectedItem();

            // запрос из БД по id
            String Select1 =
                    " SELECT " +
                            "`directory_orders`.`NumberOrder_Orders`" + "," +
                            "`directory_service`.`Name_Service`" + ", " +
                            "`buy_sell_service`.`Amount_BuySell_Service` " + "," +//
                            // "`buy_sell_service`.`DateRecording_BuySell_Service`" + " AS " + "DateRecording"+","+
                            "(" + "DATE" + "(" + "`buy_sell_service`.`DateRecording_BuySell_Service`" + ")" + ")" + " AS " + "DateRecording" + "," +

                            "`t1`.`Name_Counterparty`" + " AS " + "CounterpartyOffering" + "," +
                            "`t2`.`Name_Counterparty`" + " AS " + "CounterpartyBuying" + "," +
                            "`directory_service`.`UnitInitialCost_Service`" + "," +
                            "`directory_service`.`UnitWithAddedCost_Service`" + "," +
// расчетный столбец
                            "(" + " `buy_sell_service`.`Amount_BuySell_Service`" + "*" + "(" + "`directory_service`.`UnitInitialCost_Service`" + "+" +
                            "`directory_service`.`UnitWithAddedCost_Service`" + ")" + ")" + " AS " + " TotalCost" + "," +
                            "`buy_sell_service`.`Additionally_BuySell_Service`" + " AS " + "Comment" + "," +
                            "`buy_sell_service`.`id_BuySell_Service`" + " AS " + "id" +

                            " FROM " + " `buy_sell_service` " +
                            " JOIN " + "`directory_orders`" + " ON " + "`directory_orders`.`id_Orders`" + "=" + "`buy_sell_service`.`id_OrderNumber_BuySell_Service`" +
                            " JOIN " + "`directory_service`" + " ON " + "`directory_service`.`id_Service`" + "=" + "`buy_sell_service`.`id_NameService_BuySell_Service`" +
                            " JOIN " + "directory_counterparty" + " AS " + "`t1`" + " ON " + "`t1`.id_Counterparty" + "=" + "buy_sell_service.id_ServiceProvider_BuySell_Service" +
                            " JOIN " + "directory_counterparty" + " AS " + "`t2`" + " ON " + "`t2`.id_Counterparty" + "=" + "buy_sell_service.id_ServiceRecipient_BuySell_Service" +

                            " WHERE " + "`buy_sell_service`.`id_BuySell_Service`" + "=" + "'" + _id_del + "'";


            try (Connection conn = DbConnectDataBase.connect();
                 PreparedStatement PrSt = conn.prepareStatement(Select1)) {
                ResultSet ResSet = PrSt.executeQuery();

                while (ResSet.next()) {
                    //заполнение ячеек выделенной строки новыми значениями
                    selectedPerson.setColumn1(ResSet.getString("directory_orders.NumberOrder_Orders"));
                    selectedPerson.setColumn2(ResSet.getString("directory_service.Name_Service"));
                    selectedPerson.setColumn3(ResSet.getInt("buy_sell_service.Amount_BuySell_Service"));
                    selectedPerson.setColumn4(ResSet.getString("DateRecording"));
                    selectedPerson.setColumn5(ResSet.getString("CounterpartyOffering"));
                    selectedPerson.setColumn6(ResSet.getString("CounterpartyBuying"));
                    selectedPerson.setColumn7(ResSet.getInt("directory_service.UnitInitialCost_Service"));
                    selectedPerson.setColumn8(ResSet.getInt("directory_service.UnitWithAddedCost_Service"));
                    selectedPerson.setColumn9(ResSet.getInt("TotalCost"));
                    selectedPerson.setColumn10(ResSet.getString("Comment"));
                    selectedPerson.setColumn11(ResSet.getInt("id"));
                    selectedPerson.setColumn12("s");
                }

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            //Обновление таблицы формы
            TableView_Bay_Sell_Servis_Product.refresh();
        }
    }

    //--------------

    //Вставка значений из текст-филдов в таблицу формы.  ( при обновлении строки таблицы)
    private void Insert_ValuesTextField_TableView_Product_update () {

        if (TableView_Bay_Sell_Servis_Product.getSelectionModel().getSelectedItem() != null) {
            Table_BuySell_Service selectedPerson = TableView_Bay_Sell_Servis_Product.getSelectionModel().getSelectedItem();

            // запрос из БД по id
        String Select1 =
                " SELECT " +
                        "`directory_orders`.`NumberOrder_Orders`"+","+
                        "`directory_product`.`Name_Product`" + ", " +//+
                        "`buy_sell_products`.`Amount_BuySell_Products` " + "," +//+
                        // "`buy_sell_products`.`DateRecording_BuySell_Products`" + " AS " + "DateRecording"+","+
                        "("+"DATE"+"("+"`buy_sell_products`.`DateRecording_BuySell_Products`"+")" +")"+" AS " + "DateRecording"+","+

                        "`t1`.`Name_Counterparty`"+ " AS " + "CounterpartyOffering"+","+
                        "`t2`.`Name_Counterparty`"+ " AS " + "CounterpartyBuying"+","+
                        "`directory_product`.`UnitInitialCost_Product`" +","+//
                        "`directory_product`.`UnitWithAddedCost_Product`" +","+//
                        // расчетный столбец
                        "("+" `buy_sell_products`.`Amount_BuySell_Products`"+"*"+"("+"`directory_product`.`UnitInitialCost_Product`"+"+"+
                        "`directory_product`.`UnitWithAddedCost_Product`"+")"+")" + " AS " + " TotalCost"+","+
                        "`buy_sell_products`.`Additionally_BuySell_Products`"+ " AS " + "Comment"+","+
                        "`buy_sell_products`.`id_BuySell_Products`"+ " AS " + "id"+

                        " FROM " +" `buy_sell_products` "+
                        " JOIN "+ "`directory_orders`" + " ON "+ "`directory_orders`.`id_Orders`"+ "="+ "`buy_sell_products`.`id_OrderNumber_BuySell_Products`"+
                        " JOIN "+ "`directory_product`" + " ON "+ "`directory_product`.`id_Product`" +"="+ "`buy_sell_products`.`id_NameProduct_BuySell_Products`"+
                        " JOIN "+ "directory_counterparty" + " AS " +"`t1`"+ " ON "+ "`t1`.id_Counterparty" +"=" +"`buy_sell_products`.`id_ProductProvider_BuySell_Products`"+
                        " JOIN "+ "directory_counterparty" + " AS " +"`t2`"+ " ON "+ "`t2`.id_Counterparty" +"=" +"buy_sell_products.id_ProductRecipient_BuySell_Products"+

                        " WHERE " + "`buy_sell_products`.`id_BuySell_Products`" +"=" +"'"+_id_del+"'";


        try (Connection conn = DbConnectDataBase.connect();
             PreparedStatement PrSt = conn.prepareStatement(Select1)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {

                //заполнение ячеек выделенной строки новыми значениями
                selectedPerson.setColumn1(ResSet.getString("directory_orders.NumberOrder_Orders"));
                selectedPerson.setColumn2(ResSet.getString("directory_product.Name_Product"));
                selectedPerson.setColumn3(ResSet.getInt("buy_sell_products.Amount_BuySell_Products"));
                selectedPerson.setColumn4(ResSet.getString("DateRecording"));
                selectedPerson.setColumn5(ResSet.getString("CounterpartyOffering"));
                selectedPerson.setColumn6(ResSet.getString("CounterpartyBuying"));
                selectedPerson.setColumn7(ResSet.getInt("directory_product.UnitInitialCost_Product"));
                selectedPerson.setColumn8(ResSet.getInt("directory_product.UnitWithAddedCost_Product"));
                selectedPerson.setColumn9(ResSet.getInt("TotalCost"));
                selectedPerson.setColumn10(ResSet.getString("Comment"));
                selectedPerson.setColumn11(ResSet.getInt("id"));
                selectedPerson.setColumn12("p");

            }


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

            //Обновление таблицы формы
            TableView_Bay_Sell_Servis_Product.refresh();
        }

    }


    //------------------------------------------------------------------------------------------------------------------





}
