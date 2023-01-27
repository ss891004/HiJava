package s53_dbs.service;

import s53_dbs.model.User1Model;
import s53_dbs.model.User2Model;

import java.util.List;

public interface UserService {

    // 插入用户信息
    User1Model insert(User1Model userModel);

    User2Model insert2(User2Model userModel);

    // 查询用户所有记录
    List<User1Model> getList();

    // 查询用户所有记录
    List<User2Model> getList2();



}
