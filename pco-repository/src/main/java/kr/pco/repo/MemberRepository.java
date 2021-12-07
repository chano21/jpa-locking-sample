package kr.pco.repo;

import kr.pco.core.domain.ProductEntity;
import kr.pco.core.dto.FindMember;

public interface MemberRepository {

	FindMember findByMemberName();

	void createMember();


	ProductEntity findProductOne(Integer productSeq);

	void createProduct();

//	void changeProduct(ProductEntity productSeq, String name);
	void changeProduct(Integer productSeq, String name);

}
