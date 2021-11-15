package bo.custom.impl;

import bo.custom.ReturnOrdersBo;
import dao.CrudDAO;
import dao.DaoFactory;
import dao.custom.CustomerDAO;
import dao.custom.ReturnOrdersDAO;
import dto.CustomerDTO;
import dto.ReturnOrdersDTO;
import entity.Customer;
import entity.ReturnOrders;

import java.util.ArrayList;
import java.util.List;

public class ReturnOrdersBoImpl implements ReturnOrdersBo {
    ReturnOrdersDAO returnOrdersDAO= DaoFactory.getInstance().getDao(DaoFactory.DAOType.RETURNORDERS);

    @Override
    public boolean saveReturnOrders(ReturnOrdersDTO dto) throws Exception {
        return returnOrdersDAO.save(new ReturnOrders(dto.getOrderID(), dto.getItemCode(), dto.getDate(), dto.getDescription()));
    }

    @Override
    public ReturnOrdersDTO getReturnOrders(String Date) throws Exception {
        ReturnOrders returnOrders =  returnOrdersDAO.get(Date);
        if(returnOrders!=null){
            return new ReturnOrdersDTO(returnOrders.getOrderID(),returnOrders.getItemCode(),returnOrders.getDate(),
                    returnOrders.getDescription());
        }
        return null;
    }




    @Override
    public ArrayList<ReturnOrdersDTO> getAllReturnOrders() throws Exception {
        ArrayList<ReturnOrdersDTO>arrayList=new ArrayList<>();
        List<ReturnOrders> all=returnOrdersDAO.getAll();
        for(ReturnOrders returnOrders:all) {
            arrayList.add(new ReturnOrdersDTO(returnOrders.getOrderID(),returnOrders.getItemCode(),returnOrders.getDate(),returnOrders.getDescription()));
        }
        return  arrayList;
    }

    @Override
    public String getDate() throws Exception {
        return null;
    }


    /*@Override
    public String getOrderID() throws Exception {
        return null;
    }*/
}
