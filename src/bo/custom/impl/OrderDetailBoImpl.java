package bo.custom.impl;

import bo.custom.OrderDetailBo;
import dao.DaoFactory;
import dao.custom.OrderDetailDAO;
import dto.OrderDetailDTO;
import entity.OrderDetail;

import java.util.ArrayList;

public class OrderDetailBoImpl implements OrderDetailBo {
    OrderDetailDAO dao = DaoFactory.getInstance().getDao(DaoFactory.DAOType.ORDERDETAIL);
    @Override
    public boolean addOrderDetail(ArrayList<OrderDetailDTO> detailDTOS) throws Exception {
        for (OrderDetailDTO orderDetailDTO : detailDTOS) {
            boolean b = addOrderDetail(orderDetailDTO);
            if(!b){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addOrderDetail(OrderDetailDTO dto) throws Exception {
        return dao.save(new OrderDetail(dto.getOrderId(),dto.getCode(),dto.getQty(),dto.getAmount(),dto.getType()));
    }
}
