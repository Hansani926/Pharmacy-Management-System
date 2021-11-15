package dao.custom;

import dao.CrudDAO;
import entity.Item;

public interface ItemDAO  extends CrudDAO<Item,String> {
    public boolean updateWhenOrder(Item item) throws Exception;
}
