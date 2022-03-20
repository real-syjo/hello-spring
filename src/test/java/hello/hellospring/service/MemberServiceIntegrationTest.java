package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

class MemberServiceIntegrationTest {
	
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
	
	 @Test
	 public void 회원가입() throws Exception {
		// Given
		Member member = new Member();
		member.setName("spring100");
		// When
		Long saveId = memberService.join(member);
		// Then
		Member findMember = memberRepository.findById(saveId).get();
		assertEquals(member.getName(), findMember.getName());
	}
	 
	 @Test
	 public void 중복_회원_예외() throws Exception {
		 //Given
		 Member member1 = new Member();
		 member1.setName("spring");
		 
		 Member member2 = new Member();
		 member2.setName("spring");
		 //When
		 memberService.join(member1);
		 IllegalStateException e = assertThrows(IllegalStateException.class,
				 () -> memberService.join(member2));//예외가 발생해야 한다.
		 
		 assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		 }
	}