package dao;

import entity.SuperEntity;

import java.util.List;

public interface CrudDAO<T extends SuperEntity,CID> extends SuperDAO  {
    public  boolean save(T t)throws Exception;
    public boolean update(T t)throws Exception;
    public boolean delete(CID cid)throws Exception;
    public T get(CID cid)throws Exception;
    public  List<T>getAll()throws Exception;
}
