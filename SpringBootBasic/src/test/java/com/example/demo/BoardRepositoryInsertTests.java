package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;

/* Save() 메소드가 호출되면, 내부에서는 엔티티 매니저가 영속 컨텍스트에서 해당 식별키를 가진 엔티티가 존재하는지 확인한다.
 * 만일 동일한 식별자가 없다면, 엔티티 매니저는 영속 컨텍스트에 엔티티를 저장하고 데이터베이스에 추가한다.
 * 만일 동일한 식별자가 있다면, 메모리에 보관되어 있는 엔티티를 수정하고 데이터베이스를 갱신하는 작업을 한다.
 * */

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryInsertTests 
{
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void insertTest()
	{
		Board board = new Board();
		
		board.setBno(1L);
		board.setTitle("board title");
		board.setContent("board contents");
		board.setWriter("user01");
		
		boardRepo.save(board);
	}
}
