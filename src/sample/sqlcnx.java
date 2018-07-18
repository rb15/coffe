package sample;

import org.sqlite.SQLiteConnection;

import java.sql.*;
//connect to database
public class sqlcnx {
    public static Connection cnx() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection cnn = DriverManager.getConnection("jdbc:sqlite:coffe");
            return cnn;
        } catch (Exception e) {
            System.out.println("errerur cnx");
            return null;
        }
    }

}
