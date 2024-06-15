package com.ambientese.grupo5.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import com.ambientese.grupo5.Filters.AuthFilter;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilter() {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthFilter());
        registrationBean.addUrlPatterns("/api/*", "/funcionarios", "/empresas", "/start-avaliacao", "/avaliacao", "/result-avaliacao");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
