package com.winter.app.board;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString //로그에 변수에들어있는값들이 찍힘
public class BoardVO {
	private Long boardNo;
	private String boardTitle;
	private String boardWriter;
	private String boardContents;
	private Date boardDate;
	private Long boardHit;
}
