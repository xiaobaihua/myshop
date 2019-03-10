package com.shop.web.Filter;

import com.shop.domian.User;
import com.shop.service.UserLogin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Administrator on 2018/2/2.
 */
@WebFilter(filterName = "AutoLoginFilter", urlPatterns = "/login.jsp")
public class AutoLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        //用户没有登录
        if (null == session.getAttribute("user")){
            Cookie[] cookies = request.getCookies();

            String username = null;
            String password = null;
            //获取用户名和密码
            for (Cookie c : cookies){
                if (c.getName().equals("username")){
                    username = c.getValue();
                }else if (c.getName().equals("password") ){
                    password = c.getValue();
                }
            }

            if (null != username && null != password){
                UserLogin userLogin = new UserLogin();
                try {
                    session.setAttribute("user", userLogin.userLogin(username, password));
                    response.sendRedirect(request.getContextPath());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }else{
            //用户已经登录
            request.setAttribute("hint", "用户已经登录，无需重复登录");
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
