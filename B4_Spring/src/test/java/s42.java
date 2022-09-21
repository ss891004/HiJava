import hm.s42_jdbcTemplate.DataSourceUtils;
import hm.s42_jdbcTemplate.User;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.Test;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.lang.Nullable;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


//JdbcTemplate实现增删改查
public class s42 {

    /**
     * JdbcTemplate使用步骤
     * 1. 创建数据源DataSource
     * 2. 创建JdbcTemplate，new JdbcTemplate(dataSource)
     * 3. 调用JdbcTemplate的方法操作db，如增删改查
     */

    @Test
    public void testSelect() {
        //1.创建数据源DataSource
        DataSource dataSource = DataSourceUtils.getDataSource();
        //2.创建JdbcTemplate，new JdbcTemplate(dataSource)
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //3.调用JdbcTemplate的方法操作db，如增删改查
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from t_user");
        System.out.println(maps);
    }

    // JdbcTemplate中以update开头的方法，用来执行增、删、改操作
    @Test
    public void testInsert() {
        DataSource dataSource = DataSourceUtils.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //3.调用JdbcTemplate的方法操作db，如增删改查
        int rows = jdbcTemplate.update("insert into t_user values(null,'xxx')");
        System.out.println(rows);
        // sql中使用?作为占位符。
        rows = jdbcTemplate.update("INSERT INTO t_user (name) VALUE (?)", "mybatis系列");
        System.out.println(rows);
        rows = jdbcTemplate.update("INSERT INTO t_user (name) VALUE (?)", new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, "mysql系列");
            }
        });
        System.out.println(rows);
    }

    // 获取自增列的值
    @Test
    public void test4() {
        DataSource dataSource = DataSourceUtils.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO t_user (name) VALUE (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowCount = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                //手动创建PreparedStatement，注意第二个参数：Statement.RETURN_GENERATED_KEYS
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, "获取自增列的值");
                return ps;
            }
        }, keyHolder);
        System.out.println("新记录id：" + keyHolder.getKey().intValue());
    }

    @Test
    public void test5() {
        DataSource dataSource = DataSourceUtils.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Object[]> list = Arrays.asList(
                new Object[]{"刘德华"},
                new Object[]{"郭富城"},
                new Object[]{"张学友"},
                new Object[]{"黎明"});
        int[] updateRows = jdbcTemplate.batchUpdate("INSERT INTO t_user (name) VALUE (?)", list);
        for (int updateRow : updateRows) {
            System.out.println(updateRow);
        }
    }


    @Test
    public void test6() {
        DataSource dataSource = DataSourceUtils.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String name = jdbcTemplate.queryForObject("select name from t_user where id = ?", String.class, 114);
        System.out.println(name);
    }

    @Test
    public void test7() {
        DataSource dataSource = DataSourceUtils.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String name = jdbcTemplate.queryForObject("select name from t_user where id = ?", String.class, 0);
        System.out.println(name);
    }


    @Test
    public void test8() {
        DataSource dataSource = DataSourceUtils.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //<T> List<T> queryForList(String sql, Class<T> elementType);
        List<String> list1 = jdbcTemplate.queryForList("select name from t_user where id>131", String.class);
        System.out.println("list1:" + list1);
        //<T> List<T> queryForList(String sql, Class<T> elementType, @Nullable Object... args);
        List<String> list2 = jdbcTemplate.queryForList("select name from t_user where id>?", String.class, 131);
        System.out.println("list2:" + list2);
        //<T> List<T> queryForList(String sql, Object[] args, Class<T> elementType);
        List<String> list3 = jdbcTemplate.queryForList("select name from t_user where id>?", new Object[]{131}, String.class);
        System.out.println("list3:" + list3);
        //<T> List<T> queryForList(String sql, Object[] args, int[] argTypes, Class<T> elementType);
        List<String> list4 = jdbcTemplate.queryForList("select name from t_user where id>?", new Object[]{131}, new int[]{Types.INTEGER}, String.class);
        System.out.println("list4:" + list4);
    }




    // 查询单行记录，将记录转换成一个对象
    @Test
    public void test9() {
        DataSource dataSource = DataSourceUtils.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "select id,name from t_user where id = ?";
        //查询id为34的用户信息
        User user = jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
            @Nullable
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(1));
                return user;
            }
        }, 9);
        System.out.println(user);
    }

    // 查询单行记录，返回指定的javabean
    @Test
    public void test10() {
        DataSource dataSource = DataSourceUtils.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "select id,name from t_user where id = ?";
        //查询id为34的用户信息
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        User user = jdbcTemplate.queryForObject(sql, rowMapper, 134);
        System.out.println(user);
    }

    // 查询多列多行，每行结果为一个Map
    @Test
    public void test11() {
        DataSource dataSource = DataSourceUtils.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "select id,name from t_user where id>?";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, 4);
        System.out.println(maps);
    }


    //查询多列多行，将结果映射为javabean (空数据不会异常)
    @Test
    public void test12() {
        DataSource dataSource = DataSourceUtils.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "select id,name from t_user where id>?";
        List<User> maps = jdbcTemplate.query(sql, new RowMapper<User>() {
            @Nullable
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                return user;
            }
        }, 1);
        System.out.println(maps);
    }

    @Test
    public void test13() {
        DataSource dataSource = DataSourceUtils.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "select id,name from t_user where id>?";
        List<User> maps = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), 1);
        System.out.println(maps);
    }
}
