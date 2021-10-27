package com.springbook.biz.user.impl;

import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;

public class UserServiceImpl implements UserService{
	private UserDAO userDAO;
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}


	public UserVO getUser(UserVO vo) {
		return userDAO.getUser(vo);
	}
}
