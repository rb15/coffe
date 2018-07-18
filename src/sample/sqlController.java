package sample;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class sqlController {
    //datebase sql query
    Connection cnn;

    public sqlController() {
        sqlcnx c = new sqlcnx();
        cnn = c.cnx();

        if (cnn == null) System.exit(1);

    }

    public boolean iscnn() {
        try {
            return !cnn.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    //set table
    public ObservableList<demnde> initable(ObservableList<demnde> a,String b) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        String query ="select produits.nom_p,produits.cont,demende.vente,demende.profit from produits join demende on produits.id_p=demende.id_p where date=? and demende.vente!=0 order by vente desc";
        try {
            preparedStatement = cnn.prepareStatement(query);
            preparedStatement.setString(1,b);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                a.add(new demnde(
                        resultSet.getString("nom_p"),
                        resultSet.getInt("cont"),
                        resultSet.getInt("vente"),
                        resultSet.getDouble("profit")
                ));

            }

            return a;

        } catch (Exception e) {
            System.out.println(e);
            return a;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }
    //set table de profit de 2 date
    public ObservableList<demnde> initable2(ObservableList<demnde> a,String b,String c) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        String query ="select produits.nom_p,produits.cont,SUM(demende.vente) as vente,SUM(demende.profit) as profit from produits join demende on produits.id_p=demende.id_p where date between ? and ? group by produits.nom_p order by vente desc";
        try {
            preparedStatement = cnn.prepareStatement(query);
            preparedStatement.setString(1,b);
            preparedStatement.setString(2,c);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                a.add(new demnde(
                        resultSet.getString("nom_p"),
                        resultSet.getInt("cont"),
                        resultSet.getInt("vente"),
                        resultSet.getDouble("profit")
                ));

            }

            return a;

        } catch (Exception e) {
            System.out.println(e);
            return a;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }
    //total de profit
    public String total(String b,String c) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        String query ="select SUM(profit) as total from (select produits.nom_p,produits.cont,SUM(demende.vente) as vente,SUM(demende.profit) as profit from produits join demende on produits.id_p=demende.id_p where date between ? and ? group by produits.nom_p order by vente desc) sub";
        try {
            preparedStatement = cnn.prepareStatement(query);
            preparedStatement.setString(1,b);
            preparedStatement.setString(2,c);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                return resultSet.getString("total");

            }



        } catch (Exception e) {
            System.out.println(e);
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
        return null;
    }

    public String activation5() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        String query ="select mac_a from activation";
        try {
            preparedStatement = cnn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                 return resultSet.getString("mac_a");
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
        return null;
    }
    public void activation4() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        String query ="update activation set mac_a='true'";
        try {
            preparedStatement = cnn.prepareStatement(query);
            preparedStatement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }finally {
            preparedStatement.close();
        }
    }

    public void activation3(String a) throws SQLException {
        PreparedStatement preparedStatement = null;
        String b="badro1996";
        String query ="update activation set mac=? where serial='badro1996'";
        try {
            preparedStatement = cnn.prepareStatement(query);
            preparedStatement.setString(1,a);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            preparedStatement.close();
        }
    }

    public String activation2() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        String query ="select serial from activation";
        try {
            preparedStatement = cnn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return resultSet.getString("serial");
            }
        } catch (Exception e) {
            System.out.println(e);

        }finally {
            preparedStatement.close();
            resultSet.close();
        }
        return null;
    }

    public String activation() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        String query ="select mac from activation";
        try {
            preparedStatement = cnn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                return resultSet.getString("mac");
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
        return null;
    }

    public ObservableList<String> iniproduit(ObservableList<String> a) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        String query ="select nom_p from produits";
        try {
            preparedStatement = cnn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                a.add(
                        resultSet.getString("nom_p")
                );
            }

            return a;

        } catch (Exception e) {
            System.out.println(e);
            return a;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }

    public ObservableList<String> tree(ObservableList<String> a) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select nom_c from cate ";
        try {
            preparedStatement = cnn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                a.add(resultSet.getString("nom_c"));
            }

            return a;

        } catch (Exception e) {
            System.out.println(e);
            return a;
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
    }

    public ObservableList<String> tree2(ObservableList<String> a,String b) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        String query ="select produits.nom_p from produits join cate on produits.id_c=cate.id_c where cate.nom_c=? ";
        try {
            preparedStatement = cnn.prepareStatement(query);
            preparedStatement.setString(1,b);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                a.add(resultSet.getString("nom_p"));
            }

            return a;

        } catch (Exception e) {
            System.out.println(e);
            return a;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }
    public ObservableList<String> setcate(ObservableList<String> a) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        String query ="select nom_c from cate";
        try {
            preparedStatement = cnn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                a.add(resultSet.getString("nom_c"));
            }

            return a;

        } catch (Exception e) {
            System.out.println(e);
            return a;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }
    public int getcatid(String a) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        String query ="select id_c from cate where nom_c=?";
        try {
            preparedStatement = cnn.prepareStatement(query);
            preparedStatement.setString(1,a);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
               return resultSet.getInt("id_c");
            }

            return 0;

        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }

    }
    public int getprodid(String a) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        String query ="select id_p from produits where nom_p=?";
        try {
            preparedStatement = cnn.prepareStatement(query);
            preparedStatement.setString(1,a);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return resultSet.getInt("id_p");
            }

            return 0;

        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }
    public String getprice(String a) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select pris_vente from produits where nom_p=?";
        try {
            preparedStatement = cnn.prepareStatement(query);
            preparedStatement.setString(1, a);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getString("pris_vente");
            }

            return null;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
    }
    public void inserts(String a, int b,double c,int d,int e)throws SQLException {
        PreparedStatement preparedStatement = null;
        String query ="INSERT INTO produits (nom_p,cont,prix,pris_vente,id_c)VALUES(?,?,?,?,?)";
        try {
            preparedStatement = cnn.prepareStatement(query);
            preparedStatement.setString(1,a);
            preparedStatement.setInt(2,b);
            preparedStatement.setDouble(3,c);
            preparedStatement.setInt(4,d);
            preparedStatement.setInt(5,e);

            preparedStatement.execute();

        }catch (Exception e2){
            System.out.println(e2);
            System.out.println("not added");

        }
        finally {
            preparedStatement.close();
        }
    }
    public void insertd(String a, int b)throws SQLException {
        PreparedStatement preparedStatement = null;
        String query ="INSERT INTO demende (date,id_p)VALUES(?,?)";
        try {
            preparedStatement = cnn.prepareStatement(query);
            preparedStatement.setString(1,a);
            preparedStatement.setInt(2,b);

            preparedStatement.execute();

        }catch (Exception e2){
            System.out.println(e2);
            System.out.println("not added");

        }
        finally {
            preparedStatement.close();
        }
    }
    public void update_v(String a,String b)throws SQLException {
        int id=getprodid(a);
        PreparedStatement preparedStatement = null;
        String query ="update demende set vente=vente+1 where id_p=? and date=?";
        try {
            preparedStatement = cnn.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,b);
            preparedStatement.execute();

        }catch (Exception e2){
            System.out.println(e2);
            System.out.println("not updated");

        }
        finally {
            preparedStatement.close();
        }
    }

    public Double profit(String a) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        String query ="select sum(profit) as sum_profit from demende where date=?";
        try {
            preparedStatement = cnn.prepareStatement(query);
            preparedStatement.setString(1,a);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return resultSet.getDouble("sum_profit");
            }

            return 0.0;

        } catch (Exception e) {
            System.out.println(e);
            return 0.0;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }

    }
}