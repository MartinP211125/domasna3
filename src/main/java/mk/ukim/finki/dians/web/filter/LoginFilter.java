package mk.ukim.finki.dians.web.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.dians.model.User;

import java.io.IOException;

@WebFilter(filterName = "auth-filter", urlPatterns = "/*", dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = (User) request.getSession().getAttribute("user");
        String path = request.getServletPath();
        if (path.startsWith("/login") || path.startsWith("/register") || user != null || path.startsWith("/regCss.css")) {
            System.out.println("WebFilter preprocessing...");
            filterChain.doFilter(servletRequest, servletResponse);
            System.out.println("WebFilter postprocessing...");
        } else {
            response.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
