package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class Controller {

    @FXML
    private Button beginButton;

    @FXML
    private AnchorPane welcomeScreen;



    //For beginButton
    public void openMainScreen(ActionEvent actionEvent) throws Exception
    {
        AnchorPane mainScreenPane = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        welcomeScreen.getChildren().setAll(mainScreenPane);
    }

}
