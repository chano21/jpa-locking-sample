package kr.pco.service;

import kr.pco.core.dto.FindMember;

public interface MemberService {

	void printBean();
	FindMember findBymemberId(String memberId);
	void createMember();
	void createProduct();
	
}
