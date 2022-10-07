package hm.s53_multi_db.mapper.db2;

import hm.s53_multi_db.model.User2Model;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper3 {
    @Insert("INSERT INTO `t_user2` (name) VALUES (#{name}) ")
    void insert(User2Model user2Model);

    @Select("SELECT id,name FROM t_user2")
    List<User2Model> getList();
}