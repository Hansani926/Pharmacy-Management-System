package dto;

public class ItemDTO {
    private  String ItemCode;
    private String Description;
    private int QTY;
    private Double UnitPrice;
    private String CategoryID;

    public ItemDTO() {
    }

    public ItemDTO(String itemCode, String description, int QTY, Double unitPrice, String categoryID) {
        ItemCode = itemCode;
        Description = description;
        this.QTY = QTY;
        UnitPrice = unitPrice;
        CategoryID = categoryID;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getQTY() {
        return QTY;
    }

    public void setQTY(int QTY) {
        this.QTY = QTY;
    }

    public Double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        UnitPrice = unitPrice;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String categoryID) {
        CategoryID = categoryID;
    }
    @Override
    public String toString() {
        return "ItemDTO{" +
                "ItemCode='" + ItemCode + '\'' +
                ", Description='" + Description + '\'' +
                ", QTY=" + QTY +
                ", UnitPrice=" + UnitPrice +
                ", CategoryID='" + CategoryID + '\'' +
                '}';
    }

}
