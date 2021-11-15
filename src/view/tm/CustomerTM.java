package view.tm;


import javafx.scene.control.Button;

public class CustomerTM {
    private String CID;
    private String name;
    private String address;
    private  int contact;
    private Button button;



    public CustomerTM() {

    }

    public CustomerTM(String CID, String name, String address, int contact, Button button) {
        this.CID = CID;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.button = button;

    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return "CustomerTM{" +
                "CID='" + CID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact=" + contact +
                ", button=" + button +

                '}';
    }
}

