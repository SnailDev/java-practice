package com.snaildev;

import com.snaildev.UserRowMapper;
import com.snaildev.bean.UserModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlQuery;

import java.sql.Types;
import java.util.Map;

public class UserModelSqlQuery extends SqlQuery<UserModel> {
    public UserModelSqlQuery(JdbcTemplate jdbcTemplate){
        super.setJdbcTemplate(jdbcTemplate);
        super.setSql("SELECT * FROM test WHERE name=?");
        super.declareParameter(new SqlParameter(Types.VARCHAR));
        compile();
    }

    @Override
    protected RowMapper<UserModel> newRowMapper(Object[] objects, Map<?, ?> map) {
        return  new UserRowMapper();
    }
}
