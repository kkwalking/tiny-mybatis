package com.kelton.tinymybatis.dao;

import com.kelton.tinymybatis.po.User;

/**
 * @Author zhouzekun
 * @Date 2024/5/8 9:07
 */
public interface UserDao {

    User queryUserInfoById(Long id);
}
