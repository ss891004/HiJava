package s43_tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
@Component
public class UserService4 {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    //先清空表中数据，然后批量插入数据，要么都成功要么都失败
    @Transactional //@1
    public int insertBatch(String... names) {
        int result = 0;
        jdbcTemplate.update("truncate table t_user");
        for (String name : names) {
            result += jdbcTemplate.update("INSERT INTO t_user(name) VALUES (?)", name);
        }
        return result;
    }
    //获取所有用户信息
    public List<Map<String, Object>> userList() {
        return jdbcTemplate.queryForList("SELECT * FROM t_user");
    }
}
/*DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user(
  id int PRIMARY KEY AUTO_INCREMENT,
  name varchar(256) NOT NULL DEFAULT '' COMMENT '姓名'
);*/
