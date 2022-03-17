package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

public class MemberService {
	
	private MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberReposiotry) {
		this.memberRepository = memberRepository;
	}
	
	
	/* 회원가입 */
	public Long join(Member member) {
		//같은 이름이 있는 중복회원 안됨 X
		validateDuplicateMember(member);//중복회원검증
		memberRepository.save(member);
		return member.getId();
	}
	
	/*같은 이름이 있는 중복회원 체크 */
	private void validateDuplicateMember(Member member) {
		Optional<Member> result = memberRepository.findByName(member.getName());
		//result.ifPresent => 값이 있으면?
		result.ifPresent(m ->{
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		});
	}
	
	/* 전체회원 조회 */
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}
	
}
