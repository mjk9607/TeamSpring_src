package com.ts.kaikei.services.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ts.kaikei.dao.CustomerDAO;
import com.ts.kaikei.dao.StatementDAO;

import com.ts.kaikei.services.AccountService;
import com.ts.kaikei.vo.CustomerVO;
import com.ts.kaikei.vo.StatementListVO;
import com.ts.kaikei.vo.StatementVO;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
	@Autowired
	StatementDAO statementDAO;
	@Autowired
	CustomerDAO customerDAO;


	
	@Override
	public List<StatementListVO> getStatements(String company_cd) {
		return statementDAO.getStatementList(company_cd);
	}

	@Override
	public List<CustomerVO> getCustomerList(String company_cd, String searchParam) {
		if(searchParam == null) {
			searchParam = "";
		}
		
		Map<String, String> searchParams = new HashMap<String, String>();
		searchParams.put("company_cd", company_cd);
		searchParams.put("searchParam", searchParam);
		
		return customerDAO.getCustomerList(searchParams);
	}
	
	public void addStatement(StatementVO statementVO, String userId, String company_cd) {
		
		statementVO.setCompany_cd(company_cd);
		statementVO.setEnt_id(userId);
		statementVO.setEnt_prog("Web_kaikei");
		statementVO.setMod_id(userId);
		statementVO.setMod_prog("Web_kaikei");
		
		statementDAO.addStatement(statementVO);
	}
	
	@Override
	public CustomerVO getCustomerDetail(String company_cd, String cus_cd) {
		
		Map<String, String>param = new HashMap<String, String>();
		param.put("company_cd", company_cd);
		param.put("cus_cd", cus_cd);
		
		return customerDAO.getCustomerDetail(param);
	}

	@Override
	public boolean addCustomer(String company_cd, CustomerVO customerVO, String userId) {
		 
		customerVO.setCompany_cd(company_cd);
		
		customerVO.setEnt_id(userId);
		customerVO.setMod_id(userId);
		
		customerVO.setEnt_prog("Web-kaikei");
		customerVO.setMod_prog("Web-kaikei");
		
		customerDAO.addCustomer(customerVO);
		
		return true;
		
	}
	
	@Override
	public boolean updateCustomer(String company_cd, CustomerVO customerVO, String userId) {
		
		customerVO.setCompany_cd(company_cd);
		customerVO.setMod_id(userId);
		customerVO.setMod_prog("Web-kaikei");
		
		
		customerDAO.updateCustomer(customerVO);
		
		return true;
	}

	@Override
	public boolean deleteCustomer(String company_cd, String cus_cd) {
		
		Map<String, String>param = new HashMap<String, String>();
		param.put("company_cd", company_cd);
		param.put("cus_cd", cus_cd);
		
		customerDAO.deleteCustomer(param); 
		
		return true;
	}

	@Override
	public int customerCodeCheck(String cus_cd, String company_cd) {
		
		Map<String, String>param = new HashMap<String, String>();
		param.put("company_cd", company_cd);
		param.put("cus_cd", cus_cd);
		
		return customerDAO.customerCodeCheck(param);
	}
	
}
