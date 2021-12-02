package com.itheima.service;


import com.itheima.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SpringSecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    Map<String,User> map = new HashMap<String,User>();
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //  模拟数据库    zs   123        lisi   123
        initData();
        //  定义zs和lisi  不同的角色和权限
        List<GrantedAuthority> list_zs  = new ArrayList<GrantedAuthority>();
        List<GrantedAuthority> list_lisi  = new ArrayList<GrantedAuthority>();

        //  zs有   ROLE_ADD  角色     角色 以 ROLE_开头   否则 框架当做权限关键字处理
        SimpleGrantedAuthority role_add = new SimpleGrantedAuthority("ROLE_ADD");
        list_zs.add(role_add);

        //  lisi   拥有 ROLE_DELETE  QUERY 角色和权限
        SimpleGrantedAuthority  query = new SimpleGrantedAuthority("ROLE_QUERY");
        SimpleGrantedAuthority  delete = new SimpleGrantedAuthority("DELETE");
        list_lisi.add(query);
        list_lisi.add(delete);

        //  登录用户表单 提交的账号和密码  对应数据库 zs和lisi
        if(username.equals(((User)map.get("zs")).getUsername())){
            String password = map.get("zs").getPassword();
            return  new org.springframework.security.core.userdetails.User(username,password,list_zs);
        }
        //  登录用户表单 提交的账号和密码  对应数据库 zs和lisi
        if(username.equals(((User)map.get("lisi")).getUsername())){
            String password = map.get("lisi").getPassword();
            return  new org.springframework.security.core.userdetails.User(username,password,list_lisi);
        }

        //  如果查询不到user  返回null  认证失败
        return null;
    }

    private void initData() {

        User user = new User();
        user.setUsername("zs");
        user.setPassword(bCryptPasswordEncoder.encode("123"));//  添加用户 密码加密处理

        User user1 = new User();
        user1.setUsername("lisi");
        user1.setPassword(bCryptPasswordEncoder.encode("123"));//  添加用户 密码加密处理
        map.put("zs",user);
        map.put("lisi",user1);
    }
}













