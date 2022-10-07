package com.mp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// @ApiModel注解的时候有个坑 就是必须在controller 使用 @RequestBody 注解 否则无法显示models
@ApiModel("用户实体")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @ApiModelProperty("用户名")
    public String username;
    @ApiModelProperty("密码")
    public String password;

}