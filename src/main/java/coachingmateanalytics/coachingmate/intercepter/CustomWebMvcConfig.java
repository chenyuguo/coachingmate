package coachingmateanalytics.coachingmate.intercepter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/***
 * @Author Saul
 * @Description  TODO:
 * @Date 19:43 13/9/20
 */
@Configuration
public class CustomWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionIntercepter()).addPathPatterns("/*");
//                .excludePathPatterns("/user/login.do").excludePathPatterns("/user/register.do")
//                .excludePathPatterns("/user/send").excludePathPatterns("/index.html");
    }
}
