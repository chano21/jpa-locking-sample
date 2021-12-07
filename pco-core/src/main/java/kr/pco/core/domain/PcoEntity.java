package kr.pco.core.domain;

import java.time.LocalDateTime;

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
import lombok.ToString;

@Entity
@Table(name = "tb_pco")
@Builder
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class PcoEntity extends BaseEntity{
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "pco_name", nullable = true)
	private String pcoName;
	
	
	@Column(name = "complete_at")
	LocalDateTime completedAt;
	
	@Column(name = "user_name")
	private String userName;

	@Column(name = "age")
	private Integer age;

	@Column(name = "image_url")
	private String imageUrl;

	

}
