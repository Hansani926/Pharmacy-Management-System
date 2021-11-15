package bo.custom;

import dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBo {
    public boolean saveCustomer(CustomerDTO dto)throws Exception;
    public boolean updateCustomer(CustomerDTO dto)throws Exception;
    public boolean deleteCustomer(String id)throws Exception;
    public CustomerDTO getCustomer(String CID)throws Exception;
    public ArrayList<CustomerDTO> getAllCustomers()throws Exception;
    public String getCID()throws Exception;

    Boolean isCustomerExist(String id) throws SQLException, ClassNotFoundException;


    String getFullCustomerCount() throws Exception;
}
