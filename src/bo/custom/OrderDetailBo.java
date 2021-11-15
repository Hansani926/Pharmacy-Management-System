package bo.custom;

import dto.OrderDetailDTO;

import java.util.ArrayList;

public interface OrderDetailBo {
    public boolean addOrderDetail(ArrayList<OrderDetailDTO> detailDTOS) throws Exception;
    public boolean addOrderDetail(OrderDetailDTO dto) throws Exception;
}
