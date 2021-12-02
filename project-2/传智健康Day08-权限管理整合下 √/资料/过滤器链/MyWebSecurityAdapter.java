package com.itheima.config;

import com.itheima.filter.JWTAuthenticationFilter;
import com.itheima.filter.JWTAuthorizationFilter;
import com.itheima.filter.JwtProperties;
import com.itheima.userdetailservice.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)//  开发 注解 权限配置
@EnableConfigurationProperties(JwtProperties.class)
public class MyWebSecurityAdapter extends WebSecurityConfigurerAdapter {

@Autowired
private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtProperties properties;

    //     表单 账号和密码   tom   123  ---
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //   认证管理器对象  配置用户认证信息
        auth.userDetailsService(myUserDetailsService).passwordEncoder(createPasswordEncoder());
    }

    //  使用加密器对象 对密密码进行加密
    @Bean
    public  BCryptPasswordEncoder  createPasswordEncoder(){
         return  new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().
                disable().
                addFilter(new JWTAuthenticationFilter(super.authenticationManager(),properties))
                .addFilter(new JWTAuthorizationFilter(super.authenticationManager(),properties))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 禁用session
    }
}
