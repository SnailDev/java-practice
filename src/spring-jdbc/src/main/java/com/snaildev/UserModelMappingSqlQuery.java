package com.snaildev;

import com.snaildev.bean.UserModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class UserModelMappingSqlQuery extends MappingSqlQuery {
    public UserModelMappingSqlQuery(JdbcTemplate jdbcTemplate) {
        super.setDataSource(jdbcTemplate.getDataSource());
        super.setSql("SELECT * FROM test WHERE name=:name");
        super.declareParameter(new SqlParameter("name", Types.VARCHAR));
        compile();
    }

    @Override
    protected UserModel mapRow(ResultSet resultSet, int i) throws SQLException {
        UserModel model = new UserModel();
        model.setId(resultSet.getInt("id"));
        model.setName(resultSet.getString("name"));
        return model;
    }
}
