package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardVO;
import com.winter.app.board.FileVO;
import com.winter.app.commons.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/notice/*")
@Slf4j  // lombok 사용 : log -> sysout
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "notice";
	}
	
	@GetMapping("fileDown")
	public String getFileDown(Model model,FileVO fileVO) throws Exception{
		fileVO = noticeService.getFileDetail(fileVO);
		model.addAttribute("fileVO", fileVO);
		return "fileDownView";
	}
	
	
	@GetMapping("list")
	public String gerList(Pager pager, Model model)throws Exception{
		List<BoardVO> ar = noticeService.getList(pager);
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
		// ERROR, WARN, INFO, DEBUG, TRACE
		log.warn("getList 실행");
		return "board/list";
	}
	
	@GetMapping("add")
	public String add() throws Exception{
		return "board/add";
	}
	
	@PostMapping("add")
	public String add(NoticeVO noticeVO,MultipartFile [] files) throws Exception{
	
		int result=noticeService.add(noticeVO,files);
		
		return "redirect:./list";
	}
	@GetMapping("detail")
	public String getDetail(NoticeVO noticeVO,Model model) throws Exception{
		BoardVO boardVO = noticeService.getDetail(noticeVO);
		model.addAttribute("boardVO", boardVO);
		return "board/detail";
	}
	
	@GetMapping("update")
	public String setUpdate(NoticeVO noticeVO,Model model) throws Exception{
		BoardVO boardVO = noticeService.getDetail(noticeVO);
		model.addAttribute("boardVO",boardVO);
		return "board/update";
	}
	@PostMapping("update")
	public String setUpdate(NoticeVO noticeVO) throws Exception{
		long boardNo = noticeVO.getBoardNo();
		int result=noticeService.setUpdate(noticeVO);
		return "redirect:detail?boardNo="+boardNo;
	}
	
	@GetMapping("delete")
	public String setDelete(NoticeVO noticeVO) throws Exception{
		int result=noticeService.setDelete(noticeVO);
		return "redirect:list";
	}
	
	
}
