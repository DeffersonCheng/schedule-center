package com.frame.core.service.account.impl;

import com.frame.core.components.UserAuthoritySubject;
import com.frame.core.dao.GeneralDao;
import com.frame.core.service.account.UserService;
import com.frame.core.utils.AesEncryptUtils;
import com.frame.core.utils.EncriptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
	private final static Logger LOGGER=LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	GeneralDao dao;
	@Override
	public int login(String userLoginVerification, String userPassword) {
		if (StringUtils.isEmpty(userLoginVerification)||StringUtils.isEmpty(userPassword)) return 1;
		if (userLoginVerification.length()>31||userPassword.length()>31) return 1;
		try {
			userPassword=AesEncryptUtils.aesDecrypt(userPassword);
		} catch (Exception e) {
			LOGGER.error("解密密码出现异常",e);
			return 1;
		}
		userPassword=EncriptUtil.encriptSHA1(userPassword);
		List<?> list=dao.getHibernateTemplate().find("from UserEntity where userLoginVerification=? and userPassword=?", userLoginVerification,userPassword);
		if (list.size()>0){
			UserAuthoritySubject.setAccountSubject(list.get(0));//这句话应当放在controller中
			return 0;
		}
		return 1;
	}
}
