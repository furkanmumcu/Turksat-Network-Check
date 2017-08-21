package com.turksat.networkcheck.Security;

import com.turksat.networkcheck.view.GirisBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by furkanmumcu on 21/08/2017.
 */
public class LoginFilter implements Filter {

    public static final String LOGIN_PAGE = "/giris.xhtml";

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;


        GirisBean girisBean = (GirisBean) httpServletRequest
                .getSession().getAttribute("girisBean");

        if (girisBean != null) {
            if (girisBean.isLoggedIn()) {
                // user is logged in, continue request
                System.out.println("FILTRE" + girisBean.isLoggedIn());
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                // user is not logged in, redirect to login page
                httpServletResponse.sendRedirect(
                        httpServletRequest.getContextPath()
                                + LOGIN_PAGE);
                System.out.println("FILTRE" + girisBean.isLoggedIn());

            }
        }
        else {
            // user is not logged in, redirect to login page
            httpServletResponse.sendRedirect(
                    httpServletRequest.getContextPath() + LOGIN_PAGE);
            System.out.println("FILTRE " + girisBean.isLoggedIn());

        }

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    @Override
    public void destroy() {
        // close resources
    }
}
