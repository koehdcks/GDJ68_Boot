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
	//페이지 번호
	private Long page;
	//한 페이지당 보여줄 페이지번호갯수
	private Long perPage;
	//총 페이지 갯수
	private Long totalPage;
	//해당 페이지의 첫번째 페이지번호
	private Long startNum;
	//해당 페이지의 마지막 페이지번호
	private Long lastNum;
	// 이전 btn [true=중간블럭 | false=첫블럭]
	private boolean pre;
	// 다음 btn [true=중간블럭 | false=마지막블럭]
	private boolean next;
		
	public void makeRowNum() {
		this.startRow = (this.getPage()-1)*this.getPerPage()+1;
		this.lastRow = this.getPage()*this.getPerPage();
		
	}
	
	public void makePageNum(Long total) {
		//1. 전체 갯수로 전체 페이지 수 구하기
		this.totalPage = total/this.getPerPage();
		if(total%this.getPerPage()!=0) {
			this.totalPage++;
		}
		
		//2. 전체 페이지수로 전체 block 수 구하기
		
		long perBlock=5;//한페이지에 출력할 번호의 갯수
		
		long totalBlock = this.totalPage/perBlock;
		if(this.totalPage%perBlock!=0) {
			totalBlock++;
		}
		
		//3.현재 page번호로 블럭번호 구하기
		//현재 블록 번호
		long curBlock = this.getPage()/perBlock;
		if(this.getPage()%perBlock!=0) {
			curBlock++;
		}
		
		//4. 현재 블럭번호의 시작번호와 끝번호 구하기
		//curBlock  startNum  lastNum
		//		1			1			5
		//		2			6			10		
		//		3			11			15
		this.startNum=(curBlock-1)*perBlock+1;
		this.lastNum=curBlock*perBlock;
		
		//이전 블럭 활성화 여부
		if(curBlock>1) {
			this.pre = true;
		}
		
		//다음 블럭 활성화 여부
		if(curBlock<totalBlock) {
			this.next = true;
		}
		
		// 현재 블럭이 마지막 블럭번호와 같다면 lastNum을 totalBlock숫자를 대입
		if(!this.next) {
			this.lastNum=totalPage; 
		}
		
	}
	
	public Long getStartRow() {
		if(this.startRow == null || this.startRow<0) {
			return 0L;
		}
		return this.startRow;
	}
	public Long getLastRow() {
		if(this.lastRow == null || this.lastRow<0) {
			return 10L;
		}
		return this.lastRow;
	}
	public String getSearch() {
		if(this.search == null) {
			return "";
		}
		return this.search;
	}
	public Long getPage() {
		if(this.page==null) {
			this.page=1L;
		}
		return page;
	}
	public Long getPerPage() {
		if(this.perPage==null) {
			this.perPage=10L;
		}
		return perPage;
	}
}
