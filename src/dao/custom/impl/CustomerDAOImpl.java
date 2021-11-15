package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import entity.Customer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean save(Customer customer) throws Exception {
        return CrudUtil.execute("INSERT INTO Customer VALUES(?,?,?,?)",customer.getCID(),customer.getName(),customer.getAddress(),customer.getContact());
    }

    @Override
    public boolean update(Customer customer) throws Exception {
        return CrudUtil.execute("UPDATE Customer SET Name=?,Address=?,Contact=? Where CID=?",customer.getName(),customer.getAddress(),customer.getContact(),customer.getCID());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM Customer WHERE  CID=?",id);
    }

    @Override
    public Customer get(String CID) throws Exception {
        ResultSet resultSet=CrudUtil.execute("SELECT * FROM Customer WHERE CID=?",CID);
    if(resultSet.next()) {
        return new Customer(
                resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
    }else {
        return  null;
    }
    }

    @Override
    public List<Customer> getAll() throws Exception {
    ResultSet resultSet=CrudUtil.execute("SELECT * FROM customer");
        ArrayList<Customer>customerList=new ArrayList<>();
        while (resultSet.next()){
            customerList.add(new Customer(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4) ));
        }
        return customerList;
    }
}
