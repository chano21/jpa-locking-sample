package kr.pco.service.Impl;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import kr.pco.core.domain.ProductEntity;
import kr.pco.core.dto.FindMember;
import kr.pco.repo.MemberRepository;
import kr.pco.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository repository;

	@Autowired
	EntityManager em;

	public static int count = 0;

	@Override
	public void printBean() {
		// TODO Auto-generated method stub
		System.out.println(repository);
	}

	@Override
	public FindMember findBymemberId(String memberId) {
		// TODO Auto-generated method stub
		return repository.findByMemberName();
	}

	@Override
	public void createMember() {
		repository.createMember();
	}

	@Override
	public void changeProduct(Integer productSeq, String name) {
		try {
			count++;
			repository.changeProduct(productSeq, name);

		} catch (ObjectOptimisticLockingFailureException e) {
			System.out.println("서비스값 : " + productSeq + "이름 " + name + "카운트  " + count);
		}

	}

	@Override
	public void createProduct() {

		repository.createProduct();
		// TODO Auto-generated method stub
	}

	@Override
	public ProductEntity findProduct(Integer productSeq) {
		// TODO Auto-generated method stub
		return repository.findProductOne(productSeq);
	}

}
