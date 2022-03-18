package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

public class MemberService {
	
	public MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberReposiotry) {
		this.memberRepository = memberReposiotry;
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
		memberRepository.findByName(member.getName())
		.ifPresent(m ->{
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
