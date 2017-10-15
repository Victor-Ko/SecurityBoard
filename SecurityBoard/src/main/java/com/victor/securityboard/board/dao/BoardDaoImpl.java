package com.victor.securityboard.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.victor.securityboard.board.domain.BoardVO;

@Repository
public class BoardDaoImpl implements BoardDao{

	@Autowired
	SqlSession session;
	
	@Override
	public List<BoardVO> selectBoard(BoardVO boardVO) {
		return session.selectList("BoardDao.selectBoard", boardVO);
	}

	@Override
	public int selectBoardCount(BoardVO boardVO) {
		return session.selectOne("BoardDao.selectBoardCount", boardVO);
	}

}
