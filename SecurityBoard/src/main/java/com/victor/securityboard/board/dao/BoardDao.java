package com.victor.securityboard.board.dao;

import java.util.List;

import com.victor.securityboard.board.domain.BoardVO;

public interface BoardDao {

	public int selectBoardCount(BoardVO boardVO);
	
	public List<BoardVO> selectBoard(BoardVO boardVO);
	
}
