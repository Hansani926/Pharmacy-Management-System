package bo.custom.impl;

import bo.custom.ItemBo;
import dao.DaoFactory;
import dao.QueryDAO;
import dao.custom.ItemDAO;
import dto.ItemDTO;
import dto.OrderDetailDTO;
import entity.Item;


import java.util.ArrayList;
import java.util.List;

public class ItemBoImpl  implements ItemBo {
    ItemDAO itemDAO = DaoFactory.getInstance().getDao(DaoFactory.DAOType.ITEM);
    QueryDAO queryDAO = DaoFactory.getInstance().getDao(DaoFactory.DAOType.QUERY);

    @Override
    public boolean saveItem(ItemDTO dto) throws Exception {
        return itemDAO.save(new Item(dto.getItemCode(), dto.getDescription(),dto.getQTY(),dto.getUnitPrice(),dto.getCategoryID()));
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws Exception {
        return itemDAO.update(new Item(dto.getItemCode(), dto.getDescription(),dto.getQTY(),dto.getUnitPrice(),dto.getCategoryID()));
    }

    @Override
    public boolean deleteItem(String id) throws Exception {
        return itemDAO.delete(id);
    }

    @Override
    public ItemDTO getItem(String ItemCode) throws Exception {
        Item item = itemDAO.get(ItemCode);
        if(item!=null){
            return new ItemDTO(item.getItemCode(),item.getDescription(),item.getQTY(),
                    item.getUnitPrice(),item.getCategoryID());
        }
        return null;
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws Exception {
        ArrayList<ItemDTO>arrayList=new ArrayList<>();
        List<Item> all=itemDAO.getAll();
        for (Item item: all){
            arrayList.add(new ItemDTO(item.getItemCode(),item.getDescription(),item.getQTY(),item.getUnitPrice(),item.getCategoryID()));

        }
        return arrayList;
    }
    public  String getFullItemCount() throws Exception {
        return queryDAO.getFullItemCount();
    }
    @Override
    public String getItemCode() throws Exception {
        return null;
    }

    @Override
    public boolean UpdateStockWhenOrder(ArrayList<OrderDetailDTO> orderDetailDTOS) throws Exception {
        for (OrderDetailDTO orderDetailDTO : orderDetailDTOS) {
            boolean b = UpdateStockWhenOrder(orderDetailDTO);
            if(!b){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean UpdateStockWhenOrder(OrderDetailDTO detailDTO) throws Exception {
        return itemDAO.updateWhenOrder(new Item(detailDTO.getCode(),detailDTO.getQty()));
    }
}
