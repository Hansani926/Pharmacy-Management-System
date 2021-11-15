package view.tm;

public class CategoryTM {
    private String CategoryID;
    private String CategoryName;

    public CategoryTM() {
    }

    public CategoryTM(String categoryID, String categoryName) {
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
        return "CategoryTM{" +
                "CategoryID='" + CategoryID + '\'' +
                ", CategoryName='" + CategoryName + '\'' +
                '}';
    }
}
