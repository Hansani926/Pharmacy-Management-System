package entity;

public class Order implements SuperEntity {
    private String orderId;
    private String date;
    private String cId;

    public Order(String orderId, String date, String cId) {
        this.setOrderId(orderId);
        this.setDate(date);
        this.setcId(cId);
    }

    public Order() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }
}
