package mp.mapper;

import mp.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author caochenlei
 * @since 2020-10-03
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    public void deleteAll();

}
