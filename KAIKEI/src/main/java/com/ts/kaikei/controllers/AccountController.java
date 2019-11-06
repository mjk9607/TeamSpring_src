package com.ts.kaikei.controllers;

import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ts.kaikei.services.AccountService;
import java.util.*;
import com.ts.kaikei.vo.*;
import com.ts.kaikei.dao.*;

@Controller
public class AccountController {
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private CustomerDAO customerDAO;

	/*
	 *  @RequestMapping(value = "/account/*.do")
	 */
	
	@RequestMapping(value = "/account/ledger.do", method = RequestMethod.GET)
	public String ledger(StatementVO sVO, HttpSession httpSession, Model model) {
		CompanyVO ctl = accountService.getCompanyOf(getCompanyCode(httpSession));
		logger.info("Call : /account/ledger.jsp - GET by " + ctl.getTitle());
		
		String company_cd = getCompanyCode(httpSession);		
		List<StatementVO> list = accountService.getStatements(company_cd);
		accountService.codeToName(list);
		
		model.addAttribute("statements", list);
		
		return "/account/ledger";
	}
	

	@RequestMapping(value = "/account/addStatement.do", method = RequestMethod.POST)
	public String addStatement(StatementVO sVO, HttpSession httpSession) {
		String ccd = getCompanyCode(httpSession);
		CompanyVO ctl = accountService.getCompanyOf(ccd);		
		logger.info("Call : /account/addStatement.jsp - POST by " + ctl);
		
		sVO.setCompany_cd(ccd);
		sVO.setSeq(accountService.getMaxSeq(ccd) + 1);
		sVO.setEnt_id(getUserVO(httpSession).getId());
		sVO.setEnt_prog("web_kaikei");
		sVO.setMod_id(getUserVO(httpSession).getId());
		sVO.setMod_prog("web_kaikei");
		
		accountService.addStatement(sVO);
		
		return "/account/ledger";
	}
	
	private String getCompanyCode(HttpSession httpSession) {
		return ((UserVO)httpSession.getAttribute("userVO")).getCompany_cd();
	}
	
	private UserVO getUserVO(HttpSession httpSession) {
		return ((UserVO)httpSession.getAttribute("userVO"));
	}
}
