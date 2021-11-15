package bo;

import bo.custom.impl.*;

public class BoFactory {
    private static BoFactory boFactory;
    private BoFactory(){

    }
    public static  BoFactory getInstance(){
        return (boFactory==null)?(boFactory=new BoFactory()):(boFactory);

    }
    public  enum BOType{
        CUSTOMER,ORDER,ITEM,CATEGORY,ORDERDETAIL,RETURNORDERS
    }
    public <T> T getBo(BOType type){
        switch (type){

            case CUSTOMER:
                return (T)new CustomerBoImpl();
            case ORDER:
                return (T)new OrderBoImpl();
            case ITEM:
                return (T) new ItemBoImpl();

            case CATEGORY:
                return (T) new CategoryBoImpl();
            case ORDERDETAIL:
                return (T) new OrderDetailBoImpl();
            case RETURNORDERS:
                return (T) new ReturnOrdersBoImpl();

            default:
                return  null;
        }
    }
}
