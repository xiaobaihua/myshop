package com.shop.dao;

import com.shop.domian.User;
import com.shop.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by Administrator on 2018/1/26.
 */
public class RegisterDao {
    QueryRunner QUERY = new QueryRunner(DataSourceUtils.getDataSource());
    String SQL = null;


    public int register(User user) throws SQLException {
        SQL = "INSERT INTO user VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int sum = 0;

        sum = QUERY.update(SQL, user.getUid(), user.getUsername(), user.getPassword(),
                user.getName(), user.getEmail(), user.getTelephone(), user.getBirthday(),
                user.getSex(), user.getState(), user.getCode());

        return sum;
    }

    public int userActivation(String userCode) throws SQLException {
        SQL = "UPDATE user SET state = 1 WHERE code = ?;";
        return QUERY.update(SQL, userCode);
    }
}
