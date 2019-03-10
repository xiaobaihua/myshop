package com.shop.service;

import com.shop.dao.ProductPageDao;
import com.shop.domian.CurrentPage;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2018/2/21.
 */
public class ProductListService {
    public CurrentPage productList(String cid, CurrentPage currentPage) throws SQLException {
        ProductPageDao dao = new ProductPageDao();

        CurrentPage currentPage1 = dao.setCurrentPage(cid, currentPage);

        return currentPage1;
    }
}
