///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.service;
//
//import com.ngs.core.codification.conf.NProperties;
//import java.io.IOException;
//import java.util.List;
//import java.util.Optional;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.GenericFilterBean;
//
///**
// *
// * @author Assima
// */
//@Component
//public class GenericSellingParamsFilter extends GenericFilterBean {
//
//    @Autowired(required = false)
//    private List<LicenceParamsValidation> listSellingParams;
//    @Autowired
//    private Environment env;
//    String codeFonction = null;
//    boolean flag = false;
//
//    @Autowired
//    private NProperties properties;
//
//    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(GenericSellingParamsFilter.class);
//
//    @Override
//    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
//        flag = true;
//        HttpServletRequest servletRequest = (HttpServletRequest) sr;
//        HttpServletResponse servletResponse = (HttpServletResponse) sr1;
//        String servletPath = servletRequest.getServletPath();
//        codeFonction = null;
//        flag = false;
//
//        if (!properties.isLicenceParamActivated()) {
//            fc.doFilter(sr, sr1);
//            return;
//
//        }
//
//        if ((servletPath.contains("css") || servletPath.contains("img")
//                || servletPath.contains("plugins")
//                || servletPath.contains("fonts") || servletPath.contains("js")
//                || servletPath.contains("jquery") || servletPath.contains("menu") || servletPath.contains("selling"))) {
//            fc.doFilter(sr, sr1);
//
//        } else {
//            if (listSellingParams != null) {
//                Optional<LicenceParamsValidation> s = listSellingParams.stream().filter(x -> !x.validate()).findFirst();
//                if (s.isPresent()) {
//                    LOG.error(s.get().getErrorMessage(), s.get());
//                    if (servletPath.contains("api")) {
//                        servletResponse.sendError(400, "Paramètre de configuration invalide");
//                        return;
//                    } else {
//                        flag = true;
//                    }
//                }
//
//            }
//            if (flag) {
//                try {
//                    servletResponse.sendRedirect(servletRequest.getContextPath() + "/errors/conf/selling");
//                } catch (IOException ex) {
//                    Logger.getLogger(GenericSellingParamsFilter.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            } else {
//                fc.doFilter(sr, sr1);
//            }
//        }
//
//    }
//
//}
