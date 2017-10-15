package com.victor.securityboard.board.dao;

import com.victor.securityboard.board.domain.BoardVO;

public interface BoardDao {

	//게시글 등록
	public void insertBoard(BoardVO boardVO);
}
