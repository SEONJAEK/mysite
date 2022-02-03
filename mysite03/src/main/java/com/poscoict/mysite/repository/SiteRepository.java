package com.poscoict.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.mysite.exception.UserRepositoryException;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.SiteVo;
import com.poscoict.mysite.vo.UserVo;

@Repository
public class SiteRepository {
	
	
	@Autowired
	private SqlSession sqlSession;
	
	
	public SiteVo selectAll() {
		return sqlSession.selectOne("site.selectAll");
	}
	
	public int update(SiteVo siteVo) {
		return sqlSession.update("site.update",siteVo); 
	}

	
	
}
