package bo.custom.impl;

import bo.custom.CustomerBo;
import dao.CrudDAO;
import dao.DaoFactory;
import dao.QueryDAO;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {
    CustomerDAO customerDAO= DaoFactory.getInstance().getDao(DaoFactory.DAOType.CUSTOMER);
  QueryDAO queryDAO = DaoFactory.getInstance().getDao(DaoFactory.DAOType.QUERY);

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws Exception {
        return customerDAO.save(new Customer(dto.getCID(), dto.getName(), dto.getAddress(), dto.getContact()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws Exception {
        return customerDAO.update(new Customer(dto.getCID(), dto.getName(), dto.getAddress(), dto.getContact()));
    }

    @Override
    public boolean deleteCustomer(String id) throws Exception {
        return customerDAO.delete(id);
    }

    @Override
    public CustomerDTO getCustomer(String CID) throws Exception {
        Customer customer =  customerDAO.get(CID);
        if(customer!=null){
            return new CustomerDTO(customer.getCID(),customer.getName(),customer.getAddress(),
                    customer.getContact());
        }
        return null;
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws Exception {
   ArrayList<CustomerDTO>arrayList=new ArrayList<>();
        List<Customer>all=customerDAO.getAll();
        for(Customer customer:all) {
            arrayList.add(new CustomerDTO(customer.getCID(), customer.getName(), customer.getAddress(), customer.getContact()));
        }
        return  arrayList;
        }

    public  String getFullCustomerCount() throws Exception {
        return queryDAO.getFullCustomerCount();
}
    @Override
    public String getCID() throws Exception {
        return null;
    }

    @Override
    public Boolean isCustomerExist(String id) throws SQLException, ClassNotFoundException {
        return queryDAO.isCustomerExist(id);
    }


  /*  @Override
    public Boolean isCustomerExist(String id) throws SQLException, ClassNotFoundException {

        return queryDAO.isCustomerExist(id);
    }*/
}
