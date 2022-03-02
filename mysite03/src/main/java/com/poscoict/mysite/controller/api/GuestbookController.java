package com.poscoict.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poscoict.mysite.dto.JsonResult;
import com.poscoict.mysite.service.GuestbookService;
import com.poscoict.mysite.vo.GuestbookVo;

@RestController("/guestbookApiController")
@RequestMapping("/api/guestbook")
public class GuestbookController {
	//@getmapping 
	//service autowired
	//api호출 -> join했던거랑 비교하며 봐
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public JsonResult add(@RequestBody GuestbookVo guestbookVo) {
		guestbookService.addMessage(guestbookVo);
		return JsonResult.success(guestbookVo); 
	}
	
	@RequestMapping(value="/{no}/{password}", method=RequestMethod.DELETE)
	public JsonResult delete(
			@PathVariable(value="no") Long no, 
			@PathVariable(value="password") String password) {
		GuestbookVo vo = new GuestbookVo();
		vo.setNo(no);
		vo.setPassword(password);
		guestbookService.deleteMessage(no, password);
		return JsonResult.success(vo);
	}
	
	@RequestMapping(value="/list/{lastNo}", method=RequestMethod.GET)
	public JsonResult list(@PathVariable(value="lastNo") Long lastNo) {
      List<GuestbookVo> list = guestbookService.getList(lastNo);
      return JsonResult.success(list);
	}
}
