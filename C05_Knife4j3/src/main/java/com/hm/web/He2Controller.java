package com.hm.web;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.hm.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "he2 test")
public class He2Controller {

    @PostMapping("/postHi2")
    @ApiOperation(value = "1111", httpMethod = "POST")
    @ApiOperationSupport(author = "15250630870@qq.com")
    public String postHi(@RequestBody JSONPObject aaa) {
        return "post ok";
    }

    @GetMapping("/getHi2")
    @ApiOperation(value = "222222", httpMethod = "GET")
    public String getHi() {
        return "get ok";
    }


    @PostMapping("/getUser2")
    @ApiOperation(value = "gggg", httpMethod = "POST")
    public String getUser(@RequestBody User user) {
        System.out.println(user);
        return "ok";
    }
}
