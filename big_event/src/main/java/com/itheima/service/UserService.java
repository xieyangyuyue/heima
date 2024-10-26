package com.itheima.service;

import com.itheima.pojo.User;

public interface UserService {
    //根据用户名查询对象
    User findByUserName(String username);
    //注册
    void register(String username, String password);
    //更新
    void update(User user);

    //更新图像
    void updateAvatar(String avatarUrl);
    //更新密码
    void updatePwd(String newPwd);
}
