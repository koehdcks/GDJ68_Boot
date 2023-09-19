package com.winter.app.commons;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pager {
	
	// 시작 인덱스 번호 (limit)
	private Long startRow;
	// 가져올 갯수 (limit)
	private Long lastRow;
	
	// 검색할 컬럼
	private String kind;
	// 검색어
	private String search;
	
}
