package com.shop.dao;

import com.shop.domian.Product;
import com.shop.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * Created by Administrator on 2018/2/27.
 */
public class ProductInfoDao {
    public Product findProductInfo(String pid) throws SQLException {
        QueryRunner queryRunne = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "SELECT * FROM product WHERE pid = ?";
        return queryRunne.query(sql, new BeanHandler<Product>(Product.class), pid);
    }
}
