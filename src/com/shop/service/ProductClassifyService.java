package com.shop.service;

import com.shop.dao.ProductClassifyDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2018/2/17.
 */
public class ProductClassifyService {
    public List findProductClassify() throws SQLException {
        ProductClassifyDao dao = new ProductClassifyDao();
        List list = dao.findProductClassify();
        return  list;
    }
}
