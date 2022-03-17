package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

class MemberServiceTest {

	MemberService memberService;
	MemoryMemberRepository memberRepository;
	
	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}
	
	//테스트가 끝날때 마다 리포지토리를 깔끔하게 지워줌(메모리 클리어)
	@AfterEach //메서드 동작이 끝날때마다 실행 
	public void afterEach() {
		memberRepository.clearStore();
	}
	
	/* 테스트 코드는 한글로 적어도 됩니다.
	 * @Test 
	 * void 회원가입() {
	 * }
	 */
	
	@Test
	void join() {
		//given
		Member member = new Member();
		member.setName("hello");
		//when
		Long saveId = memberService.join(member);
		//then 
        Member findMember =  memberService.findOne(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}
	
	@Test
	public void 중복_회원_예외() {
		//given
		Member mem1 = new Member();
		mem1.setName("spring1");

		Member mem2 = new Member();
		mem2.setName("spring2");
		//when
		memberService.join(mem1);
		IllegalStateException e =  Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(mem2));
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		
		
		
		/*
		  try { memberService.join(mem2); fail("예외가 발생해야 합니다."); } catch
		  (IllegalStateException e ) {
		  assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); }
		 */
		
		
		//then
		
	}
	
	@Test
	void findMembers() {
		
	}
	
	@Test
	void findOne() {
		
	}
	
	
}
