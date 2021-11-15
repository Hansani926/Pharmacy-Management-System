package dao;

import db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static db.DBConnection.*;

public class CrudUtil {
    public  static <T> T execute(String sql,Object...args)throws SQLException,ClassNotFoundException {
        PreparedStatement stm = getInstance().getConnection().prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                stm.setObject((i + 1), args[i]);
            }
            if (sql.startsWith("SELECT")) {
                return (T) stm.executeQuery();
            }
            return ((T) (Boolean) (stm.executeUpdate()>0));
        }
    }



