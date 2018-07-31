package com.snaildev;

import com.snaildev.bean.UserModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<UserModel> {

    @Override
    public UserModel mapRow(ResultSet resultSet, int i) throws SQLException {
        UserModel model = new UserModel();
        model.setId(resultSet.getInt("id"));
        model.setName(resultSet.getString("name"));
        return model;
    }
}
