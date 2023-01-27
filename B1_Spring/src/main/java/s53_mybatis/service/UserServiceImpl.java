package s53_mybatis.service;

import s53_mybatis.model.UserModel;
import s53_mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserModel insert(UserModel userModel) {
        userMapper.insert(userModel);
        return userModel;
    }

    @Override
    public List<UserModel> getList() {
        return userMapper.getList();
    }




}
