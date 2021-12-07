package kr.pco.repo.Impl;

import static kr.pco.core.domain.QMemberEntity.memberEntity;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.pco.core.code.PcoCode.YN;
import kr.pco.core.domain.MemberEntity;
import kr.pco.core.domain.OAuth2Client;
import kr.pco.core.domain.PcoEntity;
import kr.pco.core.domain.ProductEntity;
import kr.pco.core.dto.FindMember;
import kr.pco.core.dto.QFindMember;
import kr.pco.repo.MemberRepository;
import lombok.extern.slf4j.Slf4j;

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
		return queryFactory.select(new QFindMember(memberEntity.memberId, memberEntity.memberPassword))
				.from(memberEntity).fetchOne();
	}

	@Transactional
	@Override
	public void createMember() {
		String id = "testid";
		String password = "sssssfsdfas";
		String encodedPassword = passwordEncoder.encode(password);
		OAuth2Client client1 = OAuth2Client.builder().clientId(id).clientSecret(encodedPassword).scope("dd")
				.grantTypes("password").authorities("ddd").accessTokenValidity(30000)

				.build();
		MemberEntity member = MemberEntity.builder().memberId(id).memberPassword(encodedPassword).build();

		em.persist(client1);
		em.persist(member);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public ProductEntity findProductOne(Integer productSeq) {
		return em.find(ProductEntity.class, productSeq);
	}

	@Override
//	@Lock(value=LockModeType.OPTIMISTIC_FORCE_INCREMENT)
//	public void changeProduct(ProductEntity productSeq, String name) {
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void changeProduct(Integer productSeq, String name) {
		ProductEntity product = em.find(ProductEntity.class, productSeq);
		product.changeProductName(name);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void createProduct() {
		// TODO Auto-generated method stub
		ProductEntity product1 = ProductEntity.builder().productName("이전상품").purchaseStatus(YN.Y).build();
		em.persist(product1);
	}

}
