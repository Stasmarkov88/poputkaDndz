package com.easygoapp.config;


import com.easygoapp.security.CustomSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Configuration
@EnableWebSecurity
@ComponentScan("com.easygoapp.service")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService customUserDetailService;

    //It's autowired ! Idea not correct views this!
    @Autowired
    private AuthenticationManagerBuilder auth;

    @Autowired
    AuthenticationSuccessHandler successHandler;


//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(customUserDetailService)
//                .passwordEncoder(passwordEncoder());
//    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // CSRF protection
        http.csrf()
                .disable()
                        // requests rules
                .authorizeRequests()
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/user/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .and().formLogin();

        http.formLogin()
                // login form page
                .loginPage("/")
                .loginProcessingUrl("/j_spring_security_check")
                        // URL login not success
                .successHandler(successHandler)
                .failureUrl("/403")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                        // add permissions to login page to all
                .permitAll();

        http.logout()
                // add permissions to logout to all
                .permitAll()
                        // logout URL
                .logoutUrl("/j_spring_security_logout")
                        // logout successful URL
                .logoutSuccessUrl("/")
                        // invalidation of current session
                .invalidateHttpSession(true);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        auth.userDetailsService(customUserDetailService);
        return auth.build();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new CustomSuccessHandler();
    }

    @Bean
    public SecurityContextLogoutHandler logoutHandler() {return new SecurityContextLogoutHandler();}
}
