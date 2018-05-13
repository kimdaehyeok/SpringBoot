package com.example.demo.pds;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.pdsboard.PDSBoardCascading;
import com.example.demo.domain.pdsboard.PDSFile;
import com.example.demo.pds_repository.PDSFileCascadingRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PDSBoardCascadingTests 
{
	@Autowired
	private PDSFileCascadingRepository pdsRepo;
	
	/* Cascading 설정 없이 테스트 한다면, an unsaved transient instance 에러가 발생한다.
	 * 발생하는 원인은 JPA에서 한번에 여러 Entity 객체들의 상태를 변경해 주어야 하기 때문이다.
	 * 즉, 한번에 PDSBoard 객체로 보관해야 하고, PDSFile의 상태도 보관해야 하기 때문이다. 
	 * */
	@Test
	public void cascadingTests()
	{
		PDSBoardCascading pds = new PDSBoardCascading();
		pds.setPname("DOCUMENT 1-2");
		
		PDSFile file1 = new PDSFile();
		file1.setPdsfiles("file1.doc");
		
		PDSFile file2 = new PDSFile();
		file2.setPdsfiles("file2.doc");
		
		pds.setFiles(Arrays.asList(file1,file2));
		
		pdsRepo.save(pds);
	}
}
