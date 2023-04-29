package com.barta.myanimelounge;

import com.barta.myanimelounge.interceptors.IsVerifiedInterceptor;
import com.barta.myanimelounge.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {
    private final UserRepository userRepository;

    public AppConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new IsVerifiedInterceptor(userRepository))
                .excludePathPatterns("/auth/**", "/api/test/**");
    }
}
