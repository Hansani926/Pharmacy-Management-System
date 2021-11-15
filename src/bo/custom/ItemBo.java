package bo.custom;



import dto.ItemDTO;
import dto.OrderDetailDTO;

import java.util.ArrayList;

public interface ItemBo {
    public boolean saveItem(ItemDTO dto)throws Exception;
    public boolean updateItem(ItemDTO dto)throws Exception;
    public boolean deleteItem(String id)throws Exception;
    public ItemDTO getItem(String ItemCode)throws Exception;
    public ArrayList<ItemDTO> getAllItems()throws Exception;
    public String getItemCode()throws Exception;
    public boolean UpdateStockWhenOrder(ArrayList<OrderDetailDTO> orderDetailDTOS) throws Exception;
    public boolean UpdateStockWhenOrder(OrderDetailDTO detailDTO) throws Exception;

    String getFullItemCount() throws Exception;

}
