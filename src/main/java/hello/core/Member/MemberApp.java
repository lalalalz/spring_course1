package hello.core.Member;

public class MemberApp {

    public static void main(String[] args) {

        MemberService memberService = new MemberServiceImpl();

        Member member = new Member(1L, "memberA", Grade.BASIC);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println(member.getName());
        System.out.println(findMember.getName());
    }
}
