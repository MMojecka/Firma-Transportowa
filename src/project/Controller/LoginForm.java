package project.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import project.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginForm implements Initializable {
double x=0, y=0;
static final int HBoxXMin=432;
static final int HBoxXMax=492;
static final int HBoxYMin=14;
static final int HBoxYMax=44;


    @FXML
    private AnchorPane APMain;

    @FXML
    void closeAction(MouseEvent event) {
    System.exit(0);
    }

    @FXML
    void minAction(MouseEvent event) {
     Main.stage.setIconified(true);
    }

    @FXML
    void MakeDraggable(){
        APMain.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        APMain.setOnMouseDragged(event -> {
            if(!(x>=HBoxXMin&&x<=HBoxXMax)||!(y>=HBoxYMin&&y<=HBoxYMax)) {
                Main.stage.setX(event.getScreenX() - x);
                Main.stage.setY(event.getScreenY() - y);
            }
        });
    }
    @FXML
    void login(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MainForm.fxml"));
        Parent root = loader.load();
        //MainForm mainForm = loader.getController();
        //Main.stage.initStyle(StageStyle.DECORATED);

        Scene scene = new Scene(root);
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage window = new Stage();
        window.initStyle(StageStyle.DECORATED);
        window.setScene(scene);
        window.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
MakeDraggable();
    }
}