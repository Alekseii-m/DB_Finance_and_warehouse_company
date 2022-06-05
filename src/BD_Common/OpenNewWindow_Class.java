package BD_Common;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class OpenNewWindow_Class {

    public static void OpenNewWindow_(String Path) {
        //Загрузка нового окна-------------------------------------------------------
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(OpenNewWindow_Class.class.getResource(Path));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        //Модальность создаю то что родительское окно не активно
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setScene(new Scene(root));
        stage.showAndWait();

    }

}
