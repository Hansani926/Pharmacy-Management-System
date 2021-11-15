package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ItemDAO;
import entity.Item;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean save(Item item) throws Exception {
        return CrudUtil.execute("INSERT INTO item VALUES(?,?,?,?,?)",item.getItemCode(),item.getDescription(),item.getQTY(),item.getUnitPrice(),item.getCategoryID());
    }

    @Override
    public boolean update(Item item) throws Exception {
        return CrudUtil.execute("UPDATE Item SET Description=?,Qty=? ,UnitPrice=?,CategoryID=? Where ItemCode=?",item.getItemCode(),item.getDescription(),item.getQTY(),item.getUnitPrice(),item.getCategoryID());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM Item WHERE ItemCode=?",id);
    }

    @Override
    public Item get(String ItemCode) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Item WHERE ItemCode=?",ItemCode);
        if (resultSet.next()) {
            return new Item(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getDouble(4),resultSet.getString(5));
        } else {
            return null;
        }
    }

    @Override
    public List<Item> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Item");
        ArrayList<Item> itemList = new ArrayList<>();
        while (resultSet.next()) {
            itemList.add(
                    new Item(resultSet.getString(1), resultSet.getString(2),resultSet.getInt(3),resultSet.getDouble(4),resultSet.getString(5))
            );
        }
        return itemList;
    }


    @Override
    public boolean updateWhenOrder(Item item) throws Exception {
        return CrudUtil.execute("UPDATE Item SET Qty= (Qty -?) WHERE ItemCode=?",item.getQTY(),item.getItemCode());
    }
}

