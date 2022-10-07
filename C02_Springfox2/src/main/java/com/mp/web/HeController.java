package com.mp.web;

import com.mp.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "he test")
public class HeController {

    @PostMapping("/postHi")
    @ApiOperation(value = "11", httpMethod = "POST")
    public String postHi() {
        return "post ok";
    }

    @GetMapping("/getHi")
    @ApiOperation(value = "222", httpMethod = "GET")
    public String getHi() {
        return "get ok";
    }

    @ApiOperation(value = "xxx接口说明", notes = "xxx")
    @GetMapping("/getUser")
    @ResponseBody
    public User getUser() {
        //只要这个实体在请求接口的返回值上（包括泛型），都能映射到实体项中
        return new User("xxx", "yyyy");
    }
}
