package com.mp.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "he2 test")
public class He2Controller {

    @PostMapping("/postHi2")
    @ApiOperation(value = "1111", httpMethod = "POST")
    public String postHi() {
        return "post ok";
    }

    @GetMapping("/getHi2")
    @ApiOperation(value = "222222", httpMethod = "GET")
    public String getHi() {
        return "get ok";
    }
}
