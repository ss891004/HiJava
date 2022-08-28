package com.hm.controller;

import com.hm.model.User1Model;
import com.hm.model.User2Model;
import com.hm.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "springboot-mybatis 多数据源")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${image-url}")
    private String imageUrl;

    @ApiOperation(value = "1", httpMethod = "GET")
    @GetMapping("user/insert")
    public String insert() {
        User1Model userModel = User1Model.builder().name("张点都德三").build();
        return userService.insert(userModel).toString();
    }

    @ApiOperation(value = "2", httpMethod = "GET")
    @GetMapping("user/insert2")
    public String insert2() {
        User2Model user2Model = User2Model.builder().name("aaaaaaaaaa").build();
        return userService.insert2(user2Model).toString();
    }

    @ApiOperation(value = "3", httpMethod = "GET")
    @RequestMapping(value = "user/getlist", method = RequestMethod.GET)
    public List<User1Model> getList() {
        return userService.getList();
    }

    @ApiOperation(value = "4", httpMethod = "GET")
    @RequestMapping(value = "user/getlist2", method = RequestMethod.GET)
    public List<User2Model> getList2() {
        return userService.getList2();
    }


    @ApiOperation(value = "数据存储到mongodb中", httpMethod = "GET")
    @GetMapping("user/insert3")
    public String insert3() {
        User1Model user2Model = User1Model.builder().id(System.currentTimeMillis()).name("bbb").build();
        return userService.insert3(user2Model).toString();
    }

    @ApiOperation(value = "从mongodb中获取", httpMethod = "GET")
    @RequestMapping(value = "user/getlist3", method = RequestMethod.GET)
    public List<User1Model> getList3() {
        return userService.getList3();
    }


    @GetMapping("/get")
    public String getValue(){
        System.out.println(imageUrl);
        return imageUrl;
    }
}
