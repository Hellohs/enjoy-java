package com.dtian.java.design_pattern.structure.proxy.dynamic_proxy.jdk;

/**
 * 用户查询接口实现类
 *
 */
public class UserServiceImpl implements UserService{
    @Override
    public String queryUser(int id) {
        System.out.println("real service do, query user by id, id:" + id);
        return "user a";
    }
}
