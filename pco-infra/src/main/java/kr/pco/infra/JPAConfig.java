package kr.pco.infra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Configuration
@EnableJpaRepositories
//@ComponentScan({"kr.pco.*"})
@EntityScan("kr.pco.core.domain")
@EnableJpaAuditing
public class JPAConfig {
	
	 @PersistenceContext
	    private EntityManager entityManager;

	    @Bean
	    public JPAQueryFactory jpaQueryFactory() {
	        return new JPAQueryFactory(entityManager);
	    }
	
}
