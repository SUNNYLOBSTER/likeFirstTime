package com.min.edu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.min.edu.model.service.IUsers_Service;

@Controller
public class Users_Controller {

	@Autowired
	private IUsers_Service service;
	
}
