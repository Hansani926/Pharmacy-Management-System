package bo.custom;

import dto.CustomerDTO;
import dto.OrderDTO;
import dto.OrderDetailDTO;

import java.util.ArrayList;
import java.util.List;

public interface OrderBo {
    public String getOrderID() throws Exception;
    public boolean saveOrder(OrderDTO dto, ArrayList<OrderDetailDTO> detailDTOS) throws Exception ;


    public ArrayList<OrderDTO> getAllOrders()throws Exception;

    String getFullOrderCount() throws Exception;
}
