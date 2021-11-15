package bo.custom.impl;

import bo.BoFactory;
import bo.custom.ItemBo;
import bo.custom.OrderBo;
import bo.custom.OrderDetailBo;
import dao.DaoFactory;
import dao.QueryDAO;
import dao.custom.OrderDAO;
import db.DBConnection;
import dto.CustomerDTO;
import dto.ItemDTO;
import dto.OrderDTO;
import dto.OrderDetailDTO;
import entity.Customer;
import entity.Item;
import entity.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderBoImpl implements OrderBo {

    QueryDAO queryDAO = DaoFactory.getInstance().getDao(DaoFactory.DAOType.QUERY);
    OrderDAO orderDAO = DaoFactory.getInstance().getDao(DaoFactory.DAOType.ORDER);
    OrderDetailBo detailBo = BoFactory.getInstance().getBo(BoFactory.BOType.ORDERDETAIL);
    ItemBo itemBo = BoFactory.getInstance().getBo(BoFactory.BOType.ITEM);

    @Override
    public String getOrderID() throws Exception {
        return queryDAO.getOrderID();
    }

    @Override
    public boolean saveOrder(OrderDTO dto, ArrayList<OrderDetailDTO> detailDTOS) throws Exception {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isSaved = orderDAO.save(new Order(dto.getOrderId(),dto.getDate(), dto.getcId()));
            boolean isDetailSaved = detailBo.addOrderDetail(detailDTOS);
            boolean isUpdated = itemBo.UpdateStockWhenOrder(detailDTOS);

            if (isSaved && isDetailSaved && isUpdated) {
                DBConnection.getInstance().getConnection().commit();
                return true;
            } else {
                DBConnection.getInstance().getConnection().rollback();
                return false;
            }

        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }

    @Override
    public ArrayList<OrderDTO> getAllOrders() throws Exception {
        ArrayList<OrderDTO>arrayList=new ArrayList<>();
        List<Order> all=orderDAO.getAll();
        for (Order order: all){
            arrayList.add(new OrderDTO(order.getOrderId(),order.getDate(),order.getcId()));

        }
        return arrayList;

    }

    @Override
    public String getFullOrderCount() throws Exception {
        return queryDAO.getFullOrdersCount();
    }


}
