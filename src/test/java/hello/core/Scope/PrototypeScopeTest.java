package hello.core.Scope;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeScopeTest {

    @Test
    @DisplayName("프로토타입 스코프 빈 조회")
    void GetPrototypeScopeBean() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeScopeBean.class);

        PrototypeScopeBean prototypeScopeBean1 = ac.getBean(PrototypeScopeBean.class);
        PrototypeScopeBean prototypeScopeBean2 = ac.getBean(PrototypeScopeBean.class);

        System.out.println("prototypeScopeBean1 = " + prototypeScopeBean1);
        System.out.println("prototypeScopeBean2 = " + prototypeScopeBean2);

        assertThat(prototypeScopeBean1).isNotSameAs(prototypeScopeBean2);

        ac.close();
    }

    @Test
    @DisplayName("프로토타입 스코프 빈 DL with ac")
    void PrototypeScopeBeanDLWithAC() {

        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(PrototypeScopeBean.class, SingletonScopeBean.class);


        //clientA
        SingletonScopeBean singletonScopeBean1 = ac.getBean(SingletonScopeBean.class);
        System.out.println("singletonScopeBean1.logic() = " + singletonScopeBean1.logic());


        //clientB
        SingletonScopeBean singletonScopeBean2 = ac.getBean(SingletonScopeBean.class);
        System.out.println("singletonScopeBean2.logic() = " + singletonScopeBean2.logic());


        assertThat(singletonScopeBean1.logic()).isEqualTo(1);
        assertThat(singletonScopeBean2.logic()).isEqualTo(1);
    }


    @Test
    void PrototypeScopeWithSingletonScope() {

        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(PrototypeScopeBean.class, SingletonScopeBean.class);


        //clientA
        SingletonScopeBean singletonScopeBean1 = ac.getBean(SingletonScopeBean.class);
        System.out.println("singletonScopeBean1.logic() = " + singletonScopeBean1.logic());


        //clientB
        SingletonScopeBean singletonScopeBean2 = ac.getBean(SingletonScopeBean.class);
        System.out.println("singletonScopeBean2.logic() = " + singletonScopeBean2.logic());


        assertThat(singletonScopeBean1.logic()).isEqualTo(3);
    }


static class SingletonScopeBean {

    @Autowired
    private Provider<PrototypeScopeBean> prototypeScopeBeanObjectProvider;

    int logic() {
        PrototypeScopeBean prototypeScopeBean = prototypeScopeBeanObjectProvider.get();
        return prototypeScopeBean.add();
    }
}


    @Scope("prototype")
    static class PrototypeScopeBean {

        private int count = 0;

        @PostConstruct
        public void init() {
            System.out.println("PrototypeScopeBean.init" + " : " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeScopeBean.destroy" + " : " + this);
        }

        public int add() {
            return ++this.count;
        }
    }
}

