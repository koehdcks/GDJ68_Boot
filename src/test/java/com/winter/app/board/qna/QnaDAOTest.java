package com.winter.app.board.qna;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.winter.app.board.BoardVO;

@SpringBootTest
class QnaDAOTest {

	@Autowired
	private QnaDAO qnaDAO;

	@Test
	void addTest() throws Exception {
		for (int i = 0; i < 100; i++) {
			
			BoardVO boardVO = new BoardVO();
			
			boardVO.setBoardTitle("qnatitle"+i);
			boardVO.setBoardWriter("qnawriter"+i);
			boardVO.setBoardContents("qnacontents"+i);
			int result = qnaDAO.add(boardVO);
			
			// 과부화 방지를 위한 잠깐 쉬는 코드
			if (i % 10 == 0) { // 10개씩 넣고 쉬기
				Thread.sleep(500);
			}
			
		}
		System.out.println("finish");
	}

}
