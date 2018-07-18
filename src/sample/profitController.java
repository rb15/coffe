package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class profitController implements Initializable {


    public TableView<demnde> table;
    public TableColumn<demnde, String> prod;
    public TableColumn<demnde, Integer> vente;
    public TableColumn<demnde, Double> profit;

    public DatePicker date1;
    public DatePicker date2;

    public Label total;
    public CheckBox prof_p;
    sqlController s = new sqlController();
    ObservableList<demnde> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prod.setCellValueFactory(new PropertyValueFactory<demnde, String>("prod"));
        vente.setCellValueFactory(new PropertyValueFactory<demnde, Integer>("vente"));
        profit.setCellValueFactory(new PropertyValueFactory<demnde, Double>("profit"));
    }

    public void calc(ActionEvent actionEvent) {
        if (date1.getValue()!=null && date2.getValue()!=null){
            list.clear();
            try {
                table.setItems(s.initable2(list,String.valueOf(date1.getValue()),String.valueOf(date2.getValue())));
                Double tot=Double.parseDouble(s.total(String.valueOf(date1.getValue()),String.valueOf(date2.getValue())));
                if (prof_p.isSelected()){
                    tot-=1000.0;
                    total.setText(String.valueOf(tot));
                }else {
                    total.setText(String.valueOf(tot));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


    }



}