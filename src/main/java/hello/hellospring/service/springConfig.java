package hello.hellospring.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;

//자바 코드로 직접 스프링 빈 등록하기
@Configuration
public class springConfig {
	
	private DataSource dataSource;
	
	@Autowired
	public springConfig(DataSource dataSource) {
		 this.dataSource = dataSource;
	}
	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	
	@Bean
	public MemberRepository memberRepository() {
//		return new MemoryMemberRepository();
		//순수JDBC연결
//		return new JdbcMemberRepository(dataSource);
		//통합JDBC연결
		return new JdbcTemplateMemberRepository(dataSource);
	}
}
