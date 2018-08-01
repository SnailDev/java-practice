package com.snaildev.dao.hibernate;

import com.snaildev.dao.IUserDao;
import com.snaildev.model.UserModel;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class UserHibernateDaoImpl extends HibernateDaoSupport implements IUserDao {
    private final static String COUNT_ALL_HSQL = "select count(*) from UserModel";

    @Override
    public void save(UserModel model) {
        getHibernateTemplate().save(model);
    }

    @Override
    public int countAll() {
        Number count = (Number) getHibernateTemplate().find(COUNT_ALL_HSQL).get(0);
        return count.intValue();
    }
}
