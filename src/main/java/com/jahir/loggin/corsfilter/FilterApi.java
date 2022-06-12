package com.jahir.loggin.corsfilter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@ WebFilter(asyncSupported = true, urlPatterns = { "/*" } , filterName = "FilterApi")
public class FilterApi implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        //Login no requiere autentificacion
        if(req.getMethod() == "OPTIONS" || req.getPathInfo().equalsIgnoreCase("api/log/login")){
            return ;
        }


        res.addHeader("Access-Control-Allow-Origin", "*" );
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        res.addHeader("Access-Control-Allow-Headers", "Origin, content-type, accept, authorization");
        res.addHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(req, res);
    }


}
