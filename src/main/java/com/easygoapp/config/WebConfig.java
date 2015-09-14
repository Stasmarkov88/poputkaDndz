package com.easygoapp.config;

import com.easygoapp.service.impl.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/**
 * Created by Stanislav Markov mailto: stasmarkov88@gmail.com
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.easygoapp.controllers")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
        registry.addResourceHandler("/WEB-INF/jsp/**").addResourceLocations("/jsp/");
        registry.addResourceHandler("/WEB-INF/templates/**").addResourceLocations("/templates/");
    }

    @Bean
    public ViewResolver viewResolver() {
        return new TilesViewResolver();
    }

    @Bean
    public UserDetailsService getUserDetailsService() {
        return new CustomUserDetailService();
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions("/WEB-INF/tiles-definitions.xml");
        tilesConfigurer.setCheckRefresh(true);
        return tilesConfigurer;
    }
}
