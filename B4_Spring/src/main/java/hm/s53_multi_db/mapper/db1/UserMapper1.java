package hm.s53_multi_db.mapper.db1;


import hm.s53_multi_db.model.User1Model;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper1 {
    void insert(User1Model userModel);
    List<User1Model> getList();
}