package com.victor.securityboard.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.securityboard.board.dao.BoardDao;
import com.victor.securityboard.board.domain.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDao boardDao;
	
	@Override
	public List<BoardVO> selectBoard(BoardVO boardVO) {
		return boardDao.selectBoard(boardVO);
	}

}
