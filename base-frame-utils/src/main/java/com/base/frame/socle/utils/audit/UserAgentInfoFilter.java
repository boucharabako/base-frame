/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.utils.audit;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

/**
 *
 * @author Alassani
 */
@Component
public class UserAgentInfoFilter extends GenericFilterBean {

    private static final String CONFIGURATION_CONSTANT_IPONE = "192.168.";
    // A ne pas supprimer AWI Alassani
//    private static final String CONFIGURATION_CONSTANT_IPTWO = "127.0.0.0";
    private static final String CONFIGURATION_CONSTANT_IPTREE = "10.";
    private static final String CONFIGURATION_CONSTANT_IPFOUR = "0:0:0:0:0:0:0:1";
    @Autowired(required = false)
    private UserDeviceInfoService userDeviceInfosService;

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) sr;
        servletRequest.getSession().setAttribute("IP", getClientIP(servletRequest));

        String ip = getClientIP(servletRequest);
        userDeviceInfosService.setZone(ip);


        servletRequest.getSession().setAttribute("USER-AGENT", servletRequest.getHeader("User-Agent"));
        String servletParh = servletRequest.getServletPath();

        if (servletParh.contains("/login")) {

            if (ip == null || "localhost".equals(ip)
                    || ip.startsWith(CONFIGURATION_CONSTANT_IPONE)
                    // A ne pas supprimer AWI Alassani
                    //                    || userDeviceInfoService.getIpAdresse().startsWith(CONFIGURATION_CONSTANT_IPTWO)
                    || ip.startsWith(CONFIGURATION_CONSTANT_IPTREE)
                    || ip.equals(CONFIGURATION_CONSTANT_IPFOUR)) {
                servletRequest.getSession().setAttribute("ZONE", "Local");
            } else if (ip.startsWith("172")
                    && ip.split(".").length > 0
                    && Integer.valueOf(ip.split(".")[0]) >= 16
                    && Integer.valueOf(ip.split(".")[0]) <= 31) {
                servletRequest.getSession().setAttribute("ZONE", "Local");

            }

            if (servletRequest.getParameter("device") != null) {

                servletRequest.getSession().setAttribute("DEVICE", servletRequest.getParameter("device"));

            }
        }
        fc.doFilter(sr, sr1);

    }

    private String getClientIP(HttpServletRequest request) {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

}
