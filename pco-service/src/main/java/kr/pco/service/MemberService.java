package kr.pco.service;

import kr.pco.core.domain.ProductEntity;
import kr.pco.core.dto.FindMember;

public interface MemberService {

	void printBean();
	FindMember findBymemberId(String memberId);
	void createMember();
	void createProduct();
	ProductEntity findProduct(Integer productSeq);
	void changeProduct(Integer productSeq,String name);
}
