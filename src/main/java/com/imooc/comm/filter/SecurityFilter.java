package com.imooc.comm.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
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
        GreenUrlSet.add("/login");
        GreenUrlSet.add("/user/login");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        System.out.println("过滤器sesion-id:"+request.getSession().getId());
        if(request.getSession().getAttribute(Const.LOGIN_SESSION_KEY) == null){
            //绿链请求直接放行，终止过滤器代码继续执行。
            if(GreenUrlSet.contains(uri)){
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            String html = "";
            //String base_path = "http://127.0.0.1:8022/user/";
            html = "<script type=\"text/javascript\">window.location.href=\"login\"</script>";
            System.out.println("this is MyFilter,url :"+request.getRequestURI());
            //html = html.replace("_BP_", base_path);
            servletResponse.getWriter().write(html);
        }else {
            //已登录状态下，直接放行
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
