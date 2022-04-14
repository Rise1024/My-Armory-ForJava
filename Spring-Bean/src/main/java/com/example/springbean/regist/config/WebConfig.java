package com.example.springbean.regist.config;


import com.example.springbean.regist.domain.Hello;
import com.example.springbean.regist.domain.User;
import com.example.springbean.regist.factory.CherryFactoryBean;
import org.springframework.context.annotation.*;

/**
 * @author zds
 */
@Configuration
// @ComponentScan(value = "cc.zds.demo"
        // , excludeFilters = {
        //         @Filter(type = FilterType.ANNOTATION,
        //                 classes = {Controller.class, Repository.class}),
        //         @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = User.class)
        //         @Filter(type = FilterType.CUSTOM, classes = MyTypeFilter.class)
        // }
        // includeFilters = {
        //         @Filter(type = FilterType.ANNOTATION, classes = Service.class)
        // }, useDefaultFilters = false
// )
@Import({Hello.class})
public class WebConfig {

    @Bean
    // @Conditional(MyCondition.class)
    // @Lazy
    // @Scope("prototype")
    public User user() {
        System.out.println("往IOC容器中注册user bean");
        return new User("zds", 18);
    }

    @Bean
    public CherryFactoryBean cherryFactoryBean() {
        return new CherryFactoryBean();
    }
}
