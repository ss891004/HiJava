package mp.incrementer;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.util.concurrent.atomic.AtomicLong;

public class CustomIdGenerator implements IdentifierGenerator {
    private final AtomicLong al = new AtomicLong(1);

    @Override
    public Long nextId(Object entity) {
        //可以将当前传入的class全类名来作为bizKey或者提取参数来生成bizKey进行分布式Id调用生成
        String bizKey = entity.getClass().getName();
        System.out.println("bizKey:" + bizKey);
        MetaObject metaObject = SystemMetaObject.forObject(entity);
        String name = (String) metaObject.getValue("pname");
        final long id = al.getAndAdd(1);
        System.out.println("为" + name + "生成主键值->:" + id);
        return id;
    }
}
