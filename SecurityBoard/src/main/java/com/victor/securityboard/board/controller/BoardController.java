package com.victor.securityboard.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.victor.securityboard.board.domain.BoardVO;
import com.victor.securityboard.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/list")
	public ModelAndView getBoard(@RequestParam(defaultValue="1") int curPage, BoardVO boardVO) {
		
		ModelAndView  mav 		= new ModelAndView("/board/list");
		List<BoardVO> boardList = null;
		int 		  count     = 0;
		int 		  pageSize  = 5;	// 한페이지에 몇개나 보여줄것인가
		int           blockSize = 5;	// 페이지에 보여줄 넘버링 블록 갯수
		int			  startNum  = 1;
		
		
		try {
			// 1. 카운트가 만약 23개다
			// 2. 페이지당 5개를 출력한다.
			// 3. blockSize는 23/5 처리 후 ceil 함수로 +1 처리. 즉 blockSize는 ceil(4.6) = 5
			// 4. curPage는 프론트로부터 받아서 처리한다.
			// 5. startNum = ?? curPage가 2 라면 (curPage-1) * pageSize 
			// 6. endNum = startNum + pageSize - 1
			
			count = boardService.selectBoardCount(boardVO);
			
			if(count > 0) {
				blockSize = (int)Math.ceil(count/pageSize);	// 5
				startNum  = (curPage-1) * blockSize;
				
				boardVO.setPageSize(pageSize);
				boardVO.setStartNum(startNum);
				
				boardList = boardService.selectBoard(boardVO);
				
				mav.addObject("list", boardList);
				mav.addObject("blockSize", blockSize);
			}else {
				// 데이터 없음 처리
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			mav.addObject("error", "데이터를 조회할 수 없습니다.");
		}
		
		return mav;
	}
	
}
