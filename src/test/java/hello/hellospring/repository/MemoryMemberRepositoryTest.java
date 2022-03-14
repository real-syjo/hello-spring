package hello.hellospring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;

public class MemoryMemberRepositoryTest {

	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	//테스트가 끝날때 마다 리포지토리를 깔끔하게 지워줌
	@AfterEach //메서드 동작이 끝날때마다 실행 
	public void afterEach() {
		repository.clearStore();
	}
	
	//Test는 서로 의존 관계없이 설계가 되어야 한다. 각자 개별로 실행이 됨
	
	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");
		//저장
		repository.save(member);
		//아이디 값 가져오기
		Member result = repository.findById(member.getId()).get();
		//저장한 아이디와 가져온 아이디 값이 동일한가 Test
		//Test1. junit.jupiter 사용
		//Assertions.assertEquals(member, result);
		//Test2. junit.assertj 사용
		assertThat(member).isEqualTo(result);
	}
	
	@Test
	public void findByName() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		Member result = repository.findByName("spring1").get();
		assertThat(result).isEqualTo(member1);
	}
	
	@Test
	public void findAll() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		List<Member> result = repository.findAll();
		assertThat(result.size()).isEqualTo(2);
	}
	

}
