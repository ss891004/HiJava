package com.mp.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class IPUtil {

    /**
     * location / {
     * proxy_set_header Host $host;
     *   proxy_set_header X-Real-IP $remote_addr; #获取客户端真实IP
     *   proxy_set_header REMOTE-HOST $remote_addr;
     *   proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
     * proxy_pass http://后台访问ip:端口/;
     * }
     *
     * */

    /**
     * 获取IP地址
     *
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = null;
        try {
            ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {

        }

//       //使用代理，则获取第一个IP地址
//       if(StringUtils.isEmpty(ip) && ip.length() > 15) {
//       if(ip.indexOf(",") > 0) {
//         ip = ip.substring(0, ip.indexOf(","));
//       }
//   }

        return ip;
    }

}