package com.shop.domian;

import java.util.List;

/**
 * Created by Administrator on 2018/2/24.
 */
public class CurrentPage {
    private List currentProductList = null;//当前页面商品
    private int currentflag = -1;//当前页面页码
    private int pageProductTotality = -1;//当前页面商品总数

    private int  productTotality= -1;//商品总数
    private int  pageTotality= -1;//页面总数

    public List getCurrentProductList() {
        return currentProductList;
    }

    public void setCurrentProductList(List currentProductList) {
        this.currentProductList = currentProductList;
    }

    public int getCurrentflag() {
        return currentflag;
    }

    public void setCurrentflag(int currentflag) {
        this.currentflag = currentflag;
    }

    public int getPageProductTotality() {
        return pageProductTotality;
    }

    public void setPageProductTotality(int pageProductTotality) {
        this.pageProductTotality = pageProductTotality;
    }

    public int getProductTotality() {
        return productTotality;
    }

    public void setProductTotality(int productTotality) {
        this.productTotality = productTotality;
    }

    public int getPageTotality() {
        return pageTotality;
    }

    public void setPageTotality(int pageTotality) {
        this.pageTotality = pageTotality;
    }
}
