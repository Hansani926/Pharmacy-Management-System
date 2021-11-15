package bo.custom;

import dto.CustomerDTO;
import dto.ReturnOrdersDTO;

import java.util.ArrayList;

public interface ReturnOrdersBo {

   public boolean saveReturnOrders(ReturnOrdersDTO dto)throws Exception;
   // public boolean updateCustomer(CustomerDTO dto)throws Exception;
   // public boolean deleteCustomer(String id)throws Exception;
    public ReturnOrdersDTO getReturnOrders(String Date)throws Exception;
    public ArrayList<ReturnOrdersDTO> getAllReturnOrders()throws Exception;
    public String getDate()throws Exception;

}
