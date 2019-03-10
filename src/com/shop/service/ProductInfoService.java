package com.shop.service;

import com.shop.dao.ProductInfoDao;
import com.shop.domian.Product;

import java.sql.SQLException;

/**
 * Created by Administrator on 2018/2/27.
 */
public class ProductInfoService {

    public Product findProductInfo(String cid) throws SQLException {
        ProductInfoDao dao = new ProductInfoDao();
        return dao.findProductInfo(cid);
    }
}
