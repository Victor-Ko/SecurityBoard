package com.victor.securityboard.board.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.victor.securityboard.board.domain.BoardVO;
import com.victor.securityboard.board.service.BoardService;
import com.victor.securityboard.member.domain.MemberVO;
import com.victor.securityboard.security.SecurityUtil;
import com.victor.securityboard.util.AjaxResVO;

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
	@ResponseBody
	public AjaxResVO insertAction(BoardVO boardVO, HttpServletResponse res) throws IOException{
		
		MemberVO memberVO = new MemberVO();
		
		memberVO = util.getCurrentMember();
		
		AjaxResVO ajaxResVO = new AjaxResVO();
		
		if(memberVO != null){
			boardVO.setUser_id(memberVO.getId());
			
			boardService.insertBoard(boardVO);
			
			ajaxResVO.setResult("Y");
			ajaxResVO.setMessage("게시글 등록 성공");
			ajaxResVO.setRedirectUrl("/board/list");
			
		}else{
			ajaxResVO.setResult("N");
			ajaxResVO.setMessage("게시글 등록 실패");
			ajaxResVO.setRedirectUrl("/board/list");
			//throw new AccessDeniedException("해당 세션에서 사용자를 찾을 수 없습니다.");
		}
		
		return ajaxResVO;
	}
}
