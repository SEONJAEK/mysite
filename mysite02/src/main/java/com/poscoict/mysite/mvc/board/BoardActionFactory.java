package com.poscoict.mysite.mvc.board;

import com.poscoict.web.mvc.Action;
import com.poscoict.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		System.out.println(actionName);
		if("writeform".equals(actionName)) {
			action = new WriteFormAction();
		}else if("write".equals(actionName)) {
			action = new WriteAction();//아직구현 안함
		}else if("delete".equals(actionName)) {
			action = new DeleteAction();
		}else if("updateform".equals(actionName)) {
			action = new UpdateFormAction();
		}else if("update".equals(actionName)) {
			action = new UpdateAction();
		}else if("view".equals(actionName)) {
			action = new ViewAction();
		}else if("replyform".equals(actionName)) {
			action = new ReplyFormAction();
		}else if("reply".equals(actionName)) {
			action = new ReplyAction();
		}else {
			action = new ListAction();
		}
		return action;
	}

}
