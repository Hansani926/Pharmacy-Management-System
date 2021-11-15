package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDAO;
import entity.Customer;
import entity.Order;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDAO {
    @Override
    public boolean save(Order order) throws Exception {
        return CrudUtil.execute("INSERT INTO Orders VALUES (?,?,?)",order.getOrderId(),
                order.getDate(),order.getcId());
    }

    @Override
    public boolean update(Order order) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public Order get(String s) throws Exception {
        return null;
    }

    @Override
    public List<Order> getAll() throws Exception {
        ResultSet resultSet=CrudUtil.execute("SELECT * FROM Orders");
        ArrayList<Order> orderList=new ArrayList<>();
        while (resultSet.next()){
            orderList.add(new Order(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)));
        }
        return orderList;
    }

}
