package com.test.edu;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.min.edu.model.service.IBoard_Service;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class Board_JUnitTest {
	
	@Autowired
	private IBoard_Service service;

	@Test
	public void test() {
		
		
		
	}

	
	
	
	
	
}
