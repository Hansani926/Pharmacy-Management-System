package bo.custom;



import dto.CategoryDTO;

import java.util.ArrayList;

public interface CategoryBo {
    public boolean saveCategory(CategoryDTO dto)throws Exception;
    public boolean updateCategory(CategoryDTO dto)throws Exception;
    public boolean deleteCategory(String CategoryID)throws Exception;
    public CategoryDTO getCategory(String CategoryID)throws Exception;
    public ArrayList<CategoryDTO> getAllCategorys()throws Exception;
    public String getCategoryID()throws Exception;
}
