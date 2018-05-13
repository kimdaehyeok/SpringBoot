package com.example.demo.pds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.pdsboard.PDSBoardCascading;
import com.example.demo.domain.pdsboard.PDSFile;
import com.example.demo.pds_repository.PDSFileCascadingRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PDSFileDummyInsert 
{
	@Autowired
	private PDSFileCascadingRepository pdsRepo;
	
	@Test
	@Commit
	@Transactional
	public void dummyInsert()
	{
		List<PDSBoardCascading> list = new ArrayList<>();
		
		IntStream.range(1, 100).forEach
		(i -> {
			PDSBoardCascading pds = new PDSBoardCascading();
			pds.setPname("자료 " + i);
			
			PDSFile file1 = new PDSFile();
			file1.setPdsfiles("file1.doc");
			
			PDSFile file2 = new PDSFile();
			file2.setPdsfiles("file2.doc");
			
			pds.setFiles(Arrays.asList(file1, file2));
			
			list.add(pds);
		});
		
		pdsRepo.saveAll(list);
	}
}
