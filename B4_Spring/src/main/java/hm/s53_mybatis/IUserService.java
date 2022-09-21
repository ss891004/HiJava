package hm.s53_mybatis;

import java.util.List;

public interface IUserService {
    /**
     * 插入用户信息
     *
     * @param userModel
     * @return
     */
    UserModel insert(UserModel userModel);
    /**
     * 查询用户所有记录
     *
     * @return
     */
    List<UserModel> getList();
}

