package com.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mp.entity.Employee;
import com.mp.mapper.EmployeeMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class m03_Wrapper {


/**
 * AbstractWrapper 是 QueryWrapper(LambdaQueryWrapper) 和 UpdateWrapper(LambdaUpdateWrapper) 的父类用于生成 sql 的 where 条件，
 * entity 属性也用于生成 sql 的 where 条件，注意 entity 生成的 where 条件与使用各个 api 生成的 where 条件没有任何关联行为

 */
}

