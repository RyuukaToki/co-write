//package com.example.yjs_endpoint.util;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletRequestWrapper;
//
//import java.util.*;
//
//public class RequestWrapper extends HttpServletRequestWrapper {
//    private final Map<String, String> customHeaders;
//
//    public RequestWrapper(HttpServletRequest request) {
//        super(request);
//        this.customHeaders = new HashMap<>();
//    }
//
//    public void setAttribute(String name, String value) {
//        customHeaders.put(name, value);
//    }
//
//    @Override
//    public String getHeader(String name) {
//        String headerValue = customHeaders.get(name);
//        if (headerValue != null) {
//            return headerValue;
//        }
//        return super.getHeader(name);
//    }
//}
