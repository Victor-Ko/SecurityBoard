package com.victor.securityboard.board.service;

import com.victor.securityboard.board.domain.BoardVO;

public interface BoardService {

	//게시글 등록
	public void insertBoard(BoardVO boardVO);
}
