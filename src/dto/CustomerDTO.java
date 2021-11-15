package dto;

public class CustomerDTO {
    private String CID;
    private String name;
    private String address;
    private int contact;

    public CustomerDTO() {
    }

    public CustomerDTO(String CID, String name, String address, int contact) {
        this.CID = CID;
        this.name = name;
        this.address = address;
         this.contact = contact;
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

    @Override
    public  String toString(){
        return "CustomerDTO{" +
                "CID='" + CID + '\'' +
                ", name='" + name + '\'' +
                ",address ='" + address+ '\'' +
                ",contact=" + contact +
                '}';
    }
    }

