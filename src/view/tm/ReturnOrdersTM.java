package view.tm;

public class ReturnOrdersTM {
    private String OrderID;
    private String  ItemCode;
    private String  Date;
    private String  Description;

    public ReturnOrdersTM() {
    }

    public ReturnOrdersTM(String orderID, String itemCode, String date, String description) {
        OrderID = orderID;
        ItemCode = itemCode;
        Date = date;
        Description = description;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return "ReturnOrdersTM{" +
                "OrderID='" + OrderID + '\'' +
                ", ItemCode='" + ItemCode + '\'' +
                ", Date='" + Date + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }
}
