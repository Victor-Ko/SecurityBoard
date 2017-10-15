package com.victor.securityboard.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import org.springframework.stereotype.Service;

import com.victor.securityboard.board.dao.BoardDao;
import com.victor.securityboard.board.domain.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDao boardDao;
	
	@Override
	public void insertBoard(BoardVO boardVO) {
		boardDao.insertBoard(boardVO);
	}
	
	@Override
	public List<BoardVO> selectBoard(BoardVO boardVO) {
		return boardDao.selectBoard(boardVO);
	}

}
