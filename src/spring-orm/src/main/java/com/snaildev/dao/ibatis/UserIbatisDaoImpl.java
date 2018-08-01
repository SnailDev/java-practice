package com.snaildev.dao.ibatis;

import com.snaildev.dao.IUserDao;
import com.snaildev.model.UserModel;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class UserIbatisDaoImpl extends SqlMapClientDaoSupport implements IUserDao {
    @Override
    public void save(UserModel model) {
        getSqlMapClientTemplate().insert("UserSQL.insert", model);
    }

    @Override
    public int countAll() {
        return (Integer)getSqlMapClientTemplate().queryForObject("UserSQL.countAll");
    }
}
