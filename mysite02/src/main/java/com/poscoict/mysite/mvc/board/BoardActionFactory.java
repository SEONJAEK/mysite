package com.poscoict.mysite.mvc.board;

import com.poscoict.web.mvc.Action;
import com.poscoict.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("writeform".equals(actionName)) {
			action = new WriteFormAction();
		}else if("write".equals(actionName)) {
			action = new WriteAction();//아직구현 안함
		}else if("delete".equals(actionName)) {
			action = new DeleteAction();//아직구현 안함
		}else {
			action = new ListAction();
		}
		return action;
	}

}
