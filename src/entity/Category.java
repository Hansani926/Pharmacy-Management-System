package entity;

public class Category implements SuperEntity{
    private String  CategoryID;
    private String CategoryName;

    public Category() {
    }

    public Category(String categoryID, String categoryName) {
        CategoryID = categoryID;
        CategoryName = categoryName;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String categoryID) {
        CategoryID = categoryID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "CategoryID='" + CategoryID + '\'' +
                ", CategoryName='" + CategoryName + '\'' +
                '}';
    }
}
