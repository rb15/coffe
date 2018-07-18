package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controller implements Initializable,EventHandler<ActionEvent> {
    public TreeView tree;
    public VBox vbox;
    public Label profit_la;
    public DatePicker date;
    public TableView<demnde> table;
    public TableColumn<demnde, String> prod;
    public TableColumn<demnde, Integer> cont;
    public TableColumn<demnde, Integer> vente;
    public TableColumn<demnde, Double> profit;


    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Calendar cal = Calendar.getInstance();

    sqlController s = new sqlController();
    ObservableList<String> a = FXCollections.observableArrayList();
    ObservableList<String> b = FXCollections.observableArrayList();
    ObservableList<String> d = FXCollections.observableArrayList();
    ObservableList<demnde> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if (s.activation5().equals("false")){
                TextInputDialog dialog = new TextInputDialog("badro1996");
                dialog.setTitle("activation");
                dialog.setContentText("entrer votre mot de passe");
                Optional<String> result = dialog.showAndWait();
                if (result.get().equals(s.activation2())){
                    s.activation4();
                    s.activation3(displayMyIP());
                }else {
                    Platform.exit();
                    System.exit(0);
                }
            }else{
                if (!s.activation().equals(displayMyIP())) {
                    Platform.exit();
                    System.exit(0);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        date.setValue(LocalDate.parse(dateFormat.format(cal.getTime())));
        TreeItem<String> root = new TreeItem<>("Produits");
        root.setExpanded(true);
        try {
            for (String a : s.tree(b)) {
                TreeItem<String> item = new TreeItem<String>(a);
                for (String b : s.tree2(d, a)) {
                    TreeItem<String> item1 = new TreeItem<String>(b);
                    item.getChildren().add(item1);
                }
                root.getChildren().add(item);
                d.clear();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        tree.setRoot(root);


        prod.setCellValueFactory(new PropertyValueFactory<demnde, String>("prod"));
        cont.setCellValueFactory(new PropertyValueFactory<demnde, Integer>("cont"));
        vente.setCellValueFactory(new PropertyValueFactory<demnde, Integer>("vente"));
        profit.setCellValueFactory(new PropertyValueFactory<demnde, Double>("profit"));
        try {
            table.setItems(s.initable(list,String.valueOf(date.getValue())));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            for (String f : s.iniproduit(a)) {
                Button button = new Button(f);
                button.setOnAction(this);

                vbox.getChildren().add(button);
                if (list.isEmpty()){
                    try {
                        s.insertd(String.valueOf(date.getValue()),s.getprodid(f));

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            ref(dateFormat.format(cal.getTime()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            profit_la.setText(s.profit(dateFormat.format(cal.getTime())).toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void print(Object a){
        System.out.println(a);
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        date.setValue(LocalDate.parse(dateFormat.format(cal.getTime())));
        String name =((Button)actionEvent.getSource()).getText();
        try {
            s.update_v(name,dateFormat.format(cal.getTime()));
            ref(dateFormat.format(cal.getTime()));
            profit_la.setText(s.profit(String.valueOf(date.getValue())).toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ref(dateFormat.format(cal.getTime()));
    }
    public void ref(String a) {
        list.clear();
        try {
            table.setItems(s.initable(list,a));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ref_tree() {
        b.clear();
        d.clear();
        TreeItem<String> root = new TreeItem<>("Produits");
        root.setExpanded(true);
        try {
            for (String a : s.tree(b)) {
                TreeItem<String> item = new TreeItem<String>(a);
                for (String b : s.tree2(d, a)) {
                    TreeItem<String> item1 = new TreeItem<String>(b);
                    item.getChildren().add(item1);
                }
                root.getChildren().add(item);
                d.clear();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        tree.setRoot(root);
    }

    public void setdate(ActionEvent actionEvent) {
        try {
            ref((date.getValue().toString()));
            profit_la.setText(s.profit(String.valueOf(date.getValue())).toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void demmende(ActionEvent actionEvent) throws IOException, SQLException {
        Parent window = FXMLLoader.load(getClass().getResource("commende.fxml"));
        Scene badro = new Scene(window);
        Stage st1 = new Stage();
        st1.initModality(Modality.APPLICATION_MODAL);
        st1.initOwner(table.getScene().getWindow());
        st1.setTitle("ajoute");
        st1.setScene(badro);
        st1.showAndWait();
        ref(dateFormat.format(cal.getTime()));
        profit_la.setText(s.profit(dateFormat.format(cal.getTime())).toString());
    }
    static String displayMyIP(){
        Enumeration<NetworkInterface> nets;
        try {
            nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netint : Collections.list(nets)){
                Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
                for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                }

                byte[] mac = netint.getHardwareAddress();
                if(mac != null){
                    StringBuilder macAddr = new StringBuilder();
                    for (int i = 0; i < mac.length; i++) {
                        macAddr.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? ":" : ""));
                    }
                   return macAddr.toString();
                }

            }
        } catch (SocketException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }return null;
    }
}
