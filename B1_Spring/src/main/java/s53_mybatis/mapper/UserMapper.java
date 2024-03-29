package s53_mybatis.mapper;



import s53_mybatis.model.UserModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    void insert(UserModel userModel);
    List<UserModel> getList();
}