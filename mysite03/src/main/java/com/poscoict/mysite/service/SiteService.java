package com.poscoict.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.mysite.repository.SiteRepository;
import com.poscoict.mysite.vo.SiteVo;
import com.poscoict.mysite.vo.UserVo;

@Service
public class SiteService {
   @Autowired
   private SiteRepository siteRepository;
   
   // 화면에 띄우기
   public SiteVo selectAll() {
      return siteRepository.selectAll();
   }
   
   public int update(SiteVo vo) {
      // 변경하기
      return siteRepository.update(vo);
   }
   
   
}