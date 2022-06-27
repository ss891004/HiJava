import hm.s03_redis.BookService;
import hm.s03_redis.MainConfig2;
import org.junit.Test;
import org.redisson.spring.cache.RedissonCache;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class s03 {


    @Test
    public void test7() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig2.class);
        BookService bookService = context.getBean(BookService.class);
        System.out.println(bookService.list());
        System.out.println(bookService.list());
        {
            System.out.println("下面打印出cache1缓存中的key列表");
            RedissonSpringCacheManager cacheManager = context.getBean(RedissonSpringCacheManager.class);
            RedissonCache cache1 = (RedissonCache) cacheManager.getCache("cache1");
            cache1.getNativeCache().keySet().stream().forEach(System.out::println);
        }
    }
}
