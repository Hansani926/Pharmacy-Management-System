package dao.custom.impl;

import dao.CrudUtil;
import dao.QueryDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public boolean minimiseCount(String orderID) throws Exception{
        return false;
    }

    public String getFullCustomerCount() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT  * FROM customer");
        int i=0;
        while (rst.next()) {
            i++;
        }
        return i+"";
    }

    @Override
    public String getFullOrdersCount() throws Exception{
        ResultSet rst = CrudUtil.execute("SELECT  * FROM orders");
        int i=0;
        while (rst.next()) {
            i++;
        }
        return i+"";
    }

    @Override
    /*public boolean minimiseCoun throws Exception{
        return false;
    }*/
    public String getFullItemCount() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT  * FROM Item");
        int i=0;
        while (rst.next()) {
            i++;
        }
        return i+"";
    }

    /*public String getFullOrdersCount() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT  * FROM orders");
        int i=0;
        while (rst.next()) {
            i++;
        }
        return i+"";
    }*/

    @Override
    public String getId() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT Id FROM Login ORDER BY password BY password DESC LIMIT 1");
        String password = "P001";
        if (set.next()) {
            String temp = set.getString("1");
            String[] cs = temp.split("P");
            int selectedpassword = Integer.parseInt(cs[1]);
            if (selectedpassword > 10) {
                password = "P0" + (selectedpassword + 1);
            }
        }
        return password;
    }

    @Override
    public Boolean isCustomerExist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Customer where cid=?",id);
        if (rst.next()){
            return true;
        }
        return false;
    }

    @Override
    public Boolean isOrderExist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Order where OrderID=?",id);
        if (rst.next()){
            return true;
        }
        return false;

    }

    public String getOrderID() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT OrderID FROM orders ORDER BY OrderID DESC LIMIT 1");
        String orderid = "R001";
        if (set.next()) {
            String temp = set.getString(1);
            String[] cs = temp.split("R");
            int selectedpassword = Integer.parseInt(cs[1]);
            if (selectedpassword < 10) {
                orderid = "R00" + (selectedpassword + 1);
            }else if (selectedpassword > 10) {
                orderid = "R0" + (selectedpassword + 1);
            }
        }
        return orderid;
    }
    public String getCID() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT CID FROM Customer ORDER BY CID DESC LIMIT 1");
        String cid = "C001";
        if (set.next()) {
            String temp = set.getString(1);
            String[] cs = temp.split("C");
            int selectedpassword = Integer.parseInt(cs[1]);
            if (selectedpassword < 10) {
                cid= "C00" + (selectedpassword + 1);
            }else if (selectedpassword > 10) {
                cid= "C0" + (selectedpassword + 1);
            }
        }
        return cid;
    }
}

