package com.easygoapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Stanislav Markov mailto: stasmarkov88@gmail.com
 */
@Configuration
@Import({
        PersistenceConfig.class,
        SecurityConfig.class
//        MailConfig.class
})
@ComponentScan("com.easygoapp.service")
public class RootConfig {
}
