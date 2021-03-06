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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.Class.Cennik;
import project.Utils.DataUtil;
import project.Class.Doplata;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class PrizesForm extends DataUtil implements Initializable {
    @FXML
    private AnchorPane APMain;
    @FXML
    public Label name;
    @FXML
    public Label clientType;
    @FXML
    TableView<Cennik> PriceList;
    @FXML
    TableView<Doplata> AditionalPriceList;
    @FXML
    private javafx.scene.control.TableColumn<Cennik, String> Dimension;
    @FXML
    private javafx.scene.control.TableColumn<Cennik, Float> Amount;
    @FXML
    private javafx.scene.control.TableColumn<Cennik, String> Description;
    @FXML
    private javafx.scene.control.TableColumn<Cennik, Integer> Lm;
    @FXML
    private javafx.scene.control.TableColumn<Doplata, String> Type;
    @FXML
    private javafx.scene.control.TableColumn<Doplata, Integer> AditionalAmount;
    private Socket s;
    private InetAddress ip;
    private DataInputStream dis;
    private DataOutputStream dos;
    private int counter, Limit;
    private Float Kwota;
    private String tmpstring,Gabaryt, Opis;

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
        Dimension.setCellValueFactory(new PropertyValueFactory<>("Gabaryt"));
        Amount.setCellValueFactory(new PropertyValueFactory<>("Kwota"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Opis"));
        Lm.setCellValueFactory(new PropertyValueFactory<>("Limit"));
        Type.setCellValueFactory(new PropertyValueFactory<>("TypDoplaty"));
        AditionalAmount.setCellValueFactory(new PropertyValueFactory<>("KwotaD"));
        try {
            fill_table();
            fill_table_second();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public ObservableList<Cennik> fill_table() throws IOException {
        ObservableList<Cennik> Cennik_list = FXCollections.observableArrayList();
        try {
                try {
                    ip = InetAddress.getByName("localhost");
                    s = new Socket(ip, 5057);
                    dis = new DataInputStream(s.getInputStream());
                    dos = new DataOutputStream(s.getOutputStream());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dos.writeInt(3);
                counter = dis.readInt();
                for (int i = 1; i <= counter; i++) {
                    Gabaryt = dis.readUTF();
                    tmpstring = dis.readUTF();
                    Kwota = Float.valueOf(tmpstring);
                    Opis = dis.readUTF();
                    tmpstring = dis.readUTF();
                    Limit = Integer.valueOf(tmpstring);
                    Cennik_list.add(new Cennik(Gabaryt, Kwota, Opis, Limit));
                }
            System.out.println(PriceList);
            PriceList.setItems(Cennik_list);
            dis.close();
            dos.close();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Cennik_list;
    }
    public ObservableList<Doplata> fill_table_second() throws IOException {
        ObservableList<Doplata> DoplataList = FXCollections.observableArrayList();
        try {
            try {
                ip = InetAddress.getByName("localhost");
                s = new Socket(ip, 5057);
                dis = new DataInputStream(s.getInputStream());
                dos = new DataOutputStream(s.getOutputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
            dos.writeInt(4);
            counter = dis.readInt();
            for (int i = 1; i <= counter; i++) {
                Opis = dis.readUTF();
                tmpstring = dis.readUTF();
                Kwota = Float.valueOf(tmpstring);
                DoplataList.add(new Doplata(Opis, Kwota));
            }
            AditionalPriceList.setItems(DoplataList);
            dis.close();
            dos.close();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DoplataList;
    }
}