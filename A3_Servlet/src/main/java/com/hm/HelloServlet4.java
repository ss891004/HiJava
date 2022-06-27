package com.hm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

//设置Content-Encoding响应头
@WebServlet(name = "servlet4",value = "/servlet4")
public class HelloServlet4 extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String data = "abcdabcdabcdabcdabcdabcdab" +
                "cdabcdabcdabcdabcdabcdabcdabcdabc" +
                "dabcdabcdabcdabcdabcdabcdabcdabc" +
                "dabcdabcdabcdabcdabcdabcdabcdabcdab" +
                "cdabcdabcdabcdabcdabcdabcdabcdabcdab" +
                "cdabcdabcdabcdabcdabcdabcdabcdabcdab" +
                "cdabcdabcdabcdabcdabcdabcdabcdabcdab" +
                "cdabcdabcdabcdabcdabcdabcdabcdabcdabcd";
        System.out.println("原始数据的大小为：" + data.getBytes().length);

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        GZIPOutputStream gout = new GZIPOutputStream(bout); //buffer
        gout.write(data.getBytes());
        gout.close();
        //得到压缩后的数据
        byte[] g = bout.toByteArray();
        response.setHeader("Content-Encoding", "gzip");
        response.setHeader("Content-Length", g.length + "");
        response.getOutputStream().write(g);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}
