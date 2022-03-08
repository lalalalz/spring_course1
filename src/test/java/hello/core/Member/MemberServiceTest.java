package hello.core.Member;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl(new MemoryMemberRepository());

    @Test
    void join() {
        // given
        Member member = new Member(1L, "memberA", Grade.BASIC);

        // when
        memberService.join(member);

        // then
        Assertions.assertThat(member).isEqualTo(memberService.findMember(1L));
    }


}