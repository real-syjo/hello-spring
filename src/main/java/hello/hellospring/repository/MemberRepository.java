package hello.hellospring.repository;

import java.util.List;
import java.util.Optional;

import hello.hellospring.domain.Member;

public interface MemberRepository {

	    // Optional => null 체크(자바8에 들어가있는 기능)
		Member save(Member member) ;
		Optional<Member> findById(Long id);
		Optional<Member> findByName(String name);
		List<Member> findAll();
}
