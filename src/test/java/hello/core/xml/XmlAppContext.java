package hello.core.xml;

import hello.core.Member.Member;
import hello.core.Member.MemberService;
import hello.core.Member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class XmlAppContext {

    //    GenericXmlApplicationContext genericXmlApplicationContext = new GenericXmlApplicationContext("appConfig.xml");
    ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

    @Test
    void xmlAppContextTest() {
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
}
