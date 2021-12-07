package kr.pco.app;

import static kr.pco.core.domain.QPcoEntity.pcoEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.pco.core.domain.PcoEntity;
import kr.pco.infra.JPAConfig;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@ContextConfiguration(classes = { JPAConfig.class })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@ExtendWith(MockitoExtension.class)
public class BatchTest {

	@Autowired
	private EntityManager em;

	@Autowired
	private JPAQueryFactory qeryFactory;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	int batchSize = 50000;

	@Test
	@DisplayName("배치 50000개 테스트")
	@Transactional
	void batchInsertTest() {
		int insertSize = 50000;
		List<PcoEntity> pco = new ArrayList<>();

		IntStream.range(0, insertSize).forEach(i -> {
			pco.add(PcoEntity.builder().pcoName("이름 " + i).userName("유저이름" + i).age(i).build());
		});

		batchInsert(batchSize, insertSize, pco);
		List<PcoEntity> batched = qeryFactory.selectFrom(pcoEntity).fetch();
		batched.forEach(System.out::println);
		assertEquals(batched.size(), insertSize);
	}

	@Test
	@DisplayName("NO배치 10000개 테스트")
	@Transactional
	void NoBatchInsertTest() {
		int insertSize = 50000;
		IntStream.range(0, insertSize).forEach(i -> {
			em.persist(PcoEntity.builder().pcoName("이름 " + i).userName("유저이름" + i).age(i).build());
		});

		List<PcoEntity> batched = qeryFactory.selectFrom(pcoEntity).fetch();
		batched.forEach(System.out::println);
		assertEquals(batched.size(), insertSize);
	}

	private int batchInsert(int batchSize, int batchCount, List<PcoEntity> pco) {
		jdbcTemplate.batchUpdate("INSERT INTO tb_pco (`pco_name`,`user_name`,`age`) VALUES (?, ?, ?)",
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setString(1, pco.get(i).getPcoName());
						ps.setString(2, pco.get(i).getUserName());
						ps.setString(3, String.valueOf(pco.get(i).getAge()));
					}

					@Override
					public int getBatchSize() {
						return pco.size();
					}
				});
		pco.clear();
		batchCount++;
		return batchCount;
	}
}
