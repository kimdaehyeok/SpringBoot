package com.example.demo.freeboard;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.freeboard.FreeBoard;
import com.example.demo.domain.freeboard.FreeBoardReply;
import com.example.demo.freeboard_repository.FreeBoardReplyRepository;
import com.example.demo.freeboard_repository.FreeBoardRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FreeBoardAndReplyInsertDummyTest 
{
	@Autowired
	private FreeBoardRepository boardRepo;
	
	@Autowired
	private FreeBoardReplyRepository replyRepo;
	
//	@Test
	public void insertBoardDummy()
	{
		IntStream.range(1, 200).forEach(
		i -> {
			FreeBoard board = new FreeBoard();
			
			board.setTitle("Free Board " + i);
			board.setContent("Free Content " + i);
			board.setWriter("Free Writer " + i);
			
			boardRepo.save(board);
		});
	}
	
	@Test 
	@Commit
	@Transactional
	public void insertReplyDummy()
	{
		Optional<FreeBoard> result = boardRepo.findById(100L);
		
		result.ifPresent(board -> 
			{
				List<FreeBoardReply> replies = board.getReplies();
				
				FreeBoardReply reply = new FreeBoardReply();
				reply.setReply("Reply....");
				reply.setReplyer("replyer00");
				reply.setBoard(board);
				
				replies.add(reply);
				
				board.setReplies(replies);
				
				boardRepo.save(board);
			}
		);
	}
}
