package com.johnson.toolkit.http;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jhg on 2018-09-18.
 */
public class HttpUtils {

    public static String getRealClientIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0) {
            return request.getRemoteAddr();
        } else {
            String[] list = ip.split(",");
            for (String item: list) {
                if (item == null || item.trim().length() == 0 || item.equals("unknown"))
                    continue;
                else
                    return item.trim();
            }
            return request.getRemoteAddr();
        }
    }



}
