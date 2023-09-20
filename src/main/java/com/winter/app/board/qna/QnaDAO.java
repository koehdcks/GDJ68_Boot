package com.winter.app.board.qna;

import org.apache.ibatis.annotations.Mapper;

import com.winter.app.board.BoardDAO;

@Mapper //DAO역할이기에 mapper는 필수!
public interface QnaDAO extends BoardDAO{
//QnaDAO는 인터페이스로 BoardDAO 상속
}
