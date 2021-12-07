package kr.pco.core.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "me_member_info")
@Builder
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class MemberEntity extends BaseEntity {

	@Id
	@Column(name = "ac_seq", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer memberSeq;

	@Column(name = "usr_id", nullable = false,length = 20)
	private String memberId;

	@Column(name = "usr_pw", nullable = false,length = 500)
	private String memberPassword;
	
}
