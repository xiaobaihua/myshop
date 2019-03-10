package com.shop.dao;

import com.shop.domian.User;
import com.shop.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * Created by Administrator on 2018/1/31.
 */
public class UserLoginDao {
    QueryRunner QUERY = new QueryRunner(DataSourceUtils.getDataSource());
    String SQL = null;
    
    public User userLogin(String username, String password) throws SQLException {
        SQL = "SELECT * FROM user WHERE username = ? and password = ?";
        return QUERY.query(SQL, new BeanHandler<User>(User.class), username, password);

    }
}
