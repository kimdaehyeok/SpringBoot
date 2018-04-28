package com.example.demo;

import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.repository.BoardRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTests 
{
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void inspect()
	{
		// ?는 와일드카드로 클래스가 명확하지 않은 경우 사용한다.
		// 실제 객체의 클래스 이름.
		Class<?> clz = boardRepo.getClass();
		
		// com.sum.Proxy로 시작하는 경우 Java의 Dynamic Proxy 기능에 의해 동적으로 생성된 클래스 임을 알 수 있다.
		System.out.println("** Class Name : " + clz.getName());
		System.out.println("----------------------------------");
		// 클래스가 구현하고 있는 인터페이스 목록.
		Class<?>[] interfaces = clz.getInterfaces();
		
		// '$'는 주로 내부 클래스 임을 알려준다.
		Stream.of(interfaces).forEach(inter -> System.out.println("** " + inter.getName()));
		
		System.out.println("----------------------------------");
		Class<?> superClass = clz.getSuperclass();
		
		System.out.println("** SuperClass : " + superClass.getName());
	}
}
