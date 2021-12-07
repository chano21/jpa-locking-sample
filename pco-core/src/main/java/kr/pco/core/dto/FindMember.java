package kr.pco.core.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FindMember {
	
	@QueryProjection
	public FindMember(String memberId, String password) {
		super();
		this.memberId = memberId;
		this.password = password;
	}
	
	String memberId;
	String password;
}
