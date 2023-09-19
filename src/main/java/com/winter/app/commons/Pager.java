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
	//한 페이지당 보여줄 갯수
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
		
	// 1. 보여질 data 갯수
	public void makeRowNum() {
		this.startRow = (this.getPage()-1)*this.getPerPage();		// *=10의자리 | +1=1의자리	
		
	}
	
	// 2. data 갯수에 따른 totalPage
	public void makePageNum(Long total) {
		// 2_1. 전체 페이지 수 
		this.totalPage = total/this.getPerPage();
		if(total%this.getPerPage() != 0) {
			this.totalPage++;
		}
		
		// 2_2. 전체 페이지 수로 block 나누기
		long perBlock = 5;

		long totalBlock = this.totalPage/perBlock;
		if(this.totalPage%perBlock != 0) {
			totalBlock++;
		}
		
		// 2_3. 현재 page 번호로 block 구하기
		long curBlock = this.getPage()/perBlock;

		if(this.getPage()%perBlock != 0) {
			curBlock++;
		}
		

		// 2_4. 현재 block의 시작+마지막 번호 구하기
		this.startNum = (curBlock-1)*perBlock+1;
		this.lastNum = curBlock*perBlock;
		
		// 2_5. 이전*다음 block 활성화
		if(curBlock>1) {
			this.pre=true;
		}

		if(curBlock<totalBlock) {
			this.next=true;
		}
		

		// 2_5_1. 마지막 block일 때
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
