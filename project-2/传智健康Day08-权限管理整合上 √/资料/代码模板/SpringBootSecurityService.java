package com.itheima.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启方法权限注解支持
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true) //开启方法权限注解支持
public class SpringBootSecurityService extends WebSecurityConfigurerAdapter{

    @Autowired  //  注入 用户UserDetailService 实现类
    private UserDetailsService springSecurityUserDetailsService;


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //  基于内存  定义认证用户的账号 、密码 、以及权限关键字
//        auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN");
//        String password = passwordEncoder().encode("1234");
//        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser("admin").password(password).roles("QUERY");
//    }
    //        auth.inMemoryAuthentication()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("admin")
//                .password("{noop}admin")
//                .roles("ADMIN");


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() 								// 定义当需要用户登录时候，转到的登录页面。
                .loginPage("/login.html")	 					// 设置登录页面
                .loginProcessingUrl("/login.do") 			// 表单提交的action 地址：
                .defaultSuccessUrl("/page/index.html")// 登录成功之后，默认跳转的页面
                .and()
                .authorizeRequests().antMatchers("/page/find.html").hasRole("QUERY")//  认证的用户只有ROLE_QUERY角色才可以访问该资源
                .antMatchers("/page/add.html").hasRole("ADD")//  认证的用户只有ROLE_ADD才可以访问该资源
                .antMatchers("/page/delete.html").hasAuthority("DELETE")//  认证的用户只有DELETE才可以访问该资源
                .antMatchers("/page/**").authenticated()// 表示 page下的所有资源 都需要认证才可以访问
                .and().exceptionHandling().accessDeniedPage("/error.html") // 表示 403 无权限  跳转到友好页面
                .and()  //   logoutUrl  配置处理退出的请求路径     logoutSuccessUrl  退出之后跳转的资源地址   invalidateHttpSession  注销内置session对象
                .logout().logoutUrl("/logout.do").logoutSuccessUrl("/login.html").invalidateHttpSession(true)
                .and()
                .csrf().disable(); //  关闭  csrf过滤器
       }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin() 								// 定义当需要用户登录时候，转到的登录页面。
//                .loginPage("/login.html")	 					// 设置登录页面
//                .loginProcessingUrl("/login.do") 			// 自定义的登录接口
//                .defaultSuccessUrl("/pages/main.html")	// 登录成功之后，默认跳转的页面
//                .and().authorizeRequests()		// 定义哪些URL需要被保护、哪些不需要被保护
//                .antMatchers("/pages/**").authenticated()		// 设置所有人都可以访问登录页面
//                .and().csrf().disable()	// 关闭csrf防护
//                .headers().frameOptions().sameOrigin()
//                .and().logout().logoutUrl("/logout.do").logoutSuccessUrl("/login.html").invalidateHttpSession(true);			//  iframe放行
//    }


//  静态资源 放行
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/**/*.js");
    }


//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/img/**/*", "/**/*.css", "/**/*.js","/template/**","/plugins/**");
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();//  密码加密 对象
    }

     //  数据库版的用户账号、密码、权限关键字信息
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         //  编写UserDetiaiService实现类  注入即可
        auth.userDetailsService(springSecurityUserDetailsService).passwordEncoder(passwordEncoder());
    }


    /**
     * Spring Security默认是禁用注解的，要想开启注解，要在继承WebSecurityConfigurerAdapter的类加@EnableGlobalMethodSecurity注解，
     * 并在该类中将AuthenticationManager定义为Bean。
     * @return
     * @throws Exception
     */
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
    }

