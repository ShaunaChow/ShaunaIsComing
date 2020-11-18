package top.shauna.shaunaiscoming.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/18 19:38
 * @E-Mail z1023778132@icloud.com
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean filterRegist() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new ShaunaAuthFilter());
        filterRegistrationBean.addUrlPatterns("/shaunafs/*");
        filterRegistrationBean.addUrlPatterns("/repository/*");
        return filterRegistrationBean;
    }
}
