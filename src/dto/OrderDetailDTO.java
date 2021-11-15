package dto;

public class OrderDetailDTO {
    private String orderId;
    private String code;
    private int qty;
    private double amount;
    private String type;

    public OrderDetailDTO(String orderId, String code, int qty, double amount, String type) {
        this.setOrderId(orderId);
        this.setCode(code);
        this.setQty(qty);
        this.setAmount(amount);
        this.setType(type);
    }

    public OrderDetailDTO() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
