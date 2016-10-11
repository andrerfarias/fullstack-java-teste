/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fullstack.service;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Andre
 */
@WebFilter(filterName = "CorsFilter", urlPatterns = {"/*"})
public class CorsFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    @Override
    public void destroy() {	}
    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletResponse resp = (HttpServletResponse)response;
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            this.setCORS(httpRequest,resp);
            chain.doFilter(request, response);
        } catch (IOException | ServletException t) {
            t.printStackTrace();
        }       
    }

    /**
    * Set the CORS headers if the ORIGIN is allowed
    * @param httpRequest
    * @param resp
    * @return The allowed access status
    */
    public boolean setCORS(HttpServletRequest httpRequest,HttpServletResponse resp){
        String allowUrl = ((httpRequest.getHeader("origin") == null) ? httpRequest.getHeader("host") : httpRequest.getHeader("origin") );
        if(allowUrl != null){
            resp.setHeader("Access-Control-Allow-Origin",allowUrl);
            resp.setHeader("Access-Control-Allow-Credentials","true");
            resp.setHeader("Access-Control-Max-Age","864000");
            resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With,Content-Type, accept,uid, pwd, Set-Cookie,image/png");
            resp.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
            return true;
        }
        return false;
    }
}
