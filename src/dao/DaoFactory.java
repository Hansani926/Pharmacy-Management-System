package dao;

import bo.custom.impl.ItemBoImpl;
import dao.custom.impl.*;
import entity.ReturnOrders;

public class DaoFactory {
    private  static DaoFactory daoFactory;
    private DaoFactory(){
    }
    public static DaoFactory getInstance(){
        return (daoFactory==null)?(daoFactory=new DaoFactory()):(daoFactory);

    }
    public  enum  DAOType{
        LOGIN,CUSTOMER,ITEM,CATEGORY,BATCHDETAIL,QUERY,RETURNORDERS,ORDER,ORDERDETAIL
    }
    public <T> T getDao(DAOType type){
        switch (type){

            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            case ITEM:
                return (T) new ItemDAOImpl();

            case CATEGORY:
                return (T) new CategoryDAOImpl();
            case ORDER:
                return (T) new OrderDaoImpl();
            case ORDERDETAIL:
                return (T) new OrderDetailDaoImpl();


            case RETURNORDERS:
                return (T) new ReturnOrdersDAOImpl();
            default:
                return  null;

        }
    }
}
