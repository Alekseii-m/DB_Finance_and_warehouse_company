package sample;

import java.net.URL;
import java.util.ResourceBundle;

import BD_Common.OpenNewWindow_Class;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button_serviceCard;

    @FXML
    private Button button_productCard;

    @FXML
    private Button button_clientCard;

    @FXML
    private Button button_counterparty;

    @FXML
    private Button button_orderCard;

    @FXML
    private Button button_BaySell;

    @FXML
    private Button button_assemblyProduct;

    @FXML
    void initialize() {

        button_serviceCard.setOnAction(event -> {
            //Загрузка окна
            OpenNewWindow_Class.OpenNewWindow_ ("/BD_fxml/CardService.fxml");
        });

        button_productCard.setOnAction(event -> {
            //Загрузка окна
            OpenNewWindow_Class.OpenNewWindow_ ("/BD_fxml/CardProduct.fxml");
        });

        button_clientCard.setOnAction(event -> {
            //Загрузка окна
            OpenNewWindow_Class.OpenNewWindow_ ("/BD_fxml/CardClient.fxml");
        });

        button_counterparty.setOnAction(event -> {
            //Загрузка окна
            OpenNewWindow_Class.OpenNewWindow_ ("/BD_fxml/CardCounterparty.fxml");
        });

        button_orderCard.setOnAction(event -> {
            //Загрузка окна
            OpenNewWindow_Class.OpenNewWindow_ ("/BD_fxml/CardOrder.fxml");
        });

        button_BaySell.setOnAction(event -> {
            //Загрузка окна
            OpenNewWindow_Class.OpenNewWindow_ ("/BD_fxml/BuySell_Service.fxml");
        });

        button_assemblyProduct.setOnAction(event -> {
            //Загрузка окна
            OpenNewWindow_Class.OpenNewWindow_ ("/BD_fxml/AssemblyProducts.fxml");
        });


    }
}

