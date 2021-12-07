package kr.pco.repo;

import kr.pco.core.dto.FindMember;

public interface MemberRepository {

	FindMember findByMemberName();

	void createMember();

	void createProduct();

}
