package com.min.edu.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.model.mapper.IUsers_Dao;

@Service
public class Users_ServiceImpl implements IUsers_Service {

	@Autowired
	private IUsers_Dao dao;
	
}
