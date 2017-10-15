package com.victor.securityboard.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.victor.securityboard.board.domain.BoardVO;

public class BoardDaoImpl implements BoardDao{

	@Autowired
	SqlSession session;
	
	@Override
	public void insertBoard(BoardVO boardVO) {
		session.insert("BoardDao.insertBoard", boardVO);
	}

}
