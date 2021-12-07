package kr.pco.app;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.pco.core.code.PcoCode.YN;
import kr.pco.core.domain.ProductEntity;
import kr.pco.infra.JPAConfig;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@ContextConfiguration(classes = { JPAConfig.class })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@ExtendWith(MockitoExtension.class)
public class ProductTest {

	@Autowired
	private EntityManager em;

	@AfterEach
	void after() {
		System.out.println("애프터");
		ProductEntity changeForproduct = em.find(ProductEntity.class, 13223);
		System.out.println(changeForproduct);
	//	changeProductCommit("dd");
	}
	
	
	@Test
	@DisplayName("상품변경 테스트")
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	void productChangeTest() {
		System.out.println("트랜잭션1");
		ProductEntity product1 = ProductEntity.builder()
				.productSeq(13223)
				.productName("상품").purchaseStatus(YN.Y).build();
		
		em.persist(product1);
		em.flush();
		em.clear();
		
		changeProductCommit("상품"+13223);

		em.clear();
		ProductEntity changeForproduct = em.find(ProductEntity.class, 13223);
		ProductEntity changeForproduct1 = em.find(ProductEntity.class, 1);
		System.out.println("일번 "  +changeForproduct1);
		System.out.println(changeForproduct);
	}

	//@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void changeProductCommit(String name) {
		
//		System.out.println("트랜잭션2");
//		ProductEntity product = em.find(ProductEntity.class, 1);
//		product.changeProductName(name);
//		System.out.println("나아라 " + product +"이름 :" + name);
		
		throw new RuntimeException();
//		System.out.println("트랜잭션2");
//		ProductEntity product = em.find(ProductEntity.class, 1);
//		product.changeProductName(name);
//		System.out.println("나아라 " + product +"이름 :" + name);
	}
}
