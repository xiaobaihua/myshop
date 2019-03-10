package com.shop.web;

import com.shop.domian.User;
import com.shop.service.RegisterService;
import com.shop.service.UserLogin;
import com.shop.utils.MailUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/31.
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/user"})
public class UserServlet extends BaseServlet {
    //用户登录
    public void userLogin(HttpServletRequest request, HttpServletResponse response){
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String autoLogin = request.getParameter("autoLogin");
            UserLogin userLogin = new UserLogin();
            User user = userLogin.userLogin(username, password);
            if (user != null){
                //实现自动登录，吧用户名和密码保存的cookie
                if (autoLogin!=null){
                    Cookie uCookie = new Cookie("username", username);
                    Cookie pCookie = new Cookie("password", password);
                    //保存一星期
                    uCookie.setMaxAge(60*60*24*7);
                    pCookie.setMaxAge(60*60*24*7);
                    response.addCookie(uCookie);
                    response.addCookie(pCookie);
                }
                request.getSession().setAttribute("user", user);
                response.sendRedirect(request.getContextPath());
            }else{
                request.setAttribute("hint", "密码或用户名错误！请重试！");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //用户注册
    public void userRegister(HttpServletRequest request, HttpServletResponse response){
        Map<String, String[]> parameterMap = request.getParameterMap();//获取参数
        Boolean aBoolean = false;
        HttpSession session = request.getSession();

        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
            //传递请求
            RegisterService registerService = new RegisterService();
            aBoolean = registerService.register(user);//注册成功trues
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (aBoolean){
            try {
                //发邮件

                String msg = "恭喜您注册成功， 请到http://localhost:8080/shop/userActivation?userCode="+user.getCode();
                MailUtils.sendEmil(user.getEmail(), msg);;
                session.setAttribute("user", user);
                response.sendRedirect(request.getContextPath()+"/RegisterSucceed.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                response.sendRedirect(request.getContextPath()+"/registerFailure");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void userActivation(HttpServletRequest request, HttpServletResponse response){
        String userCode = request.getParameter("userCode");

        RegisterService service = new RegisterService();
        int mark = -1;

        try {
            mark = service.userActivation(userCode);
            if (mark > 0){
                response.getWriter().write("更新成功");
            }else{
                response.getWriter().write("更新失败，请联系我们");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
