package com.snaildev.dao.jdbc;

import com.snaildev.bean.UserModel;
import com.snaildev.dao.IUserDao;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import java.util.HashMap;

public class UserJdbcDaoImpl extends NamedParameterJdbcDaoSupport implements IUserDao {
    private static final String INSERT_SQL = "INSERT INTO test(name) VALUES(:name)";
    private static final String COUNT_SQL = "SELECT COUNT(*) FROM test";

    @Override
    public void save(UserModel model) {
        getNamedParameterJdbcTemplate().update(INSERT_SQL, new BeanPropertySqlParameterSource(model));
    }

    @Override
    public int countAll() {
        return (int) getNamedParameterJdbcTemplate().queryForObject(COUNT_SQL, new HashMap<String, Object>(), Integer.class);
    }
}
