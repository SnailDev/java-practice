package com.snaildev.dao.mybatis;

import com.snaildev.dao.IUserDao;
import com.snaildev.model.UserModel;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class UserMybatisDaoImpl extends SqlSessionDaoSupport implements IUserDao {
    @Override
    public void save(UserModel model) {
        getSqlSession().insert("UserSQL.insert", model);
    }

    @Override
    public int countAll() {
        return (Integer) getSqlSession().selectOne("UserSQL.countAll");
    }
}
