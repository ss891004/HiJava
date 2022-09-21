package hm.s53_mybatis;



import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    void insert(UserModel userModel);
    List<UserModel> getList();
}