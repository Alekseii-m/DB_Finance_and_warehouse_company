package BD_Controller;

import BD_Common.Const;
import BD_Common.DbConnectDataBase;
import ObservListTable.Table_AddSelect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Cont_AddSelectCompany {

    private final ObservableList<Table_AddSelect> Table_Data = FXCollections.observableArrayList();


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Label_1;

    @FXML
    private Button Button_SelectSpecialization;

    @FXML
    private TextField TextField_SelectSpecialization;

    @FXML
    private TableView<Table_AddSelect> TableView_Select_Specialization;

    @FXML
    private TableColumn<Table_AddSelect, String> Column_ListSpecialization;

    @FXML
    private TableColumn<Table_AddSelect, Integer> Column_SpecializationId;

    @FXML
    private Button Button_InsertCardCounterparty;

    @FXML
    void initialize() {

        // Скрыть столбец id таблицы
        Column_SpecializationId.setVisible(false);

        //-------------------------------------------------------------------------------------------------------
        //Внесение данных из бд в таблицу
        Button_SelectSpecialization.setOnAction(event -> {
            // Инициализация таблицы адресатов сo столбцами.
            Column_ListSpecialization.setCellValueFactory(new PropertyValueFactory<Table_AddSelect, String>("Column1"));
            Column_SpecializationId.setCellValueFactory(new PropertyValueFactory<Table_AddSelect, Integer>("Column2"));

            // Выборка данных по условию если пусто-ChangSelect1, если нет ChangeSelekt2 :
            if(TextField_SelectSpecialization.getText().trim().equals(""))
            { ChangSelect1 (); }
            else {
                ChangeSelekt2 ();
            }
        });
//-------------------------------------------------------------------------------------------------------

        Button_InsertCardCounterparty.setOnAction(event -> {
            // Внесение выделенной строки таблицы в данные по сервису (name , id)
            if (TableView_Select_Specialization.getSelectionModel().getSelectedItem() != null) {
                Table_AddSelect selectedPerson = TableView_Select_Specialization.getSelectionModel().getSelectedItem();

                Const.CardCounterparty_Company_Counterparty = selectedPerson.getColumn1();
                Const.Id_Company_Counterparty = selectedPerson.getColumn2();

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
                "Company_Counterparty" +
                " FROM " +
                "bd_company.directory_counterparty";

        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select2)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_AddSelect(
                        ResSet.getString("Company_Counterparty"),
                        0)
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Select_Specialization.setItems(Table_Data);

    }

    //Внесение данных из бд в таблицу по значению в текстфилде
    public void ChangeSelekt2(){
        //предварительная очистка обсервайбл лист
        Table_Data.clear();

        String Select2 = "SELECT "+"distinct "+
                "Company_Counterparty" +
                " FROM " +
                "bd_company.directory_counterparty" +
                " WHERE " + "Company_Counterparty" +
                " LIKE " +"'"+TextField_SelectSpecialization.getText()+"%'";

        try (Connection conn = DbConnectDataBase.connect();
//             ResultSet ResSet = null;
             PreparedStatement PrSt = conn.prepareStatement(Select2)) {
            ResultSet ResSet = PrSt.executeQuery();

            while (ResSet.next()) {
                Table_Data.add(new Table_AddSelect(
                        ResSet.getString("Company_Counterparty"),
                        0)
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableView_Select_Specialization.setItems(Table_Data);
    }

//------------------------------------------------------------------------------------------------------------------




}

