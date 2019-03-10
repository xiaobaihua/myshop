package com.shop.web;

import com.google.gson.Gson;
import com.shop.dao.ProductInfoDao;
import com.shop.domian.CurrentPage;
import com.shop.domian.Product;
import com.shop.service.ProductClassifyService;
import com.shop.service.ProductInfoService;
import com.shop.service.ProductListService;
import com.shop.utils.JedisPoolUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2018/2/17.
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/product"})
public class ProductServlet extends BaseServlet {

    public void productClassify(HttpServletRequest request, HttpServletResponse response){
        Jedis jedis = JedisPoolUtils.getJedis();
        String productClassify = jedis.get("productClassify");

        if (productClassify == null){
            ProductClassifyService service = new ProductClassifyService();
            try {
                List list = service.findProductClassify();
                Gson gson = new Gson();
                String s = gson.toJson(list).toString();
                jedis.set("productClassify", s);
                response.getWriter().write(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            try {
                response.getWriter().write(productClassify);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void productList(HttpServletRequest request, HttpServletResponse response){
        String cid = request.getParameter("cid");

        String flag = request.getParameter("currentPageFlag");
        int currentPageFlag = flag == null? 1: Integer.parseInt(flag);

        CurrentPage currentPage = new CurrentPage();
        ProductListService service = new ProductListService();

        currentPage.setCurrentflag(currentPageFlag);
        currentPage.setPageProductTotality(12);

        try {
            service.productList(cid, currentPage);
            request.setAttribute("CurrentPage", currentPage);
            request.setAttribute("cid", cid);
            request.getRequestDispatcher("/product_list.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void productInfo(HttpServletRequest request, HttpServletResponse responset){
        String pid = request.getParameter("pid");
        ProductInfoService service = new ProductInfoService();
        try {
            Product product = service.findProductInfo(pid);
            request.setAttribute("product", product);
            request.getRequestDispatcher("/product_info.jsp").forward(request, responset);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
