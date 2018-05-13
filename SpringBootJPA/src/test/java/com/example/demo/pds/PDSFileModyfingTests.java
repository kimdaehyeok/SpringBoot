package com.example.demo.pds;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.pds_repository.PDSFileRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PDSFileModyfingTests 
{
	@Autowired
	private PDSFileRepository pdsFileRepo;
	
	/* @Query를 이용해서 update문을 실행하는데, delete나 update를 사용하는 경우 
	 * 반드시 @Transactional 처리를 필요로 하게 된다.
	 * 
	 * 정상적인 처리가 수행되었음에도 불구하고, 데이터베이스에 최종 결과가 반영되지 않는 것은 스프링 테스트에서
	 * @Transactional이 기본적으로 롤백 처리를 시도하기 때문이다.
	 * 그러므로 @Commit을 사용하여 데이터베이스에 저장할 수 있다.
	 * */
	
	/* 스프링 트랜잭션 추상화의 핵심 인터페이스는 PlatformTransactionManager이다.
	 * 모든 스프링의 트랜잭션 기능과 코드는 이 인터페이스를 통해 로우레벨의 트랜잭션 서비스를 이용할 수 있다.
	 * PlatformTransactionManager는 getTransaction(), commit(), rollback() 3가지의 메소드가 있다.
	 * PlatformTransactionManager는 트랜잭션의 경계를 지정하는데 사용하며, 트랜잭션의 시작과 종료를 전파 기법에 따라 자유롭게 조합, 확장이 가능하다.
	 * 그러므로, begin과 같은 메소드 대신 get을 사용한 것이다.
	 * 
	 * PlatformTransactionManager를 구현하는 클래스는 여러개가 있다.
	 * DataSourceTransactionManager, JpaTransactionManager, HibernateTransactionManager 등이 있으며,
	 * 하나 이상의 DB 또는 트랜잭션 리소스가 참여하는 글로벌 트랜잭션을 적용하려면 JTA를 이용해야 한다. (JtaTransactionManager)
	 * */
	
	/* URL : https://docs.spring.io/spring/docs/2.5.x/reference/transaction.html
	 * The most important concepts to grasp with regard to the Spring Framework's declarative transaction support are that this support is enabled via AOP proxies, 
	 * and that the transactional advice is driven by metadata (currently XML- or annotation-based). 
	 * The combination of AOP with transactional metadata yields an AOP proxy that uses a TransactionInterceptor
	 * in conjunction with an appropriate PlatformTransactionManager implementation to drive transactions around method invocations.
	 * 
	 * <aop:advisor id="transactionAdvisor" pointcut-ref="txAdvisePointCut" advice-ref="txAdvice"/>
	 * */
	@Test
	@Commit
	@Transactional
	public void pdsFileRepo()
	{
		Long fno = 1L;
		String newName = "updateFile1.doc";
		
		int count = pdsFileRepo.updatePDSFile(fno, newName);
		
		System.out.println("Count is : " + count);
	}
}
