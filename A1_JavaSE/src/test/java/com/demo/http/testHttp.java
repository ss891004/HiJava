package com.demo.http;

import io.github.admin4j.http.core.Pair;
import io.github.admin4j.http.util.HttpUtil;
import okhttp3.Response;
import org.junit.Test;

public class testHttp {


    @Test
    public void test1(){

        // GET 请求
        Response response = HttpUtil.get("https://github.com/search", Pair.of("q", "okhttp"));
        System.out.println("response = " + response);
    }
}
