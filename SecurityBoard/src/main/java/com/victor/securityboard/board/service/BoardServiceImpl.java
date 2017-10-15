package com.victor.securityboard.board.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.victor.securityboard.board.dao.BoardDao;
import com.victor.securityboard.board.domain.BoardVO;

public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardDao boardDao;
	
	@Override
	public void insertBoard(BoardVO boardVO) {
		boardDao.insertBoard(boardVO);
	}

}
