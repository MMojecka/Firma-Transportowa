package project.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.Class.Cennik;
import project.Class.DataUtil;
import project.Class.Zlecenie;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class CurentOrderForm extends DataUtil implements Initializable {
    private String st;
    private Socket s;
    private InetAddress ip;
    private DataInputStream dis;
    private DataOutputStream dos;
    private int counter,id;
    private String stat,kurier;
    @FXML
    public Label name;


    @FXML
    public Label clientType;
    @FXML
    private AnchorPane APMain;
    @FXML
    TableView<Zlecenie> CurrentOrder;
    @FXML
    private javafx.scene.control.TableColumn<Cennik, Integer> OrderNumber;
    @FXML
    private javafx.scene.control.TableColumn<Cennik, String> Status;
    @FXML
    private javafx.scene.control.TableColumn<Cennik, String> Courier;
    @FXML
    void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/ClientMenuForm.fxml"));
        Parent root = loader.load();
        ClientMenuForm clientMenuForm= loader.getController();
        clientMenuForm.setName(getName(), clientMenuForm.name);
        clientMenuForm.setClientType(getClientType(), clientMenuForm.clientType);
        Scene scene = new Scene(root);
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage window = new Stage();
        window.setScene(scene);
        window.show();
    }
    @FXML
    void goMenu(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/ClientMenuForm.fxml"));
        Parent root = loader.load();
        ClientMenuForm clientMenuForm= loader.getController();
        clientMenuForm.setName(getName(), clientMenuForm.name);
        clientMenuForm.setClientType(getClientType(), clientMenuForm.clientType);
        Scene scene = new Scene(root);
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage window = new Stage();
        window.setScene(scene);
        window.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    /*@FXML
    public ObservableList<Zlecenie> fill_table() throws IOException {
        ObservableList<Zlecenie> ZlecenieList = FXCollections.observableArrayList();
        try {
            try {
                ip = InetAddress.getByName("localhost");
                s = new Socket(ip, 5057);
                dis = new DataInputStream(s.getInputStream());
                dos = new DataOutputStream(s.getOutputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
            dos.writeInt(5);
            counter = dis.readInt();
            for (int i = 1; i <= counter; i++) {//TODO
                Gabaryt = dis.readUTF();
                tmpstring = dis.readUTF();
                Kwota = Float.valueOf(tmpstring);
                Opis = dis.readUTF();
                tmpstring = dis.readUTF();
                Limit = Integer.valueOf(tmpstring);
                ZlecenieList.add(new Zlecenie(id, stat, kurier));
            }
            CurrentOrder.setItems(ZlecenieList);
            dis.close();
            dos.close();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ZlecenieList;
    }*/
}
