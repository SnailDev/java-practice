package com.snaildev;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import java.sql.Types;

public class MysqlLengthFunction extends StoredProcedure {
    public MysqlLengthFunction(JdbcTemplate jdbcTemplate) {
        super.setJdbcTemplate(jdbcTemplate);
        super.setSql("FUNCTION_TEST");
        super.setFunction(true);
        super.declareParameter(new SqlOutParameter("result", Types.INTEGER));
        super.declareParameter(new SqlParameter("str", Types.VARCHAR));
        compile();
    }
}
