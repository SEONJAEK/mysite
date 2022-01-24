package com.poscoict.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.cj.Session;
import com.poscoict.mysite.repository.BoardRepository;
import com.poscoict.mysite.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;
	//새글, 답글 달기
	public boolean addContents(BoardVo vo) {
		if(vo.getGroupNo() != null) {
			increaseGroupOrderNo(vo);
		}
		return boardRepository.replyUpdate(vo);
	}
	
	//글 한개 보기//view에서 아마 에러날 것이다. 
	public List<BoardVo> getContents(Long no){
		return boardRepository.findOne(no);
	}
	
	//글 수정하기 전, 
	public List<BoardVo> getContents(Long no, Long userNo){
		
		return boardRepository.findOne(no);
	}
	
	//글 수정
	public Boolean updateContents(BoardVo vo) {
		return false;
	}
	
	//글 삭제
	public Boolean deleteContents(Long no, Long userNo) {
		return false;
	}
	
	//글 리스트 (찾기 결과)
	public Map<String, Object>getCotentsList(int currentPage, String keyword) {
		Map<String, Object> map = new HashMap<>();
		map.put("list", null);
		map.put("totalCount", 0);
		map.put("...", map);
		
		return map;
	}
	
	private boolean increaseGroupOrderNo(BoardVo vo) {
		boolean result = boardRepository.replyUpdate(vo);
		return result;
	};
}
