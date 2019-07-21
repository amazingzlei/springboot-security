package com.fh.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * security相关的配置必须使用该注解，该注解底层也是@Configuration,
 * 所有这也是一个配置类,此外还必须继承WebSecurityConfigurerAdapter
 */
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 定制请求的授权规则
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1").hasRole("VIP1")
                .antMatchers("/level2").hasRole("VIP2")
                .antMatchers("/level3").hasRole("VIP3");
        // 开启自动配置的登录功能  效果:如果没有登录或者没有权限就返回登录页面
        // 如果是post形式的login请求则时处理登录请求
        // 如果是get形式login请求则时请求登录页面
        // 如果定制了loginPage，则其参数必须有"/"，否则报错，并且上诉的请求地址login都换成loginPage的参数
        // 如果设置了自定义的登录页面，还可以通过usernameParameter和passwordParameter方法
        // 设置接收登录的参数
        http.formLogin().usernameParameter("user").passwordParameter("pwd").loginPage("/userlogin");

        // 开启自动注销 注销成功后默认返回登录页面，也可以通过logoutSuccessUrl()方法
        // 指定返回页面
        http.logout().logoutSuccessUrl("/welcome");
//        http.logout();

        // 开启记住我的功能,当用户点击记住我之后就能发送cookie给浏览器
        // 当用户注销后，该cookie就会删除
        // 如果自定义了登录页面可以通过rememberMeParameter方法设置接收的记住我参数
        http.rememberMe().rememberMeParameter("remember");
    }

    // 定义认证规则

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 实际开发过程用户信息是从数据库中获取,所以实际调用jdbcAuthentication方法
        // springboot2.0版本需要添加passwordEncoder方法，否则报错
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("zenglei").password("123456").roles("VIP3")
        .and().withUser("wuxingyu").password("123456").roles("VIP2").and().withUser("huangyaxing").password("123456").roles("VIP1");
//        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder());
//        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).;
    }
}
