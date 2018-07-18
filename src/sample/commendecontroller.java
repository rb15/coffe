package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;

public class commendecontroller implements Initializable {
    public TreeView tree;
    public VBox prod;
    public VBox prod1;
    public Label total;
    sqlController s = new sqlController();

    ObservableList<String> b = FXCollections.observableArrayList();
    ObservableList<String> d = FXCollections.observableArrayList();
    ObservableList<String> k = FXCollections.observableArrayList();

    Double tot=0.0;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
    public int count =-1;

    public void click(MouseEvent mouseEvent) {
        Label label1;
        Label label;
        if (mouseEvent.getClickCount() == 2) {
            count++;
            ObservableList<TreeItem> d = tree.getSelectionModel().getSelectedItems();
            for (TreeItem a : d) {
                k.add(String.valueOf(a.getValue()));
                try {
                    String price = s.getprice(String.valueOf(a.getValue()));
                    if (price != null) {
                        label1 = new Label(price);
                        prod1.getChildren().add(label1);
                        label = new Label(String.valueOf(a.getValue()));
                        prod.getChildren().add(label);
                        tot+=Double.parseDouble(price);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        total.setText(String.valueOf(tot));
    }

    public void supp(ActionEvent actionEvent) {
        if (count!=-1){
            Label label=(Label)prod1.getChildren().get(count);
            prod1.getChildren().remove(count);
            prod.getChildren().remove(count);
            total.setText(String.valueOf(Double.parseDouble(total.getText())-Double.parseDouble(label.getText())));
            tot-=Double.parseDouble(label.getText());
            k.remove(count);
            count--;
            }
    }

    public void ajouter(ActionEvent actionEvent) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        for (String a:k) {
            try {
                s.update_v(a,dateFormat.format(cal.getTime()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Stage badro =(Stage)tree.getScene().getWindow();
        badro.close();
    }
}