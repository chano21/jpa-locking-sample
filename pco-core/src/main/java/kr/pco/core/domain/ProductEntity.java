package kr.pco.core.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import kr.pco.core.code.PcoCode.YN;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "me_product_info")
@Builder
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class ProductEntity extends BaseEntity {

	@Id
	@Column(name = "pro_seq", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productSeq;

	@Column(name = "pro_name", nullable = false,length = 20)
	private String productName;
	
	@Column(name = "pro_status", nullable = false,length = 1)
	private YN purchaseStatus;
	
	@Version
	private Integer version;
	
	public void changeProductStatus(YN purchaseStatus){
		this.purchaseStatus=purchaseStatus;
	}

	public void changeProductName(String productName){
		this.productName=productName;
	}

}
