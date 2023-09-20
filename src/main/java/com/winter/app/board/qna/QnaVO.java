package com.winter.app.board.qna;

import com.winter.app.board.BoardVO;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class QnaVO extends BoardVO{
	private Long ref;
	private Long step;
	private Long depth;
	
}
