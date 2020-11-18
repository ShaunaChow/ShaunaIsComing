package top.shauna.shaunaiscoming.config;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/18 19:47
 * @E-Mail z1023778132@icloud.com
 */
@Slf4j
public class ShaunaAuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("过滤器init OK");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        if (session.getAttribute("user")==null){
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            session.setAttribute("msg","请先登录!!!");
            response.sendRedirect("/login/login.html");
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {
        log.info("过滤器destroy OK");
    }
}
