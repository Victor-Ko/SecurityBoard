package com.victor.securityboard.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.victor.securityboard.board.domain.BoardVO;
import com.victor.securityboard.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/list")
	public ModelAndView getBoard(HttpServletRequest req, HttpServletResponse res, BoardVO boardVO) {
		
		ModelAndView  mav 		= new ModelAndView("/board/list");
		List<BoardVO> boardList = null;
		
		try {
			boardList = boardService.selectBoard(boardVO);
			
			mav.addObject("list", boardList);
		}catch(Exception e) {
			e.printStackTrace();
			mav.addObject("error", "데이터를 조회할 수 없습니다.");
		}
		
		return mav;
	}
	
}
