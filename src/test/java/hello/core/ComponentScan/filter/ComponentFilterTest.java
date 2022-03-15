package hello.core.ComponentScan.filter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ComponentFilterTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(FilterTestAppConfig.class);

    @Test
    @DisplayName("filter 테스트")
    void FilterTest() {

        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {

            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("beanName = " + beanDefinitionName + " bean = " + bean);
            }
        }

        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean(BeanB.class));
        assertThat(ac.getBean(BeanA.class)).isInstanceOf(BeanA.class);
    }

    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class FilterTestAppConfig {
    }
}
