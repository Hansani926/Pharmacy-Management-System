package view.tm;

import javafx.scene.control.Button;

public class ItemTM {
    private  String ItemCode;
    private String Description;
    private String CategoryID;
    private int Qty;
    private Double UnitPrice;
    private Button button;

    public ItemTM() {
    }

    public ItemTM(String itemCode, String description, String categoryID, int qty, Double unitPrice, Button button) {
        ItemCode = itemCode;
        Description = description;
        CategoryID = categoryID;
        Qty = qty;
        UnitPrice = unitPrice;
        this.button = button;
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

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String categoryID) {
        CategoryID = categoryID;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public Double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        UnitPrice = unitPrice;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return "ItemTM{" +
                "ItemCode='" + ItemCode + '\'' +
                ", Description='" + Description + '\'' +
                ", CategoryID='" + CategoryID + '\'' +
                ", Qty=" + Qty +
                ", UnitPrice=" + UnitPrice +
                ", button=" + button +
                '}';
    }
}
