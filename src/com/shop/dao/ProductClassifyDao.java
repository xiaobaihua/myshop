package com.shop.dao;

import com.shop.domian.Category;
import com.shop.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.management.Query;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2018/2/17.
 */
public class ProductClassifyDao {
    public List findProductClassify() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from category";
        List<Category> list = queryRunner.query(sql, new BeanListHandler<Category>(Category.class));

        return list;
    }
}
