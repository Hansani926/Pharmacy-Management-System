package dao.custom.impl;

import dao.CrudUtil;
import dao.QueryDAO;
import dao.custom.ReturnOrdersDAO;
import entity.Customer;
import entity.ReturnOrders;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReturnOrdersDAOImpl implements ReturnOrdersDAO {
    @Override
    public boolean save(ReturnOrders returnOrders) throws Exception {
        return CrudUtil.execute("INSERT INTO ReturnOrders VALUES(?,?,?,?)",returnOrders.getOrderID(),returnOrders.getItemCode(),returnOrders.getDate(),returnOrders.getDescription());
    }


    @Override
    public boolean update(ReturnOrders returnOrders) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public ReturnOrders get(String Date) throws Exception {
        ResultSet resultSet=CrudUtil.execute("SELECT FROM ReturnOrders WHERE Date=?",Date);
        if(resultSet.next()) {
            return new ReturnOrders(
                    resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
        }else {
            return  null;
        }
    }

    @Override
    public List<ReturnOrders> getAll() throws Exception {
        ResultSet resultSet=CrudUtil.execute("SELECT * FROM returnOrders");
        ArrayList<ReturnOrders> returnOrdersList=new ArrayList<>();
        while (resultSet.next()){
            returnOrdersList.add(new ReturnOrders(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4) ));
        }
        return returnOrdersList;
    }
    }

