package hrcb.controller;

import hrcb.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@Api("hello~~~~")
public class HelloController {


    // 请求的url： http://localhost:8080/swagger-ui.html#/hello-controller
    @ApiOperation(value = "xxx接口说明", notes = "xxx")
    @GetMapping("/getUser")
    @ResponseBody
    public User getUser() {
        //只要这个实体在请求接口的返回值上（包括泛型），都能映射到实体项中
        return new User("xxx", "yyyy");
    }

    @PostMapping("/postUser")
    public String postUser() {
        return "post ok";
    }

    @PutMapping("/putUser")
    public String putUser(String u,String p) {
        System.out.println(u);
        System.out.println(p);
        return "put ok";
    }

    @DeleteMapping("/delUser")
    public String delUser() {
        return "delete ok";
    }

}
