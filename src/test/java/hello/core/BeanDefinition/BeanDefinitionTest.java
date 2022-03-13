package hello.core.BeanDefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {




    @Test
    @DisplayName("BeanDefinition 출력하기 by annotation")
    void displayBeanDefinitionByAnnotation() {

        // getBeanDefinition을 사용하기 위해서는 ApplicationContext 타입이 아닌,
        // AnnotationConfigApplicationContext 타입을 사용해야 한다.
        // 그 이유는 getBeanDefinition를 사용하기 위해 ApplicationContext가 또 다른 클래스를 상속받아야 하기 때문이다.
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + beanDefinitionName + " beanDefinition = " + beanDefinition);
            }
        }
    }

    @Test
    @DisplayName("BeanDefinition 출력하기 by xml")
    void DisplayBeanDefinitionByXml() {

        // getBeanDefinition을 사용하기 위해서는 ApplicationContext 타입이 아닌,
        // GenericXmlApplicationContext 타입을 사용해야 한다.
        // 그 이유는 getBeanDefinition를 사용하기 위해 ApplicationContext가 또 다른 클래스를 상속받아야 하기 때문이다.
        GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + beanDefinitionName + " beanDefinition = " + beanDefinition);
            }
        }
    }

}
