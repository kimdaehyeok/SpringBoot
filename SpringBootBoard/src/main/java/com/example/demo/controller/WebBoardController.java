package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.WebBoard;
import com.example.demo.persistence.WebBoardRepository;
import com.example.demo.vo.PageMaker;
import com.example.demo.vo.PageVO;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/boards/")
@Log
public class WebBoardController 
{
	@Autowired
	private WebBoardRepository boardRepo;
	
	@GetMapping("listPageAnnotation")
	public void list(@PageableDefault(direction=Direction.DESC 
									  ,sort="bno"
									  ,size=10
									  ,page=0) Pageable page)
	{
		log.info("list() called..." + page); 
	}
	
	@GetMapping("listVo")
	@ResponseBody
	public String listVo(PageVO vo, int page, int size)
	{
		Pageable paging = vo.makePageable(0, "bno");
		
		log.info("get Page : " + vo.getPage());
		log.info("get Size : " + vo.getSize());
		log.info("Test Value : " + vo.getTestValue());
		
		log.info("Size : " + size);
		log.info("Page : " + page);
		
		return "test";
	}
	
	@GetMapping("list")
	public void list(PageVO vo, Model model)
	{
		Pageable page = vo.makePageable(0, "bno");
		
		/* Page request [number: 0, size 10, sort: bno: DESC] 의 로그를 확인할 수 있으며(number => page를 의미),
		 * 브라우저에서 page와 size를 파라미터로 전달하면 자동으로 파라미터가 수집되어 Pageable 객체가 만들어지는 것을 확인할 수 있다.
		 * */
		log.info("** Page Info : " + page);
		
		Page<WebBoard> result = boardRepo.findAll(boardRepo.makePredicate(null, null), page);
		
		model.addAttribute("result", new PageMaker(result));
	}
}


