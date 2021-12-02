package com.itheima.userdetailservice;//package com.itheima.service;

import com.itheima.health.pojo.Permission;
import com.itheima.health.service.UserService;
import com.itheima.health.vo.RoleVO;
import com.itheima.health.vo.UserVO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//  数据库  认证 对象实例   由配置类 MyWebSecueiryAdapter 加载此对象
@Component
public class MyUserDetailService implements UserDetailsService {

       @Reference
      private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         //  根据账号 从数据库 查询 用户所有信息  封装   UserDetails 对象 中
          UserVO userVO =  userService.findUserInfoByUsername(username);
           if(userVO==null){
               return null;
           }
        Set<RoleVO> roles = userVO.getRoles();
        List<GrantedAuthority>  authorities = new ArrayList<GrantedAuthority>();
        for (RoleVO role : roles) {
            //   当前用户所有角色关键字  封装到 集合中
            authorities.add(new SimpleGrantedAuthority(role.getKeyword()));
              //  获取当前用户权限关键字
            Set<Permission> permissions = role.getPermissionSet();
            for (Permission permission : permissions) {
                authorities.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }
        User  user =   new User(userVO.getUsername(),userVO.getPassword(),authorities);
        return user;
    }
}








