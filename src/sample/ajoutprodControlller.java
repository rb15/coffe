package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.omg.CORBA.INTERNAL;

import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class ajoutprodControlller implements Initializable {
    public ComboBox cate;
    public TextField prod;
    public TextField cont;
    public TextField prix;
    public TextField prix_v;

    sqlController s = new sqlController();
    ObservableList<String> a = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            cate.setItems(s.setcate(a));
        }catch (SQLException e){
            System.out.println(e);
        }



    }






    public void ajout(ActionEvent actionEvent) {
        try {
            s.inserts(prod.getText(),Integer.parseInt(cont.getText()),Double.parseDouble(prix.getText()),Integer.parseInt(prix_v.getText()),s.getcatid(String.valueOf(cate.getValue())));
        }catch (SQLException r){
            System.out.println(r);
        }
        Stage a =(Stage) prod.getScene().getWindow();
        a.close();

    }


    public void close(ActionEvent actionEvent) {
        Stage a =(Stage) prod.getScene().getWindow();
        a.close();
    }


}

