package com.shop.service;

import com.shop.dao.UserLoginDao;
import com.shop.domian.User;

import java.sql.SQLException;

/**
 * Created by Administrator on 2018/1/31.
 */
public class UserLogin {
    public User userLogin(String username, String password) throws SQLException {


        UserLoginDao userLoginDao = new UserLoginDao();
        return userLoginDao.userLogin(username, password);
    }
}
