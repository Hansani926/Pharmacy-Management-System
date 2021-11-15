package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CategoryDAO;
import entity.Category;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    @Override
    public boolean save(Category category) throws Exception {
        return CrudUtil.execute("INSERT INTO Category VALUES(?,?)",category.getCategoryID(),category.getCategoryName());
    }

    @Override
    public boolean update(Category category) throws Exception {
        return CrudUtil.execute("UPDATE Category SET CategoryName=?  WHERE CategoryID=?",category.getCategoryID(),category.getCategoryName());
    }

    @Override
    public boolean delete(String CategoryID) throws Exception {
        return CrudUtil.execute("DELETE FROM Category WHERE CategoryID=?",CategoryID);
    }

    @Override
    public Category get(String CategoryID) throws Exception {
        ResultSet resultSet=CrudUtil.execute("SELECT FROM Category WHERE CategoryID=?",CategoryID);
        if(resultSet.next()) {
            return new Category(
                    resultSet.getString(1), resultSet.getString(2));
        }else {
            return  null;
        }
    }

    @Override
    public List<Category> getAll() throws Exception {
        ResultSet resultSet=CrudUtil.execute("SELECT * FROM category");
        ArrayList<Category> categoryList=new ArrayList<>();
        while (resultSet.next()){
            categoryList.add(new Category(resultSet.getString(1), resultSet.getString(2)));
        }
        return categoryList;
    }
    }

