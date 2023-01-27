package s53_dbs.service;

import s53_dbs.mapper.db1.UserMapper1;
import s53_dbs.mapper.db2.UserMapper2;
import s53_dbs.model.User1Model;
import s53_dbs.model.User2Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper1 userMapper1;

    @Autowired
    private UserMapper2 userMapper2;

    // 事务
    @Transactional(transactionManager="db1Txn",rollbackFor = Exception.class)
    @Override
    public User1Model insert(User1Model user2Model) {
        userMapper1.insert(user2Model);
        return user2Model;
    }

    @Transactional(transactionManager = "db2Txn",rollbackFor = Exception.class)
    @Override
    public User2Model insert2(User2Model user2Model) {
        userMapper2.insert(user2Model);
        return user2Model;
    }


    @Transactional(transactionManager = "db1Txn",propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<User1Model> getList() {
        return userMapper1.getList();
    }


    @Transactional(transactionManager = "db2Txn",propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<User2Model> getList2() {
        return userMapper2.getList();
    }

}
