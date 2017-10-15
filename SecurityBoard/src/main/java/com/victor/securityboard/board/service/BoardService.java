package com.victor.securityboard.board.service;

import java.util.List;

import com.victor.securityboard.board.domain.BoardVO;

public interface BoardService {

	public int selectBoardCount(BoardVO boardVO);
	
	public List<BoardVO> selectBoard(BoardVO boardVO);
	
}
