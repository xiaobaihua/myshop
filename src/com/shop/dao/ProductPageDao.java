package com.shop.dao;

import com.shop.domian.CurrentPage;
import com.shop.domian.Product;
import com.shop.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2018/2/21.
 */
public class ProductPageDao {
    QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
    String sql = null;

    public CurrentPage setCurrentPage( String cid, CurrentPage currentPage) throws SQLException {
        int currentflag = currentPage.getCurrentflag();
        int PageProductTotality = currentPage.getPageProductTotality();
        if (currentflag < 0 || PageProductTotality < 0  ){
            return null;
        }else{
            currentPage.setProductTotality(getProductTotality(Integer.parseInt(cid)));//设置该类商品总数
            currentPage.setPageTotality(getPageTotality(currentPage.getProductTotality(), PageProductTotality));
            currentPage.setCurrentProductList(getCrrentProductList(currentPage, cid));

            return currentPage;
        }
    }

    private int getProductTotality(int cid) throws SQLException {
        //        private String  productTotality= null;//商品总数
        sql = "select count(*) from product where cid = ?";
        Long productTotality = (Long)queryRunner.query(sql, new ScalarHandler(), cid);
        return productTotality.intValue();
    }

    private int getPageTotality(int  productTotality, int pageProductTotality){
        //        private String  pageTotality= null;//页面总数

        double pageTotality = 1.0 * productTotality / pageProductTotality;
        return (int) Math.ceil(pageTotality);//向上取整
    }

    private List getCrrentProductList(CurrentPage currentPage, String cid) throws SQLException {
//        private List currentProductList = null;//当前页面商品
        sql ="SELECT * FROM  product WHERE cid=? LIMIT ?, ?";
        int params = currentPage.getCurrentflag()*currentPage.getPageProductTotality() - 12;
        return queryRunner.query(sql, new BeanListHandler<Product>(Product.class), cid,params, currentPage.getPageProductTotality());
    }
}
