package com.snaildev.dao;

import com.snaildev.bean.UserModel;

public interface IUserDao {
    public void save(UserModel model);
    public int countAll();
}
