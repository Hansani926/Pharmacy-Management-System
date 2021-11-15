package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface QueryDAO extends  SuperDAO{
    public boolean minimiseCount(String orderID) throws Exception;

    public String getFullCustomerCount() throws Exception;
    public  String getFullOrdersCount() throws Exception;
    public  String  getFullItemCount()throws Exception;

    public String getId()throws Exception;

    Boolean isCustomerExist(String id) throws SQLException, ClassNotFoundException;

    Boolean isOrderExist(String id)throws SQLException,ClassNotFoundException;
    public String getOrderID() throws Exception;
}
