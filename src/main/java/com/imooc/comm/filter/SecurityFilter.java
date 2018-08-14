package com.imooc.comm.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by JSON on 2018/05/12.
 */
public class SecurityFilter implements Filter {

    private static Set<String> GreenUrlSet = new HashSet<String>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        GreenUrlSet.add("/register");
        GreenUrlSet.add("/user/register");
        GreenUrlSet.add("/login");
        GreenUrlSet.add("/user/login");
        GreenUrlSet.add("index.html");
        GreenUrlSet.add("/forgotPassword");
        GreenUrlSet.add("/user/sendForgotPasswordEmail");
        GreenUrlSet.add("/newPassword");
        GreenUrlSet.add("/user/setNewPassword");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        resp.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        //允许跨域请求中携带cookie
        resp.addHeader("Access-Control-Allow-Credentials", "true");
        // 如果存在自定义的header参数，需要在此处添加，逗号分隔
        resp.addHeader("Access-Control-Allow-Headers", "authorization,Origin, No-Cache, X-Requested-With, "
                + "If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, " + "Content-Type, X-E4M-With");
        resp.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        Cookie cookie = new Cookie("user","test1");
        resp.addCookie(cookie);
        //设置字符集
        request.setCharacterEncoding("UTF-8");
        String uri = request.getRequestURI();
        System.out.println("过滤器sesion-id:"+request.getSession().getId());
        if(request.getSession().getAttribute(Const.LOGIN_SESSION_KEY) == null){
            //绿链请求直接放行，终止过滤器代码继续执行。
            if(containsSuffix(uri) || GreenUrlSet.contains(uri)){
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            String html = "";
            //String base_path = "http://127.0.0.1:8022/user/";
            html = "<script type=\"text/javascript\">window.location.href=\"_BP_login\"</script>";
            System.out.println("this is MyFilter,url :"+request.getRequestURI());
            html = html.replace("_BP_", Const.BASE_PATH);
            servletResponse.getWriter().write(html);
        }else {
            //已登录状态下，直接放行
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {

    }

    private boolean containsSuffix(String url) {
        if (url.endsWith(".js")
                || url.endsWith(".css")
                || url.endsWith(".jpg")
                || url.endsWith(".gif")
                || url.endsWith(".png")
                || url.endsWith(".html")
                || url.endsWith(".eot")
                || url.endsWith(".svg")
                || url.endsWith(".ttf")
                || url.endsWith(".woff")
                || url.endsWith(".ico")
                || url.endsWith(".woff2")) {
            return true;
        } else {
            return false;
        }
    }
}
