package hello.core.Dependency;

import hello.core.AutoAppConfig;
import hello.core.ComponentScan.filter.BeanA;
import hello.core.Member.Member;
import hello.core.Member.MemberRepository;
import hello.core.Member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;

public class DependencyInjectionTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

    @Test
    @DisplayName("수정자 메서드 주입 테스트")
    void SetterDITest() {
        MemberServiceImpl memberServiceImpl = ac.getBean("memberServiceImpl", MemberServiceImpl.class);
        MemberRepository memberRepository = memberServiceImpl.getMemberRepository();
        System.out.println("memberRepository = " + memberRepository);
        assertThat(memberRepository).isNull();
    }

    @Test
    @DisplayName("필드 주입 테스트")
    void FieldDITest() {
        MemberServiceImpl memberServiceImpl = ac.getBean("memberServiceImpl", MemberServiceImpl.class);
        MemberRepository memberRepository = memberServiceImpl.getMemberRepository();
        System.out.println("memberRepository = " + memberRepository);
        assertThat(memberRepository).isNotNull();
    }

    @Test
    @DisplayName("일반 메서드 주입 데스트")
    void NoramlMethodDITest() {
        MemberServiceImpl memberServiceImpl = ac.getBean("memberServiceImpl", MemberServiceImpl.class);
        MemberRepository memberRepository = memberServiceImpl.getMemberRepository();
        System.out.println("memberRepository = " + memberRepository);
        assertThat(memberRepository).isNotNull();
    }

}
