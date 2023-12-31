package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;
import com.winter.app.board.FileVO;
import com.winter.app.commons.FileManager;
import com.winter.app.commons.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor=Exception.class)
public class NoticeService implements BoardService {

	@Autowired
	private NoticeDAO noticeDAO;
	@Autowired
	private FileManager fileManager;
	
	//spEl
	//properties 값을 java에서 사용할때
	//@Value("${properties의 키}")
	@Value("${app.upload}")
	private String uploadPath;
	
	@Value("${app.board.notice}")
	private String boardName;
	
	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		pager.makeRowNum();
		Long total = noticeDAO.getCount(pager);
		pager.makePageNum(total);
		return noticeDAO.getList(pager);
	}

	@Override
	
	public int add(BoardVO boardVO,MultipartFile [] files) throws Exception {
		
		int result= noticeDAO.add(boardVO);
		
		for(int i=0;i<files.length;i++) {
			if(files[i].isEmpty()) {
				continue;
			}
			NoticeFileVO fileVO = new NoticeFileVO();
			String fileName=fileManager.save(this.uploadPath+this.boardName, files[i]);
			fileVO.setBoardNo(boardVO.getBoardNo());
			fileVO.setFileName(fileName);
			fileVO.setOriName(files[i].getOriginalFilename());
			result = noticeDAO.fileAdd(fileVO);
		}
		return result;
	}

	@Override
	public BoardVO getDetail(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.getDetail(boardVO);
	}

	@Override
	public int setUpdate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.setUpdate(boardVO);
	}

	@Override
	public int setDelete(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.setDelete(boardVO);
	}
	
	@Override
	public FileVO getFileDetail(FileVO fileVO) throws Exception {
		
		return noticeDAO.getFileDetail(fileVO);
		
	}
}
