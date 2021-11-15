package bo.custom.impl;

import bo.custom.CategoryBo;
import dao.DaoFactory;

import dao.custom.CategoryDAO;
import dto.CategoryDTO;


import entity.Category;
import entity.Customer;


import java.util.ArrayList;
import java.util.List;

public class CategoryBoImpl implements CategoryBo {
    CategoryDAO categoryDAO= DaoFactory.getInstance().getDao(DaoFactory.DAOType.CATEGORY);
    @Override
    public boolean saveCategory(CategoryDTO dto) throws Exception {
        return categoryDAO.save(new Category(dto.getCategoryID(), dto.getCategoryName()));
    }

    @Override
    public boolean updateCategory(CategoryDTO dto) throws Exception {
        return categoryDAO.update(new Category(dto.getCategoryID(), dto.getCategoryName()));
    }

    @Override
    public boolean deleteCategory(String id) throws Exception {
         return categoryDAO.delete(id);
    }

    @Override
    public CategoryDTO getCategory(String CategoryID) throws Exception {
        return null;
    }

    @Override
    public ArrayList<CategoryDTO> getAllCategorys() throws Exception {
        ArrayList<CategoryDTO>arrayList=new ArrayList<>();
        List<Category> all=categoryDAO.getAll();
        for(Category category:all) {
            arrayList.add(new CategoryDTO(category.getCategoryID(),category.getCategoryName()));
        }
        return  arrayList;
    }

    @Override
    public String getCategoryID() throws Exception {
        return null;
    }
}
