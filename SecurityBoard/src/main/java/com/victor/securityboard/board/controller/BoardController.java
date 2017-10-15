package com.victor.securityboard.board.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.victor.securityboard.board.domain.BoardVO;
import com.victor.securityboard.board.service.BoardService;
import com.victor.securityboard.member.domain.MemberVO;
import com.victor.securityboard.security.SecurityUtil;

@Controller
@RequestMapping(value="/board")
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@Autowired
	SecurityUtil util;
	
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
	
	@RequestMapping(value="/insertForm")
	public String insertForm(){
		return "/board/insertBoard";
	}
	
	@RequestMapping(value="/insertAction")
	public void insertAction(BoardVO boardVO, HttpServletResponse res) throws IOException{
		
		MemberVO memberVO = new MemberVO();
		
		memberVO = util.getCurrentMember();
		
		if(memberVO != null){
			boardVO.setUser_id(memberVO.getId());
			
			boardService.insertBoard(boardVO);
			
			res.sendRedirect("/board/list");
		}else{
			throw new AccessDeniedException("해당 세션에서 사용자를 찾을 수 없습니다.");
		}
	}
}
