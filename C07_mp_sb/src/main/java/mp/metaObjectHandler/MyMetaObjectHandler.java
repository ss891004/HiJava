package mp.metaObjectHandler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

/**
 * 自动填充处理器
 */
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        System.out.println("start insert fill ....");
        this.strictInsertFill(metaObject, "status",String.class,"插入");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        System.out.println("start update fill ....");
        this.strictUpdateFill(metaObject, "status",String.class,"更新");
    }
}
