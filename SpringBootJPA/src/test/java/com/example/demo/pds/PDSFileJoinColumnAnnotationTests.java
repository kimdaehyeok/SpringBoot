package com.example.demo.pds;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.pdsboard.PDSBoardJoinColumnAnnotation;
import com.example.demo.domain.pdsboard.PDSFile;
import com.example.demo.pds_repository.PDSFileJoinColumnRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PDSFileJoinColumnAnnotationTests 
{
	@Autowired
	private PDSFileJoinColumnRepository pdsFileRepo;
	
	/* 영속성 컨텍스트란 엔티티(Entity)를 영구 저장 하는 환경을 말한다. 
	 * 엔티티 매니저로 엔티티를 저장하거나 조회하면 엔티티 매니저는 영속성 컨텍스트에 엔티티를 보관하고 관리한다.
	 * 
	 * 영속성 컨텍스트는 엔티티 매니저(Session)를 생성할 때 하나 만들어진다. 
	 * 그리고 엔티티 매니저(Session)를 통해서 영속성 컨텍스트에 접근할 수 있고 영속성 컨텍스트를 관리 할 수 있다
	 * 
	 * 여러 엔티티 매니저(Session)가 같은 영속성 컨텍스트에 접근할 수도 있다.
	 * 하나의 엔티티 매니저에 하나의 영속성 컨텍스트가 만들어진다고 생각하면 된다.
	 * */
	@Test
//	@Transactional
	public void pdsFileJoinColumnAnnotationTest()
	{
		PDSBoardJoinColumnAnnotation result = pdsFileRepo.findById(2L).get();
		
		
		System.out.println(result.getPid());
		System.out.println(result.getPname());
		System.out.println(result.getPwriter());
		
		List<PDSFile> fileResult = result.getFiles();
		
		for(PDSFile file : fileResult)
		{
			System.out.println(file.getPdsfiles());
		}
	}
}
