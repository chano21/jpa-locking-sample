package kr.pco.repo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.pco.core.domain.PcoEntity;
import kr.pco.core.domain.MemberEntity;
import kr.pco.core.domain.OAuth2Client;
import kr.pco.core.dto.FindMember;
import kr.pco.core.dto.QFindMember;
import kr.pco.repo.MemberRepository;
import lombok.val;

import static kr.pco.core.domain.QMemberEntity.memberEntity;

import javax.persistence.EntityManager;

@Repository
public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberRepository {
	@Autowired
	EntityManager em;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	private final JPAQueryFactory queryFactory;
	
	public MemberRepositoryImpl(JPAQueryFactory queryFactory) {
		super(PcoEntity.class);
		  this.queryFactory = queryFactory;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public FindMember findByMemberName() {
		return queryFactory.
				select(new QFindMember(memberEntity.memberId,memberEntity.memberPassword))
				.from(memberEntity).fetchOne();
	}


	@Override
	public void createMember() {
		String id = "testid";
		String password = "sssssfsdfas";
		String encodedPassword=passwordEncoder.encode(password);
		OAuth2Client client1=OAuth2Client.builder()
				.clientId(id)
				.clientSecret(encodedPassword)
				.scope("dd")
				.grantTypes("password")
				.authorities("ddd")
				.accessTokenValidity(30000)
				
				.build();
		MemberEntity member = MemberEntity.builder()
				.memberId(id)
				.memberPassword(encodedPassword)
				.build();
		
		em.persist(client1);
		em.persist(member);
	}

	@Override
	public void createProduct() {
		
		
	}

	
}
