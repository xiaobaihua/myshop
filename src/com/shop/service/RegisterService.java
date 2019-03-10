package com.shop.service;

import com.shop.dao.RegisterDao;
import com.shop.domian.User;
import com.shop.utils.UUIDUtils;
import com.sun.prism.ReadbackGraphics;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2018/1/26.
 */
public class RegisterService {
    public Boolean register(User user) throws InvocationTargetException, IllegalAccessException, SQLException {
        int sum = 0;//注册成功大于0

        //封装到实体UserBean中



        //private String uid = null;
        user.setUid(UUIDUtils.getUUID());

        //private String telephone = null;
        user.setTelephone("110");

        //private String state = null;
        user.setState("0");

        //private String code = null;
        user.setCode(UUIDUtils.getUUID());

        RegisterDao registerDao = new RegisterDao();
        sum = registerDao.register(user);

        return sum>0 ? true: false;
    }

    public int userActivation(String userCode) throws SQLException {
        RegisterDao dao = new RegisterDao();
        return dao.userActivation(userCode);

    }
}
