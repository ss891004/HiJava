package hm.s43_tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class User2Service {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.REQUIRED)
    public void required(String name) {
        this.jdbcTemplate.update("insert into user2(name) VALUES (?)", name);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void required_exception(String name) {
        this.jdbcTemplate.update("insert into user2(name) VALUES (?)", name);
        throw new RuntimeException();
    }
    ///////////////////////////////////////////////////////

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requires_new(String name) {
        this.jdbcTemplate.update("insert into user2(name) VALUES (?)", name);
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requires_new_exception(String name) {
        this.jdbcTemplate.update("insert into user2(name) VALUES (?)", name);
        throw new RuntimeException();
    }

    ///////////////////////////////////////////////////////

    @Transactional(propagation = Propagation.NESTED)
    public void nested(String name) {
        this.jdbcTemplate.update("insert into user2(name) VALUES (?)", name);
    }
    @Transactional(propagation = Propagation.NESTED)
    public void nested_exception(String name) {
        this.jdbcTemplate.update("insert into user2(name) VALUES (?)", name);
        throw new RuntimeException();
    }



}